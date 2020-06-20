function labeltplCtl(DTOptionsBuilder, DTColumnBuilder, $compile, $confirm, $log,
		notify, $scope, $http, $rootScope, $uibModal, $window) {
	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data')
			.withPaginationType('full_numbers').withDisplayLength(50)
			.withOption("ordering", false).withOption("responsive", false)
			.withOption("searching", true).withOption('scrollY', 600)
			.withOption('scrollX', true).withOption('bAutoWidth', true)
			.withOption('scrollCollapse', true).withOption('paging', true)
			.withOption('bStateSave', true).withOption('bProcessing', false)
			.withOption('bFilter', false).withOption('bInfo', false)
			.withOption('serverSide', false).withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			}).withOption(
					'headerCallback',
					function(header) {
						if ((!angular.isDefined($scope.headerCompiled))
								|| $scope.headerCompiled) {
							// Use this headerCompiled field to only compile
							// header once
							$scope.headerCompiled = true;
							$compile(angular.element(header).contents())
									($scope);
						}
					}).withOption("select", {
				style : 'multi',
				selector : 'td:first-child'
			});

	$scope.dtInstance = {}

	function renderName(data, type, full) {

		var html = full.model;
		return html;

	}

	function renderJg(data, type, full) {
		var html = full.rackstr + "-" + full.frame;
		return html;
	}

	$scope.selectCheckBoxAll = function(selected) {
		if (selected) {
			$scope.dtInstance.DataTable.rows().select();
		} else {
			$scope.dtInstance.DataTable.rows().deselect();
		}
	}
	var ckHtml = '<input ng-model="selectCheckBoxValue" ng-click="selectCheckBoxAll(selectCheckBoxValue)" type="checkbox">';

	$scope.dtColumns = [
		DTColumnBuilder.newColumn(null).withTitle(ckHtml).withClass(
			'select-checkbox checkbox_center').renderWith(function() {
			return ""
		}),
		DTColumnBuilder.newColumn('type').withTitle('类型').withOption(
			'sDefaultContent', ''),
		DTColumnBuilder.newColumn('ifdef').withTitle('默认').withOption(
			'sDefaultContent', ''),
		DTColumnBuilder.newColumn('ctlvalue').withTitle('配置').withOption(
			'sDefaultContent', '')

	]


	$scope.query = function() {
		flush();
	}

	var meta = {
		tablehide : false,
		toolsbtn : [],
		tools : [
				{
					id : "btn",
					label : "",
					type : "btn",
					show : true,
					template : ' <button ng-click="detail()" class="btn btn-sm btn-primary" type="submit">详情</button>'
				}
				]
	}
	$scope.meta = meta;

	function flush() {
		var ps = {}

		$http.post($rootScope.project + "/api/cmdb/resLabelTpl/selectList.do", ps)
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


	function getSelectRow() {
		var data = $scope.dtInstance.DataTable.rows({
			selected : true
		})[0];
		if (data.length == 0) {
			notify({
				message : "请至少选择一项"
			});
			return;
		} else if (data.length > 1) {
			notify({
				message : "请最多选择一项"
			});
			return;
		} else {
		 
			return $scope.dtOptions.aaData[data[0]];
		}
	}


};

app.register.controller('labeltplCtl', labeltplCtl);