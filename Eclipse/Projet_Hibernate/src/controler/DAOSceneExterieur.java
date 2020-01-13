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
	 * Cette méthode ajoute un SceneExterieur à la base de données
	 * @param SceneExterieur le SceneExterieur que l'on veut ajouter à la base de données
	 */
	public static void ajouterSceneExterieur(SceneExterieur SceneExterieur) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		em.persist(SceneExterieur);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}
	
	/**
	 * Cette méthode ajoute un Setup à une SceneExterieur
	 * @param SceneExterieur la SceneExterieur à laquelle on veut ajouter le Setup
	 * @param Setup le Setup que l'on veut ajouter
	 */
	public static void ajouterSceneExterieurSetup(SceneExterieur SceneExterieur,Setup Setup) {
		EntityManager em = Connexion.ouvrirconnexion();		
		em.getTransaction().begin();
		List<Setup> listSetup;
		listSetup = SceneExterieur.getListSetup();
		if(listSetup == null)
		{
			listSetup = new ArrayList<Setup>();
		}
		listSetup.add(Setup);
		SceneExterieur.setListSetup(listSetup);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	/**
	 * Cette méthode supprime un SceneExterieur à la base de données
	 * @param SceneExterieur le SceneExterieur que l'on veut supprimer à la base de données
	 */
	public static void supprimerSceneExterieur(SceneExterieur SceneExterieur) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		SceneExterieur SceneExterieur2 = em.find(SceneExterieur.class, SceneExterieur.getCodeScene());
		em.remove(SceneExterieur2);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}
	/**
	 * Cette méthode recherche un SceneExterieur de la base de données à partir de son Id
	 * @param IDSceneExterieur l'Id du SceneExterieur que l'on recherche
	 * @return l'objet SceneExterieur recherché 
	 */
	public static SceneExterieur rechercheSceneExterieurById(int IDSceneExterieur) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		SceneExterieur SceneExterieur = em.find(SceneExterieur.class, IDSceneExterieur);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return SceneExterieur;
	}
	/**
	 * Cette méthode permet de modifier un SceneExterieur existant dans la base de données
	 * @param IDSceneExterieur l'Id du SceneExterieur que l'on veut modifié 
	 * @param SceneExterieur le SceneExterieur qui contient les nouvelles données du SceneExterieur
	 */
	public static void modifierSceneExterieur(int IDSceneExterieur, SceneExterieur SceneExterieur) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		SceneExterieur NouveauSceneExterieur = em.find(SceneExterieur.class, IDSceneExterieur);
		NouveauSceneExterieur.setDescription(SceneExterieur.getDescription());
		NouveauSceneExterieur.setNocturne(SceneExterieur.getNocturne());
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}
	
	/**
	 * Cette méthode modifie le Lieu d'une SceneExterieur
	 * @param IDSceneExterieur l'Id de la SceneInterieur dont on veut modifier le Lieu
	 * @param Lieu le nouveau Lieu
	 */
	public static void modifierSceneExterieurLieu(int IDSceneExterieur, Lieu Lieu) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		SceneExterieur NouveauSceneExterieur = em.find(SceneExterieur.class, IDSceneExterieur);;
		NouveauSceneExterieur.setCodeLieu(Lieu);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	/**
	 * Cette méthode renvoie tous les objets SceneExterieur de la base de données
	 * @return une liste de tous les objets SceneExterieur de la base de données
	 */
	public static List<SceneExterieur> returnAllSceneExterieur() {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		ArrayList<SceneExterieur> resultat = new ArrayList<SceneExterieur>();
		String queryString = "select s from SceneExterieur s";
		Query query = em.createQuery(queryString);
		List results = query.getResultList();
		for (int i = 0; i < results.size(); i++) {
			SceneExterieur SceneExterieur = (SceneExterieur) results.get(i);
			resultat.add(SceneExterieur);
		}
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return resultat;
	};
	/**
	 * Cette méthode renvoie la prochaine Id du prochain SceneExterieur
	 * @return le numéro d'Id du prochain SceneExterieur
	 */
	public static int returnMaxIDSceneExterieur() {
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
