<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,gestioneprofessoretutoraziendale.model.ProfessoreTutorAziendale,gestioneutente.model.AndamentoModel,gestioneutente.model.Andamento"%>
    
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
	String id = request.getParameter("id");
	int id_int = 0;
	
	if(id != null)
		id_int = Integer.parseInt(id);
	
	
	AndamentoModel andamentoModel;
	
	andamentoModel = new AndamentoModel();
	
	//Tirocinio tirocinio = (Tirocinio) request.getAttribute("order");
	
	boolean control = false;
					
	if(sessione_teacher!=null)		
		 if(sessione_teacher.getEmail().length()>0)
		 {
			 control = true;
		 }
			
	if(sessione_tutor!=null)		
		 if(sessione_tutor.getEmail().length()>0)
		 {
			 control = true;
		 }
					
	if(control)		
	{	  
			Andamento andamento = (Andamento ) andamentoModel.doRetrieveByKey(id_int);
			
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
			if(request.getAttribute("Data_not_valid")!=null)
			{
				%> 
				<div class="alert alert-danger">
			      <strong> <%=request.getAttribute("Data_not_valid") %> </strong>
				</div>
			  <%
			}
			if(request.getAttribute("oraInizio_not_valid")!=null)
			{
				%> 
				<div class="alert alert-danger">
			      <strong> <%=request.getAttribute("oraInizio_not_valid") %> </strong>
				</div>
			  <%
			}
			
			if(request.getAttribute("oraFine_not_valid")!=null)
			{
				%> 
				<div class="alert alert-danger">
			      <strong> <%=request.getAttribute("oraFine_not_valid") %> </strong>
				</div>
			  <%
			}
			%>
				<h2>&ensp;Modifica Ore di lavoro di (<%=andamento.getNomeCognomeStudent()%>) </h2>
                <div class="col-sm-4">
	                <form action="timeWork" method="post">
					   <input type="hidden" name="action" value="modify_time_work">
					   <input type="hidden" name="id" value="<%=andamento.getId()%>"> 
					    <div class="form-group">
					      <label for="data">Data *</label> 
					      <input type="text" class="form-control" required maxlength="10" value="<%=andamento.getDataT()%>" name="data">
					    </div>
					    <div class="form-group">
					      <label for="ora_inizio">Ora Inizio *</label> 
					      <input type="text" class="form-control" required maxlength="5" value="<%=andamento.getOra_inizio().subSequence(0, 5)%>" name="ora_inizio">
					    </div>
					    <div class="form-group">
					      <label for="ora_fine">Ora Fine *</label> 
					      <input type="text" class="form-control" required maxlength="5" value="<%=andamento.getOra_fine().subSequence(0, 5)%>" name="ora_fine">
					    </div>
					    * Tutti i campi sono obbligatori &ensp;&ensp;&ensp;
					    <button type="submit" value="Send" class="btn btn-primary">Modifica Ore</button>
				  	</form>
			  	</div>
                <!-- /.col-lg-6 -->		
			</div>
<%
   }
   else
   {%>
		<script>  window.location.href = "index.jsp"; </script> 
 <%}%>
	 	
</body>
</html>