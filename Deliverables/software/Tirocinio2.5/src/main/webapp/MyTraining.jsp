<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,gestionestudente.model.Studente,gestioneutente.model.TirocinioModel,gestioneutente.model.Tirocinio"%>
    
<html>
<head>
<title>
	I Miei Tirocini
</title>

<%@ include file="fragments/head.html" %>
<%@ include file="fragments/nav.jsp" %>
</head>
<body>

<%  
	
	TirocinioModel TirocinioModel;
	
	TirocinioModel = new TirocinioModel();
	
	//Tirocinio tirocinio = (Tirocinio) request.getAttribute("order");

	if(sessione_student!=null)		
	{	 if(sessione_student.getEmail().length()>0)
		 { 
			request.setAttribute("trainings", TirocinioModel.myTraining("",sessione_student.getMatricola()));
		
			Collection<?> trainings = (Collection<?>) request.getAttribute("trainings");
		  %>  
			<div class="container">
			<h2>I Miei Tirocini (<%=sessione_student.getNome()%> <%=sessione_student.getCognome()%>)</h2>
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
                            Tirocini di <%=sessione_student.getNome()%> <%=sessione_student.getCognome()%>
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
                                            <th>Tipo Professore/Tutor Aziendale</th>
                                            <th>Stato</th>
                                            <th>Azione</th>
                                        </tr>
                                <% i=1;
                                }%>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td><a href="ShowProfile.jsp?id=<%=tirocinio.getTutor_username()%>"> <%=tirocinio.getNomeCognome()%> </a></td>
                                            <td><%=tirocinio.getTipoTutorProfessore()%></td>
                                            <td><%=tirocinio.getStato()%></td>
                                            <td><% if(tirocinio.getStato().equalsIgnoreCase("In Attesa")) 
                                            	   {%> <a href="tirociniocontrol?action=delete_training_student&id=<%=tirocinio.getId()%>">Cancella Domanda</a> <%} 
                                            
                                            	   if(tirocinio.getStato().equalsIgnoreCase("Accettata")) 
		                                     	   {%> <a href="tirociniocontrol?action=confirm_student_training_student&id=<%=tirocinio.getId()%>">Conferma Domanda</a> --  <a href="tirociniocontrol?action=reject_student_training_student&id=<%=tirocinio.getId()%>">Rifiuta Domanda</a><%}
                                            	   
		                                     	   if(tirocinio.getStato().equalsIgnoreCase("Confermata") || tirocinio.getStato().equalsIgnoreCase("Completato")) 
		                                     	   {%> <a href="TrendTrainingStudent.jsp?id=<%=tirocinio.getId()%>">Andamento Tirocinio</a><%}
		                                     	   
		                                     	   if(tirocinio.getStato().equalsIgnoreCase("Completato")) 
		                                     	   {%> -- <a href="/Tirocinio2.5/Users/Students/ModuloRiconoscimento_attivita_lavorativa.pdf">Modulo di Riconoscimento</a><%}%>
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
								Nessun Tirocinio trovato.
								<a href="PersonalArea.jsp">Area Riservata</a>
							<%}%>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
             
                <div class="panel panel-default">
                   <div class="panel-heading">
                            Tirocini Precedenti di <%=sessione_student.getNome()%> <%=sessione_student.getCognome()%>
                   </div>
                     <!-- /.panel-heading -->
                		<div class="panel-body">
                        <%
                        request.setAttribute("trainings", TirocinioModel.myTrainingOld("",sessione_student.getMatricola()));
                        trainings = (Collection<?>) request.getAttribute("trainings");
                        i = 0;
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
                                            <th>Azienda</th>
                                            <th>Stato</th>
                                            <th>Ore Svolte</th>
                                            <th>Compiti Svolti</th>
                                            <th>Documentazione</th>
                                        </tr>
                                <% i=1;
                                }%>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td><%=tirocinio.getNomeCognome()%></td>
                                            <td><%=tirocinio.getCompany()%></td>
                                            <td><%=tirocinio.getStato()%></td>
                                            <td><%=tirocinio.getOreSvolte()%></td>
                                            <td><div style="width:20em; word-wrap:break-word;"><%=tirocinio.getCompitiSvolti()%></div></td>
                                            <td><a href="<%=tirocinio.getDocumentLink()%>" target="_blank"> Documento Caricato</a></td>
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
								Nessun Tirocinio Precedente trovato.
								<a href="PersonalArea.jsp">Area Riservata</a>
							<%}%>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->		
			</div>
<% 		}
   }
   else
   {%>
		<script>  window.location.href = "index.jsp"; </script> 
 <%}%>
	 	
</body>
</html>