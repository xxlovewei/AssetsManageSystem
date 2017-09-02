function MainCtrl($log, $http, $scope, $rootScope, $state) {

	/* 底部固定条 */
	$rootScope.footData = [ {
		id : "index",
		href : "index.ct",
		image : "glyphicon glyphicon-user",
		name : "首页"
	}, {
		id : "cat",
		href : "cat.ct",
		image : "glyphicon glyphicon-user",
		name : "分类"
	}, {
		id : "shop",
		href : "shop.ct",
		image : "glyphicon glyphicon-user",
		name : "购物车"
	}, {
		id : "me",
		href : "me.profile",
		image : "glyphicon glyphicon-user",
		name : "我"
	} ];
	$rootScope.footcurrent = "index";
	$rootScope.foothide = 1;
	$scope.btmClick = function(item) {
		$rootScope.footcurrent = item.id;
		$state.go(item.href);
	}
};
angular.module('app').controller('MainCtrl', MainCtrl);