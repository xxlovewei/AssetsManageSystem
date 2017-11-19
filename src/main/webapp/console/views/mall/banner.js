function mallBannerItemSaveCtl($localStorage, notify, $log, $uibModal,
		$uibModalInstance, $scope, id, banner_id, $http, $rootScope) {
	
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
	
	$scope.usedOpt = [ {
		ID : "N",
		NAME : "未使用"
	}, {
		ID : "Y",
		NAME : "使用中"
	} ];

	$scope.usedSel = $scope.usedOpt[0];

	$scope.item = {};
	if (angular.isDefined(id)) {
		// 修改
		$http.post($rootScope.project + "/api/banner/queryBannerItemById.do", {
			id : id
		}).success(function(res) {
			if (res.success) {
				$scope.item = res.data
				if ($scope.item.IS_USED == "Y") {
					$scope.usedSel = $scope.usedOpt[1];
				} else {
					$scope.usedSel = $scope.usedOpt[0];
				}
				
	 
				setTimeout(function() {
					 
					var mockFile = {
							name : "图" ,
							uuid : $scope.item.IMGURL,
							href : $rootScope.project + "/api/file/imagedown.do?id=" +$scope.item.IMGURL,
							url : $rootScope.project + "/api/file/imagedown.do?id=" + $scope.item.IMGURL,
							status : "success",
							accepted : true,
							type : 'image/png'
						};
						$scope.myDropzone.emit("addedfile", mockFile);
						$scope.myDropzone.files.push(mockFile); // file must be
						$scope.myDropzone.createThumbnailFromUrl(mockFile, $rootScope.project + "/api/file/imagedown.do?id=" +$scope.item.IMGURL);
						$scope.myDropzone.emit("complete", mockFile);

				}, 600)
				
				
			} else {
				notify({
					message : res.message
				});
			}
		})
	} else {
		// 新增
	}

	$scope.sure = function() {
		// 处理图片
		if ($scope.myDropzone.files.length == 0) {
			notify({
				message : "请选择主图"
			});
			return;
		}
		var picid = getUuid();
		$scope.myDropzone.options.url = $rootScope.project
				+ '/api/file/fileupload.do?bus=news&uuid=' + picid
				+ '&type=image&interval=10000';
		if (angular.isDefined($scope.myDropzone.files[0].uuid)) {
			// 已经上传
			picid = $scope.myDropzone.files[0].uuid;
		} else {
			$scope.myDropzone.uploadFile($scope.myDropzone.files[0])
		}
		
		$scope.item.IMGURL = picid;
		$scope.item.IS_USED = $scope.usedSel.ID;
		if (!angular.isDefined(id)) {
			$scope.item.BANNER_ID = banner_id;
		}
		$http.post($rootScope.project + "/api/banner/saveBannerItem.do",
				$scope.item).success(function(res) {
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

function mallbannerCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile,
		$confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {
	$scope.bannerOpt = [];
	$http.post($rootScope.project + "/api/banner/queryBanner.do", {
		type : "mall"
	}).success(function(res) {
		if (res.success) {
			$scope.bannerOpt = res.data;
			if (res.data.length > 0) {
				$scope.bannerSel = res.data[0];
				flush();
			}
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
			}).withLanguage(DTLang);
	$scope.dtInstance = {}
	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";
		acthtml = acthtml + " <button ng-click=\"save('" + full.ID
				+ "')\" class=\"btn-white btn btn-xs\">编辑</button> ";
		acthtml = acthtml + " <button ng-click=\"row_del('" + full.ID
				+ "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
		return acthtml;
	}
	function renderStatus(data, type, full) {
		var res = "无效";
		if (full.IS_USED == "Y") {
			res = "有效";
		}
		return res;
	}
	function renderImage(data, type, full) {
		var html = ""
		html = html + "<img style='height:50px;width:50px;' src=" + $rootScope.project + "/api/file/imagedown.do?id=" + full.IMGURL + "  />"
		return html;
	}

	$scope.dtColumns = [
			DTColumnBuilder.newColumn('ID').withTitle('图片').withOption('sDefaultContent', '').renderWith(renderImage),
			DTColumnBuilder.newColumn('NAME').withTitle('名称').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('RK').withTitle('排序').withOption(
					'sDefaultContent', ''),
//			DTColumnBuilder.newColumn('TYPE').withTitle('类型').withOption(
//					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('IS_USED').withTitle('状态').withOption(
					'sDefaultContent', '').renderWith(renderStatus),
			DTColumnBuilder.newColumn('ID').withTitle('动作').withOption(
					'sDefaultContent', '').renderWith(renderAction) ]

	function flush() {
		console.log($scope.dtOptions);
		var ps = {}
		ps.banner_id = $scope.bannerSel.BANNER_ID;
		$http.post($rootScope.project + "/api/banner/queryBannerItems.do", ps)
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

	$scope.row_detail = function(id) {

	}
	$scope.row_del = function(id) {
		$confirm({
			text : '是否删除功能?'
		}).then(function() {
			$http.post($rootScope.project + "/api//banner/delBannerItem.do", {
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

	$scope.query = function() {
		flush();
	}
	$scope.save = function(id) {

		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/mall/modal_banneritem_save.html',
			controller : mallBannerItemSaveCtl,
			size : 'md',
			resolve : { // 调用控制器与modal控制器中传递值
				id : function() {
					return id;
				},
				banner_id : function() {
					return $scope.bannerSel.BANNER_ID;
				},
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

app.register.controller('mallbannerCtl', mallbannerCtl);