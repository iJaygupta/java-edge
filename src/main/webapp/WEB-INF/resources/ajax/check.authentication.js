function checkAuth() {
    // stop the form from submitting the normal way and refreshing the page
    event.preventDefault();
    // process the form
    $.ajax({
        type        : 'POST', // define the type of HTTP verb we want to use (POST for our form)
        url         : "/admin/authenticateUser", // the url where we want to POST
        dataType    : 'json', // what type of data do we expect back from the server
        encode          : true
    })
        // using the done promise callback
        .done(function(data) {
            // log data to the console so we can see
            if(data === "passed"){
                
            }else if(data === "pin"){
                
            }else {
                window.location.href = "/index";
            }
             
        });
};

