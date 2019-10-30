pragma solidity >=0.5.6 <0.6.0;

contract NeutralDragonTournament{
struct tournamentPlayers{
        address payable player;
        uint256 wizardId;
        uint256 affinityType;
}
tournamentPlayers[] public tPlayers;
uint totalNOfParticipants;
address payable developer;
uint256 admissionDuration = 120/15;
uint levelDuration = 60/15;
uint endTimer;
uint256 blockNumber;
uint levelCount = 0;
event player_count_event(uint count);
event eliminatedWizard(address adr,uint wizardId);
event playerJoiningEvent(string status,uint count);
/* event match_fixture(address[] matchFixture1,address[] matchFixture2); */
event levelNumber(uint levelCount);
event levelWinner(uint currentLevelCount,uint currentMatchCount,address winner);
event prizeAmount(uint prize1,uint prize2,uint tableTopScorer,uint developerCommission);
 event admissionTimeDuration(uint duration);

  constructor() public{
        developer = msg.sender;
        totalNOfParticipants = 0;
        blockNumber = block.number;
        /* tournamentStartTimer(); */
    }
    function add(uint256 a, uint256 b) internal pure returns (uint256) {
      uint256 c = a + b;
      require(c >= a);

      return c;
    }

function tournamentStartTimer() public view returns(uint256){
        require(blockNumber > 0);
        require(admissionDuration >= 0);
         uint256 timer = add(blockNumber,admissionDuration);
         /* emit admissionTimeDuration(timer); */
         return timer;
    }

    function tournamentEndTimer(uint startDuration) public view returns(uint256){
        require(startDuration > 0);
        require(admissionDuration >= 0);
             uint256 timer = add(startDuration,admissionDuration);
             /* emit admissionTimeDuration(timer); */
             return timer;
        }

function levelTimer(uint256 levelStartDuration) public view returns(uint256){
    require(levelStartDuration >= 0);
    uint256 timer = add(levelStartDuration,levelDuration);
    return timer;
}

   function checkPlayerExists(address playerAddress) public view returns(bool){
        for(uint256 i = 0; i < tPlayers.length; i++){
           if(tPlayers[i].player == playerAddress){ return true;
             }
        }
        return false;

}
function joinTournament(address payable adr,uint256 wizardId,uint affinityType) public payable{
    /* require(block.number<=endTimer,"Joining time exceeds"); */
    tournamentPlayers memory tP = tournamentPlayers(address(0),0,0);
        tP.player = adr;
        tP.wizardId = wizardId;
        tP.affinityType = affinityType;
        tPlayers.push(tP);
        totalNOfParticipants++;
        emit playerJoiningEvent("joined",totalNOfParticipants);
}

function wizardEliminated(address adr,uint wizardId) public{
    emit eliminatedWizard(adr,wizardId);
}

function roundFixture(address[] memory wizardAddress) public view returns(address[] memory,address[] memory) {
    /* uint tempLevelCount = levelCount; */
    uint fixtureLength = wizardAddress.length;
         address[] memory match_Array_Wizard1 = new address[](fixtureLength/2);
    address[] memory match_Array_Wizard2 = new address[](fixtureLength/2);
    for (uint i=0;i<fixtureLength/2;i++) {
        match_Array_Wizard1[i] = wizardAddress[i];
        match_Array_Wizard2[i] = wizardAddress[fixtureLength-1-i];
        }
    /* emit match_fixture(match_Array_Wizard1,match_Array_Wizard2); */
    /* emit levelNumber(tempLevelCount); */
    return(match_Array_Wizard1,match_Array_Wizard2);
    }

function resendJoiningFee(address playerAdress) public payable{
          //require(playerAdress == msg.sender);
          playerAdress = msg.sender;
          msg.sender.transfer(100000000000000000);//Transfer 0.1 amount in wei
}

 function startDuel(uint[] memory wizard1MoveSpells,uint[] memory wizard2MoveSpells) public view returns(uint[] memory,uint[] memory,uint[] memory){
  uint256[] memory wizard1RoundStatus = new uint256[](5);
   uint256[] memory wizard2RoundStatus = new uint256[](5);
   uint256[] memory wizardRoundTie = new uint256[](2);
      for(uint i=0;i<5;i++){
                    if ((wizard1MoveSpells[i]-wizard2MoveSpells[i]) == 1 || (wizard1MoveSpells[i]-wizard2MoveSpells[i]) == uint(-2)) {
                         wizard1RoundStatus[i] = 1;
                         wizard2RoundStatus[i] = 0;

                    } else if ((wizard1MoveSpells[i]-wizard2MoveSpells[i]) == uint(-1) || (wizard1MoveSpells[i]-wizard2MoveSpells[i]) == 2) {
                         wizard2RoundStatus[i] = 1;
                         wizard1RoundStatus[i] = 0;
                    }else{
                        wizardRoundTie[0] = wizardRoundTie[0]+1;
                        wizardRoundTie[1] = wizardRoundTie[1]+1;
                    }
}
return(wizardRoundTie,wizard1RoundStatus,wizard2RoundStatus);
}

function getWinner(address wizard1,address wizard2,uint256 wizard1RoundWin,uint256 wizard2RoundWin,uint256 wizard1LevelScore,uint256 wizard2LevelScore,uint256 wizard1TimeStamp,uint256 wizard2TimeStamp,uint256 currentLevelCount,uint256 currentMatchCount) public{
    address winner;
        if(wizard1RoundWin > wizard2RoundWin){
            winner = wizard1;
        } else if(wizard1RoundWin < wizard2RoundWin){
             winner = wizard2;
        } else {
            if(wizard1LevelScore > wizard2LevelScore){
            winner = wizard1;
            } else if(wizard1LevelScore < wizard2LevelScore){
             winner = wizard2;
            } else{
                if(wizard1TimeStamp > wizard2TimeStamp){
                     winner = wizard1;
                } else if(wizard1TimeStamp < wizard2TimeStamp){
                     winner = wizard2;
                }
            }
        }
        emit levelWinner(currentLevelCount,currentMatchCount,winner);
    }

 function distributePrize(address payable wizard1Address,address payable wizard2Address,address payable wizard3Address)  public payable{
     uint256 totalFeesCollected = totalNOfParticipants*100000000000000000;
     uint256 totalPrizeMoney = totalFeesCollected*90/100;
     uint256 developerCommission = totalFeesCollected*10/100;
     uint256 prize1 = totalPrizeMoney*60/100;
     uint256 prize2 = totalPrizeMoney*30/100;
     uint256 tableTopScorer = totalPrizeMoney*10/100;
     wizard1Address.transfer(prize1);
     wizard2Address.transfer(prize2);
     wizard3Address.transfer(tableTopScorer);
     developer.transfer(developerCommission);
    emit prizeAmount(prize1,prize2,tableTopScorer,developerCommission);
}


}
