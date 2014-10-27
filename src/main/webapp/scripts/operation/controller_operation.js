'use strict';

moneyApp.controller('OperationController', function ($scope, resolvedOperation, Operation, resolvedOperationCategorie) {

        $scope.operations = resolvedOperation;
        $scope.OperationCategories = resolvedOperationCategorie;

        $scope.page = 0;
        $scope.size = 20;
        
        $scope.fetchNext = function(){
        	$scope.page = $scope.page + 1;
        	Operation.query({page: $scope.page, size: $scope.size}, function(operations) {
    	        $scope.operations = operations;
    	    });
        };
        
        $scope.fetchPrevious = function(){
        	if($scope.page <= 0){
        		$scope.page = 0;
        	}else{
        		$scope.page = $scope.page -1;
        	}
        	
        	$scope.operations = Operation.query({page: $scope.page, size: $scope.size}, function(operations) {
    	        $scope.operations = operations;
    	    });
        };
        
        $scope.create = function () {
            Operation.save($scope.operation,
                function () {
                    $scope.operations = Operation.query();
                    $('#saveOperationModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.operation = Operation.get({id: id});
            $('#saveOperationModal').modal('show');
        };

        $scope.delete = function (id) {
            Operation.delete({id: id},
                function () {
                    $scope.operations = Operation.query();
                });
        };

        $scope.clear = function () {
            $scope.operation = {description: null, dateOperation: null, montant: null, id: null};
        };
    });
