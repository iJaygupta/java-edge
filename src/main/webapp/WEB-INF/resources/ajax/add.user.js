$(document).ready(function() {

    // process the form
    $('#add-user-form').submit(function(event) {
        // get the form data
        // there are many ways to get this data using jQuery (you can use the class or id also)
        
// process the form
        $.ajax({
            type        : 'POST', // define the type of HTTP verb we want to use (POST for our form)
            url         : $('#add-user-form').attr('action'), // the url where we want to POST
            data        : $("form").serialize(), // our data object
            dataType    : 'json', // what type of data do we expect back from the server
            encode          : true,
            beforeSend: function() { 
                $("#submit-btn").prop('disabled', true); // disable button
              }
        })
            // using the done promise callback
            .done(function(data) {
                
                $('#alert-message').empty();
                // log data to the console so we can see
                if(data === "User added"){
                    window.location.href = "/admin/user";
                }else if(data === "Session out"){
                    window.location.href = "/adminLogin";
                }else{
                    $('#alert-message').append('<div class="alert alert-warning">'+data+'</div>');  
                }  
                 $("#submit-btn").prop('disabled', false); // disable button
            });
        // stop the form from submitting the normal way and refreshing the page
        event.preventDefault();
    });

});