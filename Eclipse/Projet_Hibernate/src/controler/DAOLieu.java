package controler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Lieu;

public class DAOLieu {
	/**
	 * Cette m�thode ajoute un Lieu � la base de donn�es
	 * @param Lieu le Lieu que l'on veut ajouter � la base de donn�es
	 */
	public static void ajouterLieu(Lieu Lieu) {
		Connexion.getEM().getTransaction().begin();
		Connexion.getEM().persist(Lieu);
		Connexion.getEM().getTransaction().commit();
	}
	/**
	 * Cette m�thode supprime un Lieu � la base de donn�es
	 * @param Lieu le Lieu que l'on veut supprimer � la base de donn�es
	 */
	public static void supprimerLieu(Lieu Lieu) {
		Connexion.getEM().getTransaction().begin();
		Lieu Lieu2 = Connexion.getEM().find(Lieu.class, Lieu.getCodeLieu());
		Connexion.getEM().remove(Lieu2);
		Connexion.getEM().getTransaction().commit();
	}
	/**
	 * Cette m�thode recherche un Lieu de la base de donn�es � partir de son Id
	 * @param IDLieu l'Id du Lieu que l'on recherche
	 * @return l'objet Lieu recherch� 
	 */
	public static Lieu rechercheLieuById(int IDLieu) {
		Connexion.getEM().getTransaction().begin();
		Lieu Lieu = Connexion.getEM().find(Lieu.class, IDLieu);
		Connexion.getEM().getTransaction().commit();
		return Lieu;
	}
	/**
	 * Cette m�thode permet de modifier un Lieu existant dans la base de donn�es
	 * @param IDLieu l'Id du Lieu que l'on veut modifier 
	 * @param Lieu le Lieu qui contient les nouvelles donn�es du Lieu
	 */
	public static void modifierLieu(int IDLieu, Lieu Lieu) {
		Connexion.getEM().getTransaction().begin();
		Lieu NouveauLieu = Connexion.getEM().find(Lieu.class, IDLieu);
		NouveauLieu.setAdresse(Lieu.getAdresse());
		NouveauLieu.setDescription(Lieu.getDescription());
		Connexion.getEM().getTransaction().commit();
	}
	/**
	 * Cette m�thode renvoie tous les objets Lieu de la base de donn�es
	 * @return une liste de tous les objets Lieu de la base de donn�es
	 */
	public static ArrayList<Lieu> returnAllLieu() {
		Connexion.getEM().getTransaction().begin();
		ArrayList<Lieu> resultat = new ArrayList<Lieu>();
		String queryString = "select l from Lieu l";
		Query query = Connexion.getEM().createQuery(queryString);
		List results = query.getResultList();
		for (int i = 0; i < results.size(); i++) {
			Lieu Lieu = (Lieu) results.get(i);
			resultat.add(Lieu);
		}
		Connexion.getEM().getTransaction().commit();
		return resultat;
	};
	/**
	 * Cette m�thode renvoie la prochaine Id du prochain Lieu
	 * @return le num�ro d'Id du prochain Lieu
	 */
	public static int returnMaxIDLieu() {
		Connexion.getEM().getTransaction().begin();
		String queryString = "select l from Lieu l";
		Query query = Connexion.getEM().createQuery(queryString);
		List results = query.getResultList();
		int max = 0;
		for (int i = 0; i < results.size(); i++) {
			Lieu Lieu = (Lieu) results.get(i);
			if(Lieu.getCodeLieu() >= max)
			{
				max = Lieu.getCodeLieu()+1;
			}
		}
		Connexion.getEM().getTransaction().commit();
		return max;
	};
}
