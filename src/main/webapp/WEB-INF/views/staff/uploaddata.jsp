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


<div class="container">
	<form:form method="post" modelAttribute="appointmentdetails" action="${storethis}" class="form-inline">
			<br>
			<br>
			<div class="form-group">
			<label for="">Enter Url</label>
			<br>
				<form:input path="details" type="details" class="form-control" placeholder="details" value="someurl.com"/>
				<!-- bind to user.threshold-->
				<form:errors path="details" />
			</div>
			<br>
			<br>

			<button type="submit" class="btn btn-primary">Submit</button>
				
			<td>${error}</td>
			
	</form:form>
	</div>
	<h1>Click <a href="https://filebin.net/" target="_blank">Here</a> to upload files and get link</h1>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>