module.exports = {
  // See <http://truffleframework.com/docs/advanced/configuration>
  // for more about customizing your Truffle configuration!
  networks: {
    development: {
      host: "127.0.0.1",
      port: 8545,
      network_id: "*" // Match any network id
    },
    develop: {
      port: 8545
    }
  }
};



// // ÷]d667044b653e46218e8d7081594a84ec



// require('dotenv').config()
// var HDWalletProvider = require("truffle-hdwallet-provider");
// var mnemonic = process.env["MNENOMIC"];
// var tokenKey = process.env["INFURA_API_KEY"];
// const infuraKey = "d667044b653e46218e8d7081594a84ec";
// module.exports = {
//   // See <http://truffleframework.com/docs/advanced/configuration>
//   // for more about customizing your Truffle configuration!
//   networks: {
//     development: {
//       host: "127.0.0.1",
//       port: 7545,
//       network_id: "*" // Match any network id
//     },
//     ropsten: {
//       provider: () => new HDWalletProvider(mnemonic,`https://ropsten.infura.io/v3/${infuraKey}`),
//        network_id: 3,
//        gas: 5000000,
//        confirmation: 2,
//        timeoutBlocks: 200,
//        //skipDryRun: true
//        //host: '127.0.0.1',
//        //port: 8545,
//      },
//      rinkeby: {
//        provider: () => new HDWalletProvider(mnemonic,`https://rinkeby.infura.io/v3/${infuraKey}`),
//         network_id: 4,
//         gas: 4700000,
//         gasPrice: 100000000000
//       },
//       live:{
//         provider: () => new HDWalletProvider(mnemonic,`https://mainnet.infura.io/v3/${infuraKey}`),
//          network_id: 4,
//          gas: 4700000,
//          gasPrice: 100000000000
//       }
//   },
//   solc: {
//     optimizer: {
//       enabled: true,
//       runs: 200
//     }
//   }

// };
