<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Displaying all Dependents</title>
<!-- Bootstrap Mobile view enabled -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<spring:url value="/resources/bootstrap/dist/css/bootstrap.min.css"
	var="bootstrapMinCss" />
<spring:url
	value="/resources/bootstrap/dist/css/bootstrap-theme.min.css"
	var="bootstrapThemeMinCss" />
<spring:url value="/resources/css/error.css" var="errorHighlightCss" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link href="${bootstrapMinCss}" rel="stylesheet" />
<link href="${bootstrapThemeMinCss}" rel="stylesheet" />
<link href="${errorHighlightCss}" rel="stylesheet" />
</head>
<body>
	<h1>
		Contact Manager - My Contacts <a class="btn btn-primary"
			href="${pageContext.request.contextPath}/"> <span
			class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;Home
		</a>
	</h1>
<c:if test="${! empty allDependents}">
	<table class="table table-striped table-bordered">
		<tr>
			<th class="text-center"><h3>Name</h3></th>
			<th class="text-center"><h3>D.O.B</h3></th>
			<th class="text-center"><h3>Gender</h3></th>
			<th class="text-center" colspan="2"><h3>Action</h3></th>
		</tr>
			
		<c:forEach items="${allDependents}" var="dependent">
			<tr>
				<td>${dependent.getName()}</td>
				<td>${dependent.getDob() }</td>
				<td>${dependent.getGender() }</td>
				<td>
					<a class="btn btn-danger" href="delete/${dependent.getDependentId()}"> <span class="glyphicon glyphicon-remove"></span>&nbsp;&nbsp;Delete</a>
				</td>
			</tr>
		</c:forEach>
		<tr hidden="true">
			<td align="center" class="text-center">${countOfDependents}</td>
		</tr>
	</table>
	<a class="btn btn-default enabled" >Total Contacts: <h5>${countOfDependents}</h5><span id="counter" class="badge"></span></a>
	
</c:if>
</body>
</html>