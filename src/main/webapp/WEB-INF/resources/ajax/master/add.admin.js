$(document).ready(function() {

    // process the form
    $('#add-admin-form').submit(function(event) {
        // get the form data
        // there are many ways to get this data using jQuery (you can use the class or id also)
        
// process the form
        $.ajax({
            type        : 'POST', // define the type of HTTP verb we want to use (POST for our form)
            url         : $('#add-admin-form').attr('action'), // the url where we want to POST
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
                    window.location.href = "/master/admin/admin-list";
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