$(document).ready(function() {
    console.log("test");
    var readURL = function(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
			console.log(input.files[0].name);
            reader.onload = function (e) {
                console.log(e.target.result.length);
                if(e.target.result.length<=50000){
                    var img = document.getElementsByClassName("image")[0];
                    if(img.childNodes[0]){
                        img.removeChild(img.childNodes[0]);
                    }
                    img.innerHTML = "<img style='width: auto; height:80px;' src='"+e.target.result+"'>";
                    uploadImage(e.target.result);
                }else{
                    openWarningToastr("File is too large","Warning");
                }
            }
    
            reader.readAsDataURL(input.files[0]);
        }
    }
    

    $(".file-upload").on('change', function(){
        readURL(this);
    });
    
    $(".upload-button").on('click', function() {
       $(".file-upload").click();
    });
});

function uploadImage(data){
    showLoading();
     var formdata = data;
     $.ajax({
         url : '/admin/uploadOrganizationLogo',
         method: 'POST',
         data: {image: formdata},
         dataType    : 'json', // what type of data do we expect back from the server
         encode          : true,
         success : function(data) {
             if(data==="success"){
                 openSuccessToastr("Image uploaded successfully","Success");
             }else if(data==="Session out"){
                 window.location = "/admin";
             }else{
                 openErrorToastr(data,"Error");
             }
             hideLoading();
         }
     });    
}

function checkLogoExist(){
    var img = document.getElementsByClassName('image')[0];
    $.ajax({
       url: "/admin/checkLogoExist?key=logo/logo.txt",
       method: 'POST',
       dataType    : 'json', // what type of data do we expect back from the server
       encode          : true,
       success: function (response) {
           console.log(response);
           if(response.includes("logo.txt")){
                $.ajax({
                    url: "/admin/getOrganizationLogo?path="+response,
                    method: 'GET',
                    dataType    : 'json', // what type of data do we expect back from the server
                    encode          : true,
                    success: function (url) {
                        img.innerHTML = "<img style='width: auto; height:auto; max-height: 80px; max-width: 180px;' src='"+url+"'>";
                    }
                });
           }else if(response === "admin"){
               window.location = response;
           }else{
               openErrorToastr(response,"Error");
               if(img.childNodes[0]){
                    img.innerHTML = "";
                }
           }
        }
    });
};