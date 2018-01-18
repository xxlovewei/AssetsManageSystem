<%@ page language="java" import="com.dt.module.om.term.entity.Machine"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Machine currentMachine = (Machine) request.getSession().getAttribute("currentMachine");
	if(currentMachine==null){
		System.out.println("Machine data is blank");
	}	 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<link rel="stylesheet"
	href="<%=path%>/console/plugin/term/bootstrap.min.css">
<link rel="stylesheet" href="<%=path%>/console/plugin/term/style.css">
<script src="<%=path%>/console/plugin/term/jquery-2.1.4.min.js"></script>
<script src="<%=path%>/console/plugin/term/bootstrap.min.js"></script>
<script src="<%=path%>/console/plugin/term/term.js"></script>
<!--[if lt IE 9]>
	  <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
	  <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
<title>${currentMachine.hostname }@${currentMachine.username }</title>
<script type="text/javascript">
document.onkeydown = function (e) {
    var ev = window.event || e;
    var code = ev.keyCode || ev.which;
    if (code == 116) {
        if(ev.preventDefault) {
            ev.preventDefault();
        } else {
            ev.keyCode = 0;
            ev.returnValue = false;
        }
    }
}
</script>
</head>
<body>
	<div class="wrapper">
		<div class="container">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h3 class="panel-title">${currentMachine.hostname }@${currentMachine.username }</h3>
					</div>
					<div class="panel-body"
						style="color: red; min-height: 500px; background: black; padding: 0; overflow: hidden">
						<div id="content">
							<div id="term"></div>
							<div style="">
								<textarea class="form-control" id="cmd"
									style="background: #FFFF99; resize: none; border-radius: 0;"></textarea>
								<span class="pull-left" style="margin-top: 6px">特殊字符↑↑↑&nbsp;</span>
								<button type="button" style="border-radius: 0;"
									class="btn btn-danger pull-right" id="stop">关闭</button>
								<button type="button" style="border-radius: 0;"
									class="btn btn-success pull-right" id="send">发送</button>
							</div>
						</div>
					</div>
				</div>
			 
		</div>
		<div class="push"></div>
	</div>
</body>
<script type="text/javascript">
	$('#send').click(function(){
		$cmd = $('#cmd').val();
		webSocket.send($cmd);
		$('#cmd').val("");
	})
	
	$('#stop').click(function(){
		webSocket.send("exit");
		term.write("\nConnection closed.");
		$.get('stopConnection');
	})
	
	var webSocket = 
		new WebSocket("ws://<%=request.getServerName() + ":" + request.getServerPort() + path%>/term");

		webSocket.onerror = function(event) {
			onError(event);
		};

		webSocket.onopen = function(event) {
			onOpen(event)
		};

		webSocket.onmessage = function(event) {
			onMessage(event)
		};

		webSocket.onclose = function(event) {
			onClose(event)
		};

		var rows = -Math.floor(346 / 14);

		function onMessage(event) {
			term.write(event.data);
		}

		function onOpen(event) {
			 console.log("websocket连接上");
		}

		function onError(event) {
			 console.log("websocket错误");
			$.get('stopConnection');
		}

		function onClose(event) {
			 console.log("websocket关闭");
			$.get('stopConnection');
		}

		$width = $('.panel-body').width();
		$height = $('.panel-body').height();
		var term = new Terminal({
			cols : Math.floor($width / 7.25),
			rows : Math.floor($height / 17.42),
			screenKeys : false,
			useStyle : true,
			cursorBlink : true,
			convertEol : true
		});
		term.open($("#term").empty()[0]);
		term.on('data', function(data) {
			webSocket.send(data);
		});
	</script>


</html>