<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,gestioneutente.model.Tirocinio,gestioneutente.model.TirocinioModel,gestionesegreteria.model.Segreteria"%>
    
<html>
<head>
<title>
	Tirocini Convalidati
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
			request.setAttribute("questions_segreteria", tirocinioModel.doRetrieveAllConvalidate(""));
		
			Collection<?> questions = (Collection<?>) request.getAttribute("questions_segreteria");

		  %>  
			<div class="container">
			<div class="col-lg-12">
               <div class="panel panel-default">
                   <div class="panel-heading">
                            <h2>Tirocini Convalidati (<%=questions.size()%>)</h2>
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
								Nessun Tirocinio Convalidato trovato.
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