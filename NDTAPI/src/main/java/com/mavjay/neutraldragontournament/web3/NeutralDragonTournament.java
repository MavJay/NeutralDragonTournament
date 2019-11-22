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
    private static final String BINARY = "608060405234801561001057600080fd5b50600280546001600160a01b031916331790556000600155610886806100376000396000f3fe6080604052600436106100555760003560e01c80632b30aa1b1461005a5780634081d916146100825780634251cbb3146100c95780635566455814610212578063684b3882146102445780639b54f5a51461027c575b600080fd5b6100806004803603602081101561007057600080fd5b50356001600160a01b03166102a3565b005b34801561008e57600080fd5b506100b5600480360360208110156100a557600080fd5b50356001600160a01b03166102f9565b604080519115158252519081900360200190f35b3480156100d557600080fd5b50610179600480360360208110156100ec57600080fd5b81019060208101813564010000000081111561010757600080fd5b82018360208201111561011957600080fd5b8035906020019184602083028401116401000000008311171561013b57600080fd5b91908080602002602001604051908101604052809392919081815260200183836020028082843760009201919091525092955061035b945050505050565b604051808060200180602001838103835285818151815260200191508051906020019060200280838360005b838110156101bd5781810151838201526020016101a5565b50505050905001838103825284818151815260200191508051906020019060200280838360005b838110156101fc5781810151838201526020016101e4565b5050505090500194505050505060405180910390f35b6100806004803603606081101561022857600080fd5b506001600160a01b038135169060208101359060400135610471565b6100806004803603606081101561025a57600080fd5b506001600160a01b03813581169160208101358216916040909101351661059f565b34801561028857600080fd5b50610291610821565b60408051918252519081900360200190f35b6001600160a01b03811633146102b857600080fd5b6002546040516001600160a01b039091169060009067016345785d8a00009082818181858883f193505050501580156102f5573d6000803e3d6000fd5b5050565b6000805b60005481101561035057826001600160a01b03166000828154811061031e57fe5b60009182526020909120600390910201546001600160a01b03161415610348576001915050610356565b6001016102fd565b50600090505b919050565b606080600083511161036c57600080fd5b825160606002820460405190808252806020026020018201604052801561039d578160200160208202803883390190505b5090506060600283046040519080825280602002602001820160405280156103cf578160200160208202803883390190505b50905060005b60028404811015610465578681815181106103ec57fe5b602002602001015183828151811061040057fe5b60200260200101906001600160a01b031690816001600160a01b031681525050868160018603038151811061043157fe5b602002602001015182828151811061044557fe5b6001600160a01b03909216602092830291909101909101526001016103d5565b50909350915050915091565b610479610827565b5060408051606080820183526001600160a01b0386811683526020808401878152848601878152600080546001808201835591805287517f290decd9548b62a8d60345a988386fc84ba6bc95484008f6362f93160ef3e563600390920291820180546001600160a01b031916919097161790955591517f290decd9548b62a8d60345a988386fc84ba6bc95484008f6362f93160ef3e564850155517f290decd9548b62a8d60345a988386fc84ba6bc95484008f6362f93160ef3e565909301929092558154820191829055845190810191909152838152600681850152651a9bda5b995960d21b91810191909152915190917f0d1d11fa08a9e61ae3bddb37d8a8a01bff70b422c2a7d160e35e2a96086d6521916080918190039190910190a150505050565b60015460408051600480825260a0820190925267016345785d8a00008302926064670de0b6b3a76400009091028190049283850392603c840283900492601e850281900492600a8602919091049160609160208201608080388339019050509050898160008151811061060e57fe5b60200260200101906001600160a01b031690816001600160a01b031681525050888160018151811061063c57fe5b60200260200101906001600160a01b031690816001600160a01b031681525050878160028151811061066a57fe5b6001600160a01b03928316602091820292909201015260025482519116908290600390811061069557fe5b6001600160a01b039092166020928302919091019091015260408051600480825260a0820190925260609181602001602082028038833901905050905084816000815181106106e057fe5b60200260200101818152505083816001815181106106fa57fe5b602002602001018181525050828160028151811061071457fe5b602002602001018181525050868160038151811061072e57fe5b602090810291909101015260005b600381116107ac5782818151811061075057fe5b60200260200101516001600160a01b03166108fc83838151811061077057fe5b60200260200101519081150290604051600060405180830381858888f193505050501580156107a3573d6000803e3d6000fd5b5060010161073c565b50604080516001600160a01b03808e168252808d1660208301528b1681830152606081018790526080810186905260a0810185905260c0810189905290517f822495507e334cd85b661fa252dcbac4d479b8b4a080585705e112c4d75e0fd99181900360e00190a15050505050505050505050565b60015481565b604051806060016040528060006001600160a01b031681526020016000815260200160008152509056fea265627a7a723158200d2d0d0654664ccad47f59eb442e52af856996f89b163569e84fb3fbb34a5ec664736f6c634300050b0032";

    public static final String FUNC_RESENDJOININGFEE = "resendJoiningFee";

    public static final String FUNC_CHECKPLAYEREXISTS = "checkPlayerExists";

    public static final String FUNC_ROUNDFIXTURE = "roundFixture";

    public static final String FUNC_JOINTOURNAMENT = "joinTournament";

    public static final String FUNC_DISTRIBUTEPRIZE = "distributePrize";

    public static final String FUNC_TOTALNOFPARTICIPANTS = "totalNOfParticipants";

    public static final Event PLAYERJOININGEVENT_EVENT = new Event("playerJoiningEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event PRIZEAMOUNT_EVENT = new Event("prizeAmount", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
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

    public RemoteCall<BigInteger> totalNOfParticipants() {
        final Function function = new Function(FUNC_TOTALNOFPARTICIPANTS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
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
            typedResponse.wizard1Address = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.wizard2Address = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.wizard3Address = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.prize1 = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.prize2 = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.tableTopScorer = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.developerCommission = (BigInteger) eventValues.getNonIndexedValues().get(6).getValue();
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
                typedResponse.wizard1Address = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.wizard2Address = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.wizard3Address = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse.prize1 = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse.prize2 = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse.tableTopScorer = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
                typedResponse.developerCommission = (BigInteger) eventValues.getNonIndexedValues().get(6).getValue();
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

        public String wizard1Address;

        public String wizard2Address;

        public String wizard3Address;

        public BigInteger prize1;

        public BigInteger prize2;

        public BigInteger tableTopScorer;

        public BigInteger developerCommission;
    }
}
