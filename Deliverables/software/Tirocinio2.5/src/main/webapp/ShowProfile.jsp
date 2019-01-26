<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,gestionestudente.model.Studente,gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendale,gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendaleModel"%>

<%
	
	String username = request.getParameter("id");
	
	if(username != "" || username != null)
	{
		
	ProfessoreTutorAziendaleModel Tutormodel = null;
	
	Tutormodel = new ProfessoreTutorAziendaleModel();
	
	ProfessoreTutorAziendale teacherTutor = (ProfessoreTutorAziendale) Tutormodel.doRetrieveByKey(username);
	%>
		
	<!DOCTYPE html>
	<html>
	
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<title>Profilo Professore Tutor Aziendale</title>
		
		<%@ include file="fragments/head.html" %>
		<%@ include file="fragments/nav.jsp" %>
	</head>
	
	<body>
		
		<div class="container">
		<!--  <h2>Area Personale</h2>-->
		<%
		if(teacherTutor!=null)		
		{	 if(teacherTutor.getEmail().length()>0)
			 { 
			  %>		      
			      <div class="col-sm-2"> 
			      <img src="<%=teacherTutor.getImmagine_profilo()%>" class="img-rounded" style="width:100%;width:180px;height:180px;">
			  	  </div>
			  	  <div class="col-sm-5">
			  	  <font size="6px">
			  	  <strong>Profilo di  <%=teacherTutor.getNome()%> <%=teacherTutor.getCognome()%> </strong>
			      </font><br>
			      <font size="5px">
			      <i class="fa fa-user"> <%=teacherTutor.getTipo()%> </i> <br>
			      <i class="fa fa-building"> <%=teacherTutor.getCompany()%> </i> <br>
			      <i class="fa fa-home"> <%=teacherTutor.getCitta()%> </i> <br>
			      <i class="fa fa-phone"> <%=teacherTutor.getTelefono()%> </i> <br>
			      <i class="fa fa-fax"> <%=teacherTutor.getFax()%> </i> <br>
			      <i class="fa fa-envelope"> <%=teacherTutor.getEmail()%> </i> <br>
			      <i class="fa fa-map-marker"> <%=teacherTutor.getIndirizzo()%> </i> <br>
			      <%if(teacherTutor.getSitoweb().length()>0){ %><i class="fa fa-link"> <a href="<%=teacherTutor.getSitoweb()%>" target="_blank">  <%=teacherTutor.getSitoweb()%>  </a> </i><br> <%} %>
			      Il Mio Progetto: <br>
			      </font>
			      <font size="3px">
			      	<div class="review-block-description" style="word-wrap:break-word;"> <%=teacherTutor.getChisono()%> </div>
			      </font>
				  </div>
		    <%} %>
		<%	      
		}
		else
		{%>
				<script>  window.location.href = "PersonalAreaTutorProfessore.jsp"; </script> 
		<%
		}
	}
	else
	{%>
		<script>  window.location.href = "index.jsp"; </script> 
	<%
	}
	%>
	
	</div>
</body>
</html>