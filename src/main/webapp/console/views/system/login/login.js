function sysLoginCtl($rootScope, $scope, $log, $http, userService, $state, $localStorage, notify, $stateParams) {

	var to = $stateParams.to;
	console.log($state);
	$log.warn("login to:", to);
	$scope.user = {
		user : "admin",
		pwd : "admin",
		type : "username"
	};
	$scope.login = function(e) {
		if (e == "A") {
			$scope.user = {
				user : "admin",
				pwd : "admin",
				type : "username"
			};
		} else if (e == "D") {
			$scope.user = {
				user : "dev",
				pwd : "12",
				type : "username"
			};
		} else if (e == "N") {
			$scope.user = {
				user : "juck",
				pwd : "0",
				type : "username"
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
			$log.warn("userService result", result)
			if (result.success) {
				if (angular.isDefined(to) && to != null && to != 'login') {
					$log.warn("end:" + to);
					$state.go(to);
				} else {
					$state.go("content");
				}
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