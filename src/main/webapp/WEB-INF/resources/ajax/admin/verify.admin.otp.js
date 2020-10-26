$("#otpForm").submit(function(e) {
    var form = $(this);
    var url = form.attr('action');
    e.preventDefault(); // avoid to execute the actual submit of the form.
    $.ajax({
        type: "POST",
        url: url,
        data: form.serialize(), // serializes the form's elements.
        dataType    : 'json', // what type of data do we expect back from the server
         encode          : true,
         beforeSend: function() { 
             $("#submit-btn").prop('disabled', true); // disable button
             showLoading();
           }
       })
        // using the done promise callback
         .done(function(data) {
            if(data==="OTP Verified"){
                window.location = "adminLogin?message=You will receive new password within 10 minutes";
            }else if(data === "Wrong OTP"){
                openErrorToastr("Otp is incorrect","Error");
                $("#btnSubmit").attr("disabled", false);
            }else{
                window.location = "admin-password-reset?message="+data;
            }
         hideLoading();
      });  
});