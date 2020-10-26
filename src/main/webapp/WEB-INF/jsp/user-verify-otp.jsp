<%-- 
    Document   : user-verify-otp
    Created on : 6 Nov, 2018, 6:12:40 PM
    Author     : Adeep My IT Solution Private Limited
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

    <title>ADLIS | VERIFY OTP</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="${cp}/img/icon.png">
    <!-- Bootstrap Core CSS -->
    <link href="${cp}/admin/css/lib/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="${cp}/admin/css/lib/toastr/toastr.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="${cp}/admin/css/helper.css" rel="stylesheet">
    <link href="${cp}/admin/css/style.css" rel="stylesheet">
    <style>
        #partitioned {
        padding-left: 15px;
        letter-spacing: 40px;
        border: 0;
        background-image: linear-gradient(to left, black 70%, rgba(255, 255, 255, 0) 0%);
        background-position: bottom;
        background-size: 50px 1px;
        background-repeat: repeat-x;
        background-position-x: 35px;
        width: 220px;
        min-width:220px;
      }

      #divInner{
        left: 0;
        position: sticky;
      }

      #divOuter{
        width:190px; 
        overflow:hidden
      }
    </style>
</head>

<body class="fix-header fix-sidebar">
    <div id='overlay'></div>
    <!-- Preloader - style you can find in spinners.css -->
    <div class="preloader">
        <svg class="circular" viewBox="25 25 50 50">
			<circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10" /> </svg>
    </div>
    <!-- Main wrapper  -->
    <div id="main-wrapper">

        <div class="unix-login">
            <div class="container-fluid">
                <div class="row justify-content-center">
                    <div class="col-lg-4">
                        <div class="login-content card">
                            <div class="login-form">
                                <h4>Reset User Password</h4>
                                <span id="message" style="color: #26DAD2;">${param.message}</span>
                                <hr>
                                <form action="/verifyUserOTP" id="otpForm">
                                    <div class="form-group">
                                        <label>Enter OTP</label>
                                        <div id="divOuter">
                                            <div id="divInner">
                                                <input name="otp" id="partitioned" type="text" maxlength="4" minlength="4" required/>
                                            </div>
                                        </div>
                                    </div>
                                    <button id="btnSubmit" type="submit" class="btn btn-primary btn-flat m-b-30 m-t-30">Submit</button>
                                </form>
                            </div>   
                        </div>
                    </div>
                </div>
            </div>
        </div>                           
    </div>
                                        
        <!--loading-->
        <div id="wait" style="display:none;width:69px;height:89px;border:1px solid black;position:absolute;top:50%;left:50%;padding:2px;"><img src='${cp}/img/lab-loading.gif' width="64" height="64" /><br>Loading..</div>
        <!--/.loading-->     
    <!-- End Wrapper -->
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
    
    <!--Login Ajax-->
    <script src="${cp}/ajax/user/verify.user.otp.js"></script>
    <script src="${cp}/ajax/common.loading.method.js"></script>

     <script>
        var obj = document.getElementById('partitioned');
        obj.addEventListener("keydown", stopCarret); 
        obj.addEventListener("keyup", stopCarret); 

        function stopCarret() {
                if (obj.value.length > 3){
                        setCaretPosition(obj, 3);
                }
        }

        function setCaretPosition(elem, caretPos) {
            if(elem != null) {
                if(elem.createTextRange) {
                    var range = elem.createTextRange();
                    range.move('character', caretPos);
                    range.select();
                }
                else {
                    if(elem.selectionStart) {
                        elem.focus();
                        elem.setSelectionRange(caretPos, caretPos);
                    }
                    else
                        elem.focus();
                }
            }
        }
    </script>
</body>

</html>
