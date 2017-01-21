var app = angular.module('app');

app
    .config(function (blockUIConfig, jwtInterceptorProvider, $httpProvider, $logProvider, toastrConfig) {
        // BlockUI config
        blockUIConfig.template = "<div class='overlay bg-grey'><div class='absolute middle middle-spinner'>" +
            "<div class='loading spin-1'>" +
            "<div class='loading spin-2'>" +
            "<div class='loading spin-3'>" +
            "<div class='loading spin-4'>" +
            "<div class='loading spin-5'>" +
            "<div class='loading spin-6'>" +
            '</div></div></div></div></div></div></div></div>';
        blockUIConfig.autoBlock = false;
        blockUIConfig.cssClass = 'block-ui';
        blockUIConfig.delay = 400;

        // JWT config
        jwtInterceptorProvider.tokenGetter = function (store, config) {
            return store.get(config.JWT_STORAGE_KEY);
        };

        $httpProvider.interceptors.push('jwtInterceptor');

        // Toastr config
        angular.extend(toastrConfig, {
            autoDismiss: false,
            containerId: 'toast-container',
            maxOpened: 0,
            newestOnTop: true,
            positionClass: 'toast-top-right',
            preventDuplicates: false,
            preventOpenDuplicates: false,
            target: 'body',
            progressBar: true,
            showEasing: 'swing',
            hideEasing: 'linear'
        });

        // LogProvider config
        $logProvider.debugEnabled(false);
    })
    // run configuration
    .run(function ($state, $rootScope, store, config) {
        $rootScope.$on('$stateChangeStart', function (event, to) {
            if (to.data && !to.data.securedPath) {
                return;
            }

            if (!store.get(config.JWT_STORAGE_KEY)) {
                event.preventDefault();
                $state.go('security.login');
            }
        });
    })
    ;

angular.module('AppConfig', [])
    .constant('config', {
        SERVER_URL: '',
        JWT_STORAGE_KEY: 'jwt_token'
    });
