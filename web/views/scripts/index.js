/**
JavaScript functions for the home page. Assumes that jQuery 2.1.1 is loaded.

@author Chad Estioco
*/

/**
Send an AJAX call to /login to verify user credentials. If the credentials are
verified, the user should be redirected to the dashboard.
*/
function loginCheck(){
    username = $("#pynance_username")[0].value;
    password = $("#pynance_password")[0].value;
    $.ajax({
        type: "POST",
        url: "/login",
        data: {"username": username, "password":password},
        success: toDashboard,
    });
}

/**
TODO
Redirect to dashboard.
*/
function toDashboard(data, textStatus, jqXHR){
}
