function meCtl($log, $http, $scope, $rootScope, $state) {

	$rootScope.footcurrent = "me";

	$scope.ltdata = [ {
		ltuser_name : "人员",
		code : "1212",
		id : "23rsa",
		lt_time : "2012年1月1日",
		statusname : "预约中"
	}, {
		ltuser_name : "人员",
		code : "1212",
		id : "23rsa",
		lt_time : "2012年1月1日",
		statusname : "预约中"
	} ]
	$scope.tabs = {
		cur : 0,
		data : [ {
			id : "a",
			textid : "aa",
			status : 0,
			text : "所有"
		}, {
			id : "b",
			textid : "bb",
			status : 1,
			text : "派单中"
		}, {
			id : "c",
			textid : "cc",
			status : 2,
			text : "已派单"
		}, {
			id : "d",
			textid : "dd",
			status : 3,
			text : "已完成"
		} ]
	}
	function tabchange(index) {
		var data = $scope.tabs.data;
		console.log("click:" + index)
		for (var i = 0; i < data.length; i++) {
			$("#" + data[i].id).removeClass("am-active");
			$("#" + data[i].textid).removeClass("am-active");
		}
		$("#" + data[index].id).addClass("am-active");
		$("#" + data[index].textid).addClass("am-active");
		$scope.tabs.cur = index;

		// get data from service
		if (index == 1) {
			$scope.ltdata = [ {
				ltuser_name : "人员",
				code : "1212",
				id : "23rsa",
				lt_time : "2012年1月1日",
				statusname : "预约中"
			}, {
				ltuser_name : "人员",
				code : "1212",
				id : "23rsa",
				lt_time : "2012年1月1日",
				statusname : "预约中"
			}, {
				ltuser_name : "人员",
				code : "1212",
				id : "23rsa",
				lt_time : "2012年1月1日",
				statusname : "预约中"
			}, {
				ltuser_name : "人员",
				code : "1212",
				id : "23rsa",
				lt_time : "2012年1月1日",
				statusname : "预约中"
			} ]
		} else if (index == 0) {
			$scope.ltdata = [ {
				ltuser_name : "人员",
				code : "1212",
				id : "23rsa",
				lt_time : "2012年1月1日",
				statusname : "预约中"
			} ]
		} else if (index == 3) {
			$scope.ltdata = []
		}
		console.log($scope.tabs);
	}
	setTimeout(function() {
		tabchange(0)
	}, 300);

	$scope.tabclick = function(id) {
		tabchange(id);
	}
	$scope.itemClick = function(item) {
		alert(item);
	}

}

app.register.controller('meCtl', meCtl);