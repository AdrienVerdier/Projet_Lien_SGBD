package controler;

import javax.swing.JComboBox;

public class gestionScene {

	public static int getTimeFilm() {
		return 100;
	}
	
	public static JComboBox<String> RemplirListeTheatre (JComboBox<String> dropDownList){
		
		// Il faut remplir notre liste avec les descriptions des théatres
		//dropDownList.addItem("codeScene");
		
		return dropDownList;
	}
	
	public static JComboBox<String> RemplirListeLieu (JComboBox<String> dropDownList){
		
		// Il faut remplir notre liste avec les descriptions des théatres
		//dropDownList.addItem("codeScene");
		
		return dropDownList;
	}
	
	public static boolean ajouterSceneInterieur(String descriptionScene, String DescriptionTheatre, boolean isNew) {
		return true;
	}
	
	public static boolean ajouterSceneExterieur(String descriptionScene, boolean isNocturne, String AdresseLieu, String descriptionLieu, boolean isNew) {
		return true;
	}
	
}
