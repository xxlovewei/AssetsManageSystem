function meCtl($log, $http, $scope, $rootScope, $state) {

	$rootScope.footcurrent = "me";
	$scope.itemClick=function(item){
		alert(item);
	}
}

app.register.controller('meCtl', meCtl);