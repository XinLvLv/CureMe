var myChart = echarts.init(document.getElementById("blood-pressure"));

$.get('aqi-beijing.json', function (data) {
    myChart.setOption(option = {
        tooltip: {
            trigger: 'axis'
        },
        xAxis: {
            data: data.map(function (item) {
                return item[0];
            })
        },
        yAxis: {
            splitLine: {
                show: false
            }
        },
        toolbox: {
            left: 'center',
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                restore: {},
                saveAsImage: {}
            }
        },
        dataZoom: [{
            startValue: '2014-06-01'
        }, {
            type: 'inside'
        }],
        visualMap: {
            top: 10,
            right: 10,
            pieces: [{
                gt: 150,
                lte: 200,
                color: '#cc0033'
            }],
            outOfRange: {
                color: '#999'
            }
        },
        series: {
            name: 'blood pressure',
            type: 'line',
            data: data.map(function (item) {
                return item[1];
            }),
            markLine: {
                silent: true,
                data: [ {
                    yAxis: 150
                }, {
                    yAxis: 200
                }]
            }
        }
    });
});