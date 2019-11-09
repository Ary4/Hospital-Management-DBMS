<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<c:set var="root" value="${pageContext.request.contextPath}" />
<c:set var="next" value="${pageContext.request.contextPath}/doctor" />

<html>
<head>
<title>Doctor!</title>

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
				<li class="active"><a href="/dbms/doctor"><span class="glyphicon glyphicon-home"></span>Home</a></li>
				</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value="/j_spring_security_logout" />"><span class="glyphicon glyphicon-log-out"></span>
						LOGOUT</a></li>
						
			</ul>
		</div>
	</nav>

<div class="container" style="background: #ffffff">
<div class="jumbotron" style="background: #ffffff">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <h2>
            Welcome to the doctor page :${user}
        </h2>
    </c:if>
    </div>
     <h2> <a href="${next}/details">Show profile</a></h2>
    <h2> <a href="${next}/schedule">Schedule Management</a></h2>
     <h2> <a href="${next}/showappnext">Show My Upcoming Appointments</a></h2>
      <h2> <a href="${next}/showappprev">Show My Previous Appointments</a></h2>
    <h3>${message}</h3>
   
    </div>
</body>
</html>
