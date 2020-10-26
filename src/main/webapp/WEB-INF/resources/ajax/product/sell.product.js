$(document).ready(function() {

    // process the form
    $('#sell-product-form').submit(function(event) {
        // get the form data
        // there are many ways to get this data using jQuery (you can use the class or id also)
        event.preventDefault();
      swal({
        title: "Enter an amount received!!",
        text: "Collect the amount "+totalEffectivePriceSum.toFixed(2)+" Rs. before generating bill !!",
        type: "input",
        showCancelButton: true,
        closeOnConfirm: false,
        animation: "slide-from-top",
        inputPlaceholder: "2000",
        showLoaderOnConfirm: true
    },
    function(inputValue){
        if (inputValue === false) return false;
        if (inputValue === "" || !ValidatePrice(inputValue)) {
            swal.showInputError("You need to collect amount equal or more then "+totalEffectivePriceSum.toFixed(2)+" Rs.");
            return false
        }  
// process the form
        $.ajax({
            type        : 'POST', // define the type of HTTP verb we want to use (POST for our form)
            url         : $('#sell-product-form').attr('action'), // the url where we want to POST
            data        : $("form").serialize()+"&epcs="+epc_list, // our data object
            dataType    : 'json', // what type of data do we expect back from the server
            encode          : true,
            beforeSend: function() { 
                showLoading();
                $("#submit-btn").prop('disabled', true); // disable button
              }
        })
            // using the done promise callback
            .done(function(data) {
               
                // log data to the console so we can see
                if(!isNaN(data)){
//                        redirect("/RFIDInventory/product/invoice-record-list");
                    swal({
                        title: "Invoice generated",
                        text: "Please return the balance amount "+(inputValue-totalEffectivePriceSum).toFixed(2)+" Rs.",
                        type: "success",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "Yes, view invoice !!",
                        cancelButtonText: "No, cancel it !!",
                        closeOnConfirm: false,
                        closeOnCancel: true
                    },
                    function(isConfirm){
                        if (isConfirm) {
                            downloadInvoice(data);
                        }
                        else {
                            reload();
                        }
                    });
                    
                }else if(data === "pin"){
                    alert("Enter pin");
                }else if(data === "Session out"){
                        redirect("/login");
                }else{
                    swal.showInputError(data);
                    $(".confirm").prop('disabled', false); // disable button
                    $("#submit-btn").prop('disabled', false); // disable button
                }  
                    hideLoading();
            });
        // stop the form from submitting the normal way and refreshing the page
        event.preventDefault();
    });
});
});

function ValidatePrice(price){
    if(price>=totalEffectivePriceSum){
        return true;
    }
    return false;
}