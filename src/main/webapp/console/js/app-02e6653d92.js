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
	
	
	
}

function config_wx($stateProvider, $ocLazyLoadProvider) {
	console.log("App Wx config");
	$ocLazyLoadProvider.config({
				debug : true
			});

	// 菜单
	$stateProvider.state('wxmgr', {
				abstract : true,
				url : "/wxmgr",
				data : {
					pageTitle : '微信'
				},
				templateUrl : "views/common/content.html"
			}).state('wxmgr.app', {
				url : "/wxmgr_app",
				data : {
					pageTitle : '应用设置'
				},
				template:'<div ng-controller="wxappCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
				resolve : {
					loadPlugin : function($ocLazyLoad) {
						return $ocLazyLoad.load([{
									serie : true,
									files : ['views/wx/app.js?v=' + version]
								}]);
					}
				}
			}).state('wxmgr.weboauth', {
				url : "/wxmgr_weboauth",
				data : {
					pageTitle : '网页授权'
				},
				template:'<div ng-controller="weboauthCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
				resolve : {
					loadPlugin : function($ocLazyLoad) {
						return $ocLazyLoad.load([{
									serie : true,
									files : ['views/wx/weboauth.js?v=' + version]
								}]);
					}
				}
			});
	// 菜单
	$stateProvider.state('wxmsg', {
				abstract : true,
				url : "/wxmgr",
				data : {
					pageTitle : '消息'
				},
				templateUrl : "views/common/content.html"
			}).state('wxmsg.imgtext', {
				url : "/imgtext",
				data : {
					pageTitle : '图文消息'
				},
				template:'<div ng-controller="wximgtextCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
				resolve : {
					loadPlugin : function($ocLazyLoad) {
						return $ocLazyLoad.load([{
									serie : true,
									files : ['views/wx/imgtext.js?v=' + version]
								}]);
					}
				}
			}).state('wxmsg.setting', {
				url : "/setting",
				data : {
					pageTitle : '消息设置'
				},
				template:'<div ng-controller="wxmsgsettingCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
				resolve : {
					loadPlugin : function($ocLazyLoad) {
						return $ocLazyLoad.load([{
									serie : true,
									files : ['views/wx/setting.js?v=' + version]
								}]);
					}
				}
			}).state('wxmsg.msgimg', {
				url : "/msgimg",
				data : {
					pageTitle : '图片素材'
				},
				template:'<div ng-controller="wxmsgimgCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
				resolve : {
					loadPlugin : function($ocLazyLoad) {
						return $ocLazyLoad.load([{
									serie : true,
									files : ['views/wx/msgimg.js?v=' + version]
								}]);
					}
				}
			});
}

function config_mshop($stateProvider, $ocLazyLoadProvider) {
	console.log("App MShop config");
	$ocLazyLoadProvider.config({
		debug : true
	});
	
	// 商品
	$stateProvider.state('prod', {
		abstract : true,
		url : "/prod",
		templateUrl : "views/common/content.html"
	}).state('prod.dl', {
		url : "/prod_dl",
		data: { pageTitle: '商品大类'},
		templateUrl : "views/mshop/prod/dl.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/mshop/prod/dl.js?v=' + version ]
				} ]);
			}
		}
	}).state('prod.xl', {
		url : "/prod_xl",
		data: { pageTitle: '商品小类'},
		templateUrl : "views/mshop/prod/xl.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/mshop/prod/xl.js?v=' + version ]
				} ]);
			}
		}
	}).state('prod.prodquery', {
		url : "/prod_prodquery",
		data: { pageTitle: '商品查询'},
		templateUrl : "views/mshop/prod/prodquery.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/mshop/prod/prodquery.js?v=' + version ]
				} ]);
			}
		}
	}).state('prod.proddtl', {
		url : "/prod_dtl",
		data: { pageTitle: '商品详情'},
		templateUrl : "views/mshop/prod/proddtl.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/mshop/prod/proddtl.js?v=' + version ]
				} ]);
			}
		}
	});

 
	$stateProvider.state('muser', {
		abstract : true,
		url : "/muser",
		templateUrl : "views/common/content.html"
	}).state('muser.query', {
		url : "/muser_query",
		data: { pageTitle: '用户'},
		templateUrl : "views/mshop/user/user_setting.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/mshop/user/user_setting.js?v=' + version ]
				} ]);
			}
		}
	});	
}

function config_shop($stateProvider, $ocLazyLoadProvider) {
	console.log("App Shop config");
	$ocLazyLoadProvider.config({
		debug : true
	});
	
	// 产品
	$stateProvider.state('product', {
		abstract : true,
		url : "/product",
		templateUrl : "views/common/content.html"
	}).state('product.prod_publish', {
		url : "/prod_publish",
		data: { pageTitle: '产品发布'},
		templateUrl : "views/product/prod_publish.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/product/prod_publish.js?v=' + version ]
				} ]);
			}
		}
	}).state('product.prod_query', {
		url : "/prod_query",
		data: { pageTitle: '产品查询'},
		templateUrl : "views/product/prod_query.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/product/prod_query.js?v=' + version ]
				} ]);
			}
		}
	});

	// 店铺
	$stateProvider.state('shop', {
		abstract : true,
		url : "/shop",
		data: { pageTitle: '店铺列表'},
		templateUrl : "views/common/content.html"
	}).state('shop.shop_list', {
		url : "/shop_list",
		template:'<div ng-controller="shopListCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/shop/shop.js?v=' + version ]
				} ]);
			}
		}
	}).state('shop.shop_info', {
		url : "/shop_info",
		data: { pageTitle: '我的店铺'},
		template:'<div ng-controller="shopListCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/shop/myshop.js?v=' + version ]
				} ]);
			}
		}
	});

	// 品牌
	$stateProvider.state('pinp', {
		abstract : true,
		url : "/pinp",
		data: { pageTitle: '品牌设置'},
		templateUrl : "views/common/content.html"
	}).state('pinp.pinp_mgr', {
		url : "/pinp_mgr",
		template:'<div ng-controller="prodPinpCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/product/pinp/pinp.js?v=' + version ]
				} ]);
			}
		}
	});
	

	// 商城
	$stateProvider.state('mallmgr', {
		abstract : true,
		url : "/mallmgr",
		templateUrl : "views/common/content.html"
	}).state('mallmgr.banner', {
		url : "/mgr",
		data: { pageTitle: 'Banner设置'},
		template:'<div ng-controller="mallbannerCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/mall/banner.js?v=' + version ]
				} ]);
			}
		}
	}).state('mallmgr.notice', {
		url : "/notice",
		data: { pageTitle: '公告设置'},
		template:'<div ng-controller="mallNoticeCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/mall/notice.js?v=' + version ]
				} ]);
			}
		}
	});
	
	

	// 类目
	$stateProvider.state('cat', {
		abstract : true,
		url : "/cat",
		templateUrl : "views/common/content.html"
	}).state('cat.fcat_mgr', {
		url : "/fcat_mgr",
		data: { pageTitle: '前台类目'},
		templateUrl : "views/product/fcat/fcat.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/product/fcat/fcat.js?v=' + version ]
				} ]);
			}
		}
	}).state('cat.bcat_mgr', {
		url : "/bcat_mgr",
		data: { pageTitle: '后台类目'},
		templateUrl : "views/product/bcat/bcat.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/product/bcat/bcat.js?v=' + version ]
				} ]);
			}
		}
	}).state('cat.fcat_group', {
		data: { pageTitle: '前台类目组'},
		url : "/fcat_group",
		template:'<div ng-controller="prodrootfCatCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/product/fcat/rootfcat.js?v=' + version ]
				} ]);
			}
		}
	}).state('cat.indexclass', {
		url : "/indexclass",
		data: { pageTitle: '首页分类'},
		templateUrl : "views/mall/indexclass.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/mall/indexclass.js?v=' + version ]
				} ]);
			}
		}
	});
	
	
}

