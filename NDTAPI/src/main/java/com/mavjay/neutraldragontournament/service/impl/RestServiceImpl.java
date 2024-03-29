package com.mavjay.neutraldragontournament.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import com.mavjay.neutraldragontournament.dao.RestDao;
import com.mavjay.neutraldragontournament.service.RestService;

@Service
public class RestServiceImpl implements RestService {
	
	@Autowired
	RestDao restDao;

	@Transactional
	public String joinTournament(String player, int wizardId,int affinityType) {
		return restDao.joinTournament( player,  wizardId, affinityType);
	}
	
	@Transactional
	public List<String> roundFixture() {
		return restDao.roundFixture();
	}

	@Transactional
	public String updatePlaceSpell(String player,int wizardSpell1,
			int wizardSpell2,int wizardSpell3,int wizardSpell4,int wizardSpell5) {
		return restDao.updatePlaceSpell(player, wizardSpell1,
				 wizardSpell2, wizardSpell3, wizardSpell4, wizardSpell5);
	}
	
	@Transactional
	public ArrayList<Object> getMatchArr(){
		return restDao.getMatchArr();
	}
	
	@Transactional
	public String getPlayerDetails(String playerAddr){
		return restDao.getPlayerDetails(playerAddr);
	}
	
	@Transactional
	public String getTimer() {
		return restDao.getTimer();
	}
	
	@Transactional
	public String calculateScore(){
		return restDao.calculateScore();
	}
	
	@Transactional
	public String getScoreArr(){
		return restDao.getScoreArr();
	}

//	@Transactional
//	public String getWizardData() {
//		// TODO Auto-generated method stub
//		return restDao.getWizardData();
//	}
//
//	@Transactional
//	public String getWizardDataWithMinpower() {
//		// TODO Auto-generated method stub
//		return restDao.getWizardDataWithMinpower();
//	}
//	
		@Transactional
	public String getContractAddress(){
		return restDao.getContractAddress();
	}

		@Transactional
		public String getWizid(String address) {
		
			return restDao.getWizid(address);
		}
}
