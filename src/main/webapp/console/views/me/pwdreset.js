function mePwdResetCtl($log, notify, $scope, $http, $rootScope, $uibModal) {
	$scope.item = {};
	$scope.save = function() {
		if ($scope.item.npwd1 == $scope.item.npwd2) {
			var ps = {};
			ps.opwd = $scope.item.opwd;
			ps.npwd = $scope.item.npwd1;
			$http.post($rootScope.project + "/api/user/changePwd.do", ps).success(function(res) {
				notify({
					message : res.message
				});
			})
		} else {
			notify({
				message : "新密码输入不一致."
			});
		}
	}
};
app.register.controller('mePwdResetCtl', mePwdResetCtl);