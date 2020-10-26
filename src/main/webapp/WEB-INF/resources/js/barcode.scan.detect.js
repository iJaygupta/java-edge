var key = "";
var t1 = null;
$(document).keypress(function(event){
    if(t1===null){
        t1 = $.now();
    }else{
        if($.now()-t1>200){
            key="";
        }
        t1 = $.now();
    }
    var keycode = (event.keyCode ? event.keyCode : event.which);

    if(key.length > 10 && keycode == '13'){
        //Barcode detected
        console.log(key);
        key="";
    }
    key = key + event.originalEvent.key;

});