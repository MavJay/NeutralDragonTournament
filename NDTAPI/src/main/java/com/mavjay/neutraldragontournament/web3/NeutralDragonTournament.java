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
    private static final String BINARY = "6080604052600860035560048055600060075534801561001e57600080fd5b50600280546001600160a01b03191633179055600060015543600655610e61806100496000396000f3fe6080604052600436106100a75760003560e01c806374f3e6b91161006457806374f3e6b91461033e5780638753627e1461037a578063a6287999146103a4578063b19ab362146103dd578063d888cb29146105ef578063ea1b8fe514610641576100a7565b80632b30aa1b146100ac5780634081d916146100d45780634251cbb31461011b5780634790c8a21461026457806355664558146102d4578063684b388214610306575b600080fd5b6100d2600480360360208110156100c257600080fd5b50356001600160a01b0316610656565b005b3480156100e057600080fd5b50610107600480360360208110156100f757600080fd5b50356001600160a01b0316610693565b604080519115158252519081900360200190f35b34801561012757600080fd5b506101cb6004803603602081101561013e57600080fd5b81019060208101813564010000000081111561015957600080fd5b82018360208201111561016b57600080fd5b8035906020019184602083028401116401000000008311171561018d57600080fd5b9190808060200260200160405190810160405280939291908181526020018383602002808284376000920191909152509295506106f5945050505050565b604051808060200180602001838103835285818151815260200191508051906020019060200280838360005b8381101561020f5781810151838201526020016101f7565b50505050905001838103825284818151815260200191508051906020019060200280838360005b8381101561024e578181015183820152602001610236565b5050505090500194505050505060405180910390f35b34801561027057600080fd5b506100d2600480360361014081101561028857600080fd5b506001600160a01b03813581169160208101359091169060408101359060608101359060808101359060a08101359060c08101359060e0810135906101008101359061012001356107fe565b6100d2600480360360608110156102ea57600080fd5b506001600160a01b0381351690602081013590604001356108ab565b6100d26004803603606081101561031c57600080fd5b506001600160a01b0381358116916020810135821691604090910135166109ce565b34801561034a57600080fd5b506103686004803603602081101561036157600080fd5b5035610b49565b60408051918252519081900360200190f35b34801561038657600080fd5b506103686004803603602081101561039d57600080fd5b5035610b50565b3480156103b057600080fd5b506100d2600480360360408110156103c757600080fd5b506001600160a01b038135169060200135610b57565b3480156103e957600080fd5b506105116004803603604081101561040057600080fd5b81019060208101813564010000000081111561041b57600080fd5b82018360208201111561042d57600080fd5b8035906020019184602083028401116401000000008311171561044f57600080fd5b919080806020026020016040519081016040528093929190818152602001838360200280828437600092019190915250929594936020810193503591505064010000000081111561049f57600080fd5b8201836020820111156104b157600080fd5b803590602001918460208302840111640100000000831117156104d357600080fd5b919080806020026020016040519081016040528093929190818152602001838360200280828437600092019190915250929550610b9f945050505050565b60405180806020018060200180602001848103845287818151815260200191508051906020019060200280838360005b83811015610559578181015183820152602001610541565b50505050905001848103835286818151815260200191508051906020019060200280838360005b83811015610598578181015183820152602001610580565b50505050905001848103825285818151815260200191508051906020019060200280838360005b838110156105d75781810151838201526020016105bf565b50505050905001965050505050505060405180910390f35b3480156105fb57600080fd5b506106196004803603602081101561061257600080fd5b5035610dbe565b604080516001600160a01b039094168452602084019290925282820152519081900360600190f35b34801561064d57600080fd5b50610368610df8565b6040516001600160a01b0382169060009067016345785d8a00009082818181858883f1935050505015801561068f573d6000803e3d6000fd5b5050565b6000805b6000548110156106ea57826001600160a01b0316600082815481106106b857fe5b60009182526020909120600390910201546001600160a01b031614156106e25760019150506106f0565b600101610697565b50600090505b919050565b80516060908190816002820460405190808252806020026020018201604052801561072a578160200160208202803883390190505b50905060606002830460405190808252806020026020018201604052801561075c578160200160208202803883390190505b50905060005b600284048110156107f25786818151811061077957fe5b602002602001015183828151811061078d57fe5b60200260200101906001600160a01b031690816001600160a01b03168152505086816001860303815181106107be57fe5b60200260200101518282815181106107d257fe5b6001600160a01b0390921660209283029190910190910152600101610762565b50909350915050915091565b60008789111561080f575089610856565b8789101561081e575088610856565b8587111561082d575089610856565b8587101561083c575088610856565b8385111561084b575089610856565b838510156108565750885b60408051848152602081018490526001600160a01b0383168183015290517fb02794636940974c1274e91c8ddfd72579068dbcd420fc2983d437970253b0ea9181900360600190a15050505050505050505050565b6108b3610e02565b5060408051606080820183526001600160a01b0386811683526020808401878152848601878152600080546001808201835591805287517f290decd9548b62a8d60345a988386fc84ba6bc95484008f6362f93160ef3e563600390920291820180546001600160a01b031916919097161790955591517f290decd9548b62a8d60345a988386fc84ba6bc95484008f6362f93160ef3e564850155517f290decd9548b62a8d60345a988386fc84ba6bc95484008f6362f93160ef3e56590930192909255815482019091558351818152600691810191909152651a9bda5b995960d21b81850152925191927fc2efea8d8093d2ae31a75dc9cd24b1a73f978cc0e13e436abc615eebff00ad38929081900390910190a150505050565b60015460405167016345785d8a00008202916064677ce66c50e2840000820281900492670de0b6b3a764000090920281900491603c840282900491601e850281900491600a860291909104906001600160a01b038a169084156108fc029085906000818181858888f19350505050158015610a4d573d6000803e3d6000fd5b506040516001600160a01b0389169083156108fc029084906000818181858888f19350505050158015610a84573d6000803e3d6000fd5b506040516001600160a01b0388169082156108fc029083906000818181858888f19350505050158015610abb573d6000803e3d6000fd5b506002546040516001600160a01b039091169085156108fc029086906000818181858888f19350505050158015610af6573d6000803e3d6000fd5b5060408051848152602081018490528082018390526060810186905290517f8c9573c46686b71cfbbf9e9788dc813e5b47bb4909142afb0d9bee720fee3bd09181900360800190a1505050505050505050565b6003540190565b6004540190565b604080516001600160a01b03841681526020810183905281517f91464f88cd1040ed9d2a8e2ee811c119383684b08aaa5cc972480d746b1cfff2929181900390910190a15050565b60408051600580825260c08201909252606091829182918291906020820160a080388339505060408051600580825260c0820190925292935060609291506020820160a0803883395050604080516002808252606080830184529495509092509060208301908038833901905050905060005b6005811015610db157878181518110610c2757fe5b6020026020010151898281518110610c3b57fe5b60200260200101510360011480610c7b5750600119888281518110610c5c57fe5b60200260200101518a8381518110610c7057fe5b602002602001015103145b15610cb9576001848281518110610c8e57fe5b6020026020010181815250506000838281518110610ca857fe5b602002602001018181525050610da9565b600019888281518110610cc857fe5b60200260200101518a8381518110610cdc57fe5b6020026020010151031480610d195750878181518110610cf857fe5b6020026020010151898281518110610d0c57fe5b6020026020010151036002145b15610d46576001838281518110610d2c57fe5b6020026020010181815250506000848281518110610ca857fe5b81600081518110610d5357fe5b602002602001015160010182600081518110610d6b57fe5b60200260200101818152505081600181518110610d8457fe5b602002602001015160010182600181518110610d9c57fe5b6020026020010181815250505b600101610c12565b5097919650945092505050565b60008181548110610dcb57fe5b60009182526020909120600390910201805460018201546002909201546001600160a01b03909116925083565b6003546006540190565b604051806060016040528060006001600160a01b031681526020016000815260200160008152509056fea265627a7a72315820288bb92acc166c4e8428ea8c1a69350c14754179074981aae52af430a4aecd5064736f6c634300050b0032";

    public static final String FUNC_RESENDJOININGFEE = "resendJoiningFee";

    public static final String FUNC_CHECKPLAYEREXISTS = "checkPlayerExists";

    public static final String FUNC_ROUNDFIXTURE = "roundFixture";

    public static final String FUNC_GETWINNER = "getWinner";

    public static final String FUNC_JOINTOURNAMENT = "joinTournament";

    public static final String FUNC_DISTRIBUTEPRIZE = "distributePrize";

    public static final String FUNC_TOURNAMENTENDTIMER = "tournamentEndTimer";

    public static final String FUNC_LEVELTIMER = "levelTimer";

    public static final String FUNC_WIZARDELIMINATED = "wizardEliminated";

    public static final String FUNC_STARTDUEL = "startDuel";

    public static final String FUNC_TPLAYERS = "tPlayers";

    public static final String FUNC_TOURNAMENTSTARTTIMER = "tournamentStartTimer";

    public static final Event PLAYER_COUNT_EVENT_EVENT = new Event("player_count_event", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event ELIMINATEDWIZARD_EVENT = new Event("eliminatedWizard", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event PLAYERJOININGEVENT_EVENT = new Event("playerJoiningEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
    ;

    public static final Event LEVELNUMBER_EVENT = new Event("levelNumber", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event LEVELWINNER_EVENT = new Event("levelWinner", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event PRIZEAMOUNT_EVENT = new Event("prizeAmount", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event ADMISSIONTIMEDURATION_EVENT = new Event("admissionTimeDuration", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
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

    public RemoteCall<TransactionReceipt> getWinner(String wizard1, String wizard2, BigInteger wizard1RoundWin, BigInteger wizard2RoundWin, BigInteger wizard1LevelScore, BigInteger wizard2LevelScore, BigInteger wizard1TimeStamp, BigInteger wizard2TimeStamp, BigInteger currentLevelCount, BigInteger currentMatchCount) {
        final Function function = new Function(
                FUNC_GETWINNER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(wizard1), 
                new org.web3j.abi.datatypes.Address(wizard2), 
                new org.web3j.abi.datatypes.generated.Uint256(wizard1RoundWin), 
                new org.web3j.abi.datatypes.generated.Uint256(wizard2RoundWin), 
                new org.web3j.abi.datatypes.generated.Uint256(wizard1LevelScore), 
                new org.web3j.abi.datatypes.generated.Uint256(wizard2LevelScore), 
                new org.web3j.abi.datatypes.generated.Uint256(wizard1TimeStamp), 
                new org.web3j.abi.datatypes.generated.Uint256(wizard2TimeStamp), 
                new org.web3j.abi.datatypes.generated.Uint256(currentLevelCount), 
                new org.web3j.abi.datatypes.generated.Uint256(currentMatchCount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public RemoteCall<BigInteger> tournamentEndTimer(BigInteger startDuration) {
        final Function function = new Function(FUNC_TOURNAMENTENDTIMER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(startDuration)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> levelTimer(BigInteger levelStartDuration) {
        final Function function = new Function(FUNC_LEVELTIMER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(levelStartDuration)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> wizardEliminated(String adr, BigInteger wizardId) {
        final Function function = new Function(
                FUNC_WIZARDELIMINATED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(adr), 
                new org.web3j.abi.datatypes.generated.Uint256(wizardId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple3<List<BigInteger>, List<BigInteger>, List<BigInteger>>> startDuel(List<BigInteger> wizard1MoveSpells, List<BigInteger> wizard2MoveSpells) {
        final Function function = new Function(FUNC_STARTDUEL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.Utils.typeMap(wizard1MoveSpells, org.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.Utils.typeMap(wizard2MoveSpells, org.web3j.abi.datatypes.generated.Uint256.class))), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}, new TypeReference<DynamicArray<Uint256>>() {}));
        return new RemoteCall<Tuple3<List<BigInteger>, List<BigInteger>, List<BigInteger>>>(
                new Callable<Tuple3<List<BigInteger>, List<BigInteger>, List<BigInteger>>>() {
                    @Override
                    public Tuple3<List<BigInteger>, List<BigInteger>, List<BigInteger>> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<List<BigInteger>, List<BigInteger>, List<BigInteger>>(
                                convertToNative((List<Uint256>) results.get(0).getValue()), 
                                convertToNative((List<Uint256>) results.get(1).getValue()), 
                                convertToNative((List<Uint256>) results.get(2).getValue()));
                    }
                });
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

    public RemoteCall<BigInteger> tournamentStartTimer() {
        final Function function = new Function(FUNC_TOURNAMENTSTARTTIMER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public List<Player_count_eventEventResponse> getPlayer_count_eventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PLAYER_COUNT_EVENT_EVENT, transactionReceipt);
        ArrayList<Player_count_eventEventResponse> responses = new ArrayList<Player_count_eventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            Player_count_eventEventResponse typedResponse = new Player_count_eventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<Player_count_eventEventResponse> player_count_eventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, Player_count_eventEventResponse>() {
            @Override
            public Player_count_eventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PLAYER_COUNT_EVENT_EVENT, log);
                Player_count_eventEventResponse typedResponse = new Player_count_eventEventResponse();
                typedResponse.log = log;
                typedResponse.count = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<Player_count_eventEventResponse> player_count_eventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PLAYER_COUNT_EVENT_EVENT));
        return player_count_eventEventFlowable(filter);
    }

    public List<EliminatedWizardEventResponse> getEliminatedWizardEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ELIMINATEDWIZARD_EVENT, transactionReceipt);
        ArrayList<EliminatedWizardEventResponse> responses = new ArrayList<EliminatedWizardEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            EliminatedWizardEventResponse typedResponse = new EliminatedWizardEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.adr = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.wizardId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<EliminatedWizardEventResponse> eliminatedWizardEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, EliminatedWizardEventResponse>() {
            @Override
            public EliminatedWizardEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ELIMINATEDWIZARD_EVENT, log);
                EliminatedWizardEventResponse typedResponse = new EliminatedWizardEventResponse();
                typedResponse.log = log;
                typedResponse.adr = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.wizardId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<EliminatedWizardEventResponse> eliminatedWizardEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ELIMINATEDWIZARD_EVENT));
        return eliminatedWizardEventFlowable(filter);
    }

    public List<PlayerJoiningEventEventResponse> getPlayerJoiningEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PLAYERJOININGEVENT_EVENT, transactionReceipt);
        ArrayList<PlayerJoiningEventEventResponse> responses = new ArrayList<PlayerJoiningEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PlayerJoiningEventEventResponse typedResponse = new PlayerJoiningEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.status = (String) eventValues.getNonIndexedValues().get(0).getValue();
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
                return typedResponse;
            }
        });
    }

    public Flowable<PlayerJoiningEventEventResponse> playerJoiningEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PLAYERJOININGEVENT_EVENT));
        return playerJoiningEventEventFlowable(filter);
    }

    public List<LevelNumberEventResponse> getLevelNumberEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LEVELNUMBER_EVENT, transactionReceipt);
        ArrayList<LevelNumberEventResponse> responses = new ArrayList<LevelNumberEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LevelNumberEventResponse typedResponse = new LevelNumberEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.levelCount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LevelNumberEventResponse> levelNumberEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, LevelNumberEventResponse>() {
            @Override
            public LevelNumberEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LEVELNUMBER_EVENT, log);
                LevelNumberEventResponse typedResponse = new LevelNumberEventResponse();
                typedResponse.log = log;
                typedResponse.levelCount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<LevelNumberEventResponse> levelNumberEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LEVELNUMBER_EVENT));
        return levelNumberEventFlowable(filter);
    }

    public List<LevelWinnerEventResponse> getLevelWinnerEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LEVELWINNER_EVENT, transactionReceipt);
        ArrayList<LevelWinnerEventResponse> responses = new ArrayList<LevelWinnerEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LevelWinnerEventResponse typedResponse = new LevelWinnerEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.currentLevelCount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.currentMatchCount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.winner = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LevelWinnerEventResponse> levelWinnerEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, LevelWinnerEventResponse>() {
            @Override
            public LevelWinnerEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LEVELWINNER_EVENT, log);
                LevelWinnerEventResponse typedResponse = new LevelWinnerEventResponse();
                typedResponse.log = log;
                typedResponse.currentLevelCount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.currentMatchCount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.winner = (String) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<LevelWinnerEventResponse> levelWinnerEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LEVELWINNER_EVENT));
        return levelWinnerEventFlowable(filter);
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

    public List<AdmissionTimeDurationEventResponse> getAdmissionTimeDurationEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ADMISSIONTIMEDURATION_EVENT, transactionReceipt);
        ArrayList<AdmissionTimeDurationEventResponse> responses = new ArrayList<AdmissionTimeDurationEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AdmissionTimeDurationEventResponse typedResponse = new AdmissionTimeDurationEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.duration = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AdmissionTimeDurationEventResponse> admissionTimeDurationEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, AdmissionTimeDurationEventResponse>() {
            @Override
            public AdmissionTimeDurationEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ADMISSIONTIMEDURATION_EVENT, log);
                AdmissionTimeDurationEventResponse typedResponse = new AdmissionTimeDurationEventResponse();
                typedResponse.log = log;
                typedResponse.duration = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AdmissionTimeDurationEventResponse> admissionTimeDurationEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ADMISSIONTIMEDURATION_EVENT));
        return admissionTimeDurationEventFlowable(filter);
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

    public static class Player_count_eventEventResponse {
        public Log log;

        public BigInteger count;
    }

    public static class EliminatedWizardEventResponse {
        public Log log;

        public String adr;

        public BigInteger wizardId;
    }

    public static class PlayerJoiningEventEventResponse {
        public Log log;

        public String status;
    }

    public static class LevelNumberEventResponse {
        public Log log;

        public BigInteger levelCount;
    }

    public static class LevelWinnerEventResponse {
        public Log log;

        public BigInteger currentLevelCount;

        public BigInteger currentMatchCount;

        public String winner;
    }

    public static class PrizeAmountEventResponse {
        public Log log;

        public BigInteger prize1;

        public BigInteger prize2;

        public BigInteger tableTopScorer;

        public BigInteger developerCommission;
    }

    public static class AdmissionTimeDurationEventResponse {
        public Log log;

        public BigInteger duration;
    }
}
