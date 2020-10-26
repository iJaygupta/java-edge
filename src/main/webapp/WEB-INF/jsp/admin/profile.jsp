<%-- 
    Document   : profile
    Created on : 4 Dec, 2018, 3:20:57 PM
    Author     : Adeep My IT Solution Private Limited
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <div class="left-sidebar" id="left-sidebar">
            
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
                        <li class="breadcrumb-item"><a href="/admin">Home</a></li>
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
                                            <img src="${cp}/user/images/profile.png" />
                                        </div>
                                    </header>

                                    <h3>${admin.name}</h3>
                                    <div class="desc">
                                        <span>${param.message}</span>
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
                                            <div class="col-md-3 col-xs-6 b-r"> <strong>Organization Name</strong>
                                                <br>
                                                <p class="text-muted">${organization.name}</p>
                                            </div>
                                            <div class="col-md-3 col-xs-6 b-r"> <strong>Patient Id Prefix</strong>
                                                <br>
                                                <p class="text-muted">${organization.patientIdPrefix}</p>
                                            </div>
                                            <div class="col-md-3 col-xs-6 b-r"> <strong>Patient Id Count Start</strong>
                                                <br>
                                                <p class="text-muted">${organization.patientIdCountStart}</p>
                                            </div>
                                            <div class="col-md-3 col-xs-6"> <strong>Limit</strong>
                                                <br>
                                                <p class="text-muted">Test: ${organization.testLimit} User: ${organization.userLimit}</p>
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="row">
                                            <div class="col-md-3 col-xs-6 b-r"> <strong>Username</strong>
                                                <br>
                                                <p class="text-muted">${admin.username}</p>
                                            </div>
                                            <div class="col-md-3 col-xs-6 b-r"> <strong>Name</strong>
                                                <br>
                                                <p class="text-muted">${admin.name}</p>
                                            </div>
                                            <div class="col-md-3 col-xs-6 b-r"> <strong>Number</strong>
                                                <br>
                                                <p class="text-muted">${admin.number}</p>
                                            </div>
                                            <div class="col-md-3 col-xs-6"> <strong>Email</strong>
                                                <br>
                                                <p class="text-muted">${admin.email}</p>
                                            </div>
                                        </div>
                                        <hr>
                                    </div>
                                </div>
                                <div class="tab-pane" id="settings" role="tabpanel">
                                    <div class="card-body">
                                        <form class="form-horizontal form-material" id="admin-password-form" action="/LaboratoryInformationSystem/admin/adminPasswordChange" method="post">
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
                                                    <input class="btn btn-success" type="submit">
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
    <!--Toastr-->
    <script src="${cp}/admin/js/lib/toastr/toastr.min.js"></script>
    <script src="${cp}/admin/js/lib/toastr/toastr.init.js"></script>
    <!--Custom JavaScript -->
    <script src="${cp}/admin/js/custom.min.js"></script>
    
    <!--Ajax-->
    <script src="${cp}/ajax/admin/reset.admin.password.js"></script>
    <script src="${cp}/ajax/common.loading.method.js"></script>

    <!--Admin header set-->
    <script>
       $.get("${cp}/html/admin-header.html", function(data){
            $("#header").html(data);
        });
    </script>
    
    <!--Admin side menu-->
    <script>
       $.get("${cp}/html/admin-side-menu.html", function(data){
            $("#left-sidebar").html(data);
        });
    </script>
    
</body>

</html>
