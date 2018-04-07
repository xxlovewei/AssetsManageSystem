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
		data: { pageTitle: '节点管理'},
		templateUrl : "views/om/hostnode/hostmgr.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/om/hostnode/hostmgr.js?v=' + version ]
				} ]);
			}
		}
	}).state('hostnode.templ', {
		url : "/hostnode_hostmgr",
		data: { pageTitle: '节点模版'},
		templateUrl : "views/om/hostnode/templ.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/om/hostnode/templ.js?v=' + version ]
				} ]);
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
		data: { pageTitle: '服务节点'},
		templateUrl : "views/om/ser/node.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/om/ser/node.js?v=' + version ]
				} ]);
			}
		}
	}).state('servicemgr.servicesetting', {
		url : "/servicemgr_servicesetting",
		data: { pageTitle: '服务设置'},
		templateUrl : "views/om/ser/ser.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/om/ser/ser.js?v=' + version ]
				} ]);
			}
		}
	}); 
 
	
	
}
