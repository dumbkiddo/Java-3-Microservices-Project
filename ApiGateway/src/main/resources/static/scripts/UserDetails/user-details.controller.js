'use strict';

angular.module('userDetails')
    .controller('UserDetailsController', ['$http', '$stateParams', function ($http, $stateParams) {
        var self = this;

        $http.get('api/gateway/owners/' + $stateParams.ownerId).then(function (resp) {
        //$http.get('api/gateway/users/' + $stateParams.ownerId).then(function (resp) {
            self.owner = resp.data;
        });
    }]);
