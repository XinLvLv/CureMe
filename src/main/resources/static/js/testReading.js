var dom = document.getElementById("container");
var myChart = echarts.init(dom);
var app = {};
var option;


$.get('http://localhost:8080/reading/pulse/10', function (data) {
    myChart.setOption(
        option = {
            title: {
                text: 'Pulse',
                left: '1%'
            },
            tooltip: {
                trigger: 'axis'
            },
            grid: {
                left: '5%',
                right: '15%',
                bottom: '10%'
            },
            xAxis: {
                data: data.each(data, function (item) {
                    return item.datetime;
                })
            },
            yAxis: {},
            toolbox: {
                right: 10,
                feature: {
                    dataZoom: {
                        yAxisIndex: 'none'
                    },
                    restore: {},
                    saveAsImage: {}
                }
            },
            dataZoom: [{
                //startValue: '2014-06-01'
            }, {
                type: 'inside'
            }],
            visualMap: {
                top: 50,
                right: 10,
                pieces: [{
                    gt: 0,
                    lte: 60,
                    color: '#FD0100'
                }, {
                    gt: 60,
                    lte: 100,
                    color: ' #93CE07'
                }, {
                    gt: 100,
                    color: '#FD0100'
                }],
                outOfRange: {
                    color: '#999'
                }
            },
            series: {
                name: 'Pulse',
                type: 'line',
                data: data.each(data, function (item) {
                    return item.pulse;
                }),
                markLine: {
                    silent: true,
                    lineStyle: {
                        color: '#333'
                    },
                    data: [{
                        yAxis: 60
                    }, {
                        yAxis: 100
                    }]
                }
            }
        });
});

if (option && typeof option === 'object') {
    myChart.setOption(option);
}
