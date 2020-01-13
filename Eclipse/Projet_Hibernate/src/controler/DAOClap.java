package controler;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Clap;

public class DAOClap {
	//n'oublie pas de faire le lien entre la liste de Clap de la classe Setup voir tp2 Hibernate

	/**
	 * Cette méthode ajoute un clap à la base de données
	 * @param Clap le clap que l'on veut ajouter à la base de données
	 */
	public static void ajouterClap(Clap Clap) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		em.persist(Clap);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	/**
	 * Cette méthode supprime un clap à la base de données
	 * @param Clap le clap que l'on veut supprimer à la base de données
	 */
	public static void supprimerClap(Clap Clap) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		Clap Clap2 = em.find(Clap.class, Clap.getCodeClap());
		em.remove(Clap2);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	/**
	 * Cette méthode recherche un clap de la base de données à partir de son Id
	 * @param IDClap l'Id du clap que l'on recherche
	 * @return l'objet clap recherché 
	 */
	public static Clap rechercheClapById(int IDClap) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		Clap Clap = em.find(Clap.class, IDClap);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return Clap;
	}

	/**
	 * Cette méthode permet de modifier un clap existant dans la base de données
	 * @param IDClap l'Id du clap que l'on veut modifier
	 * @param Clap le clap qui contient les nouvelles données du Clap
	 */
	public static void modifierCLap(int IDClap, Clap Clap) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		Clap NouveauClap = em.find(Clap.class, IDClap);
		NouveauClap.setCodeBobine(Clap.getCodeBobine());
		NouveauClap.setDuree(Clap.getDuree());
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	/**
	 * Cette méthode renvoie tous les objets Clap de la base de données
	 * @return une liste de tous les objets Clap de la base de données
	 */
	public static ArrayList<Clap> returnAllClap() {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		ArrayList<Clap> resultat = new ArrayList<Clap>();
		String queryString = "select c from Clap c";
		Query query = em.createQuery(queryString);
		List results = query.getResultList();
		for (int i = 0; i < results.size(); i++) {
			Clap clap = (Clap) results.get(i);
			resultat.add(clap);
		}
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return resultat;
	};
	
	/**
	 * Cette méthode renvoie la prochaine Id du prochain Clap
	 * @return le numéro d'Id du prochain Clap
	 */
	public static int returnMaxIDClap() {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		String queryString = "select c from Clap c";
		Query query = em.createQuery(queryString);
		List results = query.getResultList();
		int max = 0;
		for (int i = 0; i < results.size(); i++) {
			Clap clap = (Clap) results.get(i);
			if(clap.getCodeClap() >= max)
			{
				max = clap.getCodeClap()+1;
			}
		}
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return max;
	};
}
