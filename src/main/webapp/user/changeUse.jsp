<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>   
    <title>修改个人信息</title>
	<link rel="stylesheet" type="text/css" href="/mynews/link/style.css">
  </head>
  <body>
  <div style="width:355px;margin:0 auto;">
    <form action="/mynews/servlet/userServlet?type=changeuser&type1=more" method="post"  enctype="multipart/form-data">
		<table border="frame" rules="all">
			<tr>
				<td colspan="2" align="center">修改个人信息</td>
			</tr>
			<tr>
				<td>用户名:</td>
				<td>${u.getName()}</td>
			</tr>
			<tr>
				<td>头像</td>
				<td>
					<div>
						<img src="${uin.getImageurl() }" width="64" height="64">
					</div>
					<div>
						<input type="file" name="myfile">
				 	</div>
				</td>
			</tr>
			<tr>
				<td>性别：</td>
				<td>
					<select name="sex">
						<option value="男">男</option>
						<option value="女">女</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>爱好：</td>
				<td><input type="text" size="35" value="${uin.getHobby()}" name="hobby"></td>
			</tr>
			<tr>
				<td  colspan="2" align="center">
					<input type="submit" value="确定">
				</td>
			</tr>
		</table>
	</form>
	</div>
  </body>
</html>
