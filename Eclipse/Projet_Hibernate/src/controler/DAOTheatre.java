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
		Connexion.getEM().getTransaction().begin();
		Connexion.getEM().persist(Theatre);
		Connexion.getEM().getTransaction().commit();
	}

	/**
	 * Cette m�thode supprime un Theatre � la base de donn�es
	 * @param Theatre le Theatre que l'on veut supprimer � la base de donn�es
	 */
	public static void supprimerTheatre(Theatre Theatre) {
		Connexion.getEM().getTransaction().begin();
		Theatre Theatre2 = Connexion.getEM().find(Theatre.class, Theatre.getCodeTheatre());
		Connexion.getEM().remove(Theatre2);
		Connexion.getEM().getTransaction().commit();
	}

	/**
	 * Cette m�thode recherche un Theatre de la base de donn�es � partir de son Id
	 * @param IDTheatre l'Id du Theatre que l'on recherche
	 * @return l'objet Theatre recherch� 
	 */
	public static Theatre rechercheTheatreById(int IDTheatre) {
		Connexion.getEM().getTransaction().begin();
		Theatre Theatre = Connexion.getEM().find(Theatre.class, IDTheatre);
		Connexion.getEM().getTransaction().commit();
		return Theatre;
	}

	/**
	 * Cette m�thode permet de modifier un Theatre existant dans la base de donn�es
	 * @param IDTheatre l'Id du Theatre que l'on veut modifier 
	 * @param Theatre le Theatre qui contient les nouvelles donn�es du Theatre
	 */
	public static void modifierTheatre(int IDTheatre, Theatre Theatre) {
		Connexion.getEM().getTransaction().begin();
		Theatre NouveauTheatre = Connexion.getEM().find(Theatre.class, IDTheatre);
		NouveauTheatre.setDescription(Theatre.getDescription());
		Connexion.getEM().getTransaction().commit();
	}

	/**
	 * Cette m�thode renvoie tous les objets Theatre de la base de donn�es
	 * @return une liste de tous les objets Theatre de la base de donn�es
	 */
	public static ArrayList<Theatre> returnAllTheatre() {
		Connexion.getEM().getTransaction().begin();
		ArrayList<Theatre> resultat = new ArrayList<Theatre>();
		String queryString = "select t from Theatre t";
		Query query = Connexion.getEM().createQuery(queryString);
		List results = query.getResultList();
		for (int i = 0; i < results.size(); i++) {
			Theatre Theatre = (Theatre) results.get(i);
			resultat.add(Theatre);
		}
		Connexion.getEM().getTransaction().commit();
		return resultat;
	};

	/**
	 * Cette m�thode renvoie la prochaine Id du prochain Theatre
	 * @return le num�ro d'Id du prochain Theatre
	 */
	public static int returnMaxIDTheatre() {
		Connexion.getEM().getTransaction().begin();
		String queryString = "select t from Theatre t";
		Query query = Connexion.getEM().createQuery(queryString);
		List results = query.getResultList();
		int max = 0;
		for (int i = 0; i < results.size(); i++) {
			Theatre Theatre = (Theatre) results.get(i);
			if(Theatre.getCodeTheatre() >= max)
			{
				max = Theatre.getCodeTheatre()+1;
			}
		}
		Connexion.getEM().getTransaction().commit();
		return max;
	};
}
