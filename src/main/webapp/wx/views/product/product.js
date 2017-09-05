function prodCtl($log, $http, $scope, $rootScope, $state) {

	$rootScope.footshow = false;
	$rootScope.footcurrent = "prod";
	
	$scope.prodbanner = [ {
		id : 1,
		url : "image/banner/1.jpg",
		show : "Y"
	}, {
		id : 2,
		url : "image/banner/2.jpg",
		show : "Y"
	}, {
		id : 3,
		url : "image/banner/3.jpg",
		show : "Y"
	} ];

	$scope.prodClick=function(){
		alert(1);
	}
	$scope.someFunction=function(){
		alert(1);
	}
	 
 
	
}

app.register.controller('prodCtl', prodCtl);