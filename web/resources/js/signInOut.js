/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global gapi */

function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
    });
};



function onSignIn(googleUser) {
    // Useful data for your client-side scripts:
    var profile = googleUser.getBasicProfile();
    console.log("ID: " + profile.getId()); // Don't send this directly to your server!
    console.log("Name: " + profile.getName());
    console.log("Image URL: " + profile.getImageUrl());
    console.log("Email: " + profile.getEmail());
 
    // The ID token you need to pass to your backend:
    var id_token = googleUser.getAuthResponse().id_token;
    var access_token = googleUser.getAuthResponse().access_token;
    console.log("ID Token: " + id_token);

    $("#nombre").attr("value", profile.getName());
    $("#imagen").attr("value", profile.getImageUrl());
    $("#email").attr("value", profile.getEmail());
    $("#token").attr("value", id_token); 
    $("#ac_token").attr("value", access_token);
    $(".enviarGoogle").click();
};


