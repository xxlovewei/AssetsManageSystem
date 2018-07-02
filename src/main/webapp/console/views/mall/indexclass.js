function indexclassSaveCtl($localStorage, notify, $log, $uibModal,
		$uibModalInstance, $scope, id, $http, $rootScope) {
	$log.warn("window in:" + id);
	$scope.item = {};

	$scope.statusOpt = [ {
		id : "Y",
		name : "有效"
	}, {
		id : "N",
		name : "无效"
	} ]
	$scope.statusSel = $scope.statusOpt[0];
	if (angular.isDefined(id)) {
		// 加载数据
		$http.post($rootScope.project + "/api/class/queryClassById.do", {
			class_id : id
		}).success(function(res) {
			if (res.success) {
				$scope.item = res.data
				// STATUS
				if ($scope.item.is_used == "Y") {
					$scope.statusSel = $scope.statusOpt[0];
				} else if ($scope.item.is_used == "N") {
					$scope.statusSel = $scope.statusOpt[1];
				}

			} else {
				notify({
					message : res.message
				});
			}
		})
	}

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

	$scope.sure = function() {
		$scope.item.is_used = $scope.statusSel.id;
		$scope.item.module = "mall";
		$scope.item.type = "microshopindex";

		$http.post($rootScope.project + "/api/class/saveClass.do", $scope.item)
				.success(function(res) {
					if (res.success) {
						$uibModalInstance.close("OK");
					} else {
						notify({
							message : res.message
						});
					}
				})
	};

}

function indexClassItemSaveCtl( DTOptionsBuilder, DTColumnBuilder,
		$compile, $confirm, notify, $log, $uibModal, $uibModalInstance, $scope,
		data, $http, $rootScope) {
	$log.warn("window in:", data);
	$scope.prodcatOpt = [];
	$scope.prodcatSel = {
		id : "-1"
	};
	if (!angular.isDefined(data.class_id)) {
		data.class_id = "-1";
	}
	// 获取类目选择
	$http.post($rootScope.project + "/api/categoryB/prodPublishCatList.do", {})
			.success(function(res) {
				if (res.success) {
					$scope.prodcatOpt = res.data;
					$scope.prodcatSel = $scope.prodcatOpt[0];
				} else {
					notify({
						message : res.message
					});
				}
			})

	$scope.URL = $rootScope.project
			+ "/api/class/queryClassProdNotSel.do?cat_id="
			+ $scope.prodcatSel.id + "&class_id=" + data.class_id;
	$scope.dtOptions = DTOptionsBuilder.fromSource($scope.URL).withDataProp(
			'data').withPaginationType('full_numbers').withDisplayLength(10)
			.withOption("ordering", false).withOption("responsive", true)
			.withOption("searching", false).withOption("paging", true)
			.withOption('bStateSave', true).withOption('bProcessing', false)
			.withOption('bFilter', false).withOption('bInfo', false)
			.withOption('serverSide', true).withOption('bAutoWidth', false)
			.withOption('aaData', $scope.tabdata).withOption('createdRow',
					function(row) {
						// Recompiling so we can bind Angular,directive to the
						$compile(angular.element(row).contents())($scope);
					}).withOption("select", {
				style : 'multi',
				selector : 'td:first-child'
			}).withButtons([ {
				text : '全选',
				key : '1',
				action : function(e, dt, node, config) {
					dt.rows().select();
				}
			}, {
				text : '全不选',
				key : '1',
				action : function(e, dt, node, config) {

					dt.rows().deselect();
				}
			} ]);

	$scope.dtInstance = {}
	var tabdata = {};
	$scope.reloadData = reloadData;
	function reloadData() {
		var resetPaging = false;
		$scope.dtInstance.reloadData(callback, resetPaging);
	}

	function callback(json) {
		 
		tabdata = json.data;
	}
	function renderImage(data, type, full) {
		var html = ""
		html = html + "<img style='height:50px;width:50px;' src="
				+ $rootScope.project + "/api/file/imagedown.do?id="
				+ full.master_pic + "  />"
		return html;
	}

	$scope.dtColumns = [
			DTColumnBuilder.newColumn(null).withTitle('').withClass(
					'select-checkbox').renderWith(function() {
				return '';
			}),
			DTColumnBuilder.newColumn('pic_id').withTitle('图片').withOption(
					'sDefaultContent', '').renderWith(renderImage),
			DTColumnBuilder.newColumn('prod_name').withTitle('商品名称')
					.withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('list_price').withTitle('列表价')
					.withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('stock').withTitle('库存').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('code').withTitle('货号').withOption(
					'sDefaultContent', '') ]

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

	$scope.search = function() {
		$scope.URL = $rootScope.project
				+ "/api/class/queryClassProdNotSel.do?cat_id="
				+ $scope.prodcatSel.id + "&class_id=" + data.class_id;
		$scope.dtOptions.ajax = $scope.URL;
		reloadData();
	}
	$scope.sure = function() {
		var seldata = $scope.dtInstance.DataTable.rows({
			selected : true
		})[0];
		var tabdata = $scope.dtInstance.DataTable.ajax.json().data;
		if (tabdata.length == 0 || seldata.length == 0) {
			notify({
				message : "请选择数据"
			});
			return;
		}
		var ids = [];
		for (var i = 0; i < seldata.length; i++) {
			console.log(tabdata[seldata[i]]);
			ids.push(tabdata[seldata[i]].spu);
		}

		var ps = {};
		ps.class_id = data.class_id;
		ps.ids = angular.toJson(ids);
		$http.post($rootScope.project + "/api/class/addClassItems.do", ps)
				.success(function(res) {
					if (res.success) {
						$uibModalInstance.dismiss('cancel');
					}
					notify({
						message : res.message
					});
				})
	};

}

