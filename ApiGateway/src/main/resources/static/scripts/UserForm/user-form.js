'use strict';

angular.module('userForm', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('ownerNew', {
                parent: 'app',
                url: '/owners/new',
                //url: '/users/new',
                template: '<user-form></user-form>'
            })
            .state('ownerEdit', {
                parent: 'app',
                url: '/owners/:ownerId/edit',
               // url: '/users/:userId/edit',
                template: '<user-form></user-form>'
            })
    }]);
