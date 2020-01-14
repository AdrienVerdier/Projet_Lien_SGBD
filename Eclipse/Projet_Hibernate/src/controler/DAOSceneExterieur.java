package controler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.SceneExterieur;
import model.Lieu;
import model.Scene;
import model.Setup;

public class DAOSceneExterieur {
	/**
	 * Cette m�thode ajoute une SceneExterieur � la base de donn�es
	 * @param SceneExterieur la SceneExterieur que l'on veut ajouter � la base de donn�es
	 */
	public static void ajouterSceneExterieur(SceneExterieur SceneExterieur) {
		Connexion.getEM().getTransaction().begin();
		Connexion.getEM().persist(SceneExterieur);
		Connexion.getEM().getTransaction().commit();
	}
	
	/**
	 * Cette m�thode ajoute un Setup � une SceneExterieur
	 * @param SceneExterieur la SceneExterieur � laquelle on veut ajouter le Setup
	 * @param Setup le Setup que l'on veut ajouter
	 */
	public static void ajouterSceneExterieurSetup(SceneExterieur SceneExterieur,Setup Setup) {	
		Connexion.getEM().getTransaction().begin();
		List<Setup> listSetup;
		listSetup = SceneExterieur.getListSetup();
		if(listSetup == null)
		{
			listSetup = new ArrayList<Setup>();
		}
		listSetup.add(Setup);
		SceneExterieur.setListSetup(listSetup);
		Connexion.getEM().getTransaction().commit();
	}

	/**
	 * Cette m�thode supprime une SceneExterieur � la base de donn�es
	 * @param SceneExterieur la SceneExterieur que l'on veut supprimer � la base de donn�es
	 */
	public static void supprimerSceneExterieur(SceneExterieur SceneExterieur) {
		Connexion.getEM().getTransaction().begin();
		SceneExterieur SceneExterieur2 = Connexion.getEM().find(SceneExterieur.class, SceneExterieur.getCodeScene());
		Connexion.getEM().remove(SceneExterieur2);
		Connexion.getEM().getTransaction().commit();
	}
	/**
	 * Cette m�thode recherche une SceneExterieur de la base de donn�es � partir de son Id
	 * @param IDSceneExterieur l'Id de la SceneExterieur que l'on recherche
	 * @return l'objet SceneExterieur recherch� 
	 */
	public static SceneExterieur rechercheSceneExterieurById(int IDSceneExterieur) {
		Connexion.getEM().getTransaction().begin();
		SceneExterieur SceneExterieur = Connexion.getEM().find(SceneExterieur.class, IDSceneExterieur);
		Connexion.getEM().getTransaction().commit();
		return SceneExterieur;
	}
	/**
	 * Cette m�thode permet de modifier une SceneExterieur existant dans la base de donn�es
	 * @param IDSceneExterieur l'Id de la SceneExterieur que l'on veut modifier 
	 * @param SceneExterieur la SceneExterieur qui contient les nouvelles donn�es de la SceneExterieur
	 */
	public static void modifierSceneExterieur(int IDSceneExterieur, SceneExterieur SceneExterieur) {
		Connexion.getEM().getTransaction().begin();
		SceneExterieur NouveauSceneExterieur = Connexion.getEM().find(SceneExterieur.class, IDSceneExterieur);
		NouveauSceneExterieur.setDescription(SceneExterieur.getDescription());
		NouveauSceneExterieur.setNocturne(SceneExterieur.getNocturne());
		Connexion.getEM().getTransaction().commit();
	}
	
	/**
	 * Cette m�thode modifie le Lieu d'une SceneExterieur
	 * @param IDSceneExterieur l'Id de la SceneInterieur dont on veut modifier le Lieu
	 * @param Lieu le nouveau Lieu
	 */
	public static void modifierSceneExterieurLieu(int IDSceneExterieur, Lieu Lieu) {
		Connexion.getEM().getTransaction().begin();
		SceneExterieur NouveauSceneExterieur = Connexion.getEM().find(SceneExterieur.class, IDSceneExterieur);;
		NouveauSceneExterieur.setCodeLieu(Lieu);
		Connexion.getEM().getTransaction().commit();
	}

	/**
	 * Cette m�thode renvoie tous les objets SceneExterieur de la base de donn�es
	 * @return une liste de tous les objets SceneExterieur de la base de donn�es
	 */
	public static ArrayList<SceneExterieur> returnAllSceneExterieur() {
		Connexion.getEM().getTransaction().begin();
		ArrayList<SceneExterieur> resultat = new ArrayList<SceneExterieur>();
		String queryString = "select s from SceneExterieur s";
		Query query = Connexion.getEM().createQuery(queryString);
		List results = query.getResultList();
		for (int i = 0; i < results.size(); i++) {
			SceneExterieur SceneExterieur = (SceneExterieur) results.get(i);
			resultat.add(SceneExterieur);
		}
		Connexion.getEM().getTransaction().commit();
		return resultat;
	};
	
	/**
	 * Cette m�thode renvoie la prochaine Id du prochain SceneExterieur
	 * @return le num�ro d'Id du prochain SceneExterieur
	 */
	public static int returnMaxIDSceneExterieur() {
		Connexion.getEM().getTransaction().begin();
		String queryString = "select s from Scene s";
		Query query = Connexion.getEM().createQuery(queryString);
		List results = query.getResultList();
		int max = 0;
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
