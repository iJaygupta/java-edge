<%-- 
    Document   : user-profile
    Created on : 26 Jul, 2019, 1:43:55 AM
    Author     : dell
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}/resources" scope="request" />
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>ADMIS LIS - PROFILE</title>

    <link rel="icon" type="image/png" sizes="16x16" href="${cp}/img/icon.png">

    <!-- Bootstrap Core CSS -->
    <link href="${cp}/admin/css/lib/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="${cp}/admin/css/lib/toastr/toastr.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="${cp}/admin/css/helper.css" rel="stylesheet">
    <link href="${cp}/admin/css/style.css" rel="stylesheet">
    
    <!--Medical Icons-->
    <link rel="stylesheet" href="${cp}/admin/icons/medical-icons/css/wfmi-style.css">
    
    
</head>

<body class="fix-header fix-sidebar">
    <!-- Preloader - style you can find in spinners.css -->
    <div id='overlay'></div>
    <div class="preloader">
        <svg class="circular" viewBox="25 25 50 50">
			<circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10" /> </svg>
    </div>
    <!-- Main wrapper  -->
    <div id="main-wrapper">
        <!-- header header  -->
        <div class="header" id="header">
            
        </div>
        <!-- End header header -->
        <!-- Left Sidebar  -->
        <div class="left-sidebar">
            <!-- Sidebar scroll-->
            <div class="scroll-sidebar">
                <!-- Sidebar navigation-->
                <nav class="sidebar-nav">
                    <ul id="sidebarnav">
                        <li class="nav-devider"></li>
                        <c:if test="${user.userPermission.viewPatient=='Y'}">
                            <li class="nav-label">Patient</li>
