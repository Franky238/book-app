var app = angular.module('app');

app.config(function (blockUIConfig) {
    // Change the default overlay message
    blockUIConfig.template = "<div class='overlay bg-grey'><div class='absolute middle middle-spinner'><div class='loading spin-1'> <div class='loading spin-2'> <div class='loading spin-3'> <div class='loading spin-4'> <div class='loading spin-5'> <div class='loading spin-6'></div></div></div></div></div></div></div></div>";
    blockUIConfig.autoBlock = false;
    blockUIConfig.cssClass = 'block-ui';
    blockUIConfig.delay = 400;
});

app.config(function ($logProvider) {
    $logProvider.debugEnabled(false);
});

app.config(function (toastrConfig) {
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
});

angular.module('AppConfig', [])
    .constant('config', {
        SERVER_URL: '',
        SECURE_PREFIX: '/secured'
    });
