<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改密码</title>
  </head>
  <body>
    <div style="width:400px;margin:0 auto;">
		<form action="/mynews/servlet/userServlet?type=changepassword" method="post" onsubmit="return submit1()">
			<table>
				<tr>
					<td colspan="2" align="center">修改密码</td>
				</tr>
				<tr>
					<td align="right" width="80">原来密码：</td>
					<td><input type="password" name="oldpasswd" id="oldpasswd" onblur="valPassword('oldpasswd','oldpasswdspan')"></td>
					<td><span id="oldpasswdspan"></span></td>
				</tr>
				<tr>
					<td align="right" width="80">新密码：</td>
					<td><input type="password" name="newpasswd" id="newpasswd" onblur="valPassword('newpasswd','newpasswdspan')"></td>
					<td><span id="newpasswdspan"></span></td>
				</tr>
				<tr>
					<td align="right" width="80">确认密码：</td>
					<td><input type="password" name="newpasswd1" id="newpasswd1" onblur="passwordSame('newpasswd','newpasswd1','newpasswd1span')"></td>
					<td><span id="newpasswd1span"></span></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="确认"></td>
				</tr>
			</table>
		</form>
	</div>
  </body>
</html>


<script>
	function passwordDifferent(input1,input2,span2){
			var input1Value=document.getElementById(input1).value;
			var input2Value=document.getElementById(input2).value;
			var span2Obj=document.getElementById(span2);
		
			if(input2Value==null || input2Value==""){
				span2Obj.innerHTML="*不能为空";
				return false;
			}else if(input1Value==input2Value){			
				span2Obj.innerHTML="*新旧密码不能相同！";
				return false ;
			}else{
				span2Obj.innerHTML="*ok";
				return true;
			}					
		}
	function valPassword(input, span){
			var inputValue = document.getElementById(input).value;
			var spanObj=document.getElementById(span);
			var pattern=/^(\w){6,20}$/;
			
			if(inputValue==null || inputValue==""){
				spanObj.innerHTML="*不能为空";
				return false;
			}else if(inputValue.match(pattern)==null){
				spanObj.innerHTML="*密码只能输入6-20个字母、数字、下划线";
				return false;
			}else{
				spanObj.innerHTML="ok";
				return true;
			}
		}	

	function passwordSame(input1,input2,span2){
			var input1Value=document.getElementById(input1).value;
			var input2Value=document.getElementById(input2).value;
			var span2Obj=document.getElementById(span2);
		
			if(input2Value==null || input2Value==""){
				span2Obj.innerHTML="*不能为空";
				return false;
			}else if(input1Value==input2Value){			
				span2Obj.innerHTML="*ok";
				return true ;
			}else{
				span2Obj.innerHTML="*两次密码不一样";
				return false;
			}					
		}	
	function submit1(){
			result1=valPassword("oldpasswd", "oldpasswdspan");			
			result1=valPassword("newpasswd","newpasswdspan") && result1;
			result1=passwordDifferent("oldpasswd","newpasswd","newpasswdspan") && result1;
			result1=passwordSame("newpasswd","newpasswd1","newpasswd1span") && result1;
			if( result1)
				return true;//提交
			else 
				return false;//阻止提交
		}

</script>