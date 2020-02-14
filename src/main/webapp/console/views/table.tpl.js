$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data')
		.withDOM('frtlip').withPaginationType('full_numbers')
		.withDisplayLength(100).withOption("ordering", false).withOption(
				"responsive", false).withOption("searching", false).withOption(
				'scrollY', '600px').withOption('scrollX', true).withOption(
				'bAutoWidth', true).withOption('scrollCollapse', true)
		.withOption('paging', true).withFixedColumns({
			leftColumns : 0,
			rightColumns : 0
		}).withOption('bStateSave', true).withOption('bProcessing', false)
		.withOption('bFilter', false).withOption('bInfo', false).withOption(
				'serverSide', false).withOption('aaData', $scope.tabdata)
		.withOption('createdRow', function(row) {
			$compile(angular.element(row).contents())($scope);
		}).withOption(
				'headerCallback',
				function(header) {
					if ((!angular.isDefined($scope.headerCompiled))
							|| $scope.headerCompiled) {
						$scope.headerCompiled = true;
						$compile(angular.element(header).contents())($scope);
					}
				}).withOption("select", {
			style : 'multi',
			selector : 'td:first-child'
		}).withButtons([ {
			extend : 'colvis',
			text : '显示隐藏列',
			fnLabel : function(dt, idx, title) {
				console.log(dt, idx, title);
				return (idx + 1) + ': ' + title;
			}
		}, {
			extend : 'csv',
			text : 'Excel(当前页)',
			exportOptions : {
				columns : ':visible',
				trim : true,
				modifier : {
					page : 'current'
				}
			}
		}, {
			extend : 'print',
			text : '打印(当前页)',
			exportOptions : {
				columns : ':visible',
				stripHtml : false,
				columns : ':visible',
				modifier : {
					page : 'current'
				}
			}
		} ]);
$scope.dtInstance = {}
$scope.selectCheckBoxAll = function(selected) {
	if (selected) {
		$scope.dtInstance.DataTable.rows().select();
		console.log($scope.dtInstance.DataTable)
		console.log($scope.dtInstance);
	} else {
		$scope.dtInstance.DataTable.rows().deselect();
		console.log($scope.dtInstance.DataTable)
		console.log($scope.dtInstance);
	}
}

var ckHtml = '<input ng-model="selectCheckBoxValue" ng-click="selectCheckBoxAll(selectCheckBoxValue)" type="checkbox">';
$scope.dtColumns = [];
$scope.dtColumns.push(DTColumnBuilder.newColumn(null).withTitle(ckHtml)
		.withClass('select-checkbox checkbox_center').renderWith(function() {
			return ""
		}));
$scope.dtColumns.push(DTColumnBuilder.newColumn('uuid').withTitle('资产编号')
		.withOption('sDefaultContent', '').withOption("width", '30'));
$scope.dtColumns.push(DTColumnBuilder.newColumn('classname').withTitle('资产类型')
		.withOption('sDefaultContent', '').withOption("width", '30'));

$scope.query = function() {
	flush();
}

function flush() {
	var ps = {}
	$http.post($rootScope.project + "/api/base/res/queryResAllByClass.do", ps)
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

function getSelectRows() {
	var data = $scope.dtInstance.DataTable.rows({
		selected : true
	})[0];
	if (data.length == 0) {
		notify({
			message : "请至少选择一项"
		});
		return;
	} else if (data.length > 1000) {
		notify({
			message : "不允许超过1000个"
		});
		return;
	} else {
		var res = [];
		for (var i = 0; i < data.length; i++) {
			res.push($scope.dtOptions.aaData[data[i]])
		}
		return res
	}
}

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

$scope.updates = function() {
	var selrows = getSelectRows();
	if (angular.isDefined(selrows)) {
		return;
	}
	// something
}