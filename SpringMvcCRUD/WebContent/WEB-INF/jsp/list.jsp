<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人资料</title>
<script src="/SpringMvcCRUD/js/jquery-3.6.0.js"></script>
<script type="text/javascript">
$(function() {
	$(".delete").click(function() {
		var href =$(this).attr("href");
		$("form").attr("action",href).submit();
		return false;
	});
	
	$("#CN").change(function() {
		var cn=$("#CN").val();
		window.location.href="a?locale=" +cn;
	});
	
	var a = '<%=request.getParameter("CN")%>' ;
	$("#CN").find("option[value='"+a+"']").attr("selected",true);
});
</script>
</head>
<body>
		<select id="CN">
			<option value="zh_CN">中文</option>
			<option value="en_US">英文</option>
		</select><br>
		
		<!-- 	<a href="a?locale=zh_CN">中文</a>
			<a href="a?locale=en_US">英文</a>
		 -->	
					
		<form action="" method="POST">
			<input type="hidden" name="_method" value="DELETE"/>
		</form>
	
	 <table border="1" cellpadding="10" cellspacing="0">
	 	<tr>
	 		<th><fmt:message key="id"></fmt:message></th>
	 		<th><fmt:message key="lastName"></fmt:message></th>
	 		<th><fmt:message key="head"></fmt:message></th>
	 		<th><fmt:message key="Email"></fmt:message></th>
	 		<th><fmt:message key="gender"></fmt:message></th>
	 		<th><fmt:message key="department"></fmt:message></th>
	 		<th><fmt:message key="departmentName"></fmt:message></th>
	 		<th><fmt:message key="update"></fmt:message></th>
	 		<th><fmt:message key="delete"></fmt:message></th>
	 	</tr>
	 	
	 	<c:forEach items="${requestScope.emp1 }" var="emp">
	 		<tr>
	 			<td>${emp.id }</td>
	 			<td>${emp.lastName }</td>
	 			<c:set var = "url" value="${emp.head }"></c:set>
				<th><img alt="" width="50px" height="50px" src="download?filePath=<%= URLEncoder.encode(pageContext.getAttribute("url") == null ? "" : (String)pageContext.getAttribute("url"),"utf-8")%>"></th>
	 			<td>${emp.email }</td>
	 			<td>${emp.gender==0?'女':'男' }</td>
	 			<td>${emp.department.id}</td>
	 			<td>${emp.department.departmentName}</td>
	 			<td><a href="input/${emp.id }">修改</a></td>
	 			<td><a class="delete" href="input/${emp.id }">删除</a></td>
	 		</tr>
	 	</c:forEach>
	 </table>
	 <br><br>
	 <a href="input"><fmt:message key="input">添加</fmt:message></a>
</body>
</html>