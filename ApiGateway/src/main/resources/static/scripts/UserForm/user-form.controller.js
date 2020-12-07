'use strict';

angular.module('userForm')
    .controller('UserFormController', ["$http", '$state', '$stateParams', function ($http, $state, $stateParams) {
        var self = this;

        var ownerId = $stateParams.ownerId || 0;

        if (!ownerId) {
            self.owner = {};
        } else {
            $http.get("api/customer/owners/" + ownerId).then(function (resp) {
         //   $http.get("api/customer/users/" + ownerId).then(function (resp) {
                self.owner = resp.data;
            });
        }

        self.submitOwnerForm = function () {
            var id = self.owner.id;
            var req;
            if (id) {
                req = $http.put("api/customer/owners/" + id, self.owner);
            //    req = $http.put("api/customer/users/" + id, self.owner);
            } else {
                 req = $http.post("api/customer/owners", self.owner);
            //    req = $http.post("api/customer/users", self.owner);
            }

            req.then(function () {
                $state.go('owners');
            }, function (response) {
                var error = response.data;
                alert(error.error + "\r\n" + error.errors.map(function (e) {
                        return e.field + ": " + e.defaultMessage;
                    }).join("\r\n"));
            });
        };
    }]);
