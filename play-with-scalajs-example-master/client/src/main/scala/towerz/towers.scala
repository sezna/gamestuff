package firstproj
import scala.scalajs.js.JSApp
import org.scalajs.dom
import org.scalajs.dom.html
import dom.document
import dom.CanvasRenderingContext2D
import scala.scalajs.js.annotation.JSExportTopLevel
//import org.scalajs.jquery.jQuery
import scala.scalajs.js
import scala.collection.mutable.ArrayBuffer

class Tower (var team:Int, var kind:Int, var x:Double, var y:Double)   {
  var (price, color, range, power) = 
    kind match {
      case 1  => (30, "red", 100, 5) 
      case 2  => (100, "yellow", 200, 12)
      case 3  => (400, "green", 200, 20)
      case 4  => (1500, "blue", 300, 50)
    }
  
  var rotation = 0.0
  def display(ctx: CanvasRenderingContext2D) {

	ctx.save();
	ctx.translate(this.x, this.y);
	ctx.rotate(this.rotation);
    ctx.fillStyle = this.color;
    ctx.fillRect(-10, -10, 20, 20);
	ctx.restore();
    //var tower1img = document.getElementById("tower1img").asInstanceOf[html.Image];
    //var image:HTMLImageElement = new HTMLImageElement();
    // var image = dom.document.createElement("tower1gif").asInstanceOf[html.Image];
    // image.src = "tower1.gif";
    // var image = dom.document.createElement("img").asInstanceOf[html.Image];
    //  image.src = "/src/main/scala/firstproj/target/tower1.gif";
    //  ctx.drawImage(image, 200,300, 32, 32);
  }

  def shoot(e:Enemy,ctx: CanvasRenderingContext2D){

     var dx = this.x - e.x;
     var dy = this.y - e.y;

	this.rotation = Math.atan(dy/dx);

     ctx.strokeStyle = this.color;
     ctx.beginPath();
     ctx.moveTo(this.x, this.y);
     ctx.lineTo(e.x, e.y);
     ctx.closePath();

     ctx.stroke();

  }
}
