package model;

import java.util.List;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Scene {
	@Id
	private int codeScene;
	private String description;
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "codeScene")
	private List<Setup> listSetup;

	public int getCodeScene() {
		return codeScene;
	}

	public void setCodeScene(int codeScene) {
		this.codeScene = codeScene;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Setup> getListSetup() {
		return listSetup;
	}

	public void setListSetup(List<Setup> listSetup) {
		this.listSetup = listSetup;
	}

	public Scene(int codeScene, String description, List<Setup> listSetup) {
		this.codeScene = codeScene;
		this.description = description;
		this.listSetup = listSetup;
	}
	
	

}
