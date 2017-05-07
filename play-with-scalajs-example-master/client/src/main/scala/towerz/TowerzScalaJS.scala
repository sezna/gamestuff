package firstproj
import scala.scalajs.js.JSApp
import org.scalajs.dom
import org.scalajs.dom.html
import dom.document
import scala.scalajs.js.annotation.JSExportTopLevel
//import org.scalajs.jquery.jQuery
import scala.scalajs.js
import scala.collection.mutable.ArrayBuffer
object Towerz extends JSApp {
  def main() { 
    var body = document.getElementById("body").asInstanceOf[html.Body];
    var canvas = document.createElement("canvas").asInstanceOf[html.Canvas];
    var ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D];
    var towers = scala.collection.mutable.ArrayBuffer.empty[Tower];
    var enemies = scala.collection.mutable.ArrayBuffer.empty[Enemy]
    body.appendChild(canvas);
    canvas.width = 640;
    canvas.height = 640;
    canvas.onmousedown = {
      (e: dom.MouseEvent) =>
        val rect =
          canvas.getBoundingClientRect()
        towers.append( new Tower(1,3,e.clientX - rect.left,e.clientY - rect.top));
    }
    def main(){
      dom.window.setInterval(advance _, 50);
    }
    def advance() {
      towers.foreach((tow: Tower) => tow.display());
    }
    class Tower (team:Int, kind:Int, x:Double, y:Double)   {
      var (price, color, range, power) = 
      kind match {
          case 1  => (30, "red", 100, 5) 
          case 2  => (100, "yellow", 200, 12)
          case 3  => (400, "green", 200, 20)
          case 4  => (1500, "blue", 300, 50)
        }
        def display() {
        ctx.fillStyle = this.color;
        ctx.fillRect(x, y, 20, 20);
        //var tower1img = document.getElementById("tower1img").asInstanceOf[html.Image];
        //var image:HTMLImageElement = new HTMLImageElement();
        // var image = dom.document.createElement("tower1gif").asInstanceOf[html.Image];
        // image.src = "tower1.gif";
        // var image = dom.document.createElement("img").asInstanceOf[html.Image];
        //  image.src = "/src/main/scala/firstproj/target/tower1.gif";
        //  ctx.drawImage(image, 200,300, 32, 32);
      }
    }
  }	
}
