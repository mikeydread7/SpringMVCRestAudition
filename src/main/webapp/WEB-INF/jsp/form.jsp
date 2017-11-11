<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html lang="en">
<head>
<title>Controller Audition Spring MVC</title>
</head>
<body>
	<div align="center">
		<form:form action="form" method="post" commandName="lookUpForm">
			<table>
				<tr>
					<td colspan="2" align="center"><h2>Spring MVC Controller
							- Search Accounts</h2></td>
				</tr>
				<tr>
					<td>Account:</td>
					<td><form:input  maxlength="12" path="id" /></td>
				</tr>

				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Accoumt LookUp" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>