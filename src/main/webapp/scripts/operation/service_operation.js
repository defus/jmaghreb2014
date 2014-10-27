'use strict';

moneyApp.factory('Operation', function ($resource) {
        return $resource('app/rest/operations/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });
