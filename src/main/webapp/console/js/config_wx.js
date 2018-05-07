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
			}).state('wxmgr.menu', {
				url : "/menu",
				templateUrl : "views/wx/app.html",
				resolve : {
					loadPlugin : function($ocLazyLoad) {
						return $ocLazyLoad.load([{
									serie : true,
									files : ['views/wx/app.js?v=' + version]
								}]);
					}
				}
			})
}
