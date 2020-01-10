package model;

import java.util.List;

import javax.persistence.*;

@Entity

public class Setup {
	@Id
	private int codeSetup;
	private String parametre;
	@ManyToOne(fetch = FetchType.LAZY)
	private Scene codeScene;
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "codeSetup")
	private List<Clap> listClap;
	
	public int getCodeSetup() {
		return codeSetup;
	}
	public void setCodeSetup(int codeSetup) {
		this.codeSetup = codeSetup;
	}
	public String getParametre() {
		return parametre;
	}
	public void setParametre(String parametre) {
		this.parametre = parametre;
	}
	public Setup(int codeSetup, String parametre) {
		this.codeSetup = codeSetup;
		this.parametre = parametre;
	}
	public Scene getCodeScene() {
		return codeScene;
	}
	public void setCodeScene(Scene codeScene) {
		this.codeScene = codeScene;
	}
	public List<Clap> getListClap() {
		return listClap;
	}
	public void setListClap(List<Clap> listClap) {
		this.listClap = listClap;
	}
	public Setup(int codeSetup, String parametre, Scene codeScene, List<Clap> listClap) {
		this.codeSetup = codeSetup;
		this.parametre = parametre;
		this.codeScene = codeScene;
		this.listClap = listClap;
	}
	
	public Setup() {
		super();
	}
	
}
