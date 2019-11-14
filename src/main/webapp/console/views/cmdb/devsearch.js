function cmdbdevsearchCtl(DTOptionsBuilder, DTColumnBuilder, $compile,
		$confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	// 分类
	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data')
			.withPaginationType('full_numbers').withDisplayLength(100)
			.withOption("ordering", false).withOption("responsive", false)
			.withOption("searching", true).withOption('scrollY', '600px')
			.withOption('scrollX', true).withOption('bAutoWidth', true)
			.withOption('scrollCollapse', true).withOption('paging', true)
			.withFixedColumns({
				leftColumns : 0,
				rightColumns : 0
			}).withOption('bStateSave', true).withOption('bProcessing', false)
			.withOption('bFilter', false).withOption('bInfo', false)
			.withOption('serverSide', false).withOption('aaData',
					$scope.tabdata).withOption('createdRow', function(row) {
				$compile(angular.element(row).contents())($scope);
			}).withOption(
					'headerCallback',
					function(header) {
						if ((!angular.isDefined($scope.headerCompiled))
								|| $scope.headerCompiled) {
							$scope.headerCompiled = true;
							$compile(angular.element(header).contents())
									($scope);
						}
					}).withOption("select", {
				style : 'multi',
				selector : 'td:first-child'
			})

	$scope.dtInstance = {}

	function renderName(data, type, full) {

		var html = full.model;
		// if (angular.isDefined(full.model)) {
		// html = html + "(" + full.model + ")";
		// }
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

	$scope.dtColumns = [];
	$scope.dtColumns = [
		DTColumnBuilder.newColumn(null).withTitle(ckHtml).withClass(
				'select-checkbox checkbox_center').renderWith(function() {
			return ""
		}),
		DTColumnBuilder.newColumn('uuid').withTitle('编号').withOption(
				'sDefaultContent', '').withOption("width", '30'),
		DTColumnBuilder.newColumn('classname').withTitle('类型').withOption(
				'sDefaultContent', '').withOption("width", '30'),
		DTColumnBuilder.newColumn('typestr').withTitle('小类').withOption(
				'sDefaultContent', '').withOption("width", '30'),
		DTColumnBuilder.newColumn('brandstr').withTitle('品牌').withOption(
				'sDefaultContent', '').withOption('width', '30'),
		DTColumnBuilder.newColumn('name').withTitle('型号').withOption(
				'sDefaultContent', '').withOption('width', '50')
				.renderWith(renderName),
		DTColumnBuilder.newColumn('reviewstr').withTitle('复核状态')
				.withOption('sDefaultContent', '')
				.withOption('width', '50'),
		DTColumnBuilder.newColumn('locstr').withTitle('位置').withOption(
				'sDefaultContent', '').withOption('width', '30'),
		DTColumnBuilder.newColumn('part_name').withTitle('部门').withOption(
				'sDefaultContent', '').withOption('width', '30'),
		DTColumnBuilder.newColumn('used_username').withTitle('使用人')
				.withOption('sDefaultContent', '')
				.withOption('width', '30'),
		DTColumnBuilder.newColumn('recyclestr').withTitle('状态').withOption(
				'sDefaultContent', '').withOption('width', '30'),
		DTColumnBuilder.newColumn('wbstr').withTitle('维保').withOption(
				'sDefaultContent', '').withOption('width', '30'),
		DTColumnBuilder.newColumn('envstr').withTitle('运行环境').withOption(
				'sDefaultContent', '').withOption('width', '30'),
		DTColumnBuilder.newColumn('riskstr').withTitle('风险等级').withOption(
				'sDefaultContent', '').withOption('width', '30'),
		DTColumnBuilder.newColumn('confdesc').withTitle('配置描述').withOption(
				'sDefaultContent', ''),
		DTColumnBuilder.newColumn('sn').withTitle('序列号').withOption(
				'sDefaultContent', ''),
		DTColumnBuilder.newColumn('buy_timestr').withTitle('采购时间')
				.withOption('sDefaultContent', ''),
		// DTColumnBuilder.newColumn('create_username').withTitle('录入人')
		// .withOption('sDefaultContent', ''),
		DTColumnBuilder.newColumn('update_username').withTitle('更新人')
				.withOption('sDefaultContent', ''),
		DTColumnBuilder.newColumn('review_username').withTitle('复核人')
				.withOption('sDefaultContent', '') ]

	$scope.query = function() {
		flush();

	}

	var gclass_id = "server";
	var meta = {
		tablehide : false,
		tools : [
				{
					id : "input",
					label : "内容",
					placeholder : "输入型号、编号、序列号",
					type : "input",
					show : true,
					ct : ""
				},
				{
					id : "btn",
					label : "",
					type : "btn",
					show : true,
					template : ' <button ng-click="query()" class="btn btn-sm btn-primary" type="submit">搜索</button>'
				},
				{
					id : "btn",
					label : "",
					type : "btn",
					show : true,
					template : ' <button ng-click="detail()" class="btn btn-sm btn-primary" type="submit">详情</button>'
				}, ]
	}
	$scope.meta = meta;

	function flush() {
		var ps = {}

		ps.search = $scope.meta.tools[0].ct;
		console.log($scope.meta.tools[0].ct)

		if ($scope.meta.tools[0].ct == "") {
			notify({
				message : "请输入搜索内容"
			});
			return;
		}
		$http.post($rootScope.project + "/api/base/res/queryResAll.do", ps)
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

	var gdicts = {};
	$http
			.post(
					$rootScope.project + "/api/base/res/queryDictFast.do",
					{
						dicts : "devbrand,devrisk,devenv,devrecycle,devwb,devdc,devservertype,devrack"
					}).success(function(res) {
				if (res.success) {
					gdicts = res.data;
				} else {
					notify({
						message : res.message
					});
				}
			})

	$scope.detail = function(id) {

		var ps = {};
		ps.id = id;
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/cmdb/modal_dtl.html',
			controller : modalcmdbdtlCtl,
			size : 'blg',
			resolve : { // 调用控制器与modal控制器中传递值
				meta : function() {
					return ps;
				}
			}
		});

		modalInstance.result.then(function(result) {
			$log.log("result", result);

			if (result == "OK") {

			}
		}, function(reason) {
			// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
			$log.log("reason", reason)
		});

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
			console.log("sel:", data);
			return $scope.dtOptions.aaData[data[0]];
		}
	}

	$scope.detail = function() {
		var id = "";
		var selrow = getSelectRow();
		if (angular.isDefined(selrow)) {
			id = selrow.id;
		} else {
			return;
		}
		var ps = {};
		ps.id = id;
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/cmdb/modal_dtl.html',
			controller : modalcmdbdtlCtl,
			size : 'blg',
			resolve : { // 调用控制器与modal控制器中传递值
				meta : function() {
					return ps;
				}
			}
		});
		modalInstance.result.then(function(result) {
			$log.log("result", result);
			if (result == "OK") {

			}
		}, function(reason) {
			// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
			$log.log("reason", reason)
		});

	}
};

app.register.controller('cmdbdevsearchCtl', cmdbdevsearchCtl);