package controler;

import javax.persistence.EntityManager;

import model.SceneInterieur;

public class DAOSceneInterieur {
	
	public static void ajouterSceneInterieur(SceneInterieur SceneInterieur) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		em.persist(SceneInterieur);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	public static void supprimerSceneInterieur(SceneInterieur SceneInterieur) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		em.remove(SceneInterieur);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	public static SceneInterieur rechercheSceneInterieurById(int IDSceneInterieur) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		SceneInterieur SceneInterieur = em.find(SceneInterieur.class, IDSceneInterieur);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return SceneInterieur;
	}

	public static SceneInterieur modifierSceneInterieur(SceneInterieur SceneInterieur) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		SceneInterieur AncienSceneInterieur = em.find(SceneInterieur.class, SceneInterieur.getCodeScene());
		AncienSceneInterieur.setDescription(SceneInterieur.getDescription());
		AncienSceneInterieur.setListSetup(SceneInterieur.getListSetup());
		AncienSceneInterieur.setCodeTheatre(SceneInterieur.getCodeTheatre());
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return AncienSceneInterieur;
	}
}
