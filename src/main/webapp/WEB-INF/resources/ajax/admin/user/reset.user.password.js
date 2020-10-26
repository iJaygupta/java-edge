function resetUserPasswrod(id){
showLoading();
$.ajax({
    url : '/admin/resetUserPassword?id='+id,
    method: 'Get',
    dataType    : 'json', // what type of data do we expect back from the server
    encode          : true,
    success : function(data) {
            if(data==="reset")
                openSuccessToastr('User password is changed','Success');
            else if(data === "Session out")
                reload();
            else
                openWarningToastr(data,'Warning');
            hideLoading();
        }
    });
}