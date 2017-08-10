 
/**
 * MainCtrl - controller Contains several global data used in different view
 * 
 */
function MainCtrl($log, $http, $scope, $rootScope, $state, $localStorage,
		userService) {

	//退出登录
	$scope.logout = function() {
		userService.loginout().then(function(result) {
			console.log("userService logout result", result)
			if (result.success) {
				$state.go("login");
			}
		}, function(error) {

		}, function(progress) {

		})

	}

	//处理左边菜单
	$scope.$watch(function() {
		return $rootScope.dt_sys_menus;
	}, function() {
		$log.warn("watch $rootScope.dt_sys_menus change,load from $rootScope",
				$rootScope.dt_sys_menus);
		if (angular.isDefined($rootScope.dt_sys_menus)) {
			$scope.menu = $rootScope.dt_sys_menus;

			$state.go($state.current, '', {
				reload : true
			});
		}

	}, true);

	//从localstorage中获取最新菜单,每次登录将会重置
	var dt_sys_menu = $localStorage.get("dt_sys_menus");
	if (angular.isDefined(dt_sys_menu)) {
		$log.warn("fresh load menus from localstorage");
		$scope.menu = dt_sys_menu;
		fixnav();
	}

	//用户信息
	$scope.$watch(function() {
		return $rootScope.dt_sys_user_info;
	}, function() {
		if (angular.isDefined($rootScope.dt_sys_user_info)) {
			$log.warn("user info from mem");
			$scope.sys_user_info = $rootScope.dt_sys_user_info
		}
	}, true);

	var sys_user_info = $localStorage.get("dt_sys_user_info")
	if (angular.isDefined(sys_user_info)) {
		$log.warn("user info from Local");
		$scope.sys_user_info = sys_user_info;
	}

	$scope.exampleshow = false;

	//固定左边导航
	function fixnav() {
	
//		
//		$("body").addClass('fixed-sidebar');
//		$('.sidebar-collapse').slimScroll({
//			height : '100%',
//			railOpacity : 0.9
//		});
////		if ($('#fixedsidebar').is(':checked')) {
////			$("body").addClass('fixed-sidebar');
////			$('.sidebar-collapse').slimScroll({
////				height : '100%',
////				railOpacity : 0.9
////			});
////		} else {
////			$('.sidebar-collapse').slimscroll({
////				destroy : true
////			});
////			$('.sidebar-collapse').attr('style', '');
////			$("body").removeClass('fixed-sidebar');
////		}
	}
	fixnav();

 
};
//app.register.controller('MainCtrl', MainCtrl);
angular.module('inspinia').controller('MainCtrl', MainCtrl);