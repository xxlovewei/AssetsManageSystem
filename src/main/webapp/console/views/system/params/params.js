function sysParamSaveCtl($localStorage, notify, $log, $uibModal, $uibModalInstance, $scope, id, $http, $rootScope) {

	$log.warn("window in:" + id);
	$scope.item = {};
	$scope.typeOpt = [ {
		id : "system",
		name : "系统"
	}, {
		id : "user",
		name : "用户"
	} ]
	$scope.typeSel = $scope.typeOpt[0];
	if (angular.isDefined(id)) {
		// 加载数据
		$http.post($rootScope.project + "/api/sysParams/selectById.do", {
			id : id
		}).success(function(res) {
			if (res.success) {
				$scope.item = res.data
				// DICT_LEVEL
				if ($scope.item.dict_level == "system") {
					$scope.typeSel = $scope.typeOpt[0];
				} else if ($scope.item.dict_level == "user") {
					$scope.typeSel = $scope.typeOpt[1];
				}
			} else {
				notify({
					message : res.message
				});
			}
		})
	}

	$scope.sure = function() {

		$scope.item.type = $scope.typeSel.id;
		$http.post($rootScope.project + "/api/sysParams/insertOrUpdate.do", $scope.item).success(function(res) {
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

function sysParamsCtl( DTOptionsBuilder, DTColumnBuilder, $compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	
	$scope.meta ={
			tools : [ {
				id : "1",
				label : "新增",
				type : "btn",
				template:' <button ng-click="add()" class="btn btn-sm btn-primary" type="submit">新增</button>'
	 
			} ]
		}
 
	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withOption('bAutoWidth',false).withOption('createdRow', function(row) {
		// Recompiling so we can bind Angular,directive to the
		$compile(angular.element(row).contents())($scope);
	});
	$scope.dtInstance = {}

	function renderStatus(data, type, full) {

		var value = data
		if (data == 'system') {
			value = '系统'
		} else if (data == 'user') {
			value = '用户'
		}
		return value;
	}

 
	$scope.dtColumns = [ DTColumnBuilder.newColumn('name').withTitle('名称').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('value').withTitle('编码').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('type').withTitle('类型').withOption('sDefaultContent', '').renderWith(renderStatus),
			DTColumnBuilder.newColumn('mark').withTitle('备注').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('id').withTitle('操作').renderWith(dt_renderUDAction)
					]

	function flush() {
		var ps = {}

		$http.post($rootScope.project + "/api/sysParams/selectList.do", ps).success(function(res) {
			if (res.success) {
				$scope.dtOptions.aaData = res.data;
			}
		})
	}
	 
	flush();
	$scope.update=function(id){
		save(id);
	}
	function save(id) {

		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/system/params/modal_paramsSave.html',
			controller : sysParamSaveCtl,
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
		save();
	}

	 
	$scope.del = function(id) {
		if (angular.isDefined(id)) {
			//删除
			$confirm({
				text : '是否删除?'
			}).then(function() {
				$http.post($rootScope.project + "/api/sysParams/deleteById.do", {
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

		} else {
			notify({
				message : "请选择一行"
			});
		}

	}

};

app.register.controller('sysParamsCtl', sysParamsCtl);