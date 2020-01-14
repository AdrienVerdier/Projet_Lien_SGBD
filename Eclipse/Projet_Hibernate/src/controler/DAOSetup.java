package controler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Clap;
import model.Setup;

public class DAOSetup {
	/**
	 * Cette m�thode ajoute un Setup � la base de donn�es
	 * @param Setup le Setup que l'on veut ajouter � la base de donn�es
	 */
	public static void ajouterSetup(Setup Setup) {
		Connexion.getEM().getTransaction().begin();
		Connexion.getEM().persist(Setup);
		Connexion.getEM().getTransaction().commit();
	}

	/**
	 * Cette m�thode ajoute un Clap � un Setup
	 * @param Setup le Setup auquel on veut ajouter le Clap
	 * @param Clap le Clap que l'on veut ajouter
	 */
	public static void ajouterSetupClap(Setup Setup, Clap clap) {	
		Connexion.getEM().getTransaction().begin();
		List<Clap> listClap;
		listClap = Setup.getListClap();
		if(listClap == null)
		{
			listClap = new ArrayList<Clap>();
		}
		listClap.add(clap);
		Setup.setListClap(listClap);
		Connexion.getEM().getTransaction().commit();
	}

	/**
	 * Cette m�thode supprime un Setup � la base de donn�es
	 * @param Setup le Setup que l'on veut supprimer � la base de donn�es
	 */
	public static void supprimerSetup(Setup Setup) {
		Connexion.getEM().getTransaction().begin();
		Setup Setup2 = Connexion.getEM().find(Setup.class, Setup.getCodeSetup());
		Connexion.getEM().remove(Setup2);
		Connexion.getEM().getTransaction().commit();
	}

	/**
	 * Cette m�thode recherche un Setup de la base de donn�es � partir de son Id
	 * @param IDSetup l'Id du Setup que l'on recherche
	 * @return l'objet Setup recherch� 
	 */
	public static Setup rechercheSetupById(int IDSetup) {
		Connexion.getEM().getTransaction().begin();
		Setup Setup = Connexion.getEM().find(Setup.class, IDSetup);
		Connexion.getEM().getTransaction().commit();
		return Setup;
	}

	/**
	 * Cette m�thode permet de modifier un Setup existant dans la base de donn�es
	 * @param IDSetup l'Id du Setup que l'on veut modifier 
	 * @param Setup le Setup qui contient les nouvelles donn�es du Setup
	 */
	public static void modifierSetup(int IDSetup, Setup Setup) {
		Connexion.getEM().getTransaction().begin();
		Setup NouveauSetup = Connexion.getEM().find(Setup.class, IDSetup);
		NouveauSetup.setListClap(Setup.getListClap());
		NouveauSetup.setParametre(Setup.getParametre());
		Connexion.getEM().getTransaction().commit();
	}

	/**
	 * Cette m�thode renvoie tous les objets Setup de la base de donn�es
	 * @return une liste de tous les objets Setup de la base de donn�es
	 */
	public static ArrayList<Setup> returnAllSetup() {
		Connexion.getEM().getTransaction().begin();
		ArrayList<Setup> resultat = new ArrayList<Setup>();
		String queryString = "select s from Setup s";
		Query query = Connexion.getEM().createQuery(queryString);
		List results =  query.getResultList();
		for (int i = 0; i < results.size(); i++) {
			Setup Setup = (Setup) results.get(i);
			resultat.add(Setup);
		}
		Connexion.getEM().getTransaction().commit();
		return resultat;
	};
	/**
	 * Cette m�thode renvoie la prochaine Id du prochain Setup
	 * @return le num�ro d'Id du prochain Setup
	 */
	public static int returnMaxIDSetup() {
		Connexion.getEM().getTransaction().begin();
		String queryString = "select s from Setup s";
		Query query = Connexion.getEM().createQuery(queryString);
		List results = query.getResultList();
		int max = 0;
		for (int i = 0; i < results.size(); i++) {
			Setup Setup = (Setup) results.get(i);
			if(Setup.getCodeSetup() >= max)
			{
				max = Setup.getCodeSetup()+1;
			}
		}
		Connexion.getEM().getTransaction().commit();
		return max;
	};
}
