'use strict';

moneyApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/operationcategorie', {
                    templateUrl: 'views/operationcategories.html',
                    controller: 'OperationCategorieController',
                    resolve:{
                        resolvedOperationCategorie: ['OperationCategorie', function (OperationCategorie) {
                            return OperationCategorie.query();
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });
