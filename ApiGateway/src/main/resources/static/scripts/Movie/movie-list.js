'use strict';

angular.module('movieList', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
              .state('vets', {
           // .state('movies', {
                parent: 'app',
                url: '/movies',
                template: '<movie-list></movie-list>'
            })
    }]);
