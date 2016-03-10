/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.onload = init;
var socket = new WebSocket("ws://192.168.1.141:8080/SpringMongoJSFInftelScrum/actions/" + document.getElementById("projectid").innerHTML);
socket.onmessage = onMessage;
var pretimemessage = "<li class=\"right clearfix\"><span class=\"chat-img pull-right\"> </span> <div class=\"chat-body clearfix\"> <div class=\"header\"> <small class=\" text-muted\"><span class=\"glyphicon glyphicon-time\"></span>";
var prenamemessage = "</small> <strong class=\"pull-right primary-font\">";
var premessage = "</strong> </div> <p>";
var postmessage = "</p> </div> </li>";
$('.chat').animate({scrollTop: 9999999});
function onMessage(event) {

    var mes = JSON.parse(event.data);

    for (var i in mes) {
        $("#newmessage").append(pretimemessage + mes[i].fecha + prenamemessage + mes[i].nombre + premessage + mes[i].mensaje + postmessage);
    }
    
    scrollDown();
}

function sendMessage() {
    var myjsonmessage = {
        nombre: document.getElementById("userName").innerHTML,
        mensaje: document.getElementById("btn-input").value,
        fecha: new Date().toGMTString()

    };
    socket.send(JSON.stringify(myjsonmessage));
    document.getElementById("btn-input").value = "";
}

function scrollDown() {
    var height = 0;
    $('li').each(function (i, value) {
        height += parseInt($(this).height());
    });

    height += '';

    $('#chat').animate({scrollTop: height});
}

function init() {

}

