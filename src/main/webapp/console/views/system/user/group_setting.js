 
function groupSaveFormCtl($localStorage, notify, $log, $uibModal, $uibModalInstance, $scope, id, $http, $rootScope) {

	
	$log.warn("window in:",id);
	$scope.item = {}
  

	if (angular.isDefined(id)) {
		$http.post($rootScope.project + "/user/queryUserGroupById.do", {
			group_id : id
		}).success(function(res) {
			if (res.success) {
				$scope.item = res.data;
			} else {
				notify({
					message : res.message
				});
			}
		})
	} else {

	 
	}
	$scope.sure = function() {
		 
		$http.post($rootScope.project + "/user/saveUserGroupById.do", $scope.item).success(function(res) {
			if (res.success) {
				$uibModalInstance.close("OK");
			}  
			notify({
				message : res.message
			});
		});

	}
	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};
}

function prepend(arr, item) {
	// 将arr数组复制给a
	var a = arr.slice(0);
	// 使用unshift方法向a开头添加item
	a.unshift(item);
	return a;
}

function sysGroupSettingCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withPaginationType('full_numbers').withDisplayLength(25).withOption("ordering", false).withOption("responsive", true)
			.withOption("searching", false).withOption("paging", false).withOption('bStateSave', true).withOption('bProcessing', true).withOption('bFilter', false).withOption(
					'bInfo', false).withOption('serverSide', false).withOption('bAutoWidth', false).withOption('aaData', $scope.tabdata).withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			}).withLanguage(DTLang);

	$scope.dtInstance = {}
	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";
		acthtml = acthtml + " <button ng-click=\"row_dtl('" + full.USER_ID + "')\" class=\"btn-white btn btn-xs\">详细</button> </div> ";
		return acthtml;
	}

	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";
		acthtml = acthtml + " <button ng-click=\"save('" + full.GROUP_ID
		+ "')\" class=\"btn-white btn btn-xs\">修改</button>   ";
		acthtml = acthtml + " <button ng-click=\"row_delete('" + full.GROUP_ID
				+ "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
		return acthtml;
	}
	
	
	$scope.dtColumns = [ DTColumnBuilder.newColumn('NAME').withTitle('名称').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('MARK').withTitle('备注').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('GROUP_ID').withTitle('动作').withOption('sDefaultContent', '').renderWith(renderAction) ]

	function flush() {
		$http.post($rootScope.project + "/user/queryGroup.do", {}).success(function(res) {
			if (res.success) {
				$scope.dtOptions.aaData = res.data;
			}
		})
	}
	
	flush();

	 

	$scope.row_delete = function(id) {
		$confirm({
			text : '是否删除?'
		}).then(function() {
			$http.post($rootScope.project + "/user/deleteGroup.do", {
				group_id : id
			}).success(function(res) {
				flush();
				notify({
					message : res.message
				});
			});
		});


	}

 
	$scope.query = function() {
		flush();
	}
	$scope.save = function(id) {
		
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/system/user/modal_group_save.html',
			controller : groupSaveFormCtl,
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
};

app.register.controller('sysGroupSettingCtl', sysGroupSettingCtl);