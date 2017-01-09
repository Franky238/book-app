(function () {
    angular.module('Security').factory('FormUtils', function (CommonService, config) {
        var isUnique = function (field, value, callback) {
            CommonService.post(config.SERVER_URL + '/auth/check/' + field, value).then(function (result) {
                callback(result.data);
            });
        };

        var compare = function (firstVal, secondVal) {
            return firstVal === secondVal;
        };

        return {
            isUnique: isUnique,
            compare: compare
        };
    });
})();
