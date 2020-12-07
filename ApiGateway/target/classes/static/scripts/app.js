'use strict';
var movieApp = angular.module('petClinicApp', [
    'ui.router', 'layoutNav',  'layoutWelcome',
    'userList', 'userDetails', 'userForm', 'bookingForm', 'reviews', 'movieList']);

movieApp.config(['$stateProvider', '$urlRouterProvider', '$locationProvider', '$httpProvider', function(
    $stateProvider, $urlRouterProvider, $locationProvider, $httpProvider) {

    $httpProvider.defaults.headers.common["Cache-Control"] = 'no-cache';

    $locationProvider.hashPrefix('!');

    $urlRouterProvider.otherwise('/welcome');
    $stateProvider
        .state('app', {
            abstract: true,
            url: '',
            template: '<ui-view></ui-view>'
        })
        .state('welcome', {
            parent: 'app',
            url: '/welcome',
            template: '<layout-welcome></layout-welcome>'
        });
}]);

['welcome', 'nav'].forEach(function(c) {
    var mod = 'layout' + c.toUpperCase().substring(0, 1) + c.substring(1);
    angular.module(mod, []);
    angular.module(mod).component(mod, {
        //templateUrl: "scripts/fragments/" + c + ".html"
        templateUrl: "frontend/" + c + ".html"
    });
});
