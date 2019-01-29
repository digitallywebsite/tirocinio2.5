<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendale,gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendaleModel,gestionesegreteria.model.Segreteria"%>
    
<html>
<head>
<title>
	Professori Registrati
</title>

<%@ include file="../fragments/head.html" %>
<%@ include file="../fragments/nav.jsp" %>
</head>
<body>

<%  
	
	ProfessoreTutorAziendaleModel professoreModel;
	
	professoreModel = new ProfessoreTutorAziendaleModel();

	if(sessione_segreteria!=null)		
	{	 if(sessione_segreteria.getEmail().length()>0)
		 { 
			request.setAttribute("teachers_segreteria", professoreModel.doRetrieveAllTeachers(""));
		
			Collection<?> teachers = (Collection<?>) request.getAttribute("teachers_segreteria");

		  %>  
			<div class="container">
			<div class="col-lg-12">
               <div class="panel panel-default">
                   <div class="panel-heading">
                            <h2>Professori registrati (<%=teachers.size()%>)</h2>
                   </div>
                     <!-- /.panel-heading -->
                        <div class="panel-body">
                        <%
                        int i = 0;
                        
                        if (teachers != null && teachers.size() != 0) {
							Iterator<?> it_teachers = teachers.iterator();
							
							%>
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                           <%
							while (it_teachers.hasNext()) {
								ProfessoreTutorAziendale teacher = (ProfessoreTutorAziendale) it_teachers.next();
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
                                            <td><%=teacher.getNome()%> <%=teacher.getCognome()%></td>
                                            <td><%=teacher.getCompany()%></td>
                                            <td><%=teacher.getIndirizzo()%></td>
                                            <td><%=teacher.getTelefono()%></td>
                                            <td><%=teacher.getFax()%></td>
                                            <td><%=teacher.getEmail()%></td>
                                            <td><%=teacher.getCitta()%></td>
                                            <td><%=teacher.getSitoweb()%></td>
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
								Nessun Professore registrato trovato.
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