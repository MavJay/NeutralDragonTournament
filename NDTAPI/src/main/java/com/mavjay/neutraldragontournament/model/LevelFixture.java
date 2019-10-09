package com.mavjay.neutraldragontournament.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "levelfixture")
public class LevelFixture {
	
	@Id
	@Column
	public String getPlayerAddress() {
		return playerAddress;
	}

	public void setPlayerAddress(String playerAddress) {
		this.playerAddress = playerAddress;
	}

	public int getWizardId() {
		return wizardId;
	}

	public void setWizardId(int wizardId) {
		this.wizardId = wizardId;
	}

	public int getSpell1() {
		return spell1;
	}

	public void setSpell1(int spell1) {
		this.spell1 = spell1;
	}

	public int getSpell2() {
		return spell2;
	}

	public void setSpell2(int spell2) {
		this.spell2 = spell2;
	}
	public int getSpell3() {
		return spell3;
	}

	public void setSpell3(int spell3) {
		this.spell3 = spell3;
	}
	
	public int getSpell4() {
		return spell4;
	}

	public void setSpell4(int spell4) {
		this.spell4 = spell4;
	}
	public int getSpell5() {
		return spell5;
	}

	public void setSpell5(int spell5) {
		this.spell5 = spell5;
	}
	
	public int getLevelNum() {
		return levelNum;
	}

	public void setLevelNum(int levelNum) {
		this.levelNum = levelNum;
	}
	
	public int getRoundWon() {
		return levelNum;
	}

	public void setRoundWon(int roundWon) {
		this.roundWon = roundWon;
	}
	
	@Column
	private String playerAddress;
	@Column
	private int wizardId;
	@Column
	private int spell1;
	@Column
	private int spell2;
	@Column
	private int spell3;
	@Column
	private int spell4;
	@Column
	private int spell5;
	@Column
	private String progress;
	@Column
	private int levelNum;
	@Column
	private int roundWon;

		
	public LevelFixture(String playerAddress, int wizardId,int spell1,int spell2,int spell3,int spell4,int spell5,String progress,int roundWon, int levelNum) {
		super();
		this.playerAddress = playerAddress;
		this.wizardId = wizardId;
		this.spell1 = spell1;
		this.spell2 = spell2;
		this.spell3 = spell3;
		this.spell4 = spell4;
		this.spell5 = spell5;
		this.progress = progress;
		this.roundWon = roundWon;
		this.levelNum = levelNum;
	}
		
	public LevelFixture() {
		// TODO Auto-generated constructor stub
	}

}