function shopIndexClassCtl( DTOptionsBuilder, DTColumnBuilder, $compile,
		$confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withPaginationType(
			'full_numbers').withDisplayLength(25).withOption("ordering", false)
			.withOption("responsive", true).withOption("searching", false)
			.withOption("paging", false).withOption('bStateSave', true)
			.withOption('bProcessing', true).withOption('bFilter', false)
			.withOption('bInfo', false).withOption('serverSide', false)
			.withOption('bAutoWidth', false).withOption('rowCallback',
					rowCallback).withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			}).withOption("select", {
				style : 'single'
			});

	$scope.dtInstance = {}

	function rowCallback(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
		// Unbind first in order to avoid any duplicate handler
		// (see https://github.com/l-lin/angular-datatables/issues/87)
		$('td', nRow).unbind('click');
		$('td', nRow).bind('click', function() {
			$scope.$apply(function() {
				someClickHandler(aData);
			});
		});
		return nRow;
	}
	function someClickHandler(data) {
		console.log("click", data);
		flushSubtab(data.class_id,true);
	}

	function renderMStatus(data, type, full) {
		if (data == "Y") {
			return "有效";
		} else if (data == "N") {
			return "无效";
		} else {
			return data;
		}
	}

	$scope.dtColumns = [
			DTColumnBuilder.newColumn('name').withTitle('名称').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('is_used').withTitle('状态').withOption(
					'sDefaultContent', '').renderWith(renderMStatus) ]

	function flush() {
		var ps = {};
		ps.type="microshopindex";
		$http.post($rootScope.project + "/api/class/queryClass.do", ps)
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

	function save(id) {

		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/mall/modal_indexclasssave.html',
			controller : indexclassSaveCtl,
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
	$scope.add = function() {
		save()
	}
	$scope.del = function() {

		var id = getSelectId();
		if (!angular.isDefined(id)) {
			notify({
				message : "请选择一行"
			});
			return;
		}

		$confirm({
			text : '是否删除?'
		}).then(function() {
			$http.post($rootScope.project + "/api/class/deleteClass.do", {
				class_id : id
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
	$scope.update = function() {
		var id = getSelectId();
		if (angular.isDefined(id)) {
			save(id)
		} else {
			notify({
				message : "请选择一行"
			});
		}

	}

	function getSelectId() {
		var data = $scope.dtInstance.DataTable.rows({
			selected : true
		})[0];
		// 没有选择,或选择多行都返回错误
		if (data.length == 0 || data.length > 1) {
			return;
		} else {
			return $scope.dtOptions.aaData[data[0]].class_id;
		}
	}

	/** ********************子表******************* */
	$scope.ITEMURL = $rootScope.project
			+ "/api/class/queryClassProd.do?class_id=-1";
	$scope.dtItemOptions = DTOptionsBuilder.fromSource($scope.ITEMURL)
			.withDataProp('data').withPaginationType('full_numbers')
			.withDisplayLength(25).withOption("ordering", false).withOption(
					"responsive", true).withOption("searching", false)
			.withOption("paging", true).withOption('bStateSave', true)
			.withOption('bProcessing', true).withOption('bFilter', false)
			.withOption('bInfo', false).withOption('serverSide', true)
			.withOption('bAutoWidth', false).withOption('createdRow',
					function(row) {
						// Recompiling so we can bind Angular,directive to the
						$compile(angular.element(row).contents())($scope);
					});
	$scope.dtItemInstance = {}
	$scope.reloadItemData = reloadItemData;
	function reloadItemData(reset) {
		$scope.dtItemInstance.reloadData(callbackitem, reset);
	}
	function callbackitem(json) {
		console.log(json);
	}
	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";
		acthtml = acthtml + " <button ng-click=\"row_dtl('" + full.item_id
				+ "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
		return acthtml;
	}

	$scope.dtItemColumns = [
			DTColumnBuilder.newColumn('prod_name').withTitle('名称').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('is_off').withTitle('上架').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('stock').withTitle('库存').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('sales').withTitle('销售').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('spu').withTitle('操作').withOption(
					'sDefaultContent', '').renderWith(renderAction) ]

	function flushSubtab(id,reset) {

		
		var class_id = id;
		// id不存在,则尝试从select中获取
		if (!angular.isDefined(id)||id=="") {
			class_id = getSelectId();
		}
		// 如果还是不存在则报错
		if (!angular.isDefined(class_id) ) {
			notify({
				message : "ClassId不存在"
			});
			return;
		}
		$scope.ITEMURL = $rootScope.project
				+ "/api/class/queryClassProd.do?class_id=" + class_id
		console.log($scope.ITEMURL);
		$scope.dtItemOptions.ajax = $scope.ITEMURL;
		reloadItemData(reset);

	}

	$scope.itemquery = function() {
		flushSubtab("",true);
	}

	$scope.row_update = function(id) {

		var ps = {};
		ps.class_item_id = id;
		ps.class_id = getSelectId();
		if (!angular.isDefined(ps.class_id)) {
			notify({
				message : "ClassId不存在"
			});
			return;
		}

		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/mall/modal_indexclassItemAdd.html',
			controller : indexClassItemSaveCtl,
			size : 'lg',
			resolve : { // 调用控制器与modal控制器中传递值
				data : function() {
					return ps;
				}
			}
		});
		modalInstance.result.then(function(result) {
			$log.log("result", result);
			if (result == "OK") {
				flushSubtab("",true);
			}
		}, function(reason) {
			// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
			$log.log("reason", reason)
		});
	}
	$scope.row_dtl = function(id) {
		$confirm({
			text : '是否删除?'
		}).then(function() {
			$http.post($rootScope.project + "/api/class/deleteClassItem.do", {
				id : id
			}).success(function(res) {
				if (res.success) {
					flushSubtab("",false);
				}
				notify({
					message : res.message
				});
			})
		});

	}
};

app.register.controller('shopIndexClassCtl', shopIndexClassCtl);