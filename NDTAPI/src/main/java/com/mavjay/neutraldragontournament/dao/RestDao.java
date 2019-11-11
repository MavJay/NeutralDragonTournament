package com.mavjay.neutraldragontournament.dao;

import java.util.ArrayList;
import java.util.List;

public interface RestDao {
	public String joinTournament(String player, int wizardId,int affinityType);
	
	public List<String> roundFixture();
	
	public String updatePlaceSpell(String player,int wizardSpell1,
			int wizardSpell2,int wizardSpell3,int wizardSpell4,int wizardSpell5);
	public ArrayList<Object> getMatchArr();
	public String getPlayerDetails(String playerAddr);

	
		public String getContractAddress();

	String getTimer();
	public String calculateScore();

	public String getScoreArr();

//	public String getWizardData();
//
//	String getWizardDataWithMinpower();

	public String getWizid(String address);
}