function config_om($stateProvider, $ocLazyLoadProvider) {
	console.log("App Om config");
	$ocLazyLoadProvider.config({
				debug : true
			});

	// 主机节点
	$stateProvider.state('hostnode', {
				abstract : true,
				url : "/hostnode",
				templateUrl : "views/common/content.html"
			}).state('hostnode.hostmgr', {
		url : "/hostnode_hostmgr",
		data : {
			pageTitle : '节点管理'
		},
		template:'<div ng-controller="nodeHostMgrCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([{
					serie : true,
					files : ['plugin/term/term.js?v+'
							+ version]
				},{
							serie : true,
							files : ['views/om/hostnode/hostmgr.js?v='
									+ version]
						}]);
			}
		}
	}).state('hostnode.templ', {
				url : "/hostnode_hostmgr",
				data : {
					pageTitle : '节点模版'
				},
				templateUrl : "views/om/hostnode/templ.html",
				resolve : {
					loadPlugin : function($ocLazyLoad) {
						return $ocLazyLoad.load([{
									serie : true,
									files : ['views/om/hostnode/templ.js?v='
											+ version]
								}]);
					}
				}
			});

	// servicemgr.servicenode
	$stateProvider.state('servicemgr', {
				abstract : true,
				url : "/servicemgr",
				templateUrl : "views/common/content.html"
			}).state('servicemgr.servicenode', {
				url : "/servicemgr_servicenode",
				data : {
					pageTitle : '服务节点'
				},
				template:'<div ng-controller="mnservicenodeCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
				resolve : {
					loadPlugin : function($ocLazyLoad) {
						return $ocLazyLoad.load([{
									serie : true,
									files : ['views/om/ser/node.js?v='
											+ version]
								}]);
					}
				}
			}).state('servicemgr.servicesetting', {
				url : "/servicemgr_servicesetting",
				data : {
					pageTitle : '服务设置'
				},
				templateUrl : "views/om/ser/ser.html",
				resolve : {
					loadPlugin : function($ocLazyLoad) {
						return $ocLazyLoad.load([{
									serie : true,
									files : ['views/om/ser/ser.js?v=' + version]
								}]);
					}
				}
			}).state('servicemgr.nodemetric', {
				url : "/servicemgr_nodemetric",
				data : {
					pageTitle : '节点度量'
				},
				templateUrl : "views/om/ser/nodemetric.html",
				resolve : {
					loadPlugin : function($ocLazyLoad) {
						return $ocLazyLoad.load([{
									serie : true,
									files : ['views/om/ser/nodemetric.js?v='
											+ version]
								}]);
					}
				}
			});

	// metricmgr
	$stateProvider.state('metricmgr', {
				abstract : true,
				url : "/metricmgr",
				templateUrl : "views/common/content.html"
			}).state('metricmgr.metricsetting', {
				url : "/metricmgr_metricsetting",
				data : {
					pageTitle : '度量设置'
				},
				template:'<div ng-controller="metricCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
				resolve : {
					loadPlugin : function($ocLazyLoad) {
						return $ocLazyLoad.load([{
									serie : true,
									files : ['views/om/metric/metric.js?v='
											+ version]
								}]);
					}
				}
			}).state('metricmgr.metrictempl', {
				url : "/metricmgr_metrictempl",
				data : {
					pageTitle : '度量模版'
				},
				template:'<div ng-controller="metricTemplCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
				resolve : {
					loadPlugin : function($ocLazyLoad) {
						return $ocLazyLoad.load([{
									serie : true,
									files : ['views/om/metric/templ.js?v='
											+ version]
								}]);
					}
				}
			}).state('metricmgr.metricmapping', {
				url : "/metricmgr_metricmapping",
				data : {
					pageTitle : '度量映射'
				},
				template:'<div ng-controller="metricmappingCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
				resolve : {
					loadPlugin : function($ocLazyLoad) {
						return $ocLazyLoad.load([{
									serie : true,
									files : ['views/om/metric/mapping.js?v='
											+ version]
								}]);
					}
				}
			});

	// metricmgr
	$stateProvider.state('touchmgr', {
				abstract : true,
				url : "/touchmgr",
				templateUrl : "views/common/content.html"
			}).state('touchmgr.web', {
				url : "/touchmgr_web",
				data : {
					pageTitle : '检测管理'
				},
				template:'<div ng-controller="touchWebCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
				resolve : {
					loadPlugin : function($ocLazyLoad) {
						return $ocLazyLoad.load([{
									serie : true,
									files : ['views/om/touch/web.js?v='
											+ version]
								}]);
					}
				}
			});

}

var app = angular.module('inspinia', [ 'ui.router', 'oc.lazyLoad',
		'ui.bootstrap', 'pascalprecht.translate', 'ngIdle', 'ngJsTree',
		'ngSanitize', 'cgNotify', 'angular-confirm',
		'datatables', 'datatables.select', 'datatables.buttons','datatables.fixedcolumns','localytics.directives',
		'swxLocalStorage', 'angular-loading-bar', 'ng.ueditor','datePicker'])
