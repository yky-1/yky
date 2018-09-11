<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>typeNews.jsp</title>
	<link rel="stylesheet" type="text/css" href="/mynews/link/style.css">
  </head> 
  <body>
    <div>
      <c:forEach items="${requestScope.list}" var="item">
		<div class="shownewstype" onclick="turnto('${item.newsId}')">
			<span>${item.caption}</span>
		</div>
	  </c:forEach>
	</div>
  </body>
   <script>
	function turnto(id){
		window.open('/mynews/servlet/newsServlet?type=showOneNews&id='+id);
	}
</script>
</html>
