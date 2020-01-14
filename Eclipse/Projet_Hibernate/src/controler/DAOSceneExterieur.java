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
	 * Cette méthode ajoute une SceneExterieur à la base de données
	 * @param SceneExterieur la SceneExterieur que l'on veut ajouter à la base de données
	 */
	public static void ajouterSceneExterieur(SceneExterieur SceneExterieur) {
		Connexion.getEM().getTransaction().begin();
		Connexion.getEM().persist(SceneExterieur);
		Connexion.getEM().getTransaction().commit();
	}
	
	/**
	 * Cette méthode ajoute un Setup à une SceneExterieur
	 * @param SceneExterieur la SceneExterieur à laquelle on veut ajouter le Setup
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
	 * Cette méthode supprime une SceneExterieur à la base de données
	 * @param SceneExterieur la SceneExterieur que l'on veut supprimer à la base de données
	 */
	public static void supprimerSceneExterieur(SceneExterieur SceneExterieur) {
		Connexion.getEM().getTransaction().begin();
		SceneExterieur SceneExterieur2 = Connexion.getEM().find(SceneExterieur.class, SceneExterieur.getCodeScene());
		Connexion.getEM().remove(SceneExterieur2);
		Connexion.getEM().getTransaction().commit();
	}
	/**
	 * Cette méthode recherche une SceneExterieur de la base de données à partir de son Id
	 * @param IDSceneExterieur l'Id de la SceneExterieur que l'on recherche
	 * @return l'objet SceneExterieur recherché 
	 */
	public static SceneExterieur rechercheSceneExterieurById(int IDSceneExterieur) {
		Connexion.getEM().getTransaction().begin();
		SceneExterieur SceneExterieur = Connexion.getEM().find(SceneExterieur.class, IDSceneExterieur);
		Connexion.getEM().getTransaction().commit();
		return SceneExterieur;
	}
	/**
	 * Cette méthode permet de modifier une SceneExterieur existant dans la base de données
	 * @param IDSceneExterieur l'Id de la SceneExterieur que l'on veut modifier 
	 * @param SceneExterieur la SceneExterieur qui contient les nouvelles données de la SceneExterieur
	 */
	public static void modifierSceneExterieur(int IDSceneExterieur, SceneExterieur SceneExterieur) {
		Connexion.getEM().getTransaction().begin();
		SceneExterieur NouveauSceneExterieur = Connexion.getEM().find(SceneExterieur.class, IDSceneExterieur);
		NouveauSceneExterieur.setDescription(SceneExterieur.getDescription());
		NouveauSceneExterieur.setNocturne(SceneExterieur.getNocturne());
		Connexion.getEM().getTransaction().commit();
	}
	
	/**
	 * Cette méthode modifie le Lieu d'une SceneExterieur
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
	 * Cette méthode renvoie tous les objets SceneExterieur de la base de données
	 * @return une liste de tous les objets SceneExterieur de la base de données
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
	 * Cette méthode renvoie la prochaine Id du prochain SceneExterieur
	 * @return le numéro d'Id du prochain SceneExterieur
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
