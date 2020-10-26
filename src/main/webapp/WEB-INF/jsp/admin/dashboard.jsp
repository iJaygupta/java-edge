<%-- 
    Document   : admin
    Created on : 6 Nov, 2018, 3:00:48 PM
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
	<!-- Data table CSS -->
	<link href="${cp}/admin/vendors/bower_components/datatables/media/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
	<!-- Custom CSS -->
	<link href="${cp}/admin/dist/css/style.css" rel="stylesheet" type="text/css">
</head>

<body>
	<!-- Preloader -->
	<div class="preloader-it">
		<div class="la-anim-1"></div>
	</div>
	<!-- /Preloader -->
    <div class="wrapper theme-4-active pimary-color-red">
		<!-- header header  -->
                <div class="header" id="header">
                    <%@include file="../include/account/header.jsp" %>
                </div>
                <!-- End header header -->
                <!-- Left Sidebar  -->
                <div class="left-sidebar" id="left-sidebar">
                    <%@include file="../include/account/sidemenu.jsp" %>
                </div>
        <!-- Main Content -->
		<div class="page-wrapper">
            <div class="container-fluid pt-25">
				<!-- Row -->
				<div class="row">
                                    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                                        <div class="panel panel-default card-view pa-0">
                                            <div class="panel-wrapper collapse in">
                                                <div class="panel-body pa-0">
                                                    <div class="sm-data-box bg-red">
                                                        <div class="container-fluid">
                                                            <div class="row">
                                                                <div class="col-xs-6 text-center pl-0 pr-0 data-wrap-left">
                                                                        <span class="txt-light block counter"><span class="counter-anim">20</span></span>
                                                                        <span class="weight-500 uppercase-font txt-light block">New Orders</span>
                                                                </div>
                                                                <div class="col-xs-6 text-center  pl-0 pr-0 data-wrap-right">
                                                                        <i class="ti ti-info-alt txt-light data-right-rep-icon"></i>
                                                                </div>
                                                            </div>	
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                                        <div class="panel panel-default card-view pa-0">
                                            <div class="panel-wrapper collapse in">
                                                <div class="panel-body pa-0">
                                                    <div class="sm-data-box bg-yellow">
                                                        <div class="container-fluid">
                                                                <div class="row">
                                                                <div class="col-xs-6 text-center pl-0 pr-0 data-wrap-left">
                                                                        <span class="txt-light block counter"><span class="counter-anim">40</span></span>
                                                                        <span class="weight-500 uppercase-font txt-light block font-13">Confirmed</span>
                                                                </div>
                                                                <div class="col-xs-6 text-center  pl-0 pr-0 data-wrap-right">
                                                                        <i class="fa fa-cart-plus txt-light data-right-rep-icon"></i>
                                                                </div>
                                                            </div>	
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                                        <div class="panel panel-default card-view pa-0">
                                            <div class="panel-wrapper collapse in">
                                                <div class="panel-body pa-0">
                                                    <div class="sm-data-box bg-green">
                                                        <div class="container-fluid">
                                                            <div class="row">
                                                                <div class="col-xs-6 text-center pl-0 pr-0 data-wrap-left">
                                                                        <span class="txt-light block counter"><span class="counter-anim">5</span></span>
                                                                        <span class="weight-500 uppercase-font txt-light block">Cancelled</span>
                                                                </div>
                                                                <div class="col-xs-6 text-center  pl-0 pr-0 data-wrap-right">
                                                                        <i class="fa  fa-cart-arrow-down txt-light data-right-rep-icon"></i>
                                                                </div>
                                                            </div>	
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                                        <div class="panel panel-default card-view pa-0">
                                            <div class="panel-wrapper collapse in">
                                                <div class="panel-body pa-0">
                                                    <div class="sm-data-box bg-blue">
                                                        <div class="container-fluid">
                                                            <div class="row">
                                                                <div class="col-xs-6 text-center pl-0 pr-0 data-wrap-left">
                                                                    <span class="txt-light block counter"><span class="counter-anim">40</span></span>
                                                                        <span class="weight-500 uppercase-font txt-light block font-13">Proceed</span>
                                                                </div>
                                                                <div class="col-xs-6 text-center  pl-0 pr-0 data-wrap-right">
                                                                        <i class="ti ti-package txt-light data-right-rep-icon"></i>
                                                                </div>
                                                            </div>	
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
				</div>
				<!-- /Row -->
                                <!-- Row -->
				<div class="row">
                                    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                                        <div class="panel panel-default card-view pa-0">
                                            <div class="panel-wrapper collapse in">
                                                <div class="panel-body pa-0">
                                                    <div class="sm-data-box bg-red">
                                                        <div class="container-fluid">
                                                            <div class="row">
                                                                <div class="col-xs-6 text-center pl-0 pr-0 data-wrap-left">
                                                                        <span class="txt-light block counter"><span class="counter-anim">20</span></span>
                                                                        <span class="weight-500 uppercase-font txt-light block">Shipping</span>
                                                                </div>
                                                                <div class="col-xs-6 text-center  pl-0 pr-0 data-wrap-right">
                                                                        <i class="fa fa-truck txt-light data-right-rep-icon"></i>
                                                                </div>
                                                            </div>	
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                                        <div class="panel panel-default card-view pa-0">
                                            <div class="panel-wrapper collapse in">
                                                <div class="panel-body pa-0">
                                                    <div class="sm-data-box bg-blue">
                                                        <div class="container-fluid">
                                                            <div class="row">
                                                                <div class="col-xs-6 text-center pl-0 pr-0 data-wrap-left">
                                                                    <span class="txt-light block counter"><span class="counter-anim">40</span></span>
                                                                        <span class="weight-500 uppercase-font txt-light block font-13">Delivered</span>
                                                                </div>
                                                                <div class="col-xs-6 text-center  pl-0 pr-0 data-wrap-right">
                                                                        <i class="fa fa-check-square-o txt-light data-right-rep-icon"></i>
                                                                </div>
                                                            </div>	
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                                        <div class="panel panel-default card-view pa-0">
                                            <div class="panel-wrapper collapse in">
                                                <div class="panel-body pa-0">
                                                    <div class="sm-data-box bg-yellow">
                                                        <div class="container-fluid">
                                                                <div class="row">
                                                                <div class="col-xs-6 text-center pl-0 pr-0 data-wrap-left">
                                                                        <span class="txt-light block counter"><span class="counter-anim">40</span></span>
                                                                        <span class="weight-500 uppercase-font txt-light block font-13">Return Preceed</span>
                                                                </div>
                                                                <div class="col-xs-6 text-center  pl-0 pr-0 data-wrap-right">
                                                                        <i class="fa fa-exclamation-triangle txt-light data-right-rep-icon"></i>
                                                                </div>
                                                            </div>	
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                                        <div class="panel panel-default card-view pa-0">
                                            <div class="panel-wrapper collapse in">
                                                <div class="panel-body pa-0">
                                                    <div class="sm-data-box bg-green">
                                                        <div class="container-fluid">
                                                            <div class="row">
                                                                <div class="col-xs-6 text-center pl-0 pr-0 data-wrap-left">
                                                                        <span class="txt-light block counter"><span class="counter-anim">2</span></span>
                                                                        <span class="weight-500 uppercase-font txt-light block">Returned</span>
                                                                </div>
                                                                <div class="col-xs-6 text-center  pl-0 pr-0 data-wrap-right">
                                                                        <i class="fa fa-retweet txt-light data-right-rep-icon"></i>
                                                                </div>
                                                            </div>	
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
				</div>
				<!-- /Row -->
                                <hr>
                                <!-- Row -->
				<div class="row">
                                    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                                        <div class="panel panel-default card-view pa-0">
                                            <div class="panel-wrapper collapse in">
                                                <div class="panel-body pa-0">
                                                    <div class="sm-data-box bg-red">
                                                        <div class="container-fluid">
                                                            <div class="row">
                                                                <div class="col-xs-6 text-center pl-0 pr-0 data-wrap-left">
                                                                        <span class="txt-light block counter"><span class="counter-anim">1</span></span>
                                                                        <span class="weight-500 uppercase-font txt-light block font-13">Facilities</span>
                                                                </div>
                                                                <div class="col-xs-6 text-center  pl-0 pr-0 data-wrap-right">
                                                                        <i class="fa fa-building-o txt-light data-right-rep-icon"></i>
                                                                </div>
                                                            </div>	
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                                        <div class="panel panel-default card-view pa-0">
                                            <div class="panel-wrapper collapse in">
                                                <div class="panel-body pa-0">
                                                    <div class="sm-data-box bg-yellow">
                                                        <div class="container-fluid">
                                                            <div class="row">
                                                                <div class="col-xs-6 text-center pl-0 pr-0 data-wrap-left">
                                                                        <span class="txt-light block counter"><span class="counter-anim">1</span></span>
                                                                        <span class="weight-500 uppercase-font txt-light block">Users</span>
                                                                </div>
                                                                <div class="col-xs-6 text-center  pl-0 pr-0 data-wrap-right">
                                                                        <i class="zmdi zmdi-accounts-list txt-light data-right-rep-icon"></i>
                                                                </div>
                                                            </div>	
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                                        <div class="panel panel-default card-view pa-0">
                                            <div class="panel-wrapper collapse in">
                                                <div class="panel-body pa-0">
                                                    <div class="sm-data-box bg-green">
                                                        <div class="container-fluid">
                                                            <div class="row">
                                                                <div class="col-xs-6 text-center pl-0 pr-0 data-wrap-left">
                                                                        <span class="txt-light block counter"><span class="counter-anim">5</span></span>
                                                                        <span class="weight-500 uppercase-font txt-light block">Channels</span>
                                                                </div>
                                                                <div class="col-xs-6 text-center  pl-0 pr-0 data-wrap-right">
                                                                        <i class="fa fa-amazon txt-light data-right-rep-icon"></i>
                                                                </div>
                                                            </div>	
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-3 col-md-6 col-sm-6 col-xs-12">
                                        <div class="panel panel-default card-view pa-0">
                                            <div class="panel-wrapper collapse in">
                                                <div class="panel-body pa-0">
                                                    <div class="sm-data-box bg-blue">
                                                        <div class="container-fluid">
                                                            <div class="row">
                                                                <div class="col-xs-6 text-center pl-0 pr-0 data-wrap-left">
                                                                        <span class="txt-light block counter"><span class="counter-anim">20</span></span>
                                                                        <span class="weight-500 uppercase-font txt-light block">Invoices</span>
                                                                </div>
                                                                <div class="col-xs-6 text-center  pl-0 pr-0 pt-25  data-wrap-right">
                                                                        <i class="zmdi zmdi-file txt-light data-right-rep-icon"></i>
                                                                </div>
                                                            </div>	
                                                        </div>
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
    
	<!-- Data table JavaScript -->
	<script src="${cp}/admin/vendors/bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
	
	<!-- Slimscroll JavaScript -->
	<script src="${cp}/admin/dist/js/jquery.slimscroll.js"></script>
	<!-- Progressbar Animation JavaScript -->
	<script src="${cp}/admin/vendors/bower_components/waypoints/lib/jquery.waypoints.min.js"></script>
	<script src="${cp}/admin/vendors/bower_components/jquery.counterup/jquery.counterup.min.js"></script>
	
	<!-- Fancy Dropdown JS -->
	<script src="${cp}/admin/dist/js/dropdown-bootstrap-extended.js"></script>
	
	<!-- Sparkline JavaScript -->
	<script src="${cp}/admin/vendors/jquery.sparkline/dist/jquery.sparkline.min.js"></script>
	
	<!-- Owl JavaScript -->
	<script src="${cp}/admin/vendors/bower_components/owl.carousel/dist/owl.carousel.min.js"></script>
	
	<!-- Switchery JavaScript -->
	<script src="${cp}/admin/vendors/bower_components/switchery/dist/switchery.min.js"></script>
	
	<!-- Init JavaScript -->
	<script src="${cp}/admin/dist/js/init.js"></script>
</body>

</html>
