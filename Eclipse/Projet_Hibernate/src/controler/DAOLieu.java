package controler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Clap;
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
		Lieu Lieu2 = em.find(Lieu.class, Lieu.getCodeLieu());
		em.remove(Lieu2);
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
	
	public static Lieu rechercheLieuByDescription(String description) {
		// à faire pour que ça marche
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		Lieu Lieu = em.find(Lieu.class, description);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return Lieu;
	}

	public static void modifierLieu(int IDLieu, Lieu Lieu) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		Lieu NouveauLieu = em.find(Lieu.class, IDLieu);
		NouveauLieu.setAdresse(Lieu.getAdresse());
		NouveauLieu.setDescription(Lieu.getDescription());
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	public static ArrayList<Lieu> retrunAllLieu() {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		ArrayList<Lieu> resultat = new ArrayList<Lieu>();
		String queryString = "select l from Lieu l";
		Query query = em.createQuery(queryString);
		List results = query.getResultList();
		for (int i = 0; i < results.size(); i++) {
			Lieu Lieu = (Lieu) results.get(i);
			resultat.add(Lieu);
		}
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return resultat;
	};
	
	public static int retrunMaxIDLieu() {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		String queryString = "select l from Lieu l";
		Query query = em.createQuery(queryString);
		List results = query.getResultList();
		int max = 0;
		for (int i = 0; i < results.size(); i++) {
			Lieu Lieu = (Lieu) results.get(i);
			if(Lieu.getCodeLieu() >= max)
			{
				max = Lieu.getCodeLieu()+1;
			}
		}
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return max;
	};
}
