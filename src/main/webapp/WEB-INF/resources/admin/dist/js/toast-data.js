/*Toast Init*/

function openErrorToastr(message,heading){
    $.toast({
        heading: heading,
        text: message,
        position: 'top-right',
        loaderBg:'#e69a2a',
        icon: 'error',
        hideAfter: 5000, 
        stack: 6
    });
}

function openSuccessToastr(message,heading){
    $.toast({
        heading: heading,
        text: message,
        position: 'top-right',
        loaderBg:'#e69a2a',
        icon: 'success',
        hideAfter: 5000, 
        stack: 6
    });
}

function openWarningToastr(message,heading){
    $.toast({
        heading: heading,
        text: message,
        position: 'top-right',
        loaderBg:'#ea6c41',
        icon: 'warning',
        hideAfter: 5000, 
        stack: 6
    });
}

function openInfoToastr(message,heading){
    $.toast({
        heading: heading,
        text: message,
        position: 'top-right',
        loaderBg:'#e69a2a',
        icon: 'info',
        hideAfter: 5000, 
        stack: 6
    });
}