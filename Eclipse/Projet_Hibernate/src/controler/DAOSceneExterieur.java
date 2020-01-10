package controler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Lieu;
import model.SceneExterieur;

public class DAOSceneExterieur {
	public static void ajouterSceneExterieur(SceneExterieur SceneExterieur) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		em.persist(SceneExterieur);
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

	public static SceneExterieur rechercheCalpById(int IDSceneExterieur) {
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
		NouveauSceneExterieur.setListSetup(SceneExterieur.getListSetup());
		NouveauSceneExterieur.setNocturne(SceneExterieur.getNocturne());
		NouveauSceneExterieur.setCodeLieu(SceneExterieur.getCodeLieu());
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}
	
	public static void modifierSceneExterieurLieu(int IDSceneExterieur, Lieu Lieu) {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		SceneExterieur NouveauSceneExterieur = em.find(SceneExterieur.class, IDSceneExterieur);
		NouveauSceneExterieur.setCodeLieu(Lieu);
		em.getTransaction().commit();
		Connexion.fermerconnexion(em);
	}

	public static ArrayList<SceneExterieur> retrunAllSceneExterieur() {
		EntityManager em = Connexion.ouvrirconnexion();
		em.getTransaction().begin();
		ArrayList<SceneExterieur> resultat = new ArrayList<SceneExterieur>();
		String queryString = "select c from SceneExterieur c";
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
}
