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
	}).state('servicemgr.nodemetric', {
		url : "/servicemgr_nodemetric",
		data: { pageTitle: '节点度量'},
		templateUrl : "views/om/ser/nodemetric.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/om/ser/nodemetric.js?v=' + version ]
				} ]);
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
		data: { pageTitle: '度量设置'},
		templateUrl : "views/om/metric/metric.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/om/metric/metric.js?v=' + version ]
				} ]);
			}
		}
	}).state('metricmgr.metrictempl', {
		url : "/metricmgr_metrictempl",
		data: { pageTitle: '度量模版'},
		templateUrl : "views/om/metric/templ.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/om/metric/templ.js?v=' + version ]
				} ]);
			}
		}
	}).state('metricmgr.metricmapping', {
		url : "/metricmgr_metricmapping",
		data: { pageTitle: '度量模版'},
		templateUrl : "views/om/metric/mapping.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/om/metric/mapping.js?v=' + version ]
				} ]);
			}
		}
	}); 
 
	
	
}
