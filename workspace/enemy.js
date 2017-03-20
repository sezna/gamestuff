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

	// Determines if the enemy has arrived at your base.
	arrived() {
		if (this.y > canvas.height - this.radius) {
			return true;
		}
		else {
			return false;
		}
	}

	// Moves the enemy's y pos by its velocity.
	move() {
		this.y = this.y + this.v;
	}

}

function createEnemy(type) {
	var enemy;
	switch (type) {
		// TODO determine algorithm for enemy type choice
		// TODO determine where they spawn - right now it is just random
		// TODO decide on enemy types
		case 1:
			var x = Math.floor(Math.random() * 600);
			enemy = new Enemy(2, x, 10, 2, 'red', 100, 25, 20);
			break;
		default:
			console.log("invalid enemy type");
	}
	enemies.push(enemy);

}
