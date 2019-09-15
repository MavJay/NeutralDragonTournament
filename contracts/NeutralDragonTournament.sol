pragma solidity >=0.5.6 <0.6.0;

contract NeutralDragonTournament{

    // global variables to store winCount, winnerScore for 2 players, level count
    uint256 wizard1LevelWin;
    uint256 wizard2LevelWin;
    uint256 wizard1LevelScore;
    uint256 wizard2LevelScore;
    uint256 wizard1Affinity;
    uint256 wizard2Affinity;
    uint256 wizard1TimeStamp;
    uint256 wizard2TimeStamp;
    uint256 wizard1RoundWin;
    uint256 wizard2RoundWin;
    uint256 wizard1RoundLoss;
    uint256 wizard2RoundLoss;
    uint256 wizard1RoundScore;
    uint256 wizard2RoundScore;
    uint256 wizard1ElementalWin;
    uint256 wizard1ElementalLoss;
    uint256 wizard2ElementalWin;
    uint256 wizard2ElementalLoss;
    uint256 wizard1WinAgainstElemental;
    uint256 wizard1LossAgainstElemental;
    uint256 wizard2WinAgainstElemental;
    uint256 wizard2LossAgainstElemental;
    uint256 wizard1RoundTie;
    uint256 wizard2RoundTie;
    uint256 levelNum = 0;
    uint256 totalOfParticipants;
    uint256 betAmt = 100;
    uint addmissionDuration = 60/15;
    uint endTimer;
uint constant public a =1;
   address payable developer; // to receive commission and owner of the contract.
   // Get wizard moves placed by player
    // Store winner array address
    struct finalMatchArray {
      address playerAddress;
    }
    // struct to store score of wizards( players)
    struct scoreArr{
      address plrAddress;
      uint256 totalScore;
      uint256 plrStatus;
      uint256 noOfWins;
      uint256 noOfLoss;
      uint256 noOftie;
      uint256 elementalWin;
      uint256 elementalLoss;
      uint256 noOfWinAgainstElemental;
       uint256 noOfLossAgainstElemental;
    }
    // struct for match making..
    struct matchArray {
      address player1Address;
      address player2Address;
}
    //Struct to hold player details..
    struct tournamentPlayers{
        address payable player;
        uint256 wizardId;
        uint bet;
        uint256 timestamp;
        uint256 affinityType;
        uint256 wizardSpell1;
        uint256 wizardSpell2;
        uint256 wizardSpell3;
        uint256 wizardSpell4;
        uint256 wizardSpell5;
        bool winStatus;
    }


    //Player struct array to hold players details
    tournamentPlayers[] public tPlayers;
    finalMatchArray[] public finals;
    matchArray[] public matchCombinations;
    tournamentPlayers[] public bPlayers;
    scoreArr[] public resultScores;
    // to fix tournament start date
   
    event player_count_event(uint count); // Need to check
    event returnData(uint256[]);
    event addmissionTimeDuration(uint);
    event levelTimer(uint);
    // event checkValidUser(bool);

    constructor() public{
        developer = msg.sender;
        tournamentTimer();
    }
    function tournamentTimer() public{
         endTimer = block.number+addmissionDuration;
         emit addmissionTimeDuration(endTimer);
    }
    function joinTournament(address payable adr,uint256 wizardId, uint bet, uint affinityType) public payable{

      // Need to add wizard information..
        tournamentPlayers memory tP = tournamentPlayers(address(0),0,0,0,0,0,0,0,0,0,true);
        tP.player = adr;
        tP.bet = bet;
        tp.affinityType = affinityType;
        // get wizardId from their method.
        tP.wizardId = wizardId;
        tP.timestamp = now;

    // CheckForValidWizard {
        tPlayers.push(tP);
        emit player_count_event(tPlayers.length);
        // }
    }

    function roundFixtures() public  {
        levelNum++;
        totalOfParticipants = tPlayers.length;
        if(levelNum >=2){
            for(uint i =0;i<bPlayers.length;i++){
                tournamentPlayers memory tP = tournamentPlayers(address(0),0,0,0,0,0,0,0,0,0,true);
        tP.player =  bPlayers[i].player;
        tP.wizardId = bPlayers[i].wizardId;
        tP.bet = bPlayers[i].bet;
        tP.timestamp = bPlayers[i].timestamp;
        tP.affinityType = bPlayers[i].affinityType;
        tP.wizardSpell1 = bPlayers[i].wizardSpell1;
        tP.wizardSpell2 = bPlayers[i].wizardSpell2;
        tP.wizardSpell3 = bPlayers[i].wizardSpell3;
        tP.wizardSpell4 = bPlayers[i].wizardSpell4;
        tP.wizardSpell5 = bPlayers[i].wizardSpell5;
        tP.winStatus = bPlayers[i].winStatus;
        tPlayers.push(tP);
            }
            delete bPlayers;
        }
require(totalOfParticipants != 0);
        if (totalOfParticipants == a) {
        uint amnt = tPlayers[0].bet/10;
          tPlayers[0].player.transfer(amnt*1000000000000000);//Calculate with respect to 100
            // distributePrize();

       } else{

            if (totalOfParticipants%2 == 0){
                if (totalOfParticipants == 2){
                finalMatchArray memory fMA = finalMatchArray(address(0));
                for (uint i=0;i<tPlayers.length;i++){
                fMA.playerAddress = tPlayers[i].player;
                finals.push(fMA);
                }
                createMatchFixture();
                }
                else{
                   createMatchFixture();
                }
            }
            else{
                if (levelNum == 1) { //levelNum wont be 0 here
                  bPlayers.push(tPlayers[0]);
                  delete tPlayers[0];
                  createMatchFixture();
                }
                else if (levelNum >= 2){
                //   compareRankingsAndPush(); // need to write
   // compare ranking and push to round array

                }
            }

    }

}
    function createMatchFixture() public{ // add return type to emit fixture details to frontend..
    // Map Match Pair..

    matchArray memory matchCom = matchArray(address(0),address(0));
    for (uint i=0;i<tPlayers.length/2;i++) {

        // address p1Add =
        matchCom.player1Address = tPlayers[i].player;
        matchCom.player2Address = tPlayers[tPlayers.length-1-i].player;
        matchCombinations.push(matchCom);
        }
    emit player_count_event(matchCombinations.length);

    }

    function placeSpells(address adr,uint256[] memory movesets) public returns (uint256[] memory) {
        
        uint spellDuration = 24*60*60/15;
        endTimer = endTimer+spellDuration;
        emit levelTimer(endTimer);

        //need to add address with moveset..
    for (uint i=0;i<tPlayers.length;i++){
        if (tPlayers[i].player == adr) {
                tPlayers[i].wizardSpell1 = movesets[0];
                tPlayers[i].wizardSpell2 = movesets[1];
                tPlayers[i].wizardSpell3 = movesets[2];
                tPlayers[i].wizardSpell4 = movesets[3];
                tPlayers[i].wizardSpell5 = movesets[4];
        }
    }
    return movesets;

}

    function startDuel() public {
        uint256 tempMoveSet1;
        uint256 tempMoveSet2;
        address player1;
        address player2;
         scoreArr memory pScore = scoreArr(address(0),0,0,0,0,0,0,0,0,0);
        for (uint i=0;i<matchCombinations.length;i++) {
            for (uint j=0;j<tPlayers.length;j++) {
              if (tPlayers[j].player == matchCombinations[i].player1Address){
                     player1 = tPlayers[j].player;
                     tempMoveSet1 = tPlayers[j].wizardSpell1;
                } else if(tPlayers[j].player == matchCombinations[i].player2Address){
                     player2 = tPlayers[j].player;
                     tempMoveSet2 = tPlayers[j].wizardSpell1;
                }
            }
            if (tempMoveSet1 == 0 && tempMoveSet2 == 0) {
                for(uint j=0;j<tPlayers.length;j++){
                     if(tPlayers[j].player == player1){
                          pScore.plrAddress = player1;
                          resultScores.push(pScore);
                         delete tPlayers[j];
                     } else if(tPlayers[j].player == player2){
                         pScore.plrAddress = player2;
                          resultScores.push(pScore);
                         delete tPlayers[j];
                    }
                }
                    // delete matchCombinations[i];
            }else if (tempMoveSet1 == 0 && tempMoveSet2 != 0){
                for(uint j=0;j<tPlayers.length;j++){
                    if(tPlayers[j].player == player1){
                        pScore.plrAddress = player1;
                          resultScores.push(pScore);
                        delete tPlayers[j];
                     } else if(tPlayers[j].player == player2){
                         pScore.plrAddress = player2;
                         pScore.plrStatus = 1;
                          resultScores.push(pScore);
                        bPlayers.push(tPlayers[j]);
                        delete tPlayers[j];
                    }
                }
                    //  delete matchCombinations[i].player1Address;
            }else if (tempMoveSet1 != 0 && tempMoveSet2 == 0){
                for(uint j=0;j<tPlayers.length;j++){
                    if(tPlayers[j].player == player1){
                        pScore.plrAddress = player1;
                         pScore.plrStatus = 1;
                          resultScores.push(pScore);
                        bPlayers.push(tPlayers[j]);
                        delete tPlayers[j];
                    } else if(tPlayers[j].player == player2){
                        pScore.plrAddress = player2;
                          resultScores.push(pScore);
                        delete tPlayers[j];
                     }
                }
                // delete matchCombinations[i].player2Address;
            } else {
                calculateDuel(player1,player2);
            }
            roundFixtures();
        }
    }

    function calculateDuel(address wizardAdr1,address wizardAdr2) public {
        uint[] memory wizard1MoveSpells;
        uint[] memory wizard2MoveSpells;
        bool wizardFound = false;
                for(uint j=0;j<tPlayers.length;j++){
                    if(tPlayers[j].player == wizardAdr1){
                        wizard1Affinity = tPlayers[j].affinityType;
                        wizard1MoveSpells[0] = tPlayers[j].wizardSpell1;
                        wizard1MoveSpells[1] = tPlayers[j].wizardSpell2;
                        wizard1MoveSpells[2] = tPlayers[j].wizardSpell3;
                        wizard1MoveSpells[3] = tPlayers[j].wizardSpell4;
                        wizard1MoveSpells[4] = tPlayers[j].wizardSpell5;
                    } else if(tPlayers[j].player == wizardAdr2){
                        wizard2Affinity = tPlayers[j].affinityType;
                        wizard2MoveSpells[0] = tPlayers[j].wizardSpell1;
                        wizard2MoveSpells[1] = tPlayers[j].wizardSpell2;
                        wizard2MoveSpells[2] = tPlayers[j].wizardSpell3;
                        wizard2MoveSpells[3] = tPlayers[j].wizardSpell4;
                        wizard2MoveSpells[4] = tPlayers[j].wizardSpell5;
                    }
                }
                for(uint i=0;i<5;i++){
                    if ((wizard1MoveSpells[i]-wizard2MoveSpells[i]) == 1 || (wizard1MoveSpells[i]-wizard2MoveSpells[i]) == uint(-2)) {
                         wizard1RoundWin++;
                         wizard2RoundLoss++;
                         if(wizard1MoveSpells[i] == wizard1Affinity && wizard2MoveSpells[i] == wizard2Affinity){
                            wizard1RoundScore = 5;
                            wizard2RoundScore = uint(-5);
                            wizard1LevelScore = wizard1LevelScore+wizard1RoundScore;
                            wizard2LevelScore = wizard2LevelScore+wizard2RoundScore;
                            wizard1ElementalWin = 1;
                            wizard1WinAgainstElemental = 1;
                            wizard2ElementalLoss = 1;
                            wizard2LossAgainstElemental = 1;

                         } else if (wizard1MoveSpells[i] == wizard1Affinity && wizard2MoveSpells[i] != wizard2Affinity){
                            wizard1RoundScore = 3;
                            wizard2RoundScore = uint(-2);
                            wizard1LevelScore = wizard1LevelScore+wizard1RoundScore;
                            wizard2LevelScore = wizard2LevelScore+wizard2RoundScore;
                            wizard1ElementalWin = 1;
                            wizard2LossAgainstElemental = 1;
                         } else if (wizard1MoveSpells[i] != wizard1Affinity && wizard2MoveSpells[i] == wizard2Affinity){
                            wizard1RoundScore = 2;
                            wizard2RoundScore = uint(-2);
                            wizard1LevelScore = wizard1LevelScore+wizard1RoundScore;
                            wizard2LevelScore = wizard2LevelScore+wizard2RoundScore;
                            wizard1WinAgainstElemental = 1;
                            wizard2ElementalLoss = 1;
                         } else if (wizard1MoveSpells[i] != wizard1Affinity && wizard2MoveSpells[i] != wizard2Affinity){
                            wizard1RoundScore = 1;
                            wizard2RoundScore = uint(-1);
                            wizard1LevelScore = wizard1LevelScore+wizard1RoundScore;
                            wizard2LevelScore = wizard2LevelScore+wizard2RoundScore;
                        }

                    } else if ((wizard1MoveSpells[i]-wizard2MoveSpells[i]) == uint(-1) || (wizard1MoveSpells[i]-wizard2MoveSpells[i]) == 2) {
                        wizard2RoundWin++;
                        wizard1RoundLoss++;
                        if(wizard1MoveSpells[i] == wizard1Affinity && wizard2MoveSpells[i] == wizard2Affinity){
                            wizard1RoundScore = uint(-5);
                            wizard2RoundScore = 5;
                            wizard1LevelScore = wizard1LevelScore+wizard1RoundScore;
                            wizard2LevelScore = wizard2LevelScore+wizard2RoundScore;
                             wizard2ElementalWin = 1;
                            wizard2WinAgainstElemental = 1;
                            wizard1ElementalLoss = 1;
                            wizard1LossAgainstElemental = 1;
                         } else if (wizard1MoveSpells[i] == wizard1Affinity && wizard2MoveSpells[i] != wizard2Affinity){
                            wizard1RoundScore = uint(-3);
                            wizard2RoundScore = 2;
                            wizard1LevelScore = wizard1LevelScore+wizard1RoundScore;
                            wizard2LevelScore = wizard2LevelScore+wizard2RoundScore;
                            wizard1ElementalLoss = 1;
                            wizard2WinAgainstElemental = 1;
                         } else if (wizard1MoveSpells[i] != wizard1Affinity && wizard2MoveSpells[i] == wizard2Affinity){
                            wizard1RoundScore = uint(-2);
                            wizard2RoundScore = 3;
                            wizard1LevelScore = wizard1LevelScore+wizard1RoundScore;
                            wizard2LevelScore = wizard2LevelScore+wizard2RoundScore;
                            wizard1LossAgainstElemental = 1;
                            wizard2ElementalWin = 1;
                         } else if (wizard1MoveSpells[i] != wizard1Affinity && wizard2MoveSpells[i] != wizard2Affinity){
                            wizard1RoundScore = uint(-1);
                            wizard2RoundScore = 1;
                            wizard1LevelScore = wizard1LevelScore+wizard1RoundScore;
                            wizard2LevelScore = wizard2LevelScore+wizard2RoundScore;
                        }
                    }else{
                        wizard1RoundTie = 1;
                        wizard2RoundTie = 1;
                    }
                    // /updateScore(win1,win2,w1Score,w2Score,player1Address,player2Address);
                    if(resultScores.length == 0){
                      scoreArr memory wizard1Score = scoreArr(address(0),0,0,0,0,0,0,0,0,0);
                        wizard1Score.plrAddress = wizardAdr1;
                        wizard1Score.totalScore = wizard1RoundScore;
                        wizard1Score.plrStatus = 1;
                        wizard1Score.noOfWins = wizard1RoundWin;
                        wizard1Score.noOfLoss = wizard1RoundLoss;
                        wizard1Score.noOftie = wizard1RoundTie;
                        wizard1Score.elementalWin = wizard1ElementalWin;
                        wizard1Score.elementalLoss = wizard1ElementalLoss;
                        wizard1Score.noOfWinAgainstElemental = wizard1WinAgainstElemental;
                        wizard1Score.noOfLossAgainstElemental = wizard1LossAgainstElemental;
                        resultScores.push(wizard1Score);
                      scoreArr memory wizard2Score = scoreArr(address(0),0,0,0,0,0,0,0,0,0);
                        wizard2Score.plrAddress = wizardAdr2;
                        wizard2Score.totalScore = wizard2RoundScore;
                        wizard2Score.plrStatus = 1;
                        wizard2Score.noOfWins = wizard2RoundWin;
                        wizard2Score.noOfLoss = wizard2RoundLoss;
                        wizard2Score.noOftie = wizard2RoundTie;
                        wizard2Score.elementalWin = wizard2ElementalWin;
                        wizard2Score.elementalLoss = wizard2ElementalLoss;
                        wizard2Score.noOfWinAgainstElemental = wizard2WinAgainstElemental;
                        wizard2Score.noOfLossAgainstElemental = wizard2LossAgainstElemental;
                        resultScores.push(wizard2Score);

                    } else {
                         for(uint256 i=0;i<resultScores.length;i++){
                        if(resultScores[i].plrAddress == wizardAdr1){
                        resultScores[i].totalScore = resultScores[i].totalScore+wizard1RoundScore;
                        resultScores[i].plrStatus = 1;
                        resultScores[i].noOfWins = resultScores[i].noOfWins+wizard1RoundWin;
                        resultScores[i].noOfLoss = resultScores[i].noOfLoss+wizard1RoundLoss;
                        resultScores[i].noOftie = resultScores[i].noOftie+wizard1RoundTie;
                        resultScores[i].elementalWin = resultScores[i].elementalWin+wizard1ElementalWin;
                        resultScores[i].elementalLoss = resultScores[i].elementalLoss+wizard1ElementalLoss;
                        resultScores[i].noOfWinAgainstElemental = resultScores[i].noOfWinAgainstElemental+wizard1WinAgainstElemental;
                        resultScores[i].noOfLossAgainstElemental = resultScores[i].noOfLossAgainstElemental+wizard1LossAgainstElemental;
                        wizardFound = true;
                        } else if(resultScores[i].plrAddress == wizardAdr2){
                        resultScores[i].totalScore = resultScores[i].totalScore+wizard2RoundScore;
                        resultScores[i].plrStatus = 1;
                        resultScores[i].noOfWins = resultScores[i].noOfWins+wizard2RoundWin;
                        resultScores[i].noOfLoss = resultScores[i].noOfLoss+wizard2RoundLoss;
                        resultScores[i].noOftie = resultScores[i].noOftie+wizard2RoundTie;
                        resultScores[i].elementalWin = resultScores[i].elementalWin+wizard2ElementalWin;
                        resultScores[i].elementalLoss = resultScores[i].elementalLoss+wizard2ElementalLoss;
                        resultScores[i].noOfWinAgainstElemental = resultScores[i].noOfWinAgainstElemental+wizard2WinAgainstElemental;
                        resultScores[i].noOfLossAgainstElemental = resultScores[i].noOfLossAgainstElemental+wizard2LossAgainstElemental;
                        wizardFound = true;
                        }
                    }
                    if(!wizardFound){
                         scoreArr memory wizard1Score = scoreArr(address(0),0,0,0,0,0,0,0,0,0);
                        wizard1Score.plrAddress = wizardAdr1;
                        wizard1Score.totalScore = wizard1RoundScore;
                        wizard1Score.plrStatus = 1;
                        wizard1Score.noOfWins = wizard1RoundWin;
                        wizard1Score.noOfLoss = wizard1RoundLoss;
                        wizard1Score.noOftie = wizard1RoundTie;
                        wizard1Score.elementalWin = wizard1ElementalWin;
                        wizard1Score.elementalLoss = wizard1ElementalLoss;
                        wizard1Score.noOfWinAgainstElemental = wizard1WinAgainstElemental;
                        wizard1Score.noOfLossAgainstElemental = wizard1LossAgainstElemental;
                        resultScores.push(wizard1Score);
                      scoreArr memory wizard2Score = scoreArr(address(0),0,0,0,0,0,0,0,0,0);
                        wizard2Score.plrAddress = wizardAdr2;
                        wizard2Score.totalScore = wizard2RoundScore;
                        wizard2Score.plrStatus = 1;
                        wizard2Score.noOfWins = wizard2RoundWin;
                        wizard2Score.noOfLoss = wizard2RoundLoss;
                        wizard2Score.noOftie = wizard2RoundTie;
                        wizard2Score.elementalWin = wizard2ElementalWin;
                        wizard2Score.elementalLoss = wizard2ElementalLoss;
                        wizard2Score.noOfWinAgainstElemental = wizard2WinAgainstElemental;
                        wizard2Score.noOfLossAgainstElemental = wizard2LossAgainstElemental;
                        resultScores.push(wizard2Score);
                        wizardFound = false;
                    }

                    }

                }
                getWinner(wizardAdr1,wizardAdr2,wizard1RoundWin,wizard1RoundWin,wizard1LevelScore,wizard2LevelScore,wizard1TimeStamp,wizard2TimeStamp);
    }

    function getWinner(address wizard1,address wizard2,uint256 wizard1RoundWin,uint256 wizard2RoundWin,uint256 wizard1LevelScore,uint256 wizard2LevelScore,uint256 wizard1TimeStamp,uint256 wizard2TimeStamp) public{
        if(wizard1RoundWin > wizard2RoundWin){
            for(uint i=0;i<tPlayers.length;i++){
                if(tPlayers[i].player == wizard2){
                    delete tPlayers[i];
                }
            }
        } else if(wizard1RoundWin < wizard2RoundWin){
            for(uint i=0;i<tPlayers.length;i++){
                if(tPlayers[i].player == wizard1){
                    delete tPlayers[i];
                }
            }
        } else {
            if(wizard1LevelScore > wizard2LevelScore){
            for(uint i=0;i<tPlayers.length;i++){
                if(tPlayers[i].player == wizard2){
                    delete tPlayers[i];
                }
            }
            } else if(wizard1LevelScore < wizard2LevelScore){
            for(uint i=0;i<tPlayers.length;i++){
                if(tPlayers[i].player == wizard1){
                    delete tPlayers[i];
                }
            }
            } else{
                if(wizard1TimeStamp > wizard2TimeStamp){
                    for(uint i=0;i<tPlayers.length;i++){
                         if(tPlayers[i].player == wizard2){
                             delete tPlayers[i];
                        }
                    }
                } else if(wizard1TimeStamp < wizard2TimeStamp){
                    for(uint i=0;i<tPlayers.length;i++){
                         if(tPlayers[i].player == wizard2){
                             delete tPlayers[i];
                        }
                    }
                }
            }
        }
        wizard1LevelScore = 0;
        wizard2LevelScore = 0;
        wizard1LevelWin = 0;
        wizard2LevelWin = 0;
        wizard1RoundWin = 0;
        wizard2RoundWin = 0;

    }

    function compareRankingsAndPush() public{
        
        uint256[] memory resultScoreAry;
        for (uint i = 0;i<resultScores.length;i++){
            resultScoreAry[i] = resultScores[i].totalScore;
        }
        
        sortScoreAndRank(resultScoreAry, int(0), int(resultScoreAry.length - 1));
         emit returnData(resultScoreAry);
        // createMatchFixture();
    }
    
    function sortScoreAndRank(uint256[] memory arr, int left, int right) internal{
        int i = left;
        int j = right;
        if(i==j) return;
        uint pivot = arr[uint(left + (right - left) / 2)];
        while (i <= j) {
            while (arr[uint(i)] < pivot) i++;
            while (pivot < arr[uint(j)]) j--;
            if (i <= j) {
                (arr[uint(i)], arr[uint(j)]) = (arr[uint(j)], arr[uint(i)]);
                i++;
                j--;
            }
        }
        if (left < j)
            sortScoreAndRank(arr, left, j);
        if (i < right)
            sortScoreAndRank(arr, i, right);
    }
    
    
   function distributePrize()  public payable{
    
     uint256 totalFeesCollected = totalOfParticipants*betAmt;
     uint256 totalPrizeMoney = totalFeesCollected*90/100;
     uint256 developerCommission = totalFeesCollected*10/100;
     uint256 prize1 = totalPrizeMoney*60/100;
     uint256 prize2 = totalPrizeMoney*30/100;
     uint256 tableTopScorer = totalPrizeMoney*10/100;
    
     address payable adr; 
     address payable adr1;
     address payable tableTopScorerAdr;
     //   uint commision = (100*10)/100;
              //transfer to developer
              if (tPlayers.length == 0) {
                adr = address(uint160(tPlayers[0].player)); 
                adr.transfer(developerCommission*100000000000000000);
              }
              else {
                  adr = address(uint160(tPlayers[0].player));
                  adr.transfer(prize1*100000000000000000);
                  adr1 = address(uint160(tPlayers[1].player));
                  adr1.transfer(prize2*100000000000000000);
                  tableTopScorerAdr = address(uint160(resultScores[0].plrAddress));
                  tableTopScorerAdr.transfer(tableTopScorer*100000000000000000);
              }
              
                    for(uint i=0;i<finals.length;i++){
                             delete finals[i].playerAddress;
                        }
       //  delete finalMatchArray;
}


}
