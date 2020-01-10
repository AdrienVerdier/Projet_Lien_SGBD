package controler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Theatre;
import model.SceneInterieur;
import model.Setup;

public class DAOSceneInterieur {
	
	public static void ajouterSceneInterieur(SceneInterieur SceneInterieur) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		em.persist(SceneInterieur);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	public static void ajouterSceneInterieurSetup(SceneInterieur SceneInterieur,Setup Setup) {
		EntityManager em = Connexion.ouvrirconnexion();		
		em.getTransaction().begin();
		List<Setup> listSetup;
		listSetup = SceneInterieur.getListSetup();
		if(listSetup == null)
		{
			listSetup = new ArrayList<Setup>();
		}
		listSetup.add(Setup);
		SceneInterieur.setListSetup(listSetup);
		em.getTransaction().commit();
		DAOSetup.ajouterSetup(Setup);
		Connexion.fermerconnexion(em);
	}
	
	public static void supprimerSceneInterieur(SceneInterieur SceneInterieur) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		SceneInterieur SceneInterieur2 = em.find(SceneInterieur.class, SceneInterieur.getCodeScene());
		em.remove(SceneInterieur2);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	public static SceneInterieur rechercheSceneInterieurById(int IDSceneInterieur) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		SceneInterieur SceneInterieur = em.find(SceneInterieur.class, IDSceneInterieur);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return SceneInterieur;
	}

	public static void modifierSceneInterieur(int IDSceneInterieur, SceneInterieur SceneInterieur) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		SceneInterieur NouveauSceneInterieur = em.find(SceneInterieur.class, IDSceneInterieur);
		NouveauSceneInterieur.setDescription(SceneInterieur.getDescription());
		NouveauSceneInterieur.setListSetup(SceneInterieur.getListSetup());
		NouveauSceneInterieur.setCodeTheatre(SceneInterieur.getCodeTheatre());
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}
	
	public static void modifierSceneInterieurTheatre(int IDSceneInterieur, Theatre Theatre) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		SceneInterieur NouveauSceneInterieur = em.find(SceneInterieur.class, IDSceneInterieur);
		NouveauSceneInterieur.setCodeTheatre(Theatre);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	public static List<SceneInterieur> retrunAllSceneInterieur() {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		ArrayList<SceneInterieur> resultat = new ArrayList<SceneInterieur>();
		String queryString = "select s from SceneInterieur s";
		Query query = em.createQuery(queryString);
		List results =  query.getResultList();
		for (int i = 0; i < results.size(); i++) {
			SceneInterieur SceneInterieur = (SceneInterieur) results.get(i);
			resultat.add(SceneInterieur);
		}
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
		return resultat;
	};
}
