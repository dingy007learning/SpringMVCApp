<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap Mobile view enabled -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<spring:url value="/resources/bootstrap/dist/css/bootstrap.min.css"
	var="bootstrapMinCss" />
<spring:url
	value="/resources/bootstrap/dist/css/bootstrap-theme.min.css"
	var="bootstrapThemeMinCss" />
<spring:url value="/resources/css/error.css" var="errorHighlightCss" />
<style>
	table{
		width:5px;
		height:5px;
	}
	table td{
		padding:5px;
		margin:5px;
		border:1px solid #ccc;
	}
</style>
<style>
.labelPadding {
    border: 1px solid red;
    
    padding-top: 5px;
    padding-right: 5px;
    padding-bottom: 5px;
    padding-left: 5px;
}
</style>

<link
	href="http://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link href="${bootstrapMinCss}" rel="stylesheet" />
<link href="${bootstrapThemeMinCss}" rel="stylesheet" />
<link href="${errorHighlightCss}" rel="stylesheet" />
<!-- Additional for datepicker -->
<!-- Javascript -->
<script>
         $(function() {
            $( "#mydatepicker" ).datepicker({dateFormat:"dd-mm-yy", appendText:"(dd-mm-yyyy)"});
         });
      </script>

<!-- End script for datepicker -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add New User Form</title>
</head>
<body>
	<h1>Add New Dependent</h1>
	<div class="container" align="left">
		<div class="row">
			<div class="span8"></div>
			<form:form id="formId" action="add" method="post"
				cssClass="form-horizontal" commandName="dependent">
				<div class="control-group">
					<label for="name" class="control-label"> <spring:message
							code="label.name"></spring:message>
					</label>
					<div class="controls">
						<form:input path="name" size="45" id="myname" name="myname" />
						<form:errors path="name" cssClass="fieldErrors" />
					</div>
				</div>

				<div class="control-group">
					<label for="dob" class="control-label"> <spring:message
							code="label.dob"></spring:message>
					</label>
					<div class="controls">
						<form:input path="dob" size="45" id="mydatepicker" name="dob"/>
						<form:errors path="dob" cssClass="fieldErrors" />
					</div>
				</div>

				<div class="control-group">
					<label for="gender" class="control-label"> <spring:message
							code="label.gender"></spring:message>
					</label>
					<div class="controls">
						<form:radiobutton name="radioOne" path="gender" value="Male" />
						<spring:message code="label.male" />
						<form:radiobutton name="radioOne" path="gender" value="Female" />
						<spring:message code="label.female" />
						<form:errors path="gender" cssClass="fieldErrors" />
					</div>
				</div>

				<div class="form-actions">
					<button type="submit" class="btn btn-large btn-success">Add New User</button>

					<!-- <input type="button" class="btn btn-large btn-success" value='Submit form' onClick='submitDetailsForm()'> -->
					<!-- <input type="button" class="btn btn-large btn-success" value='Submit form' onClick='submitfunction()'> -->
					
						 
 						<a class="btn btn-primary" href="${pageContext.request.contextPath}/">
						<span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;Home </a>
						
 						<a class="btn btn-info" href="${pageContext.request.contextPath}/listAllDependents">
						<span class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;List All Dependents </a>
				</div>

			</form:form>
		</div>
	</div>

	<div id="dependentFromResponse"></div>
	<div id="tableHeader" hidden="true">
		<h2>Showing recently added Dependents:</h2>
	</div>
	

	<table id="tableId" class="table table-striped table-bordered" hidden="true">
		<thead>
			<tr>
				<th>Name</th>
				<th>DOB</th>
				<th>Gender</th>
			</tr>
		</thead>
		<tbody id="tbodyId"></tbody>
	</table>
	<br><br><br>
	<h1>New Table</h1>
	<div align="center" id="newTable"></div>

	<script type="text/javascript">
	var json;
	var timer;
	var jsonList;
	var prevItemCounter = 0;
	
		function submitfunction() {
			$('#formId').submit(function(e) {
				 var isValid = true;
		            $('#myname,#mydatepicker,.radioOne').each(function () {
		                if ($.trim($(this).val()) == '') {
		                    isValid = false;
		                    $(this).css({
		                        "border": "1px solid red",
		                        "background": "#FFCECE"
		                    });
		                }
		                else {
		                    $(this).css({
		                        "border": "",
		                        "background": ""
		                    });
		                }
		            });
		            if (isValid == false) {
		                e.preventDefault();
		            }
				
				if (isValid == true) {
				// will pass the form date using the jQuery serialize function
				$.post('${pageContext.request.contextPath}/add', $(this).serialize(), 
						function(response) {
							//$('#dependentFromResponse').text("");
							//$('#dependentFromResponse').text(response);
							json=response;
							updatePage();
				});
				}
				
				e.preventDefault(); // prevent actual form submit and page reload
			});
		}

		var mData = '[{"author": "Dan Brown", "isbn": "123456", "title": "Digital Fortress"},{"author": "JK Rowling", "isbn": "234567", "title": "Harry Potter and the Chamber of Secrets"}]';

		function createTable(newTable) {
				$('#'+newTable).empty();
					$('#'+newTable).append('<table class="table table-striped table-bordered"></table>');
					var table = $('#'+newTable).children();
					console.log(jsonList);
					mData = $.parseJSON(jsonList);
					//mData = $.each(jQuery.parseJSON(jsonList));
					table.append("<th>Name</th><th>DOB</th><th>Gender</th>")
					$.each(mData, function(i, item) {
					//$.each(jQuery.parseJSON(jsonList), function(){
			   		table.append("<tr><td>"+this['name']+"</td><td>"+this['dob']+"</td><td>"+this['gender']+"</td></tr>");
			});
		}
