App = {
  web3Provider: null,
  contracts: {},
  account: '0x0',
  maininstance:{},
  joiningStatus:null,
  playerCount:0,
  tournamentEnrollTimer:0,
  tournamentEnrollEndTimer:0,
  levelStartDuration:0,
  levelEndDuration:0,
  init: async function() {
    //return await App.initWeb3();
    window.addEventListener('load', async () => {
      // Modern dapp browsers...
      if (window.ethereum) {
        window.web3 = new Web3(ethereum);
        try {
          // Request account access if needed
          await ethereum.enable();
          // Acccounts now exposed
          return await App.initWeb3();
        } catch (error) {
          // User denied account access...
        }
      }
      // Legacy dapp browsers...
      else if (window.web3) {
        window.web3 = new Web3(web3.currentProvider);
        // Acccounts always exposed
        return await App.initWeb3();
      }
      // Non-dapp browsers...
      else {
        console.log('Non-Ethereum browser detected. You should consider trying MetaMask!');
      }
    });
  },
  initWeb3: async function() {
    if (typeof web3 !== 'undefined') {
      // If a web3 instance is already provided by Meta Mask.
      // If a web3 instance is already provided by Meta Mask.
        if (window.web3.currentProvider.constructor.name == "MetamaskInpageProvider"){
          App.web3Provider = web3.currentProvider;
          web3 = new Web3(web3.currentProvider);
        }else{
          App.web3Provider = web3.currentProvider;
          web3 = new Web3(web3.currentProvider);
        }
      } else {
        // Specify default instance if no web3 instance provided
        App.web3Provider = new Web3.providers.HttpProvider('http://localhost:8545');
        web3 = new Web3(App.web3Provider);
      }


    if (web3) {
      if (web3.eth.accounts.length) {
        // if not locked, get account
        const account = web3.eth.accounts[0];
        // updates UI, state, pull data
      } else {
        // locked. update UI. Ask user to unlock.
      }
    }


    //Detecting network of metamask connenction
    web3.version.getNetwork((err, netId) => {
      switch (netId) {
        case "1":
        console.log('This is mainnet')
        break
        case "2":
        console.log('This is the deprecated Morden test network.')
        $(".metamask-info").text("Please switch to Mainnet");
        break
        case "3":
        console.log('This is the ropsten test network.')
        $(".metamask-info").text("Please switch to Mainnet");
        break
        default:
        console.log('This is local network or unknown network')
        $(".metamask-info").text("Please switch to Mainnet");
      }

    })
    return App.initContract();
  },



 snackbarCall:function(text){
    Snackbar.show({text: text,pos: 'bottom-center',actionText: 'OK',actionTextColor: "var(--text-c1)"});
  },


  initContract: function() {
var NDTContract = web3.eth.contract([{"constant":false,"inputs":[{"internalType":"address payable","name":"playerAdress","type":"address"}],"name":"resendJoiningFee","outputs":[],"payable":true,"stateMutability":"payable","type":"function"},{"constant":true,"inputs":[{"internalType":"address","name":"playerAddress","type":"address"}],"name":"checkPlayerExists","outputs":[{"internalType":"bool","name":"","type":"bool"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"internalType":"address[]","name":"wizardAddress","type":"address[]"}],"name":"roundFixture","outputs":[{"internalType":"address[]","name":"","type":"address[]"},{"internalType":"address[]","name":"","type":"address[]"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"internalType":"address","name":"wizard1","type":"address"},{"internalType":"address","name":"wizard2","type":"address"},{"internalType":"uint256","name":"wizard1RoundWin","type":"uint256"},{"internalType":"uint256","name":"wizard2RoundWin","type":"uint256"},{"internalType":"uint256","name":"wizard1LevelScore","type":"uint256"},{"internalType":"uint256","name":"wizard2LevelScore","type":"uint256"},{"internalType":"uint256","name":"wizard1TimeStamp","type":"uint256"},{"internalType":"uint256","name":"wizard2TimeStamp","type":"uint256"},{"internalType":"uint256","name":"currentLevelCount","type":"uint256"},{"internalType":"uint256","name":"currentMatchCount","type":"uint256"}],"name":"getWinner","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":false,"inputs":[{"internalType":"address payable","name":"adr","type":"address"},{"internalType":"uint256","name":"wizardId","type":"uint256"},{"internalType":"uint256","name":"affinityType","type":"uint256"}],"name":"joinTournament","outputs":[],"payable":true,"stateMutability":"payable","type":"function"},{"constant":false,"inputs":[{"internalType":"address payable","name":"wizard1Address","type":"address"},{"internalType":"address payable","name":"wizard2Address","type":"address"},{"internalType":"address payable","name":"wizard3Address","type":"address"}],"name":"distributePrize","outputs":[],"payable":true,"stateMutability":"payable","type":"function"},{"constant":true,"inputs":[{"internalType":"uint256","name":"startDuration","type":"uint256"}],"name":"tournamentEndTimer","outputs":[{"internalType":"uint256","name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"internalType":"uint256","name":"levelStartDuration","type":"uint256"}],"name":"levelTimer","outputs":[{"internalType":"uint256","name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"internalType":"address","name":"adr","type":"address"},{"internalType":"uint256","name":"wizardId","type":"uint256"}],"name":"wizardEliminated","outputs":[],"payable":false,"stateMutability":"nonpayable","type":"function"},{"constant":true,"inputs":[{"internalType":"uint256[]","name":"wizard1MoveSpells","type":"uint256[]"},{"internalType":"uint256[]","name":"wizard2MoveSpells","type":"uint256[]"}],"name":"startDuel","outputs":[{"internalType":"uint256[]","name":"","type":"uint256[]"},{"internalType":"uint256[]","name":"","type":"uint256[]"},{"internalType":"uint256[]","name":"","type":"uint256[]"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"internalType":"uint256","name":"","type":"uint256"}],"name":"tPlayers","outputs":[{"internalType":"address payable","name":"player","type":"address"},{"internalType":"uint256","name":"wizardId","type":"uint256"},{"internalType":"uint256","name":"affinityType","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[],"name":"tournamentStartTimer","outputs":[{"internalType":"uint256","name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"inputs":[],"payable":false,"stateMutability":"nonpayable","type":"constructor"},{"anonymous":false,"inputs":[{"indexed":false,"internalType":"uint256","name":"count","type":"uint256"}],"name":"player_count_event","type":"event"},{"anonymous":false,"inputs":[{"indexed":false,"internalType":"address","name":"adr","type":"address"},{"indexed":false,"internalType":"uint256","name":"wizardId","type":"uint256"}],"name":"eliminatedWizard","type":"event"},{"anonymous":false,"inputs":[{"indexed":false,"internalType":"string","name":"status","type":"string"}],"name":"playerJoiningEvent","type":"event"},{"anonymous":false,"inputs":[{"indexed":false,"internalType":"uint256","name":"levelCount","type":"uint256"}],"name":"levelNumber","type":"event"},{"anonymous":false,"inputs":[{"indexed":false,"internalType":"uint256","name":"currentLevelCount","type":"uint256"},{"indexed":false,"internalType":"uint256","name":"currentMatchCount","type":"uint256"},{"indexed":false,"internalType":"address","name":"winner","type":"address"}],"name":"levelWinner","type":"event"},{"anonymous":false,"inputs":[{"indexed":false,"internalType":"uint256","name":"prize1","type":"uint256"},{"indexed":false,"internalType":"uint256","name":"prize2","type":"uint256"},{"indexed":false,"internalType":"uint256","name":"tableTopScorer","type":"uint256"},{"indexed":false,"internalType":"uint256","name":"developerCommission","type":"uint256"}],"name":"prizeAmount","type":"event"},{"anonymous":false,"inputs":[{"indexed":false,"internalType":"uint256","name":"duration","type":"uint256"}],"name":"admissionTimeDuration","type":"event"}]);

App.maininstance = NDTContract.at('0x8ba551495a32a88cdf47d736d86b03adcdb7eb89');
console.log(App.maininstance);
      return App.render();
  },
  render: function(){
    web3.eth.getCoinbase(function(error,account){
      if(error === null){
        if(account !== null){
          App.account = account;
          web3.eth.getBalance(account, function (error, wei) {
            if (!error) {
              //web3.fromWei(eth.getBalance(eth.coinbase));
              var balance = web3.fromWei(wei, 'ether');
              App.eth_balance = balance;
              console.log(balance + " ETH");
            }
          });
            //Check Player Valid
            // var playerJoinStatus = maininstance.playerJoiningEvent({}, {fromBlock:'latest', toBlock: 'latest'});
            //     if (playerJoinStatus != undefined){
            //   playerJoinStatus.watch(function(error, result){
            //     App.joiningStatus = result.args.status.valueOf();
            //     App.playerCount = result.args.count.valueOf();
            // })
            // }

//Get Player Notification Details
                    $.ajax({
          type: "POST",
                  url: "http://localhost:8080/neutraldragontournament/rest/getPlayerDetails",
                  crossDomain: true,
                  data: 'player='+App.account+'',
                  header:{
                  },
                  success: function (data) {
                // Need to check response type
                },
                                    error: function (err) {
                                      console.log(err.responseText);
                                      var response = err.responseText;
                                      if(response == 'placeSpell'){
                                        App.snackbarCall("Please place your spells");
                                      } else if(response == 'eliminated'){
                                        App.snackbarCall("You have been eliminated from the tournament");
                                      } else if(response == 'bye'){
                                        App.snackbarCall("You have been bye passed to next level");
                                      } else {

                                      }
                                    }
                                    });
//Get Match array
$.ajax({
type: "POST",
        url: "http://localhost:8080/neutraldragontournament/rest/getMatchArr",
        crossDomain: true,
        data: {},
        header:{
        },
        success: function (data) {
      // Need to check response type
      console.log("Match Fixtures:::::::"+data);
      },
                          error: function (err) {
                          }
                          });


        }else{
          $('#account-error').show()
          $('#account-error').text("Please connect your wallet!");
        }
      }

    });
  },
  snackbarCall:function(text){
    Snackbar.show({text: text,pos: 'bottom-center',actionText: 'OK',actionTextColor: "var(--text-c1)"});
  },
  joinTournament:function(playerAddress,wizardId,joiningFee,affinityType){
    debugger
    App.maininstance.checkPlayerExists(App.account, function(err, res){
      console.log(res);
      if(!res){
          App.snackbarCall("Please confirm your transaction");
        App.maininstance.joinTournament(playerAddress,wizardId,affinityType,{from: web3.eth.accounts[0], gas: 3000000, value: web3.toWei(joiningFee,'ether')}, function(err, res){
          if(!err){
               App.snackbarCall("You have joined the tournament");
               if(App.joiningStatus == 'joined'){
             $.ajax({
             type: "POST",
                     url: "http://localhost:8080/neutraldragontournament/rest/jointournament",
                     crossDomain: true,
                     data: 'player='+playerAddress+'&wizardid='+wizardId+'&affinitytype='+affinityType+'',
                     header:{
                     },
                     success: function (data) {

                     },
                     error: function (err) {
                       console.log(err);
                     }
                     });
                     }
           }else{
             console.error(err);
           }
        });
      } else {
        App.snackbarCall("You have joined the tournament");
      }
    });
  },

  placeSpells:function(spellArray){
    debugger
    $.ajax({
    type: "POST",
            url: "http://localhost:8080/neutraldragontournament/rest/updateplacespell",
            crossDomain: true,
            data: 'player='+App.account+'&wizardspell1='+spellArray[0]+'&wizardspell2='+spellArray[1]+'&wizardspell3='+spellArray[2]+'&wizardspell4='+spellArray[3]+'&wizardspell5='+spellArray[4]+'',
            header:{
            },
            success: function (data) {
            // Need to check the reponse type
            },
            error: function (err) {
              if(err.responseText == 'notExists'){
                App.snackbarCall("Player is not in the tournament");
              } else if(err.responseText == 'success'){
                  App.snackbarCall("You have successfully placed your wizard spells");
              } else if(err.responseText == 'failed'){
                  App.snackbarCall("You have already placed your wizard spells");
              }
            }
            });
  }
};

$(function() {
  App.init();
});
