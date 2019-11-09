<%@page import="org.springframework.ui.Model"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="next" value="${pageContext.request.contextPath}/admin" />

<html>
<head>
<title>Showapp</title>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet" />
<%@ page isELIgnored="false"%>

</head>

<nav class="navbar navbar-inverse navbar-fixed-top" style="background: #ffffff !important">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">ABC HOSPITALS</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="/dbms/admin"><span class="glyphicon glyphicon-home"></span>Home</a></li>
				</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value="/j_spring_security_logout" />"><span class="glyphicon glyphicon-log-out"></span>
						LOGOUT</a></li>
						
			</ul>
		</div>
	</nav>
	
	<br>
	<br>
	<br>
	<h1>Upcoming Appointments</h1>
	<div class="container">
	<table class="table table-striped">
	<thead>
      <tr>
      	<th>App ID</th>
        <th>Date</th>
        <th>Time</th>
        <th>Doctorid</th>
        <th>Patientid</th>
        <th>Department</th>
        <th>Actions</th>
      </tr>
    </thead>
    <tbody>
    
    <c:forEach items="${ilist}" var="item">
    <tr>
    <td>${item.appid }</td>
    <td>${item.d }-${item.m }-${item.y }</td>
    <td>${item.time } </td>
    <td>${item.doctorid } </td>
    <td>${item.pid }</td>
    <td>${item.department } </td> 
    <td><a href="${next}/showappdetails/${item.appid}">Details </a> </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
	${message}
	${error}
</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>