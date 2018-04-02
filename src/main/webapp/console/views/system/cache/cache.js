function sysCacheCtl(DTLang, DTOptionsBuilder, DTColumnBuilder, $compile,
		$confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {

	$scope.cacheOpt = [];
	$scope.cacheSel = "";
	$http.post($rootScope.project + "/api/system/queryCacheName.do", {})
			.success(function(res) {
				if (res.success) {
					$scope.cacheOpt = res.data;
					if (res.data.length > 0) {
						$scope.cacheSel = $scope.cacheOpt[0];
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

		acthtml = acthtml + " <button ng-click=\"removeCacheKey('" + full.key
				+ "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
		return acthtml;
	}

	function renderStatus(data, type, full) {

		var jobenableimg = '<img src="img/sm/unknow.png"/>';
		if (data == 'true') {
			jobenableimg = '<img src="img/sm/green.png"/>';
		} else if (data == 'false') {
			jobenableimg = '<img src="img/sm/grey.png"/>';
		}
		return jobenableimg;
	}

	function renderRunStatus(data, type, full) {

		var value = data
		if (data == 'NORMAL') {
			value = '正常'
		} else if (data == 'PAUSED') {
			value = '暂停'
		}
		return value;
	}

	$scope.dtColumns = [

			DTColumnBuilder.newColumn('key').withTitle('Key').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('hit').withTitle('命中次数').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('ctime').withTitle('创建时间').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('accesstime').withTitle('访问时间')
					.withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('ttl').withTitle('TTL').withOption(
					'sDefaultContent', ''),
			DTColumnBuilder.newColumn('tti').withTitle('TTI').withOption(
					'sDefaultContent', '').withClass('none'),
			DTColumnBuilder.newColumn('key').withTitle('动作').withOption(
					'sDefaultContent', '').renderWith(renderAction) ]

	function flush() {

		$http.post($rootScope.project + "/api/system/queryCacheKeys.do", {
			cache : $scope.cacheSel.id
		}).success(function(res) {
			if (res.success) {
				$scope.dtOptions.aaData = res.data;
			} else {
				notify({
					message : res.message
				});
			}
		})
	}

	$scope.flush = function() {
		flush();
	}

	$scope.save = function() {
		alert("待开发");
	}

	$scope.del = function(seq) {
		alert("待开发");

	}
	$scope.removeCacheKey = function(key) {
		$http.post($rootScope.project + "/api/system/removeCacheKey.do", {
			key : key,
			cache : $scope.cacheSel.id
		}).success(function(res) {
			notify({
				message : res.message
			});
			if (res.success) {
				flush();
			}
		})
	}

	$scope.enable = function(seq) {
		$http.post($rootScope.project + "/api/schedule/enablejob.do", {
			seq : seq
		}).success(function(res) {
			notify({
				message : res.message
			});
			if (res.success) {
				flush();
			}
		})
	}

};

app.register.controller('sysCacheCtl', sysCacheCtl);