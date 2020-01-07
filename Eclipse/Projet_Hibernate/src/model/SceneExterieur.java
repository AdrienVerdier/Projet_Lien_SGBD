package model;

import java.util.List;

import javax.persistence.*;

@Entity

public class SceneExterieur extends Scene {

	// 1 = nocturne, 0 = jour
	private int nocturne;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Lieu codeLieu;

	public int getNocturne() {
		return nocturne;
	}

	public void setNocturne(int nocturne) {
		this.nocturne = nocturne;
	}

	public Lieu getCodeLieu() {
		return codeLieu;
	}

	public void setCodeLieu(Lieu codeLieu) {
		this.codeLieu = codeLieu;
	}

	public SceneExterieur(int codeScene, String description, List<Setup> listSetup, int nocturne, Lieu codeLieu) {
		super(codeScene, description, listSetup);
		this.nocturne = nocturne;
		this.codeLieu = codeLieu;
	}

}
