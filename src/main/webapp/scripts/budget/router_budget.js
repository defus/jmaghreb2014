'use strict';

moneyApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/budget', {
                    templateUrl: 'views/budgets.html',
                    controller: 'BudgetController',
                    resolve:{
                        resolvedBudget: ['Budget', function (Budget) {
                            return Budget.query();
                        }],
                        resolvedOperationCategorie: ['OperationCategorie', function (OperationCategorie) {
                            return OperationCategorie.query();
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });
