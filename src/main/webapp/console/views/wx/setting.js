function msgsettingsaveCtl(notify, $log, $uibModal, $uibModalInstance, $scope,
		id, $http, $rootScope) {

	$scope.classOpt = [{
				id : "action",
				name : "操作"
			}, {
				id : "push",
				name : "推送"
			}, {
				id : "reply",
				name : "回复"
			}];

	$scope.classSel = $scope.classOpt[0];
	console.log("window in:" + id);
	$scope.msgtypeOpt = [{
				id : "6",
				name : "图文消息"
			}, {
				id : "text",
				name : "文本消息"
			}]
	$scope.msgtypeSel = $scope.msgtypeOpt[1];
	$scope.item = {};
	if (angular.isDefined(id)) {
		$http.post($rootScope.project + "/api/wx/queryMessageById.do", {
					id : id
				}).success(function(res) {
					if (res.success) {
						$scope.item = res.data;

						for (var i = 0; i < $scope.msgtypeOpt.length; i++) {
							if ($scope.msgtypeOpt[i].id == $scope.item.msgtype) {
								$scope.msgtypeSel = $scope.msgtypeOpt[i];
								break;
							}
						}

						for (var i = 0; i < $scope.classOpt.length; i++) {
							if ($scope.classOpt[i].id == $scope.item.funtype) {
								$scope.classSel = $scope.classOpt[i];
								break;
							}
						}
					} else {

					}
				})

	}

	$scope.sure = function() {

		$scope.item.msgtype = $scope.msgtypeSel.id;
		$scope.item.funtype = $scope.classSel.id;
		$http.post($rootScope.project + "/api/wx/saveMessage.do", $scope.item)
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

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

}

function wxmsgsettingCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile,
		$confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.classOpt = [{
				id : "all",
				name : "全部"
			}, {
				id : "action",
				name : "操作"
			}, {
				id : "push",
				name : "推送"
			}, {
				id : "reply",
				name : "回复"
			}];

	$scope.classSel = $scope.classOpt[0];
	$scope.dtOptions = DTOptionsBuilder.fromFnPromise()
			.withPaginationType('full_numbers').withDisplayLength(25)
			.withOption("ordering", false).withOption("responsive", true)
			.withOption("searching", false).withOption("paging", false)
			.withOption('bStateSave', true).withOption('bProcessing', true)
			.withOption('bFilter', false).withOption('bInfo', false)
			.withOption('serverSide', false).withOption('bAutoWidth', false)
			.withOption('aaData', $scope.tabdata).withOption('createdRow',
					function(row) {
						// Recompiling so we can bind Angular,directive to the
						$compile(angular.element(row).contents())($scope);
					}).withLanguage(DTLang);
	$scope.dtInstance = {}
	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";
		acthtml = acthtml + " <button ng-click=\"modify('" + full.id
				+ "')\" class=\"btn-white btn btn-xs\">更新</button>  ";
		acthtml = acthtml + " <button ng-click=\"deleterow('" + full.id
				+ "')\" class=\"btn-white btn btn-xs\">删除</button>  </div>  ";
		return acthtml;
	}
	function renderType(data, type, full) {

		if (data == "6") {
			return "图文消息";
		} else if (data == "text") {
			return "文本消息";
		} else {
			return data;
		}
	}

	function renderClass(data, type, full) {

		if (data == "reply") {
			return "回复";
		} else if (data == "push") {
			return "推送";
		} else if (data == "action") {
			return "操作";
		} else {
			return data;
		}
	}
	$scope.dtColumns = [
			DTColumnBuilder.newColumn('funtype').withTitle('分类').withOption(
					'sDefaultContent', '').renderWith(renderClass),
			DTColumnBuilder.newColumn('msgtype').withTitle('类型').withOption(
					'sDefaultContent', '').renderWith(renderType),
			DTColumnBuilder.newColumn('name').withTitle('名称').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('code').withTitle('编码').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('value').withTitle('内容').withOption(
					'sDefaultContent', '').withClass('none'),
			DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
					'sDefaultContent', ''),
			/*
			 * DTColumnBuilder.newColumn('is_auto').withTitle('自动回复').withOption(
			 * 'sDefaultContent', ''),
			 */
			DTColumnBuilder.newColumn('id').withTitle('操作').withOption(
					'sDefaultContent', '').renderWith(renderAction)]

	function flush() {
		var ps = {}
		if ($scope.classSel.id != "all") {
			ps.funtype = $scope.classSel.id;
		}
		$http.post($rootScope.project + "/api/wx/queryMessages.do", ps)
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

	$scope.flush = function() {
		flush();
	}

	$scope.modify = function(id) {

		var modalInstance = $uibModal.open({
					backdrop : true,
					templateUrl : 'views/wx/modal_msgsetting.html',
					controller : msgsettingsaveCtl,
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

	$scope.deleterow = function(id) {

		$confirm({
					text : '是否删除?'
				}).then(function() {
					$http.post($rootScope.project + "/api/wx/deleteMessage.do",
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

};

app.register.controller('wxmsgsettingCtl', wxmsgsettingCtl);