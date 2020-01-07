import javax.persistence.*;

import controler.Connexion;

public class main {

	public static void main(String[] args) {

		EntityManager em = Connexion.ouvrirconnexion("creation");
		Connexion.fermerconnexion(em);

	}

}
