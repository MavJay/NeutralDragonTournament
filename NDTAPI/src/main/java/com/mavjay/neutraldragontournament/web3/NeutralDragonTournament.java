package com.mavjay.neutraldragontournament.web3;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.0.1.
 */
public class NeutralDragonTournament extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50600280546001600160a01b0319163317905560006001556107aa806100376000396000f3fe6080604052600436106100555760003560e01c80632b30aa1b1461005a5780634081d916146100825780634251cbb3146100c95780635566455814610212578063684b388214610244578063d888cb291461027c575b600080fd5b6100806004803603602081101561007057600080fd5b50356001600160a01b03166102ce565b005b34801561008e57600080fd5b506100b5600480360360208110156100a557600080fd5b50356001600160a01b031661030b565b604080519115158252519081900360200190f35b3480156100d557600080fd5b50610179600480360360208110156100ec57600080fd5b81019060208101813564010000000081111561010757600080fd5b82018360208201111561011957600080fd5b8035906020019184602083028401116401000000008311171561013b57600080fd5b91908080602002602001604051908101604052809392919081815260200183836020028082843760009201919091525092955061036d945050505050565b604051808060200180602001838103835285818151815260200191508051906020019060200280838360005b838110156101bd5781810151838201526020016101a5565b50505050905001838103825284818151815260200191508051906020019060200280838360005b838110156101fc5781810151838201526020016101e4565b5050505090500194505050505060405180910390f35b6100806004803603606081101561022857600080fd5b506001600160a01b038135169060208101359060400135610476565b6100806004803603606081101561025a57600080fd5b506001600160a01b0381358116916020810135821691604090910135166105a4565b34801561028857600080fd5b506102a66004803603602081101561029f57600080fd5b5035610711565b604080516001600160a01b039094168452602084019290925282820152519081900360600190f35b6040516001600160a01b0382169060009067016345785d8a00009082818181858883f19350505050158015610307573d6000803e3d6000fd5b5050565b6000805b60005481101561036257826001600160a01b03166000828154811061033057fe5b60009182526020909120600390910201546001600160a01b0316141561035a576001915050610368565b60010161030f565b50600090505b919050565b8051606090819081600282046040519080825280602002602001820160405280156103a2578160200160208202803883390190505b5090506060600283046040519080825280602002602001820160405280156103d4578160200160208202803883390190505b50905060005b6002840481101561046a578681815181106103f157fe5b602002602001015183828151811061040557fe5b60200260200101906001600160a01b031690816001600160a01b031681525050868160018603038151811061043657fe5b602002602001015182828151811061044a57fe5b6001600160a01b03909216602092830291909101909101526001016103da565b50909350915050915091565b61047e61074b565b5060408051606080820183526001600160a01b0386811683526020808401878152848601878152600080546001808201835591805287517f290decd9548b62a8d60345a988386fc84ba6bc95484008f6362f93160ef3e563600390920291820180546001600160a01b031916919097161790955591517f290decd9548b62a8d60345a988386fc84ba6bc95484008f6362f93160ef3e564850155517f290decd9548b62a8d60345a988386fc84ba6bc95484008f6362f93160ef3e565909301929092558154820191829055845190810191909152838152600681850152651a9bda5b995960d21b91810191909152915190917f0d1d11fa08a9e61ae3bddb37d8a8a01bff70b422c2a7d160e35e2a96086d6521916080918190039190910190a150505050565b60015460405167016345785d8a00008202916064670de0b6b3a76400009091028190049182840391603c830281900491601e840282900491600a850204906001600160a01b038a169084156108fc029085906000818181858888f19350505050158015610615573d6000803e3d6000fd5b506040516001600160a01b0389169083156108fc029084906000818181858888f1935050505015801561064c573d6000803e3d6000fd5b506040516001600160a01b0388169082156108fc029083906000818181858888f19350505050158015610683573d6000803e3d6000fd5b506002546040516001600160a01b039091169086156108fc029087906000818181858888f193505050501580156106be573d6000803e3d6000fd5b5060408051848152602081018490528082018390526060810187905290517f8c9573c46686b71cfbbf9e9788dc813e5b47bb4909142afb0d9bee720fee3bd09181900360800190a1505050505050505050565b6000818154811061071e57fe5b60009182526020909120600390910201805460018201546002909201546001600160a01b03909116925083565b604051806060016040528060006001600160a01b031681526020016000815260200160008152509056fea265627a7a7231582038a36de768c06e83f3166e50b9feb74f9761ecf660d563fc9c3821b4b05d09b464736f6c634300050b0032";

    public static final String FUNC_RESENDJOININGFEE = "resendJoiningFee";

    public static final String FUNC_CHECKPLAYEREXISTS = "checkPlayerExists";

    public static final String FUNC_ROUNDFIXTURE = "roundFixture";

    public static final String FUNC_JOINTOURNAMENT = "joinTournament";

    public static final String FUNC_DISTRIBUTEPRIZE = "distributePrize";

    public static final String FUNC_TPLAYERS = "tPlayers";

    public static final Event PLAYERJOININGEVENT_EVENT = new Event("playerJoiningEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event PRIZEAMOUNT_EVENT = new Event("prizeAmount", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected NeutralDragonTournament(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected NeutralDragonTournament(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected NeutralDragonTournament(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected NeutralDragonTournament(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<TransactionReceipt> resendJoiningFee(String playerAdress, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_RESENDJOININGFEE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(playerAdress)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<Boolean> checkPlayerExists(String playerAddress) {
        final Function function = new Function(FUNC_CHECKPLAYEREXISTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(playerAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<Tuple2<List<String>, List<String>>> roundFixture(List<String> wizardAddress) {
        final Function function = new Function(FUNC_ROUNDFIXTURE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.Utils.typeMap(wizardAddress, org.web3j.abi.datatypes.Address.class))), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}, new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteCall<Tuple2<List<String>, List<String>>>(
                new Callable<Tuple2<List<String>, List<String>>>() {
                    @Override
                    public Tuple2<List<String>, List<String>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<List<String>, List<String>>(
                                convertToNative((List<Address>) results.get(0).getValue()), 
                                convertToNative((List<Address>) results.get(1).getValue()));
                    }
                });
    }

    public RemoteCall<TransactionReceipt> joinTournament(String adr, BigInteger wizardId, BigInteger affinityType, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_JOINTOURNAMENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(adr), 
                new org.web3j.abi.datatypes.generated.Uint256(wizardId), 
                new org.web3j.abi.datatypes.generated.Uint256(affinityType)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> distributePrize(String wizard1Address, String wizard2Address, String wizard3Address, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_DISTRIBUTEPRIZE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(wizard1Address), 
                new org.web3j.abi.datatypes.Address(wizard2Address), 
                new org.web3j.abi.datatypes.Address(wizard3Address)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<Tuple3<String, BigInteger, BigInteger>> tPlayers(BigInteger param0) {
        final Function function = new Function(FUNC_TPLAYERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple3<String, BigInteger, BigInteger>>(
                new Callable<Tuple3<String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public List<PlayerJoiningEventEventResponse> getPlayerJoiningEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PLAYERJOININGEVENT_EVENT, transactionReceipt);
        ArrayList<PlayerJoiningEventEventResponse> responses = new ArrayList<PlayerJoiningEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PlayerJoiningEventEventResponse typedResponse = new PlayerJoiningEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.status = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PlayerJoiningEventEventResponse> playerJoiningEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, PlayerJoiningEventEventResponse>() {
            @Override
            public PlayerJoiningEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PLAYERJOININGEVENT_EVENT, log);
                PlayerJoiningEventEventResponse typedResponse = new PlayerJoiningEventEventResponse();
                typedResponse.log = log;
                typedResponse.status = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PlayerJoiningEventEventResponse> playerJoiningEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PLAYERJOININGEVENT_EVENT));
        return playerJoiningEventEventFlowable(filter);
    }

    public List<PrizeAmountEventResponse> getPrizeAmountEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PRIZEAMOUNT_EVENT, transactionReceipt);
        ArrayList<PrizeAmountEventResponse> responses = new ArrayList<PrizeAmountEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PrizeAmountEventResponse typedResponse = new PrizeAmountEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.prize1 = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.prize2 = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.tableTopScorer = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.developerCommission = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PrizeAmountEventResponse> prizeAmountEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, PrizeAmountEventResponse>() {
            @Override
            public PrizeAmountEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PRIZEAMOUNT_EVENT, log);
                PrizeAmountEventResponse typedResponse = new PrizeAmountEventResponse();
                typedResponse.log = log;
                typedResponse.prize1 = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.prize2 = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.tableTopScorer = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.developerCommission = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PrizeAmountEventResponse> prizeAmountEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PRIZEAMOUNT_EVENT));
        return prizeAmountEventFlowable(filter);
    }

    @Deprecated
    public static NeutralDragonTournament load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new NeutralDragonTournament(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static NeutralDragonTournament load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new NeutralDragonTournament(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static NeutralDragonTournament load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new NeutralDragonTournament(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static NeutralDragonTournament load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new NeutralDragonTournament(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<NeutralDragonTournament> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(NeutralDragonTournament.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<NeutralDragonTournament> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(NeutralDragonTournament.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<NeutralDragonTournament> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(NeutralDragonTournament.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<NeutralDragonTournament> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(NeutralDragonTournament.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class PlayerJoiningEventEventResponse {
        public Log log;

        public String status;

        public BigInteger count;
    }

    public static class PrizeAmountEventResponse {
        public Log log;

        public BigInteger prize1;

        public BigInteger prize2;

        public BigInteger tableTopScorer;

        public BigInteger developerCommission;
    }
}
