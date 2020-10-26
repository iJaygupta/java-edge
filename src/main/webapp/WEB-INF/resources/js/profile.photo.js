  $(document).ready(function() {
    var readURL = function(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                console.log(e.target.result);
                var img = document.getElementsByClassName("avatar")[0];
                if(img.childNodes[0]){
                    img.removeChild(img.childNodes[0]);
                }
                img.innerHTML = "<img style='width: auto; height:80px;' src='"+e.target.result+"'>";
                uploadImage(e.target.result);
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