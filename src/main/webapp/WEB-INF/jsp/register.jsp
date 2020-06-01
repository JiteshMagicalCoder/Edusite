<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>


<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>StudyHub</title>

<!-- Google font -->
<link
	href="https://fonts.googleapis.com/css?family=Lato:700%7CMontserrat:400,600"
	rel="stylesheet">

<!-- Bootstrap -->
<link type="text/css" rel="stylesheet"
	href="static/css/bootstrap.min.css" />
	

<!-- Font Awesome Icon -->
<link rel="stylesheet" href="static/css/font-awesome.min.css">

<!-- Custom stlylesheet -->
<link type="text/css" rel="stylesheet" href="static/css/style.css" />
<link type="text/css" rel="stylesheet" href="static/css/style2.css" />

<title>Registration</title>
<script type="text/javascript">
	var loadFile = function(event) {
		var reader = new FileReader();
		reader.onload = function() {
			var output = document.getElementById('output');
			output.src = reader.result;
		};
		reader.readAsDataURL(event.target.files[0]);
	};
</script>
</head>

</head>
<body >
	<!-- Header -->
	<header id="header">
		<div class="container">

			<div class="navbar-header">


				<!-- Mobile toggle -->
				<button class="navbar-toggle">
					<span></span>
				</button>
				<!-- /Mobile toggle -->
			</div>

			<!-- Navigation -->
			<nav id="nav">
				<ul class="main-menu nav navbar-nav navbar-right">
					<li><a href="/">Home</a></li>
					<li><a href="/aboutus">About Us</a></li>
					<li><a href="/registerthym">Register</a></li>
					<li><a href="/login">Login</a></li>


				</ul>
			</nav>

			<!-- /Navigation -->

		</div>
	</header>

	<div class="form-container ">
		<h1>Registration</h1>

		<form:form method="POST" modelAttribute="user" class="form-horizontal"
			action="/save" enctype="multipart/form-data">



			<div class="row">
				<div>
					<label for="username">User
						Name</label>
					<div>
						<form:input type="text" path="username" id="username"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="username" class="help-inline" />
						</div>
					</div>
				</div>
			</div>




			<div class="row">
				<div>
					<label for="firstName">First
						Name</label>
					<div>
						<form:input type="text" path="firstName" id="firstName"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="firstName" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div>
					<label  for="lastName">Last
						Name</label>
					<div>
						<form:input type="text" path="lastName" id="lastName"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="lastName" class="help-inline" />
						</div>
					</div>
				</div>
			</div>






			<div class="row">
				<div>
					<label for="email">Email id:</label>
					<div >
						<form:input type="text" path="email" id="email"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="email" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div >
					<label  for="password">Password</label>
					<div>
						<form:input type="password" path="password" id="password"
							class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="password" class="help-inline" />
						</div>
					</div>
				</div>
			</div>




			<div class="row">
				<div>
					<label for="phoneNumber">Phone
						Number</label>
					<div>
						<form:input type="text" path="phoneNumber" id="phoneNumber"
							class="form-control input-sm"  />
						<div class="has-error">
							<form:errors path="phoneNumber" class="help-inline" />
						</div>
					</div>
				</div>
			</div>




			<div >

				<img id="output"
					src="https://via.placeholder.com/100
					alt="your image" width="100" height="100" /> <input type="file" accept="image/*"
					onchange="loadFile(event)" type="file" name="files"
					class="file-input" required />


			</div>

			<div class="form-group">
				<span th:each="file, itrStat : ${userfiles}" class="imagecontainer"
					th:id="'imageindex'+${itrStat.index}"> </span>
			</div>



			<div class="row">
				<div class="form-actions">
					<input type="submit" value="Register" class="btn btn-primary"
						style="height: 3em; width: 8em">

				</div>
			</div>



		</form:form>
	</div>




</body>
</html>