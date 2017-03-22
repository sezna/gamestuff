var enemies = [];
var enemiesKilled = 0;
var maxHealth = 200;

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
		this.pathStage = 0;
	}

	// Draws the enemy.
	draw() {
		ctx.beginPath();
		ctx.fillStyle = "#000000" //In case of an invalid color
		ctx.fillStyle = "#00"+(Math.floor((this.health/maxHealth)*255)).toString(16)+"00";
		ctx.arc(this.x, this.y, this.radius, 0, 2 * Math.PI, false);
		ctx.fill();
		ctx.closePath();
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
		//pathstage not the last one
		if(this.y > turningPoints[this.pathStage+1].y){
			this.pathStage +=1;
		}
		switch(turningPoints[this.pathStage]){
			case 3:
				// Right
				this.x += this.v;
				break;
			case 6:
				// Down
				this.y += this.v;
				break;
			case 9:
				// Left
				this.x -= this.v;
				break;
			default:
				console.log("You are definitely doing something wrong");
		}
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
	maxHealth = enemyHealth;
	//constructor(team, x, y, v, color, health, reward, radius) {
	var enemyColor = 'green';
	enemy = new Enemy(2, x, 10, enemyVelocity, enemyColor, enemyHealth, enemyReward, enemyRadius);
	enemies.push(enemy);

}
