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
				url : "/app",
				data : {
					pageTitle : '应用设置'
				},
				templateUrl : "views/wx/app.html",
				resolve : {
					loadPlugin : function($ocLazyLoad) {
						return $ocLazyLoad.load([{
									serie : true,
									files : ['views/wx/app.js?v=' + version]
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
				templateUrl : "views/wx/imgtext.html",
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
				templateUrl : "views/wx/setting.html",
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
					pageTitle : '消息图片'
				},
				templateUrl : "views/wx/msgimg.html",
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
