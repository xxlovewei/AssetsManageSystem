function qadminhostsCtl( DTOptionsBuilder, DTColumnBuilder, $compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	 
	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			});
	$scope.dtInstance = {}
	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";
		acthtml = acthtml + " <button ng-click=\"enable('" + full.seq + "')\" class=\"btn-white btn btn-xs\">启用</button>  ";
		acthtml = acthtml + " <button ng-click=\"disable('" + full.seq + "')\" class=\"btn-white btn btn-xs\">停用</button>  ";

		acthtml = acthtml + " <button ng-click=\"pause('" + full.seq + "')\" class=\"btn-white btn btn-xs\">暂停</button>  ";
		acthtml = acthtml + " <button ng-click=\"resume('" + full.seq + "')\" class=\"btn-white btn btn-xs\">恢复</button>  ";

		acthtml = acthtml + " <button ng-click=\"runonce('" + full.seq + "')\" class=\"btn-white btn btn-xs\">执行一次</button> </div> ";
		return acthtml;
	}

	function renderStatus(data, type, full) {

		var jobenableimg = '<img src="img/sm/unknow.png"/>';
		if (data == 'true') {
			jobenableimg = '<img src="img/sm/green.png"/>';
		} else if (data == 'false') {
			jobenableimg = '<img src="img/sm/grey.png"/>';
		}
		return jobenableimg;
	}

	function renderRunStatus(data, type, full) {

		var value = data
		if (data == 'NORMAL') {
			value = '正常'
		} else if (data == 'PAUSED') {
			value = '暂停'
		}
		return value;
	}

	$scope.dtColumns = [

	DTColumnBuilder.newColumn('name').withTitle('名称').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('host').withTitle('ip').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('system_hostname_v').withTitle('主机名').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('agent_version_v').withTitle('客户端版本').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('system_name_v').withTitle('系统信息').withOption('sDefaultContent', '')
			
		 
  ]

	function flush() {
		var ps = {}

		$http.post($rootScope.project + "/api/zb/queryAllHostByGroupID.do", ps).success(function(res) {
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

	$scope.flush = function() {
		flush();
	}
 

};

app.register.controller('qadminhostsCtl', qadminhostsCtl);