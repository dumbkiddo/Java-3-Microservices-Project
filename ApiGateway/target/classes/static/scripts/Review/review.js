'use strict';

angular.module('reviews', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('visits', {
                parent: 'app',
                url: '/owners/:ownerId/pets/:petId/visits',
                template: '<reviews></reviews>'
            })
    }]);
