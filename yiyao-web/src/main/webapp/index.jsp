<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title> new document </title>
  <meta name="generator" content="editplus" />
  <meta name="author" content="" />
  <meta name="keywords" content="" />
  <meta name="description" content="" />
 </head>
<body>
<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
<script>
$(function() {
	$.ajax({
         type: "get",
         url: "/reset/user/findAll",
         dataType: "json",
         success: function(data){
        	var html = '';
         	$(data).each(function(i, n) {
         		html += '<tr><td>' + n.name + '</td><td>' + n.mobile + '</td><td>' + n.address + '</td><td>' + n.points 
         			+ '</td><td><input type="button" onclick="deleteUser(\'' + n.id + '\')" value="删除"></td></tr>';
         	});
         	$('#list').append(html);
		}
    });
});

function save() {
	$.ajax({
        type: "post",
        url: "/reset/user/addUser",
        data:$('#userForm').serialize(),
        success: function(data){
        	window.location.reload();
		}
   });
}

function deleteUser(id) {
	$.ajax({
        type: "GET",
        url: "/reset/user/delete/" + id,
        dataType: "json",
        success: function(data){
        	window.location.reload();
		}
    });
}
</script>
<h2>Hello 1</h2>

<form id="userForm">
	姓名：<input type="text" name="name" value="李四"> <br>
	密码：<input type="password" name="password" value="123456"> <br>
	手机：<input type="text" name="mobile" value="18229635896"> <br>
	地址：<input type="text" name="address" value="湖南省长沙市岳麓区xxx街道"> <br>
	积分：<input type="text" name="points" value="200"> <br>
	<input type="button" value="提交" onclick="save()">
</form>
<br>
用户列表
<table id="list" border="1">
	<tr>
		<td>name</td>
		<td>mobile</td>
		<td>address</td>
		<td>points</td>
		<td>操作</td>
	</tr>
</table>

<br>
<a href="pages/delete.jsp">删除页面</a>
</body>
</html>