var $injector = angular.injector();
function getContextPath() {
	var pathName = document.location.pathname;
	var index = pathName.substr(1).indexOf("/");
	var result = pathName.substr(0, index + 1);
	return result;
}
var version = new Date().getTime();
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
				if (angular.isDefined(responseObject.config)
						&& angular.isDefined(responseObject.config.data)
						&& angular.isDefined(responseObject.config.url)
						&& angular.isDefined(responseObject.data)) {
					$log.warn("$http|res url:", responseObject.config.url);
					$log.warn("$http|res post:", responseObject.config.data);
					$log.warn("$http|res data:", responseObject.data);
				}
				// 未登录
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
function config_main(cfpLoadingBarProvider, $locationProvider,
		$controllerProvider, $compileProvider, $stateProvider, $filterProvider,
		$provide, $urlRouterProvider, $ocLazyLoadProvider, IdleProvider,
		KeepaliveProvider, $httpProvider) {
	// 圈圈延迟出现控制
	console.log("App main config");
	cfpLoadingBarProvider.latencyThreshold = 2000;
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
	$httpProvider.defaults.headers.post['Cache-Control'] = 'no-cache';
	$httpProvider.defaults.headers.post['Pragma'] = 'no-cache';
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
	$httpProvider.defaults.transformRequest = [ function(data) {
		return angular.isObject(data) && String(data) !== '[object File]' ? param(data)
				: data;
	} ];
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
						$log.warn("账户已经登录,马上跳转至content");
						$state.go("content");
					} else {
					}
				})
			},
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/login/login.js' ]
				}, {
					serie : true,
					files : [ 'views/system/login/l.css' ]
				} ]);
			}
		}
	});
	// 默认页面需要做检查
	$stateProvider.state('content', {
		url : "/show_content",
		data : {
			pageTitle : '',
			loginCheck : true
		},
		templateUrl : "views/common/content.html"
	})
}
app
		.config(config_main)
		.run(
				function(Idle, $rootScope, $state, $http, $log, $transitions,
						userService, $templateCache) {
					console.log("App main run");
					// start watching when the app runs. also starts the
					// Keepalive service by
					// Idle.watch();
					// 替换了之前的$stateNotFound
					$state.onInvalid(function(to, from, injector) {
						$log.warn("to", to);
						$log.warn("from", from);
						$log.warn("injector", injector);
						alert("未配置路由.");
					});
					// 替换了之前的$stateChangeStart
					$transitions
							.onSuccess(
									{
										to : '**'
									},
									function(trans) {
										// 删除html缓存
										var $state = trans.router.stateService;
										if (angular
												.isDefined($state.router.globals)
												&& angular
														.isDefined($state.router.globals.current)
												&& angular
														.isDefined($state.router.globals.current.templateUrl)) {
											console
													.log("Remove|"
															+ $state.router.globals.current.templateUrl);
											$templateCache
													.remove($state.router.globals.current.templateUrl)
										}
										// 处理from
										var from_arr = trans._treeChanges.from;
										var from = null;
										if (from_arr.length > 0) {
											from = from_arr[from_arr.length - 1].state.name;
										}
										$log.warn("from:", from);
										// 处理to
										var target = trans._targetState._definition;
										if (angular.isDefined(target.data)
												&& angular
														.isDefined(target.data.loginCheck)
												&& target.data.loginCheck) {
											$log.warn("Action LoginCheck");
											var userService = trans.injector()
													.get('userService');
											userService
													.checkLogin()
													.then(
															function(result) {
																$log
																		.warn(
																				"check login result,from:"
																						+ from
																						+ ",result:",
																				result)

																if (!result.success) {
																	if (from != "login") {
																		$state
																				.go(
																						"login",
																						{
																							to : from
																						});
																	} else {
																	}
																}
															},
															function(error) {
																alert('系统错误');
																event
																		.preventDefault();
															},
															function(progress) {
															})
										}

									});
					$rootScope.$state = $state;
					var ct = getContextPath();
					$rootScope.project = ct + "/";
					$log.info('$rootScope.project:' + $rootScope.project);
					$rootScope.version = version;
					$rootScope.$on('IdleStart', function() {
						$log.warn('IdleStart');
						// the user appears to have gone idle
					});

					$rootScope.$on('IdleWarn', function(e, countdown) {
						$log.warn('IdleWarncountdown', countdown);
						if (countdown == 1) {
							// 重新激活
							// Idle.watch();
						}
						// follows after the IdleStart event, but includes a
						// countdown until the
						// user is considered timed out
						// the countdown arg is the number of seconds remaining
						// until then.
						// you can change the title or display a warning dialog
						// from here.
						// you can let them resume their session by calling
						// Idle.watch()
					});
					$rootScope.$on('IdleTimeout', function() {
						$log.warn('IdleTimeout');
						// the user has timed out (meaning idleDuration +
						// timeout has passed
						// without any activity)
						// this is where you'd log them
					});
					$rootScope.$on('IdleEnd', function() {
						$log.warn('IdleEnd');
						// the user has come back from AFK and is doing stuff.
						// if you are
						// warning them, you can use this to hide the dialog
					});
					$rootScope.$on('Keepalive', function() {
						$log.warn('IdlKeepaliveeEnd');
						// do something to keep the user's session alive
					});
				});
app.config(config_wx).run(function() {
	console.log("App Wx run");
});
app.config(config_mshop).run(function() {
	console.log("App MShop run");
});
app.config(config_shop).run(function() {
	console.log("App Shop run");
});
app.config(config_om).run(function() {
	console.log("App Om run");
});
app.config(config_system).run(function() {
	console.log("App System run");
});


function initDT(DTDefaultOptions) {
	var lng={
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
		};
	
	console.log(DTDefaultOptions);
	DTDefaultOptions.setLanguage(lng);
	DTDefaultOptions.setDOM('frtilp');
	DTDefaultOptions.setDisplayLength(25);
	DTDefaultOptions.setOption('sPaginationType','full_numbers');
	DTDefaultOptions.setOption('ordering',false);
	DTDefaultOptions.setOption('searching',false);
	DTDefaultOptions.setOption('paging',true);
	DTDefaultOptions.setOption('bStateSave',false);
	DTDefaultOptions.setOption('bProcessing',true);
	DTDefaultOptions.setOption('bInfo',false);
	DTDefaultOptions.setOption('bAutoWidth',false);
	DTDefaultOptions.setOption('responsive',true);
	DTDefaultOptions.setOption('bFilter',false);
	DTDefaultOptions.setOption('serverSide',false);
	
}

app.run(initDT);


