package com.mavjay.neutraldragontournament.controller;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mavjay.neutraldragontournament.service.RestService;
import com.mavjay.neutraldragontournament.web3.ContractInteraction;



/**
 * @author MavJay Contains all the controller methods for WebService
 */
@RestController
@RequestMapping(value = { "rest" })
public class MainController {
	
	@Autowired
	RestService restService;
	@Autowired
	ContractInteraction contractInteraction;
	@RequestMapping(value = { "jointournament" }, produces = "application/json", method = RequestMethod.POST)
	public String joinTournament(@RequestParam Map<String, String> allRequestParam) {
		System.out.println(allRequestParam);
		return restService.joinTournament(allRequestParam.get("player"),Integer.parseInt(allRequestParam.get("wizardid")),
				Integer.parseInt(allRequestParam.get("affinitytype")));
	}
	
//	@RequestMapping(value = { "roundfixture" }, produces = "application/json", method = RequestMethod.POST)
//	public List<String> roundFixture(@RequestHeader Map<String, String> headers) {
//		//System.out.println(headers);
//		List<String> playerList = restService.roundFixture(); 
//		return playerList;
//	}
	
	@RequestMapping(value = { "updateplacespell" }, produces = "application/json", method = RequestMethod.POST)
	public String updatePlaceSpell(@RequestParam Map<String, String> allRequestParams) {
		return restService.updatePlaceSpell(allRequestParams.get("player"),Integer.parseInt(allRequestParams.get("wizardspell1")),Integer.parseInt(allRequestParams.get("wizardspell2")),Integer.parseInt(allRequestParams.get("wizardspell3")),Integer.parseInt(allRequestParams.get("wizardspell4")),
						Integer.parseInt(allRequestParams.get("wizardspell5")));
	}
	
	@RequestMapping(value = { "getMatchArr" },produces = "application/json", method = RequestMethod.POST)
	public String getMatchArr(@RequestParam Map<String, String> allRequestParam) {
		 
		return restService.getMatchArr().toString();
	}
	
	@RequestMapping(value = { "getPlayerDetails" },produces = "application/json", method = RequestMethod.POST)
	public String getPlayerDetails(@RequestParam Map<String, String> allRequestParam) {
		String result = restService.getPlayerDetails(allRequestParam.get("player"));
		return result;
	}
	
//	@RequestMapping(value = { "updateMatchFixture" },produces = "application/json", method = RequestMethod.POST)
//	public String updateMatchFixture(@RequestParam Map<String, String> allRequestParam) throws Exception {
//		System.out.println("Address pf Match arr"+allRequestParam);
////		String result = String.valueOf(restService.updateMatchFixture(allRequestParam.get("matchArr")));
//		String winner1 = "0x2Cd82Fc938537aE573e88d7c6199C5142981D996";
//		String winner2 = "0xe28713AD22c9f568888b980139dCc3A96Fe434aE";
//		String winner3 = "0x0fb380FF187f437365a1BBdF3951EdFA4f9243f3";
////		String result = contractInteraction.distributePrize(winner1, winner2, winner3);
//		String result = contractInteraction.loadContract();
////		String result = restService.calculateScore();
//		return result;
//	}
	
	
	
	@RequestMapping(value = { "gettimer" },produces = "application/json", method = RequestMethod.POST)
	public String getTimer(@RequestParam Map<String, String> allRequestParam) {
		String result = restService.getTimer();
		return result;
	}
		@RequestMapping(value = { "getScoreArr" },produces = "application/json", method = RequestMethod.POST)
	public ArrayList<Object> getScoreArr(@RequestParam Map<String, String> allRequestParam) {
		ArrayList<Object> result = restService.getScoreArr();
		return result;
	}
	
	@RequestMapping(value = { "getContractAddress" },produces = "application/json", method = RequestMethod.POST)
	public String getContractAddress(@RequestParam Map<String, String> allRequestParam) {
		String result = restService.getContractAddress();
		return result;
	}

}
