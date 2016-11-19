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
function getIP(){
	return $("#ip").val();
}

function getPort(){
	return $("#port").val();
}

function getPower(){
	return $("#power").val() * 3;
}

function getBotID() {
    return $("#botlist").val();
}

function sendMotors(fl, fr, bl, br) {
	$.ajax({
		method: "POST",
		url: "/commandBot",
		data: JSON.stringify({
			botID: getBotID(),
			fl: fl,
			fr: fr,
			bl: bl,
			br: br
		}),
		processData: false,
		contentType: 'application/json'
	});
}

/* UPDATES POSITION OF ROBOT ON VISION MAP.
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
	//update(true);
});

/* Eventlistener for mouseclick on controls (adding, removing, etc.) */
$(".controls").click(function(event) {
	event.preventDefault();

	console.log("clicked");
	if($(event.target).is("#removeBot")){
		console.log("#removeBot has been clicked.");
		manageBots("/removeBot", $("#id").val(), $("#port").val());
	}
});

$('#addBot').click(function() {
    $.ajax({
    		method: "POST",
    		url: '/addBot',
    		dataType: 'json',
    		data: JSON.stringify({
    			ip: getIP(),
    			port: (getPort() || 10000)
    		}),
    		contentType: 'application/json',
    		success: function addSuccess(data) {
                updateDropdown(data,data);
    		}
    });
});

// $("#add").submit(function(e) {
// 	console.log("update has been called");
// 	e.preventDefault();

function doThing() {
	updateDropdown("BOT 3", "1");
	return false;
}


// 	var option = document.createElement("option");
// 	option.text = "PLEASE WORK FOR THE LOVE OF GOD";
// 	document.getElementById("botlist").add(option);
// });

/*
	For any update to the list of active bots, the dropdown menu
	of active bots will update accordingly (depending on the addition
	or removal of a bot).
*/
function updateDropdown(text, val) {
    var opt = document.createElement('option');
    opt.text = text;
    opt.value = val;
    var botlist = document.getElementById("botlist");
	botlist.appendChild(opt);
}

/* Helper function called from the eventlistener
*/
function manageBots(option, ip, port){
	$.ajax({
		method: "POST",
		url: getIP() + option,
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