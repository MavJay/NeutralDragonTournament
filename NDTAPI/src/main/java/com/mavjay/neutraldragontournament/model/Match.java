package com.mavjay.neutraldragontournament.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "matcharray")
public class Match {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO) // for auto
	private int matchId;	
	public int getMatchId() {
		return matchId;
	}


	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}


	public String getPlayer1Address() {
		return player1Address;
	}


	public void setPlayer1Address(String player1Address) {
		this.player1Address = player1Address;
	}


	public String getPlayer2Address() {
		return player2Address;
	}


	public void setPlayer2Address(String player2Address) {
		this.player2Address = player2Address;
	}


	@Column
	private String player1Address;
	@Column
	private String player2Address;
	
	
	public Match(int matchId, String player1Address, String player2Address) {
		super();
		this.matchId = matchId;
		this.player1Address = player1Address;
		this.player2Address = player2Address;

	}
	
	
	public Match() {
		// TODO Auto-generated constructor stub
	}

}
