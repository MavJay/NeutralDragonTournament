package com.mavjay.neutraldragontournament.web3;


import java.io.IOException;
import java.math.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.util.SystemOutLogger;
import org.springframework.stereotype.Service;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;

@Service
public class ContractInteraction {
	Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));
	Credentials credentials = null;
	private NeutralDragonTournament contract1;
	private String contractAddress = null;
	public int loadContract() throws Exception{
		credentials = Credentials.create("0x37e1de971d2d11d47bafb15cb6509e711510d8ee19e049d029107ff794703d35"); 
		@SuppressWarnings("deprecation")
		NeutralDragonTournament contract = NeutralDragonTournament.deploy(this.web3j, credentials, ManagedTransaction.GAS_PRICE, Contract.GAS_LIMIT).send();
		contractAddress = contract.getContractAddress();
		System.out.println("Is contract Valid :"+contract.isValid());
		System.out.println("Is contract Valid :"+contract.getContractBinary());
		RemoteCall<BigInteger> contractCall = contract.tournamentStartTimer();
		BigInteger result = contractCall.send();
	System.out.println("Contarct Interaction Inside"+result);
		return result.intValue();
	}
	
	@SuppressWarnings("deprecation")
	public int getTimer() throws Exception{
		contract1 = NeutralDragonTournament.load(contractAddress, web3j, credentials, ManagedTransaction.GAS_PRICE,Contract.GAS_LIMIT);
		RemoteCall<BigInteger> contractCall = contract1.tournamentStartTimer();
		BigInteger result = contractCall.send();
		System.out.println("Getting End timer on load"+result);
		return result.intValue();
	}
	
	@SuppressWarnings("deprecation")
	public ArrayList<Object> getMatchFixture(List<String> dataList2) throws Exception{
		ArrayList<Object> dataList = new ArrayList<Object>();
		contractAddress = "0xd5c86644fece0d3813a07ec21efccfe290ebd646";
		credentials = Credentials.create("0x37e1de971d2d11d47bafb15cb6509e711510d8ee19e049d029107ff794703d35");
		System.out.println("Contract and Web3j and Credentials :::::::"+contractAddress+"  "+web3j+"     "+credentials);
		contract1 = NeutralDragonTournament.load(contractAddress, web3j, credentials, ManagedTransaction.GAS_PRICE,Contract.GAS_LIMIT);
		System.out.println("Contract ADdress::::::"+contract1.getContractAddress());
		RemoteCall<Tuple2<List<String>, List<String>>> contractCall = contract1.roundFixture(dataList2);
		Tuple2<List<String>, List<String>> result = contractCall.send();
		for(int i=0;i<result.getSize();i++){
			dataList.add(result.getValue1().get(i));
			dataList.add(result.getValue2().get(i));
		}
		return dataList;
	}
}
