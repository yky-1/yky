<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>新闻内容</title>
	<link rel="stylesheet" type="text/css" href="/mynews/link/style.css">
  </head>
  <body>
    <div>
		<h3>标题:${news.getCaption()}</h3>
		<label class="shownews_label">Id号：${news.getNewsId()}</label>
		<label class="shownews_label">作者：${news.getAuthor()}</label>
		<label class="shownews_lastlabel">日期：${news.getNewsTime()}</label>
		<div class="content_div">${news.getContent()}</div>
	</div>
	<div>
		<h3>评论</h3>
		<hr>
		<c:forEach items="${requestScope.list}" var="com">
		<div class="comment_div">
			<label style="color:red">${com.comment.userName}</label>
			<span class="comment_stair">#${com.comment.stair}</span>
			<p class="content">${com.comment.content}</p>
			<span class="comment_firstspan">${com.comment.time}</span>
			<span class="comment_span" onclick="showhuifu('${com.comment.commentId}')">回复(${com.reply.size() })</span>
			<span class="comment_span" onclick="onpraise('${news.getNewsId()}','${com.comment.commentId}')">
			赞(${com.comment.praise})</span>
			<div id="${com.comment.commentId}" style="border: 1px solid gray;margin: 5px;display:none">
				<form action="/mynews/servlet/commentServlet?type=addreply" method="post" style="margin:0px;">
				<c:forEach items="${com.reply}" var="rep">
					<span  style="color:red">${rep.userName}</span>
					<span>: ${rep.content}</span><br>
				</c:forEach>
					<input type="hidden" name="commentId" value="${com.comment.commentId}">
					<input type="hidden" name="newsId" value="${news.getNewsId()}">
					<input type="text" size="30" name="content"><input type="submit" value="回复">
				</form>
			</div>
		</div>
		</c:forEach>
		<div style="margin: 10px;">
			<form action="/mynews/servlet/commentServlet?type=addComment" method="post">
				<textarea name="comment" id="" cols="45" rows="5"></textarea>
				<input type="hidden" value="${news.getNewsId()}" name="newsId">
				<input type="submit" value="发布评论">
			</form>
		</div>
	</div>
  </body>
  <script>
	function onpraise(newsid,commentid){
		window.location.href="/mynews/servlet/commentServlet?type=praise&id="+newsid+"&commentid="+commentid;
	}
	function showhuifu(id){
		var hid=document.getElementById(id);
		if(hid.style.display=="none")hid.style.display="";
		else hid.style.display="none";

	}
</script>
</html>
