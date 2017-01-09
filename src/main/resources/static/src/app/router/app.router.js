angular.module('app')
    .config(function ($stateProvider, $urlRouterProvider) {
        // default
        $urlRouterProvider.otherwise(function ($injector, $location) {
            $injector.invoke(function ($state) {
                $state.go('security');
            });
        });

        $stateProvider
            .state('security', {
                url: '/signup',
                templateUrl: 'src/app/modules/security/security.partial.html'
            })
            .state('security.register', {
                url: '/register',
                templateUrl: 'src/app/modules/security/partial/register.partial.html'
            })
            .state('security.login', {
                url: '/login',
                templateUrl: 'src/app/modules/security/partial/login.partial.html'
            })
        ;
    });