
function modaldevfaultCtl($timeout, $localStorage, notify, $log, $uibModal,
		$uibModalInstance, $scope, meta, $http, $rootScope, DTOptionsBuilder,
		DTColumnBuilder, $compile) {
	//type:detail,modify,add
 	$scope.ctl={};
	$scope.ctl.fprocessuser=false;
	$scope.ctl.fprocesstime=false;
	$scope.ctl.status=false;
	$scope.ctl.fmoney=false;
	$scope.ctl.freason=false;
	$scope.ctl.ffile=false;
	$scope.ctl.fmark=false;
	$scope.ctl.chosenzcbtn=false;
	$scope.ctl.surebtn=false;
	if(meta.actiontype=="detail"){
		$scope.ctl.fprocessuser=true;
		$scope.ctl.fprocesstime=true;
		$scope.ctl.status=true;
		$scope.ctl.fmoney=true;
		$scope.ctl.freason=true;
		$scope.ctl.fmark=true;
		$scope.ctl.ffile=true;
		$scope.ctl.chosenzcbtn=true;
		$scope.ctl.surebtn=true;
	}


	$scope.statusOpt=[{id:"wait",name:"维修中"},{id:"finish",name:"已完成"}];
	$scope.statusSel=$scope.statusOpt[0];

	$scope.dtOptions=DTOptionsBuilder.fromFnPromise().withDataProp('data').withDOM('frtlip')
		.withPaginationType('full_numbers').withDisplayLength(100)
		.withOption("ordering", false).withOption("responsive", false)
		.withOption("searching", true).withOption('scrollY', 600)
		.withOption('scrollX', true).withOption('bAutoWidth', true)
		.withOption('scrollCollapse', true).withOption('paging', false)
		.withFixedColumns({
			leftColumns : 0,
			rightColumns : 0
		}).withOption('bStateSave', true).withOption('bProcessing', false)
		.withOption('bFilter', false).withOption('bInfo', false)
		.withOption('serverSide', false).withOption('createdRow', function(row) {
			$compile(angular.element(row).contents())($scope);
		})


	$scope.dtColumns = [];
	$scope.dtColumns=zcBaseColsCreate(DTColumnBuilder,'withoutselect');

	$scope.dtOptions.aaData = [];


	$scope.data = {};
	$scope.data.mark = "";
	$scope.data.reason = "";
	$scope.dzconfig = {
		url : 'fileupload.do',
		maxFilesize : 20000,
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

	$scope.selectzc = function() {

		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/cmdb/modal_common_zclist.html',
			controller : modal_faultZcListCtl,
			size : 'blg',
			resolve : {
				id : function() {
					return ""
				},
				type : function() {
					return "many"
				},
				datatype:function(){
					return "repair"
				}
			}
		});
		modalInstance.result.then(function(result) {
			$scope.dtOptions.aaData=result;
		}, function(reason) {

			$log.log("reason", reason)
		});

	}

	if(angular.isDefined(meta.id)){
		//获取数据
		$http.post($rootScope.project + "/api/zc/resRepair/ext/selectById.do",
			{id:meta.id}).success(function(res) {
			if (res.success) {
				$scope.dtOptions.aaData=res.data.items;
				$scope.data=res.data;

				if(res.data.fstatus=="wait"){
					$scope.statusSel=$scope.statusOpt[0];
				}else{
					$scope.statusSel=$scope.statusOpt[1];
				}


				$timeout(function() {
				var files=res.data.files;
					for(var i=0;i<files.length;i++){
						var  iid=files[i].fileid
						var mockFile = {
							name : "主图",
							uuid : iid,
							href : $rootScope.project
								+ "/api/file/imagedown.do?id="
								+iid,
							url : $rootScope.project
								+ "/api/file/imagedown.do?id="
								+ iid,
							status : "success",
							accepted : true,
							type : 'image/png'
						};
						$scope.myDropzone.emit("addedfile", mockFile);
						$scope.myDropzone.files.push(mockFile);
						// manually
						$scope.myDropzone.createThumbnailFromUrl(
							mockFile, $rootScope.project
							+ "/api/file/imagedown.do?id="
							+ iid);
						$scope.myDropzone.emit("complete", mockFile);
					}

				}, 500);



			}else{
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
		if($scope.dtOptions.aaData.length==0){
			notify({
				message :"请选择资产"
			});
		}
		// 产品图片
		var file = "";
		for (var i = 0; i < $scope.myDropzone.files.length; i++) {
			var id = getUuid();
			// 判断,已经上传的不在上传
			if (typeof ($scope.myDropzone.files[i].uuid) == "undefined") {
				$scope.myDropzone.options.url = $rootScope.project
						+ '/api/file/fileupload.do?uuid=' + id
						+ '&bus=file&interval=10000&bus=file';
				$scope.myDropzone.uploadFile($scope.myDropzone.files[i])
			} else {
				id = $scope.myDropzone.files[i].uuid;
			}
			file = file + id + "#";
		}
		$scope.data.files = file
		$scope.data.items=angular.toJson($scope.dtOptions.aaData);
		$scope.data.fstatus=$scope.statusSel.id;
		$http.post($rootScope.project + "/api/zc/resRepair/ext/insertOrUpdate.do",
				$scope.data).success(function(res) {
			if (res.success) {
				$uibModalInstance.close('ok');
			}
			notify({
				message : res.message
			});
		})

	}
}

function cmdbfaultrecordCtl(DTOptionsBuilder, DTColumnBuilder, $compile,
		$confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	// 分类
	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data')
			.withPaginationType('full_numbers').withDisplayLength(100)
			.withOption("ordering", false).withOption("responsive", false)
			.withOption("searching", true).withOption('scrollY', 600)
			.withOption('scrollX', true).withOption('bAutoWidth', true)
			.withOption('scrollCollapse', true).withOption('paging', true)
			.withFixedColumns({
				leftColumns : 0,
				rightColumns : 0
			}).withOption('bStateSave', true).withOption('bProcessing', false)
			.withOption('bFilter', false).withOption('bInfo', false)
			.withOption('serverSide', false).withOption('createdRow', function(row) {
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

	$scope.selectCheckBoxAll = function(selected) {
		if (selected) {
			$scope.dtInstance.DataTable.rows().select();
		} else {
			$scope.dtInstance.DataTable.rows().deselect();
		}
	}
	function renderStatus(data, type, full) {
			if(data=="wait"){
				return "维修中"
			}else if(data=="finish"){
				return "已完成"
			}else{
				return data;
			}
	}

	var ckHtml = '<input ng-model="selectCheckBoxValue" ng-click="selectCheckBoxAll(selectCheckBoxValue)" type="checkbox">';
	$scope.dtColumns = [
		DTColumnBuilder.newColumn(null).withTitle(ckHtml).withClass(
			'select-checkbox checkbox_center').renderWith(function() {
			return ""
		}),
		DTColumnBuilder.newColumn('fuuid').withTitle('单据号').withOption(
			'sDefaultContent', ''),
		DTColumnBuilder.newColumn('fprocessuser').withTitle('维护人').withOption(
			'sDefaultContent', ''),
		DTColumnBuilder.newColumn('fprocesstime').withTitle('维护时间').withOption(
			'sDefaultContent', ''),
		DTColumnBuilder.newColumn('fstatus').withTitle('状态').withOption(
			'sDefaultContent', '').renderWith(renderStatus),
		DTColumnBuilder.newColumn('fmoney').withTitle('费用').withOption(
			'sDefaultContent', ''),
		DTColumnBuilder.newColumn('freason').withTitle('原因').withOption(
			'sDefaultContent', ''),
		DTColumnBuilder.newColumn('fmark').withTitle('备注').withOption(
			'sDefaultContent', ''),
		DTColumnBuilder.newColumn('createTime').withTitle('创建时间').withOption(
			'sDefaultContent', '')]



	$scope.query = function() {
		flush();

	}

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
					show : false,
					priv:"select",
					template : ' <button ng-click="query()" class="btn btn-sm btn-primary" type="submit">搜索</button>'
				},

				{
					id : "btn3",
					label : "",
					type : "btn",
					show : true,
					priv:"fix",
					template : ' <button ng-click="add()" class="btn btn-sm btn-primary" type="submit">申请报修</button>'
				},
			 {
				id : "btn4",
				label : "",
				type : "btn",
				show : true,
				priv:"fix",
				template : ' <button ng-click="finish()" class="btn btn-sm btn-primary" type="submit">完成维修</button>'
			}, {
				id : "btn5",
				label : "",
				type : "btn",
				show : true,
				priv:"fix",
				template : ' <button ng-click="detail()" class="btn btn-sm btn-primary" type="submit">详细</button>'
			}, {
				id : "btn5",
				label : "",
				type : "btn",
				show : true,
				priv:"fix",
				template : ' <button ng-click="modify()" class="btn btn-sm btn-primary" type="submit">修改</button>'
			},	{
				id : "btn2",
				label : "",
				type : "btn",
				show : false,
				priv:"remove",
				template : ' <button ng-click="del()" class="btn btn-sm btn-primary" type="submit"> 删除</button>'
			}]
	}
	$scope.meta = meta;
 
	privNormalCompute($scope.meta.tools, $rootScope.curMemuBtns);
	function flush() {
		var ps = {};
		ps.search = $scope.meta.tools[0].ct;
		$http
				.post($rootScope.project + "/api/zc/resRepair/ext/selectList.do",
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
			return $scope.dtOptions.aaData[data[0]];
		}
	}

	function action(actiontype,id) {
		var meta={};
		meta.actiontype=actiontype;
		meta.id=id;
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/cmdb/modal_devfault.html',
			controller : modaldevfaultCtl,
			size : 'lg',
			resolve : {
				meta : function() {
					return meta;
				}
			}
		});
		modalInstance.result.then(function(result) {
			flush();
		}, function(reason) {
			$log.log("reason", reason)
		});

	}

	$scope.finish=function(){
		var selrow = getSelectRow();
		if (angular.isDefined(selrow)&&angular.isDefined(selrow.id)) {
			$confirm({
				text : '确定要将所选单据状态修改为已完成?'
			}).then(
				function() {
					$http.post(
						$rootScope.project
						+ "/api/zc/resRepair/ext/finish.do", {
							id :selrow.id
						}).success(function(res) {
						if (res.success) {
							flush();
						} else {
							notify({
								message : res.message
							});
						}
					});
				});
		}
	}
	$scope.del = function() {

		var selrow = getSelectRow();

		if (angular.isDefined(selrow)&&angular.isDefined(selrow.id)) {

			$confirm({
				text : '是否删除?'
			}).then(
				function() {
					$http.post(
						$rootScope.project
						+ "/api/zc/resRepair/ext/deleteById.do", {
							id :selrow.id
						}).success(function(res) {
						if (res.success) {
							flush();
						} else {
							notify({
								message : res.message
							});
						}
					});
				});

		} else {
			return;
		}


	}

	$scope.modify = function() {
		var selrow = getSelectRow();

		if (angular.isDefined(selrow)&&angular.isDefined(selrow.id)) {
			if(selrow.fstatus=="finish"){
				notify({
					message : "当前状态不允许修改"
				});
				return
			}
			action('modify',selrow.id);
		} else {
			return;
		}


	}


	$scope.detail = function() {var selrow = getSelectRow();
		if (angular.isDefined(selrow)&&angular.isDefined(selrow.id)) {
			action('detail',selrow.id);
		} else {
			return;
		}

	}


	$scope.add=function(){
		action('add');
	}


	flush();

};

app.register.controller('cmdbfaultrecordCtl', cmdbfaultrecordCtl);
