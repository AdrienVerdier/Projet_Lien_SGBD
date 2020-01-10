package controler;

import javax.persistence.*;

public class Connexion {

private static EntityManagerFactory emf;
	
	public static void init()
	{
		emf = Persistence.createEntityManagerFactory("creation");
	}
	
	public static void modification()
	{
		emf = Persistence.createEntityManagerFactory("modification");
	}

	static public EntityManager ouvrirconnexion() {
		EntityManager em = emf.createEntityManager();
		return em;
	}

	static public void fermerconnexion(EntityManager em) {
		em.close();
	}
}
