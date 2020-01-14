package controler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Theatre;

public class DAOTheatre {
	/**
	 * Cette méthode ajoute un Theatre à la base de données
	 * @param Theatre le Theatre que l'on veut ajouter à la base de données
	 */
	public static void ajouterTheatre(Theatre Theatre) {
		Connexion.getEM().getTransaction().begin();
		Connexion.getEM().persist(Theatre);
		Connexion.getEM().getTransaction().commit();
	}

	/**
	 * Cette méthode supprime un Theatre à la base de données
	 * @param Theatre le Theatre que l'on veut supprimer à la base de données
	 */
	public static void supprimerTheatre(Theatre Theatre) {
		Connexion.getEM().getTransaction().begin();
		Theatre Theatre2 = Connexion.getEM().find(Theatre.class, Theatre.getCodeTheatre());
		Connexion.getEM().remove(Theatre2);
		Connexion.getEM().getTransaction().commit();
	}

	/**
	 * Cette méthode recherche un Theatre de la base de données à partir de son Id
	 * @param IDTheatre l'Id du Theatre que l'on recherche
	 * @return l'objet Theatre recherché 
	 */
	public static Theatre rechercheTheatreById(int IDTheatre) {
		Connexion.getEM().getTransaction().begin();
		Theatre Theatre = Connexion.getEM().find(Theatre.class, IDTheatre);
		Connexion.getEM().getTransaction().commit();
		return Theatre;
	}

	/**
	 * Cette méthode permet de modifier un Theatre existant dans la base de données
	 * @param IDTheatre l'Id du Theatre que l'on veut modifier 
	 * @param Theatre le Theatre qui contient les nouvelles données du Theatre
	 */
	public static void modifierTheatre(int IDTheatre, Theatre Theatre) {
		Connexion.getEM().getTransaction().begin();
		Theatre NouveauTheatre = Connexion.getEM().find(Theatre.class, IDTheatre);
		NouveauTheatre.setDescription(Theatre.getDescription());
		Connexion.getEM().getTransaction().commit();
	}

	/**
	 * Cette méthode renvoie tous les objets Theatre de la base de données
	 * @return une liste de tous les objets Theatre de la base de données
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
	 * Cette méthode renvoie la prochaine Id du prochain Theatre
	 * @return le numéro d'Id du prochain Theatre
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
