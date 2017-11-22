function prepend(arr, item) {
	// 将arr数组复制给a
	var a = arr.slice(0);
	// 使用unshift方法向a开头添加item
	a.unshift(item);
	return a;
}
function prodPicsAddCtl($compile, $timeout, $confirm, $log, notify, $scope, $http, $rootScope, $uibModalInstance) {

	$scope.dtldzconfig = {
		url : 'fileupload.do',
		maxFilesize : 10000,
		paramName : "file",
		maxThumbnailFilesize : 1,
		// 一个请求上传多个文件
		uploadMultiple : true,
		// 当多文件上传,需要设置parallelUploads>=maxFiles
		parallelUploads : 1,
		maxFiles : 1,
		dictDefaultMessage : "点击上传图片",
		acceptedFiles : "image/jpeg,image/png,image/gif",
		// 添加上传取消和删除预览图片的链接，默认不添加
		addRemoveLinks : true,
		// 关闭自动上传功能，默认会true会自动上传
		// 也就是添加一张图片向服务器发送一次请求
		autoProcessQueue : false,
		init : function() {
			$scope.myDropzone = this; // closure
		}
	};

	$scope.sure = function() {
		if ($scope.myDropzone.files.length == 0) {
			notify({
				message : "请选择图片"
			});
			return;
		}
		var picid = getUuid();
		$scope.myDropzone.options.url = $rootScope.project + '/api/file/fileupload.do?bus=prodimgs&uuid=' + picid + '&type=image&interval=10000';
		$scope.myDropzone.uploadFile($scope.myDropzone.files[0])
		$timeout(function() {
			$uibModalInstance.close(picid);
		}, 600);
	}

	
	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
}

