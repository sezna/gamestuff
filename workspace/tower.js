var towers = [];
//TBD
var towerType = -1;


class Tower {
	constructor(team, x, y, laserColor, color, health, width, height, range, power) {
		this.team = team;
		this.x = x;
		this.y = y;
		this.laserColor = laserColor;
		this.color = color;
		this.health = health;
		this.alive = true;
		this.width = width;
		this.height = height;
		this.range = range;
		this.power = power;
	}
	draw() {
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
	inRangeEnemies() {
		// TODO decide if we want this to be a circle range
		var enemiesInRange = [];
		for ( var i = 0; i < enemies.length; i++) {
			if (enemies[i].x > this.x - this.range && enemies[i].x < this.x + this.range) {
				console.log("x bound satisfied");
				if (enemies[i].y > this.y - this.range && enemies[i].y < this.y + this.range) {
					enemiesInRange.push(enemies[i]);	
				}
			}
		}
		return enemiesInRange;
	}
	shoot(enemy) {
		ctx.beginPath();
		ctx.moveTo(this.x + this.width / 2, this.y + this.height / 2);
		ctx.lineTo(enemy.x, enemy.y);
		ctx.strokeStyle = this.laserColor;
		ctx.stroke();
		enemy.damage(this.power);
	}
}
function buildTower(pos) {
	var tower;

	switch (towerType) {
		// TODO actually pick a location
		// this is for mary, make this function take in a location
		// that is from a mouse click
		// TODO decide on tower types
		case 1:
			tower = new Tower(1, pos.x, pos.y, 'red', 'red', 100, 10, 10, 100, 5);	
			break; 
		case 2:
			tower = new Tower(1, pos.x, pos.y, 'blue', 'blue', 100, 10, 10, 200, 7);
			break;
		default:
			console.log("invalid tower type");
	}
	towers.push(tower);
}

function chooseTowerType(num){
	towerType = num;
}

