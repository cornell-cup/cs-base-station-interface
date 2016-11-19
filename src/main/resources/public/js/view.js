/* 
pixi - for grid
*/

var grid = PIXI.autoDetectRenderer(520, 520);
document.body.appendChild(grid.view);
var stage = new PIXI.Container();
var botContainer = new PIXI.Container();
var gridContainer = new PIXI.Container();

stage.addChild(botContainer);
stage.addChild(gridContainer);

grid.view.style.border = "1px dashed black";
grid.view.style.position = "absolute";
grid.view.style.display = "block";

var bots = [];
bots.push(newBot(1,1,0,"bob"));

// pseudo-constructor for a bot object
function newBot(x, y, angle, id) {
	var bot = {
		x: x,
		y: y,
		angle: angle, // radians
		id: id
	};

	return bot;
}

/* Setting up a single modbot at (x, y) 
	where (0,0) is top left */
function drawBot(b) {
    // TODO: Responsive field rendering
	var circle = new PIXI.Graphics();
	circle.beginFill(0x0EB530);
	circle.drawCircle(0, 0, 25);
	circle.endFill();

	circle.x = b.x*40;
	circle.y = b.y*40;
	botContainer.addChild(circle);
}

/* Displays all bots given an array of bots */
function displayBots(botArray) {
	for(var b in botArray) {
		drawBot(botArray[b]);
	}
}

displayBots(bots);


/*
	Sets up grid lines within view.
	- 13 x 13 grid
	- x:[0, 12], y:[0, 12]
	- 
*/
function setupGridLines() {
    var lines_y = [];
    var lines_x = [];

    for(var i=0; i<25; i=i+1){
        lines_y[i] = new PIXI.Graphics();
        lines_y[i].lineStyle(1, 0xFFFFFF, 1);
        lines_y[i].moveTo(0,i*20);
        lines_y[i].lineTo(520,i*20);
        lines_y[i].x = 0; lines_y[i].y = i*20;
        gridContainer.addChild(lines_y[i]);

        lines_x[i] = new PIXI.Graphics();
        lines_x[i].lineStyle(1, 0xFFFFFF, 1);
        lines_x[i].moveTo(i*20,0);
        lines_x[i].lineTo(i*20,520);
        lines_x[i].x = i*20; lines_x[i].y = 0;
        gridContainer.addChild(lines_x[i]);
    }
}

setupGridLines();
grid.render(stage);


/*
	Updating location of bots on grid.
*/
const TIME_PER_UPDATE = 120; // ms

function getNewVisionData() {
    $.ajax({
        url: '/updateloc',
        type: 'GET',
        dataType: 'json',
        success: function visionDataGot(data) {
            bots = [];
            botContainer.removeChildren();
            for (var b in data) {
                var bot = data[b];
                bots.push(newBot(bot.x,bot.y,bot.angle,bot.id));
            }

            displayBots(bots);
            grid.render(stage);
        }
    });
}

setInterval(getNewVisionData, TIME_PER_UPDATE);

/*
	Zoom-out function to make all active bots visible on grid.
*/
$("#zoom_out").click(function() {
if(bots.length!==0) {

	var botmin_x = bots[0].x;
	var botmin_y = bots[0].y;
	var botmax_x = bots[0].x;
	var botmax_y = bots[0].y;

	for each (var b in bots) {
		botmin_x = Math.min(botmin_x, b[0].x);
		botmin_y = Math.min(botmin_y, b[0].y);
		botmax_x = Math.max(botmax_x, b[0].x);
		botmax_y = Math.max(botmax_y, b[0].y);
	}

	/*  TODO: rescale bot locations on grid so that they are to scale 
		within x:[botmin_x, botmax_x] and y: [botmin_y, botmax_y]
	*/
	var xrange = botmax_x - botmin_x;
	var yrange = botmax_y - botmin_y;

	zoombots = [];
	for each (var b in bots) {
		zoombots.push(newBot(
			(b.x - botmin_x)+(b.x-botmin_x)*xrange, // x pos
			(b.y - botmin_y)+(b.y-botmin_y)*yrange, // y pos
			b.angle, // angle
			b.id// id
		);
	}

	botContainer.removeChildren();
	displayBots(zoombots);
}
});