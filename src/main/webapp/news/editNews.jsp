<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>编辑新闻</title>	
	<script src="/mynews/plugin/utf8-jsp/ueditor.config.js" type="text/javascript"></script>
	<script src="/mynews/plugin/utf8-jsp/ueditor.all.min.js" type="text/javascript"></script>
	<script src="/mynews/plugin/utf8-jsp/lang/zh-cn/zh-cn.js" type="text/javascript"></script>
  </head>

  <body>
    <div style="padding: 10px;background-color: #f7f7f7;width:756px;margin:0 auto">
   <p>在下面修改新闻信息：</p>
		<form action="/mynews/servlet/newsServlet?type=editNews&type1=edit" method="post">
		<div>
			标题：<textarea name="title" id="" cols="30" rows="2">${news.getCaption()}</textarea>
		</div>
		<div>
			类型：<select name="newstype" id="typeselect">
					<option value="国际">国际</option>
					<option value="社会">社会</option>
					<option value="体育">体育</option>
				</select>
		</div>
		<div>
			作者：<input type="text" name="author" value="${news.author}">
		</div>	
		<div>
			日期：<input type="datetime-local" name="time" value="${news.newsTime}">
		</div>
		<div>
			<script type="text/javascript">
    			var ue = UE.getEditor('container');
  			</script>
 			<script id="container" type="text/plain" style="width:750px;height:400px;margin-top: 20px ">${news.content}</script>
		</div>
		<div><input type="submit"></div>
		<input type="hidden" id="type" value="${news.getNewsType()}">
		<input type="hidden" name="id" value="${news.newsId}">
		</form>
	</div>
  </body>
  
  <script  type = "text/javascript" >
  		var ty=document.getElementById("type");
  		var i=0;
  		if(ty.value=="国际") i=0;
  		else if(ty.value=="社会") i=1;
  		else if(ty.value=="体育") i=2;
        document.getElementById("typeselect")[i].selected=true;
        
   </script >
</html>
