function sysOnlineSessionCtl(DTLang, DTOptionsBuilder, DTColumnBuilder,
		$compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withPaginationType(
			'full_numbers').withDisplayLength(25).withOption("ordering", false)
			.withOption("responsive", true).withOption("searching", true)
			.withOption("paging", true).withOption('bStateSave', true)
			.withOption('bProcessing', true).withOption('bFilter', false)
			.withOption('bInfo', false).withOption('serverSide', false)
			.withOption('bAutoWidth', false).withOption('aaData',
					$scope.tabdata).withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			}).withLanguage(DTLang);
	$scope.dtInstance = {}
	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";

		acthtml = acthtml + " <button ng-click=\"row_dtl('" + full.USER_ID
				+ "')\" class=\"btn-white btn btn-xs\">详细</button> </div> ";
		return acthtml;
	}

	function renderUser(data, type, full) {
		if (angular.isDefined(data) && data.length > 0) {
			return full.NAME;
		} else {
			return "游客";
		}
	}

	$scope.dtColumns = [

			DTColumnBuilder.newColumn('ID').withTitle('ID').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('USER_ID').withTitle('用户名').withOption(
					'sDefaultContent', '').renderWith(renderUser),
			DTColumnBuilder.newColumn('IP').withTitle('IP').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('START_TIME').withTitle('创建时间')
					.withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('LOGIN_TIME').withTitle('登录时间')
					.withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('LASTACCESS').withTitle('最后访问')
					.withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('AGENT').withTitle('Agent').withOption(
					'sDefaultContent', '') ]

	function flush() {
		var ps = {};
		$http.post($rootScope.project + "/api/system/getOnlineSession.do", ps)
				.success(function(res) {
					if (res.success) {
						$scope.dtOptions.aaData = res.data;
					} else {
						notify({
							message : res.message
						});
					}
				})
	}
	flush();

	$scope.row_dtl = function(id) {

	}

	$scope.query = function() {
		flush();
	}
	$scope.save = function(id) {

	}

};

app.register.controller('sysOnlineSessionCtl', sysOnlineSessionCtl);