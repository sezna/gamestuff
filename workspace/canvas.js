var canvas = document.getElementById("canvas");
var ctx = canvas.getContext("2d");


//ctx.fillStyle = "#ffb261";

//ctx.fillRect(0, 580, 640, 60);
// Setting up stats

var currentMoney = 100;
var currentHealth = 20;
var currentWave = 0;


setInterval(drawGame, 100);
setInterval(advance, 100);

// Listen for mouse click all the time
canvas.addEventListener("click", getTowerPos,false);

// Main function for game logic
function advance() {
	
	// Check for dead enemies
	for (var i = 0, j = enemies.length; i < j; i++) {
		// you don't need to check this because when an enemy or tower
		// is damaged, it checks and sets the alive status.
		if (enemies[i].alive === false) {
			currentMoney += enemies[i].reward;
			enemies.splice(i, 1);
			i--;
			j--;
		}
	}

	// Check for dead towers
	for (var i = 0, j = towers.length; i < j; i++) {
		if (towers[i].alive === false) {
			towers.splice(i, 1);
			i--;
			j--;
		}
	}
	
	// Check if enemies have entered the base yet
	for (var i = 0, j = enemies.length; i < j; i++) {
		if (enemies[i].arrived()) {
			currentHealth -= 1;
			enemies.splice(i, 1);
			i--;
			j--;
		}
	}
	
	// Move all enemies forward in their own directions
	for (var i = 0; i < enemies.length; i++) {
		enemies[i].move();
	}

	// Shoot the highest priority enemy.
	for (var i = 0; i < towers.length; i++) {
		towers[i].shootHighestPriorityEnemy();
	}

	// Spawn new enemies.
	if (Math.random() < .1) {
		createEnemy();
	}

}
// Main function for drawing game logic
function drawGame() {
	// Clear the previous frame.
	ctx.clearRect(0, 0, canvas.width, canvas.height);

	// Draw towers.
	for (var i = 0; i < towers.length; i++) {
		towers[i].draw();
	}

	// Draw enemies.
	for (var i = 0; i < enemies.length; i++) {
		enemies[i].draw();
	}

	// Draw money.
	document.getElementById("money").innerHTML = currentMoney;

	// Draw health.
	document.getElementById("health").innerHTML = currentHealth;

	// Draw enemies killed.
	document.getElementById("killed").innerHTML = enemiesKilled;
}

//ctx.fillRect(0, 0, 640, 640);
function getTowerPos(evt){
	var rect = canvas.getBoundingClientRect();
	var	pos = {x: evt.clientX - rect.left, y: evt.clientY - rect.top};
	buildTower(pos)
}
