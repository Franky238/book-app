(function () {
    angular.module('Security').controller('HomeController', function ($scope, CommonService) {
        var init = function () {
            CommonService.get('/user').then(function (response) {
                $scope.something = response.data;
            }, function (err) {
                $scope.something = err.data;
            });
        };

        init();
    });
})();
