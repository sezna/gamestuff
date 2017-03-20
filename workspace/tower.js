var towers = [];

class Tower {
	constructor(team, x, y, laserColor, color, health, width, height) {
		this.team = team;
		this.x = x;
		this.y = y;
		this.laserColor = laserColor;
		this.color = color;
		this.health = health;
		this.alive = true;
		this.width = width;
		this.height = height;
	}
	draw() {
		console.log("drawing tower");
		ctx.beginPath();
		ctx.fillStyle = this.color;
		ctx.fillRect(this.x, this.y, this.width, this.height);
	}
	// Removes health from the tower
	damage(x) {
		this.health = this.health - x;
		if ( this.health <= 0 ) {
			this.alive = false;
		}
	}
}
function buildTower(type) {
	var tower;
	switch (type) {
		// TODO actually pick a location
		// this is for you mary, make this function take in a location
		// that is from a mouse click
		// TODO decide on tower types
		case 1:
			tower = new Tower(1, 0, 0, 'red', 'red', 100, 10, 10);
			break;
		case 2:
			tower = new Tower(1, 0, 0, 'blue', 'blue', 100, 10, 10);
			break;
		default:
			console.log("invalid tower type");
	}
	towers.push(tower);
}
