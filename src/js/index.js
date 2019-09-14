$("#enroll_btn").click(function(){
	alert(web3.eth.accounts[0]);
	console.log(web3);

	var apiToken="SWNXMHPV76I52TGX4vkmp3QkVgje5B8H78KNQthv";
	var apiEmail="muthukumaresh@vijayasekar.com";
//	var walletAddress ="0xb3e94487b8C4eF9169Ebc2b9672a3222b8df401f"; 
var walletAddress =web3.eth.accounts[0]; 

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
  console.log(count);
  console.log(data.wizards.length);

  if(data.wizards.length ==0){
  	alert("no wizards");
  	  App.snackbarCall("No wizards available in your wallet, get one to enroll.");
  }
else{
	console.log(data.wizards[0].id)
}
  
    },
    error: function(){
      alert("Cannot get data");
    }
});
})