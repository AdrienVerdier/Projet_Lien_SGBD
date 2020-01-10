package controler;

import javax.persistence.EntityManager;

import model.Setup;

public class DAOSetup {
	public static void ajouterSetup(Setup Setup) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		em.persist(Setup);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	public static void supprimerSetup(Setup Setup) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		em.remove(Setup);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	public static Setup rechercheSetupById(int IDSetup) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		Setup Setup = em.find(Setup.class, IDSetup);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return Setup;
	}

	public static Setup modifierSetup(Setup Setup) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		Setup AncienSetup = em.find(Setup.class, Setup.getCodeSetup());
		AncienSetup.setListClap(Setup.getListClap());
		AncienSetup.setParametre(Setup.getParametre());
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return AncienSetup;
	}
}
