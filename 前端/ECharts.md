## ECharts

> https://echarts.apache.org/zh/index.html

#### 1. 安装

```
yarn add echarts

// 引入 echarts.js
<script src="echarts.min.js"></script>
```

#### 2. 初始化

```
// 为ECharts准备一个具备大小（宽高）的Dom
<div id="main" style="width: 600px;height:400px;"></div>

echarts.init(document.getElementById('main'))
```

#### 3. 渲染

```
// 指定图表的配置项和数据
var option = {
    title: {
        text: 'ECharts 入门示例'
    },
    tooltip: {},
    legend: {
        data:['销量']
    },
    xAxis: {
        data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
    },
    yAxis: {},
    series: [{
        name: '销量',
        type: 'bar',
        data: [5, 20, 36, 10, 10, 20]
    }]
};

myChart.setOption(option);
```

