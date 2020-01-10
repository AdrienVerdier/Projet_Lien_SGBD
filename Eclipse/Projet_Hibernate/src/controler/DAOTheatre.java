package controler;

import javax.persistence.EntityManager;

import model.Theatre;

public class DAOTheatre {
	public static void ajouterTheatre(Theatre Theatre) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		em.persist(Theatre);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	public static void supprimerTheatre(Theatre Theatre) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		em.remove(Theatre);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	public static Theatre rechercheTheatreById(int IDTheatre) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		Theatre Theatre = em.find(Theatre.class, IDTheatre);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return Theatre;
	}

	public static Theatre modifierTheatre(Theatre Theatre) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		Theatre AncienTheatre = em.find(Theatre.class, Theatre.getCodeTheatre());
		AncienTheatre.setDescription(Theatre.getDescription());
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return AncienTheatre;
	}
}
