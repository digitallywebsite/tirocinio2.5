<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,gestionestudente.model.Studente,gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendale,gestionesegreteria.model.Segreteria"%>
	
<%
 

Studente sessione_student = (Studente) request.getSession().getAttribute("student");
ProfessoreTutorAziendale sessione_teacher = (ProfessoreTutorAziendale) request.getSession().getAttribute("teacher");
ProfessoreTutorAziendale sessione_tutor = (ProfessoreTutorAziendale) request.getSession().getAttribute("tutor");

Segreteria sessione_segreteria = (Segreteria) request.getSession().getAttribute("segreteria");

boolean controllo = false;

if(sessione_student!=null)		
	 if(sessione_student.getEmail().length()>0)
	 {
		 controllo = true;
	 }

if(sessione_teacher!=null)		
	 if(sessione_teacher.getEmail().length()>0)
	 {
		 controllo = true;
	 }

if(sessione_tutor!=null)		
	 if(sessione_tutor.getEmail().length()>0)
	 {
		 controllo = true; 
	 }

if(sessione_segreteria!=null)		
	 if(sessione_segreteria.getEmail().length()>0)
	 {
		 controllo = true;
	 }
%>	
	<div class="container-fluid">
		<!-- Second navbar for sign in -->
		<nav class="navbar navbar-default navbar-fixed-top">
		  <div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
			  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse-2">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar" style="background-color:#0000ff;"></span>
				<span class="icon-bar" style="background-color:#0000ff;"></span>
				<span class="icon-bar"style="background-color:#0000ff;"></span>
			  </button>
			  <%
				  if(request.getRequestURI().length()>25 && request.getRequestURI().substring(0, 25).equalsIgnoreCase("/Tirocinio2.5/segreteria/"))
				  { if(request.getAttribute("message_success_training")!=null || request.getAttribute("message_fault_training")!=null)
				    {%>
					  <a class="navbar-brand" href="index.jsp"> <img src="images/logo.png" style="width:100%;width:15em;height:5em;" alt=""> </a>
				  <%}
					else
					{%>
					  <a class="navbar-brand" href="../index.jsp"> <img src="../images/logo.png" style="width:100%;width:15em;height:5em;" alt=""> </a>
				  <%}
				  }
				  else
				  {%>
					  <a class="navbar-brand" href="index.jsp"> <img src="images/logo.png" style="width:100%;width:15em;height:5em;" alt=""> </a>	
				  <%}%>
			  <span class="navbar-brand">&ensp;</span><br>
			  <span class="navbar-brand">&ensp;</span><br>
			  <span class="navbar-brand">&ensp;</span><br>
			  <span class="navbar-brand">&ensp;</span><br>
			  </div>
		
			<!-- Collect the nav links, forms, and other content for toggling -->
			<br>
			<div class="collapse navbar-collapse" id="navbar-collapse-2">
			  <ul class="nav navbar-nav navbar-right">
				
				 <%
				 if(request.getRequestURI().length()>25 && request.getRequestURI().substring(0, 25).equalsIgnoreCase("/Tirocinio2.5/segreteria/"))
				  { if(request.getAttribute("message_success_training")!=null || request.getAttribute("message_fault_training")!=null)
				    {%>
					  <li> <a href="index.jsp"> <span class="glyphicon glyphicon-home"></span> Home</a></li>
				  <%}
					else
					{%>
					  <li> <a href="../index.jsp"> <span class="glyphicon glyphicon-home"></span> Home</a></li>				  
				  <%}
				  }
				 else
				  {%>
					  <li> <a href="index.jsp"> <span class="glyphicon glyphicon-home"></span> Home</a></li>
				  <%}
				if(!controllo)
				{%>
    				<li><a href="Login.jsp"> <span class="glyphicon glyphicon-log-in"></span> Login</a></li>
    				<li><a href="Signup.jsp"> <span class="glyphicon glyphicon-user"></span> Registrati</a></li>
    			<%
    			}
    			else
    			{
    				if(sessione_student!=null)		
    					 if(sessione_student.getEmail().length()>0)
		    			    {%>
		    			        <li><a href="PersonalArea.jsp"> <span class="glyphicon glyphicon-user"></span> Area Utente (<%=sessione_student.getNome()%> <%=sessione_student.getCognome()%>)</a></li>
						    <%
						    }
    				
    				if(sessione_teacher!=null)		
   					 if(sessione_teacher.getEmail().length()>0)
		    			    {%>
		    			        <li><a href="PersonalAreaTutorProfessore.jsp"> <span class="glyphicon glyphicon-user"></span> Area Utente (<%=sessione_teacher.getNome()%> <%=sessione_teacher.getCognome()%>)</a></li>
						    <%
						    }
    				
    				if(sessione_tutor!=null)		
   					 if(sessione_tutor.getEmail().length()>0)
		    			    {%>
		    			        <li><a href="PersonalAreaTutorProfessore.jsp"> <span class="glyphicon glyphicon-user"></span> Area Utente (<%=sessione_tutor.getNome()%> <%=sessione_tutor.getCognome()%>)</a></li>
						    <%
						    }
    				
    				if(sessione_segreteria!=null)		
   					 if(sessione_segreteria.getEmail().length()>0)
		    			    {
		   					    if(request.getRequestURI().length()>25 && request.getRequestURI().substring(0, 25).equalsIgnoreCase("/Tirocinio2.5/segreteria/"))
		   					    { if(request.getAttribute("message_success_training")!=null || request.getAttribute("message_fault_training")!=null)
		   					      {%>
		   						    <li><a href="PersonalAreaSegretary.jsp"> <span class="glyphicon glyphicon-user"></span> Area Utente (<%=sessione_segreteria.getUsername()%>)</a></li>
		   					    <%}
		   						  else
		   						  {%>
		   						    <li><a href="../PersonalAreaSegretary.jsp"> <span class="glyphicon glyphicon-user"></span> Area Utente (<%=sessione_segreteria.getUsername()%>)</a></li>
		   					    <%}
		   					    }
								else
								{%>
								  <li><a href="PersonalAreaSegretary.jsp"> <span class="glyphicon glyphicon-user"></span> Area Utente (<%=sessione_segreteria.getUsername()%>)</a></li>
							  <%}%>
						    <%
						    }
    			}
				if(controllo)
			   	  { 
					if(request.getRequestURI().length()>25 && request.getRequestURI().substring(0, 25).equalsIgnoreCase("/Tirocinio2.5/segreteria/"))
					{ if(request.getAttribute("message_success_training")!=null || request.getAttribute("message_fault_training")!=null)
					  {%>
						<li><a href="Logout.jsp"> <span class="glyphicon glyphicon-log-out"></span> Esci</a></li>
					<%}
					  else
					  {%>
						<li><a href="../Logout.jsp"> <span class="glyphicon glyphicon-log-out"></span> Esci</a></li>
					<%}
					}
					else
					{%>
					  <li><a href="Logout.jsp"> <span class="glyphicon glyphicon-log-out"></span> Esci</a></li>
				  <%}%>
				<%}%>
				
			  </ul>			  
			</div><!-- /.navbar-collapse -->
		  </div><!-- /.container -->
		</nav><!-- /.navbar -->
	</div><!-- /.container-fluid -->
<br><br><br><br><br><br>