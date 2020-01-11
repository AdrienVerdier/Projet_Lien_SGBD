package controler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Clap;
import model.Lieu;
import model.SceneExterieur;
import model.Setup;

public class DAOSceneExterieur {
	public static void ajouterSceneExterieur(SceneExterieur SceneExterieur) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		em.persist(SceneExterieur);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}
	

	public static void ajouterSceneExterieurSetup(SceneExterieur SceneExterieur,Setup Setup) {
		EntityManager em = Connexion.ouvrirconnexion();		
		em.getTransaction().begin();
		List<Setup> listSetup;
		listSetup = SceneExterieur.getListSetup();
		if(listSetup == null)
		{
			listSetup = new ArrayList<Setup>();
		}
		listSetup.add(Setup);
		SceneExterieur.setListSetup(listSetup);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}
	
	public static void supprimerSceneExterieur(SceneExterieur SceneExterieur) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		SceneExterieur SceneExterieur2 = em.find(SceneExterieur.class, SceneExterieur.getCodeScene());
		em.remove(SceneExterieur2);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	public static SceneExterieur rechercheSceneExterieurById(int IDSceneExterieur) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		SceneExterieur SceneExterieur = em.find(SceneExterieur.class, IDSceneExterieur);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return SceneExterieur;
	}

	public static void modifierSceneExterieur(int IDSceneExterieur, SceneExterieur SceneExterieur) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		SceneExterieur NouveauSceneExterieur = em.find(SceneExterieur.class, IDSceneExterieur);
		NouveauSceneExterieur.setDescription(SceneExterieur.getDescription());
		NouveauSceneExterieur.setNocturne(SceneExterieur.getNocturne());
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}
	
	public static void modifierSceneExterieurLieu(int IDSceneExterieur, Lieu Lieu) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		SceneExterieur NouveauSceneExterieur = em.find(SceneExterieur.class, IDSceneExterieur);;
		NouveauSceneExterieur.setCodeLieu(Lieu);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	public static List<SceneExterieur> retrunAllSceneExterieur() {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		ArrayList<SceneExterieur> resultat = new ArrayList<SceneExterieur>();
		String queryString = "select s from SceneExterieur s";
		Query query = em.createQuery(queryString);
		List results = query.getResultList();
		for (int i = 0; i < results.size(); i++) {
			SceneExterieur SceneExterieur = (SceneExterieur) results.get(i);
			resultat.add(SceneExterieur);
		}
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return resultat;
	};
	
	public static int retrunMaxIDSceneExterieur() {
		return DAOScene.retrunMaxIDScene();
	};
}
