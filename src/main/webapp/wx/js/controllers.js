function MainCtrl($log, $http, $scope, $rootScope, $state,$window) {

	/* 底部固定条 */
	$rootScope.footData = [ {
		id : "index",
		href : "index.ct",
		image : "mui-icon mui-icon-home",
		name : "首页"
	}, {
		id : "cat",
		href : "cat.ct",
		image : "mui-icon-extra mui-icon-extra-class",
		name : "分类"
	}, {
		id : "shop",
		href : "shop.ct",
		image : "mui-icon-extra mui-icon-extra-cart",
		name : "购物车"
	}, {
		id : "me",
		href : "me.profile",
		image : "mui-icon mui-icon-contact",
		name : "我"
	} ];

	$rootScope.footshow = true
	$scope.footerClick = function(item) {

		$rootScope.footcurrent = item.id;
		if (item == "index") {
			//$state.go("index.ct");
			$window.open("/dt/wx/dtl.html", '_blank');
		} else if (item == "cat") {
			$state.go("cat.ct");
		} else if (item == "shop") {
			$state.go("shop.ct");
		} else if (item == "me") {
			$state.go("me.profile");
		}

	}

	$scope.goIndex = function() {
		 
		//$window.open("/dt/wx/loc.html", '_blank');
		 $state.go("index.ct");
	}

	$scope.goBack = function() {
		history.back();
	}
};
angular.module('app').controller('MainCtrl', MainCtrl);