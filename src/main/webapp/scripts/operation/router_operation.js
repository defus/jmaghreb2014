'use strict';

moneyApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/operation', {
                    templateUrl: 'views/operations.html',
                    controller: 'OperationController',
                    resolve:{
                        resolvedOperation: ['Operation', function (Operation) {
                            return Operation.query();
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
