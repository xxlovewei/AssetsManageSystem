function prodCtl(DTOptionsBuilder, DTColumnBuilder, $compile, $confirm, $log,
		notify, $scope, $http, $rootScope, $uibModal) {
	$scope.btn_query = function() {
		console.log("query");
	}
	$scope.btn_add = function() {
		console.log("add");
	}
	$scope.btn_del = function() {
		console.log("del");
	}

	$scope.btn_modify = function() {
		console.log("modify");
	}
	function a() {
		console.log(99);
	}
	var opt = [ {
		id : "1",
		name : "adfasdfasA"
	}, {
		id : "1",
		name : "B"
	}, {
		id : "1",
		name : "C"
	} ];

	var meta = {
		tablehide:false,
		tools : [
				{
					id : "input",
					label : "212",
					label_hide : true,
					type : "input",
					placeholder : "1212",
					ct : ""
				},
				{
					id : "select",
					label : "选择",
					type : "select",
					disablesearch : true,
					dataOpt : opt,
					dataSel : opt[0]
				},
				{
					id : "btn",
					label : "",
					type : "btn",
					template : ' <button ng-click="abcd1()" class="btn btn-sm btn-primary" type="submit">查询c</button>'
				}, {
					id : "btn_query",
					label : "查询",
					type : "btn_query"
				}, {
					id : "btn_add",
					fun : "",
					label : "新增",
					type : "btn_add"
				}, {
					id : "btn_del",
					fun : "",
					label : "删除",
					type : "btn_del"
				}, {
					id : "btn_modify",
					fun : "",
					label : "修改",
					type : "btn_modify"
				} ,{
					id : "btn_actiona",
					fun : "",
					label : "action",
					type : "btn_actiona"
				} ]
	}

	$scope.meta = meta;

	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withOption(
			'createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			});
	$scope.dtInstance = {}
	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";
		acthtml = acthtml + " <button ng-click=\"save('" + full.role_id
				+ "')\" class=\"btn-white btn btn-xs\">编辑</button> ";
		// acthtml = acthtml + " <button ng-click=\"row_detail()\"
		// class=\"btn-white btn btn-xs\">详细</button> ";
		acthtml = acthtml + " <button ng-click=\"row_del('" + full.role_id
				+ "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
		return acthtml;
	}

	$scope.dtColumns = [
			DTColumnBuilder.newColumn('role_name').withTitle('名称').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('is_action').withTitle('状态').withOption(
					'sDefaultContent', '').renderWith(
					function(data, type, full) {
						return dt_renderMapSimple(data, type, full, [ {
							id : "Y",
							name : "有效",
							id : "N",
							name : "无效"
						} ]);
					}),
			DTColumnBuilder.newColumn('role_id').withTitle('操作').withOption(
					'sDefaultContent', '').renderWith(renderAction) ]

	function flush() {
		var ps = {}
		$http.post($rootScope.project + "/api/role/roleQuery.do", ps).success(
				function(res) {
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

	$scope.row_detail = function(id) {

	}
	$scope.row_del = function(id) {
		$confirm({
			text : '是否删除功能?'
		}).then(function() {
			$http.post($rootScope.project + "/api/role/roleDelete.do", {
				role_id : id
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

	$scope.query = function() {
		flush();
	}
	$scope.save = function(id) {
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/system/role/modal_role_save.html',
			controller : roleSaveCtl,
			size : 'lg',
			resolve : { // 调用控制器与modal控制器中传递值
				id : function() {
					return id;
				}
			}
		});

		modalInstance.result.then(function(result) {
			$log.log("result", result);
			if (result == "OK") {
				flush();
			}
		}, function(reason) {
			// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
			$log.log("reason", reason)
		});
	}

};
app.register.controller('prodCtl', prodCtl);