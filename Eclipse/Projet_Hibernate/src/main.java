import view.AppInterface;
import javax.persistence.*;
import controler.Connexion;

public class main {

	public static void main(String[] args) {

		EntityManager em = Connexion.ouvrirconnexion("modification");
		Connexion.fermerconnexion(em);

		AppInterface Application = new AppInterface();
		
	}

}
