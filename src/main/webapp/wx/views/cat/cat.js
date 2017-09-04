function catCtl($log, $http, $scope, $rootScope, $state) {
	$rootScope.footcurrent = "cat";

	$scope.catClass = [ {
		ID : 1,
		NAME : "衣服"
	}, {
		ID : 2,
		NAME : "食物"
	}, {
		ID : 3,
		NAME : "数码"
	}, {
		ID : 3,
		NAME : "汽车"
	},{
		ID : 1,
		NAME : "衣服"
	}, {
		ID : 2,
		NAME : "食物"
	}, {
		ID : 3,
		NAME : "数码"
	}, {
		ID : 3,
		NAME : "汽车"
	} ,{
		ID : 1,
		NAME : "衣服"
	}, {
		ID : 2,
		NAME : "食物"
	}, {
		ID : 3,
		NAME : "数码"
	}, {
		ID : 3,
		NAME : "汽车"
	} ,{
		ID : 1,
		NAME : "衣服"
	}, {
		ID : 2,
		NAME : "食物"
	}, {
		ID : 3,
		NAME : "数码"
	}, {
		ID : 3,
		NAME : "汽车"
	} , {
		ID : 3,
		NAME : "数码"
	}, {
		ID : 3,
		NAME : "汽车"
	}  ]
	$scope.catClassSel=$scope.catClass[0].ID;
	
	$scope.catSubClass = [];
	$scope.catNavClick = function(item) {
		var arr=[];
		for(var i=0;i<10;i++){
			arr.push({ID:1,NAME:item.NAME+i})
		}
		$scope.catSubClass=arr;
	}
	
	

}

app.register.controller('catCtl', catCtl);