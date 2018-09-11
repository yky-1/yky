<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>删除评论</title>
	<link rel="stylesheet" type="text/css" href="/mynews/link/style.css">
  </head>
  
  <body>
   <form action="/mynews/servlet/commentServlet?type=deleteComment&type1=delete&page=1" method="post" id="myform">
		<table border="frame" rules="all" class="usr_table">
			<tr class="table_first">
				<td>ID号</td>
				<td>新闻ID</td>
				<td>发布者</td>
				<td>发布时间</td>
				<td>内容</td>
				<td>点赞数</td>
				<td><input type="checkbox" name="checkboxall" onchange="checkAll1(this)"></td>
			</tr>
			<c:forEach items="${requestScope.list}" var="item">
				<tr>
					<td>${item.commentId}</td>
					<td>${item.newsId}</td>
					<td>${item.userName}</td>
					<td>${item.time}</td>
					<td width="320">${item.content} </td>
					<td>${item.praise} </td>
					<td><input type="checkbox" name="checkbox1" value="${item.commentId}"></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7"><input type="button" value="删除选中项" onclick="deleteUsers()"></td>
			</tr>
		</table>
		<input type="hidden" name="ids" id="ids" value="0">
	</form>
	<div class="page_div">
		<c:forEach var="i" begin="1" end="${lastpage}">
		<c:choose>
			<c:when test="${ page==i}">
			<a href="/mynews/servlet/commentServlet?type=deleteComment&page=${i}">
				<span class="select_page">${i}</span>
			</a>
			</c:when>
			<c:otherwise>
			<a href="/mynews/servlet/commentServlet?type=deleteComment&page=${i}">
				<span class="page">${i}</span>
			</a>
			</c:otherwise>
		</c:choose>
		</c:forEach>
		<a href="/mynews/servlet/commentServlet?type=deleteComment&page=${lastpage}">
			<span class="page">尾页</span>
		</a>
	</div>
  </body>
  <script type="text/javascript">
	  function checkAll1(obj){
	  	var checkboxs= document.getElementsByName("checkbox1");
	  	for (var i = 0; i < checkboxs.length; i++) 
	  		checkboxs[i].checked =obj.checked;	  
	  }
	  
	  function deleteUsers(){
	  	var checkboxs= document.getElementsByName("checkbox1"); 
	  	var ids="";
	  	////拼接id为 ：1,2,
	   	for(i=0;i<checkboxs.length;i++){
        	if(checkboxs[i].checked == true)
            	ids+=checkboxs[i].value+",";
        }
		if(ids.length<1){
			alert("请选择至少一个需删除的元素！");
			return false;//阻止提交
		}
		ids=ids.substring(0,ids.length-1);//删除最后的逗号
		ids1=document.getElementById("ids"); 
	  	ids1.value=ids;
	  	//提交
    	document.getElementById('myform').submit();
	  }
	</script>  
</html>