/* 
		function createTable(newTable) {
			$('#'+newTable).empty();
				$('#'+newTable).append('<table class="table table-striped table-bordered"></table>');
				var table = $('#'+newTable).children();
		
				mData = $.parseJSON(mData);
				table.append("<th>Author</th><th>ISBN</th><th>TITLE</th>")
				$.each(mData, function(i, item) {
		   		table.append("<tr><td>"+this['author']+"</td><td>"+this['isbn']+"</td><td>"+this['title']+"</td></tr>");
		});
	}
 */
		
		function displayDependents() {
			$("#tableId > tbody").empty()
			$.each(jQuery.parseJSON(jsonList), function() {
				prevItemCounter++;
				addRowToTable(this['name'],this['dob'],this['gender']);
				console.log(this['name']);
			});
		}
	 	function updatePage(){
	 		$.ajax({
	 			type:'GET',
	 			url:"<c:url value="/jsonlistAllDependents"/>",
	 			success:function(data) {
	 				jsonList=null;
	 				jsonList = JSON.stringify(data);
	 				initTable();
	 			}
	 		});
	 		
		}
	 	function initTable() {
			//$("#tableId > tbody").empty()
			//$("#tableId > tbody").html("");
			//$('#tableId tbody').remove();
			//$('#tbodyId').children('tr').remove();
			//itemsToAdd();
			//$("#tableHeader").show();
			//$("#tableId").show();
			console.log("-->showMessages() Data recieved: " + jsonList);
			//prevItemCounter=0;
			
			
			createTable('newTable')
			//displayDependents();
			//showItems();
	 	}
	 	
	 	function itemsToAdd() {
	 		var currentItemCounter=0;
	 		console.log("Items previously added: " + prevItemCounter);
			$.each(jQuery.parseJSON(jsonList), function() {
				currentItemCounter++;
			});
			console.log("Items currently added: " + currentItemCounter);
			
	 	}
	 	
	 	function startTimer() {
	 		console.log("-->startTimer()")
	 	}
	 	
		function onload() {
			updatePage();
			startTimer();
			submitfunction();
			$('#formId').submit(function(e) {
				e.preventDefault(); // prevent actual form submit and page reload
			});
		}
		
		$(document).ready(onload);
</script>

</body>
</html>