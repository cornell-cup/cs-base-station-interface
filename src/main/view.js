/* 
pixi - for grid
*/

//import interface;

var grid = PIXI.autoDetectRenderer(520, 520);
document.body.appendChild(grid.view);
var stage = new PIXI.Container();

grid.view.style.border = "1px dashed black";
grid.view.style.position = "absolute";
grid.view.style.display = "block";

var bots = [];

/* Pseudo code; will work on.

parseLocations(getLocations()); (getting locations from backend, converting them into
	understandable code)

for(each active bot) {
	bots.push(bot);
} only when initializing.

*/

// pseudo-constructor for a bot object
function newBot(x, y, o, ip) {
	var bot = {
		x: x,
		y: y,
		or: o, // 0..3
		ip: ip
	};
	return bot;
}

/* Setting up a single modbot at (x, y) 
	where (0,0) is top left */
function drawBot(b) {
	var circle = new PIXI.Graphics();
	circle.beginFill(0x0EB530);
	circle.drawCircle(0, 0, 25);
	circle.endFill();

	circle.beginFill(0xB0252E);
	if (b.or===1) { circle.drawCircle(10, 0, 10); } // right facing
	else if(b.or===2) { circle.drawCircle(0, 10, 10); } // down facing 
	else if(b.or===3) { circle.drawCircle(-10, 0, 10); } // left facing
	else { circle.drawCircle(0, -10, 10); } // up facing (default)
	circle.endFill();

	circle.x = b.x*40;
	circle.y = b.y*40;
	stage.addChild(circle);
}

function displayBots(botArray) {
	for(var b in botArray) {
		drawBot(botArray[b]);
	}
}

bots.push(newBot(1,1,0,"234"));
bots.push(newBot(2,5,1,"5234"));
bots.push(newBot(4,7,2,"wer"));

console.log(bots);
displayBots(bots);


/* setting up grid lines */
var lines_y = [];
var lines_x = [];

for(var i=0; i<25; i=i+1){
	lines_y[i] = new PIXI.Graphics();
	lines_y[i].lineStyle(1, 0xFFFFFF, 1);
	lines_y[i].moveTo(0,i*20);
	lines_y[i].lineTo(520,i*20);
	lines_y[i].x = 0; lines_y[i].y = i*20;
	stage.addChild(lines_y[i]);

	lines_x[i] = new PIXI.Graphics();
	lines_x[i].lineStyle(1, 0xFFFFFF, 1);
	lines_x[i].moveTo(i*20,0);
	lines_x[i].lineTo(i*20,520);
	lines_x[i].x = i*20; lines_x[i].y = 0;
	stage.addChild(lines_x[i]);
}

grid.render(stage);