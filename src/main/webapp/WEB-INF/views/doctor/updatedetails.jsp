<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
<title>updatedetails</title>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet" />
<meta http-equiv="X-UA-Compatible" content="ie=edge">
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
	<br>
	<br>
	<br>
	

<div class="container">
	<form:form method="post" modelAttribute="doctor" action="updatedetails" class="form-inline">
			<br>
			<br>
			<div class="form-group">
			<label for="">Name</label>
			<br>
				<form:input path="name" type="name" class="form-control" placeholder="name" />
				<!-- bind to user.name-->
				<form:errors path="name" />
			</div>
			<br>
			<br>
			<div class="form-group">
			<label for="">Phone</label>
			<br>
				<form:input path="phone" type="phone" class="form-control" placeholder="phone"/>
				<!-- bind to user.threshold-->
				<form:errors path="phone" />
			</div>
			<br>
			<br>
			<div class="form-group">
			<label for="">Email</label>
			<br>
				<form:input path="email" type="email" class="form-control" placeholder="email" />
				<!-- bind to user.threshold-->
				<form:errors path="email" />
			</div>
			<br>
			<br>
			<div class="form-group">
			<label for="">Sex</label>
			<br>
				<form:input path="sex" type="sex" class="form-control" placeholder="sex" />
				<!-- bind to user.name-->
				<form:errors path="sex" />
			</div>
			<br>
			<br>
			<div class="form-group">
			<label for="">Degree</label>
			<br>
				<form:input path="degree" type="degree" class="form-control" placeholder="degree" />
				<!-- bind to user.name-->
				<form:errors path="degree" />
			</div>
			<br>
			<br>

			<button type="submit" class="btn btn-primary">Submit</button>
				
			<td>${error}</td>
			
	</form:form>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>