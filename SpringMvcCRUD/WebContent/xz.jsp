<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<a href="testResponseEntity">下载</a>
	<a href="download">无限制下载</a>
	
	
	<form action="testResponseEntity" method="post" enctype="multipart/form-data">
		<input type="file" name="file"/>
		<br />
		<input type="text" name="name">		
		<input type="submit" value="提交"/>
	</form>
	
	
</body>
</html>