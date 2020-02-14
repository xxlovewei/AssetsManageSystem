function formSettingCtl($stateParams, DTOptionsBuilder, DTColumnBuilder,
		$compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {
	$scope.meta = {
		tools : [
				{
					id : "0",
					priv : "select",
					label : "查询",
					type : "btn_query",
					hide : false,
				},
				{
					id : "1",
					label : "新增",
					priv : "insert",
					show : false,
					type : "btn",
					template : ' <button ng-click="save()" class="btn btn-sm btn-primary" type="submit">新增</button>'

				} ]
	}
	privNormalCompute($scope.meta.tools, $rootScope.curMemuBtns);
	var crud = {
		"update" : false,
		"insert" : false,
		"select" : false,
		"remove" : false,
	};
	privCrudCompute(crud, $rootScope.curMemuBtns);

	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data')
			.withDOM('frtlip').withPaginationType('full_numbers')
			.withDisplayLength(100).withOption("ordering", false).withOption(
					"responsive", false).withOption("searching", false)
			.withOption('paging', false).withFixedColumns({
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
			});
	
	
	$scope.dtInstance = {}
	$scope.selectCheckBoxAll = function(selected) {
		if (selected) {
			$scope.dtInstance.DataTable.rows().select();
			 
		} else {
			$scope.dtInstance.DataTable.rows().deselect();
			 
		}
	}

	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";
		if (crud.update) {
			acthtml = acthtml + " <button ng-click=\"save('" + full.id
					+ "')\" class=\"btn-white btn btn-xs\">更新</button> ";
		}
		if (crud.remove) {
			acthtml = acthtml + " <button ng-click=\"row_del('" + full.id
					+ "')\" class=\"btn-white btn btn-xs\">删除</button>";
		}
		acthtml = acthtml + "</div>";
		return acthtml;
	}
	function renderStatus(data, type, full) {
		var res = "无效";
		if (full.isAction == "Y") {
			res = "有效";
		}
		return res;
	}
	var ckHtml = '<input ng-model="selectCheckBoxValue" ng-click="selectCheckBoxAll(selectCheckBoxValue)" type="checkbox">';
	$scope.dtColumns = [
			DTColumnBuilder.newColumn(null).withTitle(ckHtml).withOption("width", '13').withClass(
					'select-checkbox checkbox_center').renderWith(function() {
				return ""
			}),
			DTColumnBuilder.newColumn('name').withTitle('名称').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('id').withTitle('操作').withOption(
					'sDefaultContent', '').renderWith(renderAction) ]

	function flush() {
		var ps = {}
		$http.post(
				$rootScope.project + "/api/flow/sysProcessClass/selectList.do",
				ps).success(function(res) {
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
	$scope.btn_query = function() {
		flush();
	}

	$scope.row_del = function(id) {
		$confirm({
			text : '是否删除功能?'
		})
				.then(
						function() {
							$http
									.post(
											$rootScope.project
													+ "/api/flow/sysProcessClass/deleteById.do",
											{
												id : id
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

	$scope.save = function(id) {

		var meta = {};

		var items = [ {
			type : "input",
			disabled : "false",
			sub_type : "text",
			required : true,
			maxlength : "100",
			placeholder : "请输入名称",
			label : "名称",
			need : true,
			name : 'name',
			ng_model : "name"
		}, {
			type : "input",
			disabled : "false",
			sub_type : "text",
			required : false,
			maxlength : "500",
			placeholder : "请输入备注",
			label : "备注",
			need : false,
			name : 'mark',
			ng_model : "mark"
		} ];

		meta = {
			id : id,
			footer_hide : false,
			title : "基本信息",
			item : {},
			typeSel : "",
			items : items,
			sure : function(modalInstance, modal_meta) {
				modal_meta.meta.item.attrType = modal_meta.meta.typeSel.id;
				$http
						.post(
								$rootScope.project
										+ "/api/flow/sysProcessClass/insertOrUpdate.do",
								modal_meta.meta.item).success(function(res) {
							if (res.success) {
								modalInstance.close("OK");
							} else {
								notify({
									message : res.message
								});
							}
						});

			},
			init : function(modal_meta) {
			 

				if (angular.isDefined(modal_meta.meta.id)) {
					$http
							.post(
									$rootScope.project
											+ "/api/flow/sysProcessClass/selectById.do",
									{
										id : modal_meta.meta.id
									}).success(function(res) {
								if (res.success) {
									modal_meta.meta.item = res.data;

								} else {
									notify({
										message : res.message
									});
								}
							});

				}
			}
		}

		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/Template/modal_simpleForm.html',
			controller : modal_simpleFormCtl,
			size : 'lg',
			resolve : { // 调用控制器与modal控制器中传递值
				meta : function() {
					return meta;
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

app.register.controller('formSettingCtl', formSettingCtl);