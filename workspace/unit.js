var enemies = [];
class Enemy {
	constructor(team, x, y, v, color, health, reward) {
		this.team = team;
		this.x = x;
		this.y = y;
		this.v = v;
		this.color = color;
		this.health = health;
		this.alive = true;
		this.reward = reward;
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
	arrived() {
		if (this.y > 600) {
			return true;
		}
		else {
			return false;
		}
	}
	move() {
		this.y = this.y + v;
	}

}
