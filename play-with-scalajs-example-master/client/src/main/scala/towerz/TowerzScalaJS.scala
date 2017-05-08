package firstproj
import scala.scalajs.js.JSApp
import org.scalajs.dom
import dom.CanvasRenderingContext2D
import org.scalajs.dom.html
import dom.document
import scala.scalajs.js.annotation.JSExportTopLevel
//import org.scalajs.jquery.jQuery
import scala.scalajs.js
import scala.collection.mutable.ArrayBuffer
//@JSExportTopLevel("Towerz")
object Towerz extends JSApp {
   
	var paused = false

    var body = document.getElementById("body").asInstanceOf[html.Body]
    var canvas = document.getElementById("canvas").asInstanceOf[html.Canvas]
    var ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    var towers = scala.collection.mutable.ArrayBuffer.empty[Tower]
    var enemies = scala.collection.mutable.ArrayBuffer.empty[Enemy]
    var stats = document.getElementById("Stats").asInstanceOf[html.Paragraph]



    var tower1img = document.getElementById("tower1img").asInstanceOf[html.Image]

//var image = dom.document.createElement("img").asInstanceOf[html.Image]
//image.src = "/users/shough/WebApps/game/gamestuff/play-with-scalajs-example-master/server/app/views/tower1.gif"

	var e = new Enemy(1,1,1,1,"a",1,1,1,null)
	var euros = 100;
	var health = 30;
	var score = 3;
	
	val rando = new scala.util.Random()

   // body.appendChild(canvas)
    canvas.width = 640;
    canvas.height = 640;
    canvas.onmousedown = {

      (e: dom.MouseEvent) =>
        val rect = canvas.getBoundingClientRect()
	var newTow =  new Tower(1,currentTowerType,e.clientX - rect.left,e.clientY - rect.top);

	var data1 = ctx.getImageData(newTow.x-15, newTow.y-15, 1, 1).data;
	var data2 = ctx.getImageData(newTow.x-15, newTow.y+15, 1, 1).data;
	var data3 = ctx.getImageData(newTow.x+15, newTow.y-15, 1, 1).data;
	var data4 = ctx.getImageData(newTow.x+15, newTow.y+15, 1, 1).data;

	if(data1(0) == 51 && data1(1) == 153 && data1(2) == 102 &&
	   data2(0) == 51 && data2(1) == 153 && data2(2) == 102 &&
	   data3(0) == 51 && data3(1) == 153 && data3(2) == 102 &&
	   data4(0) == 51 && data4(1) == 153 && data4(2) == 102)  {

	if(newTow.price <= euros){
		        towers.append(newTow);
		euros -= newTow.price;
	}

	}

    }


	var currentTowerType = 1;

//var button1 = document.createElement("button").asInstanceOf[html.Button]
//body.appendChild(button1)

/*var tower1 =  document.getElementById("tower1").asInstanceOf[html.Button]
tower1.onclick = {
//chooseTowerType(3)
currentTowerType = 3
}*/

@JSExportTopLevel("chooseTowerType")
def chooseTowerType(kind:Int) {
	
		currentTowerType = kind
		
	}

def main() {
    dom.window.setInterval(advance _, 50);
    }

    def advance() {

	if(health < 0) {
		paused = true
ctx.fillStyle="black";
ctx.fillRect(320-130,320-40,260,80);
		ctx.font="40px Verdana";
// Create gradient
var gradient=ctx.createLinearGradient(0,0,1200,0);
gradient.addColorStop(0,"magenta");
gradient.addColorStop(0.5,"blue");
gradient.addColorStop(1.0,"red");
// Fill with gradient
ctx.fillStyle=gradient;
ctx.fillText("GAME OVER",205,330);
	}

	if(!paused) {

	print("Advancing")
	stats.innerHTML = "Health:" + health + "      Euros:" + euros + "     Score:"+score;
	if(rando.nextInt(100) < 30) {
	var newEnemy = e.createEnemy()
	enemies.append(newEnemy)
	}

	//Move & Garbage Collect phase

      enemies.foreach((e: Enemy) => e.move())
	var x = 0;
      for(x <- enemies.length-1 to 0 by -1){
	 if(enemies(x).health <= 0){
          enemies -= enemies(x);
	  score += 30;
	  euros +=5;
        } 
      }

      for(x <- enemies.length-1 to 0 by -1){
	 if(enemies(x).arrived(canvas)){
          enemies -= enemies(x);
	  health -= 1;
        } 
      }


      // Draw stuff

	//ctx.drawImage(image, 100,100, 32, 32);
	ctx.fillStyle = "#339966"
	ctx.fillRect(0,0,640,640)

	var path = e.turningPoints;
	
	var i = 0;


		// ctx.lineCap="square";
		 ctx.lineCap="round";
      	for(i <- 0 to path.length-2 by 1){
		var currentPoint = path(i)
		var nextPoint = path(i+1)
		 
		 ctx.lineWidth = 25;
		 ctx.lineCap="round";
		 ctx.strokeStyle = "#cccccc";
    		 ctx.beginPath();
    		 ctx.moveTo(currentPoint.x, currentPoint.y);
    		 ctx.lineTo(nextPoint.x, nextPoint.y);
    		 ctx.closePath();

    		 ctx.stroke(); 
		
		 ctx.beginPath();
    		 ctx.moveTo(currentPoint.x, currentPoint.y);
    		 ctx.lineTo(currentPoint.x, currentPoint.y);
    		 ctx.closePath();

    		 ctx.stroke();
		
		// ctx.fillStyle = "yellow"
    		// ctx.fillRect(currentPoint.x, currentPoint.y,10,10);
	        	 
	} 

	//Drawing initial/final points on path

		 ctx.beginPath();
    		 ctx.moveTo(path(0).x, 0);
    		 ctx.lineTo(path(0).x, path(0).y);
    		 ctx.closePath();
    		 ctx.stroke();

		 ctx.beginPath();
    		 ctx.moveTo(path(path.length-1).x, 640);
    		 ctx.lineTo(path(path.length-1).x, path(path.length-1).y);
    		 ctx.closePath();
    		 ctx.stroke();

		 ctx.lineWidth = 1;

      
      enemies.foreach((e: Enemy) => e.draw(ctx))

	towers.foreach((tow: Tower) => shootHighestPriorityEnemy(tow))

      towers.foreach((tow: Tower) => tow.display(ctx))
	}
  }

	def inRangeEnemies(tow:Tower):ArrayBuffer[Enemy]= {

		var enemiesInRange = scala.collection.mutable.ArrayBuffer.empty[Enemy]
		enemies.foreach { (e: Enemy) =>

			if (e.x > tow.x - tow.range && e.x < tow.x + tow.range) {
				if (e.y > tow.y - tow.range && e.y < tow.y + tow.range) {
					enemiesInRange.append(e)	
				}
			}
			
		}
		return enemiesInRange

	}

	def shootHighestPriorityEnemy(tow:Tower) {

		var enemiesInRange = inRangeEnemies(tow)
		if(enemiesInRange != null && enemiesInRange.length != 0) {

		var lowestHealth = enemiesInRange(0)

		enemiesInRange.foreach { (e: Enemy) =>
			if (e.health < lowestHealth.health) {
				lowestHealth = e
			}
		}

		lowestHealth.health -= tow.power;
		tow.shoot(lowestHealth,ctx);
		}

	}





} 

