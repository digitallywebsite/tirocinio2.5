<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,gestionestudente.model.Studente,gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendale"%>



<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<title>Modifica Profilo Personale Professore Tutor Aziendale</title>
	
	<%@ include file="fragments/head.html" %>
	<%@ include file="fragments/nav.jsp" %>
</head>

<body>
	
	<%
	if(sessione_teacher!=null)		
	{	 if(sessione_teacher.getEmail().length()>0)
		 { 
		  %>
		  	<div class="container">
		  	<h2>&ensp;Modifica Profilo (<%=sessione_teacher.getNome()%> <%=sessione_teacher.getCognome()%>)</h2>
		  	
		  	<%if(request.getAttribute("message_success_profile")!=null)
		      {%>
		      <div class="alert alert-success">
		        <strong>Complimenti!</strong> <%=request.getAttribute("message_success_profile")%>
		      </div>
	      	<%}
		  	
		  	  if(request.getAttribute("email_not_valid_profile")!=null)
		      {%>
		      <div class="alert alert-danger">
		        <strong> <%=request.getAttribute("email_not_valid_profile")%></strong> 
		      </div>
	      	<%}
		  	if(request.getAttribute("company_not_valid_upload")!=null)
		      {%>
		      <div class="alert alert-danger">
		        <strong> <%=request.getAttribute("company_not_valid_upload")%></strong> 
		      </div>
	      	<%}
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
		  	if(request.getAttribute("luogo_not_valid_profile")!=null)
		      {%>
		      <div class="alert alert-danger">
		        <strong> <%=request.getAttribute("luogo_not_valid_profile")%></strong> 
		      </div>
	      	<%}  
	      	
		  	  if(request.getAttribute("fax_not_valid_profile")!=null)
		      {%>
		      <div class="alert alert-danger">
		        <strong> <%=request.getAttribute("fax_not_valid_profile")%></strong> 
		      </div>
	      	<%}
		  	  
		  	  if(request.getAttribute("telefono_not_valid_profile")!=null)
		      {%>
		      <div class="alert alert-danger">
		        <strong> <%=request.getAttribute("telefono_not_valid_profile")%></strong> 
		      </div>
	      	<%}
		  	  
	      	  if(request.getAttribute("message_danger_profile")!=null)
		      {%>
		      <div class="alert alert-danger">
		        <strong>Spiacenti!</strong> <%=request.getAttribute("message_danger_profile")%>
		      </div>
	      	<%}%>
		  	<form action="createprofile" method="post" enctype="multipart/form-data"> 
		  	 <div class="col-sm-3">   
		  	 	  <img src="<%=sessione_teacher.getImmagine_profilo()%>" class="img-rounded" style="width:100%;width:180px;height:180px;">
			      <div class="form-group"> 
					   <label for="first_name">Scegli Immagine Profilo:</label>
					   <input type="file" required accept="image/*" name="image_profile" >
				  </div>
			 </div>
			 <div class="col-sm-5">
			  
			  	<input type="hidden" name="action" value="EditProfile">
			  
			    <div class="form-group"> 
			      <label for="first_name">Nome:</label>
				  <input name="first_name" disabled type="text" maxlength="25" required class="form-control" value="<%=sessione_teacher.getNome()%>">
			    </div>
			    <div class="form-group">
			      <label for="last_name">Cognome:</label>
				  <input name="last_name" disabled type="text" maxlength="25"  required class="form-control" value="<%=sessione_teacher.getCognome()%>">
			    </div>
			    <div class="form-group">
			      <label for="type">Tipo (Tutor Aziendale/Professore): </label>
				  <input name="type" type="text" maxlength="24" required class="form-control" value="<%=sessione_teacher.getTipo()%>" disabled>
			    </div>
			    <div class="form-group">
			      <label for="company">Company: </label>
				  <input name="company" type="text" maxlength="64" required class="form-control" value="<%=sessione_teacher.getCompany()%>">
			    </div>
			    <div class="form-group">
			      <label for="address">Indirizzo: </label>
				  <input name="address" type="text" maxlength="32" required class="form-control" value="<%=sessione_teacher.getIndirizzo()%>">
			    </div>
			    <div class="form-group">
			      <label for="phone">Telefono: </label>
				  <input name="phone" type="text" maxlength="15" required class="form-control" value="<%=sessione_teacher.getTelefono()%>">
			    </div>
			    <div class="form-group">
			      <label for="fax">Fax: </label>
				  <input name="fax" type="text" maxlength="15" required class="form-control" value="<%=sessione_teacher.getFax()%>">
			    </div>
			    <div class="form-group">
			      <label for="email">Email:</label>
			      <input type="text" maxlength="64" class="form-control" required value="<%=sessione_teacher.getEmail()%>" name="email">
			    </div>
			    <div class="form-group">
			      <label for="city">Luogo:</label>
			      <input type="text" maxlength="32" class="form-control" required value="<%=sessione_teacher.getCitta()%>" name="city">
			    </div>
			    <div class="form-group">
			      <label for="website">Sito Web:</label>
			      <input type="text" maxlength="64" class="form-control" <% if(sessione_teacher.getSitoweb()!=""){ %> value="<%=sessione_teacher.getSitoweb()%>" <%}%> placeholder="Inserisci il sito web" name="website">
			    </div>
			    <div class="form-group">
				  <label for="whoiam">Il Mio Progetto:</label>
				  <textarea type="text" maxlength="512" rows="7" cols="25" class="form-control" required name="whoiam"><%=sessione_teacher.getChisono()%></textarea>
				</div>
			    <button type="submit" value="Send" class="btn btn-success">Modifica Profilo</button> 
		 	</div> 
		 	</form>
		  	</div>
		  	<br><br><br><br><br>
		  <%} %>
	<%	      
	}
	else
	{
		if(sessione_tutor!=null)		
		{	 if(sessione_tutor.getEmail().length()>0)
			 {%>
				<div class="container">
		  	<h2>&ensp;Modifica Profilo (<%=sessione_tutor.getNome()%> <%=sessione_tutor.getCognome()%>)</h2>
		  	
		  	<%if(request.getAttribute("message_success_profile")!=null)
		      {%>
		      <div class="alert alert-success">
		        <strong>Complimenti!</strong> <%=request.getAttribute("message_success_profile")%>
		      </div>
	      	<%}
		  	
		  	  if(request.getAttribute("email_not_valid_profile")!=null)
		      {%>
		      <div class="alert alert-danger">
		        <strong> <%=request.getAttribute("email_not_valid_profile")%></strong> 
		      </div>
	      	<%}
		  	  
		  	if(request.getAttribute("luogo_not_valid_profile")!=null)
		      {%>
		      <div class="alert alert-danger">
		        <strong> <%=request.getAttribute("luogo_not_valid_profile")%></strong> 
		      </div>
	      	<%} 
      	
	  	  	if(request.getAttribute("fax_not_valid_profile")!=null)
	      	{%>
	      		<div class="alert alert-danger">
	        		<strong> <%=request.getAttribute("fax_not_valid_profile")%></strong> 
	      		</div>
    		<%}
	  	  
	  	  	if(request.getAttribute("telefono_not_valid_profile")!=null)
	      	{%>
	      		<div class="alert alert-danger">
	        		<strong> <%=request.getAttribute("telefono_not_valid_profile")%></strong> 
	     		</div>
		  	  
	      	<%}
	  	  	
	      	  if(request.getAttribute("message_danger_profile")!=null)
		      {%>
		      <div class="alert alert-danger">
		        <strong>Spiacenti!</strong> <%=request.getAttribute("message_danger_profile")%>
		      </div>
	      	<%}%>
		  	<form action="createprofile" method="post" enctype="multipart/form-data"> 
		  	 <div class="col-sm-3">   
		  	 	  <img src="<%=sessione_tutor.getImmagine_profilo()%>" class="img-rounded" style="width:100%;width:180px;height:180px;">
			      <div class="form-group"> 
					   <label for="first_name">Scegli Immagine Profilo:</label>
					   <input type="file" required accept="image/*" name="image_profile" >
				  </div>
			 </div>
			 <div class="col-sm-5">
			  
			  	<input type="hidden" name="action" value="EditProfile">
			  
			    <div class="form-group"> 
			      <label for="first_name">Nome:</label>
				  <input name="first_name" type="text" maxlength="25" required class="form-control" value="<%=sessione_tutor.getNome()%>" disabled>
			    </div>
			    <div class="form-group">
			      <label for="last_name">Cognome:</label>
				  <input name="last_name" type="text" maxlength="25"  required class="form-control" value="<%=sessione_tutor.getCognome()%>" disabled>
			    </div>
			    <div class="form-group">
			      <label for="type">Tipo (Tutor Aziendale/Professore): </label>
				  <input name="type" type="text" maxlength="24" required class="form-control" value="<%=sessione_tutor.getTipo()%>" disabled>
			    </div>
			    <div class="form-group">
			      <label for="company">Company: </label>
				  <input name="company" type="text" maxlength="64" required class="form-control" value="<%=sessione_tutor.getCompany()%>">
			    </div>
			    <div class="form-group">
			      <label for="address">Indirizzo: </label>
				  <input name="address" type="text" maxlength="32" required class="form-control" value="<%=sessione_tutor.getIndirizzo()%>">
			    </div>
			    <div class="form-group">
			      <label for="phone">Telefono: </label>
				  <input name="phone" type="text" maxlength="15" required class="form-control" value="<%=sessione_tutor.getTelefono()%>">
			    </div>
			    <div class="form-group">
			      <label for="fax">Fax: </label>
				  <input name="fax" type="text" maxlength="15" required class="form-control" value="<%=sessione_tutor.getFax()%>">
			    </div>
			    <div class="form-group">
			      <label for="email">Email:</label>
			      <input type="text" maxlength="64" class="form-control" required value="<%=sessione_tutor.getEmail()%>" name="email">
			    </div>
			    <div class="form-group">
			      <label for="city">Luogo:</label>
			      <input type="text" maxlength="32" class="form-control" required value="<%=sessione_tutor.getCitta()%>" name="city">
			    </div>
			    <div class="form-group">
			      <label for="website">Sito Web:</label>
			      <input type="text" maxlength="64" class="form-control" <% if(sessione_tutor.getSitoweb()!=""){ %> value="<%=sessione_tutor.getSitoweb()%>" <%}%> placeholder="Inserisci il sito web" name="website">
			    </div>
			    <div class="form-group">
				  <label for="whoiam">Chi Sono:</label>
				  <textarea type="text" maxlength="512" rows="7" cols="25" class="form-control" required name="whoiam"><%=sessione_tutor.getChisono()%></textarea>
				</div>
			    <button type="submit" value="Send" class="btn btn-success">Modifica Profilo</button> 
		 	</div> 
		 	</form>
		  	</div>
		  	<br><br><br><br><br>
  		   <%}
		}
		else
		{%>
			<script>  window.location.href = "index.jsp"; </script> 
	  <%}
	}%>