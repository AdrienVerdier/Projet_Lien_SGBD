package controler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Setup;

public class DAOSetup {
	//n'oublie pas de faire le lien entre la liste de Setup de la classe Scene voir tp2 Hibernate
	//add à la liste de Setup de la Scene
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
		Setup Setup2 = em.find(Setup.class, Setup.getCodeSetup());
		em.remove(Setup2);
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

	public static void modifierSetup(int IDSetup, Setup Setup) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		Setup NouveauSetup = em.find(Setup.class, IDSetup);
		NouveauSetup.setListClap(Setup.getListClap());
		NouveauSetup.setParametre(Setup.getParametre());
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	public static ArrayList<Setup> retrunAllSetup() {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		ArrayList<Setup> resultat = new ArrayList<Setup>();
		String queryString = "select s from Setup s";
		Query query = em.createQuery(queryString);
		List results =  query.getResultList();
		for (int i = 0; i < results.size(); i++) {
			Setup Setup = (Setup) results.get(i);
			resultat.add(Setup);
		}
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return resultat;
	};
}
