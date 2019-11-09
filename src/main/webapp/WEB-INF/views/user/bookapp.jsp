<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<title>bookapp</title>
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
<div class="container">
	<form:form method="post" modelAttribute="appointment" action="bookapp" class="form-inline">
			<br>
			<br>
			<div class="form-group">
			<label for="">Department</label>
			<br>
			<select name="department">
			    <option value="Paed">Paed</option>
			    <option value="Ortho">Ortho</option>
			    <option value="Surgery">Surgery</option>
			    <option value="Neuro">Neuro</option>
			    <option value="ENT">ENT</option>
			    <option value="Eye">Eye</option>
			  </select>
			<br>
			<br>
			</div>
			
			<br>
			<div class="form-group">
			<label for="">DATE</label>
			<br>
				<form:input path="d" type="d" class="form-control" placeholder="d" value="10"/>
				<!-- bind to user.name-->
				<form:errors path="d" />
			</div>
			<br>
			<br>
			<div class="form-group">
			<label for="">MONTH</label>
			<br>
				<form:input path="m" type="m" class="form-control" placeholder="m" value="10"/>
				<!-- bind to user.name-->
				<form:errors path="m" />
			</div>
			<br>
			<br>
			<div class="form-group">
			<label for="">YEAR</label>
			<br>
				<form:input path="y" type="y" class="form-control" placeholder="y" value="2020"/>
				<!-- bind to user.name-->
				<form:errors path="d" />
			</div>
			<br>
			<br>
			<div class="form-group">
			<label for="">TIME</label>
			<br>
				<form:input path="time" type="text" class="form-control" placeholder="@time" value="1230"/>
				<form:errors path="time" />
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