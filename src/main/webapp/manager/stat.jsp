<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>数据统计</title>
	<link rel="stylesheet" type="text/css" href="/mynews/link/style.css">
  </head>
  
  <body>
    <div style="display: flex;justify-content: flex-start;flex-wrap: wrap;">
		<div style="margin: 5px;">
			<table border="frame" rules="all" class="usr_table">
			<tr class="table_first">
				<td>新闻发布员</td>
				<td>一年内新闻发布数</td>
				<td>一月内新闻发布数</td>
				<td>一周内新闻发布数</td>
			</tr>
			<c:forEach items="${requestScope.nlist}" var="com">
			<tr>
				<td>${com.name}</td>
				<td>${com.year}</td>
				<td>${com.month}</td>
				<td>${com.week}</td>
			</tr>
			</c:forEach>
			</table>
		</div>
		<div style="margin: 5px;">
			<table border="frame" rules="all" class="usr_table">
				<tr class="table_first">
					<td>一般用户</td>
					<td>一年内评论数</td>
					<td>一月内评论数</td>
					<td>一周内评论数</td>
				</tr>
				<c:forEach items="${requestScope.ulist}" var="com">
			<tr>
				<td>${com.name}</td>
				<td>${com.year}</td>
				<td>${com.month}</td>
				<td>${com.week}</td>
			</tr>
			</c:forEach>
			</table>
		</div>
	</div>
	<div style="margin: 5px;">
		<table border="frame" rules="all" class="usr_table">
			<tr  class="table_first">
				<td>2018年</td>
				<td>总新闻数</td>
				<td>总评论数</td>
			</tr>
			<c:forEach items="${requestScope.slist}" var="s">
			<tr>
				<td>${s.month}</td>
				<td>${s.news}</td>
				<td>${s.comment }</td>
			</tr>
			</c:forEach>
		</table>
	</div>
  </body>
</html>
