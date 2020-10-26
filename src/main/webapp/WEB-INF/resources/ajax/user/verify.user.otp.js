$(document).ready(function() {

    // process the form
    $('#otpForm').submit(function(event) {
        // get the form data
        // there are many ways to get this data using jQuery (you can use the class or id also)
        
// process the form
        $.ajax({
            type        : 'POST', // define the type of HTTP verb we want to use (POST for our form)
            url         : $('#otpForm').attr('action'), // the url where we want to POST
            data        : $("form").serialize(), // our data object
            dataType    : 'json', // what type of data do we expect back from the server
            encode          : true,
            beforeSend: function() { 
                $("#btnSubmit").prop('disabled', true); // disable button
                showLoading();
              }
        })
            // using the done promise callback
            .done(function(data) {
                
                if(data==="OTP Verified"){
                   window.location = "userLogin?message=You will receive new password within 10 minutes";
               }else if(data === "Wrong OTP"){
                   openErrorToastr(data,"Error");
                   $("#btnSubmit").attr("disabled", false);
               }else{
                   window.location = "user-password-reset?message="+data;
               }
                hideLoading();
            });
        // stop the form from submitting the normal way and refreshing the page
        event.preventDefault();
    });

});