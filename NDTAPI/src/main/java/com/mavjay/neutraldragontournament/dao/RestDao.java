package com.mavjay.neutraldragontournament.dao;

import java.util.ArrayList;
import java.util.List;

import com.mavjay.neutraldragontournament.model.Tournament;

public interface RestDao {

	//String EnrollPlayers(String address, int wizid, int affinity);
	String joinTournament(String player, int wizardId,int affinityType);
	
	public List<String> roundFixture();
	
	public String updatePlaceSpell(String player,int wizardSpell1,
			int wizardSpell2,int wizardSpell3,int wizardSpell4,int wizardSpell5);
	public ArrayList<Object> getMatchArr();
	public String getPlayerDetails(String playerAddr);
	public String updateMatchFixture(String matchArr);
	public String calculateScore();

	public ArrayList<Object> getScoreArr();
}
