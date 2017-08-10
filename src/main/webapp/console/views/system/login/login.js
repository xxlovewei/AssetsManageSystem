function sysLoginCtl($rootScope, $scope, $log, $http, userService, $state,
		$localStorage, notify) {

	$scope.user = {
		user : "admin",
		pwd : "admin"
	};
	$scope.login = function(e) {
		if (e == "A") {
			$scope.user = {
				user : "admin",
				pwd : "admin"
			};
		} else if (e == "D") {
			$scope.user = {
				user : "dev",
				pwd : "12"
			};
		} else if (e == "N") {
			$scope.user = {
				user : "juck",
				pwd : "12"
			};
		}

		if ($scope.user.user.length == 0) {
			notify({
				message : "请输入账户"
			});
			return;
		}
		if ($scope.user.pwd.length == 0) {
			notify({
				message : "请输入密码"
			});
			return;
		}

		userService.login($scope.user).then(function(result) {
			console.log("userService result", result)
			if (result.success) {
				//登录成功
				var cmd = "/user/getUserMenus.do"
				// var cmd="/menu/treeMenus.do"
				var ps={};
				ps.user_id=result.data.user_info.USER_ID;
				console.log("log params:",ps);
				// 获取树
				$http.post($rootScope.project + cmd, ps).success(function(res) {
					console.log(res);
					if (res.success) {
						$log.warn("###load menus from http######");
						$rootScope.dt_sys_menus = res.data;
						$localStorage.put('dt_sys_menus', res.data);
						$state.go("content");
					}else{
						notify({
							message : result.message
						});
					}
				})
				
			} else {
				notify({
					message : result.message
				});
			}
		}, function(error) {

		}, function(progress) {

		})

	}
};

app.register.controller('sysLoginCtl', sysLoginCtl);