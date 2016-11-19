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

function displayBots(botArray) {
	for(var b in botArray) {
		drawBot(botArray[b]);
	}
}

displayBots(bots);

function setupGridLines() {
    /* setting up grid lines */
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