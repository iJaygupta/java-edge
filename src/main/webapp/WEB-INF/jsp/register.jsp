<%-- 
    Document   : register
    Created on : 16 Nov, 2018, 8:55:11 PM
    Author     : Adeep My IT Solution Private Limited
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}/resources" scope="request" />
<%@page import="co.admis.config.ServerConfiguration" %>
<%
  response.setHeader("Cache-Control","no-cache");
  response.setHeader("Cache-Control","no-store");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader ("Expires", 0);
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<title>${ServerConfiguration.COMPANY_SHORT_NAME} | Register</title>
                <!-- Favicon -->
                <link rel="shortcut icon" href="${cp}/img/icon.png">
                <link rel="icon" href="${cp}/img/icon.png" type="image/x-icon">
                <link href="${cp}/admin/vendors/bower_components/jquery-toast-plugin/dist/jquery.toast.min.css" rel="stylesheet" type="text/css"><!-- switchery CSS -->
                <!-- select2 CSS -->
		<link href="${cp}/admin/vendors/bower_components/select2/dist/css/select2.min.css" rel="stylesheet" type="text/css"/>
		
		<!-- bootstrap-select CSS -->
		<link href="${cp}/admin/vendors/bower_components/bootstrap-select/dist/css/bootstrap-select.min.css" rel="stylesheet" type="text/css"/>
		
		<!-- multi-select CSS -->
		<link href="${cp}/admin/vendors/bower_components/multiselect/css/multi-select.css" rel="stylesheet" type="text/css"/><!-- Custom CSS -->
		<link href="${cp}/admin/dist/css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<!--Preloader-->
		<div class="preloader-it">
			<div class="la-anim-1"></div>
		</div>
		<!--/Preloader-->
                <header class="sp-header">
                    <a style="float: right;" class="btn btn-danger" href="${ServerConfiguration.LOCAL_URL}/login">Login</a>     
                        <div class="sp-logo-wrap pull-left">
                                <a href="index.html">
                                        <img class="brand-img mr-10" src="${cp}/admin/dist/img/logo.png" alt="brand"/>
                                        <span class="brand-text">${ServerConfiguration.COMPANY_SHORT_NAME}</span>
                                </a>
                        </div>
                        <div class="clearfix"></div>
                </header>
		<div class="page-wrapper pa-0 ma-0 auth-page">
                    <!-- Main Content -->
                        <div class="container-fluid">
                            <!-- Title -->
                            <div class="row heading-bg">
                                    
                                    <!-- /Breadcrumb -->
                            </div>
                            <!-- /Title -->
                            <!-- Row -->
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="panel panel-default card-view">
                                        <div class="panel-wrapper collapse in">
                                            <div class="panel-body">
                                                <div class="form-wrap">
                                                    <form id="register-user-form" action="${ServerConfiguration.LOCAL_URL}/registerCustomer">
                                                        <h6 class="txt-dark capitalize-font"><i class="zmdi zmdi-calendar-note mr-10"></i>Note: We will verify your details and contact you accordingly
                                                            <div id="alert-message-set"></div>
                                                        </h6>
                                                        <hr class="light-grey-hr"/>
                                                        <div class="row">
                                                            <div class="col-sm-4">
                                                                    <div class="form-group">
                                                                        <label style="color: white; margin: 5px;">Company Name</label>
                                                                        <input type="text" class="form-control" name="companyName" placeholder="Enter company name*" required>
                                                                    </div>
                                                            </div>
                                                            <div class="col-sm-4">
                                                                    <div class="form-group">
                                                                        <label style="color: white; margin: 5px;">Monthly expected number of orders</label>
                                                                        <input type="number" class="form-control" name="orderCountPerMonth" placeholder="Enter expected orders number per month*" required>
                                                                    </div>
                                                            </div>
                                                            <div class="col-sm-4">
                                                                    <div class="form-group">
                                                                        <label style="color: white; margin: 5px;">Number of products</label>
                                                                        <input type="number" class="form-control" name="productCount" placeholder="Enter expected products numcber*" required>
                                                                    </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-sm-4">
                                                                    <div class="form-group">
                                                                            <label style="color: white; margin: 5px;">Full Name</label>
                                                                            <input type="text" class="form-control" name="name" placeholder="Enter your name*" required>
                                                                    </div>
                                                            </div>
                                                            <div class="col-sm-4">
                                                                    <div class="form-group">
                                                                        <label style="color: white; margin: 5px;">Contact Number</label>
                                                                        <input type="text" class="form-control" name="phone" placeholder="Enter your contact number*" required> 
                                                                    </div>
                                                            </div>
                                                            <div class="col-sm-4">
                                                                    <div class="form-group">
                                                                        <label style="color: white; margin: 5px;">Email</label>
                                                                        <input type="email" class="form-control" name="email" placeholder="Enter your email address*" required>
                                                                    </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-sm-12">
                                                                <div class="form-group">
                                                                    <label style="color: white; margin: 5px;">Channels</label>
                                                                    <select id="select-form" class="select2 select2-multiple form-control" name="channels" multiple="multiple" data-placeholder="Select channels">
                                                                        <optgroup label="Chennals">    
                                                                            <option value="amazon">Amazon</option>
                                                                            <option value="flipkart">Flipkart</option>
                                                                            <option value="snapdeal">Snapdeal</option>
                                                                        </optgroup>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-sm-12">
                                                                <div class="form-group">
                                                                    <label style="color: white; margin: 5px;">Message</label>
                                                                    <textarea rows="4" type="text" class="form-control" name="message" placeholder="Enter your message"></textarea> 
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="form-actions">
                                                            <input type="submit" class="btn btn-success btn-icon left-icon mr-10 pull-left" value="Submit"> 
                                                            <input type="reset" onclick="resetForm()" class="btn btn-warning btn-icon left-icon mr-10 pull-left" value="Reset"> 
                                                                <div class="clearfix"></div>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                    <!-- /Row -->
                        </div>
			<!-- /Main Content -->
		</div>
                <!-- jQuery -->
		<script src="${cp}/admin/vendors/bower_components/jquery/dist/jquery.min.js"></script>
		
		<!-- Bootstrap Core JavaScript -->
		<script src="${cp}/admin/vendors/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
		
		<!-- Select2 JavaScript -->
		<script src="${cp}/admin/vendors/bower_components/select2/dist/js/select2.full.min.js"></script>
		
		<!-- Bootstrap Select JavaScript -->
		<script src="${cp}/admin/vendors/bower_components/bootstrap-select/dist/js/bootstrap-select.min.js"></script>
		
                <!-- Multiselect JavaScript -->
		<script src="${cp}/admin/vendors/bower_components/multiselect/js/jquery.multi-select.js"></script>
		
		<!-- Form Advance Init JavaScript -->
                <script>
                    $(document).ready(function() {
                        "use strict";
                        /* Select2 Init*/
                        $(".select2").select2();
                    });
                </script>
		
		<!-- Bootstrap Core JavaScript -->
		<script src="${cp}/admin/vendors/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
                <script src="${cp}/admin/vendors/bower_components/jquery-toast-plugin/dist/jquery.toast.min.js"></script>
                
                <!-- Slimscroll JavaScript -->
		<script src="${cp}/admin/dist/js/jquery.slimscroll.js"></script>
	
		<!-- Init JavaScript -->
                <script src="${cp}/admin/dist/js/toast-data.js"></script>
		<script src="${cp}/admin/dist/js/init.js"></script>
                
                <!--Ajax-->
                <script src="${cp}/ajax/register.js"></script>
                <script src="${cp}/ajax/common.loading.method.js"></script>
		
	</body>
</html>