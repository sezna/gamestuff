var towers = [];
//TBD
var towerType = 1;
var towerRange = 100;

class Tower {
	constructor(price, team, x, y, laserColor, color, health, width, height, range, power) {
		this.price = price;
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

	// Finds all of the enemies in range and returns an array of them.
	inRangeEnemies() {
		// TODO decide if we want this to be a circle range
		var enemiesInRange = [];
		for ( var i = 0; i < enemies.length; i++) {
			if (enemies[i].x > this.x - this.range && enemies[i].x < this.x + this.range) {
				if (enemies[i].y > this.y - this.range && enemies[i].y < this.y + this.range) {
					enemiesInRange.push(enemies[i]);	
				}
			}
		}
		return enemiesInRange;
	}
	
	// Gets the prioritized enemy (the one closest to the bottom of the screen).
	shootHighestPriorityEnemy() {
		var enemiesInRange = this.inRangeEnemies();
		if (enemiesInRange.length === 0) {
			return null;
		}
		var highestY = enemiesInRange[0];
		for ( var i = 0; i < enemiesInRange.length; i++) {
			if (enemiesInRange[i].y > highestY.y) {
				highestY = enemiesInRange[i];
			}
		}
		this.shoot(highestY);
	}

	// Draws a laser to the enemy and damages it.
	shoot(enemy) {
		ctx.beginPath();
		ctx.moveTo(this.x + this.width / 2, this.y + this.height / 2);
		ctx.lineTo(enemy.x, enemy.y);
		ctx.moveTo(this.x + this.width / 3, this.y + this.height / 3);
		ctx.lineTo(enemy.x, enemy.y);
		ctx.moveTo(this.x + this.width / 4, this.y + this.height / 4);
		ctx.lineTo(enemy.x, enemy.y);
		ctx.moveTo(this.x + this.width / 5, this.y + this.height / 5);
		ctx.lineTo(enemy.x, enemy.y);
		ctx.moveTo(this.x + this.width / 6, this.y + this.height / 6);
		ctx.lineTo(enemy.x, enemy.y);
		ctx.strokeStyle = this.laserColor;
		ctx.stroke();

		enemy.damage(this.power);
	}
}

// Builds a tower and adds it to the array of towers.
function buildTower(pos) {
	var tower;
	switch (towerType) {
		// TODO decide on tower types
		case 1:
			tower = new Tower(10, 1, pos.x, pos.y, 'red', 'red', 100, 10, 10, 100, 5);	
			break; 
		case 2:
			tower = new Tower(20, 1, pos.x, pos.y, 'blue', 'blue', 100, 10, 10, 200, 7);
			break;
		default:
			console.log("invalid tower type");
	}
	if (currentMoney > tower.price) {
		currentMoney -= tower.price;
		towers.push(tower);
	}
	else console.log("not enough money!");
}

function chooseTowerType(num){
	towerType = num;
	// this totally sucks but we have to match the tower range with the tower type here.
	switch (towerType) {
		case 1:
			towerRange = 100;
			break;
		case 2:
			towerRange = 200;
			break;
		default: 
			towerRange = 100;
	}
}


function handleMouseHover(event) {
	var rect = canvas.getBoundingClientRect();
	cursorPosition = { x: event.clientX - rect.left, y: event.clientY - rect.top };
}
