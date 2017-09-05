function indexProdClassCtl($log, $http, $scope, $rootScope, $state, $timeout) {
	$rootScope.footcurrent = "index";
	$rootScope.footshow = true;
	$scope.swipe = function($event) {
		console.log($event);
	};

	$scope.prodsList = {
		ID : "1",
		NAME : "热门产品",
		DATA : [ {
			ID : "1",
			NAME : "信息来区别不同事物，得以认识和信息来区别不同事物，得以认识和改",
			PRICE : "12",
			IMG : "image/product/hanshi1-200x200.jpg"
		}, {
			ID : "1",
			NAME : "信息来区别不同事物，得以认识和信息来区别不同事物，得以认识和改",
			PRICE : "12",
			IMG : "image/product/hanshi1-200x200.jpg"
		}, {
			ID : "1",
			NAME : "d",
			PRICE : "12",
			IMG : "image/product/hanshi1-200x200.jpg"
		}, {
			ID : "1",
			NAME : "信息来区别不同事物，得以认识和信息来区别不同事物，得以认识和改",
			PRICE : "12",
			IMG : "image/product/hanshi1-200x200.jpg"
		}, {
			ID : "1",
			NAME : "信息来区别不同事物，得以认识和信息来区别不同事物，得以认识和改",
			PRICE : "12",
			IMG : "image/product/hanshi1-200x200.jpg"
		} ]
	}
	$scope.indexbanner = [];
	$scope.indexbanner = [ {
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
		$state.go("product.info");
	}
	 

}

app.register.controller('indexProdClassCtl', indexProdClassCtl);