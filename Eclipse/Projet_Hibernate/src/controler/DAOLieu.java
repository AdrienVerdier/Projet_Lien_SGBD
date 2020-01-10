package controler;

import javax.persistence.EntityManager;

import model.Lieu;

public class DAOLieu {
	public static void ajouterLieu(Lieu Lieu) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		em.persist(Lieu);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	public static void supprimerLieu(Lieu Lieu) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		em.remove(Lieu);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	public static Lieu rechercheLieuById(int IDLieu) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		Lieu Lieu = em.find(Lieu.class, IDLieu);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return Lieu;
	}

	public static Lieu modifierLieu(Lieu Lieu) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		Lieu AncienLieu = em.find(Lieu.class, Lieu.getCodeLieu());
		AncienLieu.setAdresse(Lieu.getAdresse());
		AncienLieu.setDescription(Lieu.getDescription());
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return AncienLieu;
	}
}
