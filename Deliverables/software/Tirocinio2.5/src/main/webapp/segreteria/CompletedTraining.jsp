<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,gestioneutente.model.Tirocinio,gestioneutente.model.TirocinioModel,gestionesegreteria.model.Segreteria"%>
    
<html>
<head>
<title>
	Tirocini Completati
</title>

<%@ include file="../fragments/head.html" %>
<%@ include file="../fragments/nav.jsp" %>
</head>
<body>

<%  
	
	TirocinioModel tirocinioModel;
	
	tirocinioModel = new TirocinioModel();

	if(sessione_segreteria!=null)		
	{	 if(sessione_segreteria.getEmail().length()>0)
		 { 
			request.setAttribute("questions_segreteria", tirocinioModel.doRetrieveAllCompleted(""));
		
			Collection<?> questions = (Collection<?>) request.getAttribute("questions_segreteria");

		  %>  
			<div class="container">
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
                            <h2>Tirocini Completati (<%=questions.size()%>)</h2>
                   </div>
                     <!-- /.panel-heading -->
                        <div class="panel-body">
                        <%
                        int i = 0;
                        
                        if (questions != null && questions.size() != 0) {
							Iterator<?> it_questions = questions.iterator();
							
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
							while (it_questions.hasNext()) {
								Tirocinio tirocinio = (Tirocinio) it_questions.next();
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
                                            <td><a href="../ShowProfile.jsp?id=<%=tirocinio.getTutor_username()%>"> <%=tirocinio.getNomeCognome()%> </a></td>
                                        	<td><%=tirocinio.getTipoTutorProfessore()%></td>
                                        	<td><% if(tirocinio.getStato().equalsIgnoreCase("Completato")) 
                                            	   {%> <a href="../tirociniocontrol?action=convalidate_training_student&id=<%=tirocinio.getId()%>" onclick="return confirmConvalidateTraining()">Convalida</a><%}%>
                                            		
                                            		<script>
															function confirmConvalidateTraining() {
																var b = confirm("Sei sicuro di voler convalidare questo tirocinio");
						                                    	if(b) 
						                                      		{
						                                      			return true;
						                                      		}
						                                    	else
						                                      		  return false;

																}
													  </script>
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
								Nessun Tirocinio Completato trovato.
								<a href="../PersonalAreaSegretary.jsp">Area Riservata</a>
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
		<script>  window.location.href = "../index.jsp"; </script> 
 <%}%>
	 	
</body>
</html>