<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>新闻列表</title>
	<link rel="stylesheet" type="text/css" href="/mynews/link/style.css">
  </head> 
  <body>
    <div>
      <c:forEach items="${requestScope.list}" var="item">
		<div class="shownews_div" onclick="turnto('${item.newsId}')">
			<p class="news_p">标题：${item.caption}</p>
			<label class="shownews_label">Id号：${item.newsId}</label>
			<label class="shownews_label">作者：${item.author}</label>
			<label class="shownews_lastlabel">日期：${item.newsTime}</label>
		</div>
	  </c:forEach>
	</div>
	<div class="page_div">
		<c:forEach var="i" begin="1" end="${sum_page}">
		<c:choose>
			<c:when test="${ page==i}">
			<a href="/mynews/servlet/newsServlet?type=shownews&page=${i}">
				<span class="select_page">${i}</span>
			</a>
			</c:when>
			<c:otherwise>
			<a href="/mynews/servlet/newsServlet?type=shownews&page=${i}">
				<span class="page">${i}</span>
			</a>
			</c:otherwise>
		</c:choose>
		</c:forEach>
		<a href="/mynews/servlet/newsServlet?type=shownews&page=${sum_page}">
			<span class="page">尾页</span>
		</a>
	</div>
  </body>
  <script>
	function turnto(id){
		window.open('/mynews/servlet/newsServlet?type=showOneNews&id='+id);
	}
</script>
</html>
