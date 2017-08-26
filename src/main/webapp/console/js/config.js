var app = angular.module('inspinia', [ 'ui.router', 'oc.lazyLoad', 'ui.bootstrap', 'pascalprecht.translate', 'ngIdle', 'ngJsTree', 'ngSanitize', 'localytics.directives',
		'treeGrid', 'cgNotify', 'angular-confirm', 'datatables', 'datatables.select', 'datatables.buttons', 'swxLocalStorage', 'angular-loading-bar', 'ng.ueditor' ])

var $injector = angular.injector();
app.factory('sessionInjector', [
		'$log',
		'$injector',
		function($log, $injector) {
			var sessionInjector = {};
			sessionInjector.request = function(config) {
				// 每个请求添加token
				// X-Requested-With,将所有请求标记为ajax请求
				config.headers['X-Requested-With'] = "XMLHttpRequest"
				var userService = $injector.get('userService');
				var tokenstr = userService.getToken();
				if (angular.isDefined(tokenstr) && tokenstr.length > 5) {
					config.headers['dt-token'] = tokenstr;
				}
				return config;
			}

			sessionInjector.response = function(responseObject) {
				// 输出调试信息
				if (angular.isDefined(responseObject.config) && angular.isDefined(responseObject.config.data) && angular.isDefined(responseObject.config.url)
						&& angular.isDefined(responseObject.data)) {
					$log.warn("$http|res url:", responseObject.config.url);
					$log.warn("$http|res post:", responseObject.config.data);
					$log.warn("$http|res data:", responseObject.data);
				}

				// 授权验证
				if (responseObject.status == "299") {
					var state = $injector.get('$state');
					state.go("login");
				} else if (responseObject.status == "298") {
					// 未授权
				}
				return responseObject;
			}
			sessionInjector.requestError = function(rejectReason) {
				var deferred = $q.defer();
				$log.warn("myInterceptor.requestError : " + responseObject);
				return deferred.promise;
			};
			sessionInjector.responseError = function(responseError) {
				$log.warn("sessionInjector,responseError", responseError);
				var notify = $injector.get('notify');
				notify({
					message : "服务器故障,ErrorCode:" + responseError.status
				});
				return responseError;
			};
			return sessionInjector;
		} ]);

