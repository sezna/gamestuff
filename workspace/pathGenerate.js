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
for(let i = 1; i < turningPoints.length-1; i+=2){
	let middlePoint = {
		x: turningPoints[i+1].x,
		y: turningPoints[i].y,
		direction: 6
	};
	if(turningPoints[i+1].x -turningPoints[i].x > 0){
		turningPoints[i].direction = 3;
	} else{
		turningPoints[i].direction = 9;
	}
	turningPoints.splice(i+1,0,middlePoint);
}