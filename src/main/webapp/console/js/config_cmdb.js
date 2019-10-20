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
	})
	
	
	
	
	// cmdb
	$stateProvider.state('cf', {
		abstract : true,
		url : "/cf",
		templateUrl : "views/common/content.html"
	}).state('cf.server', {
		url : "/cf_server",
		data: { pageTitle: '服务器'},
		template:'<div ng-controller="cmdbserverCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/server.js?v=' + version ]
				} ]);
			}
		}
	}).state('cf.firewall', {
		url : "/cf_firewall",
		data: { pageTitle: '防火墙'},
		template:'<div ng-controller="cmdbfirewallCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/firewall.js?v=' + version ]
				} ]);
			}
		}
	}).state('cf.route', {
		url : "/cf_route",
		data: { pageTitle: '路由器'},
		template:'<div ng-controller="cmdbrouteCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/route.js?v=' + version ]
				} ]);
			}
		}
	}).state('cf.switch', {
		url : "/cf_sw",
		data: { pageTitle: '交换机'},
		template:'<div ng-controller="cmddbswCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/sw.js?v=' + version ]
				} ]);
			}
		}
	}).state('cf.storage', {
		url : "/cf_storage",
		data: { pageTitle: '存储设备'},
		template:'<div ng-controller="cmddbstorageCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/storage.js?v=' + version ]
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
