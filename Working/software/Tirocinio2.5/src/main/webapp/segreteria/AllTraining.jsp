<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,gestioneutente.model.Tirocinio,gestioneutente.model.TirocinioModel,gestionesegreteria.model.Segreteria"%>
    
<html>
<head>
<title>
	Tutti i tirocini (Esterni/Interni/Precedenti)
</title>

<%@ include file="../fragments/head.html" %>
<%@ include file="../fragments/nav.jsp" %>
</head>
<body>

<%  
	
	TirocinioModel tirocinioModel;
	
    tirocinioModel = new TirocinioModel();
	
	//Tirocinio tirocinio = (Tirocinio) request.getAttribute("order");

	if(sessione_segreteria!=null)		
	{	 if(sessione_segreteria.getEmail().length()>0)
		 { 
			request.setAttribute("questions_segreteria", tirocinioModel.doRetrieveAll(""));
		
			Collection<?> trainings = (Collection<?>) request.getAttribute("questions_segreteria");
		  %>  
			<div class="container">
			<h2>Tutti i tirocini (Esterni/Interni/Precedenti)</h2>
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
                            Tirocini (Esterni/Interni) (<%=trainings.size()%>)
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
                                		<tr>
                                			<th>Studente</th>
                                			<th></th>
                                			<th></th>
                                			<th></th>
                                			<th></th>
                                        	<th>Professore/Tutor Aziendale</th>
                                        	<th></th>
                                        </tr>
                                	</thead>
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
                                            <th>Nome Cognome</th>
                                            <th>Tipo</th>
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
                                            <td><a href="../ShowProfile.jsp?id=<%=tirocinio.getTutor_username()%>"> <%=tirocinio.getNomeCognome()%> </a></td>
                                        	<td><%=tirocinio.getTipoTutorProfessore()%></td>
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
								<a href="../PersonalAreaSegretary.jsp">Area Riservata</a>
							<%}%>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
             
                <div class="panel panel-default">
                   <div class="panel-heading">
                            Tirocini (Precedenti) (<%=trainings.size()%>)
                   </div>
                     <!-- /.panel-heading -->
                		<div class="panel-body">
                        <%
                        request.setAttribute("questions_segreteria", tirocinioModel.doRetrieveAllOld(""));
                        trainings = (Collection<?>) request.getAttribute("questions_segreteria");
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
								<a href="../PersonalAreaSegretary.jsp">Area Riservata</a>
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
		<script>  window.location.href = "../index.jsp"; </script> 
 <%}%>
	 	
</body>
</html>