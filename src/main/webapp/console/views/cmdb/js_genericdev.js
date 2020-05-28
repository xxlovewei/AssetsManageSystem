 function modalresBatchUpdateCtl($timeout, $localStorage, notify, $log, $uibModal,
		$uibModalInstance, $scope, meta, $http, $rootScope,  
		$compile) {
	var tgdict=meta.gdicts;
	$scope.item={};
	$scope.item.ids=meta.selrows;
	$scope.date = {
			buytime2 : moment().subtract(15, "days"),
			wboutdate2 : moment().add(1, 'days')
		}
	$scope.ifrecycleOpt=[{id:"N",name:"不更"},{id:"Y",name:"更新"}];
	$scope.ifrecycleSel=$scope.ifrecycleOpt[0];
	$scope.recycleOpt=[];
	$scope.recycleSel="";
	if(angular.isDefined(tgdict.devrecycle)){
		$scope.recycleOpt=tgdict.devrecycle;
		if(tgdict.devrecycle.length>0){
			$scope.recycleSel=tgdict.devrecycle[0];
		}
	}
	
	$scope.ifriskOpt=[{id:"N",name:"不更"},{id:"Y",name:"更新"}];
	$scope.ifriskSel=$scope.ifriskOpt[0];
	$scope.riskOpt=[];
	$scope.riskSel="";
	if(angular.isDefined(tgdict.devrisk)){
		$scope.riskOpt=tgdict.devrisk;
		if(tgdict.devrisk.length>0){
			$scope.riskSel=tgdict.devrisk[0];
		}
	}
	
	$scope.ifenvOpt=[{id:"N",name:"不更"},{id:"Y",name:"更新"}];
	$scope.ifenvSel=$scope.ifenvOpt[0];
	$scope.envOpt=[];
	$scope.envSel="";
	if(angular.isDefined(tgdict.devenv)){
		$scope.envOpt=tgdict.devenv;
		if(tgdict.devenv.length>0){
			$scope.envSel=tgdict.devenv[0];
		}
	}
	
	$scope.ifwbOpt=[{id:"N",name:"不更"},{id:"Y",name:"更新"}];
	$scope.ifwbSel=$scope.ifwbOpt[0];
	$scope.wbOpt=[];
	$scope.wbSel="";
	if(angular.isDefined(tgdict.devwb)){
		$scope.wbOpt=tgdict.devwb;
		if(tgdict.devwb.length>0){
			$scope.wbSel=tgdict.devwb[0];
		}
	}
	
	$scope.ifusedPartOpt=[{id:"N",name:"不更"},{id:"Y",name:"更新"}];
	$scope.ifusedPartSel=$scope.ifusedPartOpt[0];
	$scope.partOpt=[];
	$scope.partSel="";
	if(angular.isDefined(tgdict.parts)){
		$scope.partOpt=tgdict.parts;
		if(tgdict.parts.length>0){
			$scope.partSel=tgdict.parts[0];
		}
	}

	$scope.ifusedUserOpt=[{id:"N",name:"不更"},{id:"Y",name:"更新"}];
	$scope.ifusedUserSel=$scope.ifusedUserOpt[0];
	$scope.usedunameOpt=[];
	$scope.usedunameSel="";
	if(angular.isDefined(tgdict.partusers)){
		$scope.usedunameOpt=tgdict.partusers;
		if(tgdict.partusers.length>0){
			$scope.usedunameSel=tgdict.partusers[0];
		}
	}
	
	$scope.iflocOpt=[{id:"N",name:"不更"},{id:"Y",name:"更新"}];
	$scope.iflocSel=$scope.iflocOpt[0];
	$scope.locOpt=[];
	$scope.locSel="";
	if(angular.isDefined(tgdict.devdc)){
		$scope.locOpt=tgdict.devdc;
		if(tgdict.devdc.length>0){
			$scope.locSel=tgdict.devdc[0];
		}
	}
	
	$scope.ifbuyOpt=[{id:"N",name:"不更"},{id:"Y",name:"更新"}];
	$scope.ifbuySel=$scope.ifbuyOpt[0];
	
	$scope.ifTbOpt=[{id:"N",name:"不更"},{id:"Y",name:"更新"}];
	$scope.ifTbSel=$scope.ifTbOpt[0];
	
	$scope.iftbComputeOpt=[{id:"N",name:"不更"},{id:"Y",name:"更新"}];
	$scope.iftbComputeSel=$scope.iftbComputeOpt[0];
	$scope.tbOpt=[];
	$scope.tbSel="";
	if(angular.isDefined(tgdict.zcwbcomoute)){
		$scope.tbOpt=tgdict.zcwbcomoute;
		if(tgdict.zcwbcomoute.length>0){
			$scope.tbSel=tgdict.zcwbcomoute[0];
		}
	}
	 
	
	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

	$scope.sure = function() {
		 
		$scope.item.ifrecycleSel=$scope.ifrecycleSel.id;
		$scope.item.recycleSel=$scope.recycleSel.dict_item_id;
		
		$scope.item.ifriskSel=$scope.ifriskSel.id;
		$scope.item.riskSel=$scope.riskSel.dict_item_id;
		
		$scope.item.ifenvSel=$scope.ifenvSel.id;
		$scope.item.envSel=$scope.envSel.dict_item_id;

		$scope.item.ifwbSel=$scope.ifwbSel.id;
		$scope.item.wbSel=$scope.wbSel.dict_item_id;
		
		$scope.item.ifusedPartSel=$scope.ifusedPartSel.id;
		$scope.item.partSel=$scope.partSel.partid;
		
		$scope.item.ifusedUserSel=$scope.ifusedUserSel.id;
		$scope.item.usedunameSel=$scope.usedunameSel.user_id;
		
		$scope.item.ifbuySel=$scope.ifbuySel.id;
		$scope.item.buy_time_f = $scope.date.buytime2.format('YYYY-MM-DD');
		
		$scope.item.ifTbSel=$scope.ifTbSel.id;
		$scope.item.wbout_date_f = $scope.date.wboutdate2.format('YYYY-MM-DD');
		
		$scope.item.iftbComputeSel=$scope.iftbComputeSel.id;
		$scope.item.tbSel=$scope.tbSel.dict_item_id;
		
		$scope.item.iflocSel=$scope.iflocSel.id;
		$scope.item.locSel=$scope.locSel.dict_item_id;


		
		$http
		.post(
				$rootScope.project
						+ "/api/base/res/batchUpdateRes.do",
						$scope.item)
		.success(function(res) {
			if (res.success) {
				$uibModalInstance.close('OK');
			} else {
				notify({
					message : res.message
				});
			}
		});
 
	}
}


function genericdevCtl(DTOptionsBuilder, DTColumnBuilder, $compile, $confirm,
		$log, notify,$scope, $http, $rootScope, $uibModal, $window,$state,$timeout) {
 
	var pbtns=$rootScope.curMemuBtns;

	var gclass_id ="";
	var gdicts = {};
	$http.post($rootScope.project + "/api/sysParams/selectById.do", {id: $state.router.globals.current.data.classid})
	.success(function(res) {
		if (res.success) {
			if(!angular.isDefined(res.data)){
				notify({
					message : "未成功获取大类编码,请先设置参数"
				});
			}
			gclass_id=res.data.value;
			var dicts = "zcwbcomoute,devbrand,devrisk,devenv,devrecycle,devwb,devdc,devrack,zcsource,zcwbsupper,zcsupper";
			// 判断输入框
			var subclass="N";
			if (angular.isDefined($state.router.globals.current.data.subclass)) {
				subclass=gclass_id;
			}

			var t=$state.router.globals.current.data.classid;
			var t2 = t.replace( "_" , "" );
			$http
					.post($rootScope.project + "/api/zc/queryDictFast.do", {
						uid:"generic"+t2,
						dicts : dicts,
						parts : "Y",
						partusers : "Y",
						comp :"Y",
						belongcomp:"Y",
						subclass:subclass
					})
					.success(
							function(res) {
								if (res.success) {
									gdicts = res.data;
									// 资产大类
									if (!angular
											.isDefined($state.router.globals.current.data.subclass)) {
										gdicts.btype = [];
									}else{
									}
									// 未使用
									gdicts.stype = [];
									
									// 填充行数据
									var tenv = [];
									angular.copy(gdicts.devenv, tenv);
									var twb = [];
									angular.copy(gdicts.devwb, twb);
									var tloc = [];
									angular.copy(gdicts.devdc, tloc);
									var trecycle = [];
									angular.copy(gdicts.devrecycle, trecycle);
									var parts = [];
									angular.copy(gdicts.parts, parts);
									var partusers = [];
									angular.copy(gdicts.partusers, partusers);
									
									tloc.unshift({
										dict_item_id : "all",
										name : "全部"
									});
									$scope.meta.tools[0].dataOpt = tloc;
									$scope.meta.tools[0].dataSel = tloc[0];
									
									tenv.unshift({
										dict_item_id : "all",
										name : "全部"
									});
									$scope.meta.tools[1].dataOpt = tenv;
									$scope.meta.tools[1].dataSel = tenv[0];
									
									twb.unshift({
										dict_item_id : "all",
										name : "全部"
									});
									$scope.meta.tools[2].dataOpt = twb;
									$scope.meta.tools[2].dataSel = twb[0];
									
									trecycle.unshift({
										dict_item_id : "all",
										name : "全部"
									});
									$scope.meta.tools[3].dataOpt = trecycle;
									$scope.meta.tools[3].dataSel = trecycle[0];
									flush();
								} else {
									notify({
										message : res.message
									});
								}
							})
			
		} else {
			notify({
				message : res.message
			});
		}
	})
	
	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data').withDOM('frtlip')
			.withPaginationType('full_numbers').withDisplayLength(100)
			.withOption("ordering", false).withOption("responsive", false)
			.withOption("searching", true).withOption('scrollY', 600)
			.withOption('scrollX', true).withOption('bAutoWidth', true)
			.withOption('scrollCollapse', true).withOption('paging', true)
			.withFixedColumns({
				leftColumns : 0,
				rightColumns : 0
			}).withOption('bStateSave', true).withOption('bProcessing', false)
			.withOption('bFilter', false).withOption('bInfo', false)
			.withOption('serverSide', false).withOption('createdRow', function(row) {
				$compile(angular.element(row).contents())($scope);
			}).withOption(
					'headerCallback',
					function(header) {
						if ((!angular.isDefined($scope.headerCompiled))
								|| $scope.headerCompiled) {
							$scope.headerCompiled = true;
							$compile(angular.element(header).contents())
									($scope);
						}
					}).withOption("select", {
				style : 'multi',
				selector : 'td:first-child' 
			}).withButtons([
	            {
	                extend: 'colvis',
	                text: '显示隐藏列',
	                fnLabel: function ( dt, idx, title ) {
	      
	                    return (idx+1)+': '+title;
	                } 
	            },
	            {
	            	extend:'csv',
	                text: 'Excel(当前页)',
	                exportOptions: {
	                	columns: ':visible',
	                	trim:true,
	                    modifier: {
	                        page: 'current'
	                    }
	                }
	            },
	            {
	            	extend:'print',
	                text: '打印(当前页)',
	                exportOptions: {
	                	 columns: ':visible',
	                	 stripHtml: false,
	                	 columns: ':visible',
	                	 modifier: {
		                        page: 'current'
		                    }
	                }
	            }
	        ]);

	function stateChange(iColumn, bVisible) {
        
    }
	$scope.dtInstance = {}
	$scope.selectCheckBoxAll = function(selected) {
		if (selected) {
			$scope.dtInstance.DataTable.rows().select();
		} else {
			$scope.dtInstance.DataTable.rows().deselect();
		 
		}
	}

	$scope.dtColumns = [];
	$scope.dtColumns=zcBaseColsCreate(DTColumnBuilder,'withselect');
	$scope.dtColumns.push(DTColumnBuilder.newColumn('riskstr').withTitle('风险等级').withOption(
		'sDefaultContent', '').withOption('width', '30'));
	$scope.dtColumns.push( DTColumnBuilder.newColumn('envstr').withTitle('运行环境').withOption(
		'sDefaultContent', '').withOption('width', '30'));
	$scope.dtColumns.push( DTColumnBuilder.newColumn('ip').withTitle('IP').withOption(
		'sDefaultContent', '').withOption('width', '50')
	);

	$scope.query = function() {
		flush();
	}

	var meta = {
		tablehide : false,
		toolsbtn : [
				{
					id : "btn",
					label : "",
					type : "btn",
					show:true,
					template : ' <button ng-click="query()" class="btn btn-sm btn-primary" type="submit">搜索</button>'
				},
				{
					id : "btn2",
					label : "",
					type : "btn",
					show:false,
					priv:"insert",
					template : ' <button ng-click="save(0)" class="btn btn-sm btn-primary" type="submit">入库</button>'
				},
				{
					id : "btn3",
					label : "",
					type : "btn",
					show:false,
					priv:"update",
					template : ' <button ng-click="save(1)" class="btn btn-sm btn-primary" type="submit">更新</button>'
				},				
				{
					id : "btn4",
					label : "",
					type : "btn",
					show:false,
					priv:"item_update",
					template : ' <button ng-click="batchupate()" class="btn btn-sm btn-primary" type="submit">批量更新</button>'
				},

				{
					id : "btn5",
					label : "",
					type : "btn",
					show:false,
					priv:"detail",
					template : ' <button ng-click="detail()" class="btn btn-sm btn-primary" type="submit">详情</button>'
				},
				{
					id : "btn6",
					label : "",
					type : "btn",
					show:false,
					priv:"remove",
					template : ' <button ng-click="del()" class="btn btn-sm btn-primary" type="submit">删除</button>'
				},
				{
					id : "btn7",
					label : "",
					type : "btn",
					show:false,
					priv:"exportfile",
					template : ' <button ng-click="filedown()" class="btn btn-sm btn-primary" type="submit">全部导出(Excel)</button>'
				} ],
		tools : [ {
			id : "select",
			label : "区域",
			type : "select",
			disablesearch : true,
			show:true,
			dataOpt : [],
			dataSel : ""
		}, {
			id : "select",
			label : "环境",
			type : "select",
			disablesearch : true,
			show:true,
			dataOpt : [],
			dataSel : ""
		}, {
			id : "select",
			label : "维保",
			type : "select",
			disablesearch : true,
			show:true,
			dataOpt : [],
			dataSel : ""
		}, {
			id : "select",
			label : "状态",
			type : "select",
			disablesearch : true,
			show:true,
			dataOpt : [],
			dataSel : ""
		}
		]
	};
 
	$scope.meta=meta;
	privNormalCompute($scope.meta.toolsbtn, pbtns);

	function flush() {
		var ps = {}
		ps.class_id = gclass_id;
		ps.loc = $scope.meta.tools[0].dataSel.dict_item_id;
		ps.env = $scope.meta.tools[1].dataSel.dict_item_id;
		ps.wb = $scope.meta.tools[2].dataSel.dict_item_id;
		ps.recycle = $scope.meta.tools[3].dataSel.dict_item_id;
		// ps.search = $scope.meta.tools[4].ct;
		$http.post($rootScope.project + "/api/base/res/queryResAllByClass.do", ps)
				.success(function(res) {
					if (res.success) {
						$scope.dtOptions.aaData = res.data;
					} else {
						notify({
							message : res.message
						});
					}
				})
				 	
	}

	$scope.filedown = function() {
		var ps = {}
		ps.id = gclass_id;
		ps.loc = $scope.meta.tools[0].dataSel.dict_item_id;
		ps.env = $scope.meta.tools[1].dataSel.dict_item_id;
		ps.wb = $scope.meta.tools[2].dataSel.dict_item_id;
		ps.recycle = $scope.meta.tools[3].dataSel.dict_item_id;
		$window.open($rootScope.project
				+ "/api/base/res/exportServerData.do?id=" + ps.id + "&loc="
				+ ps.loc + "&env=" + ps.env + "&wb=" + ps.wb + "&recycle="
				+ ps.recycle );
	}

					
					
					
	function getSelectRows() {
		var data = $scope.dtInstance.DataTable.rows({
			selected : true
		})[0];
		if (data.length == 0) {
			notify({
				message : "请至少选择一项"
			});
			return;
		} else if (data.length > 1000) {
			notify({
				message : "不允许超过1000个"
			});
			return;
		} else {
			var res = [];
		 
			for (var i = 0; i < data.length; i++) {
				res.push($scope.dtOptions.aaData[data[i]].id)
			}
			return angular.toJson(res);
		}
	}	
	$scope.batchupate=function(){
	
		var selrows=getSelectRows();
		var ps={};
		if (angular.isDefined(selrows)) {
			ps.selrows=selrows;
			ps.gdicts=gdicts;
			var modalInstance = $uibModal.open({
				backdrop : true,
				templateUrl : 'views/cmdb/modal_batchUpdateRes.html',
				controller : modalresBatchUpdateCtl,
				size : 'blg',
				resolve : {
					meta : function() {
						return ps;
					}
				}
			});

			modalInstance.result.then(function(result) {

				if (result == "OK") {
					flush();
				}
		 
 
			}, function(reason) {

				$log.log("reason", reason)
			}); 
		}
	}

	$scope.del = function() {
		var selrows=getSelectRows();
	 
		if (angular.isDefined(selrows)) {
			$confirm({
				text : '是否删除?'
			}).then(
					function() {
						$http.post(
								$rootScope.project
										+ "/api/base/res/deleteByIds.do", {
									ids : selrows
								}).success(function(res) {
							if (res.success) {
								flush();
							} else {
								notify({
									message : res.message
								});
							}
						});
					});
		}

	}

	$scope.detail = function() {
		var id = "";
		var selrow = getSelectRow();
		if (angular.isDefined(selrow)) {
			id = selrow.id;
		} else {
			return;
		}
		var ps = {};
		ps.id = id;
		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/cmdb/modal_dtl.html',
			controller : modalcmdbdtlCtl,
			size : 'blg',
			resolve : {
				meta : function() {
					return ps;
				}
			}
		});

		modalInstance.result.then(function(result) {

			if (result == "OK") {
			}
		}, function(reason) {

			$log.log("reason", reason)
		});

	}

	function getSelectRow() {
		var data = $scope.dtInstance.DataTable.rows({
			selected : true
		})[0];
		if (data.length == 0) {
			notify({
				message : "请至少选择一项"
			});
			return;
		} else if (data.length > 1) {
			notify({
				message : "请最多选择一项"
			});
			return;
		} else {
		 
			return $scope.dtOptions.aaData[data[0]];
		}
	}

	
	// //////////////////////////save/////////////////////
	$scope.save = function(type) {
		var id;
		if (type == 1) {
			var selrow = getSelectRow();
			if (angular.isDefined(selrow)) {
				id = selrow.id;
			} else {
				return;
			}
		}
		$http
				.post($rootScope.project + "/api/base/res/queryResAllById.do", {
					id : id,
					classId : gclass_id
				})
				.success(
						function(res) {
							if (!res.success) {
								notify({
									message : res.message
								});
								return;
							}
							var meta = {};

							var items = [];
							items.push({
								type : "input",
								disabled : "true",
								sub_type : "text",
								required : false,
								maxlength : "50",
								placeholder : "系统自动生成",
								label : "资产编号",
								need : false,
								name : 'uuid',
								ng_model : "uuid"
							});
							
							// 资产大类
							if (angular
									.isDefined($state.router.globals.current.data.subclass) || $state.router.globals.current.data.classid == "zcotherhard"  ) {
								items.push({
									type : "select",
									disabled : "false",
									label : "资产类型",
									need : false,
									disable_search : "true",
									dataOpt : "classOpt",
									dataSel : "classSel"
								});
							}

							items.push({
								type : "input",
								disabled : "false",
								sub_type : "text",
								required : true,
								maxlength : "50",
								placeholder : "请输型号",
								label : "规格型号",
								need : true,
								name : 'model',
								ng_model : "model"
							});
							items.push({
								type : "input",
								disabled : "false",
								sub_type : "text",
								required : false,
								maxlength : "50",
								placeholder : "请输入序列号",
								label : "序列号",
								need : false,
								name : 'sn',
								ng_model : "sn"
							});
							items.push( {
								type : "select",
								disabled : "false",
								label : "资产来源",
								need : true,
								disable_search : "true",
								dataOpt : "zcsourceOpt",
								dataSel : "zcsourceSel"
							});

							items.push( {
								type : "select",
								disabled : "false",
								label : "供应商",
								need : false,
								disable_search : "false",
								dataOpt : "zcsupperOpt",
								dataSel : "zcsupperSel"
							});
							items.push( {
								type : "select",
								disabled : "false",
								label : "资产品牌",
								need : false,
								disable_search : "false",
								dataOpt : "pinpOpt",
								dataSel : "pinpSel"
							});

							items.push({
								type : "input",
								disabled : "false",
								sub_type : "text",
								required : false,
								maxlength : "50",
								placeholder : "",
								label : "其他资产编号",
								need : false,
								name : 'fs20',
								ng_model : "fs20"
							});


							items.push( {
								type : "select",
								disabled : "false",
								label : "资产状态",
								need : true,
								disable_search : "true",
								dataOpt : "recycelOpt",
								dataSel : "recycelSel"
							});

							items.push({
								type : "input",
								disabled : "false",
								sub_type : "number",
								required : false,
								maxlength : "50",
								placeholder : "",
								label : "资产数量",
								need : false,
								name : 'zc_cnt',
								ng_model : "zc_cnt"
							});
							


							items.push( {
								type : "input",
								disabled : "false",
								sub_type : "text",
								required : false,
								maxlength : "50",
								placeholder : "请输入配置描述",
								label : "配置描述",
								need : false,
								name : 'confdesc',
								ng_model : "confdesc"
							});

							items.push({
								type : "input",
								disabled : "false",
								sub_type : "text",
								required : false,
								maxlength : "500",
								placeholder : "请输入备注",
								label : "备注",
								need : false,
								name : 'mark',
								ng_model : "mark"
							});

							items.push({
								type : "input",
								disabled : "false",
								sub_type : "text",
								required : false,
								maxlength : "50",
								placeholder : "请输入标签1",
								label : "标签1",
								need : false,
								name : 'fs1',
								ng_model : "fs1"
							});

							items.push({
								type : "input",
								disabled : "false",
								sub_type : "text",
								required : false,
								maxlength : "50",
								placeholder : "请输入标签",
								label : "标签2",
								need : false,
								name : 'fs2',
								ng_model : "fs2"
							});

							items.push({
								type : "dashed",
								name : 'model'
							});

							items.push({
								type : "select",
								disabled : "false",
								label : "所属公司",
								need : true,
								disable_search : "false",
								dataOpt : "belongcompOpt",
								dataSel : "belongcompSel"
							});

							items.push({
								type : "select",
								disabled : "false",
								label : "使用公司",
								need : true,
								disable_search : "false",
								dataOpt : "compOpt",
								dataSel : "compSel"
							});


							items.push({
								type : "select",
								disabled : "false",
								label : "使用部门",
								need : false,
								disable_search : "false",
								dataOpt : "partOpt",
								dataSel : "partSel"
							});

							items.push({
								type : "select",
								disabled : "false",
								label : "使用人",
								need : false,
								disable_search : "false",
								dataOpt : "usedunameOpt",
								dataSel : "usedunameSel"
							});


							items.push({
								type : "dashed",
								name : 'model'
							});


							items.push( {
								type : "select",
								disabled : "false",
								label : "风险等级",
								need : false,
								disable_search : "true",
								dataOpt : "riskOpt",
								dataSel : "riskSel"
							});

							items.push( {
								type : "select",
								disabled : "false",
								label : "运行环境",
								need : false,
								disable_search : "true",
								dataOpt : "envOpt",
								dataSel : "envSel"
							});
							items.push({
								type : "input",
								disabled : "false",
								sub_type : "text",
								required : false,
								maxlength : "50",
								placeholder : "请输入内容",
								label : "IP",
								need : false,
								name : 'ip',
								ng_model : "ip"
							});


							items.push({
								type : "dashed",
								name : 'model'
							});

							items.push( {
								type : "select",
								disabled : "false",
								label : "区域",
								need : true,
								disable_search : "false",
								dataOpt : "locOpt",
								dataSel : "locSel"
							});

							items.push({
								type : "select",
								disabled : "false",
								label : "机柜编号",
								need : false,
								disable_search : "true",
								dataOpt : "jgOpt",
								dataSel : "jgSel"
							});


							items.push({
								type : "input",
								disabled : "false",
								sub_type : "text",
								required : false,
								maxlength : "50",
								placeholder : "请输入机架编号",
								label : "机架编号",
								need : false,
								name : 'frame',
								ng_model : "frame"
							});

							items.push({
								type : "input",
								disabled : "false",
								sub_type : "text",
								required : false,
								maxlength : "50",
								placeholder : "请输入详细位置",
								label : "详细位置",
								need : false,
								name : 'locdtl',
								ng_model : "locdtl"
							});

							items.push({
								type : "dashed",
								name : 'model'
							});

							items.push({
								type : "datetime",
								disabled : "false",
								label : "采购时间",
								false : true,
								ng_model : "buytime"
							});
							items.push({
								type : "input",
								disabled : "false",
								sub_type : "number",
								required : false,
								maxlength : "30",
								placeholder : "请输入采购价格",
								label : "采购总额",
								need : false,
								name : 'buy_price',
								ng_model : "buy_price"
							});
							items.push({
								type : "input",
								disabled : "false",
								sub_type : "number",
								required : false,
								maxlength : "30",
								placeholder : "请输入资产净值",
								label : "资产净值",
								need : false,
								name : 'net_worth',
								ng_model : "net_worth"
							});


							items.push({
								type : "dashed",
								name : 'model'
							});

							items.push( {
								type : "select",
								disabled : "false",
								label : "维保供应商",
								need : true,
								disable_search : "false",
								dataOpt : "zcwbsupperOpt",
								dataSel : "zcwbsupperSel"
							});

							items.push( {
								type : "select",
								disabled : "false",
								label : "脱保计算",
								need : false,
								disable_search : "true",
								dataOpt : "tbOpt",
								dataSel : "tbSel"
							});
							items.push( {
								type : "datetime",
								disabled : "false",
								label : "脱保时间",
								need : false,
								ng_model : "wboutdate"
							});

							items.push( {
								type : "select",
								disabled : "false",
								label : "维保状态",
								false : true,
								disable_search : "true",
								dataOpt : "wbOpt",
								dataSel : "wbSel"
							});
							items.push({
								type : "input",
								disabled : "false",
								sub_type : "text",
								required : false,
								maxlength : "100",
								placeholder : "请输入内容",
								label : "维保说明",
								need : false,
								name : 'wbct',
								ng_model : "wbct"
							});



							items.push({
								type : "dashed",
								name : 'model'
							});

							items.push({
								type : "picupload",
								disabled : "false", 
								required : false,
								label : "图片",
								need : false,
								conf:"picconfig"
							});
							items.push({
								type : "fileupload",
								disabled : "false", 
								required : false,
								label : "附件",
								need : false,
								conf:"attachconfig"
							});
						 
					
					 
							
							var bt = moment().subtract(1, "days");
							var tbtime = moment();

							if (angular.isDefined(res.data)
									&& angular.isDefined(res.data.data)
									&& angular
											.isDefined(res.data.data.buy_timestr)) {
								bt = moment(res.data.data.buy_timestr);
							}
							if (angular.isDefined(res.data)
									&& angular.isDefined(res.data.data)
									&& angular
											.isDefined(res.data.data.wbout_datestr)) {
								tbtime = moment(res.data.data.wbout_datestr);
							}

							meta = {
								class_id : gclass_id,
								footer_hide : false,
								title : "资产-"+$state.router.globals.current.data.pageTitle,
								item : {},
								buytime : bt,
								typeOpt:[],
								typeSel:"",
								belongcompSel:"",
								compSel:"",
								wboutdate : tbtime,
								statusOpt : [],
								statusSel : "",
								pinpOpt : [],
								pinpSel : "",
								headuserOpt : [],
								headuserSel : "",
								partOpt : [],
								partSel : "",
								classOpt:[],
								classSel:[],
								usedunameOpt : [],
								usedunameSel : "",
								locOpt : [],
								locSel : "",
								wbOpt : [],
								wbSel : "",
								envOpt : [],
								tbOpt : [],
								tbSel : "",
								envSel : "",
								jgOpt : [],
								jgSel : "",
								riskOpt : [],
								riskSel : "",
								compOpt:[],
								compSel:"",
								picconfig : {
									url : 'fileupload.do',
									maxFilesize : 10000,
									paramName : "file",
									maxThumbnailFilesize : 6,
									// 一个请求上传多个文件
									uploadMultiple : true,
									// 当多文件上传,需要设置parallelUploads>=maxFiles
									parallelUploads : 6,
									maxFiles : 6,
									dictDefaultMessage : "点击上传图片",
									acceptedFiles : "image/jpeg,image/png,image/gif",
									// 添加上传取消和删除预览图片的链接，默认不添加
									addRemoveLinks : true,
									// 关闭自动上传功能，默认会true会自动上传
									// 也就是添加一张图片向服务器发送一次请求
									autoProcessQueue : false,
									init : function() {
										$scope.myDropzonepic = this; 
									}
								},
								attachconfig:{
									url : 'fileupload.do',
									maxFilesize : 10000,
									paramName : "file",
									maxThumbnailFilesize : 1,
									// 一个请求上传多个文件
									uploadMultiple : true,
									// 当多文件上传,需要设置parallelUploads>=maxFiles
									parallelUploads : 1,
									maxFiles : 1,
									dictDefaultMessage : "点击上传附件",
									acceptedFiles : "image/jpeg,image/png,image/gif",
									// 添加上传取消和删除预览图片的链接，默认不添加
									addRemoveLinks : true,
									// 关闭自动上传功能，默认会true会自动上传
									// 也就是添加一张图片向服务器发送一次请求
									autoProcessQueue : false,
									init : function() {
										$scope.myDropzonefile = this; 
									}
								},
								items : items,
								sure : function(modalInstance, modal_meta) {
									
									console.log("pic",$scope.myDropzonepic);
									// 只允许传一张图片
									modal_meta.meta.item.img="";
									if($scope.myDropzonepic.files.length>0&&$scope.myDropzonepic.files.length==1){
										 var id = getUuid();
										  if (typeof ($scope.myDropzonepic.files[0].uuid) == "undefined") {
											  // 需要上传
								                $scope.myDropzonepic.options.url = $rootScope.project
								                        + '/api/file/fileupload.do?uuid=' + id
								                        + '&bus=file&interval=10000&bus=file';
								                $scope.myDropzonepic.uploadFile($scope.myDropzonepic.files[0])
								            } else {
								                id = $scope.myDropzonepic.files[0].uuid;
								            } 
								           modal_meta.meta.item.img=id;
									}
									
									// 只允许传一张附件
									modal_meta.meta.item.attach="";
									if($scope.myDropzonefile.files.length>0&&$scope.myDropzonefile.files.length==1){
										 var id = getUuid();
										  if (typeof ($scope.myDropzonefile.files[0].uuid) == "undefined") {
											  // 需要上传
								                $scope.myDropzonefile.options.url = $rootScope.project
								                        + '/api/file/fileupload.do?uuid=' + id
								                        + '&bus=file&interval=10000&bus=file';
								                $scope.myDropzonefile.uploadFile($scope.myDropzonefile.files[0])
								            } else {
								                id = $scope.myDropzonefile.files[0].uuid;
								            } 
								           modal_meta.meta.item.attach=id;
									}

									 
									
									if(angular
											.isDefined($state.router.globals.current.data.subclass)){
										
										modal_meta.meta.item.class_id = modal_meta.meta.classSel.dict_item_id;
									}else{
										modal_meta.meta.item.class_id = gclass_id;
									}
									
									if(angular.isDefined(modal_meta.meta.typeSel.dict_item_id)){
										modal_meta.meta.item.type = modal_meta.meta.typeSel.dict_item_id;
									}

									if(angular.isDefined(modal_meta.meta.partSel.partid)){
										modal_meta.meta.item.part_id = modal_meta.meta.partSel.partid;
									}
								
									if(angular.isDefined( modal_meta.meta.usedunameSel.user_id)){
										modal_meta.meta.item.used_userid = modal_meta.meta.usedunameSel.user_id;
									}
								
									if(angular.isDefined( modal_meta.meta.envSel.dict_item_id)){
										modal_meta.meta.item.env = modal_meta.meta.envSel.dict_item_id;
									}
									
									if(angular.isDefined( modal_meta.meta.recycelSel.dict_item_id)){
										modal_meta.meta.item.recycle = modal_meta.meta.recycelSel.dict_item_id;
									}else{
										notify({
											message : "请选择资产状态"
										});
										return;
									}

									
									
									if(angular.isDefined(modal_meta.meta.pinpSel.dict_item_id)){
										modal_meta.meta.item.brand = modal_meta.meta.pinpSel.dict_item_id;
									}
									
									if(angular.isDefined( modal_meta.meta.wbSel.dict_item_id)){
										modal_meta.meta.item.wb =modal_meta.meta.wbSel.dict_item_id;
									}
									
									if(angular.isDefined( modal_meta.meta.locSel.dict_item_id)){
										modal_meta.meta.item.loc = modal_meta.meta.locSel.dict_item_id;
									}

									if(angular.isDefined( modal_meta.meta.riskSel.dict_item_id)){
										modal_meta.meta.item.risk = modal_meta.meta.riskSel.dict_item_id;
									}
									
									if(angular.isDefined( modal_meta.meta.jgSel.dict_item_id)){
										modal_meta.meta.item.rack =modal_meta.meta.jgSel.dict_item_id ;
									}
									

									if(angular.isDefined( modal_meta.meta.tbSel.dict_item_id)){
										modal_meta.meta.item.wb_auto =modal_meta.meta.tbSel.dict_item_id ;
									}


									if(angular.isDefined( modal_meta.meta.zcwbsupperSel.dict_item_id)){
										modal_meta.meta.item.wbsupplier =modal_meta.meta.zcwbsupperSel.dict_item_id ;
									}

									if(angular.isDefined( modal_meta.meta.zcsourceSel.dict_item_id)){
										modal_meta.meta.item.zcsource =modal_meta.meta.zcsourceSel.dict_item_id ;
									}

									if(angular.isDefined( modal_meta.meta.zcsupperSel.dict_item_id)){
										modal_meta.meta.item.supplier =modal_meta.meta.zcsupperSel.dict_item_id ;
									}

									if(angular.isDefined( modal_meta.meta.zcsupperSel.dict_item_id)){
										modal_meta.meta.item.supplier =modal_meta.meta.zcsupperSel.dict_item_id ;
									}

									//属于公司
									if(angular.isDefined( modal_meta.meta.belongcompSel.id)){
										modal_meta.meta.item.belong_company_id =modal_meta.meta.belongcompSel.id ;
									}

									//使用公司
									if(angular.isDefined( modal_meta.meta.compSel.id)){
										modal_meta.meta.item.used_company_id =modal_meta.meta.compSel.id ;
									}


									modal_meta.meta.item.buy_time_f = modal_meta.meta.buytime
											.format('YYYY-MM-DD');
									modal_meta.meta.item.wbout_date_f = modal_meta.meta.wboutdate
											.format('YYYY-MM-DD');
								 
								 

									// 动态参数
									if (angular.isDefined(modal_meta.meta.attr)
											&& modal_meta.meta.attr.length > 0) {
										for (var j = 0; j < modal_meta.meta.attr.length; j++) {
											var code = modal_meta.meta.attr[j].attr_code;
											modal_meta.meta.attr[j].attr_value = modal_meta.meta.item[modal_meta.meta.attr[j].attr_code];
										}
									}
									modal_meta.meta.item.attrvals = angular
											.toJson(modal_meta.meta.attr);
								 
									$http
											.post(
													$rootScope.project
															+ "/api/base/res/addResCustom.do",
													modal_meta.meta.item)
											.success(function(res) {
												if (res.success) {
													modalInstance.close("OK");
												} else {
													notify({
														message : res.message
													});
												}
											});

								},
								init : function(modal_meta) {
									var tt = {};
									angular.copy(gdicts, tt)
									loadOpt(modal_meta, tt);




									var iid=modal_meta.meta.item.img;
									if(angular.isDefined(iid)&&iid.length>0){
										$timeout(function() {
											var mockFile = {
												name : "主图",
												uuid : iid,
												href : $rootScope.project
														+ "/api/file/imagedown.do?id="
														+iid,
												url : $rootScope.project
														+ "/api/file/imagedown.do?id="
														+ iid,
												status : "success",
												accepted : true,
												type : 'image/png'
											};
											$scope.myDropzonepic.emit("addedfile", mockFile);
											$scope.myDropzonepic.files.push(mockFile);
											// manually
											$scope.myDropzonepic.createThumbnailFromUrl(
													mockFile, $rootScope.project
															+ "/api/file/imagedown.do?id="
															+ iid);
											$scope.myDropzonepic.emit("complete", mockFile); 
										}, 300);	
									}
									
									var iidf=modal_meta.meta.item.attach;
									if(angular.isDefined(iidf)&&iidf.length>0){
									$timeout(function() {
											var mockFile = {
												name : "主图",
												uuid : iidf,
												href : $rootScope.project
														+ "/api/file/imagedown.do?id="
														+iidf,
												url : $rootScope.project
														+ "/api/file/imagedown.do?id="
														+ iidf,
												status : "success",
												accepted : true,
												type : 'image/png'
											};
											$scope.myDropzonefile.emit("addedfile", mockFile);
											$scope.myDropzonefile.files.push(mockFile);
											// manually
											$scope.myDropzonefile.createThumbnailFromUrl(
													mockFile, $rootScope.project
															+ "/api/file/imagedown.do?id="
															+ iidf);
											$scope.myDropzonefile.emit("complete", mockFile); 
										}, 300);
										}
 

								}
							}

							if (angular.isDefined(res.data.data)
									&& angular.isDefined(res.data.data.id)) {
								meta.item = res.data.data;
								// 填充其他数据
							}

							// 补充属性数据
							if (angular.isDefined(res.data.attr)
									&& res.data.attr.length > 0) {
								meta.attr = res.data.attr;
								for (var i = 0; i < res.data.attr.length; i++) {
									if (i == 0) {
										var e = {
											type : "dashed"
										}
										items.push(e);
									}

									var attr_type = res.data.attr[i].attr_type;
									if (attr_type == "string") {
										var e = {
											type : "input",
											disabled : "false",
											sub_type : "text",
											required : true,
											maxlength : "100",
											placeholder : "请输入内容",
											label : res.data.attr[i].attr_name,
											need : false,
											name : res.data.attr[i].attr_code,
											ng_model : res.data.attr[i].attr_code,
										}
										items.push(e);
									} else if (attr_type == "number") {
										var e = {
											type : "input",
											disabled : "false",
											sub_type : "number",
											required : true,
											maxlength : "20",
											placeholder : "请输入内容",
											label : res.data.attr[i].attr_name,
											need : false,
											name : res.data.attr[i].attr_code,
											ng_model : res.data.attr[i].attr_code,
										}
										items.push(e);
									}

								}

							}

							// 打开静态框
							var modalInstance = $uibModal
									.open({
										backdrop : true,
										templateUrl : 'views/Template/modal_simpleForm.html',
										controller : modal_simpleFormCtl,
										size : 'lg',
										resolve : {
											meta : function() {
												return meta;
											}
										}
									});
							modalInstance.result.then(function(result) {

								if (result == "OK") {
									flush();
								}
							}, function(reason) {

								$log.log("reason", reason)
							});
						})
	}
};

app.register.controller('genericdevCtl', genericdevCtl);