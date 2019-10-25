// module.exports = {
//   // See <http://truffleframework.com/docs/advanced/configuration>
//   // for more about customizing your Truffle configuration!
//   networks: {
//     development: {
//       host: "127.0.0.1",
//       port: 8545,
//       network_id: "*" // Match any network id
//     },
//     develop: {
//       port: 8545
//     }
//   }
// };

// const HDWalletProvider = require('truffle-hdwallet-provider');
// // DOCS: https://github.com/trufflesuite/truffle-hdwallet-provider

// module.exports = {
//     networks: {
//         rinkeby: {
//             provider: () => new HDWalletProvider([process.env.INFURA_SECRET], https://rinkeby.infura.io/v3/[INFURA_PROJECT_ID], 0, 1),
//             network_id: 4,
//             gas: 5500000,        // Rinkeby has a lower block limit than mainnet
//             confirmations: 2,    // # of confs to wait between deployments. (default: 0)
//             timeoutBlocks: 200,  // # of blocks before a deployment times out  (minimum/default: 50)
//             skipDryRun: true     // Skip dry run before migrations? (default: false for public nets )
//         }
//     }
// }


// require('dotenv').config()
var HDWalletProvider = require("truffle-hdwallet-provider");
var mnemonic = process.env["MNENOMIC"];
var tokenKey = process.env["INFURA_API_KEY"];
const infuraKey = "d667044b653e46218e8d7081594a84ec";
module.exports = {
  // See <http://truffleframework.com/docs/advanced/configuration>
  // for more about customizing your Truffle configuration!
  networks: {
    development: {
      host: "127.0.0.1",
      port: 8545,
      network_id: "*", // Match any network id
        gas: 4700000,
        gasPrice: 100000000000,
        gas: 5500000,        
        // confirmations: 2,    
        // timeoutBlocks: 200,  
        // skipDryRun: true 
    },
    ropsten: {
      provider: () => new HDWalletProvider(mnemonic,`https://ropsten.infura.io/v3/${infuraKey}`),
       network_id: 3,
       gas: 5000000,
       confirmation: 2,
       timeoutBlocks: 200,
       //skipDryRun: true
       //host: '127.0.0.1',
       //port: 8545,
     },
     rinkeby: {
       provider: () => new HDWalletProvider(mnemonic,`https://rinkeby.infura.io/v3/${infuraKey}`,0,1),
        network_id: 4,
        gas: 4700000,
        gasPrice: 100000000000,
        gas: 5500000,        
        // confirmations: 2,    
        // timeoutBlocks: 200,  
        // skipDryRun: true  
      },
      // live:{
      //   provider: () => new HDWalletProvider(mnemonic,`https://mainnet.infura.io/v3/${infuraKey}`),
      //    network_id: 4,
      //    gas: 4700000,
      //    gasPrice: 100000000000
      // }
  },
  solc: {
    optimizer: {
      enabled: true,
      runs: 200
    }
  }

};
