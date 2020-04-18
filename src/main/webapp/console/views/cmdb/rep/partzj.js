function modalpartzcCtl($timeout, $localStorage, notify, $log, $uibModal,
		$uibModalInstance, $scope, meta, $http, $rootScope, DTOptionsBuilder,
		DTColumnBuilder, $compile) {
	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data')
			.withPaginationType('full_numbers').withDisplayLength(50)
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
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			});

	$scope.dtInstance = {}

	function renderJg(data, type, full) {
		var html = full.rackstr + "-" + full.frame;
		return html;
	}

	function renderName(data, type, full) {

		var html = full.model;
		return html;

	}

	function renderReview(data, type, full) {
		if (data == "reviewed") {
			return "已复核"
		} else {
			return "未复核"
		}
	}
	$scope.dtColumns = [];
	$scope.dtColumns=zcBaseColsCreate(DTColumnBuilder,'withoutselect');

	var ps = {}
	ps.part_id = meta.part_id;
	$http
			.post($rootScope.project + "/api/base/res/rep/queryZcTjByOrgId.do",
					ps).success(function(res) {
				if (res.success) {
					$scope.dtOptions.aaData = res.data;
				} else {
					notify({
						message : res.message
					});
				}
			})

	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

}

function cmdbrepPartZcCtl(DTOptionsBuilder, DTColumnBuilder, $compile,
		$confirm, $log, notify, $scope, $http, $rootScope, $uibModal, $window,$stateParams) {
	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data')
			.withPaginationType('full_numbers').withDisplayLength(50)
			.withOption("ordering", false).withOption("responsive", false)
			.withOption("searching", false).withOption('bAutoWidth', false)
			.withOption('paging', true).withOption('bStateSave', true)
			.withOption('bProcessing', false).withOption('bFilter', false)
			.withOption('bInfo', false).withOption('serverSide', false)
			.withOption('createdRow',
					function(row) {
						// Recompiling so we can bind Angular,directive to the
						$compile(angular.element(row).contents())($scope);
					});

	$scope.dtInstance = {}

	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";
		acthtml = acthtml + " <button ng-click=\"detail('" + full.part_id
				+ "')\" class=\"btn-white btn btn-xs\">详情</button> </div> ";
		return acthtml;
	}

	$scope.dtColumns = [
			DTColumnBuilder.newColumn('part_fullname').withTitle('使用部门')
					.withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('zc_cnt').withTitle('数量').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('part_id').withTitle('操作').withOption(
					'sDefaultContent', '').renderWith(renderAction) ]

	$scope.query = function() {
		flush();
	}

	var meta = {
		tablehide : false,
		toolsbtn : [],
		tools : [
				 
				{
					id : "btn",
					show : true,
					label : "",
					type : "btn",
					template : ' <button ng-click="query()" class="btn btn-sm btn-primary" type="submit">查询</button>'
				} ]
	}
	$scope.meta = meta;
 
	function flush() {
		var ps = {}
		$http.post($rootScope.project + "/api/base/res/rep/queryZcTjByOrg.do",
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
	$scope.btn_query=function(){
		flush();
	}
	$scope.detail = function(id) {
		var ps = {};
		ps.part_id = id;
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/cmdb/rep/modal_partzc.html',
			controller : modalpartzcCtl,
			size : 'blg',
			resolve : {
				meta : function() {
					return ps;
				}
			}
		});
		modalInstance.result.then(function(result) {

			if (result == "OK") {
			}
		}, function(reason) {

			$log.log("reason", reason)
		});

	}

};

app.register.controller('cmdbrepPartZcCtl', cmdbrepPartZcCtl);