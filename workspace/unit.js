class Unit {
	constructor(team, x, y, v, unitPic, health) {
		this.team = team;
		this.x = x;
		this.y = y;
		this.v = v;
		this.unitPic = unitPic;
		this.health = health;
		this.alive = true;
	}

	// getters
	get health() {
		return this.health;
	}

	// Removes health from the unit
	damage(x) {
		this.health = this.health - x;
		if ( this.health <= 0 ) {
			this.alive = false;
		}
	}

}
