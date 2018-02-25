function config_om($stateProvider, $ocLazyLoadProvider) {
	console.log("App Om config");
	$ocLazyLoadProvider.config({
		debug : false
	});
	
	// 主机节点
	$stateProvider.state('hostnode', {
		abstract : true,
		url : "/hostnode",
		templateUrl : "views/common/content.html"
	}).state('hostnode.hostmgr', {
		url : "/hostnode_hostmgr",
		data: { pageTitle: '主机节点管理'},
		templateUrl : "views/om/hostnode/hostmgr.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/om/hostnode/hostmgr.js?v=' + version ]
				} ]);
			}
		}
	}); 
 
	
	
}
