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
	- listBots
- bot dynamics
	- commandBot
	- ...
*/

/* Getters */
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

/* UPDATES POSITION OF ROBOT ON MAP.
	Receives information from GET, of all active bots. 
	Updates each of their locations in the view display. 

	ifMove	true -> called from eventlistener, updating
					the pos of a robot
			false -> called from manageBots(), meaning
					the inherent state of a bot has
					changed (a bot added or removed).
*/
function update(ifMove) {
	// if it is a position change
	if(ifMove) {
		$.ajax({
			method: "GET",
			url: getIP() + "/updateloc",
			data: JSON.stringify({
				//information to get.
			}),
			processData: false,
			contentType: 'application/json'
		});
	}
	// if it is an entire bot to add/remove
	else {

	}
}

/* When .dir is clicked, send motors to take some kind of action. */
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
	update(true);
});

/* Eventlistener for mouseclick on controls (adding, removing, etc.) */
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

/* Helper function called from the eventlistener */
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
	update(false);
}

function listBots(){
	// lists all the bots
}