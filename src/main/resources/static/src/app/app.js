(function (angular) {
    'use strict';

    var modules = [
        'blockUI',
        'ui.router',
        'templatesModule',
        'ui.bootstrap',
        'AppConfig',
        'ngAnimate',
        'toastr',

        // OAuth modules
        'angular-jwt',
        'angular-storage',

        // Modules starts here
        'Common',
        'Security',
        'Reader'
    ];

    angular.module('app', modules);
})(angular);
