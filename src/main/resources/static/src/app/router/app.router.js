angular.module('app')
    .config(function ($stateProvider, $urlRouterProvider) {
        // default
        $urlRouterProvider.otherwise(function ($injector, $location) {
            $injector.invoke(function ($state) {
                $state.go('home');
            });
        });

        $stateProvider
            .state('security', {
                url: '/signup',
                templateUrl: 'src/app/modules/security/security.partial.html',
                data: {
                    securedPath: false
                }
            })
            .state('security.register', {
                url: '/register',
                templateUrl: 'src/app/modules/security/partial/register.partial.html',
                data: {
                    securedPath: false
                }
            })
            .state('security.login', {
                url: '/login',
                templateUrl: 'src/app/modules/security/partial/login.partial.html',
                data: {
                    securedPath: false
                }
            })
            .state('home', {
                url: '/',
                templateUrl: 'src/app/modules/reader/reader.partial.html'
            })
        ;
    });
