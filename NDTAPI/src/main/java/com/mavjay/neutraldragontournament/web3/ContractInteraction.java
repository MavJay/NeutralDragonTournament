package com.mavjay.neutraldragontournament.web3;


import java.io.IOException;
import java.io.InputStream;
import java.math.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
import org.web3j.tx.Transfer;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.utils.Convert.Unit;

import com.mavjay.neutraldragontournament.service.RestService;


@Service
public class ContractInteraction {
@Autowired
private RestService restService;
	
	
	
//	Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));
	Credentials credentials = null;
	private NeutralDragonTournament contract1;
	private String contractAddress = null;
	PropertiesConfiguration prop = new PropertiesConfiguration();
	InputStream inputStream;
	public String loadContract() throws Exception{
		
		inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
		prop.load(inputStream);
		String keyPair = prop.getProperty("privateKey").toString();
		Web3j web3j = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/"+prop.getProperty("infuraKey").toString()));
		credentials = Credentials.create(keyPair);
		BigInteger gasPrice = BigInteger.valueOf(4700000); //  0.0000000000047 ETHER
		BigInteger gasLimit = BigInteger.valueOf(3100000); // 0.0000000000031 ETHER
		@SuppressWarnings("deprecation")
		NeutralDragonTournament contract = NeutralDragonTournament.deploy(web3j, credentials,gasPrice,gasLimit).send();
		contractAddress = contract.getContractAddress();
		System.out.println("Is contract Valid :"+contract.isValid());
		System.out.println("Is contract Valid :"+contractAddress);
		return "success";
	}
	
//	@SuppressWarnings("deprecation")
//	public int getTimer() throws Exception{
//		inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
//		prop.load(inputStream);
//		String keyPair = prop.getProperty("privateKey").toString();
//		Web3j web3j = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/"+prop.getProperty("infuraKey").toString()));
//		contract1 = NeutralDragonTournament.load(contractAddress, web3j, credentials, ManagedTransaction.GAS_PRICE,Contract.GAS_LIMIT);
//		RemoteCall<BigInteger> contractCall = contract1.tournamentStartTimer();
//		BigInteger result = contractCall.send();
//		System.out.println("Getting End timer on load"+result);
//		return result.intValue();
//	}
	
	@SuppressWarnings("deprecation")
	public ArrayList<Object> getMatchFixture(List<String> dataList2) throws Exception{
		inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
		prop.load(inputStream);
		String keyPair = prop.getProperty("privateKey").toString();
		Web3j web3j = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/"+prop.getProperty("infuraKey").toString()));
		ArrayList<Object> dataList = new ArrayList<Object>();
		contractAddress = restService.getContractAddress();
		credentials = Credentials.create(keyPair);
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
	
	
	@SuppressWarnings("deprecation")
	public String distributePrize(String winner1,String winner2,String winner3) throws Exception{
		inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
		prop.load(inputStream);
		String keyPair = prop.getProperty("privateKey").toString();
		Web3j web3j = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/"+prop.getProperty("infuraKey").toString()));
		contractAddress = restService.getContractAddress();
		credentials = Credentials.create(keyPair);
		contract1 = NeutralDragonTournament.load(contractAddress, web3j, credentials, ManagedTransaction.GAS_PRICE,Contract.GAS_LIMIT);
//		System.out.println("Contract ADdress::::::"+contract1.getContractAddress());
		BigInteger weivalue = new BigInteger("0");
		RemoteCall<TransactionReceipt> contractCall = contract1.distributePrize(winner1, winner2, winner3, weivalue);
		TransactionReceipt result = contractCall.send();
		EthGetBalance ethGetBalance = web3j
				  .ethGetBalance(contractAddress, DefaultBlockParameterName.LATEST)
				  .sendAsync()
				  .get();

				BigInteger wei = ethGetBalance.getBalance();
//				TransactionReceipt transactionReceipt = Transfer.sendFunds(
//				        web3j, credentials, "0xe28713AD22c9f568888b980139dCc3A96Fe434aE",
//				        BigDecimal.valueOf(1.0),Unit.ETHER).send();
//				System.out.println("Amount Transfered"+transactionReceipt);
		System.out.println("Start Duel Return"+result);
		System.out.println("Contract Balance"+wei);
		return "success";
	}
	
}
