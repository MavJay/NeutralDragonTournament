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
RestService restService;
	
	
	
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
		prop.clear();
		inputStream.close();
		return contractAddress;
	}
	
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
		for(int i=0;i<result.getSize()-1;i++){
			dataList.add(result.getValue1().get(i));
			dataList.add(result.getValue2().get(i));
		}
		prop.clear();
		inputStream.close();
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
		BigInteger weivalue = new BigInteger("0");
		RemoteCall<TransactionReceipt> contractCall = contract1.distributePrize(winner1, winner2, winner3, weivalue);
		TransactionReceipt result = contractCall.send();
		EthGetBalance ethGetBalance = web3j
				  .ethGetBalance(contractAddress, DefaultBlockParameterName.LATEST)
				  .sendAsync()
				  .get();

				BigInteger wei = ethGetBalance.getBalance();
		System.out.println("Start Duel Return"+result);
		System.out.println("Contract Balance"+wei);
		prop.clear();
		inputStream.close();
		return "success";
	}
	
}
