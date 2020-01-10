import java.time.Duration;
import java.util.ArrayList;

import javax.persistence.EntityManager;

import controler.Connexion;
import controler.DAOClap;
import model.Clap;

public class mainCreationBDD {
	public static void main(String[] args) {

		Connexion.init();
		Duration duree = Duration.ofMinutes(1);
		Clap a = new Clap(1,duree,1,null);
		Clap b = new Clap(2,duree,1,null);
		Clap c = new Clap(3,duree,4,null);
		DAOClap.ajouterClap(a);
		DAOClap.ajouterClap(b);
		DAOClap.ajouterClap(c);
		ArrayList<Clap> resultat = new ArrayList<Clap>();
		resultat = DAOClap.retrunAllClap();
		System.out.println(resultat.get(0).getCodeClap());
		System.out.println(resultat.get(0).getCodeBobine());
		System.out.println(resultat.get(1).getCodeClap());
		System.out.println(resultat.get(1).getCodeBobine());

		System.out.println(DAOClap.rechercheCalpById(1).getCodeClap());
		
		DAOClap.modifierCLap(1,c);
		System.out.println(DAOClap.rechercheCalpById(1).getCodeBobine());
	}
}
