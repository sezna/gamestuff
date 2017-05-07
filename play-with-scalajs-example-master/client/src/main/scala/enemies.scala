package firstproj


import scala.scalajs.js.JSApp
import org.scalajs.dom
import org.scalajs.dom.html
import dom.document
import scala.scalajs.js.annotation.JSExportTopLevel
import dom.html.Canvas
import dom.CanvasRenderingContext2D
import scala.scalajs.js
import scala.collection.mutable.ArrayBuffer



class Enemy(team:Int, var x:Int, var y:Int, var v:Int, var color:String, var health:Int, var reward:Int, var radius:Int, var tps:ArrayBuffer[pathSegment]){
  var pathStage = 0;
  var turningPoints = tps;
  if(tps == null){
    turningPoints = makePath(640, 640);
  }

  var alive = true;
  var maxHealth = 200;
  var enemiesKilled = 0;

  def draw(ctx: CanvasRenderingContext2D){
    ctx.beginPath();
    ctx.fillStyle = "blue";
    ctx.fillStyle = "#00"+(Math.floor((this.health/maxHealth)*255)).toString()+"00";
    //ctx.arc(this.x,this.y,this.radius,0,2*Math.PI,false);
    ctx.fillRect(x,y,radius,radius);
    ctx.fill();
    ctx.closePath();
    print("Drawn");

  }

  def makePath(width: Int, height: Int):ArrayBuffer[pathSegment] = {
    var turningPointNum = 8;
    var countDown = turningPointNum;
    var tp = new ArrayBuffer[pathSegment](0);

    while(countDown > 0){
      var thisPoint = new pathSegment((Math.random()*width).toInt,(Math.random()*width).toInt,6);
      countDown -= 1;
      tp += thisPoint;
    }

    if(tp.length > 0){
    	tp.sortBy(t => t.y);
    }

    var firstPoint = new pathSegment(tp(0).x,tp(0).y,tp(0).direction);
    tp.insert(0,firstPoint);

    for(var i = 1; i < tp.length-1; i+=2){
      var midPoint = new pathSegment(tp(i+1).x,tp(i).y,6);
      if(tp(i+1).x-tp(i).x > 0){
        tp(i).direction = 3;
      } else{
        tp(i).direction = 9;
      }
      tp.inset(i+1,midPoint);
    }


    return tp;

   
  }

  def damage(d:Int){
    this.health = health - x;
    if(this.health <= 0){
      this.alive = false;
      enemiesKilled += 1;
    }

  }

  def arrived(canvas: Canvas ):Boolean = {
    if(team == 1){
      if (this.y > canvas.height - this.radius) {
        return true;
      }
      else {
        return false;
      }
      } else {
        if(team == 2){
          if(this.y > 0 - this.radius){
            return true;
          } else return false;
        }
        return false;
      }

  }

  def move(){
    var dir = turningPoints(pathStage).direction 
    dir match{
      case 3 =>{ //Right
        this.x += this.v; 
        if(this.x > turningPoints(this.pathStage+1).x){this.pathStage+=1;}
      }
      case 6 =>{ // down
        this.y += this.v;
        if(this.pathStage < (turningPoints.length-1) && this.y > turningPoints(this.pathStage+1).y){
          this.pathStage += 1;
        }
      }
      case 9 => {
        this.x -= this.v;
        if(this.x < turningPoints(this.pathStage+1).x){
          this.pathStage += 1;
        }
      }

      case default => println("You are doing something wrong");

    }

  }

  def createEnemy():Enemy = {

    var x = turningPoints(0).x
    var enemyRadius = 10;
    var eTeam = team;
    var enemyVel = 2 + (enemiesKilled / 90);
    if(enemyVel > 20){
      enemyVel = 20;
    }

    var enemyReward = (5 + (enemiesKilled/50));
    var enemyHealth = 300 + enemiesKilled/2;
    maxHealth = enemyHealth;

    var enemyColor = "green";
    var enemy = new Enemy(eTeam,x,0,enemyVel,enemyColor,enemyHealth,enemyReward,enemyRadius,turningPoints);
    return enemy;
    //enemies.push(enemy);
  }


}

case class pathSegment(var x:Int, var y:Int, var direction:Int){

}
