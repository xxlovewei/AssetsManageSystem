function config_system($stateProvider, $ocLazyLoadProvider) {
	$ocLazyLoadProvider.config({
		debug : false
	});
	console.log("App System config");
	// 个人设置
	$stateProvider.state('me', {
		abstract : true,
		url : "/me",
		templateUrl : "views/common/content.html?v="+version
	}).state('me.common_mgr', {
		url : "/common_mgr?psBtns",
		data: { pageTitle: '通用设置'},
		templateUrl : "views/me/common_mgr.html?v="+version,
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/me/common_mgr.js?v=' + version ]
				} ]);
			}
		}
	}).state('me.receivingaddr', {
		url : "/receivingaddr?psBtns",
		data: { pageTitle: '收货地址'},
		template:'<div ng-controller="meRecAddrCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/me/receaddr.js?v=' + version ]
				} ]);
			}
		}
	}).state('me.accesslog', {
		url : "/accesslog?psBtns",
		data: { pageTitle: '访问日志'},
		template:'<div ng-controller="meAccessLogCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/me/accesslog.js?v=' + version ]
				} ]);
			}
		}
	}).state('me.pwdreset', {
		url : "/pwdreset?psBtns",
		data: { pageTitle: '密码修改'},
		templateUrl : "views/me/pwdreset.html?v="+version,
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/me/pwdreset.js?v=' + version ]
				} ]);
			}
		}
	});

	// 用户管理
	$stateProvider.state('user', {
		abstract : true,
		url : "/user",
		templateUrl : "views/common/content.html?v="+version
	}).state('user.user_query', {
		url : "/user_query?psBtns",
		data: { pageTitle: '用户查询'},
		template:'<div ng-controller="sysUserQueryCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/user/user_query.js?v=' + version ]
				} ]);
			}
		}
	}).state('user.user_group', {
		url : "/user_group?psBtns",
		data: { pageTitle: '用户组设置'},
		template:'<div ng-controller="sysGroupSettingCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/user/group_setting.js?v=' + version ]
				} ]);
			}
		}
	}).state('user.user_add', {
		url : "/user_add?psBtns",
		data: { pageTitle: '用户新增'},
		templateUrl : "views/system/user/user_add.html?v="+version,
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/user/user_add.js?v=' + version ]
				} ]);
			}
		}
	}).state(
			'user.user_setting',
			{
				url : "/user_setting?psBtns",
				templateUrl : "views/system/user/user_setting.html?v="+version,
				data: { pageTitle: '用户设置'},
				resolve : {
					loadPlugin : function($ocLazyLoad) {
						return $ocLazyLoad.load([
								{
									serie: true,
									name : 'frapontillo.bootstrap-duallistbox',
									files : [ 'vendor/bootstrap-duallistbox/dist/bootstrap-duallistbox.min.css', 'vendor/bootstrap-duallistbox/dist/jquery.bootstrap-duallistbox.min.js','plugin/dualListbox/angular-bootstrap-duallistbox.js?v=' + version ]
								}, {
									serie : true,
									files : [ 'views/system/user/user_setting.js?v=' + version ]
								} ]);
					}
				}
			});

	// 模块管理
	$stateProvider.state('module', {
		abstract : true,
		url : "/module",
		templateUrl : "views/common/content.html?v="+version
	}).state('module.module_setting', {
		url : "/module_setting?psBtns",
		data: { pageTitle: '模块管理'},
		templateUrl : "views/system/menu/menu.html?v="+version,
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ 
                    {
                        name: 'treeGrid',
                        serie: true,
                        files: ['plugin/treegrid/treeGrid.css', 'plugin/treegrid/tree-grid-directive.js', 'views/system/menu/menu.js?v=' + version]
                    } ]);
			}
		}
	}).state('module.rootmenu', {
		url : "/rootmenu?psBtns",
		data: { pageTitle: '主菜单管理'},
		template:'<div ng-controller="sysRootMenugCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/menu/rootmenu.js?v=' + version ]
				} ]);
			}
		}
	});

	// 角色管理
	$stateProvider.state('role', {
		abstract : true,
		url : "/role",
		templateUrl : "views/common/content.html?v="+version
	}).state('role.role_setting', {
		url : "/role_setting?psBtns",
		data: { pageTitle: '角色设置'},
		template:'<div ng-controller="sysRoleSettingCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/role/role_setting.js?v=' + version ]
				} ]);
			}
		}
	}).state('role.role_module_map', {
		url : "/role_module_map?psBtns",
		data: { pageTitle: '角色模块映射'},
		templateUrl : "views/system/role/role_module_map.html?v="+version,
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/role/role_module_map.js?v=' + version ]
				} ]);
			}
		}
	});
 
	// 权限管理
	$stateProvider.state('privilige', {
		abstract : true,
		url : "/privilige",
		templateUrl : "views/common/content.html?v="+version
	}).state('privilige.role', {
		url : "/role",
		abstract : true,
		templateUrl : "views/common/c.html?v="+version
	}).state('privilige.role.role_module_map', {
		url : "/role_module_map",
		templateUrl : "views/system/role/role_module_map.html?v="+version,
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/role/role_module_map.js?v=' + version ]
				} ]);
			}
		}
	});

	// 系统设置
	$stateProvider.state('system', {
		abstract : true,
		url : "/system",
		templateUrl : "views/common/content.html?v="+version

	}).state('system.file_setting', {
		url : "/filesetting?psBtns",
		data: { pageTitle: '文件设置'},
		template:'<div ng-controller="sysfileConfCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/filesetting.js?v=' + version ]
				} ]);
			}
		}
	}).state('system.dict_setting', {
		url : "/dict_setting?psBtns",
		data: { pageTitle: '字典设置'},
		templateUrl : "views/system/dict/dict.html?v="+version,
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/dict/dict.js?v=' + version ]
				} ]);
			}
		}
	}).state('system.params', {
		url : "/sys_params?psBtns",
		data: { pageTitle: '参数设置'},
		template:'<div ng-controller="sysParamsCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/params/params.js?v=' + version ]
				} ]);
			}
		}
	}).state('system.storesql', {
		url : "/storesql?psBtns",
		data: { pageTitle: 'StoreSQL设置'},
		template:'<div ng-controller="sysStoreSqlCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/store/storesql.js?v=' + version ]
				} ]);
			}
		}
	}).state('system.onlinesession', {
		url : "/online?psBtns",
		data: { pageTitle: '在线用户'},
		template:'<div ng-controller="sysOnlineSessionCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/user/online.js?v=' + version ]
				} ]);
			}
		}
	}).state('system.cachemgr', {
		url : "/cachemgr?psBtns",
		data: { pageTitle: '缓存管理'},
		template:'<div ng-controller="sysCacheCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/cache/cache.js?v=' + version ]
				} ]);
			}
		}
	}).state('system.druid', {
		url : "/druid?psBtns",
		data: { pageTitle: 'Druid监控'},
		templateUrl : "views/system/mon/druid.html?v="+version,
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/mon/druid.js?v=' + version ]
				} ]);
			}
		}
	});

	// 组织架构
	$stateProvider.state('org', {
		abstract : true,
		url : "/org",
		templateUrl : "views/common/content.html?v="+version
	}).state('org.employee', {
		url : "/org_employee?psBtns",
		data: { pageTitle: '人员查询' },
		templateUrl : "views/org/employee.html?v="+version,
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/org/employee.js?v=' + version ]
				} ]);
			}
		}
	}).state('org.employee_adjust', {
		url : "/org_employee_adjust?psBtns",
		data: { pageTitle: '人员调整' },
		templateUrl : "views/org/employee_adjust.html?v="+version,
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/org/employee_adjust.js?v=' + version ]
				} ]);
			}
		}
	}).state('org.part', {
		url : "/org_part?psBtns",
		data: { pageTitle: '组织设置' },
		templateUrl : "views/org/part.html?v="+version,
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/org/part.js?v=' + version ]
				} ]);
			}
		}
	});

	// 任务设置
	$stateProvider.state('task', {
		abstract : true,
		url : "/task",
		templateUrl : "views/common/content.html?v="+version
	}).state('task.task_mgr', {
		url : "/task_mgr?psBtns",
		data: { pageTitle: '任务管理'},
		template:'<div ng-controller="sysTaskCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/task/task.js?v=' + version ]
				} ]);
			}
		}
	});
	// flow
	$stateProvider.state('flow', {
		abstract : true,
		url : "/flow",
		templateUrl : "views/common/content.html?v="+version
	}).state('flow.designer', {
		url : "/flow_designer?psBtns",
		data: { pageTitle: '流程设计'},
		templateUrl : "views/system/flow/designer.html?v="+version,
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/flow/designer.js?v=' + version ]
				} ]);
			}
		}
	});

}
 