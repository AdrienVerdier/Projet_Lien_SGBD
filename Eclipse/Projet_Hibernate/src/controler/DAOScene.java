package controler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Clap;
import model.Scene;

public class DAOScene {
	//toute cette classe est inutile non ?
	
	public static void ajouterScene(Scene Scene) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		em.persist(Scene);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	public static void supprimerScene(Scene Scene) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		Scene Scene2 = em.find(Scene.class, Scene.getCodeScene());
		em.remove(Scene2);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	public static Scene rechercheSceneById(int IDScene) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		Scene Scene = em.find(Scene.class, IDScene);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return Scene;
	}

	public static void modifierScene(int IDScene, Scene Scene) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		Scene NouveauScene = em.find(Scene.class, IDScene);
		NouveauScene.setDescription(Scene.getDescription());
		NouveauScene.setListSetup(Scene.getListSetup());
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	public static List<Scene> retrunAllScene() {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		ArrayList<Scene> resultat = new ArrayList<Scene>();
		String queryString = "select s from Scene s";
		Query query = em.createQuery(queryString);
		List results = query.getResultList();
		for (int i = 0; i < results.size(); i++) {
			Scene Scene = (Scene) results.get(i);
			resultat.add(Scene);
		}
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return resultat;
	};
	
	public static int retrunMaxIDScene() {
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
