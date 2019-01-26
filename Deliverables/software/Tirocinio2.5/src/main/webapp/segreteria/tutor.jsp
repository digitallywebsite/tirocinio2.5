<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendale,gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendaleModel,gestionesegreteria.model.Segreteria"%>
    
<html>
<head>
<title>
	Tutor Aziendali Registrati
</title>

<%@ include file="../fragments/head.html" %>
<%@ include file="../fragments/nav.jsp" %>
</head>
<body>

<%  
	
	ProfessoreTutorAziendaleModel tutorModel;
	
	tutorModel = new ProfessoreTutorAziendaleModel();

	if(sessione_segreteria!=null)		
	{	 if(sessione_segreteria.getEmail().length()>0)
		 { 
			request.setAttribute("tutor_segreteria", tutorModel.doRetrieveAllTutor(""));
		
			Collection<?> tutor = (Collection<?>) request.getAttribute("tutor_segreteria");

		  %>  
			<div class="container">
			<div class="col-lg-12">
               <div class="panel panel-default">
                   <div class="panel-heading">
                            <h2>Tutor Aziendali registrati (<%=tutor.size()%>)</h2>
                   </div>
                     <!-- /.panel-heading -->
                        <div class="panel-body">
                        <%
                        int i = 0;
                        
                        if (tutor != null && tutor.size() != 0) {
							Iterator<?> it_tutor = tutor.iterator();
							
							%>
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                           <%
							while (it_tutor.hasNext()) {
								ProfessoreTutorAziendale tutors = (ProfessoreTutorAziendale) it_tutor.next();
								if(i==0)
								{
								%>
                                        <tr>
                                            <th>Nome Cognome</th>
                                            <th>Company</th>
                                            <th>Indirizzo</th>
                                            <th>Tel</th>
                                            <th>Fax</th>
                                            <th>Email</th>
                                            <th>Luogo</th>
                                            <th>SitoWeb</th>
                                        </tr>
                                <% i=1;
                                }%>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td><%=tutors.getNome()%> <%=tutors.getCognome()%></td>
                                            <td><%=tutors.getCompany()%></td>
                                            <td><%=tutors.getIndirizzo()%></td>
                                            <td><%=tutors.getTelefono()%></td>
                                            <td><%=tutors.getFax()%></td>
                                            <td><%=tutors.getEmail()%></td>
                                            <td><%=tutors.getCitta()%></td>
                                            <td><%=tutors.getSitoweb()%></td>
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
								Nessun Tutor Aziendale registrato trovato.
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