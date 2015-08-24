<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>添加用户</h1>
	<form action="user_add.action" method="post">
    	用户名：<input type="text" name="id"><br>
    	用户名：<input type="text" name="name"><br>
    	用户地址：<input type="text" name="address"><br>
    	创建时间:<input type="text" name="createDate"><br>
    	更新时间：<input type="text" name="updateDate"><br>
    	备注：<input type="text" name="remark"><br>
    	<input type="submit" value="提交">
    </form>
</body>
</html>