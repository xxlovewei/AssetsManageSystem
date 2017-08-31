function sysUserAddCtl($confirm, $log, notify, $scope, $http, $rootScope) {

	$scope.item = {};
	$scope.item.NAME = "";
	$scope.userStatusOpt = [{
				id : "Y",
				name : "锁定"

			}, {
				id : "N",
				name : "正常"

			}]
	$scope.userStatusSel = $scope.userStatusOpt[0];

	$scope.reset = function() {
		$scope.item = {};
		$scope.item.NAME = "";
		$scope.userStatusSel = $scope.userStatusOpt[0];
	}

	$scope.ok = function() {
		$scope.item.LOCKED = $scope.userStatusSel.id;
		$log.info($scope.item);
		$http.post($rootScope.project + "/api/user/userSave.do", $scope.item).success(
				function(res) {
					if (res.success) {
						$scope.item = {};
						$scope.item.NAME = "";
						$scope.userStatusSel = $scope.userStatusOpt[0];
					}
					notify({
								message : res.message
							});
				})
	}

};

app.register.controller('sysUserAddCtl', sysUserAddCtl);