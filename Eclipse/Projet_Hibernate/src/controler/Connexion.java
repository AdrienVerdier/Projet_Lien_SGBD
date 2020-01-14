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
		//ouvrir la co 
	}
	
	//il faut un get em

	static public EntityManager ouvrirconnexion() { // celle là ne sert plus à rien
		EntityManager em = emf.createEntityManager();
		return em;
	}

	static public void fermerconnexion(EntityManager em) {
		em.close();
	}
}
