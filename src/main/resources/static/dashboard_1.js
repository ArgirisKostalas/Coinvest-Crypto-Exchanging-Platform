
$(function() {
    "use strict";
    // ============================================================== 
    // Newsletter
    // ============================================================== 

    var xmlhttp = new XMLHttpRequest();
    var url = "https://min-api.cryptocompare.com/data/v2/histominute?fsym=BTC&tsym=EUR&limit=50&api_key=b03db73eaa6a6957380ddf0707beb220a216eb767e059f214de61fcb2ad6b93e"
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
    xmlhttp.onreadystatechange = function(){
        if (this.readyState == 4 && this.status == 200){
            var data = JSON.parse(this.responseText);
            //console.log(data);
            var time = data.Data.map(function(elem){
                return elem.time;
            })
            //console.log(time);
        }
    }
    var chart = new Chartist.Line('.campaign', {
        labels: [1, 2, 3, 4, 5, 6, 7, 8],
        series: [
            [0, 5, 6, 8, 25, 9, 8, 24],
            [0, 3, 1, 2, 8, 1, 5, 1]
        ]
    }, {
        low: 0,
        high: 28,

        showArea: true,
        fullWidth: true,
        plugins: [
            Chartist.plugins.tooltip()
        ],
        axisY: {
            onlyInteger: true,
            scaleMinSpace: 10,
            offset: 20,
            labelInterpolationFnc: function(value) {
                return (value / 1) + 'k';
            }
        },

    });

    // Offset x1 a tiny amount so that the straight stroke gets a bounding box
    // Straight lines don't get a bounding box 
    // Last remark on -> http://www.w3.org/TR/SVG11/coords.html#ObjectBoundingBox
    chart.on('draw', function(ctx) {
        if (ctx.type === 'area') {
            ctx.element.attr({
                x1: ctx.x1 + 0.001
            });
        }
    });

    // Create the gradient definition on created event (always after chart re-render)
    chart.on('created', function(ctx) {
        var defs = ctx.svg.elem('defs');
        defs.elem('linearGradient', {
            id: 'gradient',
            x1: 0,
            y1: 1,
            x2: 0,
            y2: 0
        }).elem('stop', {
            offset: 0,
            'stop-color': 'rgba(255, 255, 255, 1)'
        }).parent().elem('stop', {
            offset: 1,
            'stop-color': 'rgba(64, 196, 255, 1)'
        });
    });


    var chart = [chart];
});