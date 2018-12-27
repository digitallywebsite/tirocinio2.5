<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,gestionestudente.model.Studente,gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendale"%>



<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<title>Area Personale Professore Tutor Aziendale</title>
	
	<%@ include file="fragments/head.html" %>
	<%@ include file="fragments/nav.jsp" %>
</head>

<body>
	

	<div class="container">
	<!--  <h2>Area Personale</h2>-->
	<%
	if(sessione_teacher!=null)		
	{	 if(sessione_teacher.getEmail().length()>0)
		 { 
		  %>		      
		      <div class="col-sm-2"> 
		  	  </div>
		  	  <div class="col-sm-7">
		  	  <div class="alert alert-success">
		        <strong>Benvenuto!</strong> <%=sessione_teacher.getNome()%> <%=sessione_teacher.getCognome()%>
		      </div>
		      <fieldset>
			  <legend>Il Mio Profilo</legend>
			  &ensp;&ensp;&ensp;
			  <%if(sessione_teacher.getCompany().length()>0)
			  { %>
				  <button onclick="ShowProfile()" class="btn btn-warning">Visualizza Profilo</button> &ensp;&ensp;&ensp;&ensp;&ensp;
				  <button onclick="EditProfile()" class="btn btn-danger">Modifica Profilo</button>	  &ensp;&ensp;&ensp;&ensp;&ensp; 
			  <%} %>
			  <button onclick="CreateProfile()" class="btn btn-success">Crea Profilo</button>
			  <script>
					function ShowProfile() {
						window.location.href = "ShowProfile.jsp?id="+"<%=sessione_teacher.getUsername()%>";
						}
			  </script>
			  <script>
					function EditProfile() {
						window.location.href = "EditProfile.jsp";
						}
			  </script>
			  <script>
					function CreateProfile() {
						window.location.href = "CreateProfile.jsp";
						}
			  </script>
			  <br><br><br>
			  
			  <fieldset>
			  <legend>I Miei Dati</legend>
			  &ensp;&ensp;&ensp;
			  
			  <button onclick="EditData()" class="btn btn-danger">Modifica Dati Personali</button>	 
			  <script>
					function EditData() {
						window.location.href = "EditData.jsp";
						}
			  </script>
			  </fieldset>
			  <br><br><br>
			  
			  <fieldset>
			  <legend>Tirocini</legend>
			  &ensp;&ensp;&ensp;
			  
			  <button onclick="Training()" class="btn btn-warning">Domande Tirocini</button>	 
			  <script>
					function Training() {
						window.location.href = "RequestTraining.jsp";
						}
			  </script>
			  </fieldset>
			  </div>
	    <%} %>
	<%	      
	}
	else
	{
		if(sessione_tutor!=null)		
		{	 if(sessione_tutor.getEmail().length()>0)
			 { 
		  %>
			  <div class="col-sm-2"> 
		  	  </div>
		  	  <div class="col-sm-7">
		  	  <div class="alert alert-success">
		        <strong>Benvenuto!</strong> <%=sessione_tutor.getNome()%> <%=sessione_tutor.getCognome()%>
		      </div>
		      <fieldset>
			  <legend>Il Mio Profilo</legend>
			  &ensp;&ensp;&ensp;
			  <%if(sessione_tutor.getCompany().length()>0)
			  { %>
				  <button onclick="ShowProfile()" class="btn btn-warning">Visualizza Profilo</button> &ensp;&ensp;&ensp;&ensp;&ensp;
				  <button onclick="EditProfile()" class="btn btn-danger">Modifica Profilo</button>	  &ensp;&ensp;&ensp;&ensp;&ensp; 
			  <%} %>
			  <button onclick="CreateProfile()" class="btn btn-success">Crea Profilo</button>
			  <script>
					function ShowProfile() {
						
						window.location.href = "ShowProfile.jsp?id="+"<%=sessione_tutor.getUsername()%>";
						
						}
			  </script>
			  <script>
					function EditProfile() {
						window.location.href = "EditProfile.jsp";
						}
			  </script>
			  <script>
					function CreateProfile() {
						window.location.href = "CreateProfile.jsp";
						}
			  </script>
			  <br><br><br>
			  
			  <fieldset>
			  <legend>I Miei Dati</legend>
			  &ensp;&ensp;&ensp;
			  
			  <button onclick="EditData()" class="btn btn-danger">Modifica Dati Personali</button>	 
			  <script>
					function EditData() {
						window.location.href = "EditData.jsp";
						}
			  </script>
			  </fieldset>
			  <br><br><br>
			  
			  <fieldset>
			  <legend>Tirocini</legend>
			  &ensp;&ensp;&ensp;
			  
			  <button onclick="Training()" class="btn btn-warning">Domande Tirocini</button>	 
			  <script>
					function Training() {
						window.location.href = "RequestTraining.jsp";
						}
			  </script>
			  </fieldset>
			  </div>
  <%		}
		}
		else
		{%>
			<script>  window.location.href = "index.jsp"; </script> 
	  <%}
	}%>
	
	</div>
</body>
</html>