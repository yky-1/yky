<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>ShowUser.jsp</title>
	<link rel="stylesheet" type="text/css" href="/mynews/link/style.css">
  </head>
  
  <body>
  <div>
    <table border="frame" rules="all" class="usr_table">
		<tr class="table_first">
			<td>ID号</td>
			<td>用户名</td>
			<td>用户类型</td>
			<td>注册时间</td>
			<td>使用状态</td>
		</tr>
	<c:forEach var="i" begin="1" end="${list.size()}" step="1">
		<tr>
			<td>${list[i-1].getId()}</td>
			<td>${list[i-1].getName()}</td>
			<td>${list[i-1].getType()}</td>
			<td>${list[i-1].getRegisterdate()}</td>
			<td>${list[i-1].getEnable()}</td>
		</tr>
	</c:forEach>
	</table>
	</div>
	<div class="page_div">
		<c:forEach var="i" begin="1" end="${lastpage}">
		<c:choose>
			<c:when test="${ page==i}">
			<a href="/mynews/servlet/userServlet?type=${type}&page=${i}">
				<span class="select_page">${i}</span>
			</a>
			</c:when>
			<c:otherwise>
			<a href="/mynews/servlet/userServlet?type=${type}&page=${i}">
				<span class="page">${i}</span>
			</a>
			</c:otherwise>
		</c:choose>
		</c:forEach>
		<a href="/mynews/servlet/userServlet?type=${type}&page=${lastpage}">
			<span class="page">尾页</span>
		</a>
	</div>
  </body>
</html>
