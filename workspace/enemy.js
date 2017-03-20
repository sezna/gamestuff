var enemies = [];
class Enemy {
	constructor(team, x, y, v, color, health, reward, radius) {
		this.team = team;
		this.x = x;
		this.y = y;
		this.v = v;
		this.color = color;
		this.health = health;
		this.alive = true;
		this.reward = reward;
		this.radius = radius;
	}

	// Draws the enemy.
	draw() {
		ctx.beginPath();
		ctx.fillStyle = this.color;
		ctx.arc(this.x, this.y, this.radius, 0, 2 * Math.PI, false);
		ctx.fill();
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
		this.y = this.y + this.v;
	}

}
function createEnemy(type) {
	var enemy;
	switch (type) {
		// TODO determine algorithm for enemy type choice
		// TODO determine where they spawn
		// TODO decide on enemy types
		case 1:
			enemy = new Enemy(2, 10, 10, 2, 'red', 100, 25, 20);
			break;
		default:
			console.log("invalid enemy type");
	}
	enemies.push(enemy);

}
