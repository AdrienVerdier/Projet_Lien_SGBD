package controler;

import javax.persistence.EntityManager;

import model.Scene;

public class DAOScene {
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
		em.remove(Scene);
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

	public static Scene modifierScene(Scene Scene) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		Scene AncienScene = em.find(Scene.class, Scene.getCodeScene());
		AncienScene.setDescription(Scene.getDescription());
		AncienScene.setListSetup(Scene.getListSetup());
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return AncienScene;
	}
}
