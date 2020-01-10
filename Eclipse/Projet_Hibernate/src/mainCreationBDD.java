import javax.persistence.EntityManager;

import controler.Connexion;

public class mainCreationBDD {
	public static void main(String[] args) {

		EntityManager em = Connexion.ouvrirconnexion("creation");
		Connexion.fermerconnexion(em);
		
	}
}
