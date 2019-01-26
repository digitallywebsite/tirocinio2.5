<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logout</title>
</head>
<body>

<% session.removeAttribute("student"); session.removeAttribute("teacher"); session.removeAttribute("tutor"); session.removeAttribute("segreteria");%>

<script>
        	        top.location.href = "index.jsp";
</script>

</body>
</html>