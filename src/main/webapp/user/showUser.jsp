<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>显示个人信息</title>
	<link rel="stylesheet" type="text/css" href="/mynews/link/style.css">
  </head>
  <body>
   <div class="all">
		<div class="main">
			<div>
				<img src="${uin.getImageurl()}" width="65px" height="65px;">
			</div>
			<div class="name_div">${u.getName()}</div>
		</div>
		<div class="line">
			性别：${uin.getSex()}
		</div>
		<div class="line">
			用户类型：<c:if test="${u.getType()=='user'}">一般用户</c:if>
				  <c:if test="${u.getType()=='manager'}">管理员</c:if>
				  <c:if test="${u.getType()=='newsAuthor'}">新闻发布员</c:if>
		</div>
		<div class="line">注册时间：${u.getRegisterdate()}</div>
		<div class="line">爱好：${uin.getHobby()}</div>
	</div>
  </body>
</html>
