package controler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Clap;
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
		Theatre Theatre2 = em.find(Theatre.class, Theatre.getCodeTheatre());
		em.remove(Theatre2);
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

	public static void modifierTheatre(int IDTheatre, Theatre Theatre) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		Theatre NouveauTheatre = em.find(Theatre.class, IDTheatre);
		NouveauTheatre.setDescription(Theatre.getDescription());
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	public static ArrayList<Theatre> retrunAllTheatre() {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		ArrayList<Theatre> resultat = new ArrayList<Theatre>();
		String queryString = "select t from Theatre t";
		Query query = em.createQuery(queryString);
		List results = query.getResultList();
		for (int i = 0; i < results.size(); i++) {
			Theatre Theatre = (Theatre) results.get(i);
			resultat.add(Theatre);
		}
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return resultat;
	};
	
	public static int retrunMaxIDTheatre() {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		String queryString = "select t from Theatre t";
		Query query = em.createQuery(queryString);
		List results = query.getResultList();
		int max = 0;
		for (int i = 0; i < results.size(); i++) {
			Theatre Theatre = (Theatre) results.get(i);
			if(Theatre.getCodeTheatre() >= max)
			{
				max = Theatre.getCodeTheatre()+1;
			}
		}
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return max;
	};
}
