App = {
  web3Provider: null,
  contracts: {},

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
      }else if (window.web3.currentProvider.constructor.name == "DapperLegacyProvider"){
        //alert("Dapper-->"+window.web3.currentProvider.constructor.name)
        App.web3Provider = web3.currentProvider;
        web3 = new Web3(web3.currentProvider);
      }else{
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
        let account = getAccount();
        //alert("account-->"+account);
        let accountInterval = setInterval(() => {
          if (getAccount() !== account) {
            App.switched = true;
            account = getAccount();
            //alert("account-->"+account);
            $("#place-bet").off('click');
            $('#live-pills-tabContent .live-bet ul li').remove();
            $('.history-table tbody tr:not(:last-child)').remove();
              $('#live-pills-tabContent .live-bet label .players-count').text('0');
            App.contracts.sample = {};
            App.maininstance = {};
            //location.reload();
            return App.initContract();
          }
        }, 1000);
        // if not locked, get account
        let network = getNetwork();
        let networkInterval = setInterval(() => {
          if (getNetwork() !== network) {
            if(getNetwork() != 1){
              //alert("network:"+network)
              $(".metamask-info").text("Please switch to Mainnet");
            }else{
              $(".metamask-info").text("");
              $("#place-bet").off('click');
              $('#live-pills-tabContent .live-bet ul li').remove();
              $('.history-table tbody tr:not(:last-child)').remove();
                $('#live-pills-tabContent .live-bet label .players-count').text('0');
              App.contracts.sample = {};
              App.maininstance = {};
              //location.reload();
              return App.initContract();
            }
          }
        },1000);
        function getNetwork(){
          const network = web3.version.network;
          return network;
        }

        function getAccount(){
        const account = web3.eth.accounts[0];
        return account;
      }
        // updates UI, state, pull data
      } else {
        alert("account is locked");
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
    /*
     * Replace me...
     */

    return App.bindEvents();
  },

  bindEvents: function() {
    $(document).on('click', '.btn-adopt', App.handleAdopt);
  },

  markAdopted: function(adopters, account) {
    /*
     * Replace me...
     */
  },

  handleAdopt: function(event) {
    event.preventDefault();

    var petId = parseInt($(event.target).data('id'));

    /*
     * Replace me...
     */
  }

  

};


$(function() {
  App.init();
});
