function doQuery(){
    var selected = window.document.getElementById('listOfCountries').value;
    var amount =  window.document.getElementById('lValue').value;
    var querystr = "country=" + selected + "&amount=" + amount;
    doAjax("Servlet", querystr, "doQuery_back", "post", 0);
}
      
function doQuery_back(result){
    window.document.getElementById("result").value = result;
}