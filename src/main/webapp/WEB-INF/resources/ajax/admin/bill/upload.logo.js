$(document).ready(function() {
    // process the form
    $('#submit-btn-logo').click(function(event) {
        // get the form data
        // there are many ways to get this data using jQuery (you can use the class or id also)

        event.preventDefault();
        var form = $('#upload-logo')[0];	
        // Create an FormData object 
        var data = new FormData(form);
        
        // process the form
        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: $('#upload-logo').attr('action'),
            data: data,
            processData: false,
            contentType: false, // this
            cache: false,
            dataType    : 'json',
            timeout: 600000,
            beforeSend: function() { 
                $("#submit-btn-logo").prop('disabled', true); // disable button
                showLoading();
              }
        })
            // using the done promise callback
            .done(function(data) {
                $('#alert-message').empty();
                if(data === "success"){
                    window.location.reload();
                }else if(data === "Session out"){
                    window.location.href = "/admin";
                }else{
                    openErrorToastr(data,"Error");
                }  
                 $("#submit-btn-logo").prop('disabled', false); // disable button
                hideLoading();
            });
        // stop the form from submitting the normal way and refreshing the page
       
    });

});