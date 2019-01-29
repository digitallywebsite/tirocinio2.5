<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,gestionestudente.model.Studente,gestionesegreteria.model.Segreteria"%>



<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<title>Area Personale Segreteria</title>
	
	<%@ include file="fragments/head.html" %>
	<%@ include file="fragments/nav.jsp" %>
</head>

<body>
	
	<div class="container">
	<!--  <h2>Area Personale</h2>-->
	<%
	if(sessione_segreteria!=null)		
	{	 if(sessione_segreteria.getEmail().length()>0)
		 { 
		  %>		      
		      <div class="col-sm-2"> 
		  	  </div>
		  	  <div class="col-sm-7">
		  	  <div class="alert alert-success">
		        <strong>Benvenuta!</strong> <%=sessione_segreteria.getUsername()%>
		      </div>
		      <fieldset>
			  <legend>DASHBOARD</legend>
			  
			  <legend>Utenti Registrati</legend>
			  &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;
			  
			  <button onclick="ShowStudents()" style="width:150px;" class="btn btn-warning">Studenti</button> &ensp;&ensp;&ensp;&ensp;&ensp;
			  <button onclick="ShowTeachers()" style="width:150px;" class="btn btn-danger">Professori</button>	  &ensp;&ensp;&ensp;&ensp;&ensp; 			 
			  <button onclick="ShowTutor()" style="width:150px;" class="btn btn-success">Tutor Aziendali</button>
			  
			  <script>
					function ShowStudents() {
						window.location.href = "segreteria/students.jsp";
						}
			  </script>
			  <script>
					function ShowTeachers() {
						window.location.href = "segreteria/teachers.jsp";
						}
			  </script>
			  <script>
					function ShowTutor() {
						window.location.href = "segreteria/tutor.jsp";
						}
			  </script>
			  </fieldset>
			  <br><br><br>
			  
			  <fieldset>
			  
			  <legend><a href="segreteria/AllTraining.jsp" style="color: black; text-decoration: none;">Tirocini</a></legend>
			  &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;
			  
			  <button onclick="QuestionsTraining()" style="width:180px; height:70px" class="btn btn-warning">Domande Tirocini</button> &ensp;&ensp;&ensp;&ensp;&ensp;
			  <button onclick="CompletedTraining()" style="width:180px; height:70px" class="btn btn-danger">Tirocini Completati</button> 	<br><br> &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;		 
			  <button onclick="CreateProfile()" style="width:180px; height:70px" class="btn btn-success">Domande Tirocini <br>Precedenti</button> &ensp;&ensp;&ensp;&ensp;&ensp;
			  <button onclick="ConvalidateTraining()" style="width:180px; height:70px" class="btn btn-success">Tirocini Convalidati</button>
			  
			  <script>
					function QuestionsTraining() {
						window.location.href = "segreteria/QuestionsTraining.jsp";
						}
			  </script>
			  <script>
					function CompletedTraining() {
						window.location.href = "segreteria/CompletedTraining.jsp";
						}
			  </script>
			  <script>
					function CreateProfile() {
						window.location.href = "segreteria/OldTraining.jsp";
						}
			  </script>
			  <script>
					function ConvalidateTraining() {
						window.location.href = "segreteria/ConvalidateTraining.jsp";
						}
			  </script>
			  </fieldset>
			  
			  <fieldset>
			  
			  </div>
	    <%} %>
	<%	      
	}
	else
	{%>
		<script>  window.location.href = "index.jsp"; </script> 
	<%}%>
	
	</div>
</body>
</html>