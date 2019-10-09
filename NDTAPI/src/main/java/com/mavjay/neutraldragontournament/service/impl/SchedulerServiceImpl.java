package com.mavjay.neutraldragontournament.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mavjay.neutraldragontournament.dao.SchedulerDao;
import com.mavjay.neutraldragontournament.web3.ContractInteraction;
@Service
public class SchedulerServiceImpl  {
	
//	@Autowired
//	private SchedulerDao schedulerDao;
	
	@Autowired ContractInteraction interaction;
	
	private int deploy_flag=0;
	private int b=0;
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
	
	@Scheduled(initialDelay=1*60*1000,fixedRate=365*24*60*60*1000)
	@Transactional
	public void deployContract(){
	
		
//		Add contract deployment method here

	
			if(b==0) {
	        //method 1
	        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	      //  System.out.println(timestamp);
	        System.out.println("deploy contract: "+timestamp);	
	        b=1;
	        try {
				interaction.loadContract();
			} catch (Exception e) {
			
				e.printStackTrace();
			}
	      
			}
			
	}
	
	// first call start tournament timer , second call end tournament timer
	@Scheduled(initialDelay=2*60*1000,fixedRate=1*60*1000)
	@Transactional
	public void startTournament() {
		   //method 1
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if(deploy_flag==0) {

	      //  System.out.println(timestamp);
	        System.out.println("start tournament joining timer: "+timestamp);
	        deploy_flag=1;
		}
		else if(deploy_flag==1) {
			   //method 1
	    //    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	      //  System.out.println(timestamp);
	        System.out.println("end tournament joining timer: "+timestamp);
	        deploy_flag=2;
		}
		
	}


	@Scheduled(initialDelay=3*60*1000,fixedRate=1*60*1000)
	@Transactional
	public void increaseLevel() {
		   //method 1
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println("level check: "+timestamp);

	//	return schedulerDao.increaseLevel();
	}
	

	
}
