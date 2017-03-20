var towers = [];

class Tower {
	constructor(team, x, y, laserColor, towerColor, health, width, height) {
		this.team = team;
		this.x = x;
		this.y = y;
		this.laserColor = laserColor;
		this.towerColor = towerColor;
		this.health = health;
		this.alive = true;
		this.width = width;
		this.height = height;
	}
	draw() {
		ctx.beginPath();
		ctx.fillStyle = this.color;
		ctx.fillRect(this.x, this.y, width, height);
	}
	// getters
	get health() {
		return this.health;
	}

	// Removes health from the tower
	damage(x) {
		this.health = this.health - x;
		if ( this.health <= 0 ) {
			this.alive = false;
		}
	}
}
buildTower(type) {
	switch (type) {
		case 1: 
			
	}
}
