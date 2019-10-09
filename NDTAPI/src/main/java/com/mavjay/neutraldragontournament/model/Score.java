package com.mavjay.neutraldragontournament.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "scorearray")
public class Score {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO) // for auto
	private int scoreId;
	@Column
	private String plrAddress;
	@Column
	private int totalScore;
	@Column
	private int plrStatus;
	@Column
	private int noOfWins;
	@Column
	private int noOfLoss;
	@Column
	private int noOftie;
	@Column
	private int elementalWin;
	@Column
	private int elementalLoss;
	@Column
	private int noOfWinAgainstElemental;
	@Column
	private int noOfLossAgainstElemental;
	
	
	public int getScoreId() {
		return scoreId;
	}


	public void setScoreId(int scoreId) {
		this.scoreId = scoreId;
	}


	public String getPlrAddress() {
		return plrAddress;
	}


	public void setPlrAddress(String plrAddress) {
		this.plrAddress = plrAddress;
	}


	public int getTotalScore() {
		return totalScore;
	}


	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}


	public int getPlrStatus() {
		return plrStatus;
	}


	public void setPlrStatus(int plrStatus) {
		this.plrStatus = plrStatus;
	}


	public int getNoOfWins() {
		return noOfWins;
	}


	public void setNoOfWins(int noOfWins) {
		this.noOfWins = noOfWins;
	}


	public int getNoOfLoss() {
		return noOfLoss;
	}


	public void setNoOfLoss(int noOfLoss) {
		this.noOfLoss = noOfLoss;
	}


	public int getNoOftie() {
		return noOftie;
	}


	public void setNoOftie(int noOftie) {
		this.noOftie = noOftie;
	}


	public int getElementalWin() {
		return elementalWin;
	}


	public void setElementalWin(int elementalWin) {
		this.elementalWin = elementalWin;
	}


	public int getElementalLoss() {
		return elementalLoss;
	}


	public void setElementalLoss(int elementalLoss) {
		this.elementalLoss = elementalLoss;
	}


	public int getNoOfWinAgainstElemental() {
		return noOfWinAgainstElemental;
	}


	public void setNoOfWinAgainstElemental(int noOfWinAgainstElemental) {
		this.noOfWinAgainstElemental = noOfWinAgainstElemental;
	}


	public int getNoOfLossAgainstElemental() {
		return noOfLossAgainstElemental;
	}


	public void setNoOfLossAgainstElemental(int noOfLossAgainstElemental) {
		this.noOfLossAgainstElemental = noOfLossAgainstElemental;
	}



	
	
	public Score(int scoreId, String plrAddress, int totalScore, int plrStatus, int noOfWins, int noOfLoss, int noOftie, 
			int elementalWin, int elementalLoss, int noOfWinAgainstElemental, int noOfLossAgainstElemental) {
		super();
		this.scoreId = scoreId;
		this.plrAddress = plrAddress;
		this.totalScore = totalScore;
		this.plrStatus = plrStatus;
		this.noOfWins = noOfWins;
		this.noOfLoss = noOfLoss;
		this.noOftie = noOftie;
		this.elementalWin = elementalWin;
		this.elementalLoss = elementalLoss;
		this.noOfWinAgainstElemental = noOfWinAgainstElemental;
		this.noOfLossAgainstElemental = noOfLossAgainstElemental;
	}
	
	
	public Score() {
		// TODO Auto-generated constructor stub
	}

}
