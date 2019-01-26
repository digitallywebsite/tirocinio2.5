<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,gestionestudente.model.Studente"%>



<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<title>Area Personale</title>
	
	<%@ include file="fragments/head.html" %>
	<%@ include file="fragments/nav.jsp" %>
</head>

<body>
		
	<div class="container">
	<h2>Area Personale</h2>
	<%
	if(sessione_student!=null)		
	{	 if(sessione_student.getEmail().length()>0)
		 { 
		  %>  
			  <div class="alert alert-success">
		        <strong>Benvenuto!</strong> <%=sessione_student.getNome()%> <%=sessione_student.getCognome()%>, Matricola: <%=sessione_student.getMatricola()%>
		      </div>	
		      <% 
		      if(request.getAttribute("message_success")!=null)
		      {%>
			      <div class="alert alert-success">
			        <strong>Complimenti!</strong> <%=request.getAttribute("message_success")%>
			      </div>
		      <%} 
		      if(request.getAttribute("message_danger")!=null && request.getAttribute("filenotsupported")==null)
		      {%>
			      <div class="alert alert-danger">
			        <strong>Spiacenti!</strong> Seleziona un file da caricare!
			      </div>
		      <%}
		      if(request.getAttribute("filenotsupported")!=null)
		      {%>
			      <div class="alert alert-danger">
			        <strong>Spiacenti!</strong> <%=request.getAttribute("filenotsupported") %>
			      </div>
		      <%}
		      if(request.getAttribute("session_training")!=null) 
		      {%>
			      <div class="alert alert-success">
			        <strong>Complimenti!</strong> <%=request.getAttribute("session_training")%>
			      </div>
		      <%} 
		      if(request.getAttribute("session_training_fault")!=null) 
		      {%>
			      <div class="alert alert-danger">
			        <strong>Spiacenti!</strong> <%=request.getAttribute("session_training_fault")%>
			      </div>
		      <%} 
		      
		      if(request.getAttribute("Edit_Link")!=null) 
		      {%>
			      <div class="alert alert-warning">
			        <strong>Attenzione!</strong> <%=request.getAttribute("Edit_Link")%>
			      </div>
		      <%} 
		      %>
		      
		      <div class="col-sm-2"> 
		  	  </div>
		  	  <div class="col-sm-7">
		  	  <font size="5px">
		      <i class="fa fa-envelope"> <%=sessione_student.getEmail()%> </i> <br>
		      <i class="fa fa-user"> <%=sessione_student.getUsername()%> </i> <br>
		      </font>
		      <% if(sessione_student.getLinkedin().length()!=0)
		         {%>
		    	     <font size="5px"> <i class="fa fa-linkedin-square"> <a href="<%=sessione_student.getLinkedin()%>" target="_blank">  Profilo Linkedin </a> </i> </font>
		    	    <button onclick="myFunction()" class="btn btn-default">Modifica Profilo Linkedin</button>   
		    	    					
		        <%}
		          else
		          {%>
		        	  <button onclick="myFunction()">Inserisci Profilo Linkedin</button>
		        <%}%> 
		        	  <span id="link"><%if(request.getAttribute("Edit_Linkedin")!=null){ %><%=request.getAttribute("Edit_Linkedin")%> <% session.removeAttribute("Edit_Linkedin");}%></span>
		              <script>
						function myFunction() {
						    var txt;
						    var link = prompt("Inserisci il Link di Linkedin", "");
						    if (link == null || link == "") 
						    {
						        txt = "Il campo non deve essere vuoto.";
						    } 
						    else 
						    {
						        txt = "Il Link \"" + link + "\" è stato salvato.";
						        window.location.href = "modifyLinkedin?link="+link;
						    }
						    
						    document.getElementById("link").innerHTML = txt;
						}
						</script>
						<br><br>
		     <% if(sessione_student.getLink_curriculum().length()!=0)
		         {%>
		       		<font size="5px"> <i class="fa fa-file-pdf-o"> <a href="<%=sessione_student.getLink_curriculum()%>" target="_blank"> Curriculum</a> </i> </font> 
		       		&ensp;&ensp;&ensp;<a href="modifyLinkedin?action=delete_link"> Elimina curriculum</a> 
			       	<form action="uploadfile" method="post" enctype="multipart/form-data">
			       	   <input type="hidden" name="action" value="insert_curriculum">
			       	   <input type="file" required accept="application/pdf" name="file" style="margin-bottom: 0.5em;">
			       	   <button type="submit" class="btn btn-primary">Modifica Curriculum</button>
			       	</form>
		       <%}
		         else
		         {%> 
		       		<form action="uploadfile" method="post" enctype="multipart/form-data">
			       	   <input type="hidden" name="action" value="insert_curriculum">
                       <input type="file" required accept="application/pdf" name="file" style="margin-bottom: 0.5em;">
			       	   <button type="submit" class="btn btn-primary">Inserisci Curriculum</button>
			       	</form>
		       <%}%><br>
		       <button onclick="EditData()" class="btn btn-success" style="background-color:grey;">Modifica Dati</button>
		       <script>
					function EditData() {
						window.location.href = "EditData.jsp";
						}
			  </script>
			  <br><br>
			  
			  <button onclick="MyTraining()" class="btn btn-success">I Miei Tirocini</button> <button onclick="OldTraining()" class="btn btn-warning">Tirocini Precedenti</button>
		       <script>
					function MyTraining() {
						window.location.href = "MyTraining.jsp";
						}
			  </script>
			  <script>
					function OldTraining() {
						window.location.href = "OldTraining.jsp";
						}
			  </script>
			  </div>
			   
			  <div class="col-sm-5">
			  <br><br><br>
			  <fieldset>
			  <legend>Domanda Tirocinio Interno</legend>
			  <form action="training_internal_external" method="post">
			   <input type="hidden" name="action" value="insert_training_internal"> 
			    <div class="form-group">
			      <label for="email">Email Professore*</label> 
			      <input type="text" class="form-control" maxlength="64" placeholder="Inserisci Email Professore" name="email">
			    </div>
			    <button type="submit" value="Send" class="btn btn-primary">Invia</button>
			  </form>
			  </fieldset>
			  <br>
			  </div>
			  
			  <div class="col-sm-5">
			  <br><br><br>
			  <fieldset>
			  <legend>Domanda Tirocinio Esterno</legend>
			  <form action="training_internal_external" method="post">
			   <input type="hidden" name="action" value="insert_training_external"> 
			    <div class="form-group">
			      <label for="email">Email Turor Aziendale*</label> 
			      <input type="text" class="form-control" maxlength="64" placeholder="Inserisci Email Tutor Aziendale" name="email">
			    </div>
			    <button type="submit" value="Send" class="btn btn-primary">Invia</button>
			  </form>
			  </fieldset>
			  </div>
		<%}
	}
	else
	{%>
		<script>  window.location.href = "index.jsp"; </script> 
	<%}%>
	
	</div>
</body>
</html>