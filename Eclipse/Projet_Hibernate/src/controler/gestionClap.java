package controler;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

import model.Clap;
import model.Setup;

public class gestionClap {

	public static void ajouterClap(int Minute, int seconds, String codeBobine, int codeSetup) {
		Duration duree = Duration.ZERO;
		duree.plusMinutes(Minute);
		duree.plusSeconds(seconds);
		
		Setup setup = DAOSetup.rechercheSetupById(codeSetup);
		int bobi = Integer.parseInt(codeBobine);
		Clap clap = new Clap(DAOClap.returnMaxIDClap(), duree, bobi, setup);
		DAOSetup.ajouterSetupClap(setup, clap);
		DAOClap.ajouterClap(clap);
	}
	
	public static int nombreClap(int codeSetup) {
		return getClap(codeSetup).size();
	}
	
	public static void supprimerClap(int codeClap) {
		DAOClap.supprimerClap(DAOClap.rechercheClapById(codeClap));
	}
	
	public static ArrayList<Clap> getClap(){
		return DAOClap.returnAllClap();			
	}
	
	public static ArrayList<Clap> getClap(int codeSetup){
		Iterator<Clap> iter = DAOClap.returnAllClap().iterator();
		ArrayList<Clap> retour = new ArrayList<Clap>();
		
		while(iter.hasNext()) {
			Clap clap = iter.next();
			
			if(clap.getCodeSetup().getCodeSetup() == codeSetup) {
				retour.add(clap);
			}
		}
		
		return retour;
	}
}
