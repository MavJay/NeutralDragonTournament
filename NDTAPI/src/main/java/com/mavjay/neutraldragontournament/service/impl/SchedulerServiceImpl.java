package com.mavjay.neutraldragontournament.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.tomcat.jni.Time;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mavjay.neutraldragontournament.dao.SchedulerDao;
import com.mavjay.neutraldragontournament.model.FlagSettings;
import com.mavjay.neutraldragontournament.web3.ContractInteraction;
@Service
public class SchedulerServiceImpl  {
	
//	@Autowired
//	private SchedulerDao schedulerDao;
	
	@Autowired ContractInteraction interaction;
	
	@Autowired
	private SessionFactory sessionFact;
	
	private int tournament_flag=0;
	private int deployment_flag=0;
	private int level_flag=1;

	public SchedulerServiceImpl() {
		
	}

	// on first call deploy contract , second call will be after a year (make it maximum delay)
//	@Scheduled(initialDelay= 5000,fixedRate=10*30*9000000)
//	@Transactional
//	public void deployContract(){
//		
//		
//		
//	}
	
	@Scheduled(initialDelay=6000,fixedRate=365*24*60*60*1000)
	@Transactional
	public void deployContract(){
	
		Session session = sessionFact.getCurrentSession();
//		Add contract deployment method here

	
			if(deployment_flag==0) {
	        //method 1
	        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	      //  System.out.println(timestamp);
	        System.out.println("deploy contract: "+timestamp);	
	        deployment_flag=1;
	        try {
	        	String contractAddress=		interaction.loadContract();
		
	        	session.createSQLQuery("update FlagSettings set contractaddress=:address")
				.setParameter("address", contractAddress).executeUpdate();
	        	System.out.println(contractAddress);
				
				
			} catch (Exception e) {
			
				e.printStackTrace();
			}
	      
			}
			
	}
	
	// first call start tournament timer , second call end tournament timer
	@Scheduled(initialDelay=2*60*1000,fixedRate=1*60*1000)
	@Transactional
	public void startTournament() {
		
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if(tournament_flag==0) {

	
	        System.out.println("start tournament joining timer: "+timestamp);
	        tournament_flag=1;
		}
		else if(tournament_flag==1) {
			 
	        System.out.println("end tournament joining timer: "+timestamp);
	        tournament_flag=2;
		}
		
	}


	@Scheduled(initialDelay=3*1000,fixedRate=5*1000)
	@Transactional
	public void increaseLevel() {
		Session session = sessionFact.getCurrentSession();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println("level check: "+timestamp);
		session.createSQLQuery("update FlagSettings set levelcount=:count")
		.setParameter("count", level_flag).executeUpdate();
		level_flag++;

	}
	

	
}
