package controler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Theatre;

public class DAOTheatre {
	/**
	 * Cette m�thode ajoute un Theatre � la base de donn�es
	 * @param Theatre le Theatre que l'on veut ajouter � la base de donn�es
	 */
	public static void ajouterTheatre(Theatre Theatre) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		em.persist(Theatre);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	/**
	 * Cette m�thode supprime un Theatre � la base de donn�es
	 * @param Theatre le Theatre que l'on veut supprimer � la base de donn�es
	 */
	public static void supprimerTheatre(Theatre Theatre) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		Theatre Theatre2 = em.find(Theatre.class, Theatre.getCodeTheatre());
		em.remove(Theatre2);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	/**
	 * Cette m�thode recherche un Theatre de la base de donn�es � partir de son Id
	 * @param IDTheatre l'Id du Theatre que l'on recherche
	 * @return l'objet Theatre recherch� 
	 */
	public static Theatre rechercheTheatreById(int IDTheatre) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		Theatre Theatre = em.find(Theatre.class, IDTheatre);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return Theatre;
	}

	/**
	 * Cette m�thode permet de modifier un Theatre existant dans la base de donn�es
	 * @param IDTheatre l'Id du Theatre que l'on veut modifier 
	 * @param Theatre le Theatre qui contient les nouvelles donn�es du Theatre
	 */
	public static void modifierTheatre(int IDTheatre, Theatre Theatre) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		Theatre NouveauTheatre = em.find(Theatre.class, IDTheatre);
		NouveauTheatre.setDescription(Theatre.getDescription());
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	/**
	 * Cette m�thode renvoie tous les objets Theatre de la base de donn�es
	 * @return une liste de tous les objets Theatre de la base de donn�es
	 */
	public static ArrayList<Theatre> returnAllTheatre() {
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

	/**
	 * Cette m�thode renvoie la prochaine Id du prochain Theatre
	 * @return le num�ro d'Id du prochain Theatre
	 */
	public static int returnMaxIDTheatre() {
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
