/**
Javascript for main_app. Assumes that jQuery Datatables is loaded.

@author Chad Estioco
*/

var data = [
    ["January 2014", "888", "0"],
    ["February 2014", "888", "0"]
    ]

$(document).ready(function(){
    $("#finance_summary").DataTable({data: data});
});