function config(cfpLoadingBarProvider, $locationProvider, $controllerProvider, $compileProvider, $stateProvider, $filterProvider, $provide, $urlRouterProvider,
		$ocLazyLoadProvider, IdleProvider, KeepaliveProvider, $httpProvider) {
	// 圈圈延迟出现控制
	cfpLoadingBarProvider.latencyThreshold = 1000;
	// 拦截请求
	$httpProvider.interceptors.push('sessionInjector');

	app.register = {
		controller : $controllerProvider.register,
		directive : $compileProvider.directive,
		filter : $filterProvider.register,
		factory : $provide.factory,
		service : $provide.service
	};

	// 间隔interval秒,超时idle秒后，timeout秒未活动则退出
	IdleProvider.idle(5); // in seconds
	IdleProvider.timeout(5); // in seconds
	KeepaliveProvider.interval(2);
	$urlRouterProvider.otherwise("/login");

	$ocLazyLoadProvider.config({
		debug : false
	});

	// 登录
	$stateProvider.state('login', {
		url : "/login",
		transclude : true,
		templateUrl : "views/system/login/login.html",
		params : {
			to : null
		},
		resolve : {
			check : function(userService, $log, $state) {
				userService.checkLogin().then(function(result) {
					if (result.success) {
						// 已经登录
						$log.warn("Cofnig账户已经登录,马上跳转至content");
						$state.go("content");
					} else {

					}
				})
			},
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/login/login.js' ]
				} ]);
			}
		}
	})

	// 例子
	$stateProvider.state('demo', {
		abstract : true,
		url : "/demo",
		templateUrl : "views/common/content.html"
	}).state('demo.ueditor', {
		url : "/ueditor",
		templateUrl : "views/demo/ueditor.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/demo/ueditor.js' ]
				} ]);
			}
		}
	});

	// 默认页面需要做检查
	$stateProvider.state('content', {
		url : "/show_content",
		templateUrl : "views/common/content.html"
	})

	// 内容管理
	$stateProvider.state('ct', {
		abstract : true,
		templateUrl : "views/common/content.html",
	}).state('ct.catesetting', {
		url : "/catesetting",
		templateUrl : "views/content/ctCategory.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/content/ctCategory.js' ]
				} ]);
			}
		}
	}).state('ct.publishnews', {
		url : "/publishnews",
		templateUrl : "views/content/newsPublish.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/content/newsPublish.js' ]
				} ]);
			}
		}
	}).state('ct.news_mgr', {
		url : "/newMgr",
		templateUrl : "views/content/newsMgr.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/content/newsMgr.js' ]
				} ]);
			}
		}
	}).state('ct.company_profile', {
		url : "/company_profile",
		templateUrl : "views/content/company.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/content/company.js' ]
				} ]);
			}
		}
	});

	// 权限管理
	$stateProvider.state('privilige', {
		url : "/privilige",
		templateUrl : "views/common/content.html"
	}).state('privilige.role', {
		url : "/role",
		abstract : true,
		templateUrl : "views/common/c.html"
	}).state('privilige.role.role_setting', {
		url : "/role_setting",
		templateUrl : "views/system/role/role_setting.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/role/role_setting.js' ]
				} ]);
			}
		}
	}).state('privilige.role.role_module_map', {
		url : "/role_module_map",
		templateUrl : "views/system/role/role_module_map.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/role/role_module_map.js' ]
				} ]);
			}
		}
	}).state('privilige.user', {
		url : "/user",
		abstract : true,
		templateUrl : "views/common/c.html"
	}).state('privilige.user.user_qurey', {
		url : "/user_qurey",
		templateUrl : "views/system/user/user_query.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/user/user_query.js' ]
				} ]);
			}
		}
	}).state(
			'privilige.user.user_setting',
			{
				url : "/user_setting",
				templateUrl : "views/system/user/user_setting.html",
				resolve : {
					loadPlugin : function($ocLazyLoad) {
						return $ocLazyLoad.load([
								{
									name : 'frapontillo.bootstrap-duallistbox',
									files : [ 'css/plugins/dualListbox/bootstrap-duallistbox.min.css', 'js/plugins/dualListbox/jquery.bootstrap-duallistbox.js',
											'js/plugins/dualListbox/angular-bootstrap-duallistbox.js' ]
								}, {
									serie : true,
									files : [ 'views/system/user/user_setting.js' ]
								} ]);
					}
				}
			}).state('privilige.user.user_add', {
		url : "/user_add",
		templateUrl : "views/system/user/user_add.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/user/user_add.js' ]
				} ]);
			}
		}
	}).state('privilige.user.usergroup_mgr', {
		url : "/usergroup_mgr",
		templateUrl : "views/system/user/group_setting.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/user/group_setting.js' ]
				} ]);
			}
		}
	}).state('privilige.module', {
		url : "/module",
		abstract : true,
		templateUrl : "views/common/c.html"
	}).state('privilige.module.menu_mgr', {
		url : "/menu_mgr",
		templateUrl : "views/system/menu/menu.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/menu/menu.js' ]
				} ]);
			}
		}
	});

	// 系统设置
	$stateProvider.state('system', {
		abstract : true,
		url : "/system",
		templateUrl : "views/common/content.html"

	}).state('system.file_setting', {
		url : "/filesetting",
		templateUrl : "views/system/filesetting.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/filesetting.js' ]
				} ]);
			}
		}
	}).state('system.task_mgr', {
		url : "/task_mgr",
		templateUrl : "views/system/task/task.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/task/task.js' ]
				} ]);
			}
		}
	}).state('system.mon_druid', {
		url : "/mon_druid",
		templateUrl : "views/system/mon/druid.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/mon/druid.js' ]
				} ]);
			}
		}
	}).state('system.dict', {
		url : "/dict",
		abstract : true,
		templateUrl : "views/common/c.html"
	}).state('system.dict.dict_setting', {
		url : "/setting",
		templateUrl : "views/system/dict/dict.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/dict/dict.js' ]
				} ]);
			}
		}
	}).state('system.base', {
		url : "/base",
		abstract : true,
		templateUrl : "views/common/c.html"
	}).state('system.base.weather', {
		url : "/base_weather",
		templateUrl : "views/system/base/weather.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/base/weather.js' ]
				} ]);
			}
		}
	}).state('system.base.area', {
		url : "/base_area",
		templateUrl : "views/system/base/area.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/base/area.js' ]
				} ]);
			}
		}
	}).state('system.params', {
		url : "/sys_params",
		templateUrl : "views/system/params/params.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/params/params.js' ]
				} ]);
			}
		}
	});

	// 组织架构
	$stateProvider.state('org', {
		abstract : true,
		url : "/org",
		templateUrl : "views/common/content.html"
	}).state('org.employee', {
		url : "/org_employee",
		templateUrl : "views/org/employee.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/org/employee.js' ]
				} ]);
			}
		}
	}).state('org.employee_adjust', {
		url : "/org_employee_adjust",
		templateUrl : "views/org/employee_adjust.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/org/employee_adjust.js' ]
				} ]);
			}
		}
	}).state('org.part', {
		url : "/org_part",
		templateUrl : "views/org/part.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/org/part.js' ]
				} ]);
			}
		}
	});

	// 产品
	$stateProvider.state('product', {
		abstract : true,
		url : "/product",
		templateUrl : "views/common/content.html"

	}).state('product.prod_pinp', {
		url : "/pinp",
		templateUrl : "views/product/pinp/pinp.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/product/pinp/pinp.js' ]
				} ]);
			}
		}
	}).state('product.prod_query', {
		url : "/prod_query",
		templateUrl : "views/product/prod_query.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/product/prod_query.js' ]
				} ]);
			}
		}
	}).state('product.prod_publish', {
		url : "/publish",
		templateUrl : "views/product/prod_publish.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/product/prod_publish.js' ]
				} ]);
			}
		}
	}).state('product.catback', {
		url : "/catback",
		abstract : true,
		templateUrl : "views/common/c.html"
	}).state('product.catback.setting', {
		url : "/setting",
		templateUrl : "views/product/bcat/bcat.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/product/bcat/bcat.js' ]
				} ]);
			}
		}
	}).state('product.catfore', {
		url : "/catfore",
		abstract : true,
		templateUrl : "views/common/c.html"
	}).state('product.catfore.setting', {
		url : "/setting",
		templateUrl : "views/product/fcat/fcat.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/product/fcat/fcat.js' ]
				} ]);
			}
		}
	}).state('product.catfore.rootsetting', {
		url : "/rootsetting",
		templateUrl : "views/product/fcat/rootfcat.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/product/fcat/rootfcat.js' ]
				} ]);
			}
		}
	});

	$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
	// cookies
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
				query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';
		}

		return query.length ? query.substr(0, query.length - 1) : query;
	};

	$httpProvider.defaults.transformRequest = [ function(data) {
		return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
	} ];

}
// app.run($trace => $trace.enable());
app.config(config).run(function(Idle, $rootScope, $state, $http, $log, $transitions) {
	// start watching when the app runs. also starts the Keepalive service by
	// default.
	Idle.watch();
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

		var $state = trans.router.stateService;
		var userService = trans.injector().get('userService');
		var from_arr = trans._treeChanges.from;
		var from = null;
		if (from_arr.length > 0) {
			from = from_arr[from_arr.length - 1].state.name;
		}

		$log.warn("from:", from);
		// 不需要检查是否登录
		userService.checkLogin().then(function(result) {
			$log.warn("check login result,from:" + from + ",result:", result)
			if (!result.success) {
				if (from != "login") {
					$state.go("login", {
						to : from
					});
				} else {

				}
			}
		}, function(error) {
			alert('系统错误');
			event.preventDefault();
		}, function(progress) {
		})
	});
	$rootScope.$state = $state;
	$rootScope.project = '/dt/';

	$rootScope.$on('IdleStart', function() {
		$log.warn('IdleStart');
		// the user appears to have gone idle
	});

	$rootScope.$on('IdleWarn', function(e, countdown) {
		$log.warn('IdleWarne', e);
		$log.warn('IdleWarncountdown', countdown);
		if (countdown == 1) {
			// 重新激活
			Idle.watch();
		}
		// follows after the IdleStart event, but includes a countdown until the
		// user is considered timed out
		// the countdown arg is the number of seconds remaining until then.
		// you can change the title or display a warning dialog from here.
		// you can let them resume their session by calling Idle.watch()
	});

	$rootScope.$on('IdleTimeout', function() {
		$log.warn('IdleTimeout');
		// the user has timed out (meaning idleDuration + timeout has passed
		// without any activity)
		// this is where you'd log them
	});

	$rootScope.$on('IdleEnd', function() {
		$log.warn('IdleEnd');
		// the user has come back from AFK and is doing stuff. if you are
		// warning them, you can use this to hide the dialog
	});

	$rootScope.$on('Keepalive', function() {
		$log.warn('IdlKeepaliveeEnd');
		// do something to keep the user's session alive
	});

});
//

// datatable中文配置
app.factory('DTLang', function() {
	return {
		processing : "处理中...",
		lengthMenu : "每页显示 _MENU_ 项结果",
		zeroRecords : "没有匹配结果",
		info : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项；当前第 _PAGE_页，共 _PAGES_ 页",
		infoEmpty : "显示第 0 至 0 项结果，共 0 项",
		infoFiltered : "(由 _MAX_ 项结果过滤)",
		infoPostFix : "",
		search : "搜索:",
		url : "",
		emptyTable : "表中数据为空",
		sLoadingRecords : "载入中...",
		infoThousands : ",",
		paginate : {
			first : "首页",
			previous : "上页",
			next : "下页",
			last : "末页"
		},
		oAria : {
			sortAscending : ": 以升序排列此列",
			sortDescending : ": 以降序排列此列"
		}

	}
});
