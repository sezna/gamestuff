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
        val rect = canvas.getBoundingClientRect()
        towers.append( new Tower(1,3,e.clientX - rect.left,e.clientY - rect.top));
    }
    def main(){
      dom.window.setInterval(advance _, 50);
    }
    def advance() {
      enemies.foreach((e: Enemy) => e.move())

      // Draw stuff
      towers.foreach((tow: Tower) => tow.display(ctx))
      enemies.foreach((e: Enemy) => e.draw(ctx))
  }
}
} 

