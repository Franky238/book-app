(function () {
    angular.module('Security').controller('LoginController', function ($scope, toastr, UserModel, config, store, $state) {
        $scope.credentials = {};

        var self = this;

        self.login = function () {
            UserModel.login($scope.credentials, function (jwt) {
                store.set(config.JWT_STORAGE_KEY, jwt.token);
                $state.go('home');
            }, function (err) {
                toastr.error(err.message, 'Login failed!');
            });
        };
    });
})();
