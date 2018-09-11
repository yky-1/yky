<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>添加新闻</title>
	<script src="/mynews/plugin/utf8-jsp/ueditor.config.js" type="text/javascript"></script>
	<script src="/mynews/plugin/utf8-jsp/ueditor.all.min.js" type="text/javascript"></script>
	<script src="/mynews/plugin/utf8-jsp/lang/zh-cn/zh-cn.js" type="text/javascript"></script>

</head>
  <body>
  
   <div style="padding: 10px;background-color: #f7f7f7;width:756px;margin:0 auto">
   <p>在下面添加新闻信息：</p>
		<form action="/mynews/servlet/newsServlet?type=addnews" method="post">
		<div class="line">
			标题：<input type="text" name="title">
		</div>
		<div>
			类型：<select name="newstype" id="">
					<option value="国际">国际</option>
					<option value="社会">社会</option>
					<option value="体育">体育</option>
				</select>
		</div>
		<div>
			作者：<input type="text" name="author">
		</div>	
		<div>
			日期：<input type="datetime-local" name="time">
		</div>
		<div>
			<script type="text/javascript">
    			var ue = UE.getEditor('container');
  			 </script>
 			<script id="container" type="text/plain" style="width:750px;height:400px;margin-top: 20px "></script>
		</div>
		<div><input type="submit"></div>
		</form>
	</div>
  </body>
</html>
