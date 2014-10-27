'use strict';

moneyApp.controller('BudgetController', function ($scope, resolvedBudget, Budget, resolvedOperationCategorie) {

        $scope.budgets = resolvedBudget;
        $scope.OperationCategories = resolvedOperationCategorie;

        $scope.create = function () {
            Budget.save($scope.budget,
                function () {
                    $scope.budgets = Budget.query();
                    $('#saveBudgetModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.budget = Budget.get({id: id});
            $('#saveBudgetModal').modal('show');
        };

        $scope.delete = function (id) {
            Budget.delete({id: id},
                function () {
                    $scope.budgets = Budget.query();
                });
        };

        $scope.clear = function () {
            $scope.budget = {description: null, dateDebut: null, dateFin: null, montant: null, id: null};
        };
    });
