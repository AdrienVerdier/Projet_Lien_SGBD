package model;

import java.util.List;

import javax.persistence.*;

@Entity

public class Lieu {
	@Id
	private int codeLieu;
	private String adresse;
	private String description;
	public int getCodeLieu() {
		return codeLieu;
	}
	public void setCodeLieu(int codeLieu) {
		this.codeLieu = codeLieu;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Lieu(int codeLieu, String adresse, String description) {
		this.codeLieu = codeLieu;
		this.adresse = adresse;
		this.description = description;
	}
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "codeLieu")
	private List<SceneExterieur> listSceneExterieur;
	
}