<!--                            <li> <a href="/user/patient-list" aria-expanded="false"><i class="fa fa-users"></i><span class="hide-menu">Patient List</span></a>
                            </li>-->
                            <li> <a href="/user/patient-search" aria-expanded="false"><i class="fa fa-users"></i><span class="hide-menu">Find Patient</span></a>
                            </li>
                            <c:if test="${user.userPermission.createPermission=='Y'}">
                                <li> <a href="/user/register-patient" aria-expanded="false"><i class="fa fa-plus-circle"></i><span class="hide-menu">Add Patient</span></a>
                                </li>
                            </c:if>
                        </c:if>
                        <c:if test="${user.userPermission.viewInvoice=='Y'}">
                            <li class="nav-label">Invoice</li>
                            <li> <a href="/user/medicine/medicine-invoice-list" aria-expanded="false"><i class="fa fa-download"></i><span class="hide-menu">Medicine Invoice</span></a>
                            </li>
                             <li> <a href="/user/test-invoice-list" aria-expanded="false"><i class="fa fa-download"></i><span class="hide-menu">Test Invoice</span></a>
                            </li>
                        </c:if>
                        <c:if test="${user.userPermission.viewReport=='Y'}">    
                            <li class="nav-label">Report</li>
                            <li> <a href="/user/report-list" aria-expanded="false"><i class="fa fa-download"></i><span class="hide-menu">Download Report</span></a>
                            </li>
                        </c:if>  
                    </ul>
                </nav>
                <!-- End Sidebar navigation -->
            </div>
            <!-- End Sidebar scroll-->
        </div>
        <!-- End Left Sidebar  -->
        <!-- Page wrapper  -->
        <div class="page-wrapper">
            <!-- Bread crumb -->
            <div class="row page-titles">
                <div class="col-md-5 align-self-center">
                    <h3 class="text-primary">Dashboard</h3> </div>
                <div class="col-md-7 align-self-center">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/user">Home</a></li>
                        <li class="breadcrumb-item active">Profile</li>
                    </ol>
                </div>
            </div>
            <!-- End Bread crumb -->
            <!-- Container fluid  -->
            <div class="container-fluid">
                <!-- Start Page Content -->
                <!-- Start Page Content -->
                    <div class="row" style="width: 100%;">
                    <!-- Column -->
                    <div class="col-lg-12">
                        <div class="card" style="margin: 0px; padding: 0px;">
                            <div class="card-body">
                                <div class="card-two">
                                    <header>
                                        <div class="avatar" style="background: white;">
                                            <img src="${cp}/user/images/profile.png" alt="${user.name}" />
                                        </div>
                                    </header>
                                    <h3 style="text-transform: capitalize">${facility.facilityName}</h3>
                                    <div class="desc">
                                        <h4 style="text-transform: capitalize">${role} Panel</h4>
                                        <span>${param.message}${message}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Column -->
                    <div class="col-lg-12">
                        <div class="card">
                            <!-- Nav tabs -->
                            <ul class="nav nav-tabs profile-tab" role="tablist">
                                <li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#profile" role="tab">Profile</a> </li>
                                <li class="nav-item"> <a class="nav-link" data-toggle="tab" href="#settings" role="tab">Settings</a> </li>
                            </ul>
                            <!-- Tab panes -->
                            <div class="tab-content">
                                <!--second tab-->
                                <div class="tab-pane active" id="profile" role="tabpanel">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-md-3 col-xs-6 b-r"> <strong>Name</strong>
                                                <br>
                                                <p class="text-muted">${user.name}</p>
                                            </div>
                                            <div class="col-md-3 col-xs-6 b-r"> <strong>Number</strong>
                                                <br>
                                                <p class="text-muted">${user.number}</p>
                                            </div>
                                            <div class="col-md-3 col-xs-6"> <strong>Email</strong>
                                                <br>
                                                <p class="text-muted">${user.email}</p>
                                            </div>
                                            <div class="col-md-3 col-xs-6"> <strong>Facility</strong>
                                                <br>
                                                <p class="text-muted">${facility.facilityName}</p>
                                            </div>
                                        </div>
                                        <hr>
                                    </div>
                                </div>
                                <div class="tab-pane" id="settings" role="tabpanel">
                                    <div class="card-body">
                                        <form class="form-horizontal form-material" action="/user/changeUserPassword" id="user-password-form">
                                            <br>
                                            <div class="form-group">
                                                <label class="col-md-12">Old Password</label>
                                                <div class="col-md-12">
                                                    <input type="password" name="oldPassword" class="form-control form-control-line">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-12">New Password</label>
                                                <div class="col-md-12">
                                                    <input type="password" name="newPassword" class="form-control form-control-line">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-12">
                                                    <input class="btn btn-success" id="submit-btn" type="submit">
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Column -->                
                <!-- End PAge Content -->
            </div>
            <!-- End Container fluid  -->
        </div>
        <!-- End Page wrapper  -->
    </div>
    <!-- End Wrapper -->
    <!--loading-->
    <div id="wait" style="display:none;width:69px;height:89px;border:1px solid black;position:absolute;top:50%;left:50%;padding:2px;"><img src='${cp}/img/lab-loading.gif' width="64" height="64" /><br>Loading..</div>
    <!--/.loading-->     
    <!-- All Jquery -->
    <script src="${cp}/admin/js/lib/jquery/jquery.min.js"></script>
    <!-- Bootstrap tether Core JavaScript -->
    <script src="${cp}/admin/js/lib/bootstrap/js/popper.min.js"></script>
    <script src="${cp}/admin/js/lib/bootstrap/js/bootstrap.min.js"></script>
    <!-- slimscrollbar scrollbar JavaScript -->
    <script src="${cp}/admin/js/jquery.slimscroll.js"></script>
    <!--Menu sidebar -->
    <script src="${cp}/admin/js/sidebarmenu.js"></script>
    <!--stickey kit -->
    <script src="${cp}/admin/js/lib/sticky-kit-master/dist/sticky-kit.min.js"></script>
    <!--Custom JavaScript -->
    <script src="${cp}/admin/js/custom.min.js"></script>
    <!--Toastr-->
    <script src="${cp}/admin/js/lib/toastr/toastr.min.js"></script>
    <script src="${cp}/admin/js/lib/toastr/toastr.init.js"></script>
     <!--Ajax-->
    <script src="${cp}/ajax/user/reset.user.password.js"></script>
    <script src="${cp}/ajax/common.loading.method.js"></script>
    
    <!--Admin header set-->
    <script>
       $.get("${cp}/html/user-header.html", function(data){
            $("#header").html(data);
        });
    </script>
    
</body>

</html>
