package controler;

import javax.persistence.EntityManager;

import model.Clap;

public class DAOClap {

	public static void ajouterClap(Clap Clap) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		em.persist(Clap);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	public static void supprimerClap(Clap Clap) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		em.remove(Clap);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	public static Clap rechercheCalpById(int IDClap) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		Clap Clap = em.find(Clap.class, IDClap);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return Clap;
	}

	public static Clap modifierCLap(Clap Clap) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		Clap AncienClap = em.find(Clap.class, Clap.getCodeClap());
		AncienClap.setCodeBobine(Clap.getCodeBobine());
		AncienClap.setDuree(Clap.getDuree());
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return AncienClap;
	}
}
