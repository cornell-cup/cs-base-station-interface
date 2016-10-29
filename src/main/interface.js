/*

INTERFACE.

Pages and functions:
- getters
	- get ID
	- get IP
	- get power
- bot configs
	- addBot
	- removeBot
	- forgetBot
	- listBots
- bot dynamics
	- commandBot
	- ...

*/

/* getters */
function getBotID(){
	return $("#id").val();
}

function getIP(){
	return $("#ip").val();
}

function getPower(){
	return $("power").val();
}

function sendMotors(fl, fr, bl, br) {
	$.ajax({
		method: "POST",
		url: getIP() + "/commandBot",
		data: JSON.stringify({
			botID: getBotID(),
			fl: fl,
			fr: fr,
			bl: bl,
			br: br
		}),
		processData: false,
		contentType: 'applicaion/json'
	});
}

$(".dir").click(function(event) {
	var pow = getPower();
	var target = $(event.target);

	if(target.is("#fwd")) {
		sendMotors(pow, pow, pow, pow);
	}
	else if(target.is("#bck")) {
		sendMotors(-pow, -pow, -pow, -pow);
	}
	else if(target.is("#lft")) {
		sendMotors(-pow, pow, pow, -pow);
	}
	else if(target.is("#rt")) {
		sendMotors(pow, -pow, -pow, pow);
	}
	else if(target.is("#cw")){
		sendMotors(pow, -pow, pow, -pow);
	}
	else if(target.is("#ccw")){
		sendMotors(-pow, pow, -pow, pow);
	}
	else if(target.is("#stop")){
		sendMotors(0,0,0,0);
	}
	else {
		console.log("Clicked on a direction button but nothing has been executed.");
	}
});

$(".controls").click(function() {
	console.log("clicked");
	if(this.is("#addBot")){
		console.log("#addBot has been clicked.");
		manageBots(1, $("#id").val(), $("#port").val());
	}
	else if(this.is("#removeBot")){
		console.log("#removeBot has been clicked.");
		manageBots(-1, $("#id").val(), $("#port").val());
	}
});

function manageBots(option, ip, port){
	var mthd = "";

	if(option > 0){ // add bot
		mthd = "/addBot";
	}
	else if(option < 0){
		mthd = "/removeBot";
	}

	$.ajax({
		method: "POST",
		url: getIP() + mthd,
		data: JSON.stringify({
			ip: ip,
			port: port
		}),
		processData: false,
		contentType: 'application/json'
	});
}

function listBots(){
	// lists all the bots
}