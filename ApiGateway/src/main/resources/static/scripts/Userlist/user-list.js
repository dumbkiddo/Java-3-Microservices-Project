'use strict';

angular.module('userList', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('owners', {
                parent: 'app',
                url: '/owners',
                //url: '/users',
                template: '<user-list></user-list>'
            })
    }]);
