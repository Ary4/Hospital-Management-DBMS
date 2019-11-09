<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<c:set var="root" value="${pageContext.request.contextPath}" />
<c:set var="next" value="${pageContext.request.contextPath}/staff" />



<html>
<head>
<title>STAFF</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" />

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
<div class="container" style="background: #ffffff !important">
<div class="jumbotron" style="background: #ffffff !important">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <h2>
            Welcome to the staff page :${user}
        </h2>
    </c:if>
    </div>    
    <h2><a href="${next}/viewinventory">Show Inventory</a> </h2>
    <h2><a href="${next}/showapp">Upcoming Appointments</a> </h2>
   <!--  <h2><a href="${next}/uploaddata">Upload Files/Details/Data to an Appointment</a> </h2> -->
   <h2> <a href="${next}/showappprev">Show Previous Appointments</a></h2>
   <h2> <a href="${next}/bookapp">Book appointment for patient(on-spot)</a></h2>
	    
   

    <h3>${message}</h3>
   
    </div>
</body>
</html>