//before loading
$("#beforePage").removeClass("preloader");
$("#beforePage").addClass("preloader-hidden");





 
app.factory('scriptLoader', ['$q', '$timeout', function($q, $timeout) {

    /**
     * Naming it processedScripts because there is no guarantee any of those have been actually loaded/executed
     * @type {Array}
     */
    var processedScripts = [];
    return {
        /**
         * Parses 'data' in order to find & execute script tags asynchronously as defined.
         * Called for partial views.
         * @param data
         * @returns {*} promise that will be resolved when all scripts are loaded
         */
        loadScriptTagsFromData: function(data) {
            var deferred = $q.defer();
            var $contents = $($.parseHTML(data, document, true)),
                $scripts = $contents.filter('script[data-src][type="text/javascript-lazy"]').add($contents.find('script[data-src][type="text/javascript-lazy"]')),
                scriptLoader = this;

            scriptLoader.loadScripts($scripts.map(function(){ return $(this).attr('data-src')}).get())
                .then(function(){
                    deferred.resolve(data);
                });

            return deferred.promise;
        },


        /**
         * Sequentially and asynchronously loads scripts (without blocking) if not already loaded
         * @param scripts an array of url to create script tags from
         * @returns {*} promise that will be resolved when all scripts are loaded
         */
        loadScripts: function(scripts) {
            var previousDefer = $q.defer();
            previousDefer.resolve();
            scripts.forEach(function(script){
                if (processedScripts[script]){
                    if (processedScripts[script].processing){
                        previousDefer = processedScripts[script];
                    }
                    return
                }

                var scriptTag = document.createElement('script'),
                    $scriptTag = $(scriptTag),
                    defer = $q.defer();
                scriptTag.src = script;
                defer.processing = true;

                $scriptTag.load(function(){
                    $timeout(function(){
                        defer.resolve();
                        defer.processing = false;
                        Pace.restart();
                    })
                });

                previousDefer.promise.then(function(){
                    document.body.appendChild(scriptTag);
                });

                processedScripts[script] = previousDefer = defer;
            });

            return previousDefer.promise;
        }
    }
}]);
app.service('userService', function($http, $q, $log, $rootScope, $localStorage) {
	return {
		logout : function() {
			var deferred = $q.defer();
			$http.post($rootScope.project + "/api/user/logout.do", {}).success(function(res) {
				if (res.success) {
					$rootScope.dt_app_token = ""
					$localStorage.put('dt_app_token', "");
					$rootScope.dt_sys_menus = []
					$localStorage.put('dt_sys_menus', []);
				}
				deferred.resolve(res);
			})
			return deferred.promise;
		},
		ct : function() {
			var deferred = $q.defer();
			$http.post( "/dt/api/system/queryContextPath.do", {}).success(function(res) {
				 
				deferred.resolve(res);
			})
			return deferred.promise;
		},
		login : function(e) {
			var deferred = $q.defer();
			e.basePublic = "yes";
			e.client="web";
			$http.post($rootScope.project + "/api/user/login.do", e).success(function(res) {
				$log.warn("service login return", res);
				if (res.success) {
					if (angular.isDefined(res.data.token)) {
						// 用户token
						$log.info("return data:",res.data);
						$log.warn("set token to $rootScope")
						$rootScope.dt_app_token = res.data.token;
						$localStorage.put('dt_app_token', res.data.token);
						// 用户信息
						$rootScope.dt_sys_user_info = res.data.user_info;
						$localStorage.put('dt_sys_user_info', res.data.user_info);

						// 用户拥有的系统资源
						$rootScope.dt_systems = res.data.systems;
						$localStorage.put('dt_systems', res.data.systems);
						
						//初始化菜单
						// 当前默认可能存在Id为1的系统,默认获取该资源
						var menuid = "";
						if (angular.isDefined(res.data.cur_system)&&res.data.cur_system.length>0) {
							menuid = res.data.cur_system;
						}
						$log.info("selected menu_id:"+menuid);
						if(menuid.length>0){
							$http.post($rootScope.project + "/api/user/getUserMenus.do", {
								menu_id : menuid
							}).success(function(rs) {
								if (rs.success) {
									$log.warn("###Action login,load user_menu from service######");
									$rootScope.dt_sys_menus = rs.data;
									$localStorage.put('dt_sys_menus', rs.data);
								}
								deferred.resolve(rs);
							});
						}else{
							$log.info("当前无菜单")
							$rootScope.dt_sys_menus = [];
							$localStorage.put('dt_sys_menus', []);
						}
						
					} else {
						$log.debug("返回不存在token");
					}
				}
				deferred.resolve(res);
			})
			return deferred.promise;
		},
		getToken : function() {
			if (angular.isDefined($rootScope.dt_app_token)) {
				return $rootScope.dt_app_token;
			} else {
				return $localStorage.get("dt_app_token");
			}
			return "";
		},
		checkLogin : function() {
			
			var deferred = $q.defer();
			// 后期需要加上菜单选择判断,当前暂时不实现
			$http.post($rootScope.project + "/api/user/checkLogin.do", {}).success(function(res) {
				deferred.resolve(res);
			});
			return deferred.promise;
			
//			if(!angular.isDefined($rootScope.project )){
//				console.log("not defined")
//				var deferred = $q.defer();
//				$http.post( "../api/system/queryContextPath.do", {}).success(function(res) {
//					if(res.success){
//						$rootScope.project=res.data+"/";
//						var deferred = $q.defer();
//						// 后期需要加上菜单选择判断,当前暂时不实现
//						$http.post($rootScope.project + "/api/user/checkLogin.do", {}).success(function(res) {
//							deferred.resolve(res);
//						});
//						return deferred.promise;
//					}
//					 
//				})
//			 
//			}
 
		
		},
		switchSystem : function(id) {
			var deferred = $q.defer();
			$http.post($rootScope.project + "/api/user/getUserMenus.do", {
				menu_id : id
			}).success(function(rs) {
				if (rs.success) {
					$log.warn("###Action switchSystem,load user_menu from service######");
					$rootScope.dt_sys_menus = rs.data;
					$localStorage.put('dt_sys_menus', rs.data);
				}
				deferred.resolve(rs);
			});
			return deferred.promise;
		}
	}
});
/**
 * INSPINIA - Responsive Admin Theme
 *
 */
function config($translateProvider) {

    $translateProvider
        .translations('en', {

            // Define all menu elements
            DASHBOARD: 'Dashboard',
            GRAPHS: 'Graphs',
            MAILBOX: 'Mailbox',
            WIDGETS: 'Widgets',
            METRICS: 'Metrics',
            FORMS: 'Forms',
            APPVIEWS: 'App views',
            OTHERPAGES: 'Other pages',
            UIELEMENTS: 'UI elements',
            MISCELLANEOUS: 'Miscellaneous',
            GRIDOPTIONS: 'Grid options',
            TABLES: 'Tables',
            COMMERCE: 'E-commerce',
            GALLERY: 'Gallery',
            MENULEVELS: 'Menu levels',
            ANIMATIONS: 'Animations',
            LANDING: 'Landing page',
            LAYOUTS: 'Layouts',

            // Define some custom text
            WELCOME: 'Welcome Amelia',
            MESSAGEINFO: 'You have 42 messages and 6 notifications.',
            SEARCH: 'Search for something...',
            DEMO: 'Internationalization (sometimes shortened to \"I18N , meaning \"I - eighteen letters -N\") is the process of planning and implementing products and services so that they can easily be adapted to specific local languages and cultures, a process called localization . The internationalization process is sometimes called translation or localization enablement .'

        })
        .translations('es', {

            // Define all menu elements
            DASHBOARD: 'Salpicadero',
            GRAPHS: 'Gráficos',
            MAILBOX: 'El correo',
            WIDGETS: 'Widgets',
            METRICS: 'Métrica',
            FORMS: 'Formas',
            APPVIEWS: 'Vistas app',
            OTHERPAGES: 'Otras páginas',
            UIELEMENTS: 'UI elements',
            MISCELLANEOUS: 'Misceláneo',
            GRIDOPTIONS: 'Cuadrícula',
            TABLES: 'Tablas',
            COMMERCE: 'E-comercio',
            GALLERY: 'Galería',
            MENULEVELS: 'Niveles de menú',
            ANIMATIONS: 'Animaciones',
            LANDING: 'Página de destino',
            LAYOUTS: 'Esquemas',

            // Define some custom text
            WELCOME: 'Bienvenido Amelia',
            MESSAGEINFO: 'Usted tiene 42 mensajes y 6 notificaciones.',
            SEARCH: 'Busca algo ...',
            DEMO: 'Internacionalización (a veces abreviado como \"I18N, que significa\" I - dieciocho letras N \") es el proceso de planificación e implementación de productos y servicios de manera que se pueden adaptar fácilmente a las lenguas y culturas locales específicas, un proceso llamado localización El proceso de internacionalización. a veces se llama la traducción o la habilitación de localización.'
        });

    $translateProvider.preferredLanguage('en');

}

angular
    .module('inspinia')
    .config(config)