function prodPicUpdateCtl($log, $http, $rootScope, $scope, $uibModal, $uibModalInstance, data, notify) {

	$log.warn("window in:", data)

	$scope.prodPics = [];
	if (angular.isDefined(data.spu)) {
		$http.post($rootScope.project + "/api/product/getProdPics.do", {
			spu : data.spu
		}).success(function(res) {
			if (res.success) {
				$scope.prodPics = res.data;
			} else {
				notify({
					message : res.message
				});
			}
		})
	}

	$scope.addPicture = function() {
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/product/modal_prodPics_add.html',
			controller : prodPicsAddCtl,
			size : 'lg',
			resolve : { // 调用控制器与modal控制器中传递值
				data : function() {
					return {}
				}
			}
		});

		modalInstance.result.then(function(result) {
			$log.warn("result:" + result);
			$scope.prodPics.push({
				pic_id : result,
				od : 1
			})
		}, function(reason) {
			// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
			$log.log("reason", reason)
		});

	}
	$scope.delpic = function(item) {
		if ($scope.prodPics.length == 1) {
			notify({
				message : "至少保留一张图片"
			});
			return;
		}
		for (var i = 0; i < $scope.prodPics.length; i++) {
			if (item.PIC_ID == $scope.prodPics[i].PIC_ID) {
				$scope.prodPics.splice(i, 1);
				break;
			}
		}
	}
	$scope.sure = function() {
		$http.post($rootScope.project + "/api/product/updateProdPics.do", {
			spu : data.spu,
			pics:angular.toJson($scope.prodPics)
		}).success(function(res) {
			if (res.success) {
				$uibModalInstance.close("OK");
			} else {
				notify({
					message : res.message
				});
			}
		})
		
		
	};
	///product/updateProdPics.do
	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

}
function prodSaleAttrSaveCtl($log, $http, $rootScope, $scope, $uibModalInstance, data, notify) {

	$log.warn("window in:", data);
	$scope.if_publish = false;
	$scope.prodData = {}
	// 获取销售属性
	$scope.sale_attr = [];
	var SALE_ATTR_SET_MAP = [];
	var if_rebuild = "N";
	// 获取产品信息

	$http.post($rootScope.project + "/api/product/prodQueryBySpu.do", {
		spu : data.spu
	}).success(function(res) {
		$log.warn(res);
		if (res.success) {
			$scope.prodData = res.data;
			$scope.prodSku = res.data.SALE_DATA_LIST;
			// 获取商品模版数据
			$http.post($rootScope.project + "/api/categoryB/prodPublishCatAttrList.do", {
				cat_id : $scope.prodData.CAT_ID,
				is_used : "Y",
				base_attr : "N"
			}).success(function(res) {
				if (res.success) {
					// 销售属性必须要有,如果没有则提醒下,无法发布产品
					if (res.data.SALE_ATTR.length == 0) {
						notify({
							message : "无销售属性,不可发布商品"
						});
					} else {
						$scope.if_publish = true;
					}
					// 初始化数据属性
					$scope.sale_attr = res.data.SALE_ATTR;
					SALE_ATTR_SET_MAP = res.data.SALE_ATTR_SET_MAP;
				} else {
					notify({
						message : res.message
					});
				}
			})
		} else {
			notify({
				message : res.message
			});
		}
	})

	/** ********************处理销售属性************************ */
	var saleAttrValueContain = [];
	$scope.saleattrvaluechange = function(item, attrvalue) {
		// $log.warn(item, attrvalue);
		var isExist = false;
		for (var i = 0; i < saleAttrValueContain.length; i++) {
			if (saleAttrValueContain[i].ID == attrvalue.ID) {
				// 已经存在,则移除
				// $log.warn('to remove,' + i);
				saleAttrValueContain.splice(i, 1);
				return;
			}
		}
		saleAttrValueContain.push(attrvalue);
	}

	// sku列表匹配值,渲染输出
	$scope.saleValueRender = function(e) {

		console.log("SALE_ATTR_SET_MAP:", SALE_ATTR_SET_MAP);
		console.log("e:", e);
		if (SALE_ATTR_SET_MAP.length == 0) {
			return e.ATTR_SET_ID;
		}
		for (var i = 0; i < SALE_ATTR_SET_MAP.length; i++) {
			if (SALE_ATTR_SET_MAP[i].ATTR_SET_ID == e.ATTR_SET_ID) {
				return SALE_ATTR_SET_MAP[i].VALUE;
				break;
			}
		}
		return e.ATTR_SET_ID;
	}

	// 服务端生成SKU
	$scope.prodSku = [];
	// SALE_DATA_LIST
	var prodSkuKV = [];
	$scope.makeSKU = function() {
		$log.warn(saleAttrValueContain);
		if (saleAttrValueContain.length == 0) {
			notify({
				message : "必须选择一条销售属性"
			});
			return;
		}
		prodSkuKV = [];

		$log.warn("select attrids:", saleAttrValueContain);
		var datatmp = {};
		for (var i = 0; i < saleAttrValueContain.length; i++) {
			if (!angular.isDefined(datatmp[saleAttrValueContain[i].ATTR_ID])) {
				datatmp[saleAttrValueContain[i].ATTR_ID] = [];
			}
			datatmp[saleAttrValueContain[i].ATTR_ID].push(saleAttrValueContain[i].ATTR_SET_ID)
		}
		$log.warn("After change first:" + angular.toJson(datatmp));
		var data = [];
		$.each(datatmp, function(key, val) {
			data.push(datatmp[key]);
		});
		// 生成属性-值数据
		$.each(datatmp, function(key, val) {
			var te = {};
			te.ATTR_ID = key;
			te.DATA = datatmp[key];
			prodSkuKV.push(te);
		});
		$log.warn("After prodSkuKV value:", prodSkuKV);

		$log.warn("After change last:" + angular.toJson(data));
		// 由服务器后端去生成笛卡尔积
		$http.post($rootScope.project + "/api/product/prodDescartes.do", {
			data : angular.toJson(data),
			spu : $scope.prodData.SPU
		}).success(function(res) {
			if (res.success) {
				$scope.prodSku = res.data;
				if_rebuild = 'Y';
			} else {
				notify({
					message : res.message
				});
			}
		})
	}

	$scope.sure = function() {

		var ps = {};
		ps.SPU = $scope.prodData.SPU;
		ps.REBUILD = if_rebuild;
		ps.SALE_KV = angular.toJson(prodSkuKV);
		ps.SALE_RES = angular.toJson($scope.prodSku);
		$log.warn(ps);
		$http.post($rootScope.project + "/api/product/prodModifySaleAttr.do", ps).success(function(res) {
			if (res.success) {
				$uibModalInstance.close("OK");
			}
			notify({
				message : res.message
			});
		})

	}

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
}

