function sendMail(invoiceId){
    swal({
        title: "Enter an email !!",
        type: "input",
        showCancelButton: true,
        closeOnConfirm: false,
        animation: "slide-from-top",
        inputPlaceholder: "Write customer email"
    },
    function(inputValue){
        if (inputValue === false) return false;
        if (inputValue === "" || !ValidateEmail(inputValue)) {
            console.log("test");
            swal.showInputError("You need to write correct email!");
            return false
        }
        $.ajax({
            type        : 'POST', // define the type of HTTP verb we want to use (POST for our form)
            url         : '/RFIDInventory/customer/sendInvoiceEmail', // the url where we want to POST
            data        : "invoiceId="+invoiceId+"&email="+inputValue, // our data object
            dataType    : 'json', // what type of data do we expect back from the server
            encode          : true,
            beforeSend: function() { 
                showLoading();
                $(".confirm").prop('disabled', true); // disable button
              }
        })
            // using the done promise callback
            .done(function(data) {
               
                // log data to the console so we can see
                if(data === "sent"){
                        swal("Invoice Sent", "to : " + inputValue, "success");
                }else if(data === "pin"){
                    reload();
                }else if(data === "Session out"){
                        redirect("/login");
                }else{
                    swal.showInputError(data);
                    $(".confirm").prop('disabled', false); // disable button
                }  
                    hideLoading();
            });
        // stop the form from submitting the normal way and refreshing the page
        event.preventDefault();
        
    });    
        
// process the form
        
}

function ValidateEmail(email) {
    var expr = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
    return expr.test(email);
};

function downloadInvoice(invoiceId) {
    $.ajax({
            type        : 'POST', // define the type of HTTP verb we want to use (POST for our form)
            url         : '/RFIDInventory/customer/downloadInvoice', // the url where we want to POST
            data        : "invoiceId="+invoiceId, // our data object
            dataType    : 'json', // what type of data do we expect back from the server
            encode          : true,
            beforeSend: function() { 
                showLoading();
              }
        })
            // using the done promise callback
            .done(function(data) {
               
                // log data to the console so we can see
                if(data.includes("/invoice/")){
                     window.open(data, '_blank');   
                }else if(data === "pin"){
                    reload();
                }else if(data === "Session out"){
                        redirect("/login");
                }else{
                    openErrorToastr(data,"Error");
                }  
                    hideLoading();
            });
        // stop the form from submitting the normal way and refreshing the page
        event.preventDefault();
};