/**
 * INSPINIA - Responsive Admin Theme
 * 
 * Main directives.js file Define directives for used plugin
 * 
 * 
 * Functions (directives) - sideNavigation - iboxTools - minimalizaSidebar -
 * vectorMap - sparkline - icheck - ionRangeSlider - dropZone - responsiveVideo -
 * chatSlimScroll - customValid - fullScroll - closeOffCanvas - clockPicker -
 * landingScrollspy - fitHeight - iboxToolsFullScreen - slimScroll - truncate -
 * touchSpin - markdownEditor - resizeable - bootstrapTagsinput - passwordMeter
 * 
 */

/**
 * pageTitle - Directive for set Page title - mata title
 */
function pageTitle($rootScope, $timeout, $transitions) {
	return {
		link : function(scope, element) {
			$transitions.onSuccess({
				to : '**'
			}, function(trans) {
				var current = trans.router.globals.current;
				var title = "后台管理";
				if (angular.isDefined(current.data)
						&& angular.isDefined(current.data.pageTitle)) {
					title = current.data.pageTitle;
				}
				element.text(title);
			});
		}
	}
};

/**
 * sideNavigation - Directive for run metsiMenu on sidebar navigation
 */
function sideNavigation($timeout) {
	return {
		restrict : 'A',
		link : function(scope, element) {
			// Call the metsiMenu plugin and plug it to sidebar navigation
			$timeout(function() {
				element.metisMenu();
			});

			// Colapse menu in mobile mode after click on element
			var menuElement = $('#side-menu a:not([href$="\\#"])');
			menuElement.click(function() {
				if ($(window).width() < 769) {
					$("body").toggleClass("mini-navbar");
				}
			});

			// Enable initial fixed sidebar
			if ($("body").hasClass('fixed-sidebar')) {
				var sidebar = element.parent();
				sidebar.slimScroll({
					height : '100%',
					railOpacity : 0.9,
				});
			}

		}
	};
};

/**
 * responsibleVideo - Directive for responsive video
 */
function responsiveVideo() {
	return {
		restrict : 'A',
		link : function(scope, element) {
			var figure = element;
			var video = element.children();
			video.attr('data-aspectRatio', video.height() / video.width())
					.removeAttr('height').removeAttr('width')

			// We can use $watch on $window.innerWidth also.
			$(window).resize(
					function() {
						var newWidth = figure.width();
						video.width(newWidth).height(
								newWidth * video.attr('data-aspectRatio'));
					}).resize();
		}
	}
}

/**
 * iboxTools - Directive for iBox tools elements in right corner of ibox
 */
function iboxTools($timeout) {
	return {
		restrict : 'A',
		scope : true,
		templateUrl : 'views/common/ibox_tools.html',
		controller : function($scope, $element) {
			// Function for collapse ibox
			$scope.showhide = function() {
				var ibox = $element.closest('div.ibox');
				var icon = $element.find('i:first');
				var content = ibox.children('.ibox-content');
				content.slideToggle(200);
				// Toggle icon from up to down
				icon.toggleClass('fa-chevron-up')
						.toggleClass('fa-chevron-down');
				ibox.toggleClass('').toggleClass('border-bottom');
				$timeout(function() {
					ibox.resize();
					ibox.find('[id^=map-]').resize();
				}, 50);
			};
			// Function for close ibox
			$scope.closebox = function() {
				var ibox = $element.closest('div.ibox');
				ibox.remove();
			}
		}
	};
}

/**
 * iboxTools with full screen - Directive for iBox tools elements in right
 * corner of ibox with full screen option
 */
function iboxToolsFullScreen($timeout) {
	return {
		restrict : 'A',
		scope : true,
		templateUrl : 'views/common/ibox_tools_full_screen.html',
		controller : function($scope, $element) {
			// Function for collapse ibox
			$scope.showhide = function() {
				var ibox = $element.closest('div.ibox');
				var icon = $element.find('i:first');
				var content = ibox.children('.ibox-content');
				content.slideToggle(200);
				// Toggle icon from up to down
				icon.toggleClass('fa-chevron-up')
						.toggleClass('fa-chevron-down');
				ibox.toggleClass('').toggleClass('border-bottom');
				$timeout(function() {
					ibox.resize();
					ibox.find('[id^=map-]').resize();
				}, 50);
			};
			// Function for close ibox
			$scope.closebox = function() {
				var ibox = $element.closest('div.ibox');
				ibox.remove();
			};
			// Function for full screen
			$scope.fullscreen = function() {
				var ibox = $element.closest('div.ibox');
				var button = $element.find('i.fa-expand');
				$('body').toggleClass('fullscreen-ibox-mode');
				button.toggleClass('fa-expand').toggleClass('fa-compress');
				ibox.toggleClass('fullscreen');
				setTimeout(function() {
					$(window).trigger('resize');
				}, 100);
			}
		}
	};
}

/**
 * minimalizaSidebar - Directive for minimalize sidebar
 */
function minimalizaSidebar($timeout) {
	return {
		restrict : 'A',
		template : '<a  class="navbar-minimalize minimalize-styl-2 btn btn-switch" href="" ng-click="minimalize()"><i class="fa fa-dedent fa-fw text"></i></a>',
		controller : function($scope, $element) {
			$scope.minimalize = function() {
				$("body").toggleClass("mini-navbar");

				if (!$('body').hasClass('mini-navbar')
						|| $('body').hasClass('body-small')) {
					// Hide menu in order to smoothly turn on when maximize menu
					$('#side-menu').hide();
					// For smoothly turn on menu
					setTimeout(function() {
						$('#side-menu').fadeIn(400);
					}, 200);

				} else if ($('body').hasClass('fixed-sidebar')) {
					$('#side-menu').hide();
					setTimeout(function() {
						$('#side-menu').fadeIn(400);
					}, 100);
				} else {
					// Remove all inline style from jquery fadeIn function to
					// reset menu state
					$('#side-menu').removeAttr('style');
				}
			}
		}
	};
};

function closeOffCanvas() {
	return {
		restrict : 'A',
		template : '<a class="close-canvas-menu" ng-click="closeOffCanvas()"><i class="fa fa-times"></i></a>',
		controller : function($scope, $element) {
			$scope.closeOffCanvas = function() {
				$("body").toggleClass("mini-navbar");
			}
		}
	};
}

/**
 * vectorMap - Directive for Vector map plugin
 */
function vectorMap() {
	return {
		restrict : 'A',
		scope : {
			myMapData : '=',
		},
		link : function(scope, element, attrs) {
			var map = element.vectorMap({
				map : 'world_mill_en',
				backgroundColor : "transparent",
				regionStyle : {
					initial : {
						fill : '#e4e4e4',
						"fill-opacity" : 0.9,
						stroke : 'none',
						"stroke-width" : 0,
						"stroke-opacity" : 0
					}
				},
				series : {
					regions : [ {
						values : scope.myMapData,
						scale : [ "#1ab394", "#22d6b1" ],
						normalizeFunction : 'polynomial'
					} ]
				},
			});
			var destroyMap = function() {
				element.remove();
			};
			scope.$on('$destroy', function() {
				destroyMap();
			});
		}
	}
}

/**
 * sparkline - Directive for Sparkline chart
 */
function sparkline() {
	return {
		restrict : 'A',
		scope : {
			sparkData : '=',
			sparkOptions : '=',
		},
		link : function(scope, element, attrs) {
			scope.$watch(scope.sparkData, function() {
				render();
			});
			scope.$watch(scope.sparkOptions, function() {
				render();
			});
			var render = function() {
				$(element).sparkline(scope.sparkData, scope.sparkOptions);
			};
		}
	}
};

