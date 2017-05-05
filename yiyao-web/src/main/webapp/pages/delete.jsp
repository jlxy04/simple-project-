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
	function deleteUser() {
		var id = $('#id').val();
		$.ajax({
            type: "GET",
            url: "/reset/user/delete/" + id,
            dataType: "json",
            success: function(data){
            	alert(data);
			}
        });
	}
</script>
<h2>删除用户</h2>

	用户ID：<input type="text" name="id" id="id"> <br>
	<input type="button" value="删除" onclick="deleteUser()">
	
	<br>
	<br>
	<a href="../index.jsp">返回</a>
</body>
</html>
