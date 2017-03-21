var enemies = [];
var enemiesKilled = 0;
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

	// raws the enemy.
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
			enemiesKilled += 1;
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

function createEnemy() {
	var enemy;
	var x = Math.floor(Math.random() * 600);
	// gets smaller as they get stronger, to a minimum of 5. Max is 100.
	var enemyRadius = 10; // maybe make a bigger/harder boss?
	// they get faster too
	var enemyVelocity = 1 + ( enemiesKilled / 50 );
	// reward gets bigger
	var enemyReward = Math.floor(5 + ( enemiesKilled / 100 ));
	// they get stronger
	var enemyHealth = 200 + ( enemiesKilled / 10 );
	//constructor(team, x, y, v, color, health, reward, radius) {
	var enemyColor = 'green';
	enemy = new Enemy(2, x, 10, enemyVelocity, enemyColor, enemyHealth, enemyReward, enemyRadius);
	enemies.push(enemy);

}