/**
 * icheck - Directive for custom checkbox icheck
 */
// .directive('onFinishRender',['$timeout', '$parse', function ($timeout,
// $parse) {
function onFinishRender($timeout, $parse) {
	return {
		restrict : 'A',
		link : function(scope, element, attr) {
			if (scope.$last === true) {
				$timeout(function() {
					scope.$emit('ngRepeatFinished'); // 事件通知
					var fun = $scope.$eval(attrs.onFinishRender);
					if (fun && typeof (fun) == 'function') {
						fun(); // 回调函数
					}
				});
			}
		}
	}
};

function icheck($timeout) {
	return {
		restrict : 'A',
		require : 'ngModel',
		link : function($scope, element, $attrs, ngModel) {
			return $timeout(function() {
				var value;
				value = $attrs['value'];

				$scope.$watch($attrs['ngModel'], function(newValue) {
					$(element).iCheck('update');
				})

				return $(element)
						.iCheck({
							checkboxClass : 'icheckbox_square-green',
							radioClass : 'iradio_square-green'

						})
						.on(
								'ifChanged',
								function(event) {
									if ($(element).attr('type') === 'checkbox'
											&& $attrs['ngModel']) {
										$scope
												.$apply(function() {
													return ngModel
															.$setViewValue(event.target.checked);
												});
									}
									if ($(element).attr('type') === 'radio'
											&& $attrs['ngModel']) {
										return $scope
												.$apply(function() {
													return ngModel
															.$setViewValue(value);
												});
									}
								});
			});
		}
	};
}

/**
 * ionRangeSlider - Directive for Ion Range Slider
 */
function ionRangeSlider() {
	return {
		restrict : 'A',
		scope : {
			rangeOptions : '='
		},
		link : function(scope, elem, attrs) {
			elem.ionRangeSlider(scope.rangeOptions);
		}
	}
}

/**
 * dropZone - Directive for Drag and drop zone file upload plugin
 */
function dropZone() {
	return {
		restrict : 'AE',
		scope : {
			dzconfig : '=',
			dzeventhandlers : '=',
			alias : '@'
		},
		link : function(scope, element, attrs) {

			// //创建对象
			// if (scope.dzconfig.maxFiles == null) {
			// scope.dzconfig.maxFiles = 1;
			// }
			// drop files here to uploads
			console.log(scope.dzconfig);
			if (typeof (scope.dzconfig.dictDefaultMessage) == "undefined") {
				scope.dzconfig.dictDefaultMessage = "点击上传文件";
			}

			scope.dzconfig.dictFallbackMessage = "Your browser does not support drag'n'drop file uploads."
			scope.dzconfig.dictFallbackText = "Please use the fallback form below to upload your files like in the olden days."
			scope.dzconfig.dictFileTooBig = "File is too big ({{filesize}}MiB). Max filesize: {{maxFilesize}}MiB."
			scope.dzconfig.dictInvalidFileType = "不能上传此类文件"
			scope.dzconfig.dictResponseError = "服务端错误代码:{{statusCode}}"
			scope.dzconfig.dictCancelUpload = "取消上传"
			scope.dzconfig.dictCancelUploadConfirmation = "Are you sure you want to cancel this upload?"
			scope.dzconfig.dictRemoveFile = "删除"
			scope.dzconfig.dictRemoveFileConfirmation = null
			scope.dzconfig.dictMaxFilesExceeded = "超过最大文件限制"

			var dzeventhandlers = {
				'addedfile' : function(file) {
					scope.file = file;
					if (this.files[1] != null) {
						this.removeFile(this.files[0]);
					}
					scope.$apply(function() {
						scope.fileAdded = true;
					});
				},

				'success' : function(file, response) {
				},
				'maxfilesexceeded' : function(file) {
					if (scope.dropzone.options.maxFiles == 1) {
						scope.dropzone.removeAllFiles()
						scope.dropzone.addFile(file);
					} else {
						scope.dropzone.removeFile(file);
					}
					message_error("已超过文件最大限制数目,"
							+ scope.dropzone.options.maxFiles);
				}

			};

			dropzone = new Dropzone(element[0], scope.dzconfig);

			// angular.forEach(dzeventhandlers, function(handler, event) {
			//				
			// dropzone.on(event, handler);
			// });
			// 派发前前端配置监听事件
			if (scope.dzeventhandlers) {
				Object.keys(scope.dzeventhandlers).forEach(function(eventName) {
					dropzone.on(eventName, scope.dzeventhandlers[eventName]);
				});
			}

			scope.processDropzone = function() {
				dropzone.processQueue();
			};

			scope.resetDropzone = function() {
				dropzone.removeAllFiles();
			}
		}
	}
}

/**
 * chatSlimScroll - Directive for slim scroll for small chat
 */
function chatSlimScroll($timeout) {
	return {
		restrict : 'A',
		link : function(scope, element) {
			$timeout(function() {
				element.slimscroll({
					height : '234px',
					railOpacity : 0.4
				});

			});
		}
	};
}

/**
 * customValid - Directive for custom validation example
 */
function customValid() {
	return {
		require : 'ngModel',
		link : function(scope, ele, attrs, c) {
			scope.$watch(attrs.ngModel, function() {

				// You can call a $http method here
				// Or create custom validation

				var validText = "Inspinia";

				if (scope.extras == validText) {
					c.$setValidity('cvalid', true);
				} else {
					c.$setValidity('cvalid', false);
				}

			});
		}
	}
}

/**
 * fullScroll - Directive for slimScroll with 100%
 */
function fullScroll($timeout) {
	return {
		restrict : 'A',
		link : function(scope, element) {
			$timeout(function() {
				element.slimscroll({
					height : '100%',
					railOpacity : 0.9
				});

			});
		}
	};
}

/**
 * slimScroll - Directive for slimScroll with custom height
 */
function slimScroll($timeout) {
	return {
		restrict : 'A',
		scope : {
			boxHeight : '@'
		},
		link : function(scope, element) {
			$timeout(function() {
				element.slimscroll({
					height : scope.boxHeight,
					railOpacity : 0.9
				});

			});
		}
	};
}

/**
 * clockPicker - Directive for clock picker plugin
 */
function clockPicker() {
	return {
		restrict : 'A',
		link : function(scope, element) {
			element.clockpicker();
		}
	};
};

/**
 * landingScrollspy - Directive for scrollspy in landing page
 */
function landingScrollspy() {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			element.scrollspy({
				target : '.navbar-fixed-top',
				offset : 80
			});
		}
	}
}

/**
 * fitHeight - Directive for set height fit to window height
 */
function fitHeight() {
	return {
		restrict : 'A',
		link : function(scope, element) {
			element.css("height", $(window).height() + "px");
			element.css("min-height", $(window).height() + "px");
		}
	};
}

/**
 * truncate - Directive for truncate string
 */
function truncate($timeout) {
	return {
		restrict : 'A',
		scope : {
			truncateOptions : '='
		},
		link : function(scope, element) {
			$timeout(function() {
				element.dotdotdot(scope.truncateOptions);

			});
		}
	};
}

