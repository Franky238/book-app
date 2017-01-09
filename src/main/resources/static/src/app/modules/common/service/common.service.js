(function () {
    angular.module('Common')
        .factory('CommonService', function ($http) {
            var get = function (url) {
                return $http.get(url);
            };

            var post = function (url, data) {
                return $http.post(url, data);
            };

            return {
                get: get,
                post: post
            };
        });
})();
