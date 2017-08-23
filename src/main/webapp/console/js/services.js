// userService
app.service('userService', function($http, $q, $log, $rootScope, $localStorage) {
	return {
		loginout : function() {
			var deferred = $q.defer();
			$http.post($rootScope.project + "/user/logout.do", {}).success(function(res) {
				if (res.success) {
					$rootScope.dt_app_token = ""
					$localStorage.put('dt_app_token', "");
				}
				deferred.resolve(res);
			})
			return deferred.promise;
		},
		login : function(e) {
			var deferred = $q.defer();
			$http.post($rootScope.project + "/user/login.do", e).success(function(res) {
				$log.warn("service login return", res);
				if (res.success) {
					if (angular.isDefined(res.data.token)) {
						// 用户登录后设置token
						$log.warn("set token to $rootScope")
						$rootScope.dt_app_token = res.data.token;
						$localStorage.put('dt_app_token', res.data.token);
						// 用户信息
						$rootScope.dt_sys_user_info = res.data.user_info;
						$localStorage.put('dt_sys_user_info', res.data.user_info);
					} else {
						$log.warn("返回不存在token");
					}
				}
				deferred.resolve(res);
			})
			return deferred.promise;
		},
		getToken : function() {
			if (angular.isDefined($rootScope.dt_app_token)) {
				return $rootScope.dt_app_token;
			} else {
				return $localStorage.get("dt_app_token");
			}
			return "";
		},
		checklogin : function() {
			var deferred = $q.defer();
			$http.post($rootScope.project + "/user/checkLogin.do", {}).success(function(res) {
				deferred.resolve(res);
			});
			return deferred.promise;
		},
	}
});