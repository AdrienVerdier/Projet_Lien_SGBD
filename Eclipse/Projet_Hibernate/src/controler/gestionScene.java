package controler;

import javax.swing.JComboBox;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

import model.Lieu;
import model.Theatre;
import model.Scene;
import model.SceneExterieur;
import model.SceneInterieur;

public class gestionScene {

	public static int getTimeFilm() {
		Iterator<SceneExterieur> iterator = gestionScene.getSceneExterieur().iterator();
		Iterator<SceneInterieur> iterator2 = gestionScene.getSceneInterieur().iterator();
		Duration dureeTotale = Duration.ZERO;
		Duration toadd = Duration.ZERO;
		
		while(iterator.hasNext()) {
			SceneExterieur scene = iterator.next();
			
			toadd = Duration.ZERO;
			toadd.plusMinutes(gestionSetup.getTimeScene(scene.getCodeScene())%60);
			toadd.plusSeconds(gestionSetup.getTimeScene(scene.getCodeScene()) - ((gestionSetup.getTimeScene(scene.getCodeScene())%60)*60));
			
			dureeTotale.plus(toadd);
		}
		
		while(iterator2.hasNext()) {
			SceneInterieur scene2 = iterator2.next();
			
			toadd = Duration.ZERO;
			toadd.plusMinutes(gestionSetup.getTimeScene(scene2.getCodeScene())%60);
			toadd.plusSeconds(gestionSetup.getTimeScene(scene2.getCodeScene()) - ((gestionSetup.getTimeScene(scene2.getCodeScene())%60)*60));
			
			dureeTotale.plus(toadd);
		}
		
		return (int)dureeTotale.toMinutes();
	}
	
	public static JComboBox<String> RemplirListeTheatre (JComboBox<String> dropDownList){
		Iterator<Theatre> theatre = DAOTheatre.retrunAllTheatre().iterator();
		Theatre tmp;
		
		while(theatre.hasNext()) {
			tmp = theatre.next();
			
			dropDownList.addItem(tmp.getDescription());
		}
		
		return dropDownList;
	}
	
	public static JComboBox<String> RemplirListeLieu (JComboBox<String> dropDownList){
		Iterator<Lieu> lieu = DAOLieu.retrunAllLieu().iterator();
		Lieu tmp;
		
		while(lieu.hasNext()){
			tmp = lieu.next();
			
			dropDownList.addItem(tmp.getAdresse());
		}
		
		return dropDownList;
	}
	
	public static boolean ajouterSceneInterieur(String descriptionScene, String DescriptionTheatre, boolean isNew) {
		
		if(isNew) {
			Theatre Theatre = new Theatre(DAOTheatre.retrunMaxIDTheatre(),DescriptionTheatre,null);
			SceneInterieur SceneInterieur = new SceneInterieur(DAOSceneInterieur.retrunMaxIDSceneInterieur(),descriptionScene,null,Theatre);
			DAOTheatre.ajouterTheatre(Theatre);
			DAOSceneInterieur.ajouterSceneInterieur(SceneInterieur);
		} else {
			Theatre theatre = DAOTheatre.rechercheTheatreByDescription(DescriptionTheatre);
			SceneInterieur SceneInterieur = new SceneInterieur(DAOSceneInterieur.retrunMaxIDSceneInterieur(),descriptionScene,null,theatre);
			DAOSceneInterieur.ajouterSceneInterieur(SceneInterieur);
		}
		
		return true;
	}
	
	public static boolean ajouterSceneExterieur(String descriptionScene, boolean isNocturne, String AdresseLieu, String descriptionLieu, boolean isNew) {
		int noc;
		if(isNocturne) {
			noc = 1;
		}else {
			noc = 0;
		}
		
		if(isNew) {
			Lieu Lieu = new Lieu(DAOLieu.retrunMaxIDLieu(),AdresseLieu,descriptionLieu,null);
			SceneExterieur SceneExterieur = new SceneExterieur(DAOSceneExterieur.retrunMaxIDSceneExterieur(),descriptionScene,null,noc,Lieu);
			DAOLieu.ajouterLieu(Lieu);
			DAOSceneExterieur.ajouterSceneExterieur(SceneExterieur);
		} else {
			Lieu Lieu = DAOLieu.rechercheLieuByDescription(descriptionLieu);
			SceneExterieur SceneExterieur = new SceneExterieur(DAOSceneExterieur.retrunMaxIDSceneExterieur(),descriptionScene,null,noc,Lieu);
			DAOLieu.ajouterLieu(Lieu);
			DAOSceneExterieur.ajouterSceneExterieur(SceneExterieur);
		}
		
		return true;
	}
	
	public static int nombreScene() {		
		return 1;
	}
	
	public static void supprimerScene(int codeScene) {
		
	}
	
	public static ArrayList<SceneInterieur> getSceneInterieur(){
		
	}
	
	public static ArrayList<SceneExterieur> getSceneExterieur(){
		
	}
	
}
