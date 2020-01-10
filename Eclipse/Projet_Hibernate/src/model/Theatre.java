package model;

import java.util.List;

import javax.persistence.*;

@Entity

public class Theatre {
	@Id
	private int codeTheatre;
	private String description;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "codeTheatre")
	private List<SceneInterieur> listSceneInterieur;
	public int getCodeTheatre() {
		return codeTheatre;
	}
	public void setCodeTheatre(int codeTheatre) {
		this.codeTheatre = codeTheatre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Theatre(int codeTheatre, String description) {
		super();
		this.codeTheatre = codeTheatre;
		this.description = description;
	}
	
	public Theatre() {
		super();
	}
	public Theatre(int codeTheatre, String description, List<SceneInterieur> listSceneInterieur) {
		super();
		this.codeTheatre = codeTheatre;
		this.description = description;
		this.listSceneInterieur = listSceneInterieur;
	}
	
	
}
