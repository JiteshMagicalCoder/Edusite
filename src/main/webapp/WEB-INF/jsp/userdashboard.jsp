<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<!-- Referring to bootstrap CSS: -->
<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Referring to Bootstrap JS: -->
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<!-- Referring to jQuery JS: -->
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<title>StudyHub</title>

<!-- Google font -->
<link
	href="https://fonts.googleapis.com/css?family=Lato:700%7CMontserrat:400,600"
	rel="stylesheet">



<!-- Font Awesome Icon -->
<link rel="stylesheet" href="static/css/font-awesome.min.css">

<!-- Custom stlylesheet -->
<link type="text/css" rel="stylesheet" href="static/css/style2.css" />

<link type="text/css" rel="stylesheet" href="static/css/style.css" />

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
<script type="text/javascript">
	function preventBack() {
		window.history.forward();
	}
	setTimeout("preventBack()", 0);
	window.onunload = function() {
		null
	};
</script>
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache"); //Forces caches to obtain a new copy of the page from the origin server
		response.setHeader("Cache-Control", "no-store"); //Directs caches not to store the page under any circumstance
		response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
		response.setHeader("Pragma", "no-cache"); //HTTP 1.0 backward compatibility
		session = request.getSession();

		if (session.getAttribute("userid") == null)
			response.sendRedirect("login.jsp");
	%>

	<font color="Red">Welcome User: ${userid}</font>
	<!-- Header -->
	<header id="header">
		<div class="container">



			<!-- Mobile toggle -->
			<button class="navbar-toggle">
				<span></span>
			</button>
			<!-- /Mobile toggle -->
		</div>

		<!-- Navigation -->
		<nav id="nav">
			<ul class="main-menu nav navbar-nav navbar-right">
				<li><a href="/edituser/${userid}" style="right: 30px">Edit
						Profile</a></li>
				<li><a href="/logout" style="right: 30px">Logout</a></li>


			</ul>
		</nav>

		<!-- /Navigation -->

		</div>
	</header>



	<br>
	<br>

	<div class="container text-center" id="taskDiv">



		<h3>Study Materials</h3>

		<div
			style="height: 500px; width: 1000px; overflow: auto; overflow-x: hidden; overflow-y: scroll;">
			<div class="table-responsive">
				<font size="4" face="Georgia">
					<table class="table table-striped">
						<colgroup>
							<col width="200px" />
							<col width="150px" />
							<col width="150px" />
							<col width="16px" />
						</colgroup>
						<thead position: fixed; z-index: 1;>
							<tr
								style="margin: 0; padding: 0; border-collapse: collapse; color: White; width: 517px; height: 20px; text-align: left; background-color: Blue;">
								<th>File id</th>
								<th>File Name</th>
								<th>File Type</th>
								<th>Download</th>


							</tr>
						</thead>
						<tbody>
							<c:forEach var="dbfile" items="${dbfiles}">
								<tr>
									<td>${dbfile.id}</td>
									<td>${dbfile.filename}</td>
									<td>${dbfile.filetype}</td>





									<td><a href="/downloadFile${dbfile.id}"><span
											class="	glyphicon glyphicon-floppy-save"></span></a></td>

								</tr>
							</c:forEach>

						</tbody>

					</table>
				</font>

			</div>
		</div>
		<div align="left">
			<form action="/uploadFile" method="POST"
				enctype="multipart/form-data" />
			<input type="file" name="file" required><br> <input
				type="submit" class="btn btn-primary" id="button"></input>
			</form>
		</div>
	</div>





	<!-- Footer -->
	<footer id="footer" class="section">


		<!-- row -->
		<div id="bottom-footer" class="row">




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
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/main.js"></script>

</body>
</html>
