'use strict';

moneyApp.controller('OperationCategorieController', function ($scope, resolvedOperationCategorie, OperationCategorie) {

        $scope.operationcategories = resolvedOperationCategorie;

        $scope.create = function () {
            OperationCategorie.save($scope.operationcategorie,
                function () {
                    $scope.operationcategories = OperationCategorie.query();
                    $('#saveOperationCategorieModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.operationcategorie = OperationCategorie.get({id: id});
            $('#saveOperationCategorieModal').modal('show');
        };

        $scope.delete = function (id) {
            OperationCategorie.delete({id: id},
                function () {
                    $scope.operationcategories = OperationCategorie.query();
                });
        };

        $scope.clear = function () {
            $scope.operationcategorie = {libelle: null, id: null};
        };
    });
