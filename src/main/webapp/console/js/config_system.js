function config_system($ocLazyLoadProvider, $stateProvider,$ocLazyLoad) {
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
}

app.config(config_system).run(function() {
});
