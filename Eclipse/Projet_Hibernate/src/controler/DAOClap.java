package controler;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Clap;

public class DAOClap {

	/**
	 * Cette m�thode ajoute un clap � la base de donn�es
	 * @param Clap le clap que l'on veut ajouter � la base de donn�es
	 */
	public static void ajouterClap(Clap Clap) {
		Connexion.getEM().getTransaction().begin();
		Connexion.getEM().persist(Clap);
		Connexion.getEM().getTransaction().commit();
	}

	/**
	 * Cette m�thode supprime un clap � la base de donn�es
	 * @param Clap le clap que l'on veut supprimer � la base de donn�es
	 */
	public static void supprimerClap(Clap Clap) {
		Connexion.getEM().getTransaction().begin();
		Clap Clap2 = Connexion.getEM().find(Clap.class, Clap.getCodeClap());
		Connexion.getEM().remove(Clap2);
		Connexion.getEM().getTransaction().commit();
	}

	/**
	 * Cette m�thode recherche un clap de la base de donn�es � partir de son Id
	 * @param IDClap l'Id du clap que l'on recherche
	 * @return l'objet clap recherch� 
	 */
	public static Clap rechercheClapById(int IDClap) {
		Connexion.getEM().getTransaction().begin();
		Clap Clap = Connexion.getEM().find(Clap.class, IDClap);
		Connexion.getEM().getTransaction().commit();
		return Clap;
	}

	/**
	 * Cette m�thode permet de modifier un clap existant dans la base de donn�es
	 * @param IDClap l'Id du clap que l'on veut modifier
	 * @param Clap le clap qui contient les nouvelles donn�es du Clap
	 */
	public static void modifierCLap(int IDClap, Clap Clap) {
		Connexion.getEM().getTransaction().begin();
		Clap NouveauClap = Connexion.getEM().find(Clap.class, IDClap);
		NouveauClap.setCodeBobine(Clap.getCodeBobine());
		NouveauClap.setDuree(Clap.getDuree());
		Connexion.getEM().getTransaction().commit();
	}

	/**
	 * Cette m�thode renvoie tous les objets Clap de la base de donn�es
	 * @return une liste de tous les objets Clap de la base de donn�es
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
	 * Cette m�thode renvoie la prochaine Id du prochain Clap
	 * @return le num�ro d'Id du prochain Clap
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
