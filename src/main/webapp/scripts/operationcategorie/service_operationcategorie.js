'use strict';

moneyApp.factory('OperationCategorie', function ($resource) {
        return $resource('app/rest/operationcategories/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });
