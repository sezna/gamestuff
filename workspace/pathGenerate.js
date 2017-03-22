var turningPointNum = 8;

var turningPoints = [];


var countdown = turningPointNum;
// Randomly Generate Points
while(countdown >0){
	var thispoint = {
		x: Math.floor(Math.random()*canvas.width),
		y: Math.floor(Math.random()*canvas.width),
		direction: 6
	};
	turningPoints.push(thispoint);
	countdown--;
}

// Sort Based on y value
if(turningPoints.length >0){
	turningPoints.sort(function(a,b){
		return (a.y - b.y);
	});
}
// First Point 
var firstPoint = {
	x:turningPoints[0].x,
	y:turningPoints[0].y,
	direction: turningPoints[0].direction
};
turningPoints.splice(0,0,firstPoint);
// Directions: 12-Up, 6-Down, 9-Left, 3-Right
// Give Left/Right Directions for Every Other Point
for(var i = 1; i < turningPoints.length-1; ++i){
	if(turningPoints[i-1].direction == 6){
		if(turningPoints[i+1].x -turningPoints[i].x > 0){
			turningPoints[i].direction = 3;
		} else{
			turningPoints[i].direction = 9;
		}
	}
}
	
