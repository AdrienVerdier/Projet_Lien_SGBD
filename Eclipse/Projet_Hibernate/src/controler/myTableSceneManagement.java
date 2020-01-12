package controler;

import java.util.Iterator;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import model.Lieu;
import model.Theatre;
import model.Scene;
import model.SceneExterieur;
import model.SceneInterieur;

public class myTableSceneManagement extends AbstractTableModel {
	
	private static final long serialVersionUID = 8L;
	private String[] columnNames;
	private JTable table;

	public myTableSceneManagement(String[] columnNames) {
		this.columnNames = columnNames;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return gestionScene.nombreScene();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public void removeRow(int row) {
		gestionScene.supprimerScene((int) table.getValueAt(row, 0));
		
		this.fireTableDataChanged();
	}

	public Object getValueAt(int row, int col) {

		//Regarder si je peux pas créer une liste d'ID trié dans l'ordre croissant 
		
		if (gestionUtilisateur.nombreUtilisateur() != 0) {
			Iterator<utilisateur> iterator = gestionUtilisateur.getUser().iterator();

			for (int i = 0; i < row; i++)
				iterator.next();

			utilisateur utilisateurSelected = iterator.next();

			switch (col) {
			case 0:
				return utilisateurSelected.getIdUtilisateur();
			case 1:
				return utilisateurSelected.getLogin();
			case 2:
				return utilisateurSelected.getNom();
			case 3:
				return utilisateurSelected.getPrenom();
			}
		}
		return null;
	}

	public Class<? extends Object> getColumnClass(int c) {
		if (getValueAt(0, c) != null)
			return getValueAt(0, c).getClass();
		else
			return null;
	}

}
