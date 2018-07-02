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

function prodPublishCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.prodcatOpt = [];
	$scope.prodcatSel = "";
	$scope.brandOpt = [];
	$scope.brandSel = "";

	// 基本销售属
	$scope.base_attr = [];
	$scope.sale_attr = [];
	$scope.if_publish = false;
	// 销售属性的所有可用键值对数据
	var sale_attr_set_map = [];

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

	// 获取我的店铺
	$http.post($rootScope.project + "/api/shop/queryMyShop.do", {}).success(function(res) {
		if (res.success) {
			$scope.shopOpt = res.data;
			if ($scope.shopOpt.length > 0) {
				$scope.proddata.shop_id = $scope.shopOpt[0].shop_id;
			}

		} else {
			notify({
				message : res.message
			});
		}
	})

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

	// 监测类目选择变化,动态调整数据
	var watch2 = $scope.$watch('prodcatSel', function(oldValue, newValue, scope) {
		if (angular.isDefined($scope.prodcatSel.id)) {
			reset();
			$scope.if_publish = false;
			$http.post($rootScope.project + "/api/categoryB/prodPublishCatAttrList.do", {
				cat_id : $scope.prodcatSel.id,
				is_used : "Y"
			}).success(function(res) {
				if (res.success) {
					// 销售属性必须要有,如果没有则提醒下,无法发布产品
					if (res.data.sale_attr.length == 0) {
						notify({
							message : "无销售属性,不可发布商品"
						});
					} else {
						$scope.if_publish = true;
					}
					// 初始化数据属性
					$scope.sale_attr = res.data.sale_attr;
					$scope.base_attr = res.data.base_attr;
					sale_attr_set_map = res.data.sale_attr_set_map;
				} else {
					notify({
						message : res.message
					});
				}
			})
		}
	});

	// 产品公共属性数据
	$scope.proddata = {};
	/** ***********************处理基本属性数据********************** */
	// 基本属性数据多选的情况
	var baseAttrValueContain = [];
	$scope.baseattrvaluechange = function(item, attrvalue) {
		// $log.warn(item, attrvalue);
		var isExist = false;
		for (var i = 0; i < baseAttrValueContain.length; i++) {
			if (baseAttrValueContain[i].id == attrvalue.id) {
				// 已经存在,则移除
				// $log.warn('to remove,' + i);
				baseAttrValueContain.splice(i, 1);
				return;
			}
		}
		baseAttrValueContain.push(attrvalue);
	}

	/** ***********************处理销售属性数据******************** */
	var saleAttrValueContain = [];
	$scope.saleattrvaluechange = function(item, attrvalue) {
		// $log.warn(item, attrvalue);
		var isExist = false;
		for (var i = 0; i < saleAttrValueContain.length; i++) {
			if (saleAttrValueContain[i].id == attrvalue.id) {
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
		if (sale_attr_set_map.length == 0) {
			return e.attr_set_id;
		}
		for (var i = 0; i < sale_attr_set_map.length; i++) {
			if (sale_attr_set_map[i].attr_set_id == e.attr_set_id) {
				return sale_attr_set_map[i].value;
				break;
			}
		}
		return e.attr_set_id;
	}

	// 服务端生成SKU
	$scope.prodSku = [];
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
			if (!angular.isDefined(datatmp[saleAttrValueContain[i].attr_id])) {
				datatmp[saleAttrValueContain[i].attr_id] = [];
			}
			datatmp[saleAttrValueContain[i].attr_id].push(saleAttrValueContain[i].attr_set_id)
		}
		$log.warn("After change first:" + angular.toJson(datatmp));
		var data = [];
		$.each(datatmp, function(key, val) {
			data.push(datatmp[key]);
		});
		// 生成属性-值数据
		$.each(datatmp, function(key, val) {
			var te = {};
			te.attr_id = key;
			te.data = datatmp[key];
			prodSkuKV.push(te);
		});
		$log.warn("After prodSkuKV value:", prodSkuKV);

		$log.warn("After change last:" + angular.toJson(data));
		// 由服务器后端去生成笛卡尔积
		$http.post($rootScope.project + "/api/product/prodDescartes.do", {
			data : angular.toJson(data)
		}).success(function(res) {
			if (res.success) {
				$scope.prodSku = res.data;
			} else {
				notify({
					message : res.message
				});
			}
		})

	}
	/** *******************************处理图片************************************ */
	$scope.prodPics = [];
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
		console.log(item);
		for (var i = 0; i < $scope.prodPics.length; i++) {
			if (item.pic_id == $scope.prodPics[i].pic_id) {
				$scope.prodPics.splice(i, 1);
				break;
			}
		}
	}
	/** *******************************操作事件响应************************************ */
	$scope.submit = function() {

		var picid = getUuid();
		$scope.myDropzone.options.url = $rootScope.project + '/api/file/fileupload.do?bus=prodimgs&uuid=' + picid + '&type=image&interval=10000';
		if ($scope.myDropzone.files.length == 0) {
			notify({
				message : "请输入产品图片"
			});
			return;
		}
		$scope.myDropzone.uploadFile($scope.myDropzone.files[0])
		if ($scope.prodPics.length == 0) {
			notify({
				message : "请选择一张产品图片"
			});
		}
		if (!(angular.isDefined($scope.proddata.prod_name) && $scope.proddata.prod_name.length > 0)) {
			notify({
				message : "请输入产品名称"
			});
			return;
		}

		if (!(angular.isDefined($scope.proddata.title) && $scope.proddata.title.length > 0)) {
			notify({
				message : "请输入产品标题"
			});
			return;
		}
		if (!(angular.isDefined($scope.proddata.list_price))) {
			notify({
				message : "请输入产品列表价"
			});
			return;
		}

		if (!(angular.isDefined($scope.proddata.list_ori_price))) {
			notify({
				message : "请输入产品列表原价"
			});
			return;
		}

		if (!(angular.isDefined($scope.proddata.unit) && $scope.proddata.unit.length > 0)) {
			notify({
				message : "请输入产品单位"
			});
			return;
		}

		/** *************************************基本属性******************************** */
		var base_res = {}
		// 处理基本属性中的多选 select-multi baseAttrValueContain
		var multiattrdata = [];
		var datatmp = {};
		if (baseAttrValueContain.length >= 0) {
			for (var i = 0; i < baseAttrValueContain.length; i++) {
				if (!angular.isDefined(datatmp[baseAttrValueContain[i].attr_id])) {
					datatmp[baseAttrValueContain[i].attr_id] = [];
				}
				datatmp[baseAttrValueContain[i].attr_id].push(baseAttrValueContain[i].attr_set_id)
			}
			$log.warn("After change first:" + angular.toJson(datatmp));
			// 做第二次转换
			$.each(datatmp, function(key, val) {
				var te = {};
				te.attr_id = key;
				te.data = datatmp[key];
				multiattrdata.push(te);
			});
		}
		$log.warn("After change second:" + angular.toJson(multiattrdata));
		base_res.multiattrdata = multiattrdata
		// 处理单选和输入数据,ATTR_SET_VALUE中获取
		base_res.attrdata = angular.copy($scope.base_attr);
		for (var j = 0; j < base_res.attrdata.length; j++) {
			// 去除list数据,判断是否必须输入
			base_res.attrdata[j].list = [];
			// ATTR_SET_VALUE
			$log.warn(base_res.attrdata[j]);
			if (base_res.attrdata[j].is_need == "Y") {
				// 如果是单选和输入则判断ATTR_SET_VALUE是否存在
				if (base_res.attrdata[j].input_type == "select-single" || base_res.attrdata[j].input_type == "input") {
					if (!angular.isDefined(base_res.attrdata[j].attr_set_value)) {
						notify({
							message : "请选择属性:" + base_res.attrdata[j].name
						});
						return;
					}
				} else if (base_res.attrdata[j].input_type == "select-multi") {
					// 如果是多选则判断datatmp 是否存在
					if (!angular.isDefined(datatmp[base_res.attrdata[j].attr_id])) {
						notify({
							message : "请选择属性:" + base_res.attrdata[j].name
						});
						return;
					}
				} else {
					notify({
						message : "输入类型不支持"
					});
					return;
				}
			}
		}
		$log.warn("base_res:", base_res)
		/** ************************************销售属性直接获取sku******************************* */
		if ($scope.prodSku.length == 0) {
			notify({
				message : "没有选择销售属性,无法发布。"
			});
			return;
		}
		var sale_res = angular.copy($scope.prodSku);
		$log.warn("sale_res:", sale_res);

		/** ******公共属性************ */
		console.log($scope.brandSel);

		/** ******提交************ */

		$scope.proddata.pic_id = picid;
		$scope.proddata.cat_id = $scope.prodcatSel.id
		$scope.proddata.sale_res = angular.toJson(sale_res);
		$scope.proddata.sale_kv = angular.toJson(prodSkuKV);
		$scope.proddata.base_res = angular.toJson(base_res);
		$scope.proddata.pics = angular.toJson($scope.prodPics);
		console.log($scope.proddata);
		$http.post($rootScope.project + "/api/product/prodPublish.do", $scope.proddata).success(function(res) {
			if (res.success) {
			} else {
			}
			notify({
				message : res.message
			});
		})

	}

	function reset() {
		$scope.prodSku = [];
		saleAttrValueContain = [];
	}
	$scope.reset = function() {
		reset();
	}

};

app.register.controller('prodPublishCtl', prodPublishCtl);