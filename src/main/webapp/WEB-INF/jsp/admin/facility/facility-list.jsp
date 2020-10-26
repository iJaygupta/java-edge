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
		<title>${ServerConfiguration.COMPANY_SHORT_NAME} | Facility</title>
                <!-- Favicon -->
                <link rel="shortcut icon" href="${cp}/img/icon.png">
                <link rel="icon" href="${cp}/img/icon.png" type="image/x-icon">
                <!-- Data table CSS -->
                <link href="${cp}/admin/vendors/bower_components/datatables/media/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
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
                                            <h5 class="txt-dark">facility | <a style="color: #ea6c41;" href="${ServerConfiguration.LOCAL_URL}/admin/facility/facility-add">Add New Facility</a></h5>
                                        </div>
                                        <!-- Breadcrumb -->
                                        <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                                          <ol class="breadcrumb">
                                                <li><a href="${ServerConfiguration.LOCAL_URL}/master">Dashboard</a></li>
                                                <li class="active"><span>facility</span></li>
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
                                                    <div class="table-wrap">
                                                        <div class="table-responsive">
                                                            <table id="datable_1" class="table table-hover display  pb-30" >
                                                                <thead>
                                                                    <tr>
                                                                        <th>Date</th>
                                                                        <th>Facility Name</th>
                                                                        <th>Facility Code</th>
                                                                        <th>Facility Address</th>
                                                                        <th>Facility State</th>
                                                                        <th>Supervisor Name</th>
                                                                        <th>Status</th>
                                                                        <th>Option</th>
                                                                    </tr>
                                                                </thead>
                                                                <tfoot>
                                                                    <tr>
                                                                        <tr>
                                                                            <th>Date</th>
                                                                            <th>Facility Name</th>
                                                                            <th>Facility Code</th>
                                                                            <th>Facility Address</th>
                                                                            <th>Facility State</th>
                                                                            <th>Supervisor Name</th>
                                                                            <th>Status</th>
                                                                            <th>Option</th>
                                                                        </tr>
                                                                    </tr>
                                                                </tfoot>
                                                                <tbody>
                                                                    <c:forEach items="${facilities}" var="list" varStatus="theCount">
                                                                        <tr style="text-align: center;">
                                                                            <th><span class="txt-dark weight-500">${list.date}</span></th>
                                                                            <th><span class="txt-dark weight-500">${list.facilityName}</span></th>
                                                                            <th><span class="txt-dark weight-500">${list.facilityCode}</span></th>
                                                                            <th><span class="txt-dark weight-500">${list.facilityAddress}</span></th>
                                                                            <th><span class="txt-dark weight-500">${list.facilityState}</span></th>
                                                                            <th><span class="txt-dark weight-500">${list.facilitySupervisorName}</span></th>
                                                                            <th><span class="label label-danger">${list.facilityStatus}</span></th>
                                                                            <th class="optionColumn">
                                                                                <a href="${ServerConfiguration.LOCAL_URL}/admin/facility/facility-edit?facilityId=${list.id}">
                                                                                    <i title="Edit" class="fa fa-edit fa-2x optionIcon"></i>
                                                                                </a>    
                                                                            </th>
                                                                        </tr>
                                                                    </c:forEach>
                                                                </tbody>
                                                            </table>
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
		<!-- jQuery -->
		<script src="${cp}/admin/vendors/bower_components/jquery/dist/jquery.min.js"></script>
		
		<!-- Bootstrap Core JavaScript -->
		<script src="${cp}/admin/vendors/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
                
                <!-- Data table JavaScript -->
                <script src="${cp}/admin/vendors/bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
                <script src="${cp}/admin/dist/js/dataTables-data.js"></script>
		
		<!-- Slimscroll JavaScript -->
		<script src="${cp}/admin/dist/js/jquery.slimscroll.js"></script>
	
		<!-- Fancy Dropdown JS -->
		<script src="${cp}/admin/dist/js/dropdown-bootstrap-extended.js"></script>
		
		<!-- Owl JavaScript -->
		<script src="${cp}/admin/vendors/bower_components/owl.carousel/dist/owl.carousel.min.js"></script>
	
		<!-- Switchery JavaScript -->
		<script src="${cp}/admin/vendors/bower_components/switchery/dist/switchery.min.js"></script>
	
		<!-- Init JavaScript -->
		<script src="${cp}/admin/dist/js/init.js"></script>
		
	</body>
</html>