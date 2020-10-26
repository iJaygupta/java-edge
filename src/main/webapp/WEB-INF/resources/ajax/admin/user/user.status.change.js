function statusChange(id){
showLoading();
$.ajax({
    url : '/admin/changeUserStatus?id='+id,
    method: 'Get',
    dataType    : 'json', // what type of data do we expect back from the server
    encode          : true,
    success : function(data) {
            document.getElementById('status'+id).innerHTML = data;
            if(data==="active")
                openSuccessToastr('User is activated','Active');
            else if(data === "disable")
                openWarningToastr('User is disabled','Disabled');
            else
                openErrorToastr(data,'Error');
            hideLoading();
        }
    });
}