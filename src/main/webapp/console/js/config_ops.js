function config_ops($stateProvider, $ocLazyLoadProvider) {
    console.log("App ops config");
    $ocLazyLoadProvider.config({
        debug: true
    });
    $stateProvider.state('hmgr', {
        abstract: true,
        url: "/hmgr",
        templateUrl: "views/common/content.html?v=" + version
    }).state('hmgr.hlist', {
        url: "hmgr_hlist",
        data: {pageTitle: '主机管理'},
        templateUrl: "views/ops/hostmgr.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/ops/hostmgr.js?v=' + version]
                }]);
            }
        }
    });
    $stateProvider.state('dbmgr', {
        abstract: true,
        url: "/dbmgr",
        templateUrl: "views/common/content.html?v=" + version
    }).state('dbmgr.dblist', {
        url: "dbmgr_dblist",
        data: {pageTitle: '数据库管理'},
        templateUrl: "views/ops/dbbackup.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/ops/dbbackup.js?v=' + version]
                }]);
            }
        }
    });
    $stateProvider.state('dtj', {
        abstract: true,
        url: "/dtj",
        templateUrl: "views/common/content.html?v=" + version
    }).state('dtj.hd', {
        url: "dtj_hd",
        data: {pageTitle: '系统统计'},
        templateUrl: "views/ops/xttj.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([
                    {
                        serie: true,
                        name: 'angular-flot',
                        files: ['plugin/flot/jquery.flot.js', 'plugin/flot/jquery.flot.time.js', 'plugin/flot/jquery.flot.tooltip.min.js', 'plugin/flot/jquery.flot.spline.js', 'plugin/flot/jquery.flot.resize.js', 'plugin/flot/jquery.flot.pie.js', 'plugin/flot/curvedLines.js', 'plugin/flot/angular-flot.js', 'plugin/flot/jquery.flot.barnumbers.js']
                    },
                    {
                        serie: true,
                        files: ['views/ops/xttj.js?v=' + version]
                    }]);
            }
        }
    })
    $stateProvider.state('infosys', {
        abstract: true,
        url: "/infosys",
        templateUrl: "views/common/content.html?v=" + version
    }).state('infosys.infosys', {
        url: "infosys_infosys",
        data: {pageTitle: '业务系统'},
        templateUrl: "views/ops/infosys.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([
                    {
                        serie: true,
                        files: ['views/ops/infosys.js?v=' + version]
                    }]);
            }
        }
    })
}