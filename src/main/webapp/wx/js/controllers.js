function MainCtrl($log, $http, $scope, $rootScope, $state, $window) {

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