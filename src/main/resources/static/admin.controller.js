(function (){
    'use strict';

    angular
        .module('app')
        .controller('adminController', adminController);

    adminController.$inject=['$http'];

    function adminController($http){
        var vm = this;

        vm.transactions = [];
        vm.getAll = getAll;
        vm.getRecentTransactions = getRecentTransactions;
        vm.deleteTransaction = deleteTransaction;


        init();

        function init(){
            getAll();

        }

        function getAll(){
            var url = "/transactions/all";
            var transactionRequest = $http.get(url);
            transactionRequest.then(function(response){
                vm.transactions = response.data;
            });
        }

        function getRecentTransactions(){
            var url = "/transactions/recent/" + 10;
            var transactionRequest = $http.get(url);
            transactionRequest.then(function(response){
                vm.transactions = response.data;
            });
        }

        function deleteTransaction(id){
            var url = "/transactions/delete/" + id;
            $http.get(url).then(function(response){
               vm.transactions = response.data;
            });
        }






    }
})();