/**
 * touchSpin - Directive for Bootstrap TouchSpin
 */
function touchSpin() {
	return {
		restrict : 'A',
		scope : {
			spinOptions : '='
		},
		link : function(scope, element, attrs) {
			scope.$watch(scope.spinOptions, function() {
				render();
			});
			var render = function() {
				$(element).TouchSpin(scope.spinOptions);
			};
		}
	}
};

/**
 * markdownEditor - Directive for Bootstrap Markdown
 */
function markdownEditor() {
	return {
		restrict : "A",
		require : 'ngModel',
		link : function(scope, element, attrs, ngModel) {
			$(element).markdown({
				savable : false,
				onChange : function(e) {
					ngModel.$setViewValue(e.getContent());
				}
			});
		}
	}
};

/**
 * passwordMeter - Directive for jQuery Password Strength Meter
 */
function passwordMeter() {
	return {
		restrict : 'A',
		scope : {
			pwOptions : '='
		},
		link : function(scope, element, attrs) {
			scope.$watch(scope.pwOptions, function() {
				render();
			});
			var render = function() {
				$(element).pwstrength(scope.pwOptions);
			};
		}
	}
};
angular.module('inspinia').directive('compile', function($compile) {
	return function(scope, element, attrs) {
		scope.$watch(function(scope) {
			return scope.$eval(attrs.compile);
		}, function(value) {
			element.html(value);
			$compile(element.contents())(scope);
		});
	};
});
 
/**
 * 
 * Pass all functions into module
 */
angular.module('inspinia').directive('pageTitle', pageTitle).directive(
		'sideNavigation', sideNavigation).directive('iboxTools', iboxTools)
		.directive('minimalizaSidebar', minimalizaSidebar).directive(
				'vectorMap', vectorMap).directive('sparkline', sparkline)
		.directive('icheck', icheck)
		.directive('ionRangeSlider', ionRangeSlider).directive('dropZone',
				dropZone).directive('responsiveVideo', responsiveVideo)
		.directive('chatSlimScroll', chatSlimScroll).directive('customValid',
				customValid).directive('fullScroll', fullScroll).directive(
				'closeOffCanvas', closeOffCanvas).directive('clockPicker',
				clockPicker).directive('landingScrollspy', landingScrollspy)
		.directive('fitHeight', fitHeight).directive('iboxToolsFullScreen',
				iboxToolsFullScreen).directive('slimScroll', slimScroll)
		.directive('truncate', truncate).directive('touchSpin', touchSpin)
		.directive('markdownEditor', markdownEditor).directive('passwordMeter',
				passwordMeter).directive('onFinishRender', onFinishRender);

angular.module('inspinia').directive("bnDocumentClick",
		function($document, $parse) {
			//将Angular的上下文链接到DOM事件
			var linkFunction = function($scope, $element, $attributes) {
				//获得表达式
				var scopeExpression = $attributes.bnDocumentClick;
				//使用$parse来编译表达式
				var invoker = $parse(scopeExpression);
				var event = $attributes.bnDocumentEvent;
				if (!angular.isDefined(event)) {
					event = "click";
				}
				//绑定click事件
				$document.on(event, function(event) {
					//当点击事件被触发时，我们需要再次调用AngularJS的上下文。再次，我们使用$apply()来确保$digest()方法在幕后被调用
					$scope.$apply(function() {
						//在scope中调用处理函数，将jQuery时间映射到$event对象上
						invoker($scope, {
							$event : event
						});
					});
				});
				//当父控制器被从渲染文档中移除时监听"$destory"事件
			};
			//返回linking函数
			return (linkFunction);
		});

'use strict';

/**
 * 0.1.1
 * Deferred load js/css file, used for ui-jq.js and Lazy Loading.
 * 
 * @ flatfull.com All Rights Reserved.
 * Author url: #user/flatfull
 */

angular.module('ui.load', [])
	.service('uiLoad', ['$document', '$q', '$timeout', function ($document, $q, $timeout) {

		var loaded = [];
		var promise = false;
		var deferred = $q.defer();

		/**
		 * Chain loads the given sources
		 * @param srcs array, script or css
		 * @returns {*} Promise that will be resolved once the sources has been loaded.
		 */
		this.load = function (srcs) {
			srcs = angular.isArray(srcs) ? srcs : srcs.split(/\s+/);
			var self = this;
			if(!promise){
				promise = deferred.promise;
			}
      angular.forEach(srcs, function(src) {
      	promise = promise.then( function(){
      		return src.indexOf('.css') >=0 ? self.loadCSS(src) : self.loadScript(src);
      	} );
      });
      deferred.resolve();
      return promise;
		}

		/**
		 * Dynamically loads the given script
		 * @param src The url of the script to load dynamically
		 * @returns {*} Promise that will be resolved once the script has been loaded.
		 */
		this.loadScript = function (src) {
			if(loaded[src]) return loaded[src].promise;

			var deferred = $q.defer();
			var script = $document[0].createElement('script');
			script.src = src;
			script.onload = function (e) {
				$timeout(function () {
					deferred.resolve(e);
				});
			};
			script.onerror = function (e) {
				$timeout(function () {
					deferred.reject(e);
				});
			};
			$document[0].body.appendChild(script);
			loaded[src] = deferred;

			return deferred.promise;
		};

		/**
		 * Dynamically loads the given CSS file
		 * @param href The url of the CSS to load dynamically
		 * @returns {*} Promise that will be resolved once the CSS file has been loaded.
		 */
		this.loadCSS = function (href) {
			if(loaded[href]) return loaded[href].promise;

			var deferred = $q.defer();
			var style = $document[0].createElement('link');
			style.rel = 'stylesheet';
			style.type = 'text/css';
			style.href = href;
			style.onload = function (e) {
				$timeout(function () {
					deferred.resolve(e);
				});
			};
			style.onerror = function (e) {
				$timeout(function () {
					deferred.reject(e);
				});
			};
			$document[0].head.appendChild(style);
			loaded[href] = deferred;

			return deferred.promise;
		};
}]);

angular.module('ui.jq', ['ui.load']).
value('uiJqConfig', {}).
directive('uiJq', ['uiJqConfig', 'JQ_CONFIG', 'uiLoad', '$timeout', function uiJqInjectingFunction(uiJqConfig, JQ_CONFIG, uiLoad, $timeout) {

return {
  restrict: 'A',
  compile: function uiJqCompilingFunction(tElm, tAttrs) {

    if (!angular.isFunction(tElm[tAttrs.uiJq]) && !JQ_CONFIG[tAttrs.uiJq]) {
      throw new Error('ui-jq: The "' + tAttrs.uiJq + '" function does not exist');
    }
    var options = uiJqConfig && uiJqConfig[tAttrs.uiJq];

    return function uiJqLinkingFunction(scope, elm, attrs) {

      function getOptions(){
        var linkOptions = [];

        // If ui-options are passed, merge (or override) them onto global defaults and pass to the jQuery method
        if (attrs.uiOptions) {
          linkOptions = scope.$eval('[' + attrs.uiOptions + ']');
          if (angular.isObject(options) && angular.isObject(linkOptions[0])) {
            linkOptions[0] = angular.extend({}, options, linkOptions[0]);
          }
        } else if (options) {
          linkOptions = [options];
        }
        return linkOptions;
      }

      // If change compatibility is enabled, the form input's "change" event will trigger an "input" event
      if (attrs.ngModel && elm.is('select,input,textarea')) {
        elm.bind('change', function() {
          elm.trigger('input');
        });
      }

      // Call jQuery method and pass relevant options
      function callPlugin() {
        $timeout(function() {
          elm[attrs.uiJq].apply(elm, getOptions());
        }, 0, false);
      }

      function refresh(){
        // If ui-refresh is used, re-fire the the method upon every change
        if (attrs.uiRefresh) {
          scope.$watch(attrs.uiRefresh, function() {
            callPlugin();
          });
        }
      }

      if ( JQ_CONFIG[attrs.uiJq] ) {
        uiLoad.load(JQ_CONFIG[attrs.uiJq]).then(function() {
          callPlugin();
          refresh();
        }).catch(function() {
          
        });
      } else {
        callPlugin();
        refresh();
      }
    };
  }
};
}]);


