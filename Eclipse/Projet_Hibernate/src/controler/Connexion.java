package controler;

import javax.persistence.*;

public class Connexion {

	static public EntityManager ouvrirconnexion(String mode) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(mode);
		EntityManager em = emf.createEntityManager();
		return em;
	}

	static public void fermerconnexion(EntityManager em) {
		em.close();
	}
}
