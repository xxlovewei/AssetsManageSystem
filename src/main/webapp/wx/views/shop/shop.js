function shopCtl($log, $http, $scope, $rootScope, $state) {
	 
 
	$rootScope.footcurrent = "shop";
	console.log($rootScope.footcurrent );
}

app.register.controller('shopCtl', shopCtl);