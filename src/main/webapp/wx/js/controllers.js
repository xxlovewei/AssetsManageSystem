function MainCtrl($log, $http, $scope, $rootScope, $state) {

	/* 底部固定条 */
	$rootScope.footData = [ {
		id : "index",
		href : "index.ct",
		image : "mui-icon mui-icon-home",
		name : "首页"
	}, {
		id : "cat",
		href : "cat.ct",
		image : "mui-icon mui-icon-home",
		name : "分类"
	}, {
		id : "shop",
		href : "shop.ct",
		image : "mui-icon mui-icon-home",
		name : "购物车"
	}, {
		id : "me",
		href : "me.profile",
		image : "mui-icon mui-icon-home",
		name : "我"
	} ];
	$rootScope.footcurrent = "index";
	$rootScope.foothide = 1;
	$scope.btmClick = function(item) {
		//alert(angular.toJson(item));
		$rootScope.footcurrent = item.id;
		$state.go(item.href);
	}
};
angular.module('app').controller('MainCtrl', MainCtrl);