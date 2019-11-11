package com.mavjay.neutraldragontournament.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tournament")
public class Tournament {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO) // for auto
	private int tournamentId;	
	public int getTournamentId() {
		return tournamentId;
	}

	public void setTournamentId(int tournamentId) {
		this.tournamentId = tournamentId;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public int getWizardId() {
		return wizardId;
	}

	public void setWizardId(int wizardId) {
		this.wizardId = wizardId;
	}

	public double getBet() {
		return bet;
	}

	public void setBet(double bet) {
		this.bet = bet;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getAffinityType() {
		return affinityType;
	}

	public void setAffinityType(int affinityType) {
		this.affinityType = affinityType;
	}

	public int getWizardSpell1() {
		return wizardSpell1;
	}

	public void setWizardSpell1(int wizardSpell1) {
		this.wizardSpell1 = wizardSpell1;
	}

	public int getWizardSpell2() {
		return wizardSpell2;
	}

	public void setWizardSpell2(int wizardSpell2) {
		this.wizardSpell2 = wizardSpell2;
	}

	public int getWizardSpell3() {
		return wizardSpell3;
	}

	public void setWizardSpell3(int wizardSpell3) {
		this.wizardSpell3 = wizardSpell3;
	}

	public int getWizardSpell4() {
		return wizardSpell4;
	}

	public void setWizardSpell4(int wizardSpell4) {
		this.wizardSpell4 = wizardSpell4;
	}

	public int getWizardSpell5() {
		return wizardSpell5;
	}

	public void setWizardSpell5(int wizardSpell5) {
		this.wizardSpell5 = wizardSpell5;
	}

	public int getWinStatus() {
		return winStatus;
	}

	public void setWinStatus(int winStatus) {
		this.winStatus = winStatus;
	}

	@Column
	private String player;
	@Column
	private int wizardId;
	@Column
	private double bet;
	@Column
	private long timestamp;
	@Column
	private int affinityType;
	@Column
	private int wizardSpell1;
	@Column
	private int wizardSpell2;
	@Column
	private int wizardSpell3;
	@Column
	private int wizardSpell4;
	@Column
	private int wizardSpell5;
	@Column
	private int winStatus;
	@Column
	private int playerStatus;
	@Column
	private int duelsPlayed;
	
	public int getDuelsPlayed() {
		return duelsPlayed;
	}

	public void setDuelsPlayed(int duelsPlayed) {
		this.duelsPlayed = duelsPlayed;
	}
	
	
	public int getPlayerStatus() {
		return playerStatus;
	}

	public void setPlayerStatus(int playerStatus) {
		this.playerStatus = playerStatus;
	}

	public int getFinalStatus() {
		return finalStatus;
	}

	public void setFinalStatus(int finalStatus) {
		this.finalStatus = finalStatus;
	}

	public int getWinnerStatus() {
		return winnerStatus;
	}

	public void setWinnerStatus(int winnerStatus) {
		this.winnerStatus = winnerStatus;
	}

	public int getByeStatus() {
		return byeStatus;
	}

	public void setByeStatus(int byeStatus) {
		this.byeStatus = byeStatus;
	}

	@Column
	private int finalStatus;
	@Column
	private int winnerStatus;
	@Column
	private int byeStatus;
		
	public Tournament(int tournamentId, String player, int wizardId,int bet,long timestamp,int affinityType,int wizardSpell1,
			int wizardSpell2,int wizardSpell3,int wizardSpell4,int wizardSpell5,int winStatus,int playerStatus,int duelsPlayed, int finalStatus, int winnerStatus, int byeStatus) {
		super();
		this.tournamentId = tournamentId;
		this.player = player;
		this.wizardId = wizardId;
		this.bet = bet;
		this.timestamp = timestamp;
		this.affinityType = affinityType;
		this.wizardSpell1 = wizardSpell1;
		this.wizardSpell2 = wizardSpell2;
		this.wizardSpell3 = wizardSpell3;
		this.wizardSpell4 = wizardSpell4;
		this.wizardSpell5 = wizardSpell5;
		this.winStatus = winStatus;
		this.playerStatus = playerStatus;
		this.duelsPlayed = duelsPlayed;
		this.finalStatus = finalStatus;
		this.winnerStatus = winnerStatus;
		this.byeStatus = byeStatus;

	}
		
	public Tournament() {
		// TODO Auto-generated constructor stub
	}

}
