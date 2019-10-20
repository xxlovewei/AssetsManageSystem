function modaldevfaultCtl($timeout, $localStorage, notify, $log, $uibModal,
		$uibModalInstance, $scope, meta, $http, $rootScope, DTOptionsBuilder,
		DTColumnBuilder, $compile) {

	$scope.item = {};
	$scope.data = {};
	$scope.data.mark = "";
	$scope.data.reason = "";
	$scope.dzconfig = {
		url : 'fileupload.do',
		maxFilesize : 10000,
		paramName : "file",
		maxThumbnailFilesize : 5,
		// 一个请求上传多个文件
		uploadMultiple : true,
		// 当多文件上传,需要设置parallelUploads>=maxFiles
		parallelUploads : 5,
		maxFiles : 5,
		dictDefaultMessage : "点击上传附件",
		acceptedFiles : "image/jpeg,image/png,image/gif,.xls,.zip,.rar,.doc,.pdf,.docx,.txt,.xlsx",
		// 添加上传取消和删除预览图片的链接，默认不添加
		addRemoveLinks : true,
		// 关闭自动上传功能，默认会true会自动上传
		// 也就是添加一张图片向服务器发送一次请求
		autoProcessQueue : false,
		init : function() {
			$scope.myDropzone = this; // closure
		}
	};

	if (angular.isDefined(meta.id)) {
		// 加载数据

		$http.post($rootScope.project + "/api/base/queryResAllById.do", {
			id : meta.id
		}).success(function(res) {
			if (res.success) {
				$scope.item = res.data.data;
			} else {
				notify({
					message : res.message
				});
			}
		});
	}

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

	$scope.sure = function() {
		// 上传图片
		console.log($scope.item, "zone", $scope.myDropzone);
		// 产品图片
		var file = "";
		for (var i = 0; i < $scope.myDropzone.files.length; i++) {
			var id = getUuid();
			// 判断,已经上传的不在上传
			if (typeof ($scope.myDropzone.files[i].uuid) == "undefined") {
				console.log("开始上传文件" + id);
				$scope.myDropzone.options.url = $rootScope.project
						+ '/api/file/fileupload.do?uuid=' + id
						+ '&bus=file&interval=10000&bus=file';
				$scope.myDropzone.uploadFile($scope.myDropzone.files[i])
			} else {
				// 已经上传
				console.log("已经上传" + id);
				id = $scope.myDropzone.files[i].uuid;
			}
			file = file + id + "#";
		}
		$scope.item.files = file;

		console.log($scope.item.files);

		$scope.data.resid = $scope.item.id;
		$scope.data.files=$scope.item.files;
		$http.post($rootScope.project + "/api/base/res/addfaultdevice.do",
				$scope.data).success(function(res) {
			if (res.success) {
				notify({
					message : res.message
				});
				$uibModalInstance.dismiss('cancel');
			} else {
				notify({
					message : res.message
				});
			}
		})

	}
}

function cmdbfaultrecordCtl(DTOptionsBuilder, DTColumnBuilder, $compile,
		$confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	// 分类

	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withOption(
			'createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			}).withOption("searching", true).withDisplayLength(50);

	$scope.dtInstance = {}

	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";
		acthtml = acthtml + " <button ng-click=\"fault('" + full.id
				+ "')\" class=\"btn-white btn btn-xs\">维护</button> ";
		acthtml = acthtml + " <button ng-click=\"detail('" + full.id
				+ "')\" class=\"btn-white btn btn-xs\">详情</button> </div> ";
		return acthtml;
	}

	function renderName(data, type, full) {

		var html = full.model;
		return html;

	}

	function renderJg(data, type, full) {

		var html = full.rackstr + "-" + full.frame;
		return html;

	}

	$scope.dtColumns = [

			DTColumnBuilder.newColumn('uuid').withTitle('编号').withOption(
					'sDefaultContent', '').withOption("width", '30'),
			DTColumnBuilder.newColumn('classname').withTitle('类型').withOption(
					'sDefaultContent', '').withOption("width", '30'),
			DTColumnBuilder.newColumn('locstr').withTitle('位置').withOption(
					'sDefaultContent', '').withOption('width', '30'),
			DTColumnBuilder.newColumn('brandstr').withTitle('品牌').withOption(
					'sDefaultContent', '').withOption('width', '30'),
			DTColumnBuilder.newColumn('name').withTitle('型号').withOption(
					'sDefaultContent', '').withOption('width', '50')
					.renderWith(renderName),
			DTColumnBuilder.newColumn('envstr').withTitle('运行环境').withOption(
					'sDefaultContent', '').withOption('width', '30'),
			DTColumnBuilder.newColumn('sn').withTitle('序列号').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('id').withTitle('操作').withOption(
					'sDefaultContent', '').withOption('width', '100')
					.renderWith(renderAction) ]

	$scope.query = function() {
		flush();

	}

	$scope.fault = function(id) {

		var ps = {};
		ps.id = id;
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/cmdb/modal_devfault.html',
			controller : modaldevfaultCtl,
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

	var gclass_id = "server";
	var meta = {
		tablehide : false,
		tools : [
				{
					id : "input",
					label : "内容",
					placeholder : "输入型号、编号、序列号",
					type : "input",
					ct : ""
				},
				{
					id : "btn",
					label : "",
					type : "btn",
					template : ' <button ng-click="query()" class="btn btn-sm btn-primary" type="submit">搜索</button>'
				} ]
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
		$http.post($rootScope.project + "/api/base/queryResAll.do", ps)
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
					$rootScope.project + "/api/base/queryDictFast.do",
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

};

app.register.controller('cmdbfaultrecordCtl', cmdbfaultrecordCtl);