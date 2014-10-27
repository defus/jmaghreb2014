'use strict';

moneyApp.factory('Budget', function ($resource) {
        return $resource('app/rest/budgets/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });
