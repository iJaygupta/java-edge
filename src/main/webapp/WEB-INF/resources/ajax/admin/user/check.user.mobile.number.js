function checkMobileNumber(e){
    var number = e.target.value;
    var phoneno = /^\d{10}$/;
    if(number.length===10 && number.match(phoneno)){
        $.ajax({
        url : 'checkMobileNumber?number='+number,
        method: 'Get',
        dataType    : 'json', // what type of data do we expect back from the server
        encode          : true,
        success : function(data) {
                if(data==="Number is available"){
                    document.getElementById('numText').style.color="green";
                    document.getElementById('numText').innerHTML = data;
                    document.getElementById('submit-btn').disabled=false;
                }else if(data === "Number is already registred"){
                    document.getElementById('numText').style.color="red";
                    document.getElementById('numText').innerHTML = data;
                    document.getElementById('submit-btn').disabled=true;
                }else if(data === "Session out"){
                    reload();
                }else{
                    openErrorToastr(data,"Error");
                }
            }
        });
    }else{
        document.getElementById('numText').style.color="red";
        document.getElementById('numText').innerHTML = "Wrong input value";
        document.getElementById('submit-btn').disabled=true;
    }
}
