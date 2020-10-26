<%-- 
    Document   : user-edit
    Created on : 16 Oct, 2018, 1:05:06 PM
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

    <title>ADMIS LIS - Edit</title>

    <link rel="icon" type="image/png" sizes="16x16" href="${cp}/img/icon.png">
    <!-- Bootstrap Core CSS -->
    <link href="${cp}/admin/css/lib/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="${cp}/admin/css/lib/toastr/toastr.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="${cp}/admin/css/helper.css" rel="stylesheet">
    <link href="${cp}/admin/css/style.css" rel="stylesheet">
    <style>
        .optionIcon{
            margin-right: 15px;
        }
    </style>
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
                    <h3 class="text-primary">User</h3> </div>
                <div class="col-md-7 align-self-center">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/admin">Home</a></li>
                        <li class="breadcrumb-item active"><a href="/admin/user">User</a></li>
                        <li class="breadcrumb-item active">Edit-User</li>
                    </ol>
                </div>
            </div>
            <!-- End Bread crumb -->
            <!-- Container fluid  -->
            <div class="container-fluid">
                <!-- Start Page Content -->
                <div class="row justify-content-center">
                    <div class="col-lg-10">
                        <div class="card">
                            <div class="card-body">
                                <div class="form-validation">
                                    <form class="form-valide" action="/LaboratoryInformationSystem/admin/userUpdate" id="update-user-form">
                                        <input type="number" class="form-control" name="id" value="${user.id}" required hidden>
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label" for="val-name">Name <span class="text-danger">*</span></label>
                                            <div class="col-lg-6">
                                                <input type="text" class="form-control" name="name" value="${user.name}" required>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label">Number <span class="text-danger">*</span></label>
                                            <div class="col-lg-6">
                                                <input type="tel" class="form-control" minlength="9" maxlength="14" name="number" value="${user.number}" required>
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label">Email <span class="text-danger">*</span></label>
                                            <div class="col-lg-6">
                                                <input type="email" class="form-control" name="email" maxlength="75" value="${user.email}" required>
                                            </div>
                                        </div>    
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label">Employee Id</label>
                                            <div class="col-lg-6">
                                                <input type="text" class="form-control" name="employeeId" value="${user.employeeId}">
                                            </div>
                                        </div>   
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label">Skype Id</label>
                                            <div class="col-lg-6">
                                                <input type="text" class="form-control" name="skypeId" value="${user.skypeId}">
                                            </div>
                                        </div>       
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label">Address</label>
                                            <div class="col-lg-6">
                                                <input type="text" class="form-control" name="address" value="${user.address}">
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label">User Permission <span class="text-danger">*</span></label>
                                        </div> 
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label">View</label>
                                            <div class="col-lg-2">
                                                <span style="padding: 3px;">Patient</span><input type="checkbox" id="viewPatient" name="viewPatient" value="Y" style="margin: 3px;" <c:if test="${user.userPermission.viewPatient == 'Y'}">checked</c:if> >
                                            </div>
                                            <div class="col-lg-2">
                                                <span style="padding: 3px;">Invoice</span><input type="checkbox" id="viewInvoice" name="viewInvoice" value="Y" style="margin: 3px;" <c:if test="${user.userPermission.viewInvoice == 'Y'}">checked</c:if> >
                                            </div>
                                            <div class="col-lg-2">
                                                <span style="padding: 3px;">Report</span><input type="checkbox" id="viewReport" name="viewReport" value="Y" style="margin: 3px;" <c:if test="${user.userPermission.viewReport == 'Y'}">checked</c:if> >
                                            </div>
                                            <c:if test="${admin.adminPermission.pharmacy == 'Y'}">  
                                            <div class="col-lg-2">
                                                <span style="padding: 3px;">Medicine</span><input type="checkbox" id="viewReport" name="viewMedicine" value="Y" style="margin: 3px;" <c:if test="${user.userPermission.viewMedicine == 'Y'}">checked</c:if> >
                                            </div>
                                            </c:if>
                                        </div> 
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label">Download</label>
                                            <div class="col-lg-2">
                                                <span style="padding: 3px;">Yes</span><input type="radio" id="downloadyes" name="downloadPermission" value="Y" required style="margin: 3px;" <c:if test="${user.userPermission.downloadPermission == 'Y'}">checked</c:if> >
                                            </div>
                                            <div class="col-lg-2">
                                                <span style="padding: 3px;">No</span><input type="radio" id="downloadno" name="downloadPermission" value="N" required  style="margin: 3px;" <c:if test="${user.userPermission.downloadPermission != 'Y'}">checked</c:if> >
                                            </div>
                                        </div> 
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label">Create</label>
                                            <div class="col-lg-2">
                                                <span style="padding: 3px;">Yes</span><input type="radio" id="createyes" name="createPermission" value="Y" required style="margin: 3px;" <c:if test="${user.userPermission.createPermission == 'Y'}">checked</c:if> >
                                            </div>
                                            <div class="col-lg-2">
                                                <span style="padding: 3px;">No</span><input type="radio" id="createno" name="createPermission" value="N" required  style="margin: 3px;" <c:if test="${user.userPermission.createPermission != 'Y'}">checked</c:if> >
                                            </div>
                                        </div> 
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label">Modify</label>
                                            <div class="col-lg-2">
                                                <span style="padding: 3px;">Yes</span><input type="radio" id="modifyyes" name="modifyPermission" value="Y" required style="margin: 3px;" <c:if test="${user.userPermission.modifyPermission == 'Y'}">checked</c:if> >
                                            </div>
                                            <div class="col-lg-2">
                                                <span style="padding: 3px;">No</span><input type="radio" id="modifyno" name="modifyPermission" value="N" required  style="margin: 3px;" <c:if test="${user.userPermission.modifyPermission != 'Y'}">checked</c:if> >
                                            </div>
                                        </div>   
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label">Report Verify</label>
                                            <div class="col-lg-2">
                                                <span style="padding: 3px;">Yes</span><input type="radio" id="verifyyes" name="reportVerify" value="Y" required style="margin: 3px;" <c:if test="${user.userPermission.reportVerify == 'Y'}">checked</c:if> >
                                            </div>
                                            <div class="col-lg-2">
                                                <span style="padding: 3px;">No</span><input type="radio" id="verifyno" name="reportVerify" value="N" required  style="margin: 3px;" <c:if test="${user.userPermission.reportVerify != 'Y'}">checked</c:if> >
                                            </div>
                                        </div>   
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label">Invoice Modify</label>
                                            <div class="col-lg-2">
                                                <span style="padding: 3px;">Yes</span><input type="radio" id="verifyyes" name="invoiceModify" value="Y" required style="margin: 3px;" <c:if test="${user.userPermission.invoiceModify == 'Y'}">checked</c:if> >
                                            </div>
                                            <div class="col-lg-2">
                                                <span style="padding: 3px;">No</span><input type="radio" id="verifyno" name="invoiceModify" value="N" required  style="margin: 3px;" <c:if test="${user.userPermission.invoiceModify != 'Y'}">checked</c:if> >
                                            </div>
                                        </div>  
                                        <c:if test="${admin.adminPermission.pharmacy == 'Y'}">    
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label">Medicine Stock Update</label>
                                            <div class="col-lg-2">
                                                <span style="padding: 3px;">Yes</span><input type="radio" id="verifyyes" name="medicineStockUpdate" value="Y" required style="margin: 3px;" <c:if test="${user.userPermission.medicineStockUpdate == 'Y'}">checked</c:if> >
                                            </div>
                                            <div class="col-lg-2">
                                                <span style="padding: 3px;">No</span><input type="radio" id="verifyno" name="medicineStockUpdate" value="N" required  style="margin: 3px;" <c:if test="${user.userPermission.medicineStockUpdate != 'Y'}">checked</c:if> >
                                            </div>
                                        </div>  
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label">Medicine Sell</label>
                                            <div class="col-lg-2">
                                                <span style="padding: 3px;">Yes</span><input type="radio" id="verifyyes" name="medicineSell" value="Y" required style="margin: 3px;" <c:if test="${user.userPermission.medicineSell == 'Y'}">checked</c:if> >
                                            </div>
                                            <div class="col-lg-2">
                                                <span style="padding: 3px;">No</span><input type="radio" id="verifyno" name="medicineSell" value="N" required  style="margin: 3px;" <c:if test="${user.userPermission.medicineSell != 'Y'}">checked</c:if> >
                                            </div>
                                        </div>   
                                        </c:if>    
                                        <div class="form-group row">
                                            <label class="col-lg-4 col-form-label">Accept changes<span class="text-danger">*</span></label>
                                            <div class="col-lg-8">
                                                <label class="css-control css-control-primary css-checkbox" for="val-terms">
                                                    <input type="checkbox" class="css-control-input" required>
                                                    <span class="css-control-indicator"></span> I accept the changes
                                                </label>
                                            </div>
                                        </div>    
                                        <div class="form-group row">
                                            <div class="col-lg-8 ml-auto">
                                                <button type="submit" id="submit-btn" class="btn btn-primary">Update</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
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
    <!--Toastr-->
    <script src="${cp}/admin/js/lib/toastr/toastr.min.js"></script>
    <script src="${cp}/admin/js/lib/toastr/toastr.init.js"></script>
    <!--stickey kit -->
    <script src="${cp}/admin/js/lib/sticky-kit-master/dist/sticky-kit.min.js"></script>
    
    <!--Custom JavaScript -->
    <script src="${cp}/admin/js/custom.min.js"></script>
    
    <!--Ajax-->
    <script src="${cp}/ajax/admin/user/update.user.js"></script>
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
