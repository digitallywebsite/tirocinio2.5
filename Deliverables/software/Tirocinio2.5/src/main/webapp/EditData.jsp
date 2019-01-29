<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,gestionestudente.model.Studente,gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendale"%>

<%
	Studente register = (Studente) request.getAttribute("customer");
%>

<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<title>Modifica Dati Pesonali</title>	
	
	<%@ include file="fragments/head.html" %>
	<%@ include file="fragments/nav.jsp" %>
</head>

<body>
	
	<div class="container">
	
	<% if(session.getAttribute("vuota")!=null)
	   {
	%>
		<div class="alert alert-danger">
		    <strong>Spiacenti!</strong> <%=session.getAttribute("vuota")%> 
		</div>
	
	<%  
	   }  
	
		if(session.getAttribute("email_not_valid")!=null)
		   {
		%>
			<div class="alert alert-danger">
			    <strong>Spiacenti! </strong> <%=session.getAttribute("email_not_valid")%> 
			</div>
		
		<%  
		   } 
		
		if(session.getAttribute("username_not_valid")!=null)
		   {
		%>
			<div class="alert alert-danger">
			    <strong>Spiacenti! </strong> <%=session.getAttribute("username_not_valid")%> 
			</div>
		
		<%  
		   } 
		if(session.getAttribute("firstname_not_valid")!=null)
		   {
		%>
			<div class="alert alert-danger">
			    <strong>Spiacenti! </strong> <%=session.getAttribute("firstname_not_valid")%> 
			</div>
		
		<%  
		   }
		if(session.getAttribute("lastname_not_valid")!=null)
		   {
		%>
			<div class="alert alert-danger">
			    <strong>Spiacenti! </strong> <%=session.getAttribute("lastname_not_valid")%> 
			</div>
		
		<%  
		   }
		if(session.getAttribute("email_not_ok")!=null)
		   {
		%>
			<div class="alert alert-danger">
			    <strong>Spiacenti! </strong> <%=session.getAttribute("email_not_ok")%> 
			</div>
		
		<%  
		   }  
	
	   if(session.getAttribute("editdata_fault")!=null)
	   {
	%>
		<div class="alert alert-danger">
		    <strong>Spiacenti!</strong> <%=session.getAttribute("editdata_fault")%> , qualcosa è andato storto. 
		</div>
	
	<%  
	   }  
	   else 
		   if(session.getAttribute("editdata_completed")!=null)
		   {
		%>
			<div class="alert alert-success">
		      <strong>Congratulazione!</strong> <%=session.getAttribute("editdata_completed")%> , i dati sono stati salvati come <%=session.getAttribute("editdata_completed_as_student_tutor_teacher")%>.
		    </div>
		
		<% }  
	
	
	 int control = 0;
	 
	 if(sessione_student!=null)
		if(sessione_student.getEmail()!=null)
			control = 1;
	 
	 if(sessione_teacher!=null)
			if(sessione_teacher.getEmail()!=null)
				control = 1;
	 
	 if(sessione_tutor!=null)
			if(sessione_tutor.getEmail()!=null)
				control = 1;
			 

	if(control == 1)
	{			  
		 session.removeAttribute("editdata_completed");
		 session.removeAttribute("matricola_vuota");
		 session.removeAttribute("editdata_fault");
		 session.removeAttribute("email_not_valid");
		 session.removeAttribute("email_not_ok");
		 session.removeAttribute("username_not_valid");
		 session.removeAttribute("firstname_not_valid");
		 session.removeAttribute("lastname_not_valid");
		 %> 
    
        
		  <h2>Modifica i tuoi dati 
		     <%if(sessione_student!=null){ %> <%=sessione_student.getNome() %> <%=sessione_student.getCognome() %> (Studente) <%}%>
		     <%if(sessione_teacher!=null){ %> <%=sessione_teacher.getNome() %> <%=sessione_teacher.getCognome() %> (Professore) <%}%>
		     <%if(sessione_tutor!=null){ %> <%=sessione_tutor.getNome() %> <%=sessione_tutor.getCognome() %> (Tutor Aziendale)<%}%>
		  </h2>
		  <div class="col-sm-3"> 
		  </div>
		  <div class="col-sm-5">
		  <form name='editData_forms' action="EditData" method="post"> 
		  	<input type="hidden" name="action" value="editData">
		  
		    <div class="form-group"> 
		      <label for="first_name">Nome:</label>
			  <input name="first_name" type="text" maxlength="25" required class="form-control" placeholder="Inserisci nome" value="<%if(sessione_student!=null){%><%=sessione_student.getNome()%><%}%><%if(sessione_teacher!=null){%><%=sessione_teacher.getNome()%><%}%><%if(sessione_tutor!=null){%><%=sessione_tutor.getNome()%><%}%>">
		    </div>
		    <div class="form-group">
		      <label for="last_name">Cognome:</label>
			  <input name="last_name" type="text" maxlength="25"  required class="form-control" placeholder="Inserisci Cognome" value="<% if(sessione_student!=null){ %><%=sessione_student.getCognome()%><%}%><% if(sessione_teacher!=null){ %><%=sessione_teacher.getCognome()%><%}%><% if(sessione_tutor!=null){ %><%=sessione_tutor.getCognome()%><%}%>">
		    </div>
		    <div class="form-group">
		      <label for="username">Username: </label>
			  <input name="username" type="text" maxlength="32" required class="form-control" placeholder="Inserisci Username" value="<% if(sessione_student!=null){ %><%=sessione_student.getUsername()%><%}%><% if(sessione_teacher!=null){ %><%=sessione_teacher.getUsername()%><%}%><% if(sessione_tutor!=null){ %><%=sessione_tutor.getUsername()%><%}%>">
		    </div>
		    <div class="form-group">
		      <label for="email">Email:</label>
		      <input type="text" maxlength="64" class="form-control" required placeholder="Inserisci email" name="email" value="<%if(sessione_student!=null){%><%=sessione_student.getEmail()%><%}%><% if(sessione_teacher!=null){ %><%=sessione_teacher.getEmail()%><%}%><% if(sessione_tutor!=null){ %><%=sessione_tutor.getEmail()%><%}%>">
		    </div>
		    <div class="form-group">
		      <label for="pwd">Password:</label>
		      <input type="password" maxlength="25" class="form-control" required placeholder="Inserisci password" name="psw">
		    </div>
		    <% if(sessione_student!=null){%>
		    <div class="form-group">
		      <label for="matricola">Matricola:</label>
		      <input type="text" maxlength="10" readonly class="form-control" placeholder="Inserisci matricola" name="matricola" value="<%=sessione_student.getMatricola()%>">
		    </div>
		    <%}%>
		    <button type="submit" value="Send" class="btn btn-success">Modifica </button> 
		    <button type="reset" value="Reset" class="btn btn-danger"> Svuota Campi </button> 
		  </form>
		  </div>
		</div>
	<%
	}
	else
	{%>
		 <script>  window.location.href = "index.jsp"; </script>
	<%}
	%>
</body>
</html>

