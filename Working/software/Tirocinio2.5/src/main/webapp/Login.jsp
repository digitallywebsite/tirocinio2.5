<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,gestionestudente.model.Studente,gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendale,gestionesegreteria.model.Segreteria"%>

<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<title>Login</title>
	<%@ include file="fragments/head.html" %>
	<%@ include file="fragments/nav.jsp" %>
</head>

<body>
	
	<%  
	
	 if(sessione_student!=null)		
		 if(sessione_student.getEmail().length()==0)
		 {
			 session.removeAttribute("student"); 
		 }
	 
	 if(sessione_teacher!=null)		
		 if(sessione_teacher.getEmail().length()==0)
		 {
			 session.removeAttribute("teacher"); 
		 }
	 
	 if(sessione_tutor!=null)		
		 if(sessione_tutor.getEmail().length()==0)
		 { 
			 session.removeAttribute("tutor"); 
		 }
	 
	 if(sessione_segreteria!=null)		
		 if(sessione_segreteria.getEmail().length()==0)
		 { 
			 session.removeAttribute("segreteria"); 
		 }
    %>
	
	<div class="container">
	
	
	<%
	
	if(request.getAttribute("message_danger")!=null)
	{
		%> 
		
		<div class="alert alert-danger">
	      <strong>Spiacenti!</strong> <%=request.getAttribute("message_danger") %>
		</div>
		
	  <%
	}
	
	if(request.getAttribute("login_not_valid")!=null)
	{
		%> 
		
		<div class="alert alert-danger">
	      <strong>Spiacenti!</strong> <%=request.getAttribute("login_not_valid") %>
		</div>
		
	  <%
	}
	
	if(session.getAttribute("email_not_valid")!=null)
	   {
	%>
		<div class="alert alert-danger">
		    <strong>Spiacenti!</strong> <%=session.getAttribute("email_not_valid")%> 
		</div>
		
	<%    session.removeAttribute("email_not_valid");
	   }
	
	if(sessione_student!=null)		
		 if(sessione_student.getEmail().length()==0)
		 { %> 
			<div class="alert alert-danger">
		      <strong>Spiacenti!</strong> Login come Studente Errato. Email/Username o password errati.
			</div>
			
		  <%
		}
		 else
			{    session.setMaxInactiveInterval(900);
			
			  %>  
			  <div class="alert alert-success">
		        <strong>Benvenuto!</strong> <%=sessione_student.getNome()%> <%=sessione_student.getCognome()%>, login come Studente effettuato.
		      </div>
		      <font color="red"> <h3> Redirect nella tua area riservata tra <span id="secondi">  </span> secondi. </h3> </font>	
			  <script>
				// Redirect dopo 3 secondi
				setTimeout(function() {
					  window.location.href = "PersonalArea.jsp";
				}, 3000);
			  </script>			  
		<% }	
	
	if(sessione_teacher!=null)		
		 if(sessione_teacher.getEmail().length()==0)
		 { %> 
			<div class="alert alert-danger">
		      <strong>Spiacenti!</strong> Login come Professore Errato. Email/Username o password errati.
			</div>
			
		  <%
		}
		 else
			{    session.setMaxInactiveInterval(900);
			
			  %>  
			  <div class="alert alert-success">
		        <strong>Benvenuto!</strong> <%=sessione_teacher.getNome()%> <%=sessione_teacher.getCognome()%>, login come Professore effettuato.
		      </div>
		      <font color="red"> <h3> Redirect nella tua area riservata tra <span id="secondi">  </span> secondi. </h3> </font>	
			  <script>
				// Redirect dopo 3 secondi
				setTimeout(function() {
					  window.location.href = "PersonalAreaTutorProfessore.jsp";
				}, 3000);
			  </script>			  
		<% }
	
	if(sessione_tutor!=null)		
		 if(sessione_tutor.getEmail().length()==0)
		 { %> 
			<div class="alert alert-danger">
		      <strong>Spiacenti!</strong> Login come Tutor Aziendale Errato. Email/Username o password errati.
			</div>
		  <%
		}
		 else
			{    session.setMaxInactiveInterval(900);
			
			  %>  
			  <div class="alert alert-success">
		        <strong>Benvenuto!</strong> <%=sessione_tutor.getNome()%> <%=sessione_tutor.getCognome()%>, login come Tutor Aziendale effettuato.
		      </div>
		      
		      <font color="red"> <h3> Redirect nella tua area riservata tra <span id="secondi">  </span> secondi. </h3> </font>	
			  <script>
				// Redirect dopo 3 secondi
				setTimeout(function() {
					  window.location.href = "PersonalAreaTutorProfessore.jsp";
				}, 3000);
			  </script> 			  
		<% }
	
	if(sessione_segreteria!=null)		
		 if(sessione_segreteria.getEmail().length()==0)
		 { %> 
			<div class="alert alert-danger">
		      <strong>Spiacenti!</strong> Login come Segreteria Errato. Email/Username o password errati.
			</div>
		  <%
		}
		 else
			{    session.setMaxInactiveInterval(900);
			
			  %>  
			  <div class="alert alert-success">
		        <strong>Benvenuta!</strong> <%=sessione_segreteria.getUsername()%>, login come segreteria effettuato.
		      </div>
		      
		      <font color="red"> <h3> Redirect nella tua area riservata tra <span id="secondi">  </span> secondi. </h3> </font>	
			  <script>
				// Redirect dopo 3 secondi
				setTimeout(function() {
					  window.location.href = "PersonalAreaSegretary.jsp";
				}, 3000);
			  </script> 			  
		<% }	
	    %>	   
				
		<script language="JavaScript" type="text/JavaScript" src="js/countdown.js"></script>	
			   
		  
		  <div class="col-sm-3"> 
		  </div>
		  <div class="col-sm-5">
		  <h2>Login Utente</h2>
		  <form name='forms_login' action="login" method="post">
		   <input type="hidden" name="action" value="login"> 
		    <div class="form-group">
		      <label for="email">Email/Username:</label> 
		      <input type="text" class="form-control" required maxlength="64" id="email" placeholder="Inserisci email" name="email">
		    </div>
		    <div class="form-group">
		      <label for="pwd">Password:</label>
		      <input type="password" class="form-control" required maxlength="25" id="pwd" placeholder="Inserisci password" name="psw">
		    </div>
		    <button type="submit" value="Send" class="btn btn-success">Login</button>
		  </form>
		  </div>
		</div>

</body>
</html>