(function () {
    angular.module('Reader').controller('ReaderController', function ($scope, CommonService) {
        var init = function () {
            CommonService.get('/user').then(function (response) {
                $scope.something = response.data;
            });
        };

        init();
    });
})();
