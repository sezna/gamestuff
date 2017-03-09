class Tower {
	constructor(team, x, y, laserColor, towerPic, health) {
		this.team = team;
		this.x = x;
		this.y = y;
		this.laserColor = laserColor;
		this.towerPic = towerPic;
		this.health = health;
		this.alive = true;
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
