function config_system($stateProvider, $ocLazyLoadProvider) {
	$ocLazyLoadProvider.config({
		debug : true
	});
	console.log("App System config");
	// 基础数据
	$stateProvider.state('basedata', {
		abstract : true,
		url : "/basedata",
		templateUrl : "views/common/content.html"
	}).state('basedata.area', {
		url : "/area",
		data: { pageTitle: '省份数据'},
		templateUrl : "views/system/base/area.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/base/area.js?v=' + version ]
				} ]);
			}
		}
	}).state('basedata.weather', {
		url : "/weather",
		data: { pageTitle: '天气数据',loginCheck:true},
		// templateUrl : "views/system/base/weather.html",
		template:'<div ng-controller="prodCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/base/weather.js?v=' + version ]
				} ]);
			}
		}
	});

	// 个人设置
	$stateProvider.state('me', {
		abstract : true,
		url : "/me",
		templateUrl : "views/common/content.html"
	}).state('me.common_mgr', {
		url : "/common_mgr",
		data: { pageTitle: '通用设置'},
		templateUrl : "views/me/common_mgr.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/me/common_mgr.js?v=' + version ]
				} ]);
			}
		}
	}).state('me.receivingaddr', {
		url : "/receivingaddr",
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
		url : "/accesslog",
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
		url : "/pwdreset",
		data: { pageTitle: '密码修改'},
		templateUrl : "views/me/pwdreset.html",
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
		templateUrl : "views/common/content.html"
	}).state('user.user_query', {
		url : "/user_query",
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
		url : "/user_group",
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
		url : "/user_add",
		data: { pageTitle: '用户新增'},
		templateUrl : "views/system/user/user_add.html",
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
				url : "/user_setting",
				templateUrl : "views/system/user/user_setting.html",
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
		templateUrl : "views/common/content.html"
	}).state('module.module_setting', {
		url : "/module_setting",
		data: { pageTitle: '模块管理'},
		templateUrl : "views/system/menu/menu.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ 
                    {
                        name: 'treeGrid',
                        serie: true,
                        files: ['plugin/treegrid/treeGrid.css', 'plugin/treegrid/tree-grid-directive.js']
                    },
					{
					serie : true,
					files : [ 'views/system/menu/menu.js?v=' + version ]
				} ]);
			}
		}
	}).state('module.rootmenu', {
		url : "/rootmenu",
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
		templateUrl : "views/common/content.html"
	}).state('role.role_setting', {
		url : "/role_setting",
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
		url : "/role_module_map",
		data: { pageTitle: '角色模块映射'},
		templateUrl : "views/system/role/role_module_map.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/role/role_module_map.js?v=' + version ]
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
		data: { pageTitle: '类目设置'},
		templateUrl : "views/content/ctCategory.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/content/ctCategory.js?v=' + version ]
				} ]);
			}
		}
	}).state('ct.publishnews', {
		url : "/publishnews",
		data: { pageTitle: '发布信息'},
		templateUrl : "views/content/newsPublish.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/content/newsPublish.js?v=' + version ]
				} ]);
			}
		}
	}).state('ct.news_mgr', {
		url : "/newMgr",
		data: { pageTitle: '新闻管理'},
		template:'<div ng-controller="ctNewsMgrCtl" >'+buildSimpleToolTableTpl()+'</div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([  {
					serie : true,
					files : [ 'views/content/newsMgr.js?v=' + version ]
				} ]);
			}
		}
	}).state('ct.company_profile', {
		url : "/company_profile",
		data: { pageTitle: '公司简介'},
		templateUrl : "views/content/company.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/content/company.js?v=' + version ]
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
					files : [ 'views/system/role/role_module_map.js?v=' + version ]
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
		url : "/dict_setting",
		data: { pageTitle: '字典设置'},
		templateUrl : "views/system/dict/dict.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/dict/dict.js?v=' + version ]
				} ]);
			}
		}
	}).state('system.params', {
		url : "/sys_params",
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
		url : "/storesql",
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
		url : "/online",
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
		url : "/cachemgr",
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
		url : "/druid",
		data: { pageTitle: 'Druid监控'},
		templateUrl : "views/system/mon/druid.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/mon/druid.js?v=' + version ]
				} ]);
			}
		}
	}).state('system.melody', {
		url : "/melody",
		data: { pageTitle: 'Melody监控'},
		templateUrl : "views/system/mon/melody.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/mon/melody.js?v=' + version ]
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
		data: { pageTitle: '人员查询', specialClass: 'fixed-nav' },
		templateUrl : "views/org/employee.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/org/employee.js?v=' + version ]
				} ]);
			}
		}
	}).state('org.employee_adjust', {
		url : "/org_employee_adjust",
		data: { pageTitle: '人员调整', specialClass: 'fixed-nav' },
		templateUrl : "views/org/employee_adjust.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/org/employee_adjust.js?v=' + version ]
				} ]);
			}
		}
	}).state('org.part', {
		url : "/org_part",
		data: { pageTitle: '组织设置' },
		templateUrl : "views/org/part.html",
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
		templateUrl : "views/common/content.html"
	}).state('task.task_mgr', {
		url : "/task_mgr",
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
	
	
	// cmdb
	$stateProvider.state('res', {
		abstract : true,
		url : "/res",
		templateUrl : "views/common/content.html"
	}).state('res.restype', {
		url : "/res_restype",
		data: { pageTitle: '设备类型'},
		template:'<div ng-controller="resTypeCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/res/restype.js?v=' + version ]
				} ]);
			}
		}
	}).state('res.res_attr', {
		url : "/res_attr",
		data: { pageTitle: '资源属性'},
		template:'<div ng-controller="resAttrCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/res/resattr.js?v=' + version ]
				} ]);
			}
		}
	});
	
	// cmdb
	$stateProvider.state('cf', {
		abstract : true,
		url : "/cf",
		templateUrl : "views/common/content.html"
	}).state('cf.sb', {
		url : "/cf_sb",
		data: { pageTitle: '设备管理'},
		template:'<div ng-controller="cmdbHardCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/cmdb/hard.js?v=' + version ]
				} ]);
			}
		}
	}).state('cf.userlist', {
		url : "/cf_userList",
		data: { pageTitle: '用户列表'},
		templateUrl : "views/system/cmdb/userList.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/cmdb/userList.js?v=' + version ]
				} ]);
			}
		}
	}).state('cf.outercontact', {
		url : "/cf_outercontact",
		data: { pageTitle: '外部联系人'},
		templateUrl : "views/system/cmdb/outercontact.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/cmdb/outercontact.js?v=' + version ]
				} ]);
			}
		}
	});
	
	
	
}