app.directive('focusMe', ['$timeout', function($timeout) {  
	  return {  
	    scope: { trigger: '@focusMe' },  
	    link: function(scope, element) {  
	      scope.$watch('trigger', function(value) {  
	        if(value === "true") {  
	          $timeout(function() {  
	            element[0].focus();  
	          });  
	        }  
	      });  
	    }  
	  };  
	}]); 

app.directive('contenteditable', function() {  
	  return {  
	    require: '?ngModel',  
	    link: function(scope, element, attrs, ctrl) {  
	   
	      // Do nothing if this is not bound to a model  
	      if (!ctrl) { return; }  
	   
	      // Checks for updates (input or pressing ENTER)  
	      // view -> model  
	      element.bind('input enterKey', function() {  
	        var rerender = false;  
	        var html = element.html();  
	   
	        if (attrs.noLineBreaks) {  
	          html = html.replace(/<div>/g, '').replace(/<br>/g, '').replace(/<\/div>/g, '');  
	          rerender = true;  
	        }  
	   
	        scope.$apply(function() {  
	          ctrl.$setViewValue(html);  
	          if(rerender) {  
	            ctrl.$render();  
	          }  
	        });  
	      });  
	   
	      element.keyup(function(e){  
	        if(e.keyCode === 13){  
	          element.trigger('enterKey');  
	        }  
	      });  
	   
	      // model -> view  
	      ctrl.$render = function() {  
	        element.html(ctrl.$viewValue);  
	      };  
	   
	      // load init value from DOM  
	      ctrl.$render();  
	    }  
	  };  
	}); 
var app={
		
		
}


function MainCtrl($log, $http, $scope, $rootScope, $state, $localStorage,
		userService, notify, $timeout) {
 
	//修改主题
	var cur_theme=$localStorage.get("cur_theme");
	if(angular.isDefined(cur_theme)){
		   $scope.cur_skin=cur_theme;
	}else{
		   $scope.cur_skin="default";
	}
	
	   $scope.change_theme=function(theme){
		   $scope.cur_skin=theme;
		   $localStorage.put("cur_theme",theme);
	   }

	   
	   
	$scope.fullScreen = function() {
		var element = document.documentElement; // 若要全屏页面中div，var element=
		// document.getElementById("divID");
		// IE 10及以下ActiveXObject
		if (window.ActiveXObject) {
			var WsShell = new ActiveXObject('WScript.Shell')
			WsShell.SendKeys('{F11}');
		}
		// HTML W3C 提议
		else if (element.requestFullScreen) {
			element.requestFullScreen();
		}
		// IE11
		else if (element.msRequestFullscreen) {
			element.msRequestFullscreen();
		}
		// Webkit (works in Safari5.1 and Chrome 15)
		else if (element.webkitRequestFullScreen) {
			element.webkitRequestFullScreen();
		}
		// Firefox (works in nightly)
		else if (element.mozRequestFullScreen) {
			element.mozRequestFullScreen();
		}
	}
	// 退出登录
	$scope.logout = function() {
		userService.logout().then(function(result) {
			$log.warn("userService logout result", result)
			if (result.success) {
				$state.go("login");
			} else {
			}
		}, function(error) {
		}, function(progress) {
		})

	}

	$scope.changepwd = function() {
		$state.go("me.pwdreset");
	}

	// 切换系统
	$scope.switchSystem = function(id) {
		userService.switchSystem(id).then(function(result) {
			if (result.success) {
				$timeout(function() {
					$state.go("content");
				}, 800);
			} else {
				notify({
					message : result.message
				});
			}
		}, function(error) {
		}, function(progress) {
		})

	}
	// 处理菜单的logo函数
	$scope.menuLogoIsExist = function(logo) {
		if (angular.isDefined(logo) && logo != "") {
			return true;
		} else {
			return false;
		}
	}
	// 监听左边菜单数据
	$scope.$watch(function() {
		return $rootScope.dt_sys_menus;
	}, function() {
		$log.warn("watch $rootScope.dt_sys_menus change,load from this,again.",
				$rootScope.dt_sys_menus);
		if (angular.isDefined($rootScope.dt_sys_menus)
				&& $rootScope.dt_sys_menus != null) {
			$scope.menu = $rootScope.dt_sys_menus;
			$state.go($state.current, null, {
				reload : true
			});
		}
	}, true);

	// 页面刷新
	var dt_sys_menu = $localStorage.get("dt_sys_menus");
	if (angular.isDefined(dt_sys_menu)) {
		$log.warn("dt_sys_menu load from localstorage", dt_sys_menu);
		$scope.menu = dt_sys_menu;
		// fixnav();
	}

	// 监听用户数据
	$scope
			.$watch(
					function() {
						return $rootScope.dt_sys_user_info;
					},
					function() {
						$log
								.warn(
										"watch $rootScope.dt_sys_user_info change,load from this,again.",
										$rootScope.dt_sys_user_info);
						if (angular.isDefined($rootScope.dt_sys_user_info)
								&& $rootScope.dt_sys_user_info != null) {
							$scope.sys_user_info = $rootScope.dt_sys_user_info
						}
					}, true);

	// 页面刷新
	var sys_user_info = $localStorage.get("dt_sys_user_info")
	if (angular.isDefined(sys_user_info)) {
		$log.warn("user_info load from localstorage", sys_user_info);
		$scope.sys_user_info = sys_user_info;
	}

	// 列举系统
	$scope.$watch(function() {
		return $rootScope.dt_systems;
	}, function() {
		$log.warn("watch $rootScope.dt_systems change,load from this,again.",
				$rootScope.dt_systems);
		if (angular.isDefined($rootScope.dt_systems)
				&& $rootScope.dt_systems != null) {
			$scope.dt_systems = $rootScope.dt_systems
		}
	}, true);
	var dt_systems = $localStorage.get("dt_systems")
	if (angular.isDefined(dt_systems)) {
		$log.warn("dt_systems load from localstorage", dt_systems);
		$scope.dt_systems = dt_systems;
	}

	// 固定左边导航
	// function fixnav() {
	//	}
	//	fixnav();

};
angular.module('inspinia').controller('MainCtrl', MainCtrl);