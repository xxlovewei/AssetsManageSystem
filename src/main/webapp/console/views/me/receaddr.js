function meRecAddrCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withPaginationType('full_numbers').withDisplayLength(25).withOption("ordering", false).withOption("responsive", true)
			.withOption("searching", false).withOption("paging", false).withOption('bStateSave', true).withOption('bProcessing', true).withOption('bFilter', false).withOption(
					'bInfo', false).withOption('serverSide', false).withOption('bAutoWidth', false).withOption('aaData', $scope.tabdata).withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			}).withLanguage(DTLang);
	$scope.dtInstance = {}

	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";
		acthtml = acthtml + " <button ng-click=\"save('" + full.ID + "')\" class=\"btn-white btn btn-xs\">更新</button>  ";
		acthtml = acthtml + " <button ng-click=\"row_del('" + full.ID + "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
		return acthtml;
	}

	$scope.dtColumns = [ DTColumnBuilder.newColumn('CONTACTUSER').withTitle('联系人').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('CONTACT').withTitle('联系方式').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('ZCODE').withTitle('邮编').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('CTDTL').withTitle('地址').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('ID').withTitle('动作').withOption('sDefaultContent', '').renderWith(renderAction) ]

	function flush() {
		var ps = {}
		$http.post($rootScope.project + "/api/user/queryReceivingAddr.do", ps).success(function(res) {
			if (res.success) {
				$scope.dtOptions.aaData = res.data;
			}
		})
	}
	flush();

	$scope.flush = function() {
		flush();
	}

	$scope.save = function() {
		alert('功能未实现');
	}

	$scope.row_del = function(id) {
		$confirm({
			text : '是否删除该地址?'
		}).then(function() {
			$http.post($rootScope.project + "/api/user/delReceivingAddr.do", {
				ID : id
			}).success(function(res) {
				if (res.success) {
					flush();
				}
				notify({
					message : res.message
				});
			})
		});
	}

};

app.register.controller('meRecAddrCtl', meRecAddrCtl);