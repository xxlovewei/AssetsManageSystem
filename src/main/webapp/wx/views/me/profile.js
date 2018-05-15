function meCtl($log, $http, $scope, $rootScope, $state) {

	function isElementInViewport (el, offset = 0) {
        const box = el.getBoundingClientRect(),
              top = (box.top >= 0),
              left = (box.left >= 0),
              bottom = (box.bottom <= (window.innerHeight || document.documentElement.clientHeight) + offset),
              right = (box.right <= (window.innerWidth || document.documentElement.clientWidth) + offset);
        return (top && left && bottom && right);
    }
	
	
	$scope.item={
			dizhi:"",
			xingming:"王可可",
			rq:"",
			shouji:"15728001528",
			zone:"浙江省-宁波-海曙"
	}
	$scope.selZone=function(){
		console.log('selZone');
		
	}
	$scope.selRq=function(){
		console.log('selRq');	
	}
 
	$scope.sure = function() {
		// 如果有数据,可以触发提交
		if(angular.isDefined($scope.item.dizhi)&&$scope.item.dizhi.length>0){
			toYY();
			return;
		}
		var box=document.getElementById('firstel');
		var boxTop=box.getBoundingClientRect().top;
		var isshow=isElementInViewport(box);
		console.log(isshow);
		// 可见
		if(isshow||boxTop<=0){
			console.log("can see")
			toYY();
		}else{
			 $('html, body').animate({  
                 scrollTop: $("#firstel").offset().top  
             }, 800);  
		}
	}
 
	function toYY(){

		if(!(angular.isDefined($scope.item.dizhi)&&$scope.item.dizhi.length>0)){
			alert("请输入地址");
			return;
		}
		if(!(angular.isDefined($scope.item.shouji)&&$scope.item.shouji.length>0)){
			alert("请输入手机");
			return;
		}
		if(!(angular.isDefined($scope.item.xingming)&&$scope.item.xingming.length>0)){
			alert("请输入姓名");
			return;
		}
		if(!(angular.isDefined($scope.item.rq)&&$scope.item.rq.length>0)){
			alert("请选择日期");
			return;
		}
		if(!(angular.isDefined($scope.item.chengs)&&$scope.item.chengs.length>0)){
			alert("请选择区域");
			return;
		}
		console.log('ToYY');
	}
	
	
	
	$rootScope.footcurrent = "me";
	$rootScope.footshow = false;
	$scope.ltdata = [ {
		ltuser_name : "人员",
		code : "1212",
		id : "23rsa",
		lt_time : "2012年1月1日",
		statusname : "预约中"
	}, {
		ltuser_name : "人员",
		code : "1212",
		id : "23rsa",
		lt_time : "2012年1月1日",
		statusname : "预约中"
	} ]
	$scope.tabs = {
		cur : 0,
		data : [ {
			id : "a",
			textid : "aa",
			status : 0,
			text : "所有"
		}, {
			id : "b",
			textid : "bb",
			status : 1,
			text : "派单中"
		}, {
			id : "c",
			textid : "cc",
			status : 2,
			text : "已派单"
		}, {
			id : "d",
			textid : "dd",
			status : 3,
			text : "已完成"
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
		if (index == 1) {
			$scope.ltdata = [ {
				ltuser_name : "人员",
				code : "1212",
				id : "23rsa",
				lt_time : "2012年1月1日",
				statusname : "预约中"
			}, {
				ltuser_name : "人员",
				code : "1212",
				id : "23rsa",
				lt_time : "2012年1月1日",
				statusname : "预约中"
			}, {
				ltuser_name : "人员",
				code : "1212",
				id : "23rsa",
				lt_time : "2012年1月1日",
				statusname : "预约中"
			}, {
				ltuser_name : "人员",
				code : "1212",
				id : "23rsa",
				lt_time : "2012年1月1日",
				statusname : "预约中"
			} ]
		} else if (index == 0) {
			$scope.ltdata = [ {
				ltuser_name : "人员",
				code : "1212",
				id : "23rsa",
				lt_time : "2012年1月1日",
				statusname : "预约中"
			} ]
		} else if (index == 3) {
			$scope.ltdata = []
		}
		console.log($scope.tabs);
	}
	setTimeout(function() {
		tabchange(0)
	}, 300);

	$scope.tabclick = function(id) {
		tabchange(id);
	}
	$scope.itemClick = function(item) {
		alert(item);
	}

}

app.register.controller('meCtl', meCtl);