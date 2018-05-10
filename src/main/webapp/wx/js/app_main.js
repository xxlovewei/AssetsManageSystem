var app = angular.module('app', ['ui.router', 'oc.lazyLoad',
				'angular-loading-bar', 'ksSwiper'])
var $injector = angular.injector();

function pageTitle($rootScope, $timeout, $transitions) {
	return {
		link : function(scope, element) {
			$transitions.onSuccess({
						to : '**'
					}, function(trans) {
						console.log("set title.");
						var current = trans.router.globals.current;
						var title = "无";
						if (angular.isDefined(current.data)
								&& angular.isDefined(current.data.pageTitle)) {
							title = current.data.pageTitle;
						}
						element.text(title);
					});
		}
	}
};
var version = "20170906";

function config_main(cfpLoadingBarProvider, $locationProvider,
		$controllerProvider, $compileProvider, $stateProvider, $filterProvider,
		$provide, $urlRouterProvider, $ocLazyLoadProvider, $httpProvider) {
	// 圈圈延迟出现控制
	console.log("App main config");
	cfpLoadingBarProvider.latencyThreshold = 1500;
	// 拦截请求

	app.register = {
		controller : $controllerProvider.register,
		directive : $compileProvider.directive,
		filter : $filterProvider.register,
		factory : $provide.factory,
		service : $provide.service
	};

	$urlRouterProvider.otherwise("/me/profile");
	$ocLazyLoadProvider.config({
				debug : true
			});
	$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
	// $httpProvider.defaults.withCredentials = false;
	var param = function(obj) {
		var query = '', name, value, fullSubName, subName, subValue, innerObj, i;
		for (name in obj) {
			value = obj[name];
			if (value instanceof Array) {
				for (i = 0; i < value.length; ++i) {
					subValue = value[i];
					fullSubName = name + '[' + i + ']';
					innerObj = {};
					innerObj[fullSubName] = subValue;
					query += param(innerObj) + '&';
				}
			} else if (value instanceof Object) {
				for (subName in value) {
					subValue = value[subName];
					fullSubName = name + '[' + subName + ']';
					innerObj = {};
					innerObj[fullSubName] = subValue;
					query += param(innerObj) + '&';
				}
			} else if (value !== undefined && value !== null)
				query += encodeURIComponent(name) + '='
						+ encodeURIComponent(value) + '&';
		}

		return query.length ? query.substr(0, query.length - 1) : query;
	};

	$httpProvider.defaults.transformRequest = [function(data) {
		return angular.isObject(data) && String(data) !== '[object File]'
				? param(data)
				: data;
	}];
	// // 登录
	// $stateProvider.state('login', {
	// url : "/login",
	// transclude : true,
	// templateUrl : "views/system/login/login.html",
	// params : {
	// to : null
	// },
	// resolve : {
	// loadPlugin : function($ocLazyLoad) {
	// return $ocLazyLoad.load([ {
	// serie : true,
	// files : [ 'views/system/login/login.js' ]
	// } ]);
	// }
	// }
	// });
	//
	// // 默认页面需要做检查
	// $stateProvider.state('content', {
	// url : "/show_content",
	// templateUrl : "views/common/content.html"
	// })
}

app.config(config_main)
		.run(
				function($rootScope, $state, $http, $log, $transitions,
						$templateCache) {
					console.log("App main run");
					// 替换了之前的$stateNotFound
					$state.onInvalid(function(to, from, injector) {
								$log.warn(to);
								$log.warn(from);
								$log.warn(injector);
								alert("未配置路由");
							});
					// 替换了之前的$stateChangeStart
					$transitions.onStart({
								to : '**'
							}, function(trans) {
								// 调试阶段去除缓存
								var $state = trans.router.stateService;
								var from_arr = trans._treeChanges.from;
								var from = null;
								if (from_arr.length > 0) {
									from = from_arr[from_arr.length - 1].state.name;
								}

								$log.warn("from:", from);

							});
					$rootScope.$state = $state;
					$rootScope.project = '/dt/';
					$rootScope.version = '20170901';

				});

app.config(config_shop).run(function() {
			console.log("App Shop run");
		});
