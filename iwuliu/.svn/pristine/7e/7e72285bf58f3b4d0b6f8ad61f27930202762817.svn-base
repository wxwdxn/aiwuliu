/**
 * Created by YK on 2016/12/14.
 */
// 基于准备好的dom，初始化echarts实例
var driverAttendanceChart = echarts.init(document.getElementById('driverAttendance'));

// 指定图表的配置项和数据
var driverAttendance_option = {
    title: {
        text: '司机出勤率统计',
        subtext: '瞪羚科技',
        x:'center'
    },
    tooltip: {
        trigger: 'item',
        formatter:function (params,ticket,callback) {
            var res = '人数:' + params.value+'人';
            setTimeout(function (){callback(ticket, res);}, 100);
            return 'loading';
        }
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataView : {show: true},
            magicType : {show: true, type: ['line', 'bar']},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    xAxis: {
        axisLabel : {
            show:true,
            interval: 'auto',    // {number}
            rotate:0,
            margin: 8,
            textStyle: {
                color: 'black',
                fontSize: 10,
                fontStyle: 'normal',
                fontWeight: 'bold'
            }
        },
        splitLine : {
            show:true,
            lineStyle: {
                color: '#483d8b',
                type: 'dashed',

            }
        },
        data: ["60%以下","60%~69%","70%~79%","80%~89%","90%~99%","100%"]
    },
    yAxis : [
        {
            type : 'value',
            position: 'left',
            //min: 0,
            //max: 300,
            //splitNumber: 5,
            boundaryGap: [0,0.1],
            axisLine : {    // 轴线
                show: true,
                lineStyle: {
                    color: 'red',
                    type: 'dashed',
                    width: 2
                }
            },
            axisTick : {    // 轴标记
                show:true,
                length: 10,
                lineStyle: {
                    color: 'green',
                    type: 'solid',
                    width: 2
                }
            },
            axisLabel : {
                show:true,
                interval: 'auto',    // {number}
                rotate: 0,
                margin: 18,
                formatter: '{value} 人',    // Template formatter!
                textStyle: {
                    color: '#1e90ff',
                    fontSize: 10,
                    fontStyle: 'normal',
                    fontWeight: 'bold'
                }
            },
            splitLine : {
                show:true,
                lineStyle: {
                    color: '#483d8b',
                    type: 'dotted',
                    width: 2
                }
            }
        }
    ],
    series: [{
        name: '司机人数',
        type: 'bar',
        data: [2, 7, 25, 47, 18, 1],
        itemStyle: {
            normal: {
                color: function(params) {
                    // build a color map as your need.
                    var colorList = [
                        '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
                        '#9BCA63','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                        '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
                    ];
                    return colorList[params.dataIndex]
                }
            }
        }
    }]
};
window.onresize = function(){
    driverAttendanceChart.resize();
};
// 使用刚指定的配置项和数据显示图表。
driverAttendanceChart.setOption(driverAttendance_option);
