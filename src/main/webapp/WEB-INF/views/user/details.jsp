<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<c:set var="root" value="${pageContext.request.contextPath}" />
<c:set var="next" value="${pageContext.request.contextPath}/user" />

<html>
<head>
<title>Patient!</title>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet" />

</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" style="background: #ffffff !important">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">ABC HOSPITALS</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="/dbms/user"><span class="glyphicon glyphicon-home"></span>Home</a></li>
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
	<br>
	
<div class="container" style="background: #ffffff !important">
<h2> <a href="/dbms/user/updatedetails">Update Details</a></h2>
<h2> <a href="/dbms/user/showprevapp">Show previous appointment details(all together)</a></h2>
     Username : ${data.username }
     <br>
     Name : ${data.name } <br>
	 Phone : ${data.phone } <br>
	 Email : ${data.email }<br>
	 address : ${data.address } <br>
	 Sex : ${data.sex } <br>
	 previousdiseases : ${data.previousdiseases }<br>
	
	
	
    <h3>${message}</h3>
   
    </div>
</body>
</html>
