(function (){
    'use strict';

    angular
        .module('app')
        .controller('clientController', clientController);

    clientController.$inject=['$http'];

    function clientController($http){
        var vm = this;

        vm.transactions = [];
        //vm.clients=[]
        vm.getAll = getAll;
        vm.getRecentTransactions = getRecentTransactions;
        //vm.isBuy = isBuy(vm.transactions.txType);
        //vm.isSell = isSell(vm.transactions.txType);
        //vm.deleteTransaction = deleteTransaction;


        init();

        function init(){
            getRecentTransactions();

        }

        function isBuy(x){

            if(x.equals('BUY')){
                return true;
            }else{
                return false;
            }

        }

        function isSell(x){
            if(x.equals('SELL')){
                return true;
            }else{
                return false;
            }
        }

        function getAll(){
            var url = "/transactions/client/transactions";
            var transactionRequest = $http.get(url);
            transactionRequest.then(function(response){
                vm.transactions = response.data;
            });
        }

        function getRecentTransactions(){
            var url = "transactions/client/transactions/" + 10;
            var transactionRequest = $http.get(url);
            transactionRequest.then(function(response){
                vm.transactions = response.data;
            });
        }


    }
})();