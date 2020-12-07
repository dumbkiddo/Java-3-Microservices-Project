'use strict';

angular.module('bookingForm', ['ui.router'])
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('petNew', {
                parent: 'app',
                url: '/owners/:ownerId/new-pet',
                template: '<booking-form></booking-form>'
            })
            // .state('petEdit', {
            //     parent: 'app',
            //     url: '/owners/:ownerId/pets/:petId',
            //     template: '<booking-form></booking-form>'
            // })
    }]);
