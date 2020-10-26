<%@page import="java.security.SecureRandom"%>
<%@page import="java.net.URLEncoder"%>
<%
/*
   This is the sample Checkout Page JSP script. It can be directly used for integration with CCAvenue if your application is developed in JSP. You need to simply change the variables to match your variables as well as insert routines (if any) for handling a successful or unsuccessful transaction.
*/
%>
<%@ page import = "java.io.*,java.util.*,com.ccavenue.security.*" %>
<html>
<head>
	<title>Sub-merchant checkout page</title>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <%! 
            private static final String MERCHANT_ID = "132040";
            private static final String CURRENCY = "INR";
            private static final String REDIRECT_URL = "https://waytm.in/ccavResponseHandler";        
            private static final String CANCEL_URL = "https://waytm.in/ccavResponseHandler";
            private static final String LANGUAGE = "EN";    
            private static final String INTEGRATION_TYPE = "iframe_normal"; 
            private static final String ACCESS_CODE = "AVPI82FL21AU47IPUA";
            private static final String WORKING_KEY = "4624190E6327A9232DC88AF25E4B4D7C";
            private static final String VAULT = "Y";
        %>
</head>
<body>
	<%
            Date date = new Date();
            String tid = String.valueOf(date.getTime());
            String number = request.getAttribute("number").toString();
            String orderId = request.getAttribute("orderId").toString();
            System.out.println("id: "+orderId);
             // Get Amount and customer id from user
            String amount = request.getAttribute("amount").toString();
            String ccaRequest = "tid="+URLEncoder.encode(tid,"UTF-8")+"&"+
                    "order_id="+URLEncoder.encode(orderId,"UTF-8")+"&"+
                    "merchant_id="+URLEncoder.encode(MERCHANT_ID, "UTF-8")+"&"+
                    "currency="+URLEncoder.encode(CURRENCY, "UTF-8")+"&"+
                    "redirect_url="+URLEncoder.encode(REDIRECT_URL, "UTF-8")+"&"+
                    "cancel_url="+URLEncoder.encode(CANCEL_URL, "UTF-8")+"&"+
                    "language="+URLEncoder.encode(LANGUAGE, "UTF-8")+"&"+
                    "integration_type="+URLEncoder.encode(INTEGRATION_TYPE, "UTF-8")+"&"+
                    "amount="+URLEncoder.encode(amount, "UTF-8")+"&"+
                    "vault="+URLEncoder.encode(VAULT, "UTF-8")+"&"+
                    "customer_identifier="+URLEncoder.encode(number, "UTF-8");
            
         AesCryptUtil aesUtil=new AesCryptUtil(WORKING_KEY);
	 String encRequest = aesUtil.encrypt(ccaRequest);
	%>
	
	<form id="nonseamless" method="post" name="redirect" action="https://secure.ccavenue.com/transaction.do?command=initiateTransaction"/> 
		<input type="hidden" id="encRequest" name="encRequest" value="<%= encRequest %>">
		<input type="hidden" name="access_code" id="access_code" value="<%= ACCESS_CODE %>">
		<script language='javascript'>document.redirect.submit();</script>
	</form>
	
 </body> 
</html>
