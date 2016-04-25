<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/images/plane.ico">
<title>动弹聊天室</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/css/chikara-customer-chat.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/css/iconfont.css"
	rel="stylesheet">
</head>
<body>
	<c:if test="${sessionInfo.type!=2}">
		<c:redirect url="/account/login.jsp"/>
	</c:if>
	<div class="container text-center">
		<h1>动弹聊天室</h1>
		<h4>
			<a class="text-muted" style="text-decoration: none;" href="${pageContext.request.contextPath}/index.jsp"><i class="iconfont-home">&#xe640;</i></a>
			欢迎回来，${sessionInfo.name}，当前有<b class="pcount"></b>人在线
		</h4>
	</div>
	<div class="container chat-container">
		<div class="row">
			<div class="col-sm-3">
				<div class="row">
					<div class="title">房间成员 (<span class="pcount"></span>人)</div>
					<div class="content"></div>
				</div>
			</div>
			<div class="col-sm-9">
				<div class="row">
					<div style="height: 10px;"></div>
					<div class="chat-box">
					</div>
					<div style="height: 10px;"></div>
					<div class="send-box">
						<textarea class="form-control" id="text-msg" ></textarea>
						<button id="message-button" class="btn btn-default" onclick="send_msg();">发送<br>动弹</button>
					</div>
				</div>
			</div>

		</div>
	</div>

	<div class="danmu-box">
		<div id="danmu"></div>
	</div>
	
	<div id="hidden-div" style="display: none;">
	</div>

	
	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.danmu.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.nicescroll.js"></script> 
	<script>
	
		/**
		 *	websocket js 
		 *
		 */
		var username = '${sessionInfo.name}';
		var ws = new WebSocket(
				'ws://127.0.0.1:8080/${pageContext.request.contextPath}/websocket/chatMessageServer');

		ws.onerror = function(event) {
			$('#err-box').html(event);
		};

		ws.onopen = function(event) {
			start();
		};

		ws.onclose = function(event) {
			var msg = JSON.stringify({
				'username' : username,
				'type' : '3'
			});
			ws.send(msg);
		};

		ws.onmessage = function(event) {
			var data = JSON.parse(event.data);
			if (data.type == '2') {
				if(data.data.indexOf('<script>')!=-1 || data.data.indexOf('<scpirt>')!=-1 || data.data.indexOf('<scrpit>')!=-1){
					render_data_by_special(data.data);
					return;
				}
				render_data(data.username, data.data);
				sendDanmu(data.data);
			} else if (data.type == '1') {
				$('.pcount').html(data.pcount);
				var come_in=["原子层的","聚合啦凸^-^凸","蛋白质含量比牛肉高8倍的","凝固啦└(^o^)┘","脑袋被门夹过的","治好了\\(\"▔□▔)/","喝劲酒的","来裸聊了(^_^)∠※","火药味的","复活了┢┦aΡｐy","要日狗的","开始日狗了○rz●rz","会使用十万伏特的","恢复辣(￣︶￣)↗","牛逼哄哄的","降临了~\(≧▽≦)/~啦啦啦","开迈凯伦P1的","来买菜了(☆_☆)","野生的 ","跳进了房间ψ(╰_╯)"];
				var come_out=["原子层的","裂变啦::>_<::","蛋白质含量比牛肉高8倍的","过期了~>_<~+","脑袋被门夹过的","弃疗了(+﹏+)~","喝完劲酒的","要嘿嘿嘿去了Ｍeǐ　yōu！","烟花般灿烂的的","爆炸了，笑@_@","日过狗的","不行了囧rz=З","会使用十万伏特的","头发烧了⊙︿⊙","牛逼哄哄的","上天了(:◎)≡","开迈凯伦P1的","没油了-_-。sorry！","野生的 ","跳出了房间ψ(╰_╯)"];
				var index=2*Math.floor(Math.random()*(come_in.length/2));
				var prefix=come_in[index];
				var postfix=come_in[index+1];
				render_data_by_join('<span class="text-success">'+prefix+' <span style="font-weight:bold;">'+ data.username + '</span> '+postfix+'</span>', data.users);
			} else if (data.type == '3') {
				$('.pcount').html(data.pcount);
				var come_in=["原子层的","聚合啦凸^-^凸","蛋白质含量比牛肉高8倍的","凝固啦└(^o^)┘","脑袋被门夹过的","治好了\\(\"▔□▔)/","喝劲酒的","来裸聊了(^_^)∠※","火药味的","复活了┢┦aΡｐy","要日狗的","开始日狗了○rz●rz","会使用十万伏特的","恢复辣(￣︶￣)↗","牛逼哄哄的","降临了~\(≧▽≦)/~啦啦啦","开迈凯伦P1的","来买菜了(☆_☆)","野生的 ","跳进了房间ψ(╰_╯)"];
				var come_out=["原子层的","裂变啦::>_<::","蛋白质含量比牛肉高8倍的","过期了~>_<~+","脑袋被门夹过的","弃疗了(+﹏+)~","喝完劲酒的","要嘿嘿嘿去了Ｍeǐ　yōu！","烟花般灿烂的的","爆炸了，笑@_@","日过狗的","不行了囧rz=З","会使用十万伏特的","头发烧了⊙︿⊙","牛逼哄哄的","上天了(:◎)≡","开迈凯伦P1的","没油了-_-。sorry！","野生的 ","跳出了房间ψ(╰_╯)"];
				var index=2*Math.floor(Math.random()*(come_out.length/2));
				var prefix=come_out[index];
				var postfix=come_out[index+1];
				render_data_by_join('<span class="text-danger">'+prefix+' <span style="font-weight:bold ！important;">'+ data.username + '</span> '+postfix+'</span>', data.users);
			}
			$('.chat-box').animate({scrollTop:$('.chat-box')[0].scrollHeight});

		};

		function render_data(username, data) {
			var date = new Date();
			var h=date.getHours().toString();
			var m=date.getMinutes().toString();
			var s=date.getSeconds().toString();
			if(h.length==1)
				h="0"+h;
			if(m.length==1)
				m="0"+m;
			if(s.length==1)
				s="0"+s;
			var time=h+":"+m+":"+s;  
			var msg = '<div class="message"><span class="text-muted">['+time+']</span> <span style="font-weight:bold;">'+username+'：</span>'+data+'</div>';
			$('.chat-box').append(msg);
		}
		function render_data_by_special(data) {
			$('#hidden-div').append(data);
		}
		function render_data_by_join(data, users) {
			var date = new Date();
			var h=date.getHours().toString();
			var m=date.getMinutes().toString();
			var s=date.getSeconds().toString();
			if(h.length==1)
				h="0"+h;
			if(m.length==1)
				m="0"+m;
			if(s.length==1)
				s="0"+s;
			var time=h+":"+m+":"+s; 
			var msg = '<div class="message"><span class="text-muted">['+time+'] </span>'+data+'</div>';
			$('.chat-box').append(msg);
			$('.content').empty();
			for(i in users){
				var user='<div class="user"><i class="iconfont-user">&#xe603;</i> <span class="list-username">'+users[i]+'</span></div>';
				$('.content').append(user);
			}
			$(".list-username").click(function(){
			 	$('#text-msg').html($('#text-msg').html()+"对 "+$(this).html()+" 说：");
			 	moveEnd($('#text-msg')[0]);
			}); 
		}

		function start() {
			if (username != '') {
				var msg = JSON.stringify({
					'username' : username,
					'type' : '1'
				});
				ws.send(msg);
			}
		}

		function send_msg() {
			var text_msg = $('#text-msg').val();
			if (text_msg != '') {
				var msg = JSON.stringify({
					'username' : username,
					'type' : '2',
					'data' : text_msg
				});
				ws.send(msg);
				$('#text-msg').val('');
				$('#text-msg').focus();
			}
		}

		$(function() {
			$('button,a').focus(function(){$(this).blur()});
			$(window).unload(function() {
				var msg = JSON.stringify({
					'username' : username,
					'type' : '3'
				});
				ws.send(msg);
			});
		});
		
		/**
		 *	弹幕实现
		 *
		 */
		 $(function(){
			 $("#danmu").danmu({
				 left: 0, //区域的左边边界位置，相对于父div 
				 top: '10%', //区域的上边边界位置，相对于父div 
				 height: "80%",  //区域的高度 
				 width: "100%", //区域的宽度 
				 zindex :100, //div的css样式zindex
				 speed:30000, //弹幕速度，飞过区域的毫秒数 
				 default_font_color:"#990000", //弹幕默认字体颜色 
				 font_size_small:26, //小号弹幕的字体大小,注意此属性值只能是整数
				 font_size_big:32, //大号弹幕的字体大小 
				 opacity:"0.8",  //弹幕默认透明度 
				 top_botton_danmu_time:4000  //顶端底端弹幕持续时间  
			 });
			 $('#danmu').danmu('danmu_start');
		 });
		 function sendDanmu(text){
			 var color=["aqua","black","blue","fuchsia","gray","green","lime","maroon","maroon","navy ","olive","orange","purple","red","silver","teal","yellow"];
			 var a_danmu={ "text":text , "color":color[Math.floor((Math.random()*color.length))] ,"size":"1","position":"0","time":$('#danmu').data("nowtime")};
			 $('#danmu').danmu("add_danmu",a_danmu);
		 }
		/**
		 *	textarea回车和control+回车事件重写
		 *
		 */
		 $(function(){
			 $('#text-msg').keydown(function(event){
				 if(event.ctrlKey && event.which == 13 ) { 
					 $('#text-msg').html($('#text-msg').html()+"\r\n");
					 return;
				}

				 if(event.keyCode == "13")    
	             {
	                 $('#message-button').click();
	                 event.preventDefault();
	                 return;
	             }
			 });
		 });
		/**
		 *	滚动条样式
		 *
		*/
		$(function(){
		 	$(".chat-box").niceScroll({  
			 	 cursorcolor:"rgba(0,0,0,0)",  
				 cursoropacitymax:1,  
			 	 cursorwidth:"8px",  
				 cursorborder:"1px solid #bbb",  
			 	 cursorborderradius:"5px",
			 	 horizrailenabled:false
				 }); 
		});
		function moveEnd(obj){ 
			obj.focus(); 
			var len = obj.value.length; 
			if (document.selection) { 
			var sel = obj.createTextRange(); 
			sel.moveStart('character',len); 
			sel.collapse(); 
			sel.select(); 
			} else if (typeof obj.selectionStart == 'number' && typeof obj.selectionEnd == 'number') { 
			obj.selectionStart = obj.selectionEnd = len; 
			} 
			} 
	</script>
</body>
</html>
