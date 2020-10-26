$(document).ready(function() {

    // process the form
    $('#admin-otp-form').submit(function(event) {
        // get the form data
        // there are many ways to get this data using jQuery (you can use the class or id also)
        event.preventDefault();
// process the form
        $.ajax({
            type        : 'POST', // define the type of HTTP verb we want to use (POST for our form)
            url         : $('#admin-otp-form').attr('action'), // the url where we want to POST
            data        : $("form").serialize(), // our data object
            dataType    : 'json', // what type of data do we expect back from the server
            encode          : true,
            beforeSend: function() { 
                $("#submit-btn").prop('disabled', true); // disable button
                showLoading();
              }
        })
            // using the done promise callback
            .done(function(data) {
                
                $('#alert-message').empty();
                // log data to the console so we can see
                if(data === "sent"){
                    redirect("/admin-verify-otp");
                }else if(data === "Session out"){
                    redirect("/adminLogin");
                }else{
                    openErrorToastr(data,"Error");
                    $("#submit-btn").prop('disabled', false); // disable button
                }  
                hideLoading();
            });
        // stop the form from submitting the normal way and refreshing the page
        
    });

});