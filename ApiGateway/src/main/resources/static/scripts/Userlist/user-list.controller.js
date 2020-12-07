'use strict';

angular.module('userList')
    .controller('UserListController', ['$http', function ($http) {
        var self = this;

        $http.get('api/customer/owners').then(function (resp) {
        //$http.get('api/customer/users').then(function (resp) {
            self.owners = resp.data;
        });
    }]);
