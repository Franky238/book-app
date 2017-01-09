(function () {
    angular.module('Security').controller('RegisterController', function ($scope, FormUtils) {
        $scope.user = {};

        this.checkUserBy = function (fieldType) {
            var fieldValue = $scope.user[fieldType];
            if (fieldValue) {
                FormUtils.isUnique(fieldType, fieldValue, function (result) {
                    $scope.registerForm[fieldType].$setValidity('notUsed', result);
                });
            }
        };

        this.comparePass = function () {
            var isEqual = FormUtils.compare($scope.user.password.first, $scope.user.password.second);

            if (isEqual) {
                $scope.registerForm.password.$setValidity('equality', true);
                $scope.registerForm.repeat_password.$setValidity('equality', true);
            } else {
                $scope.registerForm.password.$setValidity('equality', false);
                $scope.registerForm.repeat_password.$setValidity('equality', false);
            }
        };

        this.save = function () {
            console.log($scope.registerForm);
        };

        var init = function () {};

        init();
    });
})();
