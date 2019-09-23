$("#enroll_btn").click(function(){
	var apiToken="SWNXMHPV76I52TGX4vkmp3QkVgje5B8H78KNQthv";
	var apiEmail="muthukumaresh@vijayasekar.com";
	// var walletAddress ="0xa20a319098BF993eA6Cc93922c9b001F1A3dd7Db";
	var walletAddress ="0xc92a2167a1f788a468665e292ea4038d7a9928dd";

// var walletAddress =web3.eth.accounts[0];

	$.ajax({
    url: "https://cheezewizards-rinkeby.alchemyapi.io/wizards?owner="+walletAddress,
    beforeSend: function(xhr) { 
      xhr.setRequestHeader("x-api-token", apiToken); 
      xhr.setRequestHeader("x-email",apiEmail);
      xhr.setRequestHeader("contentType","application/json");
    },
    type: 'GET',
    contentType: 'application/json',
    success: function (data) {
      alert(JSON.stringify(data));
  	console.log(data);

  var count = Object.keys(data).length;

  if(data.wizards.length ==0){
  	  App.snackbarCall("No wizards available in your wallet, get one to enroll.");
  }
else{
	var joiningFee = '0.1';
	debugger
	 App.joinTournament(walletAddress,data.wizards[0].id,joiningFee,data.wizards[0].affinity);
	//App.placeSpells();
}
  
    },
    error: function(){
      alert("Cannot get data");
    }
});
})


// $(window).load(function(){
// 	
// });
$(window).on("load",function() {
    setTournamentDetails();
});

function setTournamentDetails(){

	var timerValue="2:00 hrs";
	var playerCount =4;
	var priceMoney = 2 ;


	var details='<p> Tournament starts in :'+timerValue+' <br> Number of wizards enrolled:'+playerCount+'<br> Prize money: '+priceMoney+' ETH </p>';

	$("#tournament_status").html(details);
}