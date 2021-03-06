<%@page import="org.springframework.ui.Model"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="next" value="${pageContext.request.contextPath}/pharma" />

<html>
<head>
<title>CART</title>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet" />
<%@ page isELIgnored="false"%>

</head>

<nav class="navbar navbar-inverse navbar-fixed-top" style="background: #ffffff !important">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">ABC HOSPITALS</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="/dbms/pharma"><span class="glyphicon glyphicon-home"></span>Home</a></li>
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
	
	<br>
	<br>
	<br>
	<div class="container">
	<table class="table table-striped">
	<thead>
      <tr>
      	<th>Medicine Id</th>
      	<th>Quantity </th>
        <th>TotalCost</th>
        <th>Actions</th>
      </tr>
    </thead>
    <tbody>
    
    <c:forEach items="${ilist}" var="item">
    <tr>
    <td>${item.medid }</td>
    <td>${item.quantity }</td>
    <td>${item.totalcost }</td>
    <td><a href="${next}/itemquantity/${item.medid}">edit quantity </a></td>
   
    </tr>
    </c:forEach>
    </tbody>
</table>
	<h1>Total cost is ${totalprice} </h1>
	<h1><a href="${next}/checkout">Check Out</a></h1>
	${message}
	${error}
</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>