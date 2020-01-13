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
	 * Cette méthode ajoute une SceneInterieur à la base de données
	 * @param SceneInterieur la SceneInterieur que l'on veut ajouter à la base de données
	 */
	public static void ajouterSceneInterieur(SceneInterieur SceneInterieur) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		em.persist(SceneInterieur);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}
	
	/**
	 * Cette méthode ajoute un Setup à une SceneInterieur
	 * @param SceneInterieur la SceneInterieur à laquelle on veut ajouter le Setup
	 * @param Setup le Setup que l'on veut ajouter
	 */
	public static void ajouterSceneInterieurSetup(SceneInterieur SceneInterieur,Setup Setup) {
		EntityManager em = Connexion.ouvrirconnexion();		
		em.getTransaction().begin();
		List<Setup> listSetup;
		listSetup = SceneInterieur.getListSetup();
		if(listSetup == null)
		{
			listSetup = new ArrayList<Setup>();
		}
		listSetup.add(Setup);
		SceneInterieur.setListSetup(listSetup);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	/**
	 * Cette méthode supprime une SceneInterieur à la base de données
	 * @param SceneInterieur la SceneInterieur que l'on veut supprimer à la base de données
	 */
	public static void supprimerSceneInterieur(SceneInterieur SceneInterieur) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		SceneInterieur SceneInterieur2 = em.find(SceneInterieur.class, SceneInterieur.getCodeScene());
		em.remove(SceneInterieur2);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}
	/**
	 * Cette méthode recherche une SceneInterieur de la base de données à partir de son Id
	 * @param IDSceneInterieur l'Id du SceneInterieur que l'on recherche
	 * @return l'objet SceneInterieur recherché 
	 */
	public static SceneInterieur rechercheSceneInterieurById(int IDSceneInterieur) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		SceneInterieur SceneInterieur = em.find(SceneInterieur.class, IDSceneInterieur);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return SceneInterieur;
	}
	/**
	 * Cette méthode permet de modifier une SceneInterieur existant dans la base de données
	 * @param IDSceneInterieur l'Id de la SceneInterieur que l'on veut modifier
	 * @param SceneInterieur la SceneInterieur qui contient les nouvelles données du SceneInterieur
	 */

	public static void modifierSceneInterieur(int IDSceneInterieur, SceneInterieur SceneInterieur) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		SceneInterieur NouveauSceneInterieur = em.find(SceneInterieur.class, IDSceneInterieur);
		NouveauSceneInterieur.setDescription(SceneInterieur.getDescription());
		NouveauSceneInterieur.setListSetup(SceneInterieur.getListSetup());
		NouveauSceneInterieur.setCodeTheatre(SceneInterieur.getCodeTheatre());
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}
	/**
	 * Cette méthode modifie le Theatre d'une IDSceneInterieur
	 * @param IDIDSceneInterieur l'Id de la IDSceneInterieur dont on veut modifier le Theatre
	 * @param Theatre le nouveau Theatre
	 */
	public static void modifierSceneInterieurTheatre(int IDSceneInterieur, Theatre Theatre) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		SceneInterieur NouveauSceneInterieur = em.find(SceneInterieur.class, IDSceneInterieur);
		NouveauSceneInterieur.setCodeTheatre(Theatre);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	/**
	 * Cette méthode renvoie tous les objets SceneInterieur de la base de données
	 * @return une liste de tous les objets SceneInterieur de la base de données
	 */
	public static List<SceneInterieur> returnAllSceneInterieur() {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		ArrayList<SceneInterieur> resultat = new ArrayList<SceneInterieur>();
		String queryString = "select s from SceneInterieur s";
		Query query = em.createQuery(queryString);
		List results =  query.getResultList();
		for (int i = 0; i < results.size(); i++) {
			SceneInterieur SceneInterieur = (SceneInterieur) results.get(i);
			resultat.add(SceneInterieur);
		}
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return resultat;
	};
	/**
	 * Cette méthode renvoie la prochaine Id du prochain SceneInterieur
	 * @return le numéro d'Id du prochain SceneInterieur
	 */
	public static int returnMaxIDSceneInterieur() {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		String queryString = "select s from Scene s";
		Query query = em.createQuery(queryString);
		List results = query.getResultList();
		int max = 0;
		for (int i = 0; i < results.size(); i++) {
			Scene Scene = (Scene) results.get(i);
			if(Scene.getCodeScene() >= max)
			{
				max = Scene.getCodeScene()+1;
			}
		}
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return max;
	};
}
