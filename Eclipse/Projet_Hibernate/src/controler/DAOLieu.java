package controler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Lieu;

public class DAOLieu {
	/**
	 * Cette méthode ajoute un Lieu à la base de données
	 * @param Lieu le Lieu que l'on veut ajouter à la base de données
	 */
	public static void ajouterLieu(Lieu Lieu) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		em.persist(Lieu);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}
	/**
	 * Cette méthode supprime un Lieu à la base de données
	 * @param Lieu le Lieu que l'on veut supprimer à la base de données
	 */
	public static void supprimerLieu(Lieu Lieu) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		Lieu Lieu2 = em.find(Lieu.class, Lieu.getCodeLieu());
		em.remove(Lieu2);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}
	/**
	 * Cette méthode recherche un Lieu de la base de données à partir de son Id
	 * @param IDLieu l'Id du Lieu que l'on recherche
	 * @return l'objet Lieu recherché 
	 */
	public static Lieu rechercheLieuById(int IDLieu) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		Lieu Lieu = em.find(Lieu.class, IDLieu);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return Lieu;
	}
	/**
	 * Cette méthode permet de modifier un Lieu existant dans la base de données
	 * @param IDLieu l'Id du Lieu que l'on veut modifier 
	 * @param Lieu le Lieu qui contient les nouvelles données du Lieu
	 */
	public static void modifierLieu(int IDLieu, Lieu Lieu) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		Lieu NouveauLieu = em.find(Lieu.class, IDLieu);
		NouveauLieu.setAdresse(Lieu.getAdresse());
		NouveauLieu.setDescription(Lieu.getDescription());
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}
	/**
	 * Cette méthode renvoie tous les objets Lieu de la base de données
	 * @return une liste de tous les objets Lieu de la base de données
	 */
	public static ArrayList<Lieu> returnAllLieu() {
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
	/**
	 * Cette méthode renvoie la prochaine Id du prochain Lieu
	 * @return le numéro d'Id du prochain Lieu
	 */
	public static int returnMaxIDLieu() {
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
