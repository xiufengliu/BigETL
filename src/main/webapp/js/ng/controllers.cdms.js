'use strict';

appControllers.controller('TimeSeriesController', ['$scope', '$http', '$log', 'scriptLoader',
    function ($scope, $http, $log, scriptLoader) {
        $scope.populateForm = function () {
            $scope.errors = {pageError:'',formErrors:{}};
            $http.get('api/timeseries/form').
                success(function (data, status) {
                    $scope.metricNames = data['metricNames'];
                    $scope.tagValues = data['tagValues'];
                });
        };

        $scope.submit = function () {
           $scope.errors = {pageError:'',formErrors:{}};
            $http({
                method: 'POST',
                url: 'api/timeseries/query',
                data: {
                    metricName: $scope.selectedMetricName,
                    tagValue: $scope.selectedTagValue,
                    aggregateBy: $scope.selectedAggregateBy,
                    startTime: $scope.startTime,
                    endTime: $scope.endTime
                }
            }).success(function (data, status) {

                $scope.chartConfig = {
                    options: {
                        chart: {
                            type: 'area',
                            zoomType: 'x'
                       }
                    },
                    title: {
                        text: data['title']
                    },
                    subtitle: {
                        text: document.ontouchstart === undefined ?
                            'Click and drag in the plot area to zoom in' :
                            'Pinch the chart to zoom in'
                    },
                    xAxis: {
                        type: 'datetime',
                        minRange: data['minRange'],
                        dateTimeLabelFormats: {
                            second: '%H:%M:%S',
                            minute: '%H:%M',
                            hour: '%H:%M',
                            day: '%e. %b',
                            week: '%e. %b',
                            month: '%b \'%y', //month formatted as month only
                            year: '%Y'
                        }
                    },
                    yAxis: {
                        title: {
                            text: 'Consumption'
                        }
                    },
                    plotOptions: {
                        area: {
                            fillColor: {
                                linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1},
                                stops: [
                                    [0, Highcharts.getOptions().colors[0]],
                                    [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                                ]
                            },
                            marker: {
                                radius: 2
                            },
                            lineWidth: 1,
                            states: {
                                hover: {
                                    lineWidth: 1
                                }
                            },
                            threshold: null
                        }
                    },
                    loading: false,
                    series: data['series']
                }
            }).catch(function (res, status) {
                $scope.handleErrors(res.data);
            });
        };

        $scope.handleErrors = function (data) {
            if (data.message) {// The overall error message
                $scope.errors.pageError = data.message;
            }
            $scope.errors.formErrors = {};
            if (data.errors) {//Form input error message
                for (var i = 0; i < data.errors.length; i++) {
                    $scope.errors.formErrors[data.errors[i].key] = data.errors[i].message;
                }
            }
        };

        $scope.populateForm();
    }]);
