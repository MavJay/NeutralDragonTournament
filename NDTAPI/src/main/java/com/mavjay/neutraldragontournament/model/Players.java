package com.mavjay.neutraldragontournament.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "players")
public class Players {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO) // for auto
	private int plrid;
	
	@Column
	private String playeraddr;
	@Column
	private int wizardid;
	@Column
	private int wizardaffinity;
	@Column
	private String creationdate;

	
	
	public Players(int plrid, String playeraddr, int wizardid, int wizardaffinity, String creationdate
			) {
		super();
		this.plrid = plrid;
		this.playeraddr = playeraddr;
		this.wizardid = wizardid;
		this.wizardaffinity = wizardaffinity;
		this.creationdate = creationdate;
	
	}


	public int getPlrid() {
		return plrid;
	}


	public void setPlrid(int plrid) {
		this.plrid = plrid;
	}


	public String getPlayeraddr() {
		return playeraddr;
	}


	public void setPlayeraddr(String playeraddr) {
		this.playeraddr = playeraddr;
	}


	public int getWizardid() {
		return wizardid;
	}


	public void setWizardid(int wizardid) {
		this.wizardid = wizardid;
	}


	public int getWizardaffinity() {
		return wizardaffinity;
	}


	public void setWizardaffinity(int wizardaffinity) {
		this.wizardaffinity = wizardaffinity;
	}


	public String getCreationdate() {
		return creationdate;
	}


	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}


	


	public Players() {
		// TODO Auto-generated constructor stub
	}

}
