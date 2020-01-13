package controler;

import java.util.ArrayList;
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

	private int isIn(Scene scene) {
		// renvoie -1 si intérieur, 1 si extérieur, Inchallah il renvoie jamais 0
		
		Iterator<SceneExterieur> iter1 = gestionScene.getSceneExterieur().iterator();
		Iterator<SceneInterieur> iter2 = gestionScene.getSceneInterieur().iterator();
		Scene tmp;
		
		while(iter1.hasNext()) {
			tmp = iter1.next();
			if(tmp.getCodeScene() == scene.getCodeScene()) {
				return 1;
			}
		}
		
		while(iter2.hasNext()) {
			tmp = iter2.next();
			if(tmp.getCodeScene() == scene.getCodeScene()) {
				return -1;
			}
		}
		
		return 0;
	}
	
	public Object getValueAt(int row, int col) {

		//Regarder si je peux pas créer une liste d'ID trié dans l'ordre croissant 
		ArrayList<Scene> listDes = new ArrayList<Scene>();
		Iterator<SceneExterieur> iter1 = gestionScene.getSceneExterieur().iterator();
		Iterator<SceneInterieur> iter2 = gestionScene.getSceneInterieur().iterator();
		Scene tmp;
		
		while(iter1.hasNext()) {
			tmp = iter1.next();
			listDes.add(tmp);
		}
		
		while(iter2.hasNext()) {
			tmp = iter2.next();
			listDes.add(tmp);
		}
		
		ArrayList<Scene> listOrd = new ArrayList<Scene>();
		Iterator<Scene> iter3 = listDes.iterator();
		Scene toAdd = new Scene();
		int idMin = 100000000;
		int nbTotal = listDes.size();
		
		for(int i = 0; i < nbTotal ; i++) {
			while(iter3.hasNext()) {
				tmp = iter3.next();
				if(tmp.getCodeScene() < idMin) {
					idMin = tmp.getCodeScene();
					toAdd = tmp;
				}
			}
			idMin = 100000000;
			listOrd.add(toAdd);
			listDes.remove(toAdd);
		}
		
		if (gestionScene.nombreScene() != 0) {
			Iterator<Scene> iter = listOrd.iterator();

			for (int i = 0; i < row; i++)
				iter.next();

			Scene scene = iter.next();
			
			if(isIn(scene) == -1) {
				iter2 = gestionScene.getSceneInterieur().iterator();
				SceneInterieur sceneInt = new SceneInterieur();
				
				while(iter2.hasNext()) {
					sceneInt = iter2.next();
					if (sceneInt.getCodeScene() == scene.getCodeScene()) {
						switch (col) {
						case 0:
							return sceneInt.getCodeScene();
						case 1:
							return sceneInt.getDescription();
						case 2:
							return sceneInt.getCodeTheatre().getCodeTheatre();
						case 3:
							return "";
						case 4:
							return "";
						case 5:
							return "";
						case 6:
							return sceneInt.getCodeTheatre().getDescription();
						}
					}
				}
			}
			else if(isIn(scene) == 1) {
				iter1 = gestionScene.getSceneExterieur().iterator();
				SceneExterieur sceneExt = new SceneExterieur();
				
				while(iter1.hasNext()) {
					sceneExt = iter1.next();
					if (sceneExt.getCodeScene() == scene.getCodeScene()) {
						switch (col) {
						case 0:
							return sceneExt.getCodeScene();
						case 1:
							return sceneExt.getDescription();
						case 2:
							return "";
						case 3:
							return sceneExt.getCodeLieu().getCodeLieu();
						case 4:
							return sceneExt.getNocturne();
						case 5:
							return sceneExt.getCodeLieu().getAdresse();
						case 6:
							return sceneExt.getCodeLieu().getDescription();
						}
					}
				}
				
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
