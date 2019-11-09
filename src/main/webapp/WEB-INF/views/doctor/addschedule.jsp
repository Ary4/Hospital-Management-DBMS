<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
<title>addschedule</title>
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


<div class="container">
	<form:form method="post" modelAttribute="schedule" action="addschedule" class="form-inline">
			<br>
			<br>
			<br>
			<br>
			<label for="">DAY</label>
			<br>
			<select name="day">
			    <option value="mon">Monday</option>
			    <option value="tue">Tuesday</option>
			    <option value="wed">Wednesday</option>
			    <option value="thr">Thursday</option>
			    <option value="fri">Friday</option>
			    <option value="sat">Saturday</option>
			    <option value="sun">Sunday</option>
			  </select>
			<br>
			<br>
			<div class="form-group">
			<label for="">TIME IN</label>
			<br>
				<form:input path="timein" type="text" class="form-control" placeholder="@timein" value="1130"/>
				<!-- bind to user.threshold-->
				<form:errors path="timein" />
			</div>
			<br>
			<br>
			<div class="form-group">
			<label for="">TIME OUT</label>
			<br>
				<form:input path="timeout" type="text" class="form-control" placeholder="@timeout" value="1330"/>
				<!-- bind to user.threshold-->
				<form:errors path="timeout" />
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