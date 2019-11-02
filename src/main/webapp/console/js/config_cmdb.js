function config_cmdb($stateProvider, $ocLazyLoadProvider) {
	console.log("App Shop config");
	$ocLazyLoadProvider.config({
		debug : true
	});
	
	// cmdb
	$stateProvider.state('maintain', {
		abstract : true,
		url : "/maintain",
		templateUrl : "views/common/content.html"
	}).state('maintain.faultrecord', {
		url : "/maintain_faultrecord",
		data: { pageTitle: '维护工作'},
		template:'<div ng-controller="cmdbfaultrecordCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/faultrecord.js?v=' + version ]
				} ]);
			}
		}
	}).state('maintain.devsearch', {
		url : "/maintain_devsearch",
		data: { pageTitle: '设备查询'},
		template:'<div ng-controller="cmdbdevsearchCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/devsearch.js?v=' + version ]
				} ]);
			}
		}
	}).state('maintain.review', {
		url : "/maintain_review",
		data: { pageTitle: '资产复核'},
		templateUrl : "views/cmdb/review.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/review.js?v=' + version ]
				} ]);
			}
		}
	})
	
 
		// 报表
	$stateProvider.state('cmdbresp', {
		abstract : true,
		url : "/cmdbresp",
		templateUrl : "views/common/content.html"
	}).state('cmdbresp.partzc', {
		url : "/maintain_partzc",
		data: { pageTitle: '部门资产'},
		template:'<div ng-controller="cmdbrepPartZcCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/rep/partzj.js?v=' + version ]
				} ]);
			}
		}
	}).state('cmdbresp.zctjshow', {
		url : "/cmdbresp_zctjshow",
		data: { pageTitle: '资产统计'},
		templateUrl : "views/cmdb/rep/zctj.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/rep/zctj.js?v=' + version ]
				} ]);
			}
		}
	});
	
	
	// cmdb
	$stateProvider.state('cf', {
		abstract : true,
		url : "/cf",
		templateUrl : "views/common/content.html"
	}).state('cf.server', {
		url : "/cf_server",
		data: { pageTitle: '服务器',classid:'server',input_type:"devservertype"},
		templateUrl : "views/cmdb/html_genericdev.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/js_genericdev.js?v=' + version ]
				} ]);
			}
		}
	}).state('cf.firewall', {
		url : "/cf_firewall",
		data: { pageTitle: '防火墙',classid:'firewall'},
		templateUrl : "views/cmdb/html_genericdev.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/js_genericdev.js?v=' + version ]
				} ]);
			}
		}
	}).state('cf.lightsw', {
		url : "/cf_lightsw",
		data: { pageTitle: '光交',classid:'lightsw'},
		templateUrl : "views/cmdb/html_genericdev.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/js_genericdev.js?v=' + version ]
				} ]);
			}
		}
	}).state('cf.compute', {
		url : "/cf_compute",
		data: { pageTitle: 'PC电脑',classid:'pc',input_type:"devcompute"},
		templateUrl : "views/cmdb/html_genericdev.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/js_genericdev.js?v=' + version ]
				} ]);
			}
		}
	}).state('cf.outlets', {
		url : "/cf_outlets",
		data: { pageTitle: '网点设备',classid:"pointdev",input_type:"devdotequipment"},
		templateUrl : "views/cmdb/html_genericdev.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/js_genericdev.js?v=' + version ]
				} ]);
			}
		}
	}).state('cf.route', {
		url : "/cf_route",
		data: { pageTitle: '路由设备',classid:'route'},
		templateUrl : "views/cmdb/html_genericdev.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/js_genericdev.js?v=' + version ]
				} ]);
			}
		}
	}).state('cf.safety', {
		url : "/cf_safety",
		data: { pageTitle: '安全设备',classid:'safety'},
		templateUrl : "views/cmdb/html_genericdev.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/js_genericdev.js?v=' + version ]
				} ]);
			}
		}
	}).state('cf.storage', {
		url : "/cf_storage",
		data: { pageTitle: '存储设备',classid:'storage'},
		templateUrl : "views/cmdb/html_genericdev.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/js_genericdev.js?v=' + version ]
				} ]);
			}
		}
	})
	.state('cf.switch', {
		url : "/cf_switch",
		data: { pageTitle: '交换机',classid:"switch"},
		templateUrl : "views/cmdb/html_genericdev.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/js_genericdev.js?v=' + version ]
				} ]);
			}
		}
	}).state('cf.bjpj', {
		url : "/cf_bjpj",
		data: { pageTitle: 'IT备件配件',classid:'bjpj',input_type:"devbjpj"},
		templateUrl : "views/cmdb/html_genericdev.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/js_genericdev.js?v=' + version ]
				} ]);
			}
		}
	}).state('cf.zcotherhard', {
		url : "/cf_zcotherhard",
		data: { pageTitle: '其他设备',classid:"zcotherhard",input_type:"zcother"},
		templateUrl : "views/cmdb/html_genericdev.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/cmdb/js_genericdev.js?v=' + version ]
				} ]);
			}
		}
	}).
	state('cf.outercontact', {
		url : "/cf_outercontact",
		data: { pageTitle: '外部联系人'},
		templateUrl : "views/system/cmdb/outercontact.html",
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/cmdb/outercontact.js?v=' + version ]
				} ]);
			}
		}
	}).state('cf.systemlist', {
		url : "/ct_systemlist",
		data: { pageTitle: '信息系统清单'},
		template:'<div ng-controller="cmdbsystemListCtl" ng-include="\'views/Template/simpleToolTableTempl.html\'"></div>',
		resolve : {
			loadPlugin : function($ocLazyLoad) {
				return $ocLazyLoad.load([ {
					serie : true,
					files : [ 'views/system/cmdb/systemlist.js?v=' + version ]
				} ]);
			}
		}
	});
	
	
	
}

function renderName(data, type, full) {
	var html = full.model;
	return html;

}

function renderJg(data, type, full) {
	var html="";
	if(angular.isDefined( full.rackstr)){
		 html = html+full.rackstr+"-";
	}
	if(angular.isDefined( full.frame)){
		 html = html+full.frame;
	}
	return html;
}

function renderReview(data, type, full) {
	if (data == "reviewed") {
		return "已复核"
	} else {
		return "未复核"
	}
}

function renderWb(data, type, full) {
	if (angular.isDefined(full.wb)) {
		if (full.wb == "valid") {
			return full.wbstr
		} else {
			return "<span style=\"color:red\">" + full.wbstr + "</span>"
		}
	} else {
		return "";
	}
}
function loadOpt(modal_meta, gdicts) {

 
	var item = modal_meta.meta.item;
	console.log("LoadOpt,Item:",item);

	// 脱保
	modal_meta.meta.tbSel = modal_meta.meta.tbOpt[0];
	if (angular.isDefined(item.wb_auto)) {
		if (item.wb_auto == "0") {
			modal_meta.meta.tbSel = modal_meta.meta.tbOpt[1];
		}
	}
	// 品牌
	modal_meta.meta.pinpOpt = gdicts.devbrand;
	if (modal_meta.meta.pinpOpt.length > 0) {
		if (angular.isDefined(item) && angular.isDefined(item.brand)) {
			for (var i = 0; i < gdicts.devbrand.length; i++) {
			 
				if (modal_meta.meta.pinpOpt[i].dict_item_id == item.brand) {
					modal_meta.meta.pinpSel = modal_meta.meta.pinpOpt[i];
				}
			}
		} else {
			if (gdicts.devbrand.length > 0) {
// modal_meta.meta.pinpSel = modal_meta.meta.pinpOpt[0];
			}
		}
	}

	// 部门
	modal_meta.meta.partOpt = gdicts.parts;
	if (gdicts.parts.length > 0) {
		if (angular.isDefined(item) && angular.isDefined(item.part_id)) {
			for (var i = 0; i < gdicts.parts.length; i++) {
				if (gdicts.parts[i].partid == item.part_id) {
					modal_meta.meta.partSel = gdicts.parts[i];
				}
			}
		} else {
			if (gdicts.parts.length > 0) {
				// modal_meta.meta.partSel = gdicts.parts[0];
			}
		}
	}

	// 使用人
	modal_meta.meta.usedunameOpt = gdicts.partusers;
	if (gdicts.partusers.length > 0) {
		if (angular.isDefined(item) && angular.isDefined(item.used_userid)) {
			for (var i = 0; i < gdicts.partusers.length; i++) {
				if (gdicts.partusers[i].user_id == item.used_userid) {
					modal_meta.meta.usedunameSel = gdicts.partusers[i];
				}
			}
		} else {
			if (gdicts.partusers.length > 0) {
				// modal_meta.meta.usedunameSel = gdicts.partusers[0];
			}
		}
	}

	// 风险等级
	modal_meta.meta.riskOpt = gdicts.devrisk;
	if (gdicts.devrisk.length > 0) {
		if (angular.isDefined(item) && angular.isDefined(item.risk)) {
			for (var i = 0; i < gdicts.devrisk.length; i++) {
				if (gdicts.devrisk[i].dict_item_id == item.risk) {
					modal_meta.meta.riskSel = gdicts.devrisk[i];
				}
			}
		} else {
			if (gdicts.devrisk.length > 0) {
				modal_meta.meta.riskSel = gdicts.devrisk[0];
			}
		}
	}

	// 环境
	modal_meta.meta.envOpt = gdicts.devenv;
	if (gdicts.devenv.length > 0) {
		if (angular.isDefined(item) && angular.isDefined(item.env)) {
			for (var i = 0; i < gdicts.devenv.length; i++) {
				if (gdicts.devenv[i].dict_item_id == item.env) {
					modal_meta.meta.envSel = gdicts.devenv[i];
				}
			}
		} else {
			if (gdicts.devenv.length > 0) {
				// modal_meta.meta.envSel = gdicts.devenv[0];
			}
		}
	}

	// 状态
	modal_meta.meta.recycelOpt = gdicts.devrecycle;
	if (gdicts.devrecycle.length > 0) {
		if (angular.isDefined(item) && angular.isDefined(item.recycle)) {
			for (var i = 0; i < gdicts.devrecycle.length; i++) {
				if (gdicts.devrecycle[i].dict_item_id == item.recycle) {
					modal_meta.meta.recycelSel = gdicts.devrecycle[i];
				}
			}
		} else {
			if (gdicts.devrecycle.length > 0) {
				modal_meta.meta.recycelSel = gdicts.devrecycle[0];
			}
		}
	}

	// 维保
	modal_meta.meta.wbOpt = gdicts.devwb;
	if (gdicts.devwb.length > 0) {
		if (angular.isDefined(item) && angular.isDefined(item.wb)) {
			for (var i = 0; i < gdicts.devwb.length; i++) {
				if (gdicts.devwb[i].dict_item_id == item.wb) {
					modal_meta.meta.wbSel = gdicts.devwb[i];
				}
			}
		} else {
			if (gdicts.devwb.length > 0) {
				modal_meta.meta.wbSel = gdicts.devwb[0];
			}
		}
	}

	// 位置
	modal_meta.meta.locOpt = gdicts.devdc;
	if (gdicts.devdc.length > 0) {
		if (angular.isDefined(item) && angular.isDefined(item.loc)) {
			for (var i = 0; i < gdicts.devdc.length; i++) {
				if (gdicts.devdc[i].dict_item_id == item.loc) {
					modal_meta.meta.locSel = gdicts.devdc[i];
				}
			}
		} else {
			if (gdicts.devdc.length > 0) {
				modal_meta.meta.locSel = gdicts.devdc[0];
			}
		}
	}

	// 类型
	modal_meta.meta.typeOpt = gdicts.stype;
	if (gdicts.stype.length > 0) {
		if (angular.isDefined(item) && angular.isDefined(item.type)) {
			for (var i = 0; i < gdicts.stype.length; i++) {
				if (gdicts.stype[i].dict_item_id == item.type) {
					modal_meta.meta.typeSel = gdicts.stype[i];
				}
			}
		} else {
			if (gdicts.stype.length > 0) {
				modal_meta.meta.typeSel = gdicts.stype[0];
			}
		}
	}

	// 机柜
	modal_meta.meta.jgOpt = gdicts.devrack;
	if (gdicts.devrack.length > 0) {
		if (angular.isDefined(item) && angular.isDefined(item.rack)) {
			for (var i = 0; i < gdicts.devrack.length; i++) {
				if (gdicts.devrack[i].dict_item_id == item.rack) {
					modal_meta.meta.jgSel = gdicts.devrack[i];
				}
			}
		} else {
			if (gdicts.devrack.length > 0) {
				// modal_meta.meta.jgSel = gdicts.devrack[0];
			}
		}
	}

	
}