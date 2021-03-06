package model;

import javax.persistence.*;

@Entity

public class Clap {
	@Id
	private int codeClap;
	private double duree;
	private int codeBobine;
	@ManyToOne(fetch = FetchType.LAZY)
	private Setup codeSetup;

	public int getCodeClap() {
		return codeClap;
	}

	public void setCodeClap(int codeClap) {
		this.codeClap = codeClap;
	}

	public double getDuree() {
		return duree;
	}

	public void setDuree(double duree) {
		this.duree = duree;
	}

	public int getCodeBobine() {
		return codeBobine;
	}

	public void setCodeBobine(int codeBobine) {
		this.codeBobine = codeBobine;
	}

	public Clap(int codeClap, double duree, int codeBobine) {
		this.codeClap = codeClap;
		this.duree = duree;
		this.codeBobine = codeBobine;
	}

	public Setup getCodeSetup() {
		return codeSetup;
	}

	public void setCodeSetup(Setup codeSetup) {
		this.codeSetup = codeSetup;
	}

	public Clap(int codeClap, double duree, int codeBobine, Setup codeSetup) {
		this.codeClap = codeClap;
		this.duree = duree;
		this.codeBobine = codeBobine;
		this.codeSetup = codeSetup;
	}

	public Clap() {
		super();
	}

}
