package controler;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

import model.Setup;
import model.Clap;
import model.SceneExterieur;
import model.SceneInterieur;

public class gestionSetup {
	
	public static double getTimeScene(int codeScene) {
		Iterator<Setup> iterator = gestionSetup.getSetup(codeScene).iterator();
		double dureeTotale = 0;
		
		while(iterator.hasNext()) {
			Setup setup = iterator.next();
			
			Iterator<Clap> iter = gestionClap.getClap(setup.getCodeSetup()).iterator();
			while(iter.hasNext()) {
				Clap clap = iter.next();
				
				dureeTotale += clap.getDuree();
			}
		}
		
		return dureeTotale;
	}
	
	public static ArrayList<Integer> getBobine (int codeScene){
		ArrayList<Integer> listBobine = new ArrayList<Integer>();
		Iterator<Setup> iterator = gestionSetup.getSetup(codeScene).iterator();
		
		while(iterator.hasNext()) {
			Setup setup = iterator.next();
			
			Iterator<Clap> iter = gestionClap.getClap(setup.getCodeSetup()).iterator();
			while(iter.hasNext()) {
				Clap clap = iter.next();
				
				listBobine.add(clap.getCodeBobine());
			}
		}
		return listBobine;
	}
	
	public static boolean ajouterSetup(String parametreSetup, int codeScene) {
		if(DAOSceneInterieur.rechercheSceneInterieurById(codeScene) != null) {
			SceneInterieur scene = DAOSceneInterieur.rechercheSceneInterieurById(codeScene);
			Setup setup = new Setup(DAOSetup.returnMaxIDSetup(), parametreSetup, scene, null);		
			DAOSceneInterieur.ajouterSceneInterieurSetup(scene, setup);
			DAOSetup.ajouterSetup(setup);
		}
		else {
			SceneExterieur scene = DAOSceneExterieur.rechercheSceneExterieurById(codeScene);
			Setup setup = new Setup(DAOSetup.returnMaxIDSetup(), parametreSetup, scene, null);		
			DAOSceneExterieur.ajouterSceneExterieurSetup(scene, setup);
			DAOSetup.ajouterSetup(setup);
		}
		
		return true;
	}
	
	public static int nombreSetup(int codeScene) {
		return getSetup(codeScene).size();
	}
	
	public static void supprimerSetup(int codeSetup) {
		DAOSetup.supprimerSetup(DAOSetup.rechercheSetupById(codeSetup));
	}
	
	public static ArrayList<Setup> getSetup(){
		return DAOSetup.returnAllSetup();		
	}
	
	public static ArrayList<Setup> getSetup(int codeScene){
		Iterator<Setup> iter = DAOSetup.returnAllSetup().iterator();
		ArrayList<Setup> retour = new ArrayList<Setup>();
		
		while(iter.hasNext()) {
			Setup set = iter.next();
			
			if(set.getCodeScene().getCodeScene() == codeScene) {
				retour.add(set);
			}
		}
		
		return retour;
	}

}
