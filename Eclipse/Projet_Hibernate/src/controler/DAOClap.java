package controler;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Clap;

public class DAOClap {

	/**
	 * Cette méthode ajoute un clap à la base de données
	 * @param Clap le clap que l'on veut ajouter à la base de données
	 */
	public static void ajouterClap(Clap Clap) {
		Connexion.getEM().getTransaction().begin();
		Connexion.getEM().persist(Clap);
		Connexion.getEM().getTransaction().commit();
	}

	/**
	 * Cette méthode supprime un clap à la base de données
	 * @param Clap le clap que l'on veut supprimer à la base de données
	 */
	public static void supprimerClap(Clap Clap) {
		Connexion.getEM().getTransaction().begin();
		Clap Clap2 = Connexion.getEM().find(Clap.class, Clap.getCodeClap());
		Connexion.getEM().remove(Clap2);
		Connexion.getEM().getTransaction().commit();
	}

	/**
	 * Cette méthode recherche un clap de la base de données à partir de son Id
	 * @param IDClap l'Id du clap que l'on recherche
	 * @return l'objet clap recherché 
	 */
	public static Clap rechercheClapById(int IDClap) {
		Connexion.getEM().getTransaction().begin();
		Clap Clap = Connexion.getEM().find(Clap.class, IDClap);
		Connexion.getEM().getTransaction().commit();
		return Clap;
	}

	/**
	 * Cette méthode permet de modifier un clap existant dans la base de données
	 * @param IDClap l'Id du clap que l'on veut modifier
	 * @param Clap le clap qui contient les nouvelles données du Clap
	 */
	public static void modifierCLap(int IDClap, Clap Clap) {
		Connexion.getEM().getTransaction().begin();
		Clap NouveauClap = Connexion.getEM().find(Clap.class, IDClap);
		NouveauClap.setCodeBobine(Clap.getCodeBobine());
		NouveauClap.setDuree(Clap.getDuree());
		Connexion.getEM().getTransaction().commit();
	}

	/**
	 * Cette méthode renvoie tous les objets Clap de la base de données
	 * @return une liste de tous les objets Clap de la base de données
	 */
	public static ArrayList<Clap> returnAllClap() {
		Connexion.getEM().getTransaction().begin();
		ArrayList<Clap> resultat = new ArrayList<Clap>();
		String queryString = "select c from Clap c";
		Query query = Connexion.getEM().createQuery(queryString);
		List results = query.getResultList();
		for (int i = 0; i < results.size(); i++) {
			Clap clap = (Clap) results.get(i);
			resultat.add(clap);
		}
		Connexion.getEM().getTransaction().commit();
		return resultat;
	};
	
	/**
	 * Cette méthode renvoie la prochaine Id du prochain Clap
	 * @return le numéro d'Id du prochain Clap
	 */
	public static int returnMaxIDClap() {
		Connexion.getEM().getTransaction().begin();
		String queryString = "select c from Clap c";
		Query query = Connexion.getEM().createQuery(queryString);
		List results = query.getResultList();
		int max = 1;
		for (int i = 0; i < results.size(); i++) {
			Clap clap = (Clap) results.get(i);
			if(clap.getCodeClap() >= max)
			{
				max = clap.getCodeClap()+1;
			}
		}
		Connexion.getEM().getTransaction().commit();
		return max;
	};
}
