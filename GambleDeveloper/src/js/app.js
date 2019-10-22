App = {

 web3Provider: null,
 contracts: {},
 account: '0x0',
 maininstance:{},
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

        if (window.web3.currentProvider.constructor.name == "DapperLegacyProvider"){
           App.web3Provider = web3.currentProvider;
           web3 = new Web3(web3.currentProvider);
        }
      else if (window.web3.currentProvider.constructor.name == "MetamaskInpageProvider"){
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
    // web3 has to be injected/present
    if (web3) {
      if (web3.eth.accounts.length) {
        // if not locked, get account
        const account = web3.eth.accounts[0];
console.log(account);

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



  initContract: function() {
    var GambleContract = web3.eth.contract([
    {
      "constant": true,
      "inputs": [],
      "name": "totalBetAmountPlaced",
      "outputs": [
        {
          "name": "",
          "type": "uint256"
        }
      ],
      "payable": false,
      "stateMutability": "view",
      "type": "function"
    },
    {
      "constant": true,
      "inputs": [],
      "name": "numberOfBets",
      "outputs": [
        {
          "name": "",
          "type": "uint256"
        }
      ],
      "payable": false,
      "stateMutability": "view",
      "type": "function"
    },
    {
      "constant": true,
      "inputs": [],
      "name": "prizeDistributed",
      "outputs": [
        {
          "name": "",
          "type": "bool"
        }
      ],
      "payable": false,
      "stateMutability": "view",
      "type": "function"
    },
    {
      "constant": true,
      "inputs": [],
      "name": "ethToWei",
      "outputs": [
        {
          "name": "",
          "type": "uint256"
        }
      ],
      "payable": false,
      "stateMutability": "view",
      "type": "function"
    },
    {
      "inputs": [],
      "payable": false,
      "stateMutability": "nonpayable",
      "type": "constructor"
    },
    {
      "anonymous": false,
      "inputs": [
        {
          "indexed": false,
          "name": "wizardId",
          "type": "uint256"
        },
        {
          "indexed": false,
          "name": "totalBetters",
          "type": "uint256"
        },
        {
          "indexed": false,
          "name": "wizardTotalBet",
          "type": "uint256"
        },
        {
          "indexed": false,
          "name": "totalBetPlaced",
          "type": "uint256"
        },
        {
          "indexed": false,
          "name": "player",
          "type": "address"
        },
        {
          "indexed": false,
          "name": "betAmountOnwizard",
          "type": "uint256"
        }
      ],
      "name": "detailsOnLoad",
      "type": "event"
    },
    {
      "anonymous": false,
      "inputs": [
        {
          "indexed": false,
          "name": "wizardId",
          "type": "uint256"
        },
        {
          "indexed": false,
          "name": "transferAmount",
          "type": "uint256"
        },
        {
          "indexed": false,
          "name": "playerAddress",
          "type": "address"
        }
      ],
      "name": "finalWinner",
      "type": "event"
    },
    {
      "anonymous": false,
      "inputs": [
        {
          "indexed": false,
          "name": "remainder",
          "type": "uint256"
        },
        {
          "indexed": false,
          "name": "quotient",
          "type": "uint256"
        }
      ],
      "name": "returnValue",
      "type": "event"
    },
    {
      "anonymous": false,
      "inputs": [
        {
          "indexed": false,
          "name": "wizardId",
          "type": "uint256"
        },
        {
          "indexed": false,
          "name": "wizardTotalBet",
          "type": "uint256"
        }
      ],
      "name": "throwWizardInfo",
      "type": "event"
    },
    {
      "constant": true,
      "inputs": [
        {
          "name": "playerAddress",
          "type": "address"
        }
      ],
      "name": "checkPlayerExists",
      "outputs": [
        {
          "name": "",
          "type": "bool"
        }
      ],
      "payable": false,
      "stateMutability": "view",
      "type": "function"
    },
    {
      "constant": false,
      "inputs": [
        {
          "name": "userAddress",
          "type": "address"
        },
        {
          "name": "selectedWizard",
          "type": "uint256"
        },
        {
          "name": "betAmt",
          "type": "uint256"
        },
        {
          "name": "wizardPower",
          "type": "uint256"
        },
        {
          "name": "tPWizards",
          "type": "uint256"
        },
        {
          "name": "wizardSOT",
          "type": "uint256"
        },
        {
          "name": "wizardTOB",
          "type": "uint256"
        }
      ],
      "name": "joinTournamentByBet",
      "outputs": [],
      "payable": true,
      "stateMutability": "payable",
      "type": "function"
    },
    {
      "constant": true,
      "inputs": [
        {
          "name": "wizardSOT",
          "type": "uint256"
        },
        {
          "name": "wizardTOB",
          "type": "uint256"
        }
      ],
      "name": "calculateWizardRatio",
      "outputs": [
        {
          "name": "",
          "type": "uint256"
        }
      ],
      "payable": false,
      "stateMutability": "pure",
      "type": "function"
    },
    {
      "constant": false,
      "inputs": [
        {
          "name": "numerator",
          "type": "uint256"
        },
        {
          "name": "denominator",
          "type": "uint256"
        }
      ],
      "name": "getDivided",
      "outputs": [],
      "payable": false,
      "stateMutability": "nonpayable",
      "type": "function"
    },
    {
      "constant": true,
      "inputs": [
        {
          "name": "numerator",
          "type": "uint256"
        },
        {
          "name": "denominator",
          "type": "uint256"
        },
        {
          "name": "precision",
          "type": "uint256"
        }
      ],
      "name": "percent",
      "outputs": [
        {
          "name": "quotient",
          "type": "uint256"
        }
      ],
      "payable": false,
      "stateMutability": "pure",
      "type": "function"
    },
    {
      "constant": true,
      "inputs": [
        {
          "name": "wizardPower",
          "type": "uint256"
        },
        {
          "name": "tPWizards",
          "type": "uint256"
        }
      ],
      "name": "calculatePowerRatio",
      "outputs": [
        {
          "name": "",
          "type": "uint256"
        }
      ],
      "payable": false,
      "stateMutability": "pure",
      "type": "function"
    },
    {
      "constant": true,
      "inputs": [
        {
          "name": "betAmt",
          "type": "uint256"
        },
        {
          "name": "powerRatio",
          "type": "uint256"
        },
        {
          "name": "wizardRatio",
          "type": "uint256"
        }
      ],
      "name": "calculateStandardizedBet",
      "outputs": [
        {
          "name": "",
          "type": "uint256"
        }
      ],
      "payable": false,
      "stateMutability": "pure",
      "type": "function"
    },
    {
      "constant": true,
      "inputs": [
        {
          "name": "wizardId",
          "type": "uint256"
        }
      ],
      "name": "calculateTotalBetAmoutnOnThisWizard",
      "outputs": [
        {
          "name": "",
          "type": "uint256"
        }
      ],
      "payable": false,
      "stateMutability": "view",
      "type": "function"
    },
    {
      "constant": true,
      "inputs": [
        {
          "name": "wizardId",
          "type": "uint256"
        }
      ],
      "name": "calculateSsbFromTournamentWinningWizard",
      "outputs": [
        {
          "name": "",
          "type": "uint256"
        }
      ],
      "payable": false,
      "stateMutability": "view",
      "type": "function"
    },
    {
      "constant": false,
      "inputs": [],
      "name": "getContractBalance",
      "outputs": [
        {
          "name": "",
          "type": "uint256"
        }
      ],
      "payable": false,
      "stateMutability": "nonpayable",
      "type": "function"
    },
    {
      "constant": false,
      "inputs": [
        {
          "name": "wizardId",
          "type": "uint256"
        }
      ],
      "name": "distributePrizeMoney",
      "outputs": [],
      "payable": true,
      "stateMutability": "payable",
      "type": "function"
    },
    {
      "constant": false,
      "inputs": [
        {
          "name": "playerAddress",
          "type": "address"
        }
      ],
      "name": "getAllInfoOfAUser",
      "outputs": [],
      "payable": false,
      "stateMutability": "nonpayable",
      "type": "function"
    }
  ]);

    App.maininstance = GambleContract.at('0x4bbD126E6dD7D36a40269596189DA23c8DE051c2');
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

          // Game logic /////

          // Load contract data
          // App.contracts.Sample.deployed().then(function(instance) {
          //   maininstance = instance;
          //
          //
          // });
        }else{
          $('#account-error').show()
          $('#account-error').text("Please connect your wallet!");
        }
      }

    });

    var finalWinner = maininstance.finalWinner({}, {fromBlock:'latest', toBlock: 'latest'});
        if (finalWinner != undefined){
      finalWinner.watch(function(error, result){
        var winingWizard = result.args.wizardId.valueOf();
        var winingAmount = result.args.transferAmount.valueOf();
        var winnerAddress = result.args.playerAddress.valueOf();

        alert(winingWizard,winingAmount,winnerAddress);

    })
    }

  },

        claimPrize:function(){
          var inputVal = document.getElementById("myInput").value;
          alert(inputVal)
            App.maininstance.distributePrizeMoney(inputVal, function(acc,error){
              if(!error){
              //  location.reload(true);

              }else{
                console.error(error);
              }

            })
        }

   };



   $(function() {
    App.init();

  });
