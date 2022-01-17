(function (){
    'use strict';

    angular
        .module('app')
        .controller('adminController', adminController);

    adminController.$inject=['$http'];

    function adminController($http){
        var vm = this;

        vm.transactions = [];
        vm.getClients = getClients;
        vm.getOnlyClient = getOnlyClient;
        vm.deleteClient = deleteClient;

        init();

        function init(){
            getClients();
        }

        function getClients(){
            var url = "/client/all";
            var transactionRequest = $http.get(url);
            transactionRequest.then(function(response){
                vm.transactions= response.data;
            });
        }

        function getOnlyClient(){
            var url = "/client/clients/";
            var transactionRequest = $http.get(url);
            transactionRequest.then(function(response){
                vm.transactions = response.data;
            });
        }

        function deleteClient(id){
            var url = "/client/delete/" + id;
            $http.get(url).then(function(response){
                vm.transactions = response.data;
            });
        }
    }
})();