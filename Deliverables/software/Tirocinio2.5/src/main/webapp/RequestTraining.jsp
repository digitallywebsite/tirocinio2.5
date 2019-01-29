<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendale,gestioneutente.model.TirocinioModel,gestioneutente.model.Tirocinio"%>
    
<html>
<head>
<title>
	Richieste di Tirocini
</title>

<%@ include file="fragments/head.html" %>
<%@ include file="fragments/nav.jsp" %>
</head>
<body>

<%  
	
	
	TirocinioModel TirocinioModel;
	
	TirocinioModel = new TirocinioModel();
	
	//Tirocinio tirocinio = (Tirocinio) request.getAttribute("order");


	if(sessione_teacher!=null)		
	{	 if(sessione_teacher.getEmail().length()>0)
		 { 
			request.setAttribute("trainings", TirocinioModel.requestTraining("",sessione_teacher.getUsername()));
		
			Collection<?> trainings = (Collection<?>) request.getAttribute("trainings");
		  %>  
			<div class="container">
			<h2>Richieste di Tirocini (<%=sessione_teacher.getNome()%> <%=sessione_teacher.getCognome()%>)</h2>
			<%
			if(request.getAttribute("message_success_training")!=null)
			{
				%> 
				<div class="alert alert-success">
			      <strong> <%=request.getAttribute("message_success_training") %> </strong>
				</div>
			  <%
			}
			
			if(request.getAttribute("message_fault_training")!=null)
			{
				%> 
				<div class="alert alert-danger">
			      <strong> <%=request.getAttribute("message_fault_training") %> </strong>
				</div>
			  <%
			}
			%>
			<div class="col-lg-12">
               <div class="panel panel-default">
                   <div class="panel-heading">
                            Richieste Tirocini di <%=sessione_teacher.getNome()%> <%=sessione_teacher.getCognome()%>
                   </div>
                     <!-- /.panel-heading -->
                        <div class="panel-body">
                        <%
                        int i = 0;
                        if (trainings != null && trainings.size() != 0) {
							Iterator<?> it_trainings = trainings.iterator();
							
							%>
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                           <%
							while (it_trainings.hasNext()) {
								Tirocinio tirocinio = (Tirocinio) it_trainings.next();
								if(i==0)
								{
								%>
                                        <tr>
                                            <th>Nome Cognome</th>
                                            <th>Username</th>
                                            <th>Email</th>
                                            <th>Matricola</th>
                                            <th>Stato</th>
                                            <th>Azione</th>
                                        </tr>
                                <% i=1;
                                }%>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td><%=tirocinio.getNomeCognomeStudent()%></td>
                                            <td><%=tirocinio.getStudent_usename()%></td>
                                            <td><%=tirocinio.getStudent_email()%></td>
                                            <td><%=tirocinio.getMatricola_studente()%></td>
                                            <td><%=tirocinio.getStato()%></td> 
                                            <td><% if(tirocinio.getStato().equalsIgnoreCase("In Attesa")) 
                                            	   {%> <a href="tirociniocontrol?action=accept_training_student&id=<%=tirocinio.getId()%>">Accetta Domanda</a> --  <a href="tirociniocontrol?action=reject_training_student&id=<%=tirocinio.getId()%>">Rifiuta Domanda</a> <%} 
                                            	   
		                                     	   if(tirocinio.getStato().equalsIgnoreCase("Confermata") || tirocinio.getStato().equalsIgnoreCase("Completato")) 
		                                     	   {%> <a href="TrendTraining.jsp?id=<%=tirocinio.getId()%>">Andamento Tirocinio</a><%}%>
                                            </td>
                                        </tr>
                             <%
								}%>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.table-responsive -->
                            <%
							}
							else
							{%>
								Nessun Domanda di Tirocinio Trovata.
								<a href="PersonalAreaTutorProfessore.jsp">Area Riservata</a>
							<%}%>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-6 -->		
			</div>
<% 		}
   }
   else
   {
	   if(sessione_tutor!=null)		
		{	 if(sessione_tutor.getEmail().length()>0)
			 {
				request.setAttribute("trainings", TirocinioModel.requestTraining("",sessione_tutor.getUsername()));
				
				Collection<?> trainings = (Collection<?>) request.getAttribute("trainings");
			  %>  
				<div class="container">
				<h2>Richieste di Tirocini (<%=sessione_tutor.getNome()%> <%=sessione_tutor.getCognome()%>)</h2>
				<%
				if(request.getAttribute("message_success_training")!=null)
				{
					%> 
					<div class="alert alert-success">
				      <strong> <%=request.getAttribute("message_success_training") %> </strong>
					</div>
				  <%
				}
				
				if(request.getAttribute("message_fault_training")!=null)
				{
					%> 
					<div class="alert alert-danger">
				      <strong> <%=request.getAttribute("message_fault_training") %> </strong>
					</div>
				  <%
				}
				%>
				<div class="col-lg-12">
	               <div class="panel panel-default">
	                   <div class="panel-heading">
	                            Richieste Tirocini di <%=sessione_tutor.getNome()%> <%=sessione_tutor.getCognome()%>
	                   </div>
	                     <!-- /.panel-heading -->
	                        <div class="panel-body">
	                        <%
	                        int i = 0;
	                        if (trainings != null && trainings.size() != 0) {
								Iterator<?> it_trainings = trainings.iterator();
								
								%>
	                            <div class="table-responsive">
	                                <table class="table table-striped">
	                                    <thead>
	                           <%
								while (it_trainings.hasNext()) {
									Tirocinio tirocinio = (Tirocinio) it_trainings.next();
									if(i==0)
									{
									%>
	                                        <tr>
	                                            <th>Nome Cognome</th>
	                                            <th>Username</th>
	                                            <th>Email</th>
	                                            <th>Matricola</th>
	                                            <th>Stato</th>
	                                            <th>Azione</th>
	                                        </tr>
	                                <% i=1;
	                                }%>
	                                    </thead>
	                                    <tbody>
	                                        <tr>
	                                            <td><%=tirocinio.getNomeCognomeStudent()%></td>
	                                            <td><%=tirocinio.getStudent_usename()%></td>
	                                            <td><%=tirocinio.getStudent_email()%></td>
	                                            <td><%=tirocinio.getMatricola_studente()%></td>
	                                            <td><%=tirocinio.getStato()%></td> 
	                                            <td><% if(tirocinio.getStato().equalsIgnoreCase("In Attesa")) 
	                                            	   {%> <a href="tirociniocontrol?action=accept_training_student&id=<%=tirocinio.getId()%>">Accetta Domanda</a> --  <a href="tirociniocontrol?action=reject_training_student&id=<%=tirocinio.getId()%>">Rifiuta Domanda</a> <%} 
	                                            	   
			                                     	   if(tirocinio.getStato().equalsIgnoreCase("Confermata") || tirocinio.getStato().equalsIgnoreCase("Completato")) 
			                                     	   {%> <a href="TrendTraining.jsp?id=<%=tirocinio.getId()%>">Andamento Tirocinio</a><%}%>
	                                            </td>
	                                        </tr>
	                             <%
									}%>
	                                    </tbody>
	                                </table>
	                            </div>
	                            <!-- /.table-responsive -->
	                            <%
								}else
								{%>
									Nessun Domanda di Tirocinio Trovata.
									<a href="PersonalAreaTutorProfessore.jsp">Area Riservata</a>
								<%}%>
	                        </div>
	                        <!-- /.panel-body -->
	                    </div>
	                    <!-- /.panel -->
	                </div>
	                <!-- /.col-lg-6 -->		
				</div>				
	<% 		}
	   }
	   else
	   {%>
			<script>  window.location.href = "index.jsp"; </script> 
	 <%}%>
 <%}%>
	 	
</body>
</html>