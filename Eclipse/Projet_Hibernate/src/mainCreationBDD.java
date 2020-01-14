import java.time.Duration;
import java.util.ArrayList;

import javax.persistence.EntityManager;

import controler.*;
import model.*;

public class mainCreationBDD {
	public static void main(String[] args) {

		Connexion.init();
		
		//creation
		
		//ATTENTION ID SCENE SONT LES MEMES POUR LA BDD DONC METTRE DIFFERENT
		SceneExterieur SceneExterieur1 = new SceneExterieur(1,"Setup 1 et 2",null,0,null);
		SceneInterieur SceneInterieur1 = new SceneInterieur(2,"Setup 3",null,null);

		Setup Setup1 = new Setup(1,"Clap 1 et 2",SceneExterieur1,null);
		Setup Setup2 = new Setup(2,"Clap 3 et 4",SceneExterieur1,null);
		Setup Setup3 = new Setup(3,"Clap 5",SceneInterieur1,null);
		
		Lieu Lieu1 = new Lieu(1,"adresse","SceneExterieur1 et 2",null);
		Lieu Lieu2 = new Lieu(2,"adresse","SceneExterieur3",null);
		Theatre Theatre1 = new Theatre(1,"SceneInterieur1 et 2",null);
		Theatre Theatre2 = new Theatre(2,"SceneInterieur3",null);
		
		SceneExterieur SceneExterieur2 = new SceneExterieur(3,"vide",null,0,Lieu1);
		SceneExterieur SceneExterieur3 = new SceneExterieur(4,"vide2",null,0,Lieu2);
		

		SceneInterieur SceneInterieur2 = new SceneInterieur(5,"vide 3",null,Theatre1);
		SceneInterieur SceneInterieur3 = new SceneInterieur(6,"vide 4",null,Theatre2);
		//voir pour faire ll'inverse ajouter un lieu puis ajouter les scenes mais un peu bizarre ?
		// est ce qu'on peut avoir un lieu sans Scene ?
		//ajout à la BDD

		DAOSceneExterieur.ajouterSceneExterieur(SceneExterieur1);
		DAOSceneInterieur.ajouterSceneInterieur(SceneInterieur1);
		
		DAOLieu.ajouterLieu(Lieu1);
		DAOLieu.ajouterLieu(Lieu2);
		DAOTheatre.ajouterTheatre(Theatre1);
		DAOTheatre.ajouterTheatre(Theatre2);
		//test
		DAOSceneExterieur.modifierSceneExterieurLieu(1, Lieu1);
		DAOSceneInterieur.modifierSceneInterieurTheatre(2, Theatre1);
		
		DAOSceneExterieur.ajouterSceneExterieur(SceneExterieur2);
		DAOSceneExterieur.ajouterSceneExterieur(SceneExterieur3);
		
		DAOSceneInterieur.ajouterSceneInterieur(SceneInterieur2);
		DAOSceneInterieur.ajouterSceneInterieur(SceneInterieur3);
		
		DAOSceneExterieur.ajouterSceneExterieurSetup(SceneExterieur1, Setup1);
		DAOSetup.ajouterSetup(Setup1);
		System.out.println(SceneExterieur1.getListSetup());
		DAOSceneExterieur.ajouterSceneExterieurSetup(SceneExterieur1, Setup2);
		DAOSetup.ajouterSetup(Setup2);
		System.out.println(SceneExterieur1.getListSetup());
		//DAOSetup.supprimerSetup(Setup2);
		System.out.println(DAOSetup.returnAllSetup());
		DAOSceneInterieur.ajouterSceneInterieurSetup(SceneInterieur1, Setup3);
		DAOSetup.ajouterSetup(Setup3);
		//DAOLieu.supprimerLieu(Lieu1);
		//DAOTheatre.supprimerTheatre(Theatre1);
		

		Clap Clap1 = new Clap(1,20,1,Setup1);
		Clap Clap2 = new Clap(2,10,1,Setup1);
		Clap Clap3 = new Clap(3,200,4,Setup2);
		Clap Clap4 = new Clap(4,150,2,Setup2);
		Clap Clap5 = new Clap(5,90,3,Setup3);
		DAOSetup.ajouterSetupClap(Setup1, Clap1);
		DAOClap.ajouterClap(Clap1);
		DAOSetup.ajouterSetupClap(Setup1, Clap2);
		DAOClap.ajouterClap(Clap2);
		DAOSetup.ajouterSetupClap(Setup2, Clap3);
		DAOClap.ajouterClap(Clap3);
		DAOSetup.ajouterSetupClap(Setup2, Clap4);
		DAOClap.ajouterClap(Clap4);
		DAOSetup.ajouterSetupClap(Setup3, Clap5);
		DAOClap.ajouterClap(Clap5);
		System.out.println(DAOClap.returnMaxIDClap()); // 5 existants donc new id = 6
		//DAOSetup.supprimerSetup(Setup2);
		//DAOClap.supprimerClap(Clap2);
		//DAOSceneExterieur.supprimerSceneExterieur(SceneExterieur1);
		DAOLieu.supprimerLieu(Lieu1);
		DAOSceneExterieur.modifierSceneExterieurLieu(1, null);
		DAOSceneExterieur.modifierSceneExterieurLieu(3, null);
		
	}
}