function prodBaseAttrSaveCtl($log, $http, $rootScope, $scope, $uibModalInstance, data, notify) {

	$log.warn("window in:", data);

	$scope.prodData = {}
	$scope.base_attr = [];
	// 获取销售属性
	$scope.brandOpt = [];
	$scope.brandSel = "";
	// 获取品牌数据
	$http.post($rootScope.project + "/api/brand/brandQuery.do", {}).success(function(res) {
		if (res.success) {
			$scope.brandOpt = res.data;
		} else {
			notify({
				message : res.message
			});
		}
	})

	// 获取产品信息
	$http.post($rootScope.project + "/api/product/prodQueryBySpu.do", {
		spu : data.spu
	}).success(function(res) {
		$log.warn(res);
		if (res.success) {
			$scope.prodData = res.data;
			$scope.base_attr = res.data.BASE_ATTR;
			// 获取商品模版数据

		} else {
			notify({
				message : res.message
			});
		}
	})

	$scope.sure = function() {

		var ps = {};
		$log.warn($scope.base_attr);
		ps = angular.copy($scope.prodData);
		ps.BASE_RES = angular.toJson($scope.base_attr);

		$log.warn("ps", ps);
		$http.post($rootScope.project + "/api/product/prodModifyBaseAttr.do", ps).success(function(res) {
			if (res.success) {
				$uibModalInstance.close("OK");
			} else {

			}
			notify({
				message : res.message
			});
		})

	}

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

}

function prodQueryCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.prodcatOpt = [];
	$scope.prodcatSel = "";

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

	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withPaginationType('full_numbers').withDisplayLength(25).withOption("ordering", false).withOption("responsive", true)
			.withOption("searching", false).withOption("paging", false).withOption('bStateSave', true).withOption('bProcessing', true).withOption('bFilter', false).withOption(
					'bInfo', false).withOption('serverSide', false).withOption('bAutoWidth', false).withOption('aaData', $scope.tabdata).withOption('createdRow', function(row) {
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
	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";
		acthtml = acthtml + " <button ng-click=\"save('" + full.BRAND_ID + "')\" class=\"btn-white btn btn-xs\">更新</button>  ";
		acthtml = acthtml + " <button ng-click=\"row_delete('" + full.BRAND_ID + "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
		return acthtml;

	}

	function renderStatus(data, type, full) {

		if (data == "Y") {
			return "售中"
		} else if (data == "N") {
			return "下架";
		} else {
			return "";
		}

	}

	function renderImage(data, type, full) {
		var html = ""
		html = html + "<img style='height:50px;width:50px;' src=" + $rootScope.project + "/api/file/imagedown.do?id=" + full.MASTER_PIC + "  />"
		return html;
	}
	$scope.dtColumns = [ DTColumnBuilder.newColumn(null).withTitle('').withClass('select-checkbox').renderWith(function() {
		return '';
	}), DTColumnBuilder.newColumn('MASTER_PIC').withTitle('图片').withOption('sDefaultContent', '').renderWith(renderImage),
			DTColumnBuilder.newColumn('PROD_NAME').withTitle('商品名称').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('LIST_PRICE').withTitle('列表价').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('STOCK').withTitle('库存').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('IS_OFF').withTitle('状态').withOption('sDefaultContent', '').renderWith(renderStatus),
			DTColumnBuilder.newColumn('CODE').withTitle('货号').withOption('sDefaultContent', '') ]

	function flush() {

		var ps = {};
		ps.cat_id = $scope.prodcatSel.ID;
		$http.post($rootScope.project + "/api/product/prodQueryByCat.do", ps).success(function(res) {
			if (res.success) {
				$scope.dtOptions.aaData = res.data;
			}else{
				notify({
					message : res.message
				});
			}
		})
	}

	flush();

	$scope.row_delete = function(id) {

	}

	$scope.row_dtl = function(id) {

	}

	$scope.query = function() {
		flush();
	}

	function prodOnOff(stat, prods) {
		$http.post($rootScope.project + "/api/product/prodOffOn.do", {
			is_off : stat,
			prods : prods
		}).success(function(res) {
			if (res.success) {
				flush();
			}
			notify({
				message : res.message
			});

		})
	}

	function getSelectItems() {
		var prods = [];
		var data = $scope.dtInstance.DataTable.rows({
			selected : true
		})[0];
		for (var i = 0; i < data.length; i++) {
			prods.push({
				spu : $scope.dtOptions.aaData[data[i]].SPU
			});
		}
		return prods;
	}
	$scope.prod_on = function() {
		prodOnOff("Y", angular.toJson(getSelectItems()));
	}

	$scope.prod_off = function() {
		prodOnOff("N", angular.toJson(getSelectItems()));
	}

	$scope.del = function() {

		$confirm({
			text : '是否删除?'
		}).then(function() {
			$http.post($rootScope.project + "/api/product/prodDelete.do", {
				prods : angular.toJson(getSelectItems())
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

	}

	$scope.prodModify = function() {
		var arr = getSelectItems();
		if (arr.length == 0) {
			notify({
				message : "请选择产品"
			});
			return;
		}
		if (arr.length > 1) {
			notify({
				message : "只能选择一款产品"
			});
			return;
		}
		//

		var ps = arr[0];
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/product/modal_prodBaseAttr_save.html',
			controller : prodBaseAttrSaveCtl,
			size : 'lg',
			resolve : { // 调用控制器与modal控制器中传递值
				data : function() {
					return ps
				}
			}
		});

		modalInstance.result.then(function(result) {
			if (result == "OK") {
				flush();
			}
		}, function(reason) {
			// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
			$log.log("reason", reason)
		});

	}

	$scope.prodsaleattrModify = function() {
		var arr = getSelectItems();
		if (arr.length == 0) {
			notify({
				message : "请选择产品"
			});
			return;
		}
		if (arr.length > 1) {
			notify({
				message : "只能选择一款产品"
			});
			return;
		}

		var ps = arr[0];
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/product/modal_prodSaleAttr_save.html',
			controller : prodSaleAttrSaveCtl,
			size : 'lg',
			resolve : { // 调用控制器与modal控制器中传递值
				data : function() {
					return ps
				}
			}
		});

		modalInstance.result.then(function(result) {
			if (result == "OK") {
				flush();
			}
		}, function(reason) {
			// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
			$log.log("reason", reason)
		});

	}
	$scope.prodPicsUpdate = function() {
		var arr = getSelectItems();
		if (arr.length == 0) {
			notify({
				message : "请选择产品"
			});
			return;
		}
		if (arr.length > 1) {
			notify({
				message : "只能选择一款产品"
			});
			return;
		}

		var ps = arr[0];
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/product/modal_prodPics_update.html',
			controller : prodPicUpdateCtl,
			size : 'lg',
			resolve : { // 调用控制器与modal控制器中传递值
				data : function() {
					return ps
				}
			}
		});

		modalInstance.result.then(function(result) {
			if (result == "OK") {
				flush();
			}
		}, function(reason) {
			// 点击空白区域，总会输出backdrop click，点击取消，则会cancel
			$log.log("reason", reason)
		});

	}

};
app.register.controller('prodQueryCtl', prodQueryCtl);