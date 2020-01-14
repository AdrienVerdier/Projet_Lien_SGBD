package controler;

import javax.persistence.*;

public class Connexion {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	
	public static void init()
	{
		emf = Persistence.createEntityManagerFactory("creation");
		em = emf.createEntityManager();
	}
	
	public static void modification()
	{
		emf = Persistence.createEntityManagerFactory("modification");
		em = emf.createEntityManager();
	}

	static public EntityManager getEM() { // celle là ne sert plus à rien
		return em;
	}

	static public void fermerconnexion() {
		em.close();
	}
}
