function prodCtl( DTOptionsBuilder, DTColumnBuilder, $compile, $confirm,
		$log, notify, $scope, $http, $rootScope, $uibModal) {

	var opt=[ {
		id : "1",
		name : "adfasdfasA"
	}, {
		id : "1",
		name : "B"
	}, {
		id : "1",
		name : "C"
	} ];
	
	$scope.abcd1=function(){
		console.log(1);
		console.log($scope.meta.tools[0].dataSel)
	}
	$scope.abcd2=function(){
		console.log(2);
	}
	function abcd1(){
		console.log(111)
		
	}
	var meta = {
		tools : [ {
			id : "1",
			name : "刷新",
			type : "select",
			disablesearch:false,
			dataOpt :opt ,
			dataSel : opt[0]
		}, {
			id : "1",
			name : "刷新",
			type : "btn",
			template:' <button ng-click="abcd1()" class="btn btn-sm btn-primary" type="submit">查询</button>'
 
		} ]
	}
	$scope.meta = meta;
 
	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			});
	$scope.dtInstance = {}
	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";
		acthtml = acthtml + " <button ng-click=\"save('" + full.role_id
				+ "')\" class=\"btn-white btn btn-xs\">编辑</button> ";
		// acthtml = acthtml + " <button ng-click=\"row_detail()\"
		// class=\"btn-white btn btn-xs\">详细</button> ";
		acthtml = acthtml + " <button ng-click=\"row_del('" + full.role_id
				+ "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
		return acthtml;
	}
	function renderStatus(data, type, full) {
		var res = "无效";
		if (full.is_action == "Y") {
			res = "有效";
		}
		return res;
	}

	$scope.dtColumns = [
			DTColumnBuilder.newColumn('role_name').withTitle('名称').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('is_action').withTitle('状态').withOption(
					'sDefaultContent', '').renderWith(renderStatus),
			DTColumnBuilder.newColumn('role_id').withTitle('操作').withOption(
					'sDefaultContent', '').renderWith(renderAction) ]

	function flush() {
		var ps = {}
		$http.post($rootScope.project + "/api/role/roleQuery.do", ps).success(
				function(res) {
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

	$scope.row_detail = function(id) {

	}
	$scope.row_del = function(id) {
		$confirm({
			text : '是否删除功能?'
		}).then(function() {
			$http.post($rootScope.project + "/api/role/roleDelete.do", {
				role_id : id
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
			templateUrl : 'views/system/role/modal_role_save.html',
			controller : roleSaveCtl,
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
app.register.controller('prodCtl', prodCtl);