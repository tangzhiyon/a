<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


	<form action="conversiontext" method="post">
		Employee: <input type="text" name="employee"/>
		<input type="submit" value="提交">
	</form>
	<br/><br/>



	<form:form action="${pageContext.request.contextPath }/input" method="post" modelAttribute="employee" enctype="multipart/form-data">
	
		
		<br/>
		<c:if test="${employee.id ==null }">
		<fmt:message key="lastName"></fmt:message>: <form:input path="lastName" />
		<form:errors path="lastName"></form:errors>
		</c:if>
		<br/>
		<c:if test="${employee.id!=null}">
		<form:hidden path="id"/>
		<input type="hidden" name="_method" value="PUT"/>
		</c:if>
		<fmt:message key="Email"></fmt:message>: <form:input path="Email"/>
		<form:errors path="email"></form:errors>
		<br/>
		<fmt:message key="gender"></fmt:message>: <form:radiobuttons path="gender" items="${requestScope.map2 }"/>
		<form:errors path="gender"></form:errors>
		<br/>
		<fmt:message key="department"></fmt:message>: <form:select path="department.id" items="${requestScope.in }" itemLabel="departmentName"  itemValue="id"></form:select>
		<br/>
		<fmt:message key="birth"></fmt:message>: <form:input path="birth"/>
		<form:errors path="birth"></form:errors>
		<br/>
		<fmt:message key="salary"></fmt:message>: <form:input path="salary"/>
		<form:errors path="salary"></form:errors>
		<br/>
		<c:set var = "url" value="${employee.head }"></c:set>
		<img alt="" width="100px" height="100px" src="<%=request.getContextPath()%>/download?filePath=<%= URLEncoder.encode(pageContext.getAttribute("url") == null ? "" : (String)pageContext.getAttribute("url"),"utf-8")%>">
		<input type="file" name="hea"/>
		<br/>
		<input type="submit" value="提交"/>
	</form:form>
</body>
</html>