<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>审核新闻中心</title>
	<link rel="stylesheet" type="text/css" href="/mynews/link/style.css">
  </head>
  <body>
    <form action="/mynews/servlet/newsServlet?type=delete" method="post" id="myform">
		<table border="frame" rules="all" class="usr_table">
			<tr class="table_first">
				<td>ID号</td>
				<td>作者</td>
				<td>标题</td>
				<td>发布时间</td>
				<td>审核情况</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${requestScope.list}" var="news">
				<tr>
					<td>${news.newsId}</td>
					<td>${news.author}</td>
					<td width="230" >
						<a href="/mynews/servlet/newsServlet?type=showOneNews&id=${news.newsId}">
						${news.caption}</a>
					</td>
					<td>${news.publishTime}</td>
					<td>
						<c:if test="${news.check==1}">已通过</c:if>
						<c:if test="${news.check==0}">未通过</c:if>
					<td>
						<c:choose>
							<c:when test="${news.check==1}">
							 <a href="/mynews/servlet/newsServlet?type=checkNews&newsId=${news.newsId}&check=0&page=${page}">
							 撤回审核</a>
							</c:when>
							<c:when test="${news.check==0}">
								<a href="/mynews/servlet/newsServlet?type=checkNews&newsId=${news.newsId}&check=1&page=${page}">
							通过</a>
							</c:when>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</table>
		<input type="hidden" name="ids" id="ids" value="0">
	</form>
    <div class="page_div">
		<c:forEach var="i" begin="1" end="${lastpage}">
		<c:choose>
			<c:when test="${ page==i}">
			<a href="/mynews/servlet/newsServlet?type=checkNews&page=${i}">
				<span class="select_page">${i}</span>
			</a>
			</c:when>
			<c:otherwise>
			<a href="/mynews/servlet/newsServlet?type=checkNews&page=${i}">
				<span class="page">${i}</span>
			</a>
			</c:otherwise>
		</c:choose>
		</c:forEach>
		<a href="/mynews/servlet/newsServlet?type=checkNews&page=${lastpage}">
			<span class="page">尾页</span>
		</a>
	</div>
  </body>
</html>
