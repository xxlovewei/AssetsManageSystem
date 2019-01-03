function cmdbHardCtl(DTOptionsBuilder, DTColumnBuilder, $compile, $confirm,
		$log, notify, $scope, $http, $rootScope, $uibModal) {

	var meta = {
		tablehide : false,
		tools : [
				{
					id : "select",
					label : "分类",
					type : "select",
					disablesearch : true,
					dataOpt : [],
					dataSel : ""
				},
				{
					id : "select",
					label : "配置项",
					type : "select",
					disablesearch : true,
					dataOpt : [],
					dataSel : ""
				},
				{
					id : "btn",
					label : "",
					type : "btn",
					template : ' <button ng-click="query()" class="btn btn-sm btn-primary" type="submit">查询</button>'
				},
				{
					id : "btn2",
					label : "",
					type : "btn",
					template : ' <button ng-click="save()" class="btn btn-sm btn-primary" type="submit">新增</button>'
				} ]
	}
	$scope.meta = meta;

	// 分类
	$http
			.post(
					$rootScope.project
							+ "/api/cmdb/resClass/queryCategoryChildren.do?parent_id=3&is_action=Y",
					{}).success(function(res) {
				if (res.success) {
					$scope.meta.tools[0].dataOpt = res.data;
					;
					if (res.data.length > 0) {
						$scope.meta.tools[0].dataSel = res.data[0];
					}
				} else {
					notify({
						message : res.message
					});
				}
			})

	var watch2 = $scope.$watch('meta.tools[0].dataSel', function(oldValue,
			newValue, scope) {
		if (angular.isDefined($scope.meta.tools[0].dataSel)) {

			var ps = {};
			ps.class_id = $scope.meta.tools[0].dataSel.id;
			$http.post(
					$rootScope.project
							+ "/api/cmdb/resClass/queryConfItemByCategory.do",
					ps).success(function(xlres) {
				if (xlres.success) {

					$scope.meta.tools[1].dataOpt = xlres.data;
					if (xlres.data.length > 0) {
						$scope.meta.tools[1].dataSel = xlres.data[0];
						// $scope.confSel.class_id=xlres.data[0];
					}

				}
			})
		}
	});
	// 自动获取配置项

	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withOption(
			'createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			});

	// $scope.dtOptions = DTOptionsBuilder.fromFnPromise().withOption('scrollY',
	// '800px').withOption('scrollX', true).withOption('bAutoWidth', true)
	// .withOption('responsive', false).withOption('scrollCollapse', true)
	// .withOption('paging', true).withFixedColumns({
	// leftColumns : 0,
	// rightColumns : 1
	// })
	//	

	$scope.dtInstance = {}

	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";
		acthtml = acthtml + " <button ng-click=\"save('" + full.id
				+ "')\" class=\"btn-white btn btn-xs\">更新</button>  ";
		acthtml = acthtml + " <button ng-click=\"del('" + full.id
				+ "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";

		return acthtml;
	}

	$scope.dtColumns = [
			DTColumnBuilder.newColumn('uuid').withTitle('编号').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('name').withTitle('名称').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('statusstr').withTitle('状态').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('envstr').withTitle('环境').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('mainlevelstr').withTitle('等级')
					.withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('version').withTitle('版本').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('pinpstr').withTitle('品牌').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('locstr').withTitle('位置').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('headuseridstr').withTitle('负责人')
					.withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('id').withTitle('操作').withOption(
					'sDefaultContent', '').renderWith(renderAction) ]

	function flush() {
		var ps = {}
		if (angular.isDefined($scope.meta.tools[1].dataSel.class_id)) {
			ps.id = $scope.meta.tools[1].dataSel.class_id;
		}
		$http
				.post(
						$rootScope.project
								+ "/api/cmdb/resClass/queryResByConfItem.do",
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

	$scope.query = function() {
		flush();

	}

	$scope.del = function(seq) {
		alert("待开发");

	}

	function loadOpt(modal_meta) {
		// 品牌
		var item = modal_meta.meta.item;

		$http.post(
				$rootScope.project
						+ "/api/sysDictItem/selectDictItemByDict.do ", {
					dictId : "1079765355797626882"
				}).success(function(res) {
			console.log(res);
			modal_meta.meta.pinpOpt = res.data;

			if (res.data.length > 0) {
				if (angular.isDefined(item) && angular.isDefined(item.pinp)) {
					for (var i = 0; i < res.data.length; i++) {
						if (res.data[i].dictItemId == item.pinp) {
							modal_meta.meta.pinpSel = res.data[i];
						}
					}
				} else {
					if (res.data.length > 0) {
						modal_meta.meta.pinpSel = res.data[0];
					}
				}
			}
		});
		// 负责人
		$http.post(
				$rootScope.project
						+ "/api/sysDictItem/selectDictItemByDict.do ", {
					dictId : "1079009262096052225"
				}).success(
				function(res) {
					console.log(res);
					modal_meta.meta.headuserOpt = res.data;
					if (res.data.length > 0) {

						if (angular.isDefined(item)
								&& angular.isDefined(item.headuserid)) {
							for (var i = 0; i < res.data.length; i++) {
								if (res.data[i].dictItemId == item.headuserid) {
									modal_meta.meta.headuserSel = res.data[i];
								}
							}
						} else {
							if (res.data.length > 0) {
								modal_meta.meta.headuserSel = res.data[0];
							}
						}
					}
				});

		// 等级
		$http.post(
				$rootScope.project
						+ "/api/sysDictItem/selectDictItemByDict.do ", {
					dictId : "1079695969170706433"
				}).success(
				function(res) {
					modal_meta.meta.mainlevelOpt = res.data;
					if (res.data.length > 0) {

						if (angular.isDefined(item)
								&& angular.isDefined(item.mainlevel)) {
							for (var i = 0; i < res.data.length; i++) {
								if (res.data[i].dictItemId == item.mainlevel) {
									modal_meta.meta.mainlevelSel = res.data[i];
								}
							}
						} else {
							if (res.data.length > 0) {
								modal_meta.meta.mainlevelSel = res.data[0];
							}
						}
					}
				});
		// 环境
		$http.post(
				$rootScope.project
						+ "/api/sysDictItem/selectDictItemByDict.do ", {
					dictId : "1079009262096052225"
				}).success(function(res) {
			modal_meta.meta.envOpt = res.data;
			if (res.data.length > 0) {
				if (angular.isDefined(item) && angular.isDefined(item.env)) {

					for (var i = 0; i < res.data.length; i++) {
						if (res.data[i].dictItemId == item.env) {
							modal_meta.meta.envSel = res.data[i];
						}
					}
				} else {
					if (res.data.length > 0) {
						modal_meta.meta.envSel = res.data[0];
						console.log('adfasdfasdf', res.data[0])
					}
				}
			}
		});
		// 状态
		$http.post(
				$rootScope.project
						+ "/api/sysDictItem/selectDictItemByDict.do ", {
					dictId : "1079695434816376834"
				}).success(function(res) {
			console.log(res);
			modal_meta.meta.statusOpt = res.data;
			if (res.data.length > 0) {
				if (angular.isDefined(item) && angular.isDefined(item.status)) {

					for (var i = 0; i < res.data.length; i++) {
						if (res.data[i].dictItemId == item.status) {
							modal_meta.meta.statusSel = res.data[i];
						}
					}
				} else {
					if (res.data.length > 0) {
						modal_meta.meta.statusSel = res.data[0];
					}
				}
			}
		});

		// 位置
		$http.post(
				$rootScope.project
						+ "/api/sysDictItem/selectDictItemByDict.do ", {
					dictId : "1079696267025010689"
				}).success(function(res) {
			modal_meta.meta.locOpt = res.data;
			if (res.data.length > 0) {

				if (angular.isDefined(item) && angular.isDefined(item.loc)) {
					for (var i = 0; i < res.data.length; i++) {
						if (res.data[i].dictItemId == item.loc) {
							modal_meta.meta.locSel = res.data[i];
						}
					}
				} else {
					if (res.data.length > 0) {
						modal_meta.meta.locSel = res.data[0];
					}
				}
			}
		});

		// 维保
		$http.post(
				$rootScope.project
						+ "/api/sysDictItem/selectDictItemByDict.do ", {
					dictId : "1079010352015306754"
				}).success(
				function(res) {
					modal_meta.meta.companyOpt = res.data;
					if (res.data.length > 0) {

						if (angular.isDefined(item)
								&& angular.isDefined(item.company)) {
							for (var i = 0; i < res.data.length; i++) {
								if (res.data[i].dictItemId == item.company) {
									modal_meta.meta.companySel = res.data[i];
								}
							}
						} else {
							if (res.data.length > 0) {
								modal_meta.meta.companySel = res.data[0];
							}
						}
					}
				});

	}

	// //////////////////////////save/////////////////////
	$scope.save = function(id) {

		var class_id = "";
		console.log($scope.meta.tools[1].dataSel);
		if (!angular.isDefined(id)) {
			// 获取class_id
			console.log($scope.meta.tools[1].dataSel);
			if (angular.isDefined($scope.meta.tools[1].dataSel)
					&& $scope.meta.tools[1].dataSel != null
					&& angular.isDefined($scope.meta.tools[1].dataSel.class_id)) {
				class_id = $scope.meta.tools[1].dataSel.class_id;
			} else {
				alert("请选择配置项!");
				return;
			}
		}

		$http
				.post($rootScope.project + "/api/cmdb/res/queryResAllById.do",
						{
							id : id,
							class_id : class_id
						})
				.success(
						function(res) {
							console.log(res.data);
							if (!res.success) {
								notify({
									message : res.message
								});
								return;
							}
							var meta = {};

							var items = [ {
								type : "input",
								disabled : "false",
								sub_type : "text",
								required : false,
								maxlength : "50",
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
								maxlength : "50",
								placeholder : "请输入序列号",
								label : "序列号",
								need : true,
								name : 'sn',
								ng_model : "sn"
							}, {
								type : "input",
								disabled : "false",
								sub_type : "text",
								required : false,
								maxlength : "50",
								placeholder : "请输入版本",
								label : "版本",
								need : true,
								name : 'version',
								ng_model : "version"
							}, {
								type : "input",
								disabled : "false",
								sub_type : "text",
								required : false,
								maxlength : "50",
								placeholder : "请输入描述",
								label : "描述",
								need : true,
								name : 'describe',
								ng_model : "describe"
							}, {
								type : "datetime",
								disabled : "false",
								label : "开始时间",
								need : true,
								ng_model : "stime"
							}, {
								type : "select",
								disabled : "false",
								label : "状态",
								need : false,
								disable_search : "true",
								dataOpt : "statusOpt",
								dataSel : "statusSel"
							}, {
								type : "select",
								disabled : "false",
								label : "环境",
								need : false,
								disable_search : "true",
								dataOpt : "envOpt",
								dataSel : "envSel"
							}, {
								type : "select",
								disabled : "false",
								label : "负责人",
								need : false,
								disable_search : "true",
								dataOpt : "headuserOpt",
								dataSel : "headuserSel"
							}, {
								type : "select",
								disabled : "false",
								label : "位置",
								need : false,
								disable_search : "true",
								dataOpt : "locOpt",
								dataSel : "locSel"
							}, {
								type : "select",
								disabled : "false",
								label : "等级",
								need : false,
								disable_search : "true",
								dataOpt : "mainlevelOpt",
								dataSel : "mainlevelSel"
							}, {
								type : "select",
								disabled : "false",
								label : "品牌",
								need : false,
								disable_search : "true",
								dataOpt : "pinpOpt",
								dataSel : "pinpSel"
							}, {
								type : "select",
								disabled : "false",
								label : "维保",
								need : false,
								disable_search : "true",
								dataOpt : "companyOpt",
								dataSel : "companySel"
							} ];
							meta = {
								class_id : class_id,
								footer_hide : false,
								title : "基础设施",
								item : {},
								stime : moment().subtract(15, "days"),
								statusOpt : [],
								statusSel : "",
								pinpOpt : [],
								pinpSel : "",
								headuserOpt : [],
								headuserSel : "",
								locOpt : [],
								locSel : "",
								envOpt : [],
								envSel : "",
								mainlevelOpt : [],
								mainlevelSel : "",
								companyOpt : [],
								companySel : "",
								items : items,
								sure : function(modalInstance, modal_meta) {
									// 返回接口
									if (!angular
											.isDefined(modal_meta.meta.item.id)) {
										modal_meta.meta.item.class_id = modal_meta.meta.class_id;
									}
									modal_meta.meta.item.env = modal_meta.meta.envSel.dictItemId;
									modal_meta.meta.item.loc = modal_meta.meta.locSel.dictItemId;
									modal_meta.meta.item.mainlevel = modal_meta.meta.mainlevelSel.dictItemId;
									modal_meta.meta.item.company = modal_meta.meta.companySel.dictItemId;
									modal_meta.meta.item.status = modal_meta.meta.statusSel.dictItemId;
									modal_meta.meta.item.pinp = modal_meta.meta.pinpSel.dictItemId;
									modal_meta.meta.item.headuserid = modal_meta.meta.headuserSel.dictItemId;
									// 动态参数
									console.log(modal_meta.meta.attr)
									if (angular.isDefined(modal_meta.meta.attr)
											&& modal_meta.meta.attr.length > 0) {
										for (var j = 0; j < modal_meta.meta.attr.length; j++) {
											var code = modal_meta.meta.attr[j].attr_code;
											modal_meta.meta.attr[j].attr_value = modal_meta.meta.item[modal_meta.meta.attr[j].attr_code];
										}
									}
									modal_meta.meta.item.attrvals = angular
											.toJson(modal_meta.meta.attr);
									console.log(modal_meta.meta);
									$http
											.post(
													$rootScope.project
															+ "/api/cmdb/res//addResCustom.do",
													modal_meta.meta.item)
											.success(function(res) {

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

									loadOpt(modal_meta);

								}
							}

							//
							if (angular.isDefined(res.data.data)
									&& angular.isDefined(res.data.data.id)) {
								meta.item = res.data.data;
								// 填充其他数据
							}

							// 补充属性数据
							if (angular.isDefined(res.data.attr)
									&& res.data.attr.length > 0) {
								meta.attr = res.data.attr;
								for (var i = 0; i < res.data.attr.length; i++) {
									if (i == 0) {
										var e = {
											type : "dashed"
										}
										items.push(e);
									}

									var attr_type = res.data.attr[i].attr_type;
									if (attr_type == "string") {
										var e = {
											type : "input",
											disabled : "false",
											sub_type : "text",
											required : true,
											maxlength : "100",
											placeholder : "请输入内容",
											label : res.data.attr[i].attr_name,
											need : false,
											name : res.data.attr[i].attr_code,
											ng_model : res.data.attr[i].attr_code,
										}
										items.push(e);
									} else if (attr_type == "number") {
										var e = {
											type : "input",
											disabled : "false",
											sub_type : "number",
											required : true,
											maxlength : "20",
											placeholder : "请输入内容",
											label : res.data.attr[i].attr_name,
											need : false,
											name : res.data.attr[i].attr_code,
											ng_model : res.data.attr[i].attr_code,
										}
										items.push(e);
									}

								}

							}

							console.log(meta);
							// 打开静态框
							var modalInstance = $uibModal
									.open({
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

						})

	}

};

app.register.controller('cmdbHardCtl', cmdbHardCtl);