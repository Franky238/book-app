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

        // Modules starts here
        'Common',
        'Security'
    ];

    angular.module('app', modules);
})(angular);
