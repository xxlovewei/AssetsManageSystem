function config_shop($stateProvider, $ocLazyLoadProvider) {
	console.log("App Shop config");
	$ocLazyLoadProvider.config({
		debug : true
	});
	
	// 产品
	$stateProvider.state('product', {
		abstract : true,
		url : "/product",
		templateUrl : "views/common/content.html"
	}).state('product.prod_publish', {
		url : "/prod_publish",
		data: { pageTitle: '产品发布'},
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
		data: { pageTitle: '产品查询'},
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
		data: { pageTitle: '店铺列表'},
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
		data: { pageTitle: '我的店铺'},
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
		data: { pageTitle: '品牌设置'},
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
	

	// 商城
	$stateProvider.state('mallmgr', {
		abstract : true,
		url : "/mallmgr",
		templateUrl : "views/common/content.html"
	}).state('mallmgr.banner', {
		url : "/mgr",
		data: { pageTitle: 'Banner设置'},
		templateUrl : "views/mall/banner.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/mall/banner.js?v=' + version ]
				} ]);
			}
		}
	}).state('mallmgr.notice', {
		url : "/notice",
		data: { pageTitle: '公告设置'},
		templateUrl : "views/mall/notice.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/mall/notice.js?v=' + version ]
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
		data: { pageTitle: '前台类目'},
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
		data: { pageTitle: '后台类目'},
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
		data: { pageTitle: '前台类目组'},
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
	}).state('cat.indexclass', {
		url : "/indexclass",
		data: { pageTitle: '首页分类'},
		templateUrl : "views/mall/indexclass.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/mall/indexclass.js?v=' + version ]
				} ]);
			}
		}
	});
	
	
}
