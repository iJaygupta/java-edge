<%-- 
    Document   : bill-format
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
		<title>${ServerConfiguration.COMPANY_SHORT_NAME} | Format</title>
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
                            <%@include file="../../include/account/header.jsp" %>
                        </div>
                        <!-- End header header -->
                        <!-- Left Sidebar  -->
                        <div class="left-sidebar" id="left-sidebar">
                            <%@include file="../../include/account/sidemenu.jsp" %>
                        </div>
			<!-- Main Content -->
			<div class="page-wrapper">
				<div class="container-fluid">
					<!-- Title -->
					<div class="row heading-bg">
						<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
						  <h5 class="txt-dark">bill-format</h5>
						</div>
						<!-- Breadcrumb -->
						<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
						  <ol class="breadcrumb">
							<li><a href="${ServerConfiguration.LOCAL_URL}/master">Dashboard</a></li>
							<li class="active"><span>bill-format</span></li>
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
                                                                <form action="${ServerConfiguration.LOCAL_URL}/admin/updateBillFormat" id="update-bill-form">
                                                                    <input type="text" name="id" value="${format.id}" required hidden>
                                                                    <h6 class="txt-dark capitalize-font"><i class="zmdi zmdi-calendar-note mr-10"></i>Note: These details will be use for billing purpose</h6>
                                                                    <hr class="light-grey-hr"/>
                                                                    <div class="row">
                                                                        <div class="col-sm-4">
                                                                            <div class="form-group">
                                                                                <input type="text" class="form-control" placeholder="CIN" name="cin" value="${format.cin}" pattern="^([L|U]{1})([0-9]{5})([A-Za-z]{2})([0-9]{4})([A-Za-z]{3})([0-9]{6})$">
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-4">
                                                                            <div class="form-group">
                                                                                <input type="text" class="form-control" placeholder="PAN" name="panNumber" value="${format.panNumber}" pattern="^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$">
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-4">
                                                                            <div class="form-group">
                                                                                <input type="text" class="form-control" placeholder="GST" name="gstNumber" value="${format.gstNumber}" pattern="^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$">
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                    <div class="row">
                                                                        <div class="col-sm-4">
                                                                            <div class="form-group">
                                                                                <input type="text" placeholder="Invoice Id Prefix"  name="invoiceNumberPrefix" value="${format.invoiceNumberPrefix}" title="Enter invoice number prefix" class="form-control" maxlength="8">
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-4">
                                                                            <div class="form-group">
                                                                                <input type="text" id="year" class="form-control">
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-sm-4">
                                                                            <div class="form-group">
                                                                                <input type="number" class="form-control" name="invoiceCountStart" value="${format.invoiceCountStart}" title="Enter invoice count start number" placeholder="Invoice Count Start">
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
                <script src="${cp}/ajax/admin/bill/update.bill.format.js"></script>
                <script src="${cp}/ajax/admin/bill/upload.logo.js"></script>
                <script src="${cp}/ajax/common.loading.method.js"></script>

                <!--Convert Money into words-->
                <script>  
               window.onload =  function convertNumberToWords() {
                document.getElementById("year").value = new Date().getFullYear();   
                }
                </script>
		
	</body>
</html>