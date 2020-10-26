<%-- 
    Document   : verify-login
    Created on : 6 Nov, 2018, 2:51:29 PM
    Author     : Adeep My IT Solution Private Limited
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}/resources" scope="request" />
<%@page import="co.admis.config.ServerConfiguration" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <title>${ServerConfiguration.COMPANY_SHORT_NAME} | PIN</title>

        <!-- Favicon -->
        <link rel="shortcut icon" href="${cp}/img/icon.png">
        <link rel="icon" href="${cp}/img/icon.png" type="image/x-icon">
        <!-- vector map CSS -->
        <link href="${cp}/admin/vendors/bower_components/jquery-toast-plugin/dist/jquery.toast.min.css" rel="stylesheet" type="text/css"><!-- switchery CSS -->
        <link href="${cp}/admin/vendors/bower_components/switchery/dist/switchery.min.css" rel="stylesheet" type="text/css"/>
        <!-- Custom CSS -->
        <link href="${cp}/admin/dist/css/style.css" rel="stylesheet" type="text/css">
        <style>
            #partitioned {
            padding-left: 15px;
            letter-spacing: 42px;
            border: 0;
            background-image: linear-gradient(to left, black 70%, rgba(255, 255, 255, 0) 0%);
            background-position: bottom;
            background-size: 50px 3px;
            background-repeat: repeat-x;
            background-position-x: 35px;
            width: 350px;
            min-width:350px;
            }

            #divInner{
            left: 0;
            position: sticky;
            }

            #divOuter{
            width:300px; 
            overflow:hidden
            }
            .container-fluid{
              padding: 0px;
            }
            .mb-10{
                text-align: left;
            }
            
             #fixbutton {
                line-height: 12px;
                width: 18px;
                font-size: 8pt;
                font-family: tahoma;
                margin-top: 1px;
                margin-right: 2px;
                position:absolute;
                top:0;
                right:0;
            }
        </style>
    </head>
    <body>
        <!--Preloader-->
        <div class="preloader-it">
                <div class="la-anim-1"></div>
        </div>
        <!--/Preloader-->

        <div class="wrapper pa-0">
                <header class="sp-header">
                    <a style="float: right;" class="btn btn-danger" href="${ServerConfiguration.LOCAL_URL}/logout">Logout</a>     
                    <div class="sp-logo-wrap pull-left">
                        <a href="/admin">
                            <img class="brand-img mr-10" src="${cp}/admin/dist/img/logo.png" alt="brand"/>
                            <span class="brand-text">${ServerConfiguration.COMPANY_NAME}</span>
                        </a>
                    </div>
                    <div class="clearfix"></div>
                </header>   
                <!-- Main Content -->
                <div class="page-wrapper pa-0 ma-0 auth-page">
                    <div class="container-fluid">
                        <!-- Row -->
                        <div class="table-struct full-width full-height">
                            <div class="table-cell vertical-align-middle auth-form-wrap">
                                <div class="auth-form  ml-auto mr-auto no-float">
                                    <div class="row">
                                        <div class="col-sm-12 col-xs-12" id="panel1">
                                            <div id="messageBox"></div>
                                            <h3 class="text-center txt-dark mb-10">Enter secure pin</h3>	
                                            <div class="form-wrap">
                                                <form action="${ServerConfiguration.LOCAL_URL}/verifyPin" id="otpForm">
                                                    <div class="form-group">
                                                        <div id="divOuter">
                                                            <div id="divInner">
                                                                <input style="background-color:  #ea6c41;" class="form-control" name="pin" id="partitioned" type="text" maxlength="6" minlength="6" required/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <button type="submit" class="btn btn-success btn-flat m-b-30 m-t-30">Submit</button> 
                                                    <button onclick="resetPin()" class="btn btn-warning btn-flat m-b-30 m-t-30" style="margin-left: 115px;">Reset</button> 
                                                </form>
                                            </div>
                                        </div>	
                                        <div class="col-sm-12 col-xs-12" id="panel2" style="display: none;">
                                            <button onclick="back()" class="btn btn-primary btn-flat m-b-30 m-t-30" style="margin-bottom: 10px;">Back</button> 
                                            <div class="alert alert-info alert-dismissable">
                                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button><span id="responseMessage">Click on send OTP</span> 
                                            </div>
                                            <h3 class="text-center txt-dark mb-10">Enter received otp</h3>
                                            <div class="form-wrap">
                                                <form action="${ServerConfiguration.LOCAL_URL}/changePin" id="otpForm1">
                                                    <div class="form-group">
                                                        <div id="divOuter">
                                                            <div id="divInner">
                                                                <input style="background-color:  #ea6c41;" class="form-control" name="otp" id="partitioned" type="text" maxlength="6" minlength="6" required/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <button type="submit" class="btn btn-success btn-flat m-b-30 m-t-30">Submit</button> 
                                                    <button onclick="sendOtp()" class="btn btn-warning btn-flat m-b-30 m-t-30" style="margin-left: 88px;">Send OTP</button> 
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
        <!-- /#wrapper -->

        <!-- JavaScript -->

        <!-- jQuery -->
        <script src="${cp}/admin/vendors/bower_components/jquery/dist/jquery.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="${cp}/admin/vendors/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="${cp}/admin/vendors/bower_components/jquery-toast-plugin/dist/jquery.toast.min.js"></script>

        <!-- Slimscroll JavaScript -->
        <script src="${cp}/admin/dist/js/jquery.slimscroll.js"></script>

        <!-- Init JavaScript -->
        <script src="${cp}/admin/dist/js/toast-data.js"></script>
        <script src="${cp}/admin/dist/js/init.js"></script>
        <!--Login Ajax-->
        <script src="${cp}/ajax/master/master.otp.js"></script>

        <!--Common Methods--> 
        <script src="${cp}/ajax/common.loading.method.js"></script>
         <!--Ajax-->
        <script src="${cp}/ajax/verify.pin.js"></script>
        <script src="${cp}/ajax/send.otp.js"></script>
        <script src="${cp}/ajax/change.pin.js"></script>
        <script src="${cp}/ajax/common.loading.method.js"></script>

         <script>
            var error = '${error}';
            if(error!==null && error!==""){
                openErrorToastr(error,"Error");
            }
            var obj = document.getElementById('partitioned');
            obj.addEventListener("keydown", stopCarret); 
            obj.addEventListener("keyup", stopCarret); 

            function stopCarret() {
                    if (obj.value.length > 5){
                            setCaretPosition(obj, 5);
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
            
            function resetPin(){
                $("#panel1").hide();
                $("#panel2").show();
            }
            
            function back(){
                $("#panel2").hide();
                $("#panel1").show();
            }
        </script>
    </body>
</html>
