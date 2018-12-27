<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,gestionestudente.model.Studente,gestionestudente.model.StudenteModel,gestionesegreteria.model.Segreteria"%>
    
<html>
<head>
<title>
	Studenti Registrati
</title>

<%@ include file="../fragments/head.html" %>
<%@ include file="../fragments/nav.jsp" %>
</head>
<body>

<%  

	StudenteModel studenteModel;
	
	studenteModel = new StudenteModel();

	if(sessione_segreteria!=null)		
	{	 if(sessione_segreteria.getEmail().length()>0)
		 { 
			request.setAttribute("students_segreteria", studenteModel.doRetrieveAll(""));
		
			Collection<?> students = (Collection<?>) request.getAttribute("students_segreteria");

		  %>  
			<div class="container">
			<div class="col-lg-12">
               <div class="panel panel-default">
                   <div class="panel-heading">
                            <h2>Studenti registrati (<%=students.size()%>)</h2>
                   </div>
                     <!-- /.panel-heading -->
                        <div class="panel-body">
                        <%
                        int i = 0;
                        
                        if (students != null && students.size() != 0) {
							Iterator<?> it_students = students.iterator();
							
							%>
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                           <%
							while (it_students.hasNext()) {
								Studente stud = (Studente) it_students.next();
								if(i==0)
								{
								%>
                                        <tr>
                                            <th>Nome Cognome</th>
                                            <th>Username</th>
                                            <th>Email</th>
                                            <th>Matricola</th>
                                        </tr>
                                <% i=1;
                                }%>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td><%=stud.getNome()%> <%=stud.getCognome()%></td>
                                            <td><%=stud.getUsername()%></td>
                                            <td><%=stud.getEmail()%></td>
                                            <td><%=stud.getMatricola()%></td>
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
								Nessun Studente registrato trovato.
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