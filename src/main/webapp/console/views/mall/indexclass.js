function indexclassSaveCtl($localStorage, notify, $log, $uibModal,
		$uibModalInstance, $scope, id, $http, $rootScope) {
	$log.warn("window in:" + id);
	$scope.item = {};

	$scope.statusOpt = [ {
		ID : "Y",
		NAME : "有效"
	}, {
		ID : "N",
		NAME : "无效"
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
				if ($scope.item.IS_USED == "Y") {
					$scope.statusSel = $scope.statusOpt[0];
				} else if ($scope.item.IS_USED == "N") {
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
		$scope.item.IS_USED = $scope.statusSel.ID;
		$scope.item.MODULE = "mall";
		$scope.item.TYPE = "microshopindex";

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

function indexClassItemSaveCtl(DTLang, DTOptionsBuilder, DTColumnBuilder,
		$compile, $confirm, notify, $log, $uibModal, $uibModalInstance, $scope,
		data, $http, $rootScope) {
	$log.warn("window in:" + data);
	$scope.prodcatOpt=[];
	$scope.prodcatSel="";
	// 获取类目选择
	$http.post($rootScope.project + "/api/categoryB/prodPublishCatList.do", {}).success(function(res) {
		if (res.success) {
			$scope.prodcatOpt = res.data;
			$scope.prodcatSel = $scope.prodcatOpt[0];
		} else {
			notify({
				message : res.message
			});
		}
	})
	
	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withPaginationType(
			'full_numbers').withDisplayLength(25).withOption("ordering", false)
			.withOption("responsive", true).withOption("searching", false)
			.withOption("paging", false).withOption('bStateSave', true)
			.withOption('bProcessing', true).withOption('bFilter', false)
			.withOption('bInfo', false).withOption('serverSide', false)
			.withOption('bAutoWidth', false).withOption('aaData',
					$scope.tabdata).withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			}).withLanguage(DTLang).withOption("select", {
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

	function renderImage(data, type, full) {
		var html = ""
		html = html + "<img style='height:50px;width:50px;' src=" + $rootScope.project + "/api/file/imagedown.do?id=" + full.MASTER_PIC + "  />"
		return html;
	}
	
	$scope.dtColumns = [
			DTColumnBuilder.newColumn(null).withTitle('').withClass(
					'select-checkbox').renderWith(function() {
				return '';
			}),
			DTColumnBuilder.newColumn('MASTER_PIC').withTitle('图片').withOption(
					'sDefaultContent', '').renderWith(renderImage),
			DTColumnBuilder.newColumn('PROD_NAME').withTitle('商品名称')
					.withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('LIST_PRICE').withTitle('列表价')
					.withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('STOCK').withTitle('库存').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('CODE').withTitle('货号').withOption(
					'sDefaultContent', '') ]

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

	$scope.search=function(){
		
	}
	$scope.sure = function() {
		// $http.post($rootScope.project + "/api/dict/saveDictItem.do",
		// $scope.item).success(function(res) {
		// if (res.success) {
		// $uibModalInstance.close("OK");
		// } else {
		// notify({
		// message : res.message
		// });
		// }
		// })
	};

}

function shopIndexClassCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile,
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
			}).withLanguage(DTLang);

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
		flushSubtab(data.DICT_ID);
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
			DTColumnBuilder.newColumn('NAME').withTitle('名称').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('IS_USED').withTitle('状态').withOption(
					'sDefaultContent', '').renderWith(renderMStatus) ]

	function flush() {
		var ps = {};
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
			size : 'md',
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
			return $scope.dtOptions.aaData[data[0]].CLASS_ID;
		}
	}

	/** ********************子表******************* */

	$scope.dtItemOptions = DTOptionsBuilder.fromFnPromise().withPaginationType(
			'full_numbers').withDisplayLength(25).withOption("ordering", false)
			.withOption("responsive", true).withOption("searching", false)
			.withOption("paging", false).withOption('bStateSave', true)
			.withOption('bProcessing', true).withOption('bFilter', false)
			.withOption('bInfo', false).withOption('serverSide', false)
			.withOption('bAutoWidth', false).withOption('createdRow',
					function(row) {
						// Recompiling so we can bind Angular,directive to the
						$compile(angular.element(row).contents())($scope);
					}).withLanguage(DTLang);
	$scope.dtItemInstance = {}

	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";
		// acthtml = acthtml + " <button ng-click=\"row_update('" +
		// full.DICT_ITEM_ID + "')\" class=\"btn-white btn btn-xs\">更新</button>
		// ";
		acthtml = acthtml + " <button ng-click=\"row_dtl('" + full.DICT_ITEM_ID
				+ "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
		return acthtml;
	}

	$scope.dtItemColumns = [
			DTColumnBuilder.newColumn('NAME').withTitle('名称').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('DICT_ID').withTitle('动作').withOption(
					'sDefaultContent', '').renderWith(renderAction) ]

	function flushSubtab(id) {

		var ps = {
			id : id
		};

		// id不存在,则尝试从select中获取
		if (!angular.isDefined(id)) {
			ps.id = getSelectId();
		}
		// 如果还是不存在则报错
		if (!angular.isDefined(ps.id)) {
			notify({
				message : "ID不存在"
			});
			return;
		}

		$http.post($rootScope.project + "/api/dict/queryDictItem.do", ps)
				.success(function(res) {
					if (res.success) {
						$scope.dtItemOptions.aaData = res.data;
					} else {
						notify({
							message : res.message
						});
					}
				})

	}

	$scope.itemquery = function() {
		flushSubtab();
	}

	$scope.row_update = function(id) {

		var ps = {};
		ps.dict_item_id = id;
		ps.dict_id = getSelectId();
		if (!angular.isDefined(ps.dict_id)) {
			notify({
				message : "ID不存在"
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
				flushSubtab();
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
			$http.post($rootScope.project + "/api/dict/deleteDictItem.do", {
				id : id
			}).success(function(res) {
				if (res.success) {
					flushSubtab();
				}
				notify({
					message : res.message
				});
			})
		});

	}
};

app.register.controller('shopIndexClassCtl', shopIndexClassCtl);