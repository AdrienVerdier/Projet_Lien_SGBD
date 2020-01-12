package controler;

import java.util.Iterator;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import model.Setup;

public class myTableSetupManagement extends AbstractTableModel {
	
	private static final long serialVersionUID = 9L;
	private String[] columnNames;
	private JTable table;
	private int codeScene;

	public myTableSetupManagement(String[] columnNames, int codeScene) {
		this.columnNames = columnNames;
		this.codeScene = codeScene;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return gestionSetup.nombreSetup(codeScene);
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public void removeRow(int row) {
		gestionSetup.supprimerSetup((int) table.getValueAt(row, 0));
		
		this.fireTableDataChanged();
	}

	public Object getValueAt(int row, int col) {

		if (gestionSetup.nombreSetup(codeScene) != 0) {
			Iterator<Setup> iterator = gestionSetup.getSetup(codeScene).iterator();

			for (int i = 0; i < row; i++)
				iterator.next();

			Setup setupSelected = iterator.next();

			switch (col) {
			case 0:
				return setupSelected.getCodeSetup();
			case 1:
				return setupSelected.getParametre();
			case 2:
				return setupSelected.getCodeScene();
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
