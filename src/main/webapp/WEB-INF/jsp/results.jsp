<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<title>Controller Audition Spring MVC form Results</title>
</head>
<body>
	<div>
		<div>
			<table>

				<tr>
					<td colspan="2" align="center"><h2>Spring MVC Controller
							Results</h2></td>
				</tr>
				<tr>
					<td>Account Found:</td>
					<td>${lookUpForm.result}</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>