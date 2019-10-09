package com.mavjay.neutraldragontournament.dao.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mavjay.neutraldragontournament.dao.RestDao;
import com.mavjay.neutraldragontournament.model.Match;
import com.mavjay.neutraldragontournament.model.Tournament;
import com.mavjay.neutraldragontournament.web3.ContractInteraction;;

@Repository
public class RestDaoImpl implements RestDao {
	@Autowired
	private SessionFactory sessionFact;
	
	@Override
	//public String joinTournament(String address, int wizid, int affinity) {
	public String joinTournament(String player, int wizardId,int affinityType) {
		Session session = sessionFact.getCurrentSession();
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		long currentTimeStamp = ts.getTime();
		Tournament tn = new Tournament();
		tn.setPlayer(player);
		tn.setWizardId(wizardId);
		tn.setBet(Double.parseDouble("0.1"));
		tn.setTimestamp(currentTimeStamp);
		tn.setAffinityType(affinityType);
		tn.setWizardSpell1(0);
		tn.setWizardSpell2(0);
		tn.setWizardSpell3(0);
		tn.setWizardSpell4(0);
		tn.setWizardSpell5(0);
		tn.setWinStatus(0);
		tn.setPlayerStatus(0);
		tn.setFinalStatus(0);
		tn.setWinnerStatus(0);
		tn.setByeStatus(0);	
		session.save(tn);
		return "success";
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public List<String> roundFixture() {
		Session session = sessionFact.getCurrentSession();		
		List<String> dataList = new ArrayList<String>();
		try {
			List<Object> playerList = null;			
			Query Query = session.createQuery("select player from Tournament where playerStatus=:playerStatus")
					.setParameter("playerStatus",0);
			playerList = Query.list();
			System.out.println("playerList-->"+playerList);
				if(playerList.size()==1) {
					for(Object playerFetch : playerList){
						dataList.add((String) playerList.get(1));
					}
				}else if(playerList.size() % 2 == 0){
					for(Object playerFetch : playerList){
						dataList.add((String) playerList.get(1));
					}
				} else {
					Query getPlayer = session.createSQLQuery("select player from Tournament WHERE tournamentId NOT IN (SELECT MAX(tournamentId) from Tournament) and playerStatus='0' ORDER BY tournamentId DESC");	
					List<Object> getPlayerList = null;	
					getPlayerList = getPlayer.list();
					int i =0;
					for(Object playerFetch : getPlayerList){
						i++;
						dataList.add((String) playerList.get(i));
					}
					int updateByeStatus = session.createSQLQuery("update Tournament set byeStatus='1' LIMIT 1").executeUpdate();
				}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		ContractInteraction cI = new ContractInteraction();
		try {
			ArrayList<Object> matchArray = new ArrayList();
			matchArray = cI.getMatchFixture(dataList);
			for(int i=0;i<matchArray.size();){
				Query insertQuery = session.createSQLQuery("INSERT INTO matcharray(player1address,player2address) VALUES (?,?)");
				insertQuery.setParameter(0, matchArray.get(i));
				insertQuery.setParameter(1, matchArray.get(i+1));
				insertQuery.executeUpdate();
				i=i+2;
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}
		return dataList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String updatePlaceSpell(String player,int wizardSpell1,
			int wizardSpell2,int wizardSpell3,int wizardSpell4,int wizardSpell5){
		Session session = sessionFact.getCurrentSession();
		List<Tournament> uniqueappointment;
		int unique;
		Query findunique=	session.createQuery("from Tournament where player=:player")
		.setParameter("player", player);
		uniqueappointment=findunique.list();
		unique=uniqueappointment.size();
		System.out.println("unique-->"+unique);
		if(uniqueappointment.size()==0) {
			//does not exist so return error
			return "notExists";
		}
		else
		{
			int tournamentId = 0;
			int wizardSpell = 0;
			//player exist so continue to update their spells in DB
			for (Tournament tournament : uniqueappointment) {
				tournamentId = tournament.getTournamentId();	
				wizardSpell = tournament.getWizardSpell1();
			}
			if(wizardSpell == 0){
				
			
			System.out.println("tournamentId-->"+tournamentId);
			String updateQuery = "UPDATE Tournament SET wizardSpell1 = :wizardSpell1, wizardSpell2=:wizardSpell2,wizardSpell3=:wizardSpell3,"
					+ "wizardSpell4=:wizardSpell4,wizardSpell5=:wizardSpell5 where tournamentId=:tournamentId";
			Query qry = session.createQuery(updateQuery);
			qry.setParameter("wizardSpell1", wizardSpell1);
			qry.setParameter("wizardSpell2", wizardSpell2);
			qry.setParameter("wizardSpell3", wizardSpell3);
			qry.setParameter("wizardSpell4", wizardSpell4);
			qry.setParameter("wizardSpell5", wizardSpell5);
			qry.setParameter("tournamentId", tournamentId);
			qry.executeUpdate();
		System.out.println("updated successfully");		
		return "success";
		} else {
			return "failed";
		}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Object> getMatchArr(){
		Session session = sessionFact.getCurrentSession();
		
		ArrayList<Object> matchList = new ArrayList<Object>();
		List<Match> matchDetails;
		Query scheduledMatches=	session.createQuery("from Match");
				matchDetails=scheduledMatches.list();
		for (Match match : matchDetails) {
			List<Object> dataList = new ArrayList<Object>();
			dataList.add(match.getPlayer1Address());
			dataList.add(match.getPlayer2Address());
			matchList.add(dataList);
		}
		System.out.println("List ::::::"+matchList);
		return matchList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getPlayerDetails(String playerAddr){
		Session session = sessionFact.getCurrentSession();
		int spell = 0,playerStatus = 0,byeStatus = 0;
		List<Tournament> playerDetails;
		int unique;
		Query findPlayer=	session.createQuery("from Tournament where player=:player")
		.setParameter("player", playerAddr);
		playerDetails=findPlayer.list();
		for (Tournament tournament : playerDetails) {
			spell = tournament.getWizardSpell1();
			playerStatus = tournament.getPlayerStatus();
			byeStatus = tournament.getByeStatus();
		}
		if(spell == 0 && playerStatus == 0){
			return "placeSpell";
		} else if(playerStatus == 1){
			return "eliminated";
		} else if(byeStatus == 1){
			return "bye";
		} else {
			return "failed";
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String updateMatchFixture(String matchArr){
//		System.out.println("Array in IMpl"+arr);
		ContractInteraction cI = new ContractInteraction();
		try {
			cI.loadContract();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	
}
