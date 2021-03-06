package controler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Theatre;
import model.Scene;
import model.SceneInterieur;
import model.Setup;

public class DAOSceneInterieur {
	/**
	 * Cette m�thode ajoute une SceneInterieur � la base de donn�es
	 * @param SceneInterieur la SceneInterieur que l'on veut ajouter � la base de donn�es
	 */
	public static void ajouterSceneInterieur(SceneInterieur SceneInterieur) {
		Connexion.getEM().getTransaction().begin();
		Connexion.getEM().persist(SceneInterieur);
		Connexion.getEM().getTransaction().commit();
	}
	
	/**
	 * Cette m�thode ajoute un Setup � une SceneInterieur
	 * @param SceneInterieur la SceneInterieur � laquelle on veut ajouter le Setup
	 * @param Setup le Setup que l'on veut ajouter
	 */
	public static void ajouterSceneInterieurSetup(SceneInterieur SceneInterieur,Setup Setup) {	
		Connexion.getEM().getTransaction().begin();
		List<Setup> listSetup;
		listSetup = SceneInterieur.getListSetup();
		if(listSetup == null)
		{
			listSetup = new ArrayList<Setup>();
		}
		listSetup.add(Setup);
		SceneInterieur.setListSetup(listSetup);
		Connexion.getEM().getTransaction().commit();
	}

	/**
	 * Cette m�thode supprime une SceneInterieur � la base de donn�es
	 * @param SceneInterieur la SceneInterieur que l'on veut supprimer � la base de donn�es
	 */
	public static void supprimerSceneInterieur(SceneInterieur SceneInterieur) {
		Connexion.getEM().getTransaction().begin();
		SceneInterieur SceneInterieur2 = Connexion.getEM().find(SceneInterieur.class, SceneInterieur.getCodeScene());
		Connexion.getEM().remove(SceneInterieur2);
		Connexion.getEM().getTransaction().commit();
	}
	/**
	 * Cette m�thode recherche une SceneInterieur de la base de donn�es � partir de son Id
	 * @param IDSceneInterieur l'Id du SceneInterieur que l'on recherche
	 * @return l'objet SceneInterieur recherch� 
	 */
	public static SceneInterieur rechercheSceneInterieurById(int IDSceneInterieur) {
		Connexion.getEM().getTransaction().begin();
		SceneInterieur SceneInterieur = Connexion.getEM().find(SceneInterieur.class, IDSceneInterieur);
		Connexion.getEM().getTransaction().commit();
		return SceneInterieur;
	}
	/**
	 * Cette m�thode permet de modifier une SceneInterieur existant dans la base de donn�es
	 * @param IDSceneInterieur l'Id de la SceneInterieur que l'on veut modifier
	 * @param SceneInterieur la SceneInterieur qui contient les nouvelles donn�es du SceneInterieur
	 */

	public static void modifierSceneInterieur(int IDSceneInterieur, SceneInterieur SceneInterieur) {
		Connexion.getEM().getTransaction().begin();
		SceneInterieur NouveauSceneInterieur = Connexion.getEM().find(SceneInterieur.class, IDSceneInterieur);
		NouveauSceneInterieur.setDescription(SceneInterieur.getDescription());
		NouveauSceneInterieur.setListSetup(SceneInterieur.getListSetup());
		NouveauSceneInterieur.setCodeTheatre(SceneInterieur.getCodeTheatre());
		Connexion.getEM().getTransaction().commit();
	}
	/**
	 * Cette m�thode modifie le Theatre d'une IDSceneInterieur
	 * @param IDIDSceneInterieur l'Id de la IDSceneInterieur dont on veut modifier le Theatre
	 * @param Theatre le nouveau Theatre
	 */
	public static void modifierSceneInterieurTheatre(int IDSceneInterieur, Theatre Theatre) {
		Connexion.getEM().getTransaction().begin();
		SceneInterieur NouveauSceneInterieur = Connexion.getEM().find(SceneInterieur.class, IDSceneInterieur);
		NouveauSceneInterieur.setCodeTheatre(Theatre);
		Connexion.getEM().getTransaction().commit();
	}

	/**
	 * Cette m�thode renvoie tous les objets SceneInterieur de la base de donn�es
	 * @return une liste de tous les objets SceneInterieur de la base de donn�es
	 */
	public static ArrayList<SceneInterieur> returnAllSceneInterieur() {
		Connexion.getEM().getTransaction().begin();
		ArrayList<SceneInterieur> resultat = new ArrayList<SceneInterieur>();
		String queryString = "select s from SceneInterieur s";
		Query query = Connexion.getEM().createQuery(queryString);
		List results =  query.getResultList();
		for (int i = 0; i < results.size(); i++) {
			SceneInterieur SceneInterieur = (SceneInterieur) results.get(i);
			resultat.add(SceneInterieur);
		}
		Connexion.getEM().getTransaction().commit();
		return resultat;
	};
	/**
	 * Cette m�thode renvoie la prochaine Id du prochain SceneInterieur
	 * @return le num�ro d'Id du prochain SceneInterieur
	 */
	public static int returnMaxIDSceneInterieur() {
		Connexion.getEM().getTransaction().begin();
		String queryString = "select s from Scene s";
		Query query = Connexion.getEM().createQuery(queryString);
		List results = query.getResultList();
		int max = 1;
		for (int i = 0; i < results.size(); i++) {
			Scene Scene = (Scene) results.get(i);
			if(Scene.getCodeScene() >= max)
			{
				max = Scene.getCodeScene()+1;
			}
		}
		Connexion.getEM().getTransaction().commit();
		return max;
	};
}
