function zcindexCtl(DTOptionsBuilder, DTColumnBuilder, $compile, $confirm,
                    $log, notify, $scope, $http, $rootScope, $uibModal, $window, $state) {
    $scope.item = {};
    $scope.item.lywait_cnt = 0;
    $scope.item.jywait_cnt = 0;
    $scope.item.dbwait_cnt = 0;
    $scope.item.bx_cnt = 0;
    $scope.item.zc_cnt = 0;
    $scope.item.zc_money = 0;
    $http.post($rootScope.project + "/api/zc/report/dashboard.do", {})
        .success(function (res) {
            if (res.success) {
                $scope.item = res.data;
                $scope.item.zc_money = 0;
                var ticks = res.data.chart_meta;
                var data = res.data.chart_data;
                var dataset = [{
                    label: "资产状态",
                    data: data,
                    color: "#5482FF"
                }];
                var barOptions = {
                    series: {
                        bars: {
                            show: true,
                            barWidth: 0.6,
                            fill: true,
                            fillColor: {
                                colors: [{
                                    opacity: 0.8
                                }, {
                                    opacity: 0.8
                                }]
                            },
                            align: "center",
                            numbers: {
                                show: true
                            }
                        }
                    },
                    bars: {
                        align: "center",
                        fill: 1,
                        barWidth: 0.5
                    },
                    grid: {
                        hoverable: true,
                        borderWidth: 2,
                        backgroundColor: {
                            colors: ["#ffffff", "#EDF5FF"]
                        }
                    },
                    xaxis: {
                        axisLabel: "",
                        axisLabelUseCanvas: true,
                        axisLabelFontSizePixels: 12,
                        axisLabelFontFamily: 'Verdana, Arial',
                        axisLabelPadding: 10,
                        tickSize: 2,
                        ticks: ticks
                    },
                    legend: {
                        show: false
                    },
                    tooltip: true,
                    tooltipOpts: {
                        content: "数量: %y"
                    }
                };
                $scope.charts = {};
                $scope.charts.flotChartData = dataset;
                $scope.charts.flotBarOptions = barOptions;
                var part_ticks = res.data.part_chart_meta;
                var part_data = res.data.part_chart_data;
                var part_dataset = [{
                    label: "资产统计",
                    data: part_data,
                    color: "#5482FF"
                }];
                var partbarOptions = {
                    series: {
                        bars: {
                            show: true,
                            barWidth: 0.6,
                            fill: true,
                            fillColor: {
                                colors: [{
                                    opacity: 0.8
                                }, {
                                    opacity: 0.8
                                }]
                            },
                            align: "center",
                            numbers: {
                                show: true
                            }
                        }
                    },
                    bars: {
                        align: "center",
                        fill: 1,
                        barWidth: 0.5
                    },
                    grid: {
                        hoverable: true,
                        borderWidth: 2,
                        backgroundColor: {
                            colors: ["#ffffff", "#EDF5FF"]
                        }
                    },
                    xaxis: {
                        axisLabel: "",
                        axisLabelUseCanvas: true,
                        axisLabelFontSizePixels: 12,
                        axisLabelFontFamily: 'Verdana, Arial',
                        axisLabelPadding: 10,
                        tickSize: 2,
                        ticks: part_ticks
                    },
                    legend: {
                        show: false
                    },
                    tooltip: true,
                    tooltipOpts: {
                        content: "数量: %y"
                    }
                };
                $scope.charts.partflotChartData = part_dataset;
                $scope.charts.partflotBarOptions = partbarOptions;
                var cat_ticks = res.data.cat_chart_meta;
                var cat_data = res.data.cat_chart_data;
                var cat_dataset = [{
                    label: "资产分类统计",
                    data: cat_data,
                    color: "#5482FF"
                }];
                var catbarOptions = {
                    series: {
                        bars: {
                            show: true,
                            barWidth: 0.6,
                            fill: true,
                            fillColor: {
                                colors: [{
                                    opacity: 0.8
                                }, {
                                    opacity: 0.8
                                }]
                            },
                            align: "center",
                            numbers: {
                                show: true
                            }
                        }
                    },
                    bars: {
                        align: "center",
                        fill: 1,
                        barWidth: 0.5
                    },
                    grid: {
                        hoverable: true,
                        borderWidth: 2,
                        backgroundColor: {
                            colors: ["#ffffff", "#EDF5FF"]
                        }
                    },
                    xaxis: {
                        axisLabel: "",
                        axisLabelUseCanvas: true,
                        axisLabelFontSizePixels: 12,
                        axisLabelFontFamily: 'Verdana, Arial',
                        axisLabelPadding: 10,
                        tickSize: 2,
                        ticks: cat_ticks
                    },
                    legend: {
                        show: false
                    },
                    tooltip: true,
                    tooltipOpts: {
                        content: "数量: %y"
                    }
                };
                $scope.charts.catflotChartData = cat_dataset;
                $scope.charts.catflotBarOptions = catbarOptions
            } else {
                notify({
                    message: res.message
                });
            }
        })
};
app.register.controller('zcindexCtl', zcindexCtl);