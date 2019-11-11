var tournamentTimer;
var newMilli=0;
var tournament_starter ;
var BetInfo = "Waiting for the transaction to be confirmed.<br>Please confirm your transaction in METAMASK if you have not done so.";


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
      // alert(JSON.stringify(data));
  	console.log(data);

  var count = Object.keys(data).length;

  if(data.wizards.length ==0){
     $("#userNotificationText").html("No wizards available in your wallet, get one to enroll.");
    $("#notificationinfo").show();
    $("#noti_close").show();
  	  // App.snackbarCall("No wizards available in your wallet, get one to enroll.");
  }
else{
	var joiningFee = '0.1';

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
// $(window).on("load",function() {
//     setTournamentDetails();
// });

// function setTournamentDetails(){

// 	var timerValue="2:00 hrs";
// 	var playerCount =4;
// 	var priceMoney = 2 ;


// 	var details='<p> Tournament starts in :'+timerValue+' <br> Number of wizards enrolled:'+playerCount+'<br> Prize money: '+priceMoney+' ETH </p>';

// 	$("#tournament_status").html(details);
// }


// setRankingTable();

function setRankingTable(dataArray){

  // debugger

  var Basetable ='<table  id="rank_master"  class="table table-striped table-bordered">'+
  '<thead><tr><th>Wizard ID</th><th>Affinity</th><th>Address</th><th>Rank</th><th>Score</th><th>Duels played</th><th>Normal wins</th>'+
  '<th>Normal losses</th><th>Ties</th><th>Wins against opponent’s Elemental spell</th><th>Losses against opponent’s Elemental spell</th>'+
  '<th>Elemental wins</th><th>Elemental losses</th></tr></thead><tbody id="rank_master_body"></tbody></table>';


  $("#table_container").html(Basetable);


  var tableData ="";


  for(var i=0;i<dataArray.length;i++){








    tableData= tableData+'<tr><td>'+dataArray[i].wizid+'</td>'
    +'<td>'+dataArray[i].affinity+'</td>'
    +'<td title="'+dataArray[i].plrAddress+'">'+dataArray[i].plrAddress+'</div></td>'
    +'<td>'+dataArray[i].rank+'</td>'
    +'<td>'+dataArray[i].totalScore+'</td>'
    +'<td>'+dataArray[i].duelsPlayed+'</td>'
    +'<td>'+dataArray[i].noofwins+'</td>'
    +'<td>'+dataArray[i].noOfLoss+'</td>'
    +'<td>'+dataArray[i].noOftie+'</td>'
    +'<td>'+dataArray[i].noOfWinAgainstElemental+'</td>'
    +'<td>'+dataArray[i].noOfLossAgainstElemental+'</td>'
    +'<td>'+dataArray[i].elementalWin+'</td>'
    +'<td>'+dataArray[i].elementalLoss+'</td></tr>';
  }
      // tableData=   
      // '<tr><td>1234</td><td>Fire</td><td>0x6E6A91Fd6E11D77Ef7a40391534B13A7E03A86FC</td><td>1</td><td>25</td><td>3</td><td>6</td><td>1</td><td>1</td><td>2</td><td>1</td><td>2</td><td>2</td></tr>';
      // // '<tr><td>1234</td><td>Fire</td><td>0x6E6A91Fd6E11D77Ef7a40391534B13A7E03A86FC</td><td>1</td><td>25</td><td>3</td><td>6</td><td>1</td><td>1</td><td>2</td><td>1</td><td>2</td><td>2</td></tr>'+
      // // '<tr><td>1234</td><td>Fire</td><td>0x6E6A91Fd6E11D77Ef7a40391534B13A7E03A86FC</td><td>1</td><td>25</td><td>3</td><td>6</td><td>1</td><td>1</td><td>2</td><td>1</td><td>2</td><td>2</td></tr>';



$("#rank_master_body").append(tableData);
// $('#rank_master').DataTable();
var wiztabel_height = $(window).height()-$('.navbar').height()-$("footer").height()-400;
console.log("height", wiztabel_height);
$('#rank_master').DataTable( {
    scrollY: wiztabel_height
  } );

//  $(document).ready( function () {
//     $('#rank_master').DataTable();
// } );
}

 function setTimer(millisec) {
        var seconds = (millisec / 1000).toFixed(0);
        var minutes = Math.floor(seconds / 60);
        var hours = "";
        if (minutes > 59) {
            hours = Math.floor(minutes / 60);
            hours = (hours >= 10) ? hours : "0" + hours;
            minutes = minutes - (hours * 60);
            minutes = (minutes >= 10) ? minutes : "0" + minutes;
        }

        seconds = Math.floor(seconds % 60);
        seconds = (seconds >= 10) ? seconds : "0" + seconds;
        if (hours != "") {
            return hours + ":" + minutes + ":" + seconds;
        }
        return minutes + ":" + seconds;
    }

    function sec2time(timeInSeconds) {
    var pad = function(num, size) { return ('000' + num).slice(size * -1); },
    time = parseFloat(timeInSeconds).toFixed(3),
    hours = Math.floor(time / 60 / 60),
    minutes = Math.floor(time / 60) % 60,
    seconds = Math.floor(time - minutes * 60),
    milliseconds = time.slice(-3);

    return pad(hours, 2) + ':' + pad(minutes, 2) + ':' + pad(seconds, 2) + ',' + pad(milliseconds, 3);
}


function millitoDay(forCalc){

  if(forCalc>1000){
  // debugger

  forCalc = forCalc - 1000;
  newMilli=forCalc;
                let secondsInMilli = 1000;
                let minutesInMilli = secondsInMilli * 60;
                let hoursInMilli = minutesInMilli * 60;
                let daysInMilli = hoursInMilli * 24;

                let elapsedDays = forCalc / daysInMilli;
                let d1 = forCalc % daysInMilli;
                //  different = different % daysInMilli;
                    elapsedDays=parseInt(elapsedDays);

                let elapsedHours = d1 / hoursInMilli;
                d1 = d1 % hoursInMilli;
                elapsedHours=parseInt(elapsedHours);

                let elapsedMinutes = d1 / minutesInMilli;
                d1 = d1 % minutesInMilli;
                elapsedMinutes=parseInt(elapsedMinutes);

                let elapsedSeconds = d1 / secondsInMilli;
                elapsedSeconds=parseInt(elapsedSeconds);

             /*   System.out.printf(
                        "%d days, %d hours, %d minutes, %d seconds%n",
                        elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds);*/
                //     appointmentViewHolder.tickDown.setText( elapsedDays+"days "+ elapsedHours+"hours "+ elapsedMinutes+"minutes to join call");
                let timer = "";
                if (elapsedDays > 1 && elapsedHours>0) {
                    timer = elapsedDays + " days " + elapsedHours + " hours " + elapsedMinutes + " minutes " + elapsedSeconds ;
                }
                else if(elapsedDays==1 &&elapsedHours>0){
                    timer = elapsedDays + " day " + elapsedHours + " hours " + elapsedMinutes + " minutes " + elapsedSeconds ;
                }
                else if(elapsedDays>1&&elapsedHours==0){
                    timer = elapsedDays + " days "  + elapsedMinutes + " minutes " + elapsedSeconds ;
                }
                else if(elapsedDays==1&&elapsedHours==0){
                    timer = elapsedDays + " day "  + elapsedMinutes + " minutes " + elapsedSeconds ;

                }
                else if (elapsedHours != 0) {
                    timer = elapsedHours + ":" + elapsedMinutes + ":" + elapsedSeconds ;
                }
                else if(elapsedMinutes>1){
                    timer = elapsedMinutes + " minutes " + elapsedSeconds ;
                }
                else if(elapsedMinutes>0&& elapsedMinutes==1){
                    timer = "00:"+elapsedMinutes + ":" + elapsedSeconds ;
                }
                else if(elapsedMinutes==0&& elapsedSeconds>0){
                    timer =  "00:00:"+elapsedSeconds;
                }
                return timer;
              }

              else{

              clearInterval(tournament_starter);
              //   $("#ranking_table").show();
              // $("#rank_master").show();
              //   $("#rank_master_wrapper").show();
                // var div = document.getElementById('table_container');
                // div.style.visibility="visibility";
                $("#table_container").show();

                 var div = document.getElementById('table_container');
                div.style.zIndex="5";
               var div = document.getElementById('tournament_status');
                div.style.visibility="hidden";
                 var div = document.getElementById('enroll_container');
                div.style.visibility="hidden";
                var div = document.getElementById('tower_div');
                div.style.opacity="0.2";
                
          
               
              }

}



    var settime = function() { 
        
      var time = millitoDay(newMilli);
     var timerdetails= "Tournament starts in : <br>"+ time;
      $("#countdown").html(timerdetails);

    };

    function starttimer(){
 tournament_starter = setInterval(settime, 1000);
}
// setInterval(() => {
//   settime();
// }, 1000);
   

    function myFunction(){
    $("#userNotificationText").html('');
    $("#notificationinfo").hide();
    $("#noti_close").hide();
    // console.log("hit");
}