function config_monitor($stateProvider, $ocLazyLoadProvider) {
    console.log("App Monitpr config");
    $ocLazyLoadProvider.config({
        debug: false
    });
    $stateProvider.state('zbxindex', {
        url: "/zbxindex",
        templateUrl: "views/monitor/dashboard.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([
                    {
                        serie: true,
                        name: 'angular-flot',
                        files: ['plugin/flot/jquery.flot.js', 'plugin/flot/jquery.flot.time.js', 'plugin/flot/jquery.flot.tooltip.min.js', 'plugin/flot/jquery.flot.spline.js', 'plugin/flot/jquery.flot.resize.js', 'plugin/flot/jquery.flot.pie.js', 'plugin/flot/curvedLines.js', 'plugin/flot/angular-flot.js',]
                    },
                    {
                        serie: true,
                        files: ['views/monitor/dashboard.js?v=' + version]
                    }]);
            }
        }
    });
    $stateProvider.state('zbxresmgr', {
        abstract: true,
        url: "/zbxresmgr",
        templateUrl: "views/common/content.html?v=" + version
    }).state('zbxresmgr.host', {
        url: "/zbxresmgr_host",
        data: {pageTitle: '主机'},
        templateUrl: "views/monitor/host.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/monitor/host.js?v=' + version]
                }]);
            }
        }
    }).state('zbxresmgr.hostgroup', {
        url: "/zbxresmgr_hostgroup",
        data: {pageTitle: '主机组'},
        templateUrl: "views/monitor/hostgroup.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/monitor/hostgroup.js?v=' + version]
                }]);
            }
        }
    }).state('zbxresmgr.templ', {
        url: "/zbxresmgr_templ",
        data: {pageTitle: '模版'},
        templateUrl: "views/monitor/tpls.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/monitor/tpls.js?v=' + version]
                }]);
            }
        }
    }).state('zbxresmgr.graphmgr', {
        url: "/zbxresmgr_graphmgr",
        data: {pageTitle: "图形"},
        templateUrl: "views/monitor/hostgraph.html?v=" + version,
        resolve: {
            loadPlugin: function ($ocLazyLoad) {
                return $ocLazyLoad.load([{
                    serie: true,
                    files: ['views/monitor/hostgraph.js?v=' + version]
                }]);
            }
        }
    });
}
