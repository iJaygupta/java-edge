 function showLoading(){
    $("#overlay").css("display", "block");
    $("#wait").css("display", "block");
}

function hideLoading(){
   $("#wait").css("display", "none");
   $("#overlay").css("display", "none");
}

function redirect(url){
   window.location = url;
}

function reload(){
   window.location.reload();
}

function wait(ms){
    var start = new Date().getTime();
    var end = start;
    while(end < start + ms) {
      end = new Date().getTime();
   }
 }