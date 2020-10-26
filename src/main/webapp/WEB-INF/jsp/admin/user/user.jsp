<%-- 
    Document   : facility
    Created on : 07 Otc, 2018, 7:33:17 AM
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

    <title>ADMIS LIS - User</title>

    <link rel="icon" type="image/png" sizes="16x16" href="${cp}/img/icon.png">

    <link href="${cp}/admin/css/lib/toastr/toastr.min.css" rel="stylesheet">
    
    <!-- Bootstrap Core CSS -->
    <link href="${cp}/admin/css/lib/bootstrap/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="${cp}/admin/css/helper.css" rel="stylesheet">
    <link href="${cp}/admin/css/style.css" rel="stylesheet">
   
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
                        <li class="breadcrumb-item active">User</li>
                    </ol>
                </div>
            </div>
            <!-- End Bread crumb -->
            <!-- Container fluid  -->
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <ul class="navbar-nav mr-auto mt-md-0">
                                    <!-- Add User -->
                                    <form id="add-user-form" action="/LaboratoryInformationSystem/admin/addNewUser">
                                    <li class="nav-item dropdown mega-dropdown"> <a class="nav-link dropdown-toggle text-muted  " style="display: contents;" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Add User <i class="fa fa-th-large"></i></a>
                                        <div class="dropdown-menu animated zoomIn">
                                            <ul class="mega-dropdown-menu row">
                                                <li class="col-lg-3  m-b-30">
                                                    <!-- Contact -->
                                                    <div id="alert-message">
                                                        
                                                    </div>
                                                        <div class="form-group">
                                                            <label>Name<span class="text-danger">*</span></label>
                                                            <input type="text" class="form-control" name="name" placeholder="Enter employee name" required> </div>
                                                            <div class="form-group" style="height: 155px;">
                                                            <label>Contact Number<span class="text-danger">*</span><br><span id="numText"></span></label>
                                                            <input type="tel" class="form-control" name="number" placeholder="Enter mobile number" onkeyup="checkMobileNumber(event)" autocomplete="off" id="facilityCode" required> </div>
                                                        <div class="form-group">
                                                            <label>User Access<span class="text-danger">*</span></label></div>
                                                        <div class="form-group">
                                                            <label>View<span class="text-danger">*</span></label>
                                                            <span style="padding: 3px;">Patient</span><input type="checkbox" id="viewPatient" name="viewPatient" value="Y" style="margin: 3px;">
                                                            <span style="padding: 3px;">Invoice</span><input type="checkbox" id="viewInvoice" name="viewInvoice" value="Y" style="margin: 3px;">
                                                            <span style="padding: 3px;">Report</span><input type="checkbox" id="viewReport" name="viewReport" value="Y" style="margin: 3px;">
                                                            <c:if test="${admin.adminPermission.pharmacy == 'Y'}">   
                                                            <span style="padding: 3px;">Medicine</span><input type="checkbox" id="viewMedicine" name="viewMedicine" value="Y" style="margin: 3px;">
                                                            </c:if>
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Download<span class="text-danger">*</span></label>
                                                            <span style="padding: 3px;">Yes</span><input type="radio" id="downloadyes" name="downloadPermission" value="Y" required style="margin: 3px;">
                                                            <span style="padding: 3px;">No</span><input type="radio" id="downloadno" name="downloadPermission" value="N" required  style="margin: 3px;">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Create<span class="text-danger">*</span></label>
                                                            <span style="padding: 3px;">Yes</span><input type="radio" id="createyes" name="createPermission" value="Y" required style="margin: 3px;">
                                                            <span style="padding: 3px;">No</span><input type="radio" id="createno" name="createPermission" value="N" required  style="margin: 3px;">
                                                        </div>
                                                    <br>
                                                        <div class="form-group">
                                                            <label>Modify<span class="text-danger">*</span></label>
                                                            <span style="padding: 3px;">Yes</span><input type="radio" id="modifyyes" name="modifyPermission" value="Y" required style="margin: 3px;">
                                                            <span style="padding: 3px;">No</span><input type="radio" id="modifyno" name="modifyPermission" value="N" required  style="margin: 3px;">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Report Verify<span class="text-danger">*</span></label>
                                                            <span style="padding: 3px;">Yes</span><input type="radio" id="verifyyes" name="reportVerify" value="Y" required style="margin: 3px;">
                                                            <span style="padding: 3px;">No</span><input type="radio" id="verifyno" name="reportVerify" value="N" required  style="margin: 3px;">
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Invoice Modify<span class="text-danger">*</span></label>
                                                            <span style="padding: 3px;">Yes</span><input type="radio" id="invoiceModifyyes" name="invoiceModify" value="Y" required style="margin: 3px;">
                                                            <span style="padding: 3px;">No</span><input type="radio" id="invoiceModifyno" name="invoiceModify" value="N" required  style="margin: 3px;">
                                                        </div>
                                                </li>
                                                <li class="col-lg-3  m-b-30">
                                                    <!-- Contact -->
                                                        <div class="form-group">
                                                            <label>Email<span class="text-danger">*</span></label>
                                                            <input type="email" class="form-control" name="email" placeholder="Enter email" required> </div>
                                                        <div class="form-group">
                                                            <label>Employee Id</label>
                                                            <input type="text" class="form-control" name="employeeId" placeholder="Enter Employee Id"> </div>
                                                        <div class="form-group">
                                                            <label>Skype Id</label>
                                                            <input type="text" class="form-control" name="skypeId" placeholder="Enter Skype Id"> </div>    
                                                        <div class="form-group">
                                                            <label style="color: red;">Note: </label></div>
                                                        <div class="form-group">
                                                            <label>Pages user can view.</label></div><br>
                                                        <div class="form-group">
                                                            <label>User can print invoice, report etc.</label></div>  
                                                        <div class="form-group">
                                                            <label>User add new patient, invoice, report and edit the same patient details.</label></div>   
                                                        <div class="form-group">
                                                            <label style="color: red;">User can modify any patient details.</label></div>    
                                                        <div class="form-group">
                                                            <label style="color: red;">Doctor can verify patient report.</label></div>        
                                                         <div class="form-group">
                                                            <label style="color: red;">User can change payment status.</label></div> 
                                                    
                                                </li>
                                                <li class="col-lg-3  m-b-30">
                                                    <!-- Contact -->
                                                        <div class="form-group">
                                                            <label>Address</label>
                                                            <textarea class="form-control" name="address" rows="3" placeholder="Enter full address"></textarea>
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Role<span class="text-danger">*</span></label>
                                                            <select class="form-control" id="role" name="role" required onchange="useraccess(this)">
                                                                <option></option>
                                                                <c:forEach items="${roles}" var="list" varStatus="theCount">
                                                                    <option style="text-transform: capitalize" value="${list.id}">${list.role}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="form-group">
                                                            <label>Facility<span class="text-danger">*</span></label>
                                                            <select class="form-control" id="facility" name="facilityId" required>
                                                                <option></option>
                                                                <c:forEach items="${facilities}" var="list" varStatus="theCount">
                                                                    <option style="text-transform: capitalize" value="${list.id}">${list.facilityName}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    <button id="submit-btn" type="submit" class="btn btn-info" style="margin-top: 100px;">Add User</button>
                                                </li>
                                            </ul>
                                        </div>
                                    </li>
                                    </form>
                                    <!-- End Messages -->
                                </ul>
                        <!-- /# card -->
                                <div class="table-responsive m-t-40">
                                    <table id="example23" class="display nowrap table table-hover table-striped table-bordered" cellspacing="0" width="100%">
                                        <thead>
                                            <tr>
                                                <th>Timestamp</th>
                                                <th>Name</th>
                                                <th>Number</th>
                                                <th>Email</th>
                                                <th>Employee Id</th>
                                                <th>Address</th>
                                                <th>Role</th>
                                                <th>Facility</th>
                                                <th>Status</th>
                                                <th>Password Reset</th>
                                                <th>Option</th>
                                            </tr>
                                        </thead>
                                        <tfoot>
                                            <tr>
                                                <th>Timestamp</th>
                                                <th>Name</th>
                                                <th>Number</th>
                                                <th>Email</th>
                                                <th>Employee Id</th>
                                                <th>Address</th>
                                                <th>Role</th>
                                                <th>Facility</th>
                                                <th>Status</th>
                                                <th>Password Reset</th>
                                                <th>Option</th>
                                            </tr>
                                        </tfoot>
                                        <tbody>
                                            <c:forEach items="${users}" var="list" varStatus="theCount">
                                                <tr style="text-align: center;">
                                                    <th>${list.registerDate}</th>
                                                    <th>${list.name}</th>
                                                    <th>${list.number}</th>
                                                    <th>${list.email}</th>
                                                    <th>${list.employeeId}</th>
                                                    <th>${list.address}</th>
                                                    <c:forEach items="${roles}" var="role" varStatus="theCount">
                                                        <c:if test="${role.id == list.role}">
                                                            <th style="text-transform: capitalize;">${role.role}</th>
                                                        </c:if>
                                                    </c:forEach>
                                                    <c:forEach items="${facilitiesall}" var="facility" varStatus="theCount">
                                                        <c:if test="${facility.id == list.facilityId}">
                                                            <th style="text-transform: capitalize;">${facility.facilityName}</th>
                                                        </c:if>
                                                    </c:forEach>        
                                                    <th style="background-color: #1976D2;"><a href="#" onclick="statusChange('${list.id}');"><span style="color: white;" id="status${list.id}">${list.status}</span></a></th>
                                                    <th><a onclick="resetUserPasswrod('${list.id}')" href="#">Reset</a></th>
                                                    <th class="optionColumn">
                                                        <a href="user-edit?id=${list.id}">
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
    
    <script src="${cp}/admin/js/lib/toastr/toastr.min.js"></script>
    <script src="${cp}/admin/js/lib/toastr/toastr.init.js"></script>
    
    <!--Custom JavaScript -->
    <script src="${cp}/admin/js/custom.min.js"></script>


    <script src="${cp}/admin/js/lib/datatables/datatables.min.js"></script>
    <script src="${cp}/admin/js/lib/datatables/cdn.datatables.net/buttons/1.2.2/js/dataTables.buttons.min.js"></script>
    <script src="${cp}/admin/js/lib/datatables/cdn.datatables.net/buttons/1.2.2/js/buttons.flash.min.js"></script>
    <script src="${cp}/admin/js/lib/datatables/cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
    <script src="${cp}/admin/js/lib/datatables/cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js"></script>
    <script src="${cp}/admin/js/lib/datatables/cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js"></script>
    <script src="${cp}/admin/js/lib/datatables/cdn.datatables.net/buttons/1.2.2/js/buttons.html5.min.js"></script>
    <script src="${cp}/admin/js/lib/datatables/cdn.datatables.net/buttons/1.2.2/js/buttons.print.min.js"></script>
    <script src="${cp}/admin/js/lib/datatables/datatables-init.js"></script>
    
    <!--Ajax-->
    <script src="${cp}/ajax/admin/user/add.user.js"></script>
    <script src="${cp}/ajax/admin/user/check.user.mobile.number.js"></script>
    <script src="${cp}/ajax/admin/user/user.status.change.js"></script>
    <script src="${cp}/ajax/admin/user/reset.user.password.js"></script>
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
    
    <script>
        function useraccess(obj){
            var value = parseInt(obj.value);
            console.log(value);
            var viewPatient = document.getElementById("viewPatient");
            var viewInvoice = document.getElementById("viewInvoice");
            var viewReport = document.getElementById("viewReport");
            var viewMedicine = document.getElementById("viewMedicine");
            var downloadyes = document.getElementById("downloadyes");
            var downloadno = document.getElementById("downloadno");
            var createyes = document.getElementById("createyes");
            var createno = document.getElementById("createno");
            var modifyyes = document.getElementById("modifyyes");
            var modifyno = document.getElementById("modifyno");
            var verifyyes = document.getElementById("verifyyes");
            var verifyno = document.getElementById("verifyno");
            var invoiceModifyyes = document.getElementById("invoiceModifyyes");
            var invoiceModifyno = document.getElementById("invoiceModifyno");
            var medicineStockUpdateyes = document.getElementById("medicineStockUpdateyes");
            var medicineStockUpdateno = document.getElementById("medicineStockUpdateno");
            var medicineSellyes = document.getElementById("medicineSellyes");
            var medicineSellno = document.getElementById("medicineSellno");
            
            switch(value) {
                case 1:
                  // Technician
                  viewPatient.checked = true;
                  viewInvoice.checked = true;
                  viewReport.checked = true;
                  viewMedicine.checked = false;
                  downloadyes.checked = true;
                  createyes.checked = true;
                  modifyno.checked = true;
                  verifyno.checked = true;
                  invoiceModifyno.checked = true;
                  medicineStockUpdateno.checked = true;
                  medicineSellno.checked = true;
                  break;
                case 2:
                  // Receptionist
                  viewPatient.checked = true;
                  viewInvoice.checked = true;
                  viewReport.checked = true;
                  viewMedicine.checked = false;
                  downloadyes.checked = true;
                  createyes.checked = true;
                  modifyno.checked = true;
                  verifyno.checked = true;
                  invoiceModifyno.checked = true;
                  medicineStockUpdateno.checked = true;
                  medicineSellno.checked = true;
                  break;
                case 3:
                  // Accountant
                  viewPatient.checked = false;
                  viewInvoice.checked = true;
                  viewReport.checked = false;
                  viewMedicine.checked = false;
                  downloadyes.checked = true;
                  createno.checked = true;
                  modifyno.checked = true;
                  verifyno.checked = true;
                  invoiceModifyyes.checked = true;
                  medicineStockUpdateno.checked = true;
                  medicineSellno.checked = true;
                  break;
                case 4:
                  // Supervisor
                  viewPatient.checked = true;
                  viewInvoice.checked = true;
                  viewReport.checked = true;
                  viewMedicine.checked = false;
                  downloadyes.checked = true;
                  createno.checked = true;
                  modifyyes.checked = true;
                  verifyno.checked = true;
                  invoiceModifyno.checked = true;
                  medicineStockUpdateno.checked = true;
                  medicineSellno.checked = true;
                  break;
                case 5:
                  // Doctor
                  viewPatient.checked = true;
                  viewInvoice.checked = true;
                  viewReport.checked = true;
                  viewMedicine.checked = false;
                  downloadyes.checked = true;
                  createno.checked = true;
                  modifyno.checked = true;
                  verifyyes.checked = true;
                  invoiceModifyno.checked = true;
                  medicineStockUpdateno.checked = true;
                  medicineSellno.checked = true;
                  break;  
                case 6:
                  // Pharmacy
                  viewPatient.checked = true;
                  viewInvoice.checked = true;
                  viewReport.checked = true;
                  viewMedicine.checked = false;
                  downloadyes.checked = true;
                  createyes.checked = true;
                  modifyno.checked = true;
                  verifyno.checked = true;
                  invoiceModifyno.checked = true;
                  medicineStockUpdateyes.checked = true;
                  medicineSellyes.checked = true;
                  break;  
                default:  
                  // code block
                  viewPatient.checked = false;
                  viewInvoice.checked = false;
                  viewReport.checked = false;
                  viewMedicine.checked = false;
                  downloadyes.checked = false;
                  downloadno.checked = false;
                  createno.checked = false;
                  createyes.checked = false;
                  modifyyes.checked = false;
                  modifyno.checked = false;
                  verifyyes.checked = false;
                  verifyno.checked = false;
                  invoiceModifyyes.checked = false;
                  invoiceModifyno.checked = false;
                  medicineStockUpdateyes.checked = false;
                  medicineStockUpdateno.checked = false;
                  medicineSellyes.checked = false;
                  medicineSellno.checked = false;
              }
        }
    </script>
</body>

</html>
