package com.mavjay.neutraldragontournament.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.core.RemoteCall;

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
	
	@RequestMapping(value = { "roundfixture" }, produces = "application/json", method = RequestMethod.POST)
	public List<String> roundFixture(@RequestHeader Map<String, String> headers) {
		//System.out.println(headers);
		List<String> playerList = restService.roundFixture(); 
		return playerList;
	}
	
	@RequestMapping(value = { "updateplacespell" }, produces = "application/json", method = RequestMethod.POST)
	public String updatePlaceSpell(@RequestParam Map<String, String> allRequestParams) {
		return restService.updatePlaceSpell(allRequestParams.get("player"),Integer.parseInt(allRequestParams.get("wizardspell1")),Integer.parseInt(allRequestParams.get("wizardspell2")),Integer.parseInt(allRequestParams.get("wizardspell3")),Integer.parseInt(allRequestParams.get("wizardspell4")),
						Integer.parseInt(allRequestParams.get("wizardspell5")));
	}
	
	@RequestMapping(value = { "getMatchArr" },produces = "application/json", method = RequestMethod.POST)
	public ArrayList<Object> getMatchArr(@RequestParam Map<String, String> allRequestParam) {
		ArrayList<Object> result = restService.getMatchArr();
		return result;
	}
	
	@RequestMapping(value = { "getPlayerDetails" },produces = "application/json", method = RequestMethod.POST)
	public String getPlayerDetails(@RequestParam Map<String, String> allRequestParam) {
		String result = restService.getPlayerDetails(allRequestParam.get("player"));
		return result;
	}
	
	@RequestMapping(value = { "updateMatchFixture" },produces = "application/json", method = RequestMethod.POST)
	public int updateMatchFixture(@RequestParam Map<String, String> allRequestParam) throws Exception {
		System.out.println("Address pf Match arr"+allRequestParam);
//		String result = String.valueOf(restService.updateMatchFixture(allRequestParam.get("matchArr")));
		int result = contractInteraction.loadContract();
		int result1 = contractInteraction.getTimer();
		return result;
	}
	
	@RequestMapping(value = { "getScoreArr" },produces = "application/json", method = RequestMethod.POST)
	public ArrayList<Object> getScoreArr(@RequestParam Map<String, String> allRequestParam) {
		ArrayList<Object> result = restService.getScoreArr();
		return result;
	}

}
