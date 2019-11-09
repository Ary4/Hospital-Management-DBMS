<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ page import="java.security.Principal" %>
<html>

<head>
<title>LOGIN</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet" />
<style>
.errorblock {
	color: #ff0000;
	background-color: #ffffff;
}
</style>
</head>
<body onload='document.loginForm.j_username.focus()' style="background: #ffffff">
	<div>
	<center>
			<div class="container">
				<div style="background: #ffffff !important" class="jumbotron">
					<h2 style="font-size: 50px;font-family: Western">ABC HOSPITALS</h2>
					<p>Where we care for you</p>
				</div>
				<h3 style="font-size: 25px">Login</h3>

				<%
					String errorString = (String) request.getAttribute("error");
					if (errorString != null && errorString.trim().equals("true")) {
						out.println("<span class=\"errorblock\">Incorrect login name or password. Please try again");
					}
				%>

				<form name='loginForm' action="<c:url value='login' />"
					method='POST' class="form-inline">
					<div class="form-group" style="font-size: 22px">
						<label>User Name:</label> <input type='text' name='username'
							class="form-control" placeholder="username" value=''><br>
					</div>
					<br>
					<div class="form-group" style="font-size: 22px">
						<label> Password:</label> <input type='password' name='password'
							class="form-control" placeholder="password" />
					</div>
					<br> <input name="submit" type="submit"
						class="btn btn-success" value="LogIn" /> <input name="reset"
						type="reset" class="btn btn-success" value="Reset" />

				</form>


				<div class="container" style="font-size: 25px">
					<h3 style="font-size: 25px">IF NOT REGISTERED, REGISTER HERE</h3>
					<form action="register" class="form-inline">

						<input value="Register" type="submit" class="btn btn-success">
					</form>
	</div>
	</div>
	</center>
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>