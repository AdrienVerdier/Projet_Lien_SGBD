package model;

import javax.persistence.*;

@Entity

public class Theatre {
	@Id
	private int codeTheatre;
	private String description;
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
	
}
