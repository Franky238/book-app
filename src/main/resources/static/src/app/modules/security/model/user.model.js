(function () {
    angular.module('Security').factory('UserModel', function (CommonService) {
        var register = function (data, callback) {
            var url = '/auth/register';

            CommonService.post(url, data).then(function (result) {
                callback(result.data);
            }, function (err) {
                if (err) {
                    console.log(err.message);
                }
            });
        };

        var login = function (data, successCallback, errorCallback) {
            var url = '/auth/login';

            CommonService.post(url, data).then(function (result) {
                successCallback(result.data);
            }, function (err) {
                if (err) {
                    console.log(err.data.message);
                    errorCallback(err.data);
                }
            });
        };

        return {
            register: register,
            login: login
        };
    });
})();
