function GetDateNowId() {
    var vNow = new Date();
    var sNow = "";
    sNow += String(vNow.getFullYear());
    sNow += String(vNow.getMonth() + 1);
    sNow += String(vNow.getDate());
    sNow += String(vNow.getHours());
    sNow += String(vNow.getMinutes());
    sNow += String(vNow.getSeconds());
    sNow += String(vNow.getMilliseconds());
    return sNow;
}

function rendersn(data, type, full) {
    if (angular.isUndefined(data)) {
        data = "";
    }
    if (full.tsnstatus == "true") {
        return "<span style=\"color:purple;font-weight:bold\">" + data + "</span>"
    } else {
        return "<span style=\"color:red;font-weight:bold\">不变更</span>"
    }
}

function renderclass(data, type, full) {
    if (angular.isUndefined(data)) {
        data = "";
    }
    if (full.tclassidstatus == "true") {
        return "<span style=\"color:purple;font-weight:bold\">" + data + "</span>"
    } else {
        return "<span style=\"color:red;font-weight:bold\">不变更</span>"
    }
}

function rendermodel(data, type, full) {
    if (angular.isUndefined(data)) {
        data = "";
    }
    if (full.tmodelstatus == "true") {
        return "<span style=\"color:purple;font-weight:bold\">" + data + "</span>"
    } else {
        return "<span style=\"color:red;font-weight:bold\">不变更</span>"
    }
}

function rendersource(data, type, full) {
    if (angular.isUndefined(data)) {
        data = "";
    }
    if (full.tzcsourcestatus == "true") {
        return "<span style=\"color:purple;font-weight:bold\">" + data + "</span>"
    } else {
        return "<span style=\"color:red;font-weight:bold\">不变更</span>"
    }
}

function rendersupplier(data, type, full) {
    if (angular.isUndefined(data)) {
        data = "";
    }
    if (full.tsupplierstatus == "true") {
        return "<span style=\"color:purple;font-weight:bold\">" + data + "</span>"
    } else {
        return "<span style=\"color:red;font-weight:bold\">不变更</span>"
    }
}

function renderusefullife(data, type, full) {
    if (angular.isUndefined(data)) {
        data = "";
    }
    if (full.tusefullifestatus == "true") {
        return "<span style=\"color:purple;font-weight:bold\">" + data + "</span>"
    } else {
        return "<span style=\"color:red;font-weight:bold\">不变更</span>"
    }
}

function renderbrand(data, type, full) {
    if (angular.isUndefined(data)) {
        data = "";
    }
    if (full.tbrandstatus == "true") {
        return "<span style=\"color:purple;font-weight:bold\">" + data + "</span>"
    } else {
        return "<span style=\"color:red;font-weight:bold\">不变更</span>"
    }
}

function renderloc(data, type, full) {
    if (angular.isUndefined(data)) {
        data = "";
    }
    if (full.tlocstatus == "true") {
        return "<span style=\"color:purple;font-weight:bold\">" + data + "</span>"
    } else {
        return "<span style=\"color:red;font-weight:bold\">不变更</span>"
    }
}

function rendercbuytime(data, type, full) {
    if (angular.isUndefined(data)) {
        data = "";
    }
    if (full.tbuytimestatus == "true") {
        return "<span style=\"color:purple;font-weight:bold\">" + data + "</span>"
    } else {
        return "<span style=\"color:red;font-weight:bold\">不变更</span>"
    }
}

function rendercomp(data, type, full) {
    if (angular.isUndefined(data)) {
        data = "";
    }
    if (full.tusedcompanyidstatus == "true") {
        return "<span style=\"color:purple;font-weight:bold\">" + data + "</span>"
    } else {
        return "<span style=\"color:red;font-weight:bold\">不变更</span>"
    }
}

function renderpart(data, type, full) {
    if (angular.isUndefined(data)) {
        data = "";
    }
    if (full.tpartidstatus == "true") {
        return "<span style=\"color:purple;font-weight:bold\">" + data + "</span>"
    } else {
        return "<span style=\"color:red;font-weight:bold\">不变更</span>"
    }
}

function renderuser(data, type, full) {
    if (angular.isUndefined(data)) {
        data = "";
    }
    if (full.tuseduseridstatus == "true") {
        return "<span style=\"color:purple;font-weight:bold\">" + data + "</span>"
    } else {
        return "<span style=\"color:red;font-weight:bold\">不变更</span>"
    }
}

function rendercnt(data, type, full) {
    if (angular.isUndefined(data)) {
        data = "";
    }
    if (full.tzccntstatus == "true") {
        return "<span style=\"color:purple;font-weight:bold\">" + data + "</span>"
    } else {
        return "<span style=\"color:red;font-weight:bold\">不变更</span>"
    }
}

function renderunit(data, type, full) {
    if (angular.isUndefined(data)) {
        data = "";
    }
    if (full.tunitstatus == "true") {
        return "<span style=\"color:purple;font-weight:bold\">" + data + "</span>"
    } else {
        return "<span style=\"color:red;font-weight:bold\">不变更</span>"
    }
}

function renderfs1(data, type, full) {
    if (angular.isUndefined(data)) {
        data = "";
    }
    if (full.tlabel1status == "true") {
        return "<span style=\"color:purple;font-weight:bold\">" + data + "</span>"
    } else {
        return "<span style=\"color:red;font-weight:bold\">不变更</span>"
    }
}

function renderlocdtl(data, type, full) {
    if (angular.isUndefined(data)) {
        data = "";
    }
    if (full.tlocdtlstatus == "true") {
        return "<span style=\"color:purple;font-weight:bold\">" + data + "</span>"
    } else {
        return "<span style=\"color:red;font-weight:bold\">不变更</span>"
    }
}

function renderconfdesc(data, type, full) {
    if (angular.isUndefined(data)) {
        data = "";
    }
    if (full.tconfdescstatus == "true") {
        return "<span style=\"color:purple;font-weight:bold\">" + data + "</span>"
    } else {
        return "<span style=\"color:red;font-weight:bold\">不变更</span>"
    }
}

function cgjblistCtl($confirm, $timeout, $localStorage, notify, $log, $uibModal,
                     $uibModalInstance, $scope, meta, $http, $rootScope, DTOptionsBuilder,
                     DTColumnBuilder, $compile) {
    var item = meta;
    $scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data').withDOM('frtlip')
        .withPaginationType('full_numbers').withDisplayLength(100)
        .withOption("ordering", false).withOption("responsive", false)
        .withOption("searching", true).withOption('scrollY', 600)
        .withOption('scrollX', true).withOption('bAutoWidth', true)
        .withOption('scrollCollapse', true).withOption('paging', true)
        .withOption('bStateSave', true).withOption('bProcessing', false)
        .withOption('bFilter', false).withOption('bInfo', false)
        .withOption('serverSide', false).withOption('createdRow', function (row) {
            $compile(angular.element(row).contents())($scope);
        }).withOption(
            'headerCallback',
            function (header) {
                if ((!angular.isDefined($scope.headerCompiled))
                    || $scope.headerCompiled) {
                    $scope.headerCompiled = true;
                    $compile(angular.element(header).contents())
                    ($scope);
                }
            }).withOption("select", {
            style: 'multi',
            selector: 'td:first-child'
        }).withButtons([
            {
                extend: 'csv',
                text: 'Excel(当前页)',
                exportOptions: {
                    columns: ':visible',
                    trim: true,
                    modifier: {
                        page: 'current'
                    }
                }
            },
            {
                extend: 'print',
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
    $scope.dtInstance = {}
    $scope.dtColumns = [];
    var dtColumns = [];

    function renderZCAction(data, type, full) {
        var acthtml = " <div class=\"btn-group\"> ";
        acthtml = acthtml + " <button ng-click=\"delitem('"
            + full.id
            + "')\" class=\"btn-white btn btn-xs\">删除</button>   ";
        acthtml = acthtml + "</div>"
        return acthtml;
    }

    $scope.dtColumns = [
        DTColumnBuilder.newColumn('uuid').withTitle('资产编号').withOption(
            'sDefaultContent', '').withOption("width", '30'),
        DTColumnBuilder.newColumn('sn').withTitle('序列').withOption(
            'sDefaultContent', '').withOption('width', '50'),
        DTColumnBuilder.newColumn('fclassfullname').withTitle('资产类别(变更前)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColorGreenH),
        DTColumnBuilder.newColumn('tclassfullname').withTitle('资产类别(变更后)').withOption(
            'sDefaultContent', '').renderWith(renderclass),
        DTColumnBuilder.newColumn('fmodel').withTitle('规格型号(变更前)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColorGreenH),
        DTColumnBuilder.newColumn('tmodel').withTitle('规格型号(变更后)').withOption(
            'sDefaultContent', '').renderWith(rendermodel),
        DTColumnBuilder.newColumn('fsn').withTitle('序列(变更前)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColorGreenH),
        DTColumnBuilder.newColumn('tsn').withTitle('序列(变更后)').withOption(
            'sDefaultContent', '').renderWith(rendersn),
        DTColumnBuilder.newColumn('funit').withTitle('计量单位(变更前)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColorGreenH),
        DTColumnBuilder.newColumn('tunit').withTitle('计量单位(变更后)').withOption(
            'sDefaultContent', '').renderWith(renderunit),
        DTColumnBuilder.newColumn('fzccnt').withTitle('数量(变更前)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColorGreenH),
        DTColumnBuilder.newColumn('tzccnt').withTitle('数量(变更后)').withOption(
            'sDefaultContent', '').renderWith(rendercnt),
        DTColumnBuilder.newColumn('fsupplierstr').withTitle('供应商(变更前)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColorGreenH),
        DTColumnBuilder.newColumn('tsupplierstr').withTitle('供应商(变更后)').withOption(
            'sDefaultContent', '').renderWith(rendersupplier),
        DTColumnBuilder.newColumn('fbrandstr').withTitle('品牌(变更前)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColorGreenH),
        DTColumnBuilder.newColumn('tbrandstr').withTitle('品牌(变更后)').withOption(
            'sDefaultContent', '').renderWith(renderbrand),
        DTColumnBuilder.newColumn('fzcsourcestr').withTitle('来源(变更前)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColorGreenH),
        DTColumnBuilder.newColumn('tzcsourcestr').withTitle('来源(变更后)').withOption(
            'sDefaultContent', '').renderWith(rendersource),
        DTColumnBuilder.newColumn('flocstr').withTitle('区域(变更前)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColorGreenH),
        DTColumnBuilder.newColumn('tlocstr').withTitle('区域(变更后)').withOption(
            'sDefaultContent', '').renderWith(renderloc),
        DTColumnBuilder.newColumn('flocdtl').withTitle('位置(变更前)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColorGreenH),
        DTColumnBuilder.newColumn('tlocdtl').withTitle('位置(变更后)').withOption(
            'sDefaultContent', '').renderWith(renderlocdtl),
        DTColumnBuilder.newColumn('fusefullifestr').withTitle('使用期限(变更前)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColorGreenH),
        DTColumnBuilder.newColumn('tusefullifestr').withTitle('使用期限(变更后)').withOption(
            'sDefaultContent', '').renderWith(renderusefullife),
        DTColumnBuilder.newColumn('fbuytimestr').withTitle('采购日期(变更前)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColorGreenH),
        DTColumnBuilder.newColumn('tbuytimestr').withTitle('采购日期(变更后)').withOption(
            'sDefaultContent', '').renderWith(rendercbuytime),
        DTColumnBuilder.newColumn('fconfdesc').withTitle('配置描述(变更前)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColorGreenH),
        DTColumnBuilder.newColumn('tconfdesc').withTitle('配置描述(变更后)').withOption(
            'sDefaultContent', '').renderWith(renderconfdesc),
        DTColumnBuilder.newColumn('fusedcompanyname').withTitle('使用公司(变更前)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColorGreenH),
        DTColumnBuilder.newColumn('tusedcompanyname').withTitle('使用公司(变更后)').withOption(
            'sDefaultContent', '').renderWith(rendercomp),
        DTColumnBuilder.newColumn('fpartname').withTitle('使用部门(变更前)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColorGreenH),
        DTColumnBuilder.newColumn('tpartname').withTitle('使用部门(变更后)').withOption(
            'sDefaultContent', '').renderWith(renderpart),
        DTColumnBuilder.newColumn('fusedusername').withTitle('使用人(变更前)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColorGreenH),
        DTColumnBuilder.newColumn('tusedusername').withTitle('使用人(变更后)').withOption(
            'sDefaultContent', '').renderWith(renderuser),
        DTColumnBuilder.newColumn('flabel1').withTitle('标签1(变更前)').withOption(
            'sDefaultContent', '').renderWith(renderDTFontColorGreenH),
        DTColumnBuilder.newColumn('tlabel1').withTitle('标签1(变更后)').withOption(
            'sDefaultContent', '').renderWith(renderfs1),
        DTColumnBuilder.newColumn('create_time').withTitle('创建时间').withOption(
            'sDefaultContent', '')]

    function flush() {
        $http.post($rootScope.project + "/api/zc/resCBasicinformation/ext/selectByUuid.do",
            item).success(function (res) {
            if (res.success) {
                $scope.dtOptions.aaData = res.data;
            } else {
                notify({
                    message: res.message
                });
            }
        })
    }

    flush();
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
}

function zccgjbSaveCtl($timeout, $localStorage, notify, $log, $uibModal,
                       $uibModalInstance, $scope, meta, $http, $rootScope, DTOptionsBuilder,
                       DTColumnBuilder, $compile, $confirm) {
    $scope.ctl = {processuser: false};
    $scope.item = {};

    function selectnall() {
        $scope.item.tclassidstatus = false;
        $scope.item.tmodelstatus = false;
        $scope.item.tsnstatus = false;
        $scope.item.tzcsourcestatus = false;
        $scope.item.tzccntstatus = false;
        $scope.item.tsupplierstatus = false;
        $scope.item.tbrandstatus = false;
        $scope.item.tbuytimestatus = false;
        $scope.item.tlocstatus = false;
        $scope.item.tusefullifestatus = false;
        $scope.item.tusedcompanyidstatus = false;
        $scope.item.tpartidstatus = false;
        $scope.item.tuseduseridstatus = false;
        $scope.item.tsnstatus = false;
        $scope.item.tlabel1status = false;
        $scope.item.tunitstatus = false;
        $scope.item.tconfdescstatus = false;
        $scope.item.tlocdtlstatus = false;
    }

    function selectall() {
        $scope.item.tclassidstatus = true;
        $scope.item.tmodelstatus = true;
        $scope.item.tsnstatus = true;
        $scope.item.tzcsourcestatus = true;
        $scope.item.tzccntstatus = true;
        $scope.item.tsupplierstatus = true;
        $scope.item.tbrandstatus = true;
        $scope.item.tbuytimestatus = true;
        $scope.item.tlocstatus = true;
        $scope.item.tusefullifestatus = true;
        $scope.item.tusedcompanyidstatus = true;
        $scope.item.tpartidstatus = true;
        $scope.item.tuseduseridstatus = true;
        $scope.item.tsnstatus = true;
        $scope.item.tlabel1status = true;
        $scope.item.tunitstatus = true;
        $scope.item.tconfdescstatus = true;
        $scope.item.tlocdtlstatus = true;
    }

    selectnall();
    $scope.selectall = function () {
        selectall();
    }
    $scope.selectnall = function () {
        selectnall();
    }
    $scope.classOpt = meta.dict.btype;
    $scope.classSel = "";
    if ($scope.classOpt.length > 0) {
        $scope.classSel = $scope.classOpt[0];
    }
    $scope.supplierOpt = meta.dict.zcsupper;
    $scope.supplierSel = "";
    if ($scope.supplierOpt.length > 0) {
        $scope.supplierSel = $scope.supplierOpt[0];
    }
    $scope.brandOpt = meta.dict.devbrand;
    $scope.brandSel = "";
    if ($scope.brandOpt.length > 0) {
        $scope.brandSel = $scope.brandOpt[0];
    }
    $scope.usefullifeOpt = meta.dict.zcusefullife
    $scope.usefullifeSel = ""
    if ($scope.usefullifeOpt.length > 0) {
        $scope.usefullifeSel = $scope.usefullifeOpt[0];
    }
    $scope.zcsourceOpt = meta.dict.zcsource;
    $scope.zcsourceSel = "";
    if ($scope.zcsourceOpt.length > 0) {
        $scope.zcsourceSel = $scope.zcsourceOpt[0];
    }
    $scope.locOpt = meta.dict.devdc;
    $scope.locSel = "";
    if ($scope.locOpt.length > 0) {
        $scope.locSel = $scope.locOpt[0];
    }
    $scope.usedcompOpt = meta.dict.comp;
    $scope.usedcompSel = {};
    if ($scope.usedcompOpt.length > 0) {
        $scope.usedcompSel = $scope.usedcompOpt[0];
    }
    $scope.usedpartOpt = meta.dict.parts;
    $scope.usedpartSel = {};
    if ($scope.usedpartOpt.length > 0) {
        $scope.usedpartSel = $scope.usedpartOpt[0];
    }
    $scope.useduserOpt = meta.dict.partusers;
    $scope.useduserSel = "";
    if ($scope.useduserOpt.length > 0) {
        $scope.useduserSel = $scope.useduserOpt[0];
    }
    $scope.adminuserOpt = meta.dict.partusers;
    $scope.adminuserSel = "";
    if ($scope.adminuserOpt.length > 0) {
        $scope.adminuserSel = $scope.adminuserOpt[0];
        if (angular.isDefined($rootScope.dt_sys_user_info)) {
            for (var i = 0; i < $scope.adminuserOpt.length; i++) {
                if ($rootScope.dt_sys_user_info.userId == $scope.adminuserOpt[i].user_id) {
                    $scope.adminuserSel = $scope.adminuserOpt[i];
                    $scope.ctl.processuser = true;
                    break;
                }
            }
        }
    }
    $scope.date = {
        buytime: moment()
    }
    $scope.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
    $scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data').withDOM('frtlip')
        .withPaginationType('full_numbers').withDisplayLength(100)
        .withOption("ordering", false).withOption("responsive", false)
        .withOption("searching", true).withOption('scrollY', 600)
        .withOption('scrollX', true).withOption('bAutoWidth', true)
        .withOption('scrollCollapse', true).withOption('paging', true)
        .withOption('bStateSave', true).withOption('bProcessing', false)
        .withOption('bFilter', false).withOption('bInfo', false)
        .withOption('serverSide', false).withOption('createdRow', function (row) {
            $compile(angular.element(row).contents())($scope);
        }).withOption(
            'headerCallback',
            function (header) {
                if ((!angular.isDefined($scope.headerCompiled))
                    || $scope.headerCompiled) {
                    $scope.headerCompiled = true;
                    $compile(angular.element(header).contents())
                    ($scope);
                }
            }).withOption("select", {
            style: 'multi',
            selector: 'td:first-child'
        });
    $scope.dtInstance = {}
    var dtColumns = [];

    function renderZCAction(data, type, full) {
        var acthtml = " <div class=\"btn-group\"> ";
        acthtml = acthtml + " <span ng-click=\"delitem('"
            + full.id
            + "')\" class=\"btn-white btn btn-xs\">删除</span>   ";
        acthtml = acthtml + " <span ng-click=\"filldata('"
            + full.id
            + "')\" class=\"btn-white btn btn-xs\">填充</span>   ";
        acthtml = acthtml + "</div>"
        return acthtml;
    }

    $scope.delitem = function (id) {
        var del = 0;
        for (var i = 0; i < $scope.dtOptions.aaData.length; i++) {
            if ($scope.dtOptions.aaData[i].id == id) {
                del = i;
                break;
            }
        }
        $scope.dtOptions.aaData.splice(del, 1);
    }
    dtColumns.push(DTColumnBuilder.newColumn('id').withTitle('操作').withOption(
        'sDefaultContent', '').withOption("width", '100').renderWith(renderZCAction));
    dtColumns.push(DTColumnBuilder.newColumn('uuid').withTitle('资产编号').withOption(
        'sDefaultContent', '').withOption("width", '30'));
    dtColumns.push(DTColumnBuilder.newColumn('fs20').withTitle('其他编号').withOption(
        'sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('classfullname').withTitle('资产类别').withOption(
        'sDefaultContent', '').withOption("width", '30'));
    dtColumns.push(DTColumnBuilder.newColumn('model').withTitle('规格型号').withOption(
        'sDefaultContent', '').withOption('width', '50'));
    dtColumns.push(DTColumnBuilder.newColumn('recyclestr').withTitle('资产状态').withOption(
        'sDefaultContent', '').withOption('width', '30').renderWith(renderZcRecycle));
    dtColumns.push(DTColumnBuilder.newColumn('zcsourcestr').withTitle('来源').withOption(
        'sDefaultContent', '').withOption("width", '30'));
    dtColumns.push(DTColumnBuilder.newColumn('supplierstr').withTitle('供应商').withOption(
        'sDefaultContent', '').withOption("width", '30'));
    dtColumns.push(DTColumnBuilder.newColumn('brandstr').withTitle('品牌').withOption(
        'sDefaultContent', '').withOption('width', '30'));
    dtColumns.push(DTColumnBuilder.newColumn('sn').withTitle('序列').withOption(
        'sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('zc_cnt').withTitle('数量')
        .withOption('sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('confdesc').withTitle('配置描述').withOption(
        'sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('belongcom_name').withTitle('所属公司').withOption(
        'sDefaultContent', '').renderWith(renderDTFontColoPurpleH));
    dtColumns.push(DTColumnBuilder.newColumn('comp_name').withTitle('使用公司').withOption(
        'sDefaultContent', '').renderWith(renderDTFontColoPurpleH));
    dtColumns.push(DTColumnBuilder.newColumn('part_fullname').withTitle('使用部门').withOption(
        'sDefaultContent', '').renderWith(renderDTFontColoPurpleH));
    dtColumns.push(DTColumnBuilder.newColumn('used_username').withTitle('使用人').withOption(
        'sDefaultContent', '').renderWith(renderDTFontColoPurpleH));
    dtColumns.push(DTColumnBuilder.newColumn('locstr').withTitle('区域').withOption(
        'sDefaultContent', '').renderWith(renderDTFontColoPurpleH));
    dtColumns.push(DTColumnBuilder.newColumn('locdtl').withTitle('位置').withOption(
        'sDefaultContent', '').renderWith(renderZcLoc));
    dtColumns.push(DTColumnBuilder.newColumn('usefullifestr').withTitle('使用年限')
        .withOption('sDefaultContent', '').renderWith(renderDTFontColorGreenH));
    dtColumns.push(DTColumnBuilder.newColumn('buy_timestr').withTitle('采购日期')
        .withOption('sDefaultContent', '').renderWith(renderDTFontColorGreenH));
    dtColumns.push(DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
        'sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('fs1').withTitle('标签1').withOption(
        'sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('fs2').withTitle('标签2').withOption(
        'sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('lastinventorytimestr').withTitle('最近盘点')
        .withOption('sDefaultContent', ''));
    dtColumns.push(DTColumnBuilder.newColumn('classrootname').withTitle('类目').withOption(
        'sDefaultContent', '').withOption("width", '30'));
    $scope.dtColumns = dtColumns;
    $scope.dtOptions.aaData = [];
    $scope.sure = function () {
        if ($scope.dtOptions.aaData.length == 0) {
            notify({
                message: "请选择资产"
            });
            return;
        }
        if ($scope.item.tmodelstatus) {
            if (angular.isDefined($scope.item.tmodel) && $scope.item.tmodel.length > 0) {
            } else {
                notify({
                    message: "请输入型号"
                });
                return;
            }
        }
        $scope.item.processuserid = $scope.adminuserSel.user_id;
        $scope.item.processusername = $scope.adminuserSel.name;
        $scope.item.tbuytime = $scope.date.buytime.format('YYYY-MM-DD');
        $scope.item.items = angular.toJson($scope.dtOptions.aaData);
        if (angular.isDefined($scope.zcsourceSel.dict_item_id)) {
            $scope.item.tzcsource = $scope.zcsourceSel.dict_item_id;
        }
        if (angular.isDefined($scope.supplierSel.dict_item_id)) {
            $scope.item.tsupplier = $scope.supplierSel.dict_item_id;
        }
        if (angular.isDefined($scope.brandSel.dict_item_id)) {
            $scope.item.tbrand = $scope.brandSel.dict_item_id;
        }
        if (angular.isDefined($scope.locSel.dict_item_id)) {
            $scope.item.tloc = $scope.locSel.dict_item_id;
        }
        if (angular.isDefined($scope.classSel.dict_item_id)) {
            $scope.item.tclassid = $scope.classSel.dict_item_id;
        }
        if (angular.isDefined($scope.usefullifeSel.dict_item_id)) {
            $scope.item.tusefullife = $scope.usefullifeSel.dict_item_id;
        }
        if (angular.isDefined($scope.usedcompSel.id)) {
            $scope.item.tusedcompanyid = $scope.usedcompSel.id;
        }
        if (angular.isDefined($scope.usedpartSel.partid)) {
            $scope.item.tpartid = $scope.usedpartSel.partid;
        }
        if (angular.isDefined($scope.useduserSel.user_id)) {
            $scope.item.tuseduserid = $scope.useduserSel.user_id;
        }
        $confirm({
            text: '是否确定变更?'
        }).then(
            function () {
                $http.post($rootScope.project + "/api/zc/resCBasicinformation/ext/insert.do",
                    $scope.item).success(function (res) {
                    if (res.success) {
                        $uibModalInstance.close('OK');
                    }
                    notify({
                        message: res.message
                    });
                })
            });
    }
    $scope.review = function () {
        var mdata = {};
        mdata.id = "";
        mdata.type = "many";
        mdata.datarange = "CG";
        mdata.showusefullife = "true";
        var modalInstance = $uibModal.open({
            backdrop: true,
            templateUrl: 'views/cmdb/modal_common_zclist.html',
            controller: modal_common_ZcListCtl,
            size: 'blg',
            resolve: {
                data: function () {
                    return mdata
                }
            }
        });
        modalInstance.result.then(function (result) {
            $scope.dtOptions.aaData = result;
        }, function (reason) {
            $log.log("reason", reason)
        });
    }
    $scope.filldata = function (id, data2) {
        var data = {};
        for (var i = 0; i < $scope.dtOptions.aaData.length; i++) {
            if ($scope.dtOptions.aaData[i].id == id) {
                data = $scope.dtOptions.aaData[i];
                break;
            }
        }
        $scope.zcsourceSel = "";
        $scope.supplierSel = "";
        $scope.brandSel = "";
        $scope.locSel = "";
        $scope.classSel = "";
        $scope.usefullifeSel = "";
        $scope.usedcompSel = "";
        $scope.usedpartSel = "";
        $scope.useduserSel = "";
        $scope.item.tsn = ""
        $scope.item.tmodel = "";
        $scope.item.tlabel1 = "";
        $scope.item.tunit = "";
        $scope.item.tconfdesc = "";
        $scope.item.tlocdtl = "";
        if (angular.isDefined(data.locdtl)) {
            $scope.item.tlocdtl = data.locdtl;
        }
        if (angular.isDefined(data.unit)) {
            $scope.item.tunit = data.unit;
        }
        if (angular.isDefined(data.confdesc)) {
            $scope.item.tconfdesc = data.confdesc;
        }
        if (angular.isDefined(data.fs1)) {
            $scope.item.tlabel1 = data.fs1;
        }
        if (angular.isDefined(data.sn)) {
            $scope.item.tsn = data.sn;
        }
        if (angular.isDefined(data.zc_cnt)) {
            $scope.item.tzccnt = data.zc_cnt;
        }
        if (angular.isDefined(data.model)) {
            $scope.item.tmodel = data.model;
        }
        if (angular.isDefined(data.buy_timestr)) {
            $scope.date.buytime = moment(data.buy_timestr);
        }
        if (angular.isDefined(data.zcsource)) {
            for (var i = 0; i < $scope.zcsourceOpt.length; i++) {
                if (data.zcsource == $scope.zcsourceOpt[i].dict_item_id) {
                    $scope.zcsourceSel = $scope.zcsourceOpt[i];
                    break;
                }
            }
        }
        if (angular.isDefined(data.supplier)) {
            for (var i = 0; i < $scope.supplierOpt.length; i++) {
                if (data.supplier == $scope.supplierOpt[i].dict_item_id) {
                    $scope.supplierSel = $scope.supplierOpt[i];
                    break;
                }
            }
        }
        if (angular.isDefined(data.brand)) {
            for (var i = 0; i < $scope.brandOpt.length; i++) {
                if (data.brand == $scope.brandOpt[i].dict_item_id) {
                    $scope.brandSel = $scope.brandOpt[i];
                    break;
                }
            }
        }
        if (angular.isDefined(data.loc)) {
            for (var i = 0; i < $scope.locOpt.length; i++) {
                if (data.loc == $scope.locOpt[i].dict_item_id) {
                    $scope.locSel = $scope.locOpt[i];
                    break;
                }
            }
        }
        if (angular.isDefined(data.usefullife)) {
            for (var i = 0; i < $scope.usefullifeOpt.length; i++) {
                if (data.usefullife == $scope.usefullifeOpt[i].dict_item_id) {
                    $scope.usefullifeSel = $scope.usefullifeOpt[i];
                    break;
                }
            }
        }
        if (angular.isDefined(data.class_id)) {
            for (var i = 0; i < $scope.classOpt.length; i++) {
                if (data.class_id == $scope.classOpt[i].dict_item_id) {
                    $scope.classSel = $scope.classOpt[i];
                    break;
                }
            }
        }
        if (angular.isDefined(data.used_company_id)) {
            for (var i = 0; i < $scope.usedcompOpt.length; i++) {
                if (data.used_company_id == $scope.usedcompOpt[i].id) {
                    $scope.usedcompSel = $scope.usedcompOpt[i];
                    break;
                }
            }
        }
        if (angular.isDefined(data.part_id)) {
            for (var i = 0; i < $scope.usedpartOpt.length; i++) {
                if (data.part_id == $scope.usedpartOpt[i].partid) {
                    $scope.usedpartSel = $scope.usedpartOpt[i];
                    break;
                }
            }
        }
        if (angular.isDefined(data.used_userid)) {
            for (var i = 0; i < $scope.useduserOpt.length; i++) {
                if (data.used_userid == $scope.useduserOpt[i].user_id) {
                    $scope.useduserSel = $scope.useduserOpt[i];
                    break;
                }
            }
        }
    }
}

function zccgjbCtl(DTOptionsBuilder, DTColumnBuilder, $compile, $window,
                   $confirm, $log, notify, $scope, $http, $rootScope, $uibModal) {
    var gdict = {};
    var dicts = "zcusefullife,devbrand,devdc,zcsource,zcsupper";
    $http
        .post($rootScope.project + "/api/zc/queryDictFast.do", {
            dicts: dicts,
            parts: "Y",
            partusers: "Y",
            comp: "Y",
            belongcomp: "N",
            classroot: "3",
            uid: "zccgjbd"
        })
        .success(
            function (res) {
                if (res.success) {
                    gdict = res.data;
                } else {
                    notify({
                        message: res.message
                    });
                }
            })
    // 分类
    $scope.dtOptions = DTOptionsBuilder.fromFnPromise().withDataProp('data')
        .withPaginationType('full_numbers').withDisplayLength(100)
        .withOption("ordering", false).withOption("responsive", false)
        .withOption("searching", true).withOption('scrollY', 600)
        .withOption('scrollX', true).withOption('bAutoWidth', true)
        .withOption('scrollCollapse', true).withOption('paging', true)
        .withOption('bStateSave', true).withOption('bProcessing', false)
        .withOption('bFilter', false).withOption('bInfo', false)
        .withOption('serverSide', false).withOption('createdRow', function (row) {
            $compile(angular.element(row).contents())($scope);
        }).withOption(
            'headerCallback',
            function (header) {
                if ((!angular.isDefined($scope.headerCompiled))
                    || $scope.headerCompiled) {
                    $scope.headerCompiled = true;
                    $compile(angular.element(header).contents())
                    ($scope);
                }
            }).withOption("select", {
            style: 'multi',
            selector: 'td:first-child'
        })
    $scope.dtInstance = {}
    $scope.selectCheckBoxAll = function (selected) {
        if (selected) {
            $scope.dtInstance.DataTable.rows().select();
        } else {
            $scope.dtInstance.DataTable.rows().deselect();
        }
    }

    function renderAction(data, type, full) {
        //分配，删除，详情
        if (data == "wait") {
            return "维修中"
        } else if (data == "finish") {
            return "已完成"
        } else {
            return data;
        }
    }

    function renderCheckStatus(data, type, full) {
        if (data == "init") {
            return "等待校验"
        } else if (data == "success") {
            return "成功"
        } else if (data == "failed") {
            return "失败"
        } else {
            return data;
        }
    }

    function renderCGStatus(data, type, full) {
        if (data == "wait") {
            return "未处理"
        } else if (data == "cancel") {
            return "取消"
        } else if (data == "success") {
            return "完成"
        } else if (data == "failed") {
            return "失败"
        } else {
            return data;
        }
    }

    function renderAction(data, type, full) {
        var acthtml = " <div class=\"btn-group\"> ";
        acthtml = acthtml + " <button ng-click=\"detail('"
            + full.busuuid
            + "','" + full.status + "')\" class=\"btn-white btn btn-xs\">变更明细</button>   ";
        acthtml = acthtml + "</div>"
        return acthtml;
    }

    function renderprocess(data, type, full) {
        if (angular.isDefined(data) && data.length() > 0) {
            return "申请详情"
        } else {
            return "无审批"
        }
    }

    var ckHtml = '<input ng-model="selectCheckBoxValue" ng-click="selectCheckBoxAll(selectCheckBoxValue)" type="checkbox">';
    $scope.dtColumns = [
        DTColumnBuilder.newColumn(null).withTitle(ckHtml).withClass(
            'select-checkbox checkbox_center').renderWith(function () {
            return ""
        }),
        DTColumnBuilder.newColumn('id').withTitle('操作').withOption(
            'sDefaultContent', '').renderWith(renderAction),
        DTColumnBuilder.newColumn('busuuid').withTitle('变更单号').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('status').withTitle('办理状态').withOption(
            'sDefaultContent', '').renderWith(renderCGStatus),
        DTColumnBuilder.newColumn('pinst').withTitle('流程详情').withOption(
            'sDefaultContent', '').renderWith(renderprocess),
        DTColumnBuilder.newColumn('processusername').withTitle('处理人').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('tclassfullname').withTitle('资产类别').withOption(
            'sDefaultContent', '').renderWith(renderclass),
        DTColumnBuilder.newColumn('tmodel').withTitle('规格型号').withOption(
            'sDefaultContent', '').renderWith(rendermodel),
        DTColumnBuilder.newColumn('tsn').withTitle('序列').withOption(
            'sDefaultContent', '').renderWith(rendersn),
        DTColumnBuilder.newColumn('tunit').withTitle('计量单位').withOption(
            'sDefaultContent', '').renderWith(renderunit),
        DTColumnBuilder.newColumn('tzccnt').withTitle('数量').withOption(
            'sDefaultContent', '').renderWith(rendercnt),
        DTColumnBuilder.newColumn('tsupplierstr').withTitle('供应商').withOption(
            'sDefaultContent', '').renderWith(rendersupplier),
        DTColumnBuilder.newColumn('tbrandstr').withTitle('品牌').withOption(
            'sDefaultContent', '').renderWith(renderbrand),
        DTColumnBuilder.newColumn('tzcsourcestr').withTitle('来源').withOption(
            'sDefaultContent', '').renderWith(rendersource),
        DTColumnBuilder.newColumn('tlocstr').withTitle('区域').withOption(
            'sDefaultContent', '').renderWith(renderloc),
        DTColumnBuilder.newColumn('tlocdtl').withTitle('位置').withOption(
            'sDefaultContent', '').renderWith(renderlocdtl),
        DTColumnBuilder.newColumn('tusefullifestr').withTitle('使用期限').withOption(
            'sDefaultContent', '').renderWith(renderusefullife),
        DTColumnBuilder.newColumn('tbuytimestr').withTitle('采购日期').withOption(
            'sDefaultContent', '').renderWith(rendercbuytime),
        DTColumnBuilder.newColumn('tconfdesc').withTitle('配置描述').withOption(
            'sDefaultContent', '').renderWith(renderconfdesc),
        DTColumnBuilder.newColumn('tusedcompanyname').withTitle('使用公司').withOption(
            'sDefaultContent', '').renderWith(rendercomp),
        DTColumnBuilder.newColumn('tpartname').withTitle('使用部门').withOption(
            'sDefaultContent', '').renderWith(renderpart),
        DTColumnBuilder.newColumn('tusedusername').withTitle('使用人').withOption(
            'sDefaultContent', '').renderWith(renderuser),
        DTColumnBuilder.newColumn('tlabel1').withTitle('标签1').withOption(
            'sDefaultContent', '').renderWith(renderfs1),
        DTColumnBuilder.newColumn('mark').withTitle('备注').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('createusername').withTitle('操作人').withOption(
            'sDefaultContent', ''),
        DTColumnBuilder.newColumn('create_time').withTitle('创建时间').withOption(
            'sDefaultContent', '')
    ]
    $scope.query = function () {
        flush();
    }
    var meta = {
        tablehide: false,
        tools: [
            {
                id: "btn",
                label: "",
                type: "btn",
                show: true,
                priv: "select",
                template: ' <button ng-click="query()" class="btn btn-sm btn-primary" type="submit">查询</button>'
            },
            {
                id: "btn3",
                label: "",
                type: "btn",
                show: true,
                priv: "insert",
                template: ' <button ng-click="add()" class="btn btn-sm btn-primary" type="submit">新建</button>'
            }]
    }
    $scope.meta = meta;

    function flush() {
        var ps = {};
        $http
            .post($rootScope.project + "/api/zc/resCBasicinformation/ext/selectList.do",
                ps).success(function (res) {
            if (res.success) {
                $scope.dtOptions.aaData = res.data;
            } else {
                notify({
                    message: res.message
                });
            }
        })
    }

    function getSelectRow() {
        var data = $scope.dtInstance.DataTable.rows({
            selected: true
        })[0];
        if (data.length == 0) {
            notify({
                message: "请至少选择一项"
            });
            return;
        } else if (data.length > 1) {
            notify({
                message: "请最多选择一项"
            });
            return;
        } else {
            return $scope.dtOptions.aaData[data[0]];
        }
    }

    $scope.detail = function (uuid, status) {
        meta.uuid = uuid;
        meta.status = status;
        var modalInstance = $uibModal.open({
            backdrop: true,
            templateUrl: 'views/cmdb/modal_cglist.html',
            controller: cgjblistCtl,
            size: 'blg',
            resolve: {
                meta: function () {
                    return meta;
                }
            }
        });
        modalInstance.result.then(function (result) {
            flush();
        }, function (reason) {
            $log.log("reason", reason)
        });
    }

    function action(id) {
        var meta = {};
        meta.id = id;
        meta.dict = gdict;
        var modalInstance = $uibModal.open({
            backdrop: true,
            templateUrl: 'views/cmdb/modal_zccgjbSave.html',
            controller: zccgjbSaveCtl,
            size: 'blg',
            resolve: {
                meta: function () {
                    return meta;
                }
            }
        });
        modalInstance.result.then(function (result) {
            flush();
        }, function (reason) {
            $log.log("reason", reason)
        });
    }

    $scope.add = function () {
        action();
    }
    flush();
};
app.register.controller('zccgjbCtl', zccgjbCtl);
