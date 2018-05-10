function meCtl($log, $http, $scope, $rootScope, $state) {

	$rootScope.footcurrent = "me";

	$scope.tabs = {
		cur : 0,
		data : [ {
			 
			id : "a",
			textid : "aa",
			text : "所有"
		}, {
		 
			id : "b",
			textid : "bb",
			text : "A"
		}, {
 
			id : "c",
			textid : "cc",
			text : "B"
		}, {
	 
			id : "d",
			textid : "dd",
			text : "C"
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
		console.log($scope.tabs);
	}
	setTimeout(function() {
		tabchange(1)
	}, 300);

	$scope.tabclick = function(id) {
		tabchange(id);
	}
	$scope.itemClick = function(item) {
		alert(item);
	}

}

app.register.controller('meCtl', meCtl);