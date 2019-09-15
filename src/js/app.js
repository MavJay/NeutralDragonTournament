App = {
  // web3Provider: null,
  // contracts: {},
  // maininstance:{},

  //  init: async function() {
  //   //return await App.initWeb3();
  //   window.addEventListener('load', async () => {
  //     // Modern dapp browsers...
  //     if (window.ethereum) {
  //       window.web3 = new Web3(ethereum);
  //       try {
  //         // Request account access if needed
  //         await ethereum.enable();
  //         // Acccounts now exposed
  //         return await App.initWeb3();
  //       } catch (error) {
  //         // User denied account access...
  //       }
  //     }
  //     // Legacy dapp browsers...
  //     else if (window.web3) {

  //       window.web3 = new Web3(web3.currentProvider);
  //       // Acccounts always exposed
  //       return await App.initWeb3();
  //     }
  //     // Non-dapp browsers...
  //     else {
  //       console.log('Non-Ethereum browser detected. You should consider trying MetaMask!');
  //     }
  //   });
  // },
  // initWeb3: async function() {
  //   if (typeof web3 !== 'undefined') {
  //     // If a web3 instance is already provided by Meta Mask.
  //     if (window.web3.currentProvider.constructor.name == "MetamaskInpageProvider"){
  //       App.web3Provider = web3.currentProvider;
  //       web3 = new Web3(web3.currentProvider);
  //     }else if (window.web3.currentProvider.constructor.name == "DapperLegacyProvider"){
  //       //alert("Dapper-->"+window.web3.currentProvider.constructor.name)
  //       App.web3Provider = web3.currentProvider;
  //       web3 = new Web3(web3.currentProvider);
  //     }else{
  //       App.web3Provider = web3.currentProvider;
  //       web3 = new Web3(web3.currentProvider);
  //     }
  //   } else {
  //     // Specify default instance if no web3 instance provided
  //     App.web3Provider = new Web3.providers.HttpProvider('http://localhost:7545');
  //     web3 = new Web3(App.web3Provider);
  //   }
  //   // web3 has to be injected/present
  //   if (web3) {
  //     if (web3.eth.accounts.length) {
  //       let account = getAccount();
  //       alert("account-->"+account);
  //       let accountInterval = setInterval(() => {
  //         if (getAccount() !== account) {
  //           App.switched = true;
  //           account = getAccount();
  //           alert("account-->"+account);
  //           $("#place-bet").off('click');
  //           $('#live-pills-tabContent .live-bet ul li').remove();
  //           $('.history-table tbody tr:not(:last-child)').remove();
  //           $('#live-pills-tabContent .live-bet label .players-count').text('0');
  //           App.contracts.sample = {};
  //           App.maininstance = {};
  //           //location.reload();
  //           return App.initContract();
  //         }
  //       }, 1000);
  //       // if not locked, get account
  //       let network = getNetwork();
  //       let networkInterval = setInterval(() => {
  //         if (getNetwork() !== network) {
  //           if(getNetwork() != 1){
  //             alert("network:"+network)
  //             $(".metamask-info").text("Please switch to Mainnet");
  //           }else{
  //             $(".metamask-info").text("");
  //             $("#place-bet").off('click');
  //             $('#live-pills-tabContent .live-bet ul li').remove();
  //             $('.history-table tbody tr:not(:last-child)').remove();
  //               $('#live-pills-tabContent .live-bet label .players-count').text('0');
  //             App.contracts.sample = {};
  //             App.maininstance = {};
  //             //location.reload();
  //             return App.initContract();
  //           }
  //         }
  //       },1000);
  //       function getNetwork(){
  //         const network = web3.version.network;
  //         return network;
  //       }

  //       function getAccount(){
  //       const account = web3.eth.accounts[0];
  //       return account;
  //     }
  //       // updates UI, state, pull data
  //     } else {
  //       alert("account is locked");
  //       // locked. update UI. Ask user to unlock.
  //     }
  //   }
  //   //Detecting network of metamask connenction
  //   web3.version.getNetwork((err, netId) => {
  //     switch (netId) {
  //       case "1":
  //       console.log('This is mainnet')
  //       break
  //       case "2":
  //       console.log('This is the deprecated Morden test network.')
  //       $(".metamask-info").text("Please switch to Mainnet");
  //       break
  //       case "3":
  //       console.log('This is the ropsten test network.')
  //       $(".metamask-info").text("Please switch to Mainnet");
  //       break
  //       default:
  //       console.log('This is local network or unknown network')
  //       $(".metamask-info").text("Please switch to Mainnet");
  //     }

  //   })
  //   return App.initContract();
  // },



 web3Provider: null,
  contracts: {},
  account: '0x0',
  maininstance:{},
  switched:false,
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
      if (window.web3.currentProvider.constructor.name == "MetamaskInpageProvider"){
        App.web3Provider = web3.currentProvider;
        web3 = new Web3(web3.currentProvider);
      }}else if (window.web3.currentProvider.constructor.name == "DapperLegacyProvider"){
          App.web3Provider = web3.currentProvider;
          web3 = new Web3(web3.currentProvider);
       }
      else{
        App.web3Provider = web3.currentProvider;
        web3 = new Web3(web3.currentProvider);
      }
    } else {
      // Specify default instance if no web3 instance provided
      App.web3Provider = new Web3.providers.HttpProvider('http://localhost:7545');
      web3 = new Web3(App.web3Provider);
    }
    // web3 has to be injected/present
    if (web3) {
      if (web3.eth.accounts.length) {
        // if not locked, get account
        const account = web3.eth.accounts[0];
        web3.eth.defaultAccount = web3.eth.accounts[0];
        return account;
      }
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
    $.getJSON("NeutralDragonTournament.json", function(Tournament) {
      console.log(Tournament)
      //  Instantiate a new truffle contract from the artifact
      App.contracts.Sample = TruffleContract(Tournament);
      //  Connect provider to interact with contract
      App.contracts.Sample.setProvider(App.web3Provider);

      return App.render();

    });

    // return App.bindEvents();
  },


  render: function(){

    web3.eth.getCoinbase(function(error,account){
      debugger
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

          // Game logic /////

          // Load contract data
          App.contracts.Sample.deployed().then(function(instance) {
            maininstance = instance;
            //Check Player Valid
            var playercount_event = maininstance.player_count_event({}, {fromBlock:'latest', toBlock: 'latest'});
                if (playercount_event != undefined){
              playercount_event.watch(function(error, result){
                var playerCount = result.args.count.valueOf();
                alert("Players in Tornament"+playerCount);
            })
            }

            var matchFixture = maininstance.match_fixture({}, {fromBlock:'latest', toBlock: 'latest'});
                if (playercount_event != undefined){
              matchFixture.watch(function(error, result){
                // var playerCount = result.args.count.valueOf();
                alert(result);
            })
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
      maininstance.checkPlayerExists(playerAddress).then(function(bool){
        if(!bool){
          App.snackbarCall("Please confirm your transaction");
           maininstance.joinTournament(playerAddress,wizardId,joiningFee,affinityType,{from: App.account,value: web3.toWei(joiningFee,'ether')}).then(function(acc,error){
            if(!error){
                $(".metamask-info p").text("Bet submitted! Waiting for player to place a bet.");
                App.snackbarCall("You have joined the tournament");
            }else{
              $(".metamask-info p").text("Something went wrong!");
              console.error(error);
            }

          }).catch(function(err){
            if(err.message.includes("User denied transaction signature")){
              $(".metamask-info p").text("You rejected last transaction in metamask");
            }else{
              $(".metamask-info p").text("Something went wrong. Please check your metamask for detailed error");
            }

          });
        } else {
        App.snackbarCall("Already joined the tournament");
        }
      });
  },

  placeSpells:function(){
    debugger
    maininstance.roundFixtures().then(function(acc,error){

  });
  }
};


$(function() {
  App.init();
});




// App = {
//   web3Provider: null,
//   contracts: {},
//   account: '0x0',
//   maininstance:{},
//   switched:false,
//   init: async function() {
//     //return await App.initWeb3();
//     window.addEventListener('load', async () => {
//       // Modern dapp browsers...
//       if (window.ethereum) {
//         window.web3 = new Web3(ethereum);
//         try {
//           // Request account access if needed
//           await ethereum.enable();
//           // Acccounts now exposed
//           return await App.initWeb3();
//         } catch (error) {
//           // User denied account access...
//         }
//       }
//       // Legacy dapp browsers...
//       else if (window.web3) {

//         window.web3 = new Web3(web3.currentProvider);
//         // Acccounts always exposed
//         return await App.initWeb3();
//       }
//       // Non-dapp browsers...
//       else {
//         console.log('Non-Ethereum browser detected. You should consider trying MetaMask!');
//       }
//     });
//   },
//   initWeb3: async function() {
//     if (typeof web3 !== 'undefined') {
//       // If a web3 instance is already provided by Meta Mask.
//       if (window.web3.currentProvider.constructor.name == "MetamaskInpageProvider"){
//         App.web3Provider = web3.currentProvider;
//         web3 = new Web3(web3.currentProvider);
//       }else{
//         App.web3Provider = web3.currentProvider;
//         web3 = new Web3(web3.currentProvider);
//       }
//     } else {
//       // Specify default instance if no web3 instance provided
//       App.web3Provider = new Web3.providers.HttpProvider('http://localhost:7545');
//       web3 = new Web3(App.web3Provider);
//     }
//     // web3 has to be injected/present
//     if (web3) {
//       if (web3.eth.accounts.length) {
//         let account = getAccount();
//         let accountInterval = setInterval(() => {
//           if (getAccount() !== account) {
//             App.switched = true;
//             account = getAccount();
//             $("#place-bet").off('click');
//             $('#live-pills-tabContent .live-bet ul li').remove();
//             $('.history-table tbody tr:not(:last-child)').remove();
//               $('#live-pills-tabContent .live-bet label .players-count').text('0');
//             App.contracts.sample = {};
//             App.maininstance = {};
//             //location.reload();
//             return App.initContract();
//           }
//         }, 1000);
//         // if not locked, get account
//         let network = getNetwork();
//         let networkInterval = setInterval(() => {
//           if (getNetwork() !== network) {
//             if(getNetwork() != 1){
//               //alert(network)
//               $(".metamask-info").text("Please switch to Mainnet");
//             }else{
//               $(".metamask-info").text("");
//               $("#place-bet").off('click');
//               $('#live-pills-tabContent .live-bet ul li').remove();
//               $('.history-table tbody tr:not(:last-child)').remove();
//                 $('#live-pills-tabContent .live-bet label .players-count').text('0');
//               App.contracts.sample = {};
//               App.maininstance = {};
//               //location.reload();
//               return App.initContract();
//             }
//           }
//         },1000);
//         function getNetwork(){
//           const network = web3.version.network;
//           return network;
//         }

//         function getAccount(){
//         const account = web3.eth.accounts[0];
//         return account;
//       }
//         // updates UI, state, pull data
//       } else {
//         // locked. update UI. Ask user to unlock.
//       }
//     }
//     //Detecting network of metamask connenction
//     web3.version.getNetwork((err, netId) => {
//       switch (netId) {
//         case "1":
//         console.log('This is mainnet')
//         break
//         case "2":
//         console.log('This is the deprecated Morden test network.')
//         $(".metamask-info").text("Please switch to Mainnet");
//         break
//         case "3":
//         console.log('This is the ropsten test network.')
//         $(".metamask-info").text("Please switch to Mainnet");
//         break
//         default:
//         console.log('This is local network or unknown network')
//         $(".metamask-info").text("Please switch to Mainnet");
//       }

//     })
//     return App.initContract();
//   },
//   initContract: function() {
//     $.getJSON("Enroll.json", function(GamePlay) {
//       //  Instantiate a new truffle contract from the artifact
//       App.contracts.Sample = TruffleContract(GamePlay);
//       //  Connect provider to interact with contract
//       App.contracts.Sample.setProvider(App.web3Provider);

//       return App.render();

//     });
//   },
//   render: function(){

//     web3.eth.getCoinbase(function(error,account){
//       if(error === null){
//         if(account !== null){
//           App.account = account;
//           web3.eth.getBalance(account, function (error, wei) {
//             if (!error) {
//               //web3.fromWei(eth.getBalance(eth.coinbase));
//               var balance = web3.fromWei(wei, 'ether');
//               App.eth_balance = balance;
//               console.log(balance + " ETH");
//             }
//           });
//           $("#guessnum").keyup(function(e){
//             if(e.currentTarget.value == ""){
//               $('#place-bet').attr('disabled','true');
//               $('#place-bet').removeClass('active');
//             }else{
//               $('#place-bet').removeAttr('disabled');
//               $('#place-bet').addClass('active');
//             }
//               });
//           $("#guessnum").keydown(function(e){
//             if($(this).context.value.length < 2){
//               // prevent: "e", "=", ",", "-", "."
//               if ([69, 187, 188, 189, 190].includes(e.keyCode)) {
//                 e.preventDefault();
//               }else{
//                 $('#place-bet').removeAttr('disabled');
//               }
//             }else{

//               if(e.keyCode != 8){
//                 e.preventDefault();
//               }
//             }

//           })
//           // Load contract data
//           App.contracts.Sample.deployed().then(function(instance) {
//             maininstance = instance;
//             instance.playSize.call().then(function(v) {
//               $('.total-count').text(v);
//             })

//             var poolArray = [50,100,500,10]
//             for(var i=0;i<poolArray.length;i++){

//               instance.getPlayerAddress(poolArray[i]).then(function(arr){
//                 var array=arr[0];
//                 var betAmt = parseInt(arr[1].valueOf());
//                 var live;
//                 switch(betAmt) {
//                   case 50:
//                   live= "live1";
//                   break;
//                   case 100:
//                   live="live2";
//                   break;
//                   case 500:
//                   live="live3";
//                   break;
//                   case 10:
//                   live="live4";
//                   break;
//                   default:
//                   live="live4";
//                   break;
//                 }
//                 var count = 0;
//                 for(i=0;i<array.length;i++){
//                   if(array[i] != 0x0000000000000000000000000000000000000000){
//                     count++;
//                     $('#'+live +' ul').append('<li>'+App.truncate(array[i],12)+'</li>');
//                     $('#'+live+ ' .players-count').text(count);
//                   }
//                 }
//               })
//             }
//             var playercount_event;
//             var winScreen_event;
//             var totalwinamoubt;
//             const playerdetails_event = instance.playerdetails_event({},{fromBlock: 0, toBlock: 'latest'});
//             if(App.switched == false){
//                playercount_event = instance.player_count_event({}, {fromBlock:'latest', toBlock: 'latest'});
//                winScreen_event = instance.playerdetails_event({},{fromBlock: 'latest', toBlock: 'latest'});
//                totalwinamoubt = instance.totalwinamoubt({},{fromBlock: 'latest', toBlock: 'latest'});
//             }

// //live tracking online count and player update ui
//             var pc_block_num = 0;
//             if (playercount_event != undefined){
//             playercount_event.watch(function(error, result){
//               $(".metamask-info").text("");
//               if(pc_block_num != result.blockNumber){
//                 $(".play-arena section").css({"filter":"unset","pointer-events":"auto"});
//                 pc_block_num = result.blockNumber;
//               d = parseInt(result.args.bet.valueOf());
//               c = result.args.count.valueOf()
//               e = result.args.addr.valueOf();
//               var live;
//               switch(d) {
//                 case 50:
//                 live= "live1";
//                 break;
//                 case 100:
//                 live="live2";
//                 break;
//                 case 500:
//                 live="live3";
//                 break;
//                 case 10:
//                 live="live4";
//                 break;
//                 default:
//                 live="live4";
//                 break;
//               }

//               $('#'+live+ ' .players-count').text(c);
//               $('#'+live +' ul').append('<li>'+App.truncate(e,12)+'</li>');
//               $('#total_players').text(c);
//             }
//           })
//         }

// //win modal pop up ui
//           var win_block_num = 0;
//           if (winScreen_event != undefined){
//           winScreen_event.watch(function(error,result){
//             if(win_block_num != result.blockNumber){
//               win_block_num = result.blockNumber;
//               addr = result.args.adr.valueOf();
//               tablecount = result.args.count.valueOf();
//               guessnums = result.args.guessnums.valueOf();
//               winPercent = result.args.winPercent.valueOf();
//               bet = parseInt(result.args.bet.valueOf());
//               var winAmountBlock;
//               if(addr.includes(App.account)){
//               current_win_amount = 0;

//               if(totalwinamoubt != undefined){
//                 totalwinamoubt.watch(function(error,result){
//                   if(winAmountBlock != result.blockNumber){
//                     winAmountBlock = result.blockNumber;
//                     addr = result.args.adr.valueOf();
//                     winAmount = result.args.winamount;
//                     winAmount.forEach(function(element,index) {
//                       if(addr[index] == App.account){
//                         $('.eth-amount').text("You won "+web3.fromWei(element.valueOf(),"ether")+" ETH")
//                       //current_win_amount = ;
//                       }
//                     })
//                   }
//                 })
//               }
// }
//               var bet_amt;
//               var live;
//               switch (bet) {
//                 case 50:
//                 bet_amt = 0.05;
//                 live="live1";
//                 break;
//                 case 100:
//                 bet_amt = 0.1;
//                 live="live2";
//                 break;
//                 case 500:
//                 bet_amt = 0.5;
//                 live="live3";
//                 break;
//                 case 10:
//                 bet_amt = 0.01;
//                 live="live4";
//                 break;
//                 default:
//               }
//               $('#'+live+ ' ul li').remove();
//               $('#'+live+' label .players-count').text("0");
//               var newObj = {};
//                     winPercent.forEach(function(element,index) {
//                       var sample= {};
//                       var arr=[];
//                       sample["number"] = guessnums[index].valueOf();
//                       sample["address"] = addr[index];
//                       sample["percent"] = element.valueOf();
//                       if (!Object.keys(newObj).includes(element.valueOf())){
//                         arr.push(sample)
//                         newObj[parseInt(element.valueOf())] = arr;
//                       }else{
//                         newObj[parseInt(element.valueOf())].push(sample)
//                       }
//                     })
//                     var intResult = Object.keys(newObj).map(function (x) {
//                       return parseInt(x, 10);
//                     });
//                     sortedPercentage = intResult.sort( function(x, y)
//                     {
//                     return y - x;
//                     } );
//                     var appendStr = "";
//                     sortedPercentage.forEach(function(ele,index){
//                       newObj[ele].forEach(function(e,i){
//                         var dotclass;
//                         if(App.account == e.address){
//                           dotclass = "current-player-dot";
//                           $('#WinModalCenter').fireworks();
//                           //$('#win-detail').text("YOU WON "+parseInt(e.percent)/100+"% ");
//                           $('#WinModalCenter').modal('show');
//                         }
//                         appendStr += '<tr><td><span class="'+dotclass+'"></span>'+ App.truncate(e.address,12) +'</td><td>'+e.number+'</td><td>'+parseInt(e.percent)/100+'</td></tr>';
//                       })
//                     })
//                     const sum_mean = guessnums.reduce((total, amount) => parseInt(total) + parseInt(amount));
//                     const mean = sum_mean/guessnums.length;
//                     appendStr += '<tr><td><b>Mean</b></td><td><b>'+mean.toFixed(2)+'</b></td><td></td></tr>';
//                     $('.history-winscreen-table tbody').append(appendStr);
//             }
//           });
//         }

// //player transaction history update in ui
//             var pd_block_num = 0
//             if (playerdetails_event != undefined){
//             playerdetails_event.watch(function(error,result){
//             //  debugger
//               //console.log("yes inside")
//               web3.eth.getTransactionReceipt(result.transactionHash,function(err,value){
//                 //value.logs.topics[ 2 ]
//               })

//               if (pd_block_num != result.blockNumber){
//                 pd_block_num = result.blockNumber;
//                 addr = result.args.adr.valueOf();
//                 tablecount = result.args.count.valueOf();
//                 guessnums = result.args.guessnums.valueOf();
//                 winPercent = result.args.winPercent.valueOf();
//                 bet = parseInt(result.args.bet.valueOf());
//                 var pool;
//                 var bet_amt;
//                 switch (bet) {
//                   case 50:
//                   pool = "pool1";
//                   bet_amt = 0.05;
//                   break;
//                   case 100:
//                   pool = "pool2";
//                   bet_amt = 0.1;
//                   break;
//                   case 500:
//                   pool = "pool3";
//                   bet_amt = 0.5;
//                   break;
//                   case 10:
//                   pool = "pool4";
//                   bet_amt = 0.01;
//                   break;
//                   default:
//                 }
//                 ///Ascending ordered table format
//                           var newObj = {};
//                                 winPercent.forEach(function(element,index) {
//                                   var sample= {};
//                                   var arr=[];
//                                   sample["number"] = guessnums[index].valueOf();
//                                   sample["address"] = addr[index];
//                                   sample["percent"] = element.valueOf();
//                                   if (!Object.keys(newObj).includes(element.valueOf())){
//                                     arr.push(sample)
//                                     newObj[parseInt(element.valueOf())] = arr;
//                                   }else{
//                                     newObj[parseInt(element.valueOf())].push(sample)
//                                   }
//                                 })
//                                 var intResult = Object.keys(newObj).map(function (x) {
//                                   return parseInt(x, 10);
//                                 });
//                                 sortedPercentage = intResult.sort( function(x, y)
//                                 {
//                                 return y - x;
//                                 } );
//                                 var appendStr = "";
//                                 sortedPercentage.forEach(function(ele,index){
//                                   newObj[ele].forEach(function(e,i){
//                                     if(App.account == e.address){
//                                       web3.eth.getBlock(result.blockNumber, (error, block) => {
//                                         const timestamp = block.timestamp;
//                                         const months = ["JAN", "FEB", "MAR","APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"];
//                                         var date1 = new Date(timestamp*1000);
//                                         var utc_date = date1.toUTCString()
//                                         var loc_date = new Date(utc_date)
//                                         var f_date = loc_date.getDate() + "-" + months[loc_date.getMonth()] +"-"+ loc_date.getFullYear()+"; "+loc_date.getHours()+":"+ (loc_date.getMinutes()<10?'0':'') + loc_date.getMinutes()  ;
//                                         $('#self-wins tbody tr:first').before('<tr><td>'+ bet_amt +'</td><td>'+ f_date +'</td><td>'+e.number+'</td><td>'+parseInt(e.percent)/100+'</td></tr>');
//                                       });
//                                     }
//                                   appendStr += '<tr><td>'+ App.truncate(e.address,12) +'</td><td>'+e.number+'</td><td>'+parseInt(e.percent)/100+'</td></tr>';

//                                   commision = (addr.length * bet_amt) - ((addr.length * bet_amt)*10/100)
//                                   win_amt = commision/(addr.length*(parseInt(e.percent)/100)/100)
//                                   })
//                                 })
//                 const sum_mean = guessnums.reduce((total, amount) => parseInt(total) + parseInt(amount));
//                 const mean = sum_mean/guessnums.length;
//                 appendStr += '<tr><td><b>Mean</b></td><td><b>'+mean.toFixed(2)+'</b></td><td></td></tr>';
//                 $('#'+pool+' tbody tr:first').before(appendStr);              }
//             })
//           }
//           });



//           $('#account-error').hide()
//           $(".select-ether").click(function () {
//             $(".select-ether").removeClass("active");
//             $(this).addClass("active");

//           });

//           $('#place-bet').click(function(){
//             var num = $('#guessnum').val();
//             var val = parseInt($('.coin-bet .active').attr('data-value'));
//             var eth = parseFloat($('.coin-bet .active .ether-value').text());
//             if($(".coin-bet .active").length != 0 ){
//               if(num!= 0 && num < 100 && num != ""){
//                 App.placebet(App.account,num,val,eth)

//               }else{
//                 App.snackbarCall("Please type a valid number");
//               }
//             }else{
//               App.snackbarCall("Please choose a bet amount");
//             }

//           })
//         }else{
//           $('#account-error').show()
//           $('#account-error').text("Please connect your wallet!");
//         }
//       }
//     });
//   },
//   snackbarCall:function(text){
//     Snackbar.show({text: text,pos: 'bottom-center',actionText: 'OK',actionTextColor: "var(--text-c1)"});
//   },
//   truncate : function (fullStr, strLen, separator) {
//     if (fullStr.length <= strLen) return fullStr;

//     separator = separator || '...';

//     var sepLen = separator.length,
//     charsToShow = strLen - sepLen,
//     frontChars = Math.ceil(charsToShow/2),
//     backChars = Math.floor(charsToShow/2);

//     return fullStr.substr(0, frontChars) +
//     separator +
//     fullStr.substr(fullStr.length - backChars);
//   },
//   placebet:function(a,b,c,d){
//       $(".metamask-info").text("Please confirm in Metamask");
//       var e = parseInt(c);
//       maininstance.checkPoolcheck(e).then(function(bool){
//         if(bool){
//           maininstance.checkDuplicate(App.account,c).then(function(bool){
//             if(!bool){
//               $(".play-arena section").css({"filter":"blur(8px)","pointer-events":"none"});
//               maininstance.joinGame(a,b,c,{from: App.account,value: web3.toWei(d,'ether')}).then(function(acc,error){
//                 if(!error){
//                     $(".metamask-info").text("Bet submitted!");
//                     App.snackbarCall("You bet is placed successfully");
//                 }else{
//                   $(".play-arena section").css({"filter":"unset","pointer-events":"auto"});
//                   $(".metamask-info").text("Something went wrong!");
//                 }
//               }).catch(function(err){
//                 if(err.message.includes("User denied transaction signature")){
//                   $(".play-arena section").css({"filter":"unset","pointer-events":"auto"});
//                   $(".metamask-info").text("You rejected last transaction in metamask");
//                 }else{
//                   $(".play-arena section").css({"filter":"unset","pointer-events":"auto"});
//                   $(".metamask-info").text("Something went wrong. Please check your metamask for detailed error");
//                 }

//               });
//             }else{
//               $(".play-arena section").css({"filter":"unset","pointer-events":"auto"});
//               $(".metamask-info").text("You already placed your bet for this pool. Try other pool!");
//               App.snackbarCall("You already placed your bet for this pool. Try other pool!");
//             }
//           })
//         }else{
//           $(".play-arena section").css({"filter":"unset","pointer-events":"auto"});
//           App.snackbarCall("Something went wrong!. Please try again later")
//         }
//       })
//   }

// };

// $(function() {
//   App.init();
// });
