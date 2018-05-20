function config_mshop($stateProvider, $ocLazyLoadProvider) {
	console.log("App MShop config");
	$ocLazyLoadProvider.config({
		debug : true
	});
	
	// 商品
	$stateProvider.state('prod', {
		abstract : true,
		url : "/prod",
		templateUrl : "views/common/content.html"
	}).state('prod.dl', {
		url : "/prod_dl",
		data: { pageTitle: '商品大类'},
		templateUrl : "views/mshop/prod/dl.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/mshop/prod/dl.js?v=' + version ]
				} ]);
			}
		}
	}).state('prod.xl', {
		url : "/prod_xl",
		data: { pageTitle: '商品小类'},
		templateUrl : "views/mshop/prod/xl.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/mshop/prod/xl.js?v=' + version ]
				} ]);
			}
		}
	}).state('prod.prodquery', {
		url : "/prod_prodquery",
		data: { pageTitle: '商品查询'},
		templateUrl : "views/mshop/prod/prodquery.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/mshop/prod/prodquery.js?v=' + version ]
				} ]);
			}
		}
	});

 
	
	
}
