<%-- 
    Document   : admin-pin
    Created on : 26 Jan, 2020, 2:24:13 PM
    Author     : dell
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}/resources" scope="request" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="firebaseui-auth-container"></div>
        
        <%@include file="../include/firebase.jsp" %>
    </body>
</html>
