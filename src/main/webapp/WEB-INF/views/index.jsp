<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page session="false" %>
<html>
<head>
	<title>User Application Index page</title>
<!-- Bootstrap -->
<!-- Bootstrap Mobile view enabled -->
<meta name="viewport" content="width=device-width, initial-scale=0.5">
<spring:url value="/resources/bootstrap/dist/css/bootstrap.min.css" var="bootstrapMinCss" />
<link href="${bootstrapMinCss}" rel="stylesheet" />

<!-- Include JQuery version 1.11.3 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
</head>
<body>
<h1>User Contact Application</h1>
<h2>
	<a href="addDependentForm" class="btn btn-primary btn-lg btn-block">Add a new contact</a>
</h2>
<h2>
	<a href="listAllDependents" class="btn btn-success btn-lg btn-block">View all contacts</a>
	<span id="counter" class="badge"></span>
</h2>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
