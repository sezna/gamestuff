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
object Towerz extends JSApp {
   
    var body = document.getElementById("body").asInstanceOf[html.Body]
    var canvas = document.createElement("canvas").asInstanceOf[html.Canvas]
    var ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    var towers = scala.collection.mutable.ArrayBuffer.empty[Tower]
    var enemies = scala.collection.mutable.ArrayBuffer.empty[Enemy]
	var e = new Enemy(1,1,1,1,"a",1,1,1,null)


	val rando = new scala.util.Random

    body.appendChild(canvas)
    canvas.width = 640;
    canvas.height = 640;
    canvas.onmousedown = {
      (e: dom.MouseEvent) =>
        val rect = canvas.getBoundingClientRect()
        towers.append( new Tower(1,3,e.clientX - rect.left,e.clientY - rect.top))
    }

def main() {
    dom.window.setInterval(advance _, 50);
    }

    def advance() {
	print("Advancing")
	if(rando.nextInt(100) < 7) {
	var newEnemy = e.createEnemy()
	enemies.append(newEnemy)
	}

	//Move & Garbage Collect phase

      enemies.foreach((e: Enemy) => e.move())
	var x = 0;
      for(x <- enemies.length-1 to 0 by -1){
	 if(enemies(x).health <= 0){
          enemies -= enemies(x);
        } 
      }


      // Draw stuff

	ctx.fillStyle = "#339966"
	ctx.fillRect(0,0,640,640)
      towers.foreach((tow: Tower) => tow.display(ctx))
      
      enemies.foreach((e: Enemy) => e.draw(ctx))

	towers.foreach((tow: Tower) => shootHighestPriorityEnemy(tow))
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
		if(enemiesInRange != null || enemiesInRange.length != 0) {

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

