function config_shop($stateProvider, $ocLazyLoadProvider) {
	console.log("App Shop config");
	$ocLazyLoadProvider.config({
		debug : true
	});
	// 我
	$stateProvider.state('me', {
		abstract : true,
		url : "/me",
		templateUrl : "views/common/content.html"
	}).state('me.profile', {
		url : "/profile",
		templateUrl : "views/me/profile.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/me/profile.css?v=' + version ]
				} ,{
					serie : true,
					files : [ 'views/me/profile.js?v=' + version ]
				} ]);
			}
		}
	});

	// 首页
	$stateProvider.state('index', {
		abstract : true,
		url : "/index",
		templateUrl : "views/common/content.html"
	}).state('index.ct', {
		url : "/ct",
		templateUrl : "views/index/content.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/index/content.js?v=' + version ]
				} ]);
			}
		}
	});
	
	// 分类
	$stateProvider.state('cat', {
		abstract : true,
		url : "/cat",
		templateUrl : "views/common/content.html"
	}).state('cat.ct', {
		url : "/ct",
		templateUrl : "views/cat/cat.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cat/cat.css?v=' + version ]
				} ,{
					serie : true,
					files : [ 'views/cat/cat.js?v=' + version ]
				} ]);
			}
		}
	});
	
	// 购物车
	$stateProvider.state('shop', {
		abstract : true,
		url : "/shop",
		templateUrl : "views/common/content.html"
	}).state('shop.ct', {
		url : "/ct",
		templateUrl : "views/shop/shop.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/shop/shop.css?v=' + version ]
				} ,{
					serie : true,
					files : [ 'views/shop/shop.js?v=' + version ]
				} ]);
			}
		}
	});
	
	//产品
	$stateProvider.state('product', {
		abstract : true,
		url : "/product",
		templateUrl : "views/common/content.html"
	}).state('product.info', {
		url : "/info",
		templateUrl : "views/product/product.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/product/product.js?v=' + version ]
				} ]);
			}
		}
	});
	
}
