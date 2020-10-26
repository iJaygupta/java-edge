$(document).ready(function() {

    // process the form
    $('#user-password-form').submit(function(event) {
        // get the form data
        // there are many ways to get this data using jQuery (you can use the class or id also)
        event.preventDefault();
// process the form
        $.ajax({
            type        : 'POST', // define the type of HTTP verb we want to use (POST for our form)
            url         : $('#user-password-form').attr('action'), // the url where we want to POST
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
                if(data === "Success"){
                    document.getElementById("user-password-form").reset();
                    openSuccessToastr("Password changed successfully","Success");
                }else if(data === "Session out"){
                    redirect("/adminLogin");
                }else{
                    openErrorToastr(data,"Error");
                }  
                hideLoading();
                $("#submit-btn").prop('disabled', false); // disable button
            });
        // stop the form from submitting the normal way and refreshing the page
        
    });

});