(function () {
    angular.module('Security').controller('RegisterController', function ($scope, FormUtils, toastr, UserModel, $state) {
        $scope.user = {};

        var self = this;

        self.checkUserBy = function (fieldType) {
            var fieldValue = $scope.user[fieldType];
            if (fieldValue) {
                FormUtils.isUnique(fieldType, fieldValue, function (isNotUsed) {
                    $scope.registerForm[fieldType].$setValidity('notUsed', isNotUsed);
                    if (!isNotUsed) {
                        toastr.error('Value ' + fieldValue + ' is already used', 'Please use different ' + fieldType);
                    }
                });
            }
        };

        self.comparePass = function () {
            if (!$scope.user.password || !$scope.user.repeatPassword) {
                return;
            }

            var isEqual = FormUtils.compare($scope.user.password, $scope.user.repeatPassword);

            if (isEqual) {
                $scope.registerForm.password.$setValidity('equality', true);
                $scope.registerForm.repeat_password.$setValidity('equality', true);
            } else {
                $scope.registerForm.password.$setValidity('equality', false);
                $scope.registerForm.repeat_password.$setValidity('equality', false);
                // toastr.error('Please try to fill passwords again', 'Password not match');
            }
        };

        self.save = function () {
            UserModel.register($scope.user, function () {
                toastr.success('Continue with log in', 'Well done!');
                $scope.user = {};
                $state.go('security.login');
            });
        };

        var init = function () {
            $scope.$watch('user.password', function () {
                self.comparePass();
            });
            $scope.$watch('user.repeatPassword', function () {
                self.comparePass();
            });
        };

        init();
    });
})();
