function printDiv(divName) {
    var printContents = document.getElementById(divName).innerHTML;

    var w = window.open();

    $(w.document.body).html(printContents);
    w.print();
}   

function printDivWithName(divName, fileName) {
    var printContents = document.getElementById(divName).innerHTML;
    var originalContents = document.body.innerHTML;

    document.body.innerHTML = printContents;
    
    document.title = fileName;

    window.print();

    document.body.innerHTML = originalContents;
}   