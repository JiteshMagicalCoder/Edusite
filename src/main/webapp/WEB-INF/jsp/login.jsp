<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
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


<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->





<script type="text/javascript" >
   function preventBack(){window.history.forward();}
    setTimeout("preventBack()", 0);
    window.onunload=function(){null};
</script>

</head>
<body>

	<%
		response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
		response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
		response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
		response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
		session = request.getSession();

	%>



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

	<!-- /Hero-area -->
	<c:if test="${param.act eq 'lo' }">
		<p class="success">Logout Successfully! Thanks for your visit</p>
	</c:if>
	<!-- Contact -->
	
	<!-- /container -->


	<!-- Login Form-->
	<div class="container">
	 <div class="imgcontainer">
    <img src="img_avatar2.png" alt="Avatar" class="avatar">
  </div>
		
		<form class="form-horizontal_1" method="POST" action="login-user">
			<c:if test="${not empty error}">
				<div class="alert alert-danger">
					<c:out value="${error}"></c:out>
				</div>
			</c:if>
			<div class="form-group">
				<label >Username</label>
				<div >
					<input type="text" class="form-control" name="username"
						value="${user.username }"  required="required"/>
				</div>
			</div>
			<div class="form-group">
				<label >Password</label>
				<div>
					<input type="password" class="form-control" name="password"
						value="${user.password }"  required="required"/>
				</div>
			</div>
			<div class="form-group">
				 <input type="submit" class="btn btn-primary" style="height: 3em; width: 8em" value="Login" />
				
			</div>
		</form>
	</div>
	<!--Login Form End-->
	<!-- Footer -->
	<footer id="footer" class="section">

		<!-- container -->
		<div class="container">

			<!-- row -->
			<div class="row"></div>
			<!-- /row -->

			<!-- row -->
			<div id="bottom-footer" class="row">

				<!-- social -->

				<!-- /social -->

				<!-- copyright -->

				<div class="footer-copyright">
					<span>&copy; Copyright 2019. All Rights Reserved <i
						class="fa fa-heart-o" aria-hidden="true"></i> by EduHub
					</span>
				</div>
			</div>
			<!-- /copyright -->

		</div>
		<!-- row -->


		<!-- /container -->

	</footer>
	<!-- /Footer -->

	<!-- preloader -->

	<!-- /preloader -->


	<!-- jQuery Plugins -->
	<script type="text/javascript" src="static/js/jquery.min.js"></script>
	<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="static/js/main.js"></script>

</body>
</html>
