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
             $(".btn").prop('disabled', true); // disable button
           }
       })
        // using the done promise callback
         .done(function(data) {
            if(data==="passed"){
                reload();
            }else{
                openErrorToastr(data,"Error");
                $(".btn").attr("disabled", false);
            }
      });  
});