<%-- 
    Document   : login
    Created on : 26 Jan, 2020, 12:58:52 PM
    Author     : dell
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}/resources" scope="request" />
<%@page import="co.admis.config.ServerConfiguration" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<title>${ServerConfiguration.COMPANY_SHORT_NAME} | Login</title>
		
		<!-- Favicon -->
		<link rel="shortcut icon" href="${cp}/img/icon.png">
                <link rel="icon" href="${cp}/img/icon.png" type="image/x-icon">
		<!-- vector map CSS -->
		<link href="${cp}/admin/vendors/bower_components/jquery-toast-plugin/dist/jquery.toast.min.css" rel="stylesheet" type="text/css"><!-- switchery CSS -->
		<link href="${cp}/admin/vendors/bower_components/switchery/dist/switchery.min.css" rel="stylesheet" type="text/css"/>
		<!-- Custom CSS -->
		<link href="${cp}/admin/dist/css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<!--Preloader-->
		<div class="preloader-it">
			<div class="la-anim-1"></div>
		</div>
		<!--/Preloader-->
		
		<div class="wrapper pa-0">
			<header class="sp-header">
				<div class="sp-logo-wrap pull-left">
					<a href="index.html">
						<img class="brand-img mr-10" src="${cp}/admin/dist/img/logo.png" alt="brand"/>
						<span class="brand-text">${ServerConfiguration.COMPANY_NAME}</span>
					</a>
				</div>
				<div class="clearfix"></div>
			</header>
			
			<!-- Main Content -->
			<div class="page-wrapper pa-0 ma-0 auth-page">
                            <div class="container-fluid">
                                <!-- Row -->
                                <div class="table-struct full-width full-height">
                                    <div class="table-cell vertical-align-middle auth-form-wrap">
                                        <div class="auth-form  ml-auto mr-auto no-float">
                                            <div class="row">
                                                <div class="col-sm-12 col-xs-12">
                                                    <div class="mb-30">
                                                            <h3 class="text-center txt-dark mb-10">Sign in to ${ServerConfiguration.COMPANY_SHORT_NAME}</h3>
                                                            <h6 class="text-center nonecase-font txt-grey">Enter your details below</h6>
                                                    </div>	
                                                    <div class="form-wrap">
                                                        <form method="post" action="${ServerConfiguration.LOCAL_URL}/master/masterLogin" id="master-login-form">
                                                            <div class="form-group">
                                                                <label>Username</label>
                                                                <input name="username" type="text" class="form-control" placeholder="Username">
                                                            </div>
                                                            <div class="form-group">
                                                                <label>Password</label>
                                                                <input name="password" type="password" class="form-control" placeholder="Password">
                                                            </div>
                                                            <button type="submit" class="btn btn-primary btn-flat m-b-30 m-t-30">Sign in</button>

                                                        </form>
                                                    </div>
                                                </div>	
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- /Row -->	
                            </div>
			</div>
			<!-- /Main Content -->
		</div>
		<!-- /#wrapper -->
		
		<!-- JavaScript -->
		
		<!-- jQuery -->
		<script src="${cp}/admin/vendors/bower_components/jquery/dist/jquery.min.js"></script>
		
		<!-- Bootstrap Core JavaScript -->
		<script src="${cp}/admin/vendors/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
                <script src="${cp}/admin/vendors/bower_components/jquery-toast-plugin/dist/jquery.toast.min.js"></script>
		
		<!-- Slimscroll JavaScript -->
		<script src="${cp}/admin/dist/js/jquery.slimscroll.js"></script>
		
		<!-- Init JavaScript -->
                <script src="${cp}/admin/dist/js/toast-data.js"></script>
		<script src="${cp}/admin/dist/js/init.js"></script>
                <!--Login Ajax-->
                <script src="${cp}/ajax/master/master.login.js"></script>

                <!--Common Methods--> 
                <script src="${cp}/ajax/common.loading.method.js"></script>
	</body>
        
</html>
