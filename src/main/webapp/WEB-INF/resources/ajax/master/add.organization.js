$(document).ready(function() {
    // process the form
    $('#add-organization-form').submit(function(event) {
// process the form
        $.ajax({
            type        : 'POST', // define the type of HTTP verb we want to use (POST for our form)
            url         : $('#add-organization-form').attr('action'), // the url where we want to POST
            data        : $("form").serialize(), // our data object
            dataType    : 'json', // what type of data do we expect back from the server
            encode          : true,
            beforeSend: function() { 
                 $(".btn").prop('disabled', true); // disable button
              }
        })
            // using the done promise callback
            .done(function(data) {
                // log data to the console so we can see
                if(data === "added"){
                    window.location.href = "/master/organization/organization-list";
                }else if(data === "Session out"){
                    reload();
                }else{
                    openErrorToastr(data,"Error");
                    $(".btn").prop('disabled', false); // disable button
                }   
            });
        // stop the form from submitting the normal way and refreshing the page
        event.preventDefault();
    });

});

function resetForm(){
    openInfoToastr("Reset","Form reset successfully");
}