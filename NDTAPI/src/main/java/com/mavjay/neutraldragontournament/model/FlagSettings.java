package com.mavjay.neutraldragontournament.model;

import javax.persistence.Column;

import javax.persistence.Table;
import javax.persistence.Id;

//import org.hibernate.annotations.Entity;
import javax.persistence.Entity;

@Entity
@Table(name="FlagSettings")
public class FlagSettings {
	@Id
	@Column
	private int fgid;
	
	@Column
	private int levelCount;	
	@Column
	private boolean roundFixture;
	@Column
	private boolean scoreUpdate;
	@Column
	private String contractAddress;
	
	@Column
	private String tournamentStartTime;
	
	@Column
	private String contractdeploystarttime;
	
	


	public String getTournamentStartTime() {
		return tournamentStartTime;
	}


	public void setTournamentStartTime(String tournamentStartTime) {
		this.tournamentStartTime = tournamentStartTime;
	}


	public String getContractAddress() {
		return contractAddress;
	}


	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}


	public int getLevelCOunt() {
		return levelCount;
	}


	public void setLevelCount(int levelCount) {
		this.levelCount = levelCount;
	}


	public boolean getRoundFixture() {
		return roundFixture;
	}


	public void setRoundFixture(boolean roundFixture) {
		this.roundFixture = roundFixture;
	}


	public boolean getScoreUpdate() {
		return scoreUpdate;
	}


	public void setScoreUpdate(boolean scoreUpdate) {
		this.scoreUpdate = scoreUpdate;
	}

	public String getContractdeploystarttime() {
		return contractdeploystarttime;
	}


	public void setContractdeploystarttime(String contractdeploystarttime) {
		this.contractdeploystarttime = contractdeploystarttime;
	}

	
	
	public FlagSettings(int fgid,int levelCount, boolean roundFixture, boolean scoreUpdate,String contractAddress, String tournamentStartTime, String contractdeploystarttime) {
		super();
		this.fgid = fgid;
		this.levelCount = levelCount;
		this.roundFixture = roundFixture;
		this.scoreUpdate = scoreUpdate;
		this.contractAddress=contractAddress;
		this.tournamentStartTime=tournamentStartTime;
		this.contractdeploystarttime=contractdeploystarttime;
		
	}
	
	
	public int getFgid() {
		return fgid;
	}


	public void setFgid(int fgid) {
		this.fgid = fgid;
	}


	public FlagSettings() {
		// TODO Auto-generated constructor stub
	}
}
