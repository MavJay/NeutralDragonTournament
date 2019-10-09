package com.mavjay.neutraldragontournament.model;

import javax.persistence.Column;

import javax.persistence.Table;

import org.hibernate.annotations.Entity;

@Entity
@Table(name="flagsettings")
public class FlagSettings {
	
	@Column
	
	private int levelCount;	
	
	@Column
	private boolean roundFixture;
	@Column
	private boolean scoreUpdate;
	
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



	
	
	public FlagSettings(int levelCount, boolean roundFixture, boolean scoreUpdate) {
		super();
		this.levelCount = levelCount;
		this.roundFixture = roundFixture;
		this.scoreUpdate = scoreUpdate;

	}
	
	
	public FlagSettings() {
		// TODO Auto-generated constructor stub
	}
}
