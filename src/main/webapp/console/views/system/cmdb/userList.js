function cmdblistUserCtl($timeout,$localStorage, notify, $log, $uibModal, $uibModalInstance, $scope, id, $http, $rootScope) {
 
	$scope.users=[];
	if (angular.isDefined(id)) {
		// 加载数据
		$http.post($rootScope.project + "/api/res/queryResAllById.do", {
			id : id
		}).success(function(res) {
			if (res.success) {
				$scope.users=res.data.userlist;
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
}

function cmdbUserListCtl(DTOptionsBuilder, DTColumnBuilder, $compile, $confirm,
		$log, notify, $scope, $http, $rootScope, $uibModal) {

	 
	 
	// 自动获取配置项
	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withOption('createdRow', function(row) {
		// Recompiling so we can bind Angular,directive to the
		$compile(angular.element(row).contents())($scope);
	});

 

	$scope.dtInstance = {}

	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";
		acthtml = acthtml + " <button ng-click=\"listuser('" + full.id
				+ "')\" class=\"btn-white btn btn-xs\">用户</button>  ";
		acthtml = acthtml + " <button ng-click=\"del('" + full.id
				+ "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
		return acthtml;
	}
	$scope.dtColumns = [
			DTColumnBuilder.newColumn('ip').withTitle('IP').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('name').withTitle('名称').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('ucnt').withTitle('数量').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('id').withTitle('操作').withOption(
					'sDefaultContent', '').renderWith(renderAction) ]

	function flush() {
		var ps = {}
		 
		$http
				.post(
						$rootScope.project
								+ "/api/res/queryResByNodeForUser.do",
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
	flush();

	$scope.query = function() {
		flush();

	}

	$scope.del = function(seq) {
		alert("待开发");

	}
	
	$scope.listuser=function(id){
		

		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/system/cmdb/modal_listUsers.html',
			controller : cmdblistUserCtl,
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

	 

};

app.register.controller('cmdbUserListCtl', cmdbUserListCtl);