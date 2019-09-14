// pragma solidity >=0.5.6 <0.6.0;


// contract Enroll{
    
    
//     struct playerArr{
//         address payable plrAddress;
//         uint256 affinity;
//         uint256 wizid;

//     }

//     playerArr[] public pArray;

//         address payable developer = 0xad81B0a267722a97289C86DD47B8F3eDcDd9896E;


     

//  // function joinTournament() public {
//       function joinTournament(address payable adr, uint bet,uint256 wizardId) public view returns (bool){
     
//       // Need to add wizard information..
//         playerArr memory tP = playerArr(address(0),0,0);
//         tP.plrAddress = adr;
//         tP.affinity = bet;
       
//         // get wizardId from their method.
//         tP.wizid = wizardId;
//         // tP.timestamp = now;
       
//     // CheckForValidWizard {
//         pArray.push(tP);
//         // }

//        return true;
//     }
    
    
// }

// pragma solidity >=0.5.6 <0.6.0;

// contract Enroll {

 
//  string fName;
//  uint age;
 
//  function setInstructor(string _fName, uint _age) public {
//    fName = _fName;
//    age = _age;
//  }
 
//  function getInstructor() public constant returns (string, uint) {
//    return (fName, age);
//  }
 

// }


pragma solidity >=0.5.6 <0.6.0;


contract Enroll{
    
    
    struct playerArr{
        address plrAddress;
        uint256 affinity;
        uint256 wizid;

    }
    address payable developer = 0xad81B0a267722a97289C86DD47B8F3eDcDd9896E;

    playerArr[] public pArray;


     // function joinTournament(address payable adr,uint wizardId, uint bet) public payable{


    function joinTournament(address payable adr,uint wizardId, uint bet) public payable{
     
      // Need to add wizard information..
        playerArr memory tP = playerArr(address(0),0,0);
        tP.plrAddress = adr;
        tP.affinity = bet;
       
        // get wizardId from their method.
        tP.wizid = wizardId;
        // tP.timestamp = now;
       
    // CheckForValidWizard {
        pArray.push(tP);
        // }

   //     return true;


    }

    // function joinTournament(address adr,uint bet) public view returns(bool){

    //     return true;
      
    // }
    
    
}