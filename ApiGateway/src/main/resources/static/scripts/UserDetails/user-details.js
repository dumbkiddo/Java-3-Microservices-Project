'use strict';

angular.module('userDetails', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('ownerDetails', {
                parent: 'app',
                url: '/owners/details/:ownerId',
              //  url: '/users/details/:userId',
                template: '<user-details></user-details>'
            })
    }]);
