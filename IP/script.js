function del(x){
    document.getElementById("display").value=x.substring(0,x.length-1);
}

function compute(){
    var x=document.getElementById("display").value;
    var y=eval(x);
    document.getElementById("display").value=y;
}