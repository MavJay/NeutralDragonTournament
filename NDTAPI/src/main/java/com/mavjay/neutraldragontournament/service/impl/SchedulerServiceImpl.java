package com.mavjay.neutraldragontournament.service.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.hibernate.Query;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	
	@Scheduled(initialDelay=6000,fixedRate=365*24*60*60*1000)
	@Transactional
	public void deployContract(){
	
		Session session = sessionFact.getCurrentSession();
//		Add contract deployment method here
			if(deployment_flag==0) {
	        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	        System.out.println("deploy contract: "+timestamp);	
	        deployment_flag=1;
	        try {
	        	        	
	        	//String strCntSTime = (String) session.createQuery("select contractdeploystarttime from FlagSettings")
	    		//.uniqueResult();
	        	Query userQuery = (Query) session.createQuery(
	    		"from FlagSettings");
	        	//userQuery.addEntity(FlagSettings.class);
	        	
				List<FlagSettings> list =  (List<FlagSettings>)userQuery.list();
				String strCntSTime = new String();
				System.out.println("data in DB size-->"+list.size());
				System.out.println("data in DB value-->"+list.toString());
				Date dateCDT = null;
				DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if (list != null) {
					for(FlagSettings user : list){
						
						try {
							dateCDT = df1.parse(user.getContractdeploystarttime());
						} catch (ParseException e) {
							e.printStackTrace();
						}
						//dateCDT = (Date) formatter.parse(user.getContractdeploystarttime());
					}
				}else {
					System.out.println("No data in DB");
				}
		
	        	System.out.println("CntSTime-->"+dateCDT);
	       if(strCntSTime!="") {
	        		System.out.println("Contract already deployed");
	        		startTournament();
		        		
	        	}else {
	        		System.out.println("Contract not deployed. So, inserting in FlagSettings table and loading contract");
	        		String contractAddress=		interaction.loadContract();
	        		//insert flagsetting settings tables: contractAddress, timing=now()
		        	FlagSettings cntInfo = new FlagSettings();
		        	cntInfo.setContractAddress(contractAddress);
		        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		    		Date date = new Date();
		        	cntInfo.setContractdeploystarttime(formatter.format(date));
		        	session.save(cntInfo);
		        	
		        	session.createSQLQuery("update FlagSettings set contractaddress=:address")
					.setParameter("address", contractAddress).executeUpdate();
		        	System.out.println(contractAddress);
	        	}
	        	
	        	
				
				
			} catch (Exception e) {
			
				e.printStackTrace();
			}
	      
			}
			
	}
	
	// first call start tournament timer , second call end tournament timer
//	@Scheduled(initialDelay=2*60*1000,fixedRate=1*60*1000)
	@Scheduled(initialDelay=10000,fixedRate=1*60*1000)
	@Transactional
	public void startTournament() {
		
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if(tournament_flag==0) {
			Session session = sessionFact.getCurrentSession();
	        System.out.println("start tournament joining timer: "+timestamp);
	        tournament_flag=1;
//	        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
	        
//	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
//	        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//	        SimpleDateFormat localDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
//	        Date now;
//			try {
//				now = localDateFormat.parse( simpleDateFormat.format(new Date()) );
//				 Date  endtime = DateUtils.addMinutes(now, 20);
//			        System.out.println("current utc"+now);
//			        session.createSQLQuery("update FlagSettings set tournamentstarttime=:time")
//					.setParameter("time", endtime.toString()).executeUpdate();
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	        
	       
	        
//	        System.out.println("end time"+ now.plusMinutes(20));
//	        OffsetDateTime contractAddress=		now.plusMinutes(20);
        	
	        
	        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
	        OffsetDateTime end= now.plusSeconds(60);
	        long timeinmilli= ChronoUnit.MILLIS.between(now, end);
	        System.out.println(now+"   "+ end+"   "+ChronoUnit.MILLIS.between(now, end));
	        session.createSQLQuery("update FlagSettings set tournamentstarttime=:time")
			.setParameter("time", String.valueOf(timeinmilli)).executeUpdate();
        	
        	
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
