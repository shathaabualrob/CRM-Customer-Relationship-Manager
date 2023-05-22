<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="web.customer.tracker.util.SortUtils" %>
<!-- construct a sort link for each th -->		
<!--  First Name -->
<c:url var="sortLinkFirstName" value="/customer/sort">
	<c:param name="sort" value="<%= Integer.toString(SortUtils.FIRST_NAME) %>" />
</c:url>
<!-- Last Name -->
<c:url var="sortLinkLastName" value="/customer/sort">
	<c:param name="sort" value="<%= Integer.toString(SortUtils.LAST_NAME) %>" />
</c:url>
<!-- Email-->
<c:url var="sortLinkEmail" value="/customer/sort">
	<c:param name="sort" value="<%= Integer.toString(SortUtils.EMAIL) %>" />
</c:url>

<!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>
			List Customers
		</title>
		<link type="text/css"
			  rel="stylesheet"
			  href="${pageContext.request.contextPath}/resources/css/style.css"
		/> <!-- the first part gives us the proper name of the application  -->
		
	</head>
	<body>
		<div id="wrapper">
			<div id="header">
				<h2>CRM - Customer Relationship Manager</h2>
			</div>
		</div>
		<div id="container">
			<div id="content">
				<input type="button" value="Add Customer"
				       onClick="window.location.href='showFormForAdd'; return false;"
						class="add-button"
				/>
				<!--  add a search box -->
	            <form:form action="search" method="GET">
	                Search customer: <input type="text" name="name" />
	                
	                <input type="submit" value="Search" class="add-button" />
	            </form:form>
				<table>
					<tr>
						
						<th><a href="${sortLinkFirstName}">First Name</a></th>
						<th><a href="${sortLinkLastName}">Last Name</a></th>
						<th><a href="${sortLinkEmail}">Email</a></th>
						<th>Action</th>
					</tr>
					<c:forEach var="customer" items="${customers}">
						
						<!-- construct an update link with customer id -->
						<c:url var="updateLink" value="/customer/showFormForUpdate">
							<c:param name="customerId" value="${customer.id}"/>
						</c:url>
						<!-- construct a delete link with customer id -->
						<c:url var="deleteLink" value="/customer/delete">
							<c:param name="customerId" value="${customer.id}"/>
						</c:url>
						<tr>
							<td>${customer.firstName }</td>
							<td>${customer.lastName }</td>
							<td>${customer.email }</td>
							<td>
								<a href="${updateLink }">Update</a>
								|
								<a href="${deleteLink }"
								   onclick="if(!(confirm('Are You sure you want to delete this customer?'))) return false">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		
		</div>
	</body>
</html>





