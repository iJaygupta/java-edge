<%-- 
    Document   : adminLogin
    Created on : 6 Oct, 2018, 10:06:43 PM
    Author     : Adeep My IT Solution Private Limited
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}/resources" scope="request" />
<%
  response.setHeader("Cache-Control","no-cache");
  response.setHeader("Cache-Control","no-store");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader ("Expires", 0);
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>ADMIS | LIS | LOGIN</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="${cp}/img/icon.png">
    <!-- Bootstrap Core CSS -->
    <link href="${cp}/admin/css/lib/bootstrap/bootstrap.min.css" rel="stylesheet">
    
    <link href="${cp}/admin/css/lib/toastr/toastr.min.css" rel="stylesheet">
    
    <!-- Custom CSS -->
    <link href="${cp}/admin/css/helper.css" rel="stylesheet">
    <link href="${cp}/admin/css/style.css" rel="stylesheet">
</head>

<body class="fix-header fix-sidebar">
    <!-- Preloader - style you can find in spinners.css -->
    <div class="preloader">
        <svg class="circular" viewBox="25 25 50 50">
			<circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10" /> </svg>
    </div>
    <!-- Main wrapper  -->
    <div id="main-wrapper">
        <div id='overlay'></div>
        <div class="unix-login">
            <div class="container-fluid">
                <div class="row justify-content-center">
                    <div class="col-lg-4">
                        <div class="login-content card">
                            <div class="login-form" id="loginForm">
                                <h4>Admin Login</h4>
                                <span style="color: #FC6180">${param.message}</span>
                                <hr>
                                <form action="/LaboratoryInformationSystem/admin/adminLogin" id="admin-login-form">
                                    <div class="form-group">
                                        <label>Username</label>
                                        <input name="username" type="text" class="form-control" placeholder="Username">
                                    </div>
                                    <div class="form-group">
                                        <label>Password</label>
                                        <input name="password" type="password" class="form-control" placeholder="Password">
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox"> Remember Me
                                        </label>
                                        <label class="pull-right">
                                            <a href="/admin-password-reset">Forgotten Password?</a>
                                        </label>
                                    </div>
                                    <button type="submit" id="submit-btn" class="btn btn-primary btn-flat m-b-30 m-t-30">Sign in</button>
                                </form>
                            </div>  
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--loading-->
        <div id="wait" style="display:none;width:69px;height:89px;border:1px solid black;position:absolute;top:50%;left:50%;padding:2px;"><img src='${cp}/img/lab-loading.gif' width="64" height="64" /><br>Loading..</div>
        <!--/.loading-->                            
    </div>
    <!-- End Wrapper -->
    <!-- All Jquery -->
    <script src="${cp}/admin/js/lib/jquery/jquery.min.js"></script>
    <!-- Bootstrap tether Core JavaScript -->
    <script src="${cp}/admin/js/lib/bootstrap/js/bootstrap.min.js"></script>
    <!-- slimscrollbar scrollbar JavaScript -->
    <script src="${cp}/admin/js/jquery.slimscroll.js"></script>
    <!--Menu sidebar -->
    <script src="${cp}/admin/js/sidebarmenu.js"></script>
    
    <!--Toastr-->
    <script src="${cp}/admin/js/lib/toastr/toastr.min.js"></script>
    <script src="${cp}/admin/js/lib/toastr/toastr.init.js"></script>
    
    <!--stickey kit -->
    <script src="${cp}/admin/js/lib/sticky-kit-master/dist/sticky-kit.min.js"></script>
    <!--Custom JavaScript -->
    <script src="${cp}/admin/js/custom.min.js"></script>
    
    <!--Login Ajax-->
    <script src="${cp}/ajax/admin/admin.login.js"></script>

    <!--Common Methods--> 
    <script src="${cp}/ajax/common.loading.method.js"></script>

</body>

</html>
