function config_ops($stateProvider, $ocLazyLoadProvider) {
	console.log("App ops config");
	$ocLazyLoadProvider.config({
		debug : true
	});

	// 管理
	$stateProvider.state('infosys', {
		abstract : true,
		url : "/infosys",
		templateUrl : "views/common/content.html?v="+version
	}).state('infosys.host', {
		url : "infosys_host",
		data: { pageTitle: '主机管理'},
		templateUrl : "views/ops/hostmgr.html?v="+version,
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/ops/hostmgr.js?v=' + version ]
				} ]);
			}
		}
	})

}