function config_shop($stateProvider, $ocLazyLoadProvider) {
	console.log("App Shop config");
	$ocLazyLoadProvider.config({
		debug : false
	});
	// 产品
	$stateProvider.state('product', {
		abstract : true,
		url : "/product",
		templateUrl : "views/common/content.html"
	}).state('product.prod_publish', {
		url : "/prod_publish",
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
		templateUrl : "views/common/content.html"
	}).state('shop.shop_list', {
		url : "/shop_list",
		templateUrl : "views/shop/shop.html",
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
		templateUrl : "views/shop/myshop.html",
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
		templateUrl : "views/common/content.html"
	}).state('pinp.pinp_mgr', {
		url : "/pinp_mgr",
		templateUrl : "views/product/pinp/pinp.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/product/pinp/pinp.js?v=' + version ]
				} ]);
			}
		}
	});
	

	// 品牌
	$stateProvider.state('mallmgr', {
		abstract : true,
		url : "/mallmgr",
		templateUrl : "views/common/content.html"
	}).state('mallmgr.banner', {
		url : "/mgr",
		templateUrl : "views/mall/banner.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/mall/banner.js?v=' + version ]
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
		url : "/fcat_group",
		templateUrl : "views/product/fcat/rootfcat.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/product/fcat/rootfcat.js?v=' + version ]
				} ]);
			}
		}
	});
}
