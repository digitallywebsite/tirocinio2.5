<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
   
<html>
<head>
<title>
	Home Tirocinio2.5
</title>

<%@ include file="fragments/head.html" %>
<%@ include file="fragments/nav.jsp" %>
</head>
<body>
<%@ include file="fragments/slider.html" %>
<br><br><br>
<div class="container">
	<div class="col-sm-3"> 
	</div>
	<div class="col-sm-3">
		<center>
			<a href="teacherList.jsp" style="color: black; text-decoration: none;
			">
				<i class="fa fa-address-card-o fa-5x"> </i> <br>
				Elenco Professori
			</a>
		</center>
	</div>
	<div class="col-sm-3">
		<center>
			<a href="tutorList.jsp" style="color: black; text-decoration: none;
			"> 
				<i class="fa fa-address-card fa-5x"> </i> <br>
				Elenco Tutor Aziendali
			</a>
		</center>
	</div>
</div>
</body>
</html>