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
    $.getJSON("BigGamble.json", function(Gamble) {
      // console.log(Gamble)
      //  Instantiate a new truffle contract from the artifact
      App.contracts.Sample = TruffleContract(Gamble);
      //  Connect provider to interact with contract
      App.contracts.Sample.setProvider(App.web3Provider);

      return App.render();

    });

    // return App.bindEvents();
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
              // console.log(balance + " ETH");

              
            }
          });


  App.contracts.Sample.deployed().then(function(instance) {
            debugger
            maininstance = instance;


          });


         

  }
}
});

  },

        // claimPrize:function(){
        //   debugger
        //   var inputVal = document.getElementById("myInput").value;
        //   alert(inputVal)
        //     App.maininstance.distributePrizeMoney(inputVal, function(acc,error){
        //       if(!error){
        //         debugger
        //       //  location.reload(true);
        //       console.log("success",acc,error);

        //       }else{
        //         console.log("error",acc,error);
        //       }

        //     })
        // }




        claimPrize:function(){
          var inputVal = document.getElementById("myInput").value;
          alert(inputVal)

          maininstance.distributePrizeMoney(parseInt(inputVal));
           
        },

   };



   $(function() {
    App.init();

  });


//   var account1 = web3.eth.accounts[0];
//   var accountInterval = setInterval(function() {
//   if (web3.eth.accounts[0] !== account1) {
//   account1 = web3.eth.accounts[0];
//   location.reload();
//   }
// }, 100);