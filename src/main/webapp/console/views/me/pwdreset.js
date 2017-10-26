function mePwdResetCtl($log, notify, $scope, $http, $rootScope, $uibModal) {
	$scope.item = {};
	$scope.save = function() {
		if ($scope.item.NPWD1 == $scope.item.NPWD2) {
			var ps = {};
			ps.OPWD = $scope.item.OPWD;
			ps.NPWD = $scope.item.NPWD1;
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