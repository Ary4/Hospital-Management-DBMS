<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="root" value="${pageContext.request.contextPath}" />
<c:set var="next" value="${pageContext.request.contextPath}/staff" />
<head>
<title>takeitem</title>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet" />
<meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>


<div class="container">
	<form:form method="post" modelAttribute="transaction" action="${next}/fillitem" class="form-inline">
			<br>
			<br>
			<div class="form-group">
			<label for="">QUANTITY</label>
			<br>
			<input type="number" name="quantity" min="1" max="${1000}" >
				<!-- <form:input min="1" max="${transaction.quantity}" path="quantity" type="text" class="form-control" placeholder="@quantity" />
				<!-- bind to user.quantity
				<form:errors path="quantity" /> -->
			</div>
			<br>
			<br>
			<div class="form-group">
			<label for="">COMMENTS</label>
			<br>
				<form:input path="comments" type="text" class="form-control" placeholder="@comments" value="aaa"/>
				<!-- bind to user.quantity-->
				<form:errors path="comments" />
			</div>
			
			
			<input type="hidden" id="ppId" name="prodid" value="${transaction.prodid }">
			 
			<br>
			<br>

			<button type="submit" class="btn btn-primary">Submit</button>
				
			<td>${error}</td>
			
	</form:form>
	
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>