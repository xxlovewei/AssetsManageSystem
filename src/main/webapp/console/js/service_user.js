app.service('userService', function($http, $q, $log, $rootScope, $localStorage) {
	return {
		logout : function() {
			var deferred = $q.defer();
			$http.post($rootScope.project + "/api/user/logout.do", {}).success(function(res) {
				if (res.success) {
					$rootScope.dt_app_token = ""
					$localStorage.put('dt_app_token', "");
					$rootScope.dt_sys_menus = []
					$localStorage.put('dt_sys_menus', []);
				}
				deferred.resolve(res);
			})
			return deferred.promise;
		},
		login : function(e) {
			var deferred = $q.defer();
			e.basePublic = "yes";
			e.client="web";
			$http.post($rootScope.project + "/api/user/login.do", e).success(function(res) {
				$log.warn("service login return", res);
				if (res.success) {
					if (angular.isDefined(res.data.token)) {
						// 用户token
						$log.warn("set token to $rootScope")
						$rootScope.dt_app_token = res.data.token;
						$localStorage.put('dt_app_token', res.data.token);
						// 用户信息
						$rootScope.dt_sys_user_info = res.data.user_info;
						$localStorage.put('dt_sys_user_info', res.data.user_info);

						// 用户拥有的系统资源
						$rootScope.dt_systems = res.data.systems;
						$localStorage.put('dt_systems', res.data.systems);
						// 当前默认可能存在Id为1的系统,默认获取该资源
						$log.warn(res.data.user_info);
						var menuid = "";
						if (angular.isDefined(res.data.cur_system)) {
							menuid = res.data.cur_system;
						}
						$http.post($rootScope.project + "/api//user/getUserMenus.do", {
							menu_id : menuid
						}).success(function(rs) {
							if (rs.success) {
								$log.warn("###Action login,load user_menu from service######");
								$rootScope.dt_sys_menus = rs.data;
								$localStorage.put('dt_sys_menus', rs.data);
							}
							deferred.resolve(rs);
						});
					} else {
						$log.debug("返回不存在token");
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
		checkLogin : function() {
			var deferred = $q.defer();
			// 后期需要加上菜单选择判断,当前暂时不实现
			$http.post($rootScope.project + "/api/user/checkLogin.do", {}).success(function(res) {
				deferred.resolve(res);
			});
			return deferred.promise;
		},
		switchSystem : function(id) {
			var deferred = $q.defer();
			$http.post($rootScope.project + "/api/user/getUserMenus.do", {
				menu_id : id
			}).success(function(rs) {
				if (rs.success) {
					$log.warn("###Action switchSystem,load user_menu from service######");
					$rootScope.dt_sys_menus = rs.data;
					$localStorage.put('dt_sys_menus', rs.data);
				}
				deferred.resolve(rs);
			});
			return deferred.promise;
		}
	}
});