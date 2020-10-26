<%-- 
    Document   : organization
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
		<title>${ServerConfiguration.COMPANY_SHORT_NAME} | Admin</title>
                <!-- Favicon -->
                <link rel="shortcut icon" href="${cp}/img/icon.png">
                <link rel="icon" href="${cp}/img/icon.png" type="image/x-icon">
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
		<div class="wrapper theme-4-active pimary-color-red">
			<!-- header header  -->
                        <div class="header" id="header">
                            <%@include file="../../include/master/header.jsp" %>
                        </div>
                        <!-- End header header -->
                        <!-- Left Sidebar  -->
                        <div class="left-sidebar" id="left-sidebar">
                            <%@include file="../../include/master/sidemenu.jsp" %>
                        </div>
			<!-- Main Content -->
			<div class="page-wrapper">
				<div class="container-fluid">
					<!-- Title -->
					<div class="row heading-bg">
						<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
						  <h5 class="txt-dark">update-admin</h5>
						</div>
						<!-- Breadcrumb -->
						<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
						  <ol class="breadcrumb">
							<li><a href="${ServerConfiguration.LOCAL_URL}/master">Dashboard</a></li>
                                                        <li><a href="${ServerConfiguration.LOCAL_URL}/master/admin/admin-list">Admins</a></li>
							<li class="active"><span>update-admin</span></li>
						  </ol>
						</div>
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
                                                                <form action="${ServerConfiguration.LOCAL_URL}/master/updateAdmin" method="POST" id="update-admin-form">
                                                                    <input type="text" value="${admin.username}" name="username" hidden>
                                                                    <h6 class="txt-dark capitalize-font"><i class="zmdi zmdi-calendar-note mr-10"></i>general info | ${admin.username}</h6>
                                                                    <hr class="light-grey-hr"/>
                                                                    <div class="row">
                                                                        <div class="col-sm-3">
                                                                                <div class="form-group">
                                                                                    <input type="text" value="${admin.name}" name="name" class="form-control" placeholder="Name" required>
                                                                                </div>
                                                                        </div>
                                                                        <div class="col-sm-3">
                                                                                <div class="form-group">
                                                                                        <input type="text" value="${admin.number}" name="number" class="form-control" placeholder="Number" required>
                                                                                </div>
                                                                        </div>
                                                                        <div class="col-sm-3">
                                                                                <div class="form-group">
                                                                                        <input type="email" value="${admin.email}" name="email" class="form-control" placeholder="Email" required>
                                                                                </div>
                                                                        </div>
                                                                        <div class="col-sm-3">
                                                                                <div class="form-group">
                                                                                    <select name="status" class="form-control" required>
                                                                                        <option <c:if test="${admin.status == 'active'}">selected</c:if> value="active">Active</option>
                                                                                        <option <c:if test="${admin.status == 'disabled'}">selected</c:if> value="disabled">Disabled</option>
                                                                                        <option <c:if test="${admin.status == 'block'}">selected</c:if> value="block">Block</option>
                                                                                    </select> 
                                                                                </div>
                                                                        </div>        
                                                                    </div>
                                                                    <div class="form-actions">
                                                                        <input type="submit" class="btn btn-success btn-icon left-icon mr-10 pull-left" value="Update"> 
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
			</div>
			<!-- /Main Content -->
		</div>
		<!-- jQuery -->
		<script src="${cp}/admin/vendors/bower_components/jquery/dist/jquery.min.js"></script>
		
		<!-- Bootstrap Core JavaScript -->
		<script src="${cp}/admin/vendors/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
                <script src="${cp}/admin/vendors/bower_components/jquery-toast-plugin/dist/jquery.toast.min.js"></script>
		
		<!-- Slimscroll JavaScript -->
		<script src="${cp}/admin/dist/js/jquery.slimscroll.js"></script>
	
		<!-- Fancy Dropdown JS -->
		<script src="${cp}/admin/dist/js/dropdown-bootstrap-extended.js"></script>
		
		<!-- Owl JavaScript -->
		<script src="${cp}/admin/vendors/bower_components/owl.carousel/dist/owl.carousel.min.js"></script>
	
		<!-- Switchery JavaScript -->
		<script src="${cp}/admin/vendors/bower_components/switchery/dist/switchery.min.js"></script>
	
		<!-- Init JavaScript -->
                 <script src="${cp}/admin/dist/js/toast-data.js"></script>
		<script src="${cp}/admin/dist/js/init.js"></script>
                
                <!--Ajax-->
                <script src="${cp}/ajax/master/update.admin.js"></script>
                <script src="${cp}/ajax/common.loading.method.js"></script>
		
	</body>
</html>