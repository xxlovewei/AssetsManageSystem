function meAccessLogCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {
	$scope.URL = $rootScope.project + "/api/user/queryAccessLog.do";
	$scope.dtOptions = DTOptionsBuilder.fromSource($scope.URL).withDataProp('data').withPaginationType('full_numbers').withDisplayLength(10).withOption("ordering", false)
	.withOption("responsive", true).withOption("searching", false).withOption("paging", true).withOption('bStateSave', true).withOption('bProcessing', false).withOption(
			'bFilter', false).withOption('bInfo', false).withOption('serverSide', true).withOption('bAutoWidth', false).withOption('createdRow', function(row) {
		// Recompiling so we can bind Angular,directive to the
		$compile(angular.element(row).contents())($scope);
	}).withLanguage(DTLang);
	
	
	$scope.dtInstance = {}
	$scope.reloadData = reloadData;
	function reloadData() {
		var resetPaging = false;
		$scope.dtInstance.reloadData(callback, resetPaging);
	}
	function callback(json) {
		console.log(json);
	}
	
	function renderAction(data, type, full) {
	 
	}

	$scope.dtColumns = [ DTColumnBuilder.newColumn('rtime').withTitle('日期').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('ip').withTitle('IP').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('url').withTitle('访问地址').withOption('sDefaultContent', '')
			 ]
 

	$scope.flush = function() {
		reloadData();
	}

 

};

app.register.controller('meAccessLogCtl', meAccessLogCtl);