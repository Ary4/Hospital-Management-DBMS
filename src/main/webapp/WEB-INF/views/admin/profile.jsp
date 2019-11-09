<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<c:set var="root" value="${pageContext.request.contextPath}" />
<c:set var="next" value="${pageContext.request.contextPath}/admin" />



<html>
<head>
<title>ADMIN</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>var base = "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js";</script>

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
				<li class="active"><a href="/dbms/admin"><span class="glyphicon glyphicon-home"></span>Home</a></li>
				</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value="/j_spring_security_logout" />"><span class="glyphicon glyphicon-log-out"></span>
						LOGOUT</a></li>
						
			</ul>
		</div>
	</nav>
<div class="container" style="background:#ffffff">
<div class="jumbotron" style="background:#ffffff">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <h2>
            Welcome to the admin page :${user}
        </h2>
    </c:if>
    </div>
    <h2> <a href="${root}/admin/allusers">All USERS</a></h2>
    
    <h2><a href="${next}/viewdoctors">Show all Doctors</a> | <a href="${root}/admin/adddoctor">Add Doctor</a></h2>
    
    <h2><a href="${next}/viewstaffs">Show all Staffs</a> | <a href="${root}/admin/addstaff">Add Staff</a></h2>
    <h2><a href="${next}/showapp">Show all Appointments</a> </h2>
    <h2><a href="${next}/viewinventory">Show Inventory </a> | <a href="${root}/admin/invtrans">Show Inventory Transaction</a></h2>
    <h2><a href="${next}/showattendance">Show Attendance </a> </h2>
        

    <h3>${message}</h3>
   
    </div>
</body>
</html>
