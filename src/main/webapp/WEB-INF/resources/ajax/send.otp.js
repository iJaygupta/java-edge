function sendOtp(){
    // get the form data
    // there are many ways to get this data using jQuery (you can use the class or id also)
    event.preventDefault();
// process the form
    $.ajax({
        type        : 'POST', // define the type of HTTP verb we want to use (POST for our form)
        url         : "/MultiEChannel/sendPinResetOtp", // the url where we want to POST
        dataType    : 'json', // what type of data do we expect back from the server
        encode          : true,
        beforeSend: function() { 
            $(".btn").prop('disabled', true); // disable button
          }
    })
        // using the done promise callback
        .done(function(data) {
            // log data to the console so we can see
            if(data === "sent"){
                openSuccessToastr("Otp sent successfully","Success");
                $("#responseMessage").html("Enter otp received on your registred mobile number");
            }else if(data === "Session out"){
                redirect("/adminLogin");
            }else{
                openErrorToastr(data,"Error");
                $("#responseMessage").html(data);
            }  
            $(".btn").prop('disabled', false); // disable button
        });
    // stop the form from submitting the normal way and refreshing the page

}