<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
<title>additem</title>
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
	


<div class="container">
	<form:form method="post" modelAttribute="product" action="additem" class="form-inline">
			<br>
			<br>
			<div class="form-group">
			<label for="">PRODUCT NAME</label>
			<br>
				<form:input path="prodname" type="prodname" class="form-control" placeholder="prodname" value="aaa"/>
				<!-- bind to user.name-->
				<form:errors path="prodname" />
			</div>
			<br>
			<br>
			<div class="form-group">
			<label for="">THRESHOLD</label>
			<br>
				<form:input path="threshold" type="text" class="form-control" placeholder="@threshold" value="11"/>
				<!-- bind to user.threshold-->
				<form:errors path="threshold" />
			</div>
			<br>
			<br>
			<div class="form-group">
			<label for="">DESCRIPTION</label>
			<br>
				<form:input path="description" type="text" class="form-control" placeholder="@description" value="aaa"/>
				<!-- bind to user.threshold-->
				<form:errors path="description" />
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