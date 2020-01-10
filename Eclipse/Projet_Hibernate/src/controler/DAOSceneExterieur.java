package controler;

import javax.persistence.EntityManager;

import model.SceneExterieur;

public class DAOSceneExterieur {
	public static void ajouterSceneExterieur(SceneExterieur SceneExterieur) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		em.persist(SceneExterieur);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	public static void supprimerSceneExterieur(SceneExterieur SceneExterieur) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		em.remove(SceneExterieur);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	public static SceneExterieur rechercheSceneExterieurById(int IDSceneExterieur) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		SceneExterieur SceneExterieur = em.find(SceneExterieur.class, IDSceneExterieur);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return SceneExterieur;
	}

	public static SceneExterieur modifierSceneExterieur(SceneExterieur SceneExterieur) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		SceneExterieur AncienSceneExterieur = em.find(SceneExterieur.class, SceneExterieur.getCodeScene());
		AncienSceneExterieur.setDescription(SceneExterieur.getDescription());
		AncienSceneExterieur.setListSetup(SceneExterieur.getListSetup());
		AncienSceneExterieur.setNocturne(SceneExterieur.getNocturne());
		AncienSceneExterieur.setCodeLieu(SceneExterieur.getCodeLieu());
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return AncienSceneExterieur;
	}
}
