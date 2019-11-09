<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
<title>addstaff</title>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet" />
<meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>
<body style="background: #ffffff">
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

<div class="container">
	<form:form method="post" modelAttribute="user" action="addstaff" class="form-inline">
	<div class="form-group">
            <label for="">USERNAME</label>
            <br>
            <form:input type="text" class="form-control" path="username" placeholder="username" value="aaa" />
            <form:errors path="username" />
        </div>
			<br>
			<br>
			<div class="form-group">
			<label for="">PASSWORD</label>
			<br>
				<form:input path="password" type="password" class="form-control" placeholder="password" value="aaa"/>
				<!-- bind to user.name-->
				<form:errors path="password" />
			</div>
			<br>
			<br>
			<div class="form-group">

				<label for="">Confirm password</label>
				<br>
				<form:input path="mpassword" type="password" class="form-control" value="aaa" />
				<!-- bind to user.name-->
				<form:errors path="mpassword" />
			</div>
			<br>
			<br>
			<div class="form-group">
			<label for="">NAME</label>
			<br>
				<form:input path="name" type="text" class="form-control" placeholder="@name" value="aaa"/>
				<!-- bind to user.name-->
				<form:errors path="name" />
			</div>
			<br>
			<br>
			<div class="form-group">
			<label for="">EMAIL</label>
			<br>
				<form:input path="email" type="text" class="form-control" placeholder="@email" value="aaa"/>
				<!-- bind to user.name-->
				<form:errors path="email" />
			</div>
			<br>
			<br>
			<div class="form-group">
			<label for="">PHONE</label>
			<br>
				<form:input path="phone" type="text" class="form-control" placeholder="@phone" value="111"/>
				<!-- bind to user.name-->
				<form:errors path="phone" />
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