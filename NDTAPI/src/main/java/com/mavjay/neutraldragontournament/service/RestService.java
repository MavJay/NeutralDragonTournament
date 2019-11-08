
package com.mavjay.neutraldragontournament.service;

import java.util.ArrayList;
import java.util.List;

public interface RestService {

	public String joinTournament(String player, int wizardId,int affinityType);
	public List<String> roundFixture();
	public String updatePlaceSpell(String player,int wizardSpell1,
			int wizardSpell2,int wizardSpell3,int wizardSpell4,int wizardSpell5);
	public ArrayList<Object> getMatchArr();
	public String getPlayerDetails(String playerAddr);
	public String updateMatchFixture(String matchArr);
	public String calculateScore();
	public ArrayList<Object> getScoreArr();
	public String getContractAddress();
}
