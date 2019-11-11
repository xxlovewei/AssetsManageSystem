function sysLoginCtl($timeout, $rootScope, $scope, $log, $http, userService,
		$state, $localStorage, notify,$stateParams) {

	var to = $stateParams.to;
 
	$scope.user = {
		user : "",
		pwd : "",
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
		} else if (e == "S") {
			$scope.user = {
				user : "sys",
				pwd : "0",
				type : "username"
			};
		}

		userService.login($scope.user).then(function(result) {
			$log.warn("userService result", result)
			if (result.success) {
				$timeout(function() {
					if (angular.isDefined(to) && to != null && to != 'login') {
						$log.warn("end:" + to);
						$state.go(to, {
							 
						});
					} else {
						$state.go("content");
					}
				}, 500)
			} else {
				notify({
					message : result.message
				});
			}
		})

	}
};

app.register.controller('sysLoginCtl', sysLoginCtl);