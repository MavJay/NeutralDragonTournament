pragma solidity >=0.5.6 <0.6.0;

contract NeutralDragonTournament{
struct tournamentPlayers{
        address payable player;
        uint256 wizardId;
        uint256 affinityType;
}
tournamentPlayers[] private tPlayers;
uint public totalNOfParticipants;
address payable private developer;
event playerJoiningEvent(string status,uint count);
event prizeAmount(uint prize1,uint prize2,uint tableTopScorer,uint developerCommission);
  constructor() public{
        developer = msg.sender;
        totalNOfParticipants = 0;
    }
    function add(uint256 a, uint256 b) internal pure returns (uint256) {
      uint256 c = a + b;
      require(c >= a);

      return c;
    }


   function checkPlayerExists(address playerAddress) public view returns(bool){
        for(uint256 i = 0; i < tPlayers.length; i++){
           if(tPlayers[i].player == playerAddress){ return true;
             }
        }
        return false;

}
    function joinTournament(address payable adr,uint256 wizardId,uint affinityType) public payable{
    tournamentPlayers memory tP = tournamentPlayers(address(0),0,0);
        tP.player = adr;
        tP.wizardId = wizardId;
        tP.affinityType = affinityType;
        tPlayers.push(tP);
        totalNOfParticipants++;
        emit playerJoiningEvent("joined",totalNOfParticipants);
    }

    function roundFixture(address[] memory wizardAddress) public view returns(address[] memory,address[] memory) {
 
    require(wizardAddress.length > 0);
    uint fixtureLength = wizardAddress.length;
    address[] memory match_Array_Wizard1 = new address[](fixtureLength/2);
    address[] memory match_Array_Wizard2 = new address[](fixtureLength/2);
   
    for (uint i=0;i<fixtureLength/2;i++) {
        match_Array_Wizard1[i] = wizardAddress[i];
        match_Array_Wizard2[i] = wizardAddress[fixtureLength-1-i];
        }
   
    return(match_Array_Wizard1,match_Array_Wizard2);
    }

function resendJoiningFee(address playerAdress) public payable{
          require(playerAdress == msg.sender);
         developer.transfer(0.1 ether);//Transfer 0.1 amount in wei
}
 function distributePrize(address payable wizard1Address,address payable wizard2Address,address payable wizard3Address)  public payable
{
    uint256 totalFeesCollected = totalNOfParticipants*100000000000000000;
    uint256 totalPrizeMoney = totalFeesCollected*90/100;
    uint256 developerCommission = totalFeesCollected*10/100;
    uint256 prize1 = totalPrizeMoney*60/100;
    uint256 prize2 = totalPrizeMoney*30/100;
    uint256 tableTopScorer = totalPrizeMoney*10/100;
     address payable[] memory winAddrs = new address payable[](4);
    winAddrs[0] = wizard1Address;
    winAddrs[1] = wizard2Address;
    winAddrs[2] = wizard3Address;
    winAddrs[3] = developer;
    uint256[] memory prizeAmtAry = new uint256[](4);
    prizeAmtAry[0] = prize1;
    prizeAmtAry[1] = prize2;
    prizeAmtAry[2] = tableTopScorer;
    prizeAmtAry[3] = developerCommission;
   
    for(uint i=0;i<=3;i++)
    {
require(winAddrs[i] == msg.sender);
winAddrs[i].transfer(prizeAmtAry[i]);
    }
   //  wizard1Address.transfer(prize1);
   //  wizard2Address.transfer(prize2);
   //  wizard3Address.transfer(tableTopScorer);
   //  developer.transfer(developerCommission);
   emit prizeAmount(prize1,prize2,tableTopScorer,developerCommission);
}
}  