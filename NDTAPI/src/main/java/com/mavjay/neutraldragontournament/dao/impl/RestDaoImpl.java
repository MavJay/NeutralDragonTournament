package com.mavjay.neutraldragontournament.dao.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mavjay.neutraldragontournament.dao.RestDao;
import com.mavjay.neutraldragontournament.model.LevelFixture;
import com.mavjay.neutraldragontournament.model.Match;
import com.mavjay.neutraldragontournament.model.Score;
import com.mavjay.neutraldragontournament.model.Tournament;
import com.mavjay.neutraldragontournament.web3.ContractInteraction;;

@Repository
public class RestDaoImpl implements RestDao {
	@Autowired
	private SessionFactory sessionFact;
	@Autowired ContractInteraction interaction;
	
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
		tn.setDuelsPlayed(0);
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
		try {
			ArrayList<Object> matchArray = new ArrayList();
			matchArray = interaction.getMatchFixture(dataList);
			for(int i=0;i<matchArray.size();){
				Query insertQuery = session.createSQLQuery("INSERT INTO matcharray(player1address,player2address) VALUES (?,?)");
				insertQuery.setParameter(0, matchArray.get(i));
				insertQuery.setParameter(1, matchArray.get(i+1));
				insertQuery.executeUpdate();
				i=i+2;
			}
			int levelNum = (int) session.createSQLQuery("select levelCount from FlagSettings").uniqueResult();
			for(int i=0;i<matchArray.size();i++){
				int wizardId = (int) session.createSQLQuery("select wizardId from Tournament where player=:player")
						.setParameter("player", matchArray.get(i))
						.uniqueResult();
				Query insertQuery = session.createSQLQuery("INSERT INTO levelfixture(playerAddress,wizardId,levelNum) VALUES (?,?,?)");
				insertQuery.setParameter(0, matchArray.get(i));
				insertQuery.setParameter(1, wizardId);
				insertQuery.setParameter(2, levelNum);
				insertQuery.executeUpdate();
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
			int duelsPlayed = 0;
			//player exist so continue to update their spells in DB
			for (Tournament tournament : uniqueappointment) {
				tournamentId = tournament.getTournamentId();	
				wizardSpell = tournament.getWizardSpell1();
				duelsPlayed = tournament.getDuelsPlayed();
			}
			if(wizardSpell == 0){
				
			
			System.out.println("tournamentId-->"+tournamentId);
			String updateQuery = "UPDATE Tournament SET wizardSpell1 = :wizardSpell1, wizardSpell2=:wizardSpell2,wizardSpell3=:wizardSpell3,"
					+ "wizardSpell4=:wizardSpell4,wizardSpell5=:wizardSpell5,duelsPlayed=:duelsPlayed where tournamentId=:tournamentId";
			Query qry = session.createQuery(updateQuery);
			qry.setParameter("wizardSpell1", wizardSpell1);
			qry.setParameter("wizardSpell2", wizardSpell2);
			qry.setParameter("wizardSpell3", wizardSpell3);
			qry.setParameter("wizardSpell4", wizardSpell4);
			qry.setParameter("wizardSpell5", wizardSpell5);
			qry.setParameter("tournamentId", tournamentId);
			qry.setParameter("duelsPlayed", duelsPlayed+1);
			qry.executeUpdate();
			
			String updateLevelFixture = "UPDATE LevelFixture SET spell1=:wizardSpell1, spell2=:wizardSpell2,spell3=:wizardSpell3,"
					+ "spell4=:wizardSpell4,spell5=:wizardSpell5 where playerAddress=:player and progress=:progress";
			Query qry1 = session.createQuery(updateLevelFixture);
			qry1.setParameter("wizardSpell1", wizardSpell1);
			qry1.setParameter("wizardSpell2", wizardSpell2);
			qry1.setParameter("wizardSpell3", wizardSpell3);
			qry1.setParameter("wizardSpell4", wizardSpell4);
			qry1.setParameter("wizardSpell5", wizardSpell5);
			qry1.setParameter("player", player);
			qry1.setParameter("progress", "inprogress");
			qry1.executeUpdate();
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
		
		ArrayList<Object> completedmatchList = new ArrayList<Object>();
		ArrayList<Object> scheduledmatchList = new ArrayList<Object>();
		ArrayList<Object> resultList = new ArrayList<Object>();
		List<LevelFixture> compeltedmatchDetails;
		
		Query completedMatches=	session.createQuery("from LevelFixture where progress=:progress")
				.setParameter("progress", "completed");
		compeltedmatchDetails=completedMatches.list();
//		Map<String, Object> appList = new HashMap<String, Object>();
		JSONObject appList = new JSONObject();
		for (LevelFixture match : compeltedmatchDetails) {
//			List<Object> dataList = new ArrayList<Object>();
			JSONObject dataList = new JSONObject();
			try {
			
			dataList.put("address",match.getPlayerAddress());
			dataList.put("wizid",match.getWizardId());
			dataList.put("spell1",match.getSpell1());
			dataList.put("spell2",match.getSpell2());
			dataList.put("spell3",match.getSpell3());
			dataList.put("spell4",match.getSpell4());
			dataList.put("spell5",match.getSpell5());
			dataList.put("roundwon",match.getRoundWon());
			dataList.put("level",match.getLevelNum());
			
			completedmatchList.add(dataList);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			appList.put("completed", completedmatchList);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<LevelFixture> scheduledmatchDetails;
		Query scheduledMatches=	session.createQuery("from LevelFixture where progress=:progress")
				.setParameter("progress", "inprogress");
		scheduledmatchDetails=scheduledMatches.list();
		for (LevelFixture match1 : scheduledmatchDetails) {
			JSONObject progressList = new JSONObject();
//			List<Object> dataList1 = new ArrayList<Object>();
			try {
			progressList.put("address",match1.getPlayerAddress());
			progressList.put("wizid",match1.getWizardId());
			progressList.put("level",match1.getLevelNum());
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			scheduledmatchList.add(progressList);
		}
		try {
			appList.put("scheduled",scheduledmatchList);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resultList.add(appList);
		System.out.println("List ::::::"+resultList);
		return resultList;
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
//		try {
////			cI.loadContract();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return "success";
	}
	
	@SuppressWarnings("null")
	public String calculateScore(){
		try{
		Session session = sessionFact.getCurrentSession();
		List<Match> matchFixture;
		Query matchQuery = session.createQuery("from Match");
		matchFixture = matchQuery.list();
		String wizard1Address = null,wizard2Address=null;
		int wizard1Spell[] = new int[5];
		int wizard2Spell[] = new int[5];
		int wizard1Affinity=0,wizard2Affinity=0;
		int wizard1Status =0; int wizard2Status = 0;
		
		for(Match match : matchFixture){
			int wizard1RoundWin = 0; int wizard2RoundWin = 0; int wizard1RoundLoss = 0; int wizard2RoundLoss = 0; long wizard1Timestamp=0; long wizard2Timestamp=0;
			int wizard1RoundTie = 0; int wizard2RoundTie = 0; int wizard1ElementalWin = 0; int wizard2ElementalWin = 0;
			int wizard1ElementalLoss = 0; int wizard2ElementalLoss = 0; int wizard1WinAgainst = 0; int wizard2WinAgainst = 0;
			int wizard1LossAgainst = 0; int wizard2LossAgainst = 0; int wizard1NormalWin =0; int wizard2NormalWin =0; int wizard1NormalLoss =0;
			int wizard2NormalLoss =0; int wizard1Score = 0; int wizard2Score = 0; 
			List<Tournament> wizardDetails;
			Query getWizardDetails = session.createQuery("from Tournament where player IN(:player1,:player2)")
					.setParameter("player1", match.getPlayer1Address())
					.setParameter("player2", match.getPlayer2Address());
			wizardDetails = getWizardDetails.list();
			int j=1;
			for(Tournament tournament : wizardDetails){
				if(j == 1){
					wizard1Address = tournament.getPlayer();
					wizard1Timestamp = tournament.getTimestamp();
					wizard1Affinity = tournament.getAffinityType();
					wizard1Spell[0] = tournament.getWizardSpell1();
					wizard1Spell[1] = tournament.getWizardSpell2();
					wizard1Spell[2] = tournament.getWizardSpell3();
					wizard1Spell[3] = tournament.getWizardSpell4();
					wizard1Spell[4] = tournament.getWizardSpell5();
				} else {
					wizard2Address = tournament.getPlayer();
					wizard2Timestamp = tournament.getTimestamp();
					wizard2Affinity = tournament.getAffinityType();
					wizard2Spell[0] = tournament.getWizardSpell1();
					wizard2Spell[1] = tournament.getWizardSpell2();
					wizard2Spell[2] = tournament.getWizardSpell3();
					wizard2Spell[3] = tournament.getWizardSpell4();
					wizard2Spell[4] = tournament.getWizardSpell5();
				}
				j++;
			}
			for(int i=0;i<5;i++){
				  if ((wizard1Spell[i]-wizard2Spell[i]) == 1 || (wizard1Spell[i]-wizard2Spell[i]) == -2) {
                      wizard1RoundWin = wizard1RoundWin+1;
                      wizard2RoundLoss = wizard2RoundLoss+1;
                      if(wizard1Spell[i] == wizard1Affinity && wizard2Spell[i] == wizard2Affinity){
                    	  wizard1Score = wizard1Score+3+2;
                    	  wizard2Score = wizard2Score-2-3;
                    	  wizard1ElementalWin = wizard1ElementalWin+1;
                    	  wizard2ElementalLoss = wizard2ElementalLoss+1;
                    	  wizard1WinAgainst = wizard1WinAgainst+1;
                    	  wizard2LossAgainst = wizard2LossAgainst+1;
                      } else if(wizard1Spell[i] == wizard1Affinity && wizard2Spell[i] != wizard2Affinity){
                    	  wizard1Score = wizard1Score+2;
                    	  wizard2Score = wizard2Score-3;
                    	  wizard1WinAgainst = wizard1WinAgainst+1;
                    	  wizard2ElementalLoss = wizard2ElementalLoss+1;
                      } else if(wizard1Spell[i] != wizard1Affinity && wizard2Spell[i] == wizard2Affinity){
                    	  wizard1Score = wizard1Score+3;
                    	  wizard2Score = wizard2Score-2;
                    	  wizard1ElementalWin = wizard1ElementalWin+1;
                    	  wizard2LossAgainst = wizard2LossAgainst+1;
                      } else if(wizard1Spell[i] != wizard1Affinity && wizard2Spell[i] != wizard2Affinity){
                    	  wizard1Score = wizard1Score+1;
                    	  wizard2Score = wizard2Score-1;
                    	  wizard1NormalWin = wizard1NormalWin+1;
                    	  wizard2NormalLoss = wizard2NormalLoss+1;
                      }
                 } else if ((wizard1Spell[i]-wizard2Spell[i]) == -1 || (wizard1Spell[i]-wizard2Spell[i]) == 2) {
                	 wizard2RoundWin = wizard2RoundWin+1;
                     wizard1RoundLoss = wizard1RoundLoss+1;
                     if(wizard1Spell[i] == wizard1Affinity && wizard2Spell[i] == wizard2Affinity){
                   	  wizard2Score = wizard2Score+3+2;
                   	  wizard1Score = wizard1Score-2-3;
                   	 wizard2ElementalWin = wizard2ElementalWin+1;
               	  wizard1ElementalLoss = wizard1ElementalLoss+1;
               	  wizard2WinAgainst = wizard2WinAgainst+1;
               	  wizard1LossAgainst = wizard1LossAgainst+1;
                     } else if(wizard1Spell[i] == wizard1Affinity && wizard2Spell[i] != wizard2Affinity){
                   	  wizard2Score = wizard2Score+2;
                   	  wizard1Score = wizard1Score-3;
                   	 wizard2WinAgainst = wizard2WinAgainst+1;
               	  wizard1ElementalLoss = wizard1ElementalLoss+1;
                     } else if(wizard1Spell[i] != wizard1Affinity && wizard2Spell[i] == wizard2Affinity){
                   	  wizard2Score = wizard2Score+3;
                   	  wizard1Score = wizard1Score-2;
                   	wizard2ElementalWin = wizard2ElementalWin+1;
              	  wizard1LossAgainst = wizard1LossAgainst+1;
                     } else if(wizard1Spell[i] != wizard1Affinity && wizard2Spell[i] != wizard2Affinity){
                   	  wizard2Score = wizard2Score+1;
                   	  wizard1Score = wizard1Score-1;
                   	wizard2NormalWin = wizard2NormalWin+1;
              	  wizard1NormalLoss = wizard1NormalLoss+1;
                     }
                 }else{
                     wizard1RoundTie = wizard1RoundTie+1;
                     wizard2RoundTie = wizard2RoundTie+1;
                 }
			}
			if(wizard1RoundWin>wizard2RoundWin){
				wizard1Status = 0;
				wizard2Status = 1;
			} else if(wizard1RoundWin<wizard2RoundWin){
				wizard1Status = 1;
				wizard2Status = 0;
			} else if(wizard1Score>wizard2Score){
				wizard1Status = 0;
				wizard2Status = 1;
			} else if(wizard1Score<wizard2Score){
				wizard1Status = 1;
				wizard2Status = 0;
			} else if(wizard1Timestamp>wizard2Timestamp){
				wizard1Status = 0;
				wizard2Status = 1;
			} else if(wizard1Timestamp<wizard2Timestamp){
				wizard1Status = 1;
				wizard2Status = 0;
			}
			List<Score> getScore;
			Query scoreQuery = session.createQuery("from Score where plrAddress IN(:player1,:player2)")
					.setParameter("player1", match.getPlayer1Address())
					.setParameter("player2", match.getPlayer2Address());
			getScore = scoreQuery.list();
			if(getScore.isEmpty() || getScore == null){
				for(int i=0;i<2;i++){
					Score score1 = new Score();
					if(i==0){
					score1.setPlrAddress(wizard1Address);
					score1.setPlrStatus(wizard1Status);
					score1.setTotalScore(wizard1Score);
					score1.setNoOfWins(wizard1RoundWin);
					score1.setNoOfLoss(wizard1RoundLoss);
					score1.setNoOftie(wizard1RoundTie);
					score1.setElementalWin(wizard1ElementalWin);
					score1.setElementalLoss(wizard1ElementalLoss);
					score1.setNoOfWinAgainstElemental(wizard1WinAgainst);
					score1.setNoOfLossAgainstElemental(wizard1LossAgainst);
					session.save(score1);
					String updateSpell = "UPDATE LevelFixture SET wizardSpell1=:wizardSpell1, wizardSpell2=:wizardSpell2,wizardSpell3=:wizardSpell3,"
							+ "wizardSpell4=:wizardSpell4,wizardSpell5=:wizardSpell5 where player=:player";
					Query qry1 = session.createQuery(updateSpell);
					qry1.setParameter("wizardSpell1", 0);
					qry1.setParameter("wizardSpell2", 0);
					qry1.setParameter("wizardSpell3", 0);
					qry1.setParameter("wizardSpell4", 0);
					qry1.setParameter("wizardSpell5", 0);
					qry1.setParameter("player", wizard1Address);
					qry1.executeUpdate();
					} else {
						score1.setPlrAddress(wizard2Address);
						score1.setPlrStatus(wizard2Status);
						score1.setTotalScore(wizard2Score);
						score1.setNoOfWins(wizard2RoundWin);
						score1.setNoOfLoss(wizard2RoundLoss);
						score1.setNoOftie(wizard2RoundTie);
						score1.setElementalWin(wizard2ElementalWin);
						score1.setElementalLoss(wizard2ElementalLoss);
						score1.setNoOfWinAgainstElemental(wizard2WinAgainst);
						score1.setNoOfLossAgainstElemental(wizard2LossAgainst);
						session.save(score1);
						String updateSpell = "UPDATE LevelFixture SET wizardSpell1=:wizardSpell1, wizardSpell2=:wizardSpell2,wizardSpell3=:wizardSpell3,"
								+ "wizardSpell4=:wizardSpell4,wizardSpell5=:wizardSpell5 where player=:player";
						Query qry1 = session.createQuery(updateSpell);
						qry1.setParameter("wizardSpell1", 0);
						qry1.setParameter("wizardSpell2", 0);
						qry1.setParameter("wizardSpell3", 0);
						qry1.setParameter("wizardSpell4", 0);
						qry1.setParameter("wizardSpell5", 0);
						qry1.setParameter("player", wizard2Address);
						qry1.executeUpdate();
					}
				}
				
				
			} else {
				for(Score score : getScore){
					if(wizard1Address.equals(score.getPlrAddress())){
						int updateScore = session.createQuery("UPDATE Score SET plrStatus=:status,totalScore=:score,noOfWins=:win,"
								+ "noOfLoss=:loss,noOfTie=:tie,elementalWin=:elementalWin,elementalLoss=:elementalLoss,noOfWinAgainstElemental=:winAgainst,noOfLossAgainstElemental=:lossAgainst "
								+ "where plrAddress=:player").setParameter("player", wizard1Address)
								.setParameter("status", wizard1Status)
								.setParameter("score", score.getTotalScore()+wizard1Score)
								.setParameter("win", score.getNoOftie()+wizard1RoundWin)
								.setParameter("loss", score.getNoOfLoss()+wizard1RoundLoss)
								.setParameter("tie", score.getNoOftie()+wizard1RoundTie)
								.setParameter("elementalWin", score.getElementalWin()+wizard1ElementalWin)
								.setParameter("elementalLoss", score.getElementalLoss()+wizard1ElementalLoss)
								.setParameter("winAgainst", score.getNoOfWinAgainstElemental()+wizard1WinAgainst)
								.setParameter("lossAgainst", score.getNoOfLossAgainstElemental()+wizard1LossAgainst).executeUpdate();
						String updateSpell = "UPDATE LevelFixture SET wizardSpell1=:wizardSpell1, wizardSpell2=:wizardSpell2,wizardSpell3=:wizardSpell3,"
								+ "wizardSpell4=:wizardSpell4,wizardSpell5=:wizardSpell5 where player=:player";
						Query qry1 = session.createQuery(updateSpell);
						qry1.setParameter("wizardSpell1", 0);
						qry1.setParameter("wizardSpell2", 0);
						qry1.setParameter("wizardSpell3", 0);
						qry1.setParameter("wizardSpell4", 0);
						qry1.setParameter("wizardSpell5", 0);
						qry1.setParameter("player", wizard1Address);
						qry1.executeUpdate();
					} else {
						int updateScore = session.createQuery("UPDATE Score SET plrStatus=:status,totalScore=:score,noOfWins=:win,"
								+ "noOfLoss=:loss,noOfTie=:tie,elementalWin=:elementalWin,elementalLoss=:elementalLoss,noOfWinAgainstElemental=:winAgainst,noOfLossAgainstElemental=:lossAgainst "
								+ "where plrAddress=:player").setParameter("player", wizard2Address)
								.setParameter("status", wizard2Status)
								.setParameter("score", score.getTotalScore()+wizard2Score)
								.setParameter("win", score.getNoOftie()+wizard2RoundWin)
								.setParameter("loss", score.getNoOfLoss()+wizard2RoundLoss)
								.setParameter("tie", score.getNoOftie()+wizard2RoundTie)
								.setParameter("elementalWin", score.getElementalWin()+wizard2ElementalWin)
								.setParameter("elementalLoss", score.getElementalLoss()+wizard2ElementalLoss)
								.setParameter("winAgainst", score.getNoOfWinAgainstElemental()+wizard2WinAgainst)
								.setParameter("lossAgainst", score.getNoOfLossAgainstElemental()+wizard2LossAgainst).executeUpdate();
						String updateSpell = "UPDATE LevelFixture SET wizardSpell1=:wizardSpell1, wizardSpell2=:wizardSpell2,wizardSpell3=:wizardSpell3,"
								+ "wizardSpell4=:wizardSpell4,wizardSpell5=:wizardSpell5 where player=:player";
						Query qry1 = session.createQuery(updateSpell);
						qry1.setParameter("wizardSpell1", 0);
						qry1.setParameter("wizardSpell2", 0);
						qry1.setParameter("wizardSpell3", 0);
						qry1.setParameter("wizardSpell4", 0);
						qry1.setParameter("wizardSpell5", 0);
						qry1.setParameter("player", wizard2Address);
						qry1.executeUpdate();
						
					}
				}
			}
			
		}
		if(matchFixture.size() == 1){
			String winner3 =null;
			List<Score> getScore;
			Query queryScore = session.createSQLQuery("SELECT TOP 1 * from Score s ORDER BY s.totalScore DESC");
			getScore = queryScore.list();
			for(Score score : getScore){
				winner3 = score.getPlrAddress();
			}
			String winner1 =null,winner2 = null;
			//distribute Prize Money
			if(wizard1Status == 0){
				ContractInteraction contract = new ContractInteraction();
				winner1 = wizard1Address;
				winner2 = wizard2Address;
				contract.distributePrize(winner1,winner2,winner3);
			} else if(wizard2Status == 0){
				ContractInteraction contract = new ContractInteraction();
				winner1 = wizard2Address;
				winner2 = wizard1Address;
				contract.distributePrize(winner1,winner2,winner3);
			}
			
		}
		int deleteMatchFixtures = session.createQuery("delete Match").executeUpdate();
		return "success";
		} catch(Exception e){
			e.printStackTrace();
			return "failed";
		}
	}
	
	public ArrayList<Object> getScoreArr(){
		Session session = sessionFact.getCurrentSession();
		
		ArrayList<Object> resultScore = new ArrayList<>();
		List<Score> getScore;
		Query queryScore = session.createQuery("from Score s ORDER BY s.totalScore DESC");
		getScore = queryScore.list();
		int i=1;
		for(Score score : getScore){
			List<Object> scoreTable = new ArrayList<>();
			Query getPlayerDetails = (Query) session.createQuery("from Tournament where player=:player")
					.setParameter("player", score.getPlrAddress()).uniqueResult();
			List<Tournament> getPlayer = getPlayerDetails.list();
			int duelsPlayed = 0;
			for(Tournament tournament: getPlayer){
				scoreTable.add(tournament.getWizardId());
				scoreTable.add(tournament.getAffinityType());
				duelsPlayed = tournament.getDuelsPlayed();
			}
			scoreTable.add(score.getPlrAddress());
			scoreTable.add(i);
			scoreTable.add(score.getTotalScore());
			scoreTable.add(duelsPlayed);
			scoreTable.add(score.getNoOfWins());
			scoreTable.add(score.getNoOfLoss());
			scoreTable.add(score.getNoOftie());
			scoreTable.add(score.getNoOfWinAgainstElemental());
			scoreTable.add(score.getNoOfLossAgainstElemental());
			scoreTable.add(score.getElementalWin());
			scoreTable.add(score.getElementalLoss());
			resultScore.add(scoreTable);
			i++;
		}
		System.out.println("Result update SCore ::;:: "+resultScore);
		return resultScore;
	}
	
	public String getContractAddress(){
		Session session = sessionFact.getCurrentSession();
		 String contractAddress = (String) session.createSQLQuery("select contractAddress from FlagSettings").uniqueResult();
		return contractAddress;
	}
}
