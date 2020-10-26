var epcs = [];
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

    if(key.length > 5 && key.length < 25 && keycode == '13'){
        //RFID ID
        console.log(key);
        //Add EPC
        if($.inArray(key,epcs) < 0){
            epcs.push(key);
        }
        key="";
        updateEPC();
    }
    key = key + event.originalEvent.key;
});
function updateEPC(){
    //Set Quantity
    $("#quantity").val(epcs.length);
    $("#epcList").empty();
    for (i = 0; i < epcs.length; i++) {
        $("#epcList").append('<li id="epclist'+i+'"><div class="input-group input-group-rounded"><input id="epcid'+i+'" type="text" name="multipleepc" class="form-control" readonly value="'+epcs[i]+'">'+
           '<span class="input-group-btn"><button style="height: 32px; padding: 0px;" class="btn btn-primary btn-group-right" type="submit" onclick="removeEPC('+i+')"><i class="fa fa-remove"></i></button></span></div></li>');
    }
}

function removeEPC(id){
    console.log(id);
    var epc = $("#epcid"+id).val();
    console.log(epc);
    epcs.splice(id, 1);
    updateEPC();
}