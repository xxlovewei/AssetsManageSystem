
function modalhcinoutCtl(DTOptionsBuilder, DTColumnBuilder, $compile,
							$confirm, $log, notify, $scope, $http, $rootScope, $uibModal,meta,
							$uibModalInstance, $window, $stateParams,$timeout) {

	$scope.data={};
	$scope.data.zc_cnt=0;
	$scope.data.batchno=new Date().getTime();
	$scope.data.buy_price=0;
	$scope.data.cgtime = moment();
	$scope.catOpt=[];
	$scope.catSel={};

	$scope.compOpt=[];
	$scope.compSel={};

	$scope.locOpt=[];
	$scope.locSel={};

	$scope.wareHouseOpt=[];
	$scope.wareHouseSel={};

	$scope.supperOpt=[];
	$scope.supperSel={};

	var dicts="devdc,zcsupper,warehouse";

	$http.post($rootScope.project + "/api/zc/queryDictFast.do",
		{
			uid:"hcinoutdicts",
			zchccat:"Y",
			comp :"Y",
			dicts : dicts
		}).success(function(res) {
		if (res.success) {
			$scope.supperOpt=res.data.zcsupper;
			if($scope.supperOpt.length>0){
				$scope.supperSel=$scope.supperOpt[0];
			}

			$scope.wareHouseOpt=res.data.warehouse;
			if($scope.wareHouseOpt.length>0){
				$scope.wareHouseSel=$scope.wareHouseOpt[0];
			}


			$scope.locOpt=res.data.devdc;
			if($scope.locOpt.length>0){
				$scope.locSel=$scope.locOpt[0];
			}

			$scope.catOpt=res.data.zchccat;
			if($scope.catOpt.length>0){
				$scope.catSel=$scope.catOpt[0];
				$scope.catSel.seckc=$scope.catSel.downcnt+"-"+$scope.catSel.upcnt;
			}

			$scope.compOpt=res.data.comp;
			if($scope.compOpt.length>0){
				$scope.compSel=$scope.compOpt[0];
			}



		} else {
			notify({
				message : res.message
			});
		}
	})


	$scope.$watch('catSel',function(newValue,oldValue){
		if(angular.isDefined($scope.catSel.id)){
			$scope.catSel.seckc=$scope.catSel.downcnt+"-"+$scope.catSel.upcnt;
		}

	});



	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};





	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data').withDOM('frtlip')
		.withPaginationType('full_numbers')
		.withDisplayLength(25)
		.withOption("ordering", false).withOption("responsive", false)
		.withOption("searching", true).withOption('scrollY', 420)
		.withOption('scrollX', true).withOption('bAutoWidth', true)
		.withOption('scrollCollapse', true).withOption('paging', false)
		.withOption('bStateSave', true).withOption('bProcessing', true)
		.withOption('bFilter', false).withOption('bInfo', false)
		.withOption('serverSide', false).withOption('createdRow', function(row) {
			$compile(angular.element(row).contents())($scope);
		});

	$scope.dtInstance = {}
	function renderAction(data, type, full) {
		var acthtml=" <a href=\"javascript:void(0)\" style=\"margin-top: 3px;\" ng-click=\"remove('"+full.lid+"')\" class=\"btn-white btn btn-xs\">删除</a>";
		return acthtml;

	}
	var dtColumns = [];
	dtColumns.push(DTColumnBuilder.newColumn('lid').withTitle('动作').withOption(
		'sDefaultContent', '').withOption("name", '30').renderWith(renderAction));

	dtColumns.push(DTColumnBuilder.newColumn('name').withTitle('物品类型').withOption(
		'sDefaultContent', '').withOption("name", '30'));

	dtColumns.push(DTColumnBuilder.newColumn('model').withTitle('规格型号').withOption(
		'sDefaultContent', '').withOption("width", '30'));

	dtColumns.push(DTColumnBuilder.newColumn('unit').withTitle('单位').withOption(
		'sDefaultContent', '').withOption("width", '30'));

	dtColumns.push(DTColumnBuilder.newColumn('supplierstr').withTitle('厂商').withOption(
		'sDefaultContent', '').withOption("width", '30'));

	dtColumns.push( DTColumnBuilder.newColumn('comp_name').withTitle('使用公司').withOption(
		'sDefaultContent', ''));

	dtColumns.push( DTColumnBuilder.newColumn('locstr').withTitle('区域').withOption(
		'sDefaultContent', '').withOption('width', '30'));

	dtColumns.push( DTColumnBuilder.newColumn('warehousestr').withTitle('仓库').withOption(
		'sDefaultContent', '').withOption('width', '30'));

	dtColumns.push( DTColumnBuilder.newColumn('buy_timestr').withTitle('采购时间')
		.withOption('sDefaultContent', ''));
	dtColumns.push( DTColumnBuilder.newColumn('buy_price').withTitle('采购金额')
		.withOption('sDefaultContent', ''));
	dtColumns.push( DTColumnBuilder.newColumn('zc_cnt').withTitle('数量')
		.withOption('sDefaultContent', ''));
	dtColumns.push( DTColumnBuilder.newColumn('batchno').withTitle('批次号')
		.withOption('sDefaultContent', ''));
	dtColumns.push(  DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
		'sDefaultContent', ''));
	$scope.dtColumns=dtColumns;
	$scope.dtOptions.aaData=[];

	$scope.zcadd=function(){

		var e={};
		var time = new Date().getTime();
		$scope.data.lid=time;
		$scope.data.name=$scope.catSel.name;

		if(angular.isDefined($scope.catSel.model)){
			$scope.data.model=$scope.catSel.model;
		}

		if(angular.isDefined($scope.catSel.unit)){
			$scope.data.unit=$scope.catSel.unit;
		}

		if(angular.isDefined($scope.locSel.dict_item_id)){
			$scope.data.loc=$scope.locSel.dict_item_id;
			$scope.data.locstr=$scope.locSel.name;
		}
		if(angular.isDefined($scope.wareHouseSel.dict_item_id)){
			$scope.data.warehouse=$scope.wareHouseSel.dict_item_id;
			$scope.data.warehousestr=$scope.wareHouseSel.name
		}

		if(angular.isDefined($scope.supperSel.dict_item_id)){
			$scope.data.supplier=$scope.supperSel.dict_item_id;
			$scope.data.supplierstr=$scope.supperSel.name;
		}

		if(angular.isDefined($scope.compSel.id)){
			$scope.data.used_company_id=$scope.compSel.id;
			$scope.data.comp_name=$scope.compSel.name;
		}

		$scope.data.buy_timestr= $scope.data.cgtime.format('YYYY-MM-DD')
		$scope.data.class_id=$scope.catSel.id;
		$scope.data.zc_category=$scope.catSel.root;
		angular.copy($scope.data,e);
		$scope.dtOptions.aaData.push(e);
		console.log($scope.dtOptions.aaData);

	}
	$scope.remove=function(id){
		var del=0;
		for(var i=0;i<$scope.dtOptions.aaData.length;i++){
			if($scope.dtOptions.aaData[i].lid==id){
				del=i;
			}
		}
		$scope.dtOptions.aaData.splice(del,1);
	}

	$scope.sure = function() {

		if($scope.dtOptions.aaData==0){
			notify({
				message : "请选择物品"
			});
			return;
		}
		$scope.data.items=angular.toJson($scope.dtOptions.aaData)
		$scope.data.type="hc";
		$scope.data.action="HCRK";
		$scope.data.buytime=$scope.data.cgtime.format('YYYY-MM-DD');
		$http.post($rootScope.project + "/api/zc/resInout/ext/insert.do",
			$scope.data).success(function(res) {
			if (res.success) {
				$uibModalInstance.close("OK");
			} else {

			}
			notify({
				message : res.message
			});
		})
	}



}

function zcHcinoutCtl(DTOptionsBuilder, DTColumnBuilder, $compile, $confirm,
		$log, notify,$scope, $http, $rootScope, $uibModal, $window,$state) {

	var pbtns=$rootScope.curMemuBtns;
	var gclassroot='7';


	$scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data').withDOM('frtlip')
		.withPaginationType('full_numbers')
			.withDisplayLength(25)
			.withOption("ordering", false).withOption("responsive", false)
			.withOption("searching", true).withOption('scrollY', 420)
			.withOption('scrollX', true).withOption('bAutoWidth', true)
			.withOption('scrollCollapse', true).withOption('paging', true)
		     .withOption('bStateSave', true).withOption('bProcessing', true)
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
	$scope.dtColumns=zcBaseInOutColsCreate(DTColumnBuilder,'withselect');

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
					 show:true,
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
					priv:"detail",
					template : ' <button ng-click="detail()" class="btn btn-sm btn-primary" type="submit">详情</button>'
				},
				{
					id : "btn5",
					label : "",
					type : "btn",
					show:false,
					priv:"remove",
					template : ' <button ng-click="del()" class="btn btn-sm btn-primary" type="submit">删除</button>'
				},
				{
					id : "btn6",
					label : "",
					type : "btn",
					show:false,
					priv:"exportfile",
					template : ' <button ng-click="filedown()" class="btn btn-sm btn-primary" type="submit">全部导出(Excel)</button>'
				} ],
		tools : [

		// 	{
		// 	id : "select",
		// 	label : "区域",
		// 	type : "select",
		// 	disablesearch : true,
		// 	show:true,
		// 	dataOpt : [],
		// 	dataSel : ""
		// }, {
		// 	id : "select",
		// 	label : "状态",
		// 	type : "select",
		// 	disablesearch : true,
		// 	show:true,
		// 	dataOpt : [],
		// 	dataSel : ""
		// },
			{
			id : "input",
			show:true,
			label : "内容",
			placeholder : "输入型号、编号、序列号",
			type : "input",
			ct : ""
		}

		]
	};
 
	$scope.meta=meta;
	privNormalCompute($scope.meta.toolsbtn, pbtns);

	function flush() {
		var ps={};
		ps.type='hc';
		$http.post($rootScope.project + "/api/zc/resInout/ext/selectList.do", ps)
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


	function callback(json){
		console.log(json)
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

			var d = $scope.dtInstance.DataTable.context[0].json.data;
			for (var i = 0; i < data.length; i++) {
				res.push(d[data[i]].id)
			}
			return angular.toJson(res);
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



	
	// //////////////////////////save/////////////////////
	$scope.save = function(type) {

		var modalInstance = $uibModal.open({
			backdrop : true,
			templateUrl : 'views/cmdb/modal_hcinout.html',
			controller : modalhcinoutCtl,
			size : 'blg',
			resolve : {
				meta:function(){
					return ""
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

	flush();

};

app.register.controller('zcHcinoutCtl',zcHcinoutCtl);