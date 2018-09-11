<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>初始页面</title>
	<link rel="stylesheet" type="text/css" href="/mynews/link/style.css">
  </head>
  <body  style="margin: 0px;">
  <div class="news_top">
		<div>
			<span class="title_span">新闻管理系统</span>
		</div>
		<div></div>
		<div style="align-self: center;">
			<a href="/mynews/resource/login.html" class="index_a">登陆</a>
		<c:if test="${! (empty sessionScope.user )}">
			<a href="/mynews/servlet/userServlet?type=showprivate" class="index_a">${username}</a>
		</c:if>
			<a href="/mynews/resource/register.html" class="index_a">注册</a>
			<a href="/mynews/servlet/userServlet?type=exit" class="index_a">注销</a>
		</div>
	</div>
	<div style="padding:8px;margin-left:10%">
	<c:if test="${username!=null}">
	<p>
    	<a class="function_select" href="/mynews/servlet/userServlet?type=showprivate">显示个人信息</a>
    	<a class="function_select" href="/mynews/servlet/userServlet?type=changeuser">修改用户信息</a>
    	<a class="function_select" href="/mynews/user/changepasswd.jsp">修改密码</a>
    </p>
    </c:if>
	<c:choose>
	<c:when test="${user.type=='manager'}">
     <p>
    	<a class="function_select" href="/mynews/servlet/userServlet?type=showpage&page=1">浏览用户</a>
    	<a class="function_select" href="/mynews/servlet/userServlet?type=check&page=1">审查用户</a>
    	<a class="function_select" href="/mynews/manager/searchUser.jsp">查询用户</a>
    	<a class="function_select" href="/mynews/servlet/userServlet?type=deleteUser&page=1">删除用户</a>
    	<a class="function_select" href="/mynews/servlet/newsServlet?type=checkNews&page=1">审核新闻</a>
    	<a class="function_select" href="/mynews/servlet/newsServlet?type=deleteNews&page=1">删除新闻</a>
    	<a class="function_select" href="/mynews/servlet/commentServlet?type=deleteComment&page=1">删除评论</a>
    	<a class="function_select" href="/mynews/servlet/commentServlet?type=stat">统计数据</a>
     </p>
     </c:when>
     <c:when test="${user.type=='newsAuthor'}">
    <p>
    	<a class="function_select" href="/mynews/news/addnews.jsp">添加新闻</a>
    	<a class="function_select" href="/mynews/servlet/newsServlet?type=shownews&page=1">显示新闻</a>
    	<a class="function_select" href="/mynews/servlet/newsServlet?type=manageNews&page=1">管理新闻</a>
    </p>
    </c:when>
    </c:choose>
    </div>
	<div style="background-color: #fbfbfbfa">
	<div class="news_body">
		<fieldset class="news_fieldset">
			<legend class="news_legend">国际新闻</legend>
			<iframe class="news_iframe" src="/mynews/servlet/newsServlet?type=typeNews&newsType=国际">
					
			</iframe>
		</fieldset>
		<fieldset class="news_fieldset">
			<legend class="news_legend">社会新闻</legend>
			<iframe scrolling="no" class="news_iframe" src="/mynews/servlet/newsServlet?type=typeNews&newsType=社会">
				
			</iframe>
		</fieldset>
		<fieldset  class="news_fieldset">
			<legend class="news_legend">体育新闻</legend>
			<iframe class="news_iframe" src="/mynews/servlet/newsServlet?type=typeNews&newsType=体育">
			
			</iframe>
		</fieldset>
	</div>
	</div>
	<div class="news_foot">
		<center>
			<c:if test="${username==null}">更多功能请登陆使用</c:if>
			<c:if test="${username!=null}">欢迎使用新闻管理系统</c:if>
		</center>
	</div>
  </body>
</html>
