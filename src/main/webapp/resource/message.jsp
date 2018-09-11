<%@ page language="java" import="java.util.*,tool.message" pageEncoding="UTF-8"%>
<%
   message mage=(message)request.getAttribute("message");
   response.setHeader("refresh", "3;URL="+mage.getRedirectUrl());
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>message.jsp</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
    <p>${message.getMessage()}</p>
    <p>3秒后自动跳转</p>
  </body>
</html>
