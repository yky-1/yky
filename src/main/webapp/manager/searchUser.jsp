<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>  
    <title>searchUser.jsp</title>
	<link rel="stylesheet" type="text/css" href="/mynews/link/style.css">
  </head>
  
  <body>
  <form action="/mynews/servlet/userServlet?type=search&page=1" method="post">
   <table border="frame" rules="all" class="usr_table">
	<tr><td colspan="4">输入查询内容</td></tr>
		<tr>
			<td>用户名</td>
			<td>用户类型</td>
			<td>使用状态</td>
			<td>注册时间</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="name">
			</td>
			<td>
				<select name="usertype" id="">
					<option value="%">所有</option>
					<option value="manager">管理员</option>
					<option value="user">普通用户</option>
					<option value="newsAuthor">新闻发布员</option>
				</select>
			</td>
			<td>
				<select name="enable" id="">
					<option value="%">所有</option>
					<option value="use">可用</option>
					<option value="stop">已停用</option>
				</select>
			</td>
			<td>
				介于<input type="date" name="time1">与<input type="date" name="time2">之间
			</td>
		</tr>
		<tr><td colspan="4"><input type="submit"></td></tr>
   </table>
   </form>
  </body>
</html>
