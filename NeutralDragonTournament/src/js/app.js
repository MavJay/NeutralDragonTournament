var meta_address,meta_network;

//var serverURl="http://localhost:8080/neutraldragontournament/rest/";

var serverURl="https://ndt.mavjay.com/neutraldragontournament/rest/";
var currentPage="";



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
  contractAddress:"0x0",
  winner1Address:'0x0',
  winner2Address:'0x0',
  winner3Address:'0x0',
  prize1:0,
  prize2:0,
  tableTopScorer:0,
  wizid1:0,
  wizid2:0,
  wizid3:0,
  init: async function() {
    //return await App.initWeb3();
 //   window.addEventListener('load', async () => {
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
      $("#userNotificationText").html('<br><br> You should connect your wallet to access.');
      $("#notificationinfo").show();
      $("#noti_close").hide();
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
  //  });


  },
  initWeb3: async function() {
    if (typeof web3 !== 'undefined') {
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
        meta_address= web3.eth.accounts[0];

        // Checks for account or network change.
        web3.currentProvider.publicConfigStore.on('update', callback);
      } else {
        // locked. update UI. Ask user to unlock.
      }
    }






//Detecting network of metamask connenction
    web3.version.getNetwork((err, netId) => {
        meta_network=netId;
      switch (netId) {
      
        case "1":
        console.log('This is mainnet')
        break
        case "2":
        console.log('This is the deprecated Morden test network.')
        // $(".metamask-info").text("Please switch to Mainnet");

            $("#userNotificationText").html('<br><br>Please switch to main net.<br>');
            $("#notificationinfo").show();
              $("#noti_close").hide();
        break
        case "3":
        console.log('This is the ropsten test network.')
        // $(".metamask-info").text("Please switch to Mainnet");
            $("#userNotificationText").html('<br><br>Please switch to main net.<br>');
            $("#notificationinfo").show();
              $("#noti_close").hide();
        break
        case "4":
        console.log('This is the rinkeby test network.')
        // $(".metamask-info").text("Please switch to Mainnet");
            // $("#userNotificationText").html('<br><br>Please switch to main net.<br>');
            // $("#notificationinfo").show();
            //   $("#noti_close").hide();
              // meta_address
        break
        default:
        console.log('This is local network or unknown network')
        // $(".metamask-info").text("Please switch to Mainnet");
            $("#userNotificationText").html('<br><br>Please switch to main net.<br>');
            $("#notificationinfo").show();
              $("#noti_close").hide();
      }

    })
     return App.initContract();
   },

 snackbarCall:function(text){
    Snackbar.show({text: text,pos: 'bottom-center',actionText: 'OK',actionTextColor: "var(--text-c1)"});
  },


  initContract: function() {
var NDTContract = web3.eth.contract([{"constant":false,"inputs":[{"internalType":"address","name":"playerAdress","type":"address"}],"name":"resendJoiningFee","outputs":[],"payable":true,"stateMutability":"payable","type":"function"},{"constant":true,"inputs":[{"internalType":"address","name":"playerAddress","type":"address"}],"name":"checkPlayerExists","outputs":[{"internalType":"bool","name":"","type":"bool"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":true,"inputs":[{"internalType":"address[]","name":"wizardAddress","type":"address[]"}],"name":"roundFixture","outputs":[{"internalType":"address[]","name":"","type":"address[]"},{"internalType":"address[]","name":"","type":"address[]"}],"payable":false,"stateMutability":"view","type":"function"},{"constant":false,"inputs":[{"internalType":"address payable","name":"adr","type":"address"},{"internalType":"uint256","name":"wizardId","type":"uint256"},{"internalType":"uint256","name":"affinityType","type":"uint256"}],"name":"joinTournament","outputs":[],"payable":true,"stateMutability":"payable","type":"function"},{"constant":false,"inputs":[{"internalType":"address payable","name":"wizard1Address","type":"address"},{"internalType":"address payable","name":"wizard2Address","type":"address"},{"internalType":"address payable","name":"wizard3Address","type":"address"}],"name":"distributePrize","outputs":[],"payable":true,"stateMutability":"payable","type":"function"},{"constant":true,"inputs":[],"name":"totalNOfParticipants","outputs":[{"internalType":"uint256","name":"","type":"uint256"}],"payable":false,"stateMutability":"view","type":"function"},{"inputs":[],"payable":false,"stateMutability":"nonpayable","type":"constructor"},{"anonymous":false,"inputs":[{"indexed":false,"internalType":"string","name":"status","type":"string"},{"indexed":false,"internalType":"uint256","name":"count","type":"uint256"}],"name":"playerJoiningEvent","type":"event"},{"anonymous":false,"inputs":[{"indexed":false,"internalType":"address","name":"wizard1Address","type":"address"},{"indexed":false,"internalType":"address","name":"wizard2Address","type":"address"},{"indexed":false,"internalType":"address","name":"wizard3Address","type":"address"},{"indexed":false,"internalType":"uint256","name":"prize1","type":"uint256"},{"indexed":false,"internalType":"uint256","name":"prize2","type":"uint256"},{"indexed":false,"internalType":"uint256","name":"tableTopScorer","type":"uint256"},{"indexed":false,"internalType":"uint256","name":"developerCommission","type":"uint256"}],"name":"prizeAmount","type":"event"}]);
App.maininstance = NDTContract.at(App.contractAddress);
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
          //  Check Player Valid
          // debugger

//execute only on index
if(currentPage=="index.html"){

            var playerJoinStatus = App.maininstance.playerJoiningEvent({}, {fromBlock:'latest', toBlock: 'latest'});
                if (playerJoinStatus != undefined){
                  // debugger
              playerJoinStatus.watch(function(error, result){
                // debugger
                console.log("joinstatus error",error);
                console.log("joinstatus result",result);
                App.joiningStatus = result.args.status.valueOf();
                App.playerCount = result.args.count.valueOf();
                alert(App.playerCount);

                $("#playercount").html("Number of wizards enrolled:"+ App.playerCount);
                $("#pricepool").html("Prize money:"+ App.playerCount*0.1+" ETH");
                
            })
            }

          }


//execute only if results page
if(currentPage=="results.html"){

try{

 updateResultsMessage();

  var winnerDetails = App.maininstance.prizeAmount({}, {fromBlock:0, toBlock: 'latest'});
           // App.getMyBets(account);
           console.log("requested for results", winnerDetails);
            if (winnerDetails != undefined){
              winnerDetails.watch(function(error, result){
                console.log("winner details received",result);
                
                try{
                App.wizard1Address = result.args.wizard1Address.valueOf();
                App.wizard2Address = result.args.wizard2Address.valueOf();
                App.wizard2Address = result.args.wizard2Address.valueOf();
                App.prize1= result.args.prize1.valueOf();
                App.prize2= result.args.prize2.valueOf();
                App.tableTopScorer= result.args.tableTopScorer.valueOf();
                // App.wizid1= result.args.wizid1.valueOf();
                // App.wizid2= result.args.wizid2.valueOf();
                // App.wizid3= result.args.wizid3.valueOf();

                App.wizid1=getwizid(App.wizard1Address,1);
                App.wizid2=getwizid(App.wizard2Address,2);
                App.wizid3=getwizid(App.wizard3Address,3);

              
              updateResults();
              }
              catch(error){
                 console.log("winner details error received",error);
               

              }
                });
              }

            else{

              updateResultsMessage();
            }

}
catch(error){
  console.log(error);
}
}


// exeucte only if home or duel
 if(currentPage=="index.html"||currentPage=="duel.html"){

//Get Player Notification Details
                  $.ajax({
                  type: "POST",
                  url: serverURl+"getPlayerDetails",
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
                                   //     App.snackbarCall("Please place your spells");
                                      } else if(response == 'eliminated'){
                                        App.snackbarCall("You have been eliminated from the tournament");
                                      } else if(response == 'bye'){
                                        App.snackbarCall("You have been bye passed to next level");
                                      } else {

                                      }
                                    }
                                    });
                 }


// // execute only if fixture
//  if(currentPage=="fixture.html"){

                 

//                          }

// execute only if index page
   if(currentPage=="index.html"){ 


             //Get Match array
                          $.ajax({
                          type: "POST",
                                  url: serverURl+"getScoreArr",
                                  crossDomain: true,
                                  data: {},
                                  header:{
                                  },
                                  success: function (data) {
                                // Need to check response type
                                console.log("Score Fixtures:::::::"+data);
                                // debugger
                                // for(var i=0;i<data.length;i++){
                                //   var details1='<tr><td>'+data[i][0]+'</td><td>'+data[i][1]+'</td><td>'+data[i][2]+'</td></tr>'
                                 //   $("#tableRow").append(details1);
                                // }
                                setRankingTable(data);

                                },
                                error: function (err) {
                                           }
                                      });


                                $.ajax({
                          type: "POST",
                                  url: serverURl+"gettimer",
                                  crossDomain: true,
                                  data: {},
                                  header:{
                                  },
                                  success: function (data) {
                                // Need to check response type
                                
                                // debugger
                                // for(var i=0;i<data.length;i++){
                                //   var details1='<tr><td>'+data[i][0]+'</td><td>'+data[i][1]+'</td><td>'+data[i][2]+'</td></tr>'
                                 //   $("#tableRow").append(details1);
                                // }
                              newMilli= parseInt(data);
                              starttimer();

                                },
                                error: function (err) {
                                           }
                                      });

                              }


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
    // debugger
    App.maininstance.checkPlayerExists(App.account, function(err, res){
      console.log(res);
      if(!res){
      $("#userNotificationText").html('<br>'+BetInfo);
      $("#notificationinfo").show();
        $("#noti_close").hide();
        App.maininstance.joinTournament(playerAddress,wizardId,affinityType,{from: web3.eth.accounts[0], gas: 3000000, value: web3.toWei(joiningFee,'ether')}, function(err, res){
          if(!err){
        //       App.snackbarCall("You have joined the tournament");
            $("#notificationinfo").show();
            $("#userNotificationText").html('<br> <br>'+"You have joined the tournament.");
            $("#noti_close").show();
               // if(App.joiningStatus == 'joined'){
             $.ajax({
             type: "POST",
                     url: serverURl+"jointournament",
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
                     // }
           }else{
             console.error(err);
             App.snackbarCall("You have rejected the last transaction.");
            $("#notificationinfo").show();
             $("#userNotificationText").html('<br> <br>'+"You have rejected the last transaction.");
               $("#noti_close").show();
           }
        });
      } else {
        App.snackbarCall("You have joined the tournament already");
      }
    });
  },

  placeSpells:function(spellArray){
    // debugger 
if (typeof App.maininstance !== 'undefined'&& typeof web3 !== 'undefined'){
    $("#notificationinfo").show();
    $("#userNotificationText").html('<br> <br>'+"Updading spell");
    $("#noti_close").hide();
   
    $.ajax({
    type: "POST",
            url: serverURl+"updateplacespell",
            crossDomain: true,
            data: 'player='+App.account+'&wizardspell1='+spellArray[0]+'&wizardspell2='+spellArray[1]+'&wizardspell3='+spellArray[2]+'&wizardspell4='+spellArray[3]+'&wizardspell5='+spellArray[4]+'',
            header:{
            },
            success: function (data) {
            // Need to check the reponse type
             $("#notificationinfo").show();
             $("#userNotificationText").html('<br> <br>'+"Spells updated successfully.");
             $("#noti_close").show();
            },
            error: function (err) {


              if(err.responseText == 'notExists'){
                App.snackbarCall("Player is not in the tournament");
              $("#notificationinfo").show();
             $("#userNotificationText").html('<br> <br>'+"Player is not in the tournament");
             $("#noti_close").show();
              } else if(err.responseText == 'success'){
                  App.snackbarCall("You have successfully placed your wizard spells");

              $("#notificationinfo").show();
             $("#userNotificationText").html('<br> <br>'+"You have successfully placed your wizard spells.");
             $("#noti_close").show();
              } else if(err.responseText == 'failed'){

              $("#notificationinfo").show();
             $("#userNotificationText").html('<br> <br>'+"You have already placed your wizard spells");
             $("#noti_close").show();
                  App.snackbarCall("You have already placed your wizard spells");
              }
            }
            });
  }
  else{

        App.init();
  }
  },
   initAgain: async function() {
          await ethereum.enable();
          // Acccounts now exposed
          return await App.initWeb3();
        },

};

   function callback(data){
    if(meta_address!==data.selectedAddress||meta_network!==data.networkVersion){
      location.reload();
    }


   }

$(function() {
currentPage= window.location.href.substring(window.location.href.lastIndexOf('/') + 1);

      $.ajax({
type: "POST",
  url: serverURl+"getContractAddress",
  crossDomain: true,
  data: {},
  header:{
  },
  success: function (data) {
// Need to check response type
},
                    error: function (err) {
App.contractAddress = err.responseText;
 App.init();
                    }
                    });

});

