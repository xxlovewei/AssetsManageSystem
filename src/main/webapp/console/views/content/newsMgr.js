
function ctNewsMgrCtl( DTOptionsBuilder, DTColumnBuilder, $compile, $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {
	
//	$scope.meta ={
//			tools : []
//		}
//	 
	
	
	$scope.URL = $rootScope.project + "/api/news/queryNewsByPage.do?noContent=Y";
	$scope.dtOptions = DTOptionsBuilder.fromSource($scope.URL).withDataProp('data').withOption('bProcessing', true).
	withOption('scrollY', '700px')
    .withOption('scrollX', true)
        .withOption('bAutoWidth', false)
       .withOption('responsive',false)
    .withOption('scrollCollapse', true)
    .withFixedColumns({
        leftColumns: 1,
        rightColumns: 1
    }).withOption('serverSide', true).withOption('createdRow', function(row) {
				// Recompiling so we can bind Angular,directive to the
				$compile(angular.element(row).contents())($scope);
			});
 	 
	
	$scope.reloadData = reloadData;
	$scope.dtInstance={}
	function reloadData() {
		var resetPaging = true;
		$scope.dtInstance.reloadData(callback, false);
	}
	function callback(json) {
		console.log(json);
	}
	function renderAction(data, type, full) {
		var acthtml = " <div class=\"btn-group\"> ";
 
		acthtml = acthtml + " <button ng-click=\"row_del('" + full.id + "')\" class=\"btn-white btn btn-xs\">删除</button> </div> ";
		return acthtml;
	}
	function renderStatus(data, type, full) {
		var res = "正常";
		if (full.LOCKED == "Y") {
			res = "锁定";
		}
		return res;
	}

	function renderType(data, type, full) {
		if (data == "outer") {
			return "外链";
		} else if (data == "local") {
			return "内置";
		} else {
			return data;
		}
	}

	function renderMImage(data, type, full) {
		var html = ""
		html = html + "<img style='height:50px;width:50px;' src=" + $rootScope.project + "/api/file/imagedown.do?id=" + full.mpic + "  />"
		return html;
	}

	$scope.dtColumns = [

	DTColumnBuilder.newColumn('id').withTitle('主图').withOption('sDefaultContent', '').renderWith(renderMImage),
			DTColumnBuilder.newColumn('title').withTitle('标题').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('createTime').withTitle('创建时间').withOption('sDefaultContent', ''),
			DTColumnBuilder.newColumn('hits').withTitle('点击量').withOption('sDefaultContent', '') ,
			DTColumnBuilder.newColumn('urltype').withTitle('链接类型').withOption('sDefaultContent', '').renderWith(renderType),
			DTColumnBuilder.newColumn('userId').withTitle('操作').withOption('sDefaultContent', '').renderWith(renderAction) ]

	$scope.row_del = function(id) {
	//	reloadData();
		//return ;
		$confirm({
			text : '是否删除?'
		}).then(function() {
			$http.post($rootScope.project + "/api/news/deleteNews.do", {
				id : id
			}).success(function(res) {
				if (res.success) {
					reloadData();
				}
				notify({
					message : res.message
				});
			})
		});

	}

	$scope.query = function() {
		reloadData();
	}
	$scope.save = function(id) {

	}

};

app.register.controller('ctNewsMgrCtl', ctNewsMgrCtl);