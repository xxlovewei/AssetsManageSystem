function config_cmdb($stateProvider, $ocLazyLoadProvider) {
	console.log("App Shop config");
	$ocLazyLoadProvider.config({
		debug : true
	});
	
	// cmdb
	$stateProvider.state('maintain', {
		abstract : true,
		url : "/maintain",
		templateUrl : "views/common/content.html"
	}).state('maintain.faultrecord', {
		url : "/maintain_faultrecord",
		data: { pageTitle: '故障上报'},
		template:'<div ng-controller="cmdbfaultrecordCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/faultrecord.js?v=' + version ]
				} ]);
			}
		}
	}).state('maintain.devsearch', {
		url : "/maintain_devsearch",
		data: { pageTitle: '设备查询'},
		template:'<div ng-controller="cmdbdevsearchCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/devsearch.js?v=' + version ]
				} ]);
			}
		}
	}).state('maintain.review', {
		url : "/maintain_review",
		data: { pageTitle: '资产复核'},
		templateUrl : "views/cmdb/review.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/review.js?v=' + version ]
				} ]);
			}
		}
	})
	
 
		// 报表
	$stateProvider.state('cmdbresp', {
		abstract : true,
		url : "/cmdbresp",
		templateUrl : "views/common/content.html"
	}).state('cmdbresp.partzc', {
		url : "/maintain_partzc",
		data: { pageTitle: '部门资产'},
		template:'<div ng-controller="cmdbrepPartZcCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/rep/partzj.js?v=' + version ]
				} ]);
			}
		}
	}).state('cmdbresp.zctjshow', {
		url : "/cmdbresp_zctjshow",
		data: { pageTitle: '资产统计'},
		templateUrl : "views/cmdb/rep/zctj.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/rep/zctj.js?v=' + version ]
				} ]);
			}
		}
	});
	
	
	// cmdb
	$stateProvider.state('cf', {
		abstract : true,
		url : "/cf",
		templateUrl : "views/common/content.html"
	}).state('cf.server', {
		url : "/cf_server",
		data: { pageTitle: '服务器'},
		templateUrl : "views/cmdb/html_server.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/js_server.js?v=' + version ]
				} ]);
			}
		}
	}).state('cf.firewall', {
		url : "/cf_firewall",
		data: { pageTitle: '防火墙'},
		templateUrl : "views/cmdb/html_firewall.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/js_firewall.js?v=' + version ]
				} ]);
			}
		}
	}).state('cf.lightsw', {
		url : "/cf_lightsw",
		data: { pageTitle: '光交'},
		templateUrl : "views/cmdb/html_lightsw.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/js_lightsw.js?v=' + version ]
				} ]);
			}
		}
	}).state('cf.compute', {
		url : "/cf_compute",
		data: { pageTitle: 'PC电脑'},
		templateUrl : "views/cmdb/html_pc.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/js_pc.js?v=' + version ]
				} ]);
			}
		}
	}).state('cf.outlets', {
		url : "/cf_outlets",
		data: { pageTitle: '网点设备'},
		templateUrl : "views/cmdb/html_pointdev.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/js_pointdev.js?v=' + version ]
				} ]);
			}
		}
	}).state('cf.route', {
		url : "/cf_route",
		data: { pageTitle: '路由设备'},
		templateUrl : "views/cmdb/html_route.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/js_route.js?v=' + version ]
				} ]);
			}
		}
	}).state('cf.safety', {
		url : "/cf_safety",
		data: { pageTitle: '安全设备'},
		templateUrl : "views/cmdb/html_safety.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/js_safety.js?v=' + version ]
				} ]);
			}
		}
	}).state('cf.storage', {
		url : "/cf_storage",
		data: { pageTitle: '存储设备'},
		templateUrl : "views/cmdb/html_storage.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/js_storage.js?v=' + version ]
				} ]);
			}
		}
	})
	.state('cf.switch', {
		url : "/cf_switch",
		data: { pageTitle: '交换机'},
		templateUrl : "views/cmdb/html_switch.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/js_switch.js?v=' + version ]
				} ]);
			}
		}
	}).state('cf.bjpj', {
		url : "/cf_bjpj",
		data: { pageTitle: 'IT备件配件'},
		templateUrl : "views/cmdb/html_bjpj.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/js_bjpj.js?v=' + version ]
				} ]);
			}
		}
	}).state('cf.zcotherhard', {
		url : "/cf_zcotherhard",
		data: { pageTitle: '其他设备'},
		templateUrl : "views/cmdb/html_zcotherhard.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/js_zcotherhard.js?v=' + version ]
				} ]);
			}
		}
	}).
	state('cf.outercontact', {
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
	}).state('cf.systemlist', {
		url : "/ct_systemlist",
		data: { pageTitle: '信息系统清单'},
		template:'<div ng-controller="cmdbsystemListCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/cmdb/systemlist.js?v=' + version ]
				} ]);
			}
		}
	});
	
	
	
}
