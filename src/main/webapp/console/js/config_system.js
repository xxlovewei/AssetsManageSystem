function config_system($stateProvider, $ocLazyLoadProvider) {
	$ocLazyLoadProvider.config({
		debug : false
	});
	console.log("App System config");
	// 基础数据
	$stateProvider.state('basedata', {
		abstract : true,
		url : "/basedata",
		templateUrl : "views/common/content.html"
	}).state('basedata.area', {
		url : "/area",
		templateUrl : "views/system/base/area.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/base/area.js' ]
				} ]);
			}
		}
	}).state('basedata.weather', {
		url : "/weather",
		templateUrl : "views/system/base/weather.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/base/weather.js' ]
				} ]);
			}
		}
	});
	// 用户管理
	$stateProvider.state('user', {
		abstract : true,
		url : "/user",
		templateUrl : "views/common/content.html"
	}).state('user.user_query', {
		url : "/user_query",
		templateUrl : "views/system/user/user_query.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/user/user_query.js' ]
				} ]);
			}
		}
	}).state('user.user_group', {
		url : "/user_group",
		templateUrl : "views/system/user/group_setting.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/user/group_setting.js' ]
				} ]);
			}
		}
	}).state('user.user_add', {
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
	}).state(
			'user.user_setting',
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
			});

	// 模块管理
	$stateProvider.state('module', {
		abstract : true,
		url : "/module",
		templateUrl : "views/common/content.html"
	}).state('module.module_setting', {
		url : "/module_setting",
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

	// 角色管理
	$stateProvider.state('role', {
		abstract : true,
		url : "/role",
		templateUrl : "views/common/content.html"
	}).state('role.role_setting', {
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
	}).state('role.role_module_map', {
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
	});

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
		abstract : true,
		url : "/privilige",
		templateUrl : "views/common/content.html"
	}).state('privilige.role', {
		url : "/role",
		abstract : true,
		templateUrl : "views/common/c.html"
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
	}).state('system.dict_setting', {
		url : "/dict_setting",
		templateUrl : "views/system/dict/dict.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/dict/dict.js' ]
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
	//运维监控
	$stateProvider.state('devos', {
		abstract : true,
		url : "/devos",
		templateUrl : "views/common/content.html"
	}).state('devos.druid', {
		url : "/druid",
		templateUrl : "views/system/mon/druid.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/mon/druid.js' ]
				} ]);
			}
		}
	}).state('devos.melody', {
		url : "/melody",
		templateUrl : "views/system/mon/melody.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/mon/melody.js' ]
				} ]);
			}
		}
	});
	//任务设置
	$stateProvider.state('task', {
		abstract : true,
		url : "/task",
		templateUrl : "views/common/content.html"
	}).state('task.task_mgr', {
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
	});
}

