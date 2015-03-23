<!DOCTYPE html>
<html>
    <head>
        <title>Comeon</title>
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css" />
        <style type="text/css">
            p {width:100px;margin:0 auto;}
            #container {width:100%;}
            #rndLong, #rndStr {border:1px solid #000; width: 300px; height: 30px; margin: 5px auto;}
        </style>
        <script type="application/javascript">
            var ws = null;

            if ('WebSocket' in window) {
                ws = new WebSocket('ws://localhost:8080/comeon2/simulate/');
            } else {
                alert('WebSocket is not supported by this browser.');
            }

            ws.onopen = function () {
                console.log('connected');
            };

            ws.onmessage = function (event) {
                var data = JSON.parse(event.data)
                document.getElementById("rndLong").innerHTML = data.rndLong;
                document.getElementById("rndStr").innerHTML = data.rndStr;
            };
        </script>
    </head>
    <body>
        <div id="container">
            <p><img src="img/dice.gif" /></p>
            <div id="rndLong"></div>
            <div id="rndStr"></div>
        </div>
    </body>
</html>