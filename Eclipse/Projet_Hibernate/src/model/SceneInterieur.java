package model;

import java.util.List;

import javax.persistence.*;

@Entity

public class SceneInterieur extends Scene {

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Theatre codeTheatre;

	public Theatre getCodeTheatre() {
		return codeTheatre;
	}

	public void setCodeTheatre(Theatre codeTheatre) {
		this.codeTheatre = codeTheatre;
	}

	public SceneInterieur(int codeScene, String description, List<Setup> listSetup, Theatre codeTheatre) {
		super(codeScene, description, listSetup);
		this.codeTheatre = codeTheatre;
	}
	
	public SceneInterieur() {
		super();
	}

}
