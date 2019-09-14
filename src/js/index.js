$("#enroll_btn").click(function(){
	alert(web3.eth.accounts[0]);
	console.log(web3);

	var apiToken="SWNXMHPV76I52TGX4vkmp3QkVgje5B8H78KNQthv";
	var apiEmail="muthukumaresh@vijayasekar.com";


	$.ajax({
    url: "https://cheezewizards-rinkeby.alchemyapi.io/wizards?owner="+web3.eth.accounts[0],
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

  console.log(data.wizards[0].id)
    },
    error: function(){
      alert("Cannot get data");
    }
});
})