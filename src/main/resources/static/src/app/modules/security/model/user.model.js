(function () {
    angular.module('Security').factory('UserModel', function (CommonService) {
        var register = function (data, callback) {
            var url = '/auth/register';

            CommonService.post(url, data).then(function (result) {
                callback(result.data);
            }, function (err) {
                if (err) {
                    console.log(err);
                }
            });
        };

        return {
            register: register
        };
    });
})();
