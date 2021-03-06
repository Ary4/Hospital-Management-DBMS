<%@page import="org.springframework.ui.Model"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="next" value="${pageContext.request.contextPath}/staff" />

<html>
<head>
<title>Showresult</title>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet" />
<%@ page isELIgnored="false"%>

</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" style="background: #ffffff !important">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">ABC HOSPITALS</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="/dbms/staff"><span class="glyphicon glyphicon-home"></span>Home</a></li>
				</ul>
			<ul class="nav navbar-nav navbar-right">
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<li><a href="<c:url value="/j_spring_security_logout" />"><span class="glyphicon glyphicon-log-out"></span>
						LOGOUT</a></li>
						</c:if>
						
			</ul>
		</div>
	</nav>
	
	<br>
	<br>
	<br>
	<h1>Search Result</h1>
	<div class="container">
	<table class="table table-striped">
	<thead>
      <tr>
      	<th>Doctor Id</th>
        <th>Day</th>
        <th>Timein</th>
        <th>Timeout</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody>
    
    <c:forEach items="${ilist}" var="item">
    <tr>
    <td>${item.doctorid }</td>
    <td>${item.day }</td>
    <td>${item.timein }</td>
    <td>${item.timeout }</td>
    <td><a href="${next}/confirmapp/${item.doctorid}+${d}+${m}+${y}+${department}+${time}+${pid}">Book Appointment </a>
   
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