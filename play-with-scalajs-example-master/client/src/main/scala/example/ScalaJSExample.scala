package example

import org.scalajs.dom
import shared.SharedMessages

import scala.scalajs.js

import scala.scalajs.js.annotation.JSExportTopLevel


object ScalaJSExample extends js.JSApp {
   def main(): Unit = {
    println("Set up polling")
    shoutOut();
    scalajs.js.timers.setInterval(500)(pollList())
    /*jQuery("#button").click((event: Any) => {
      drawRandom()
    })*/
  }
  
  def drawRandom(): Unit = {
    //jQuery("#random").load("/random")
  }

  def pollList(): Unit = {
    //jQuery("#students").load("/list")
  }

  @JSExportTopLevel("shoutOut")
  def shoutOut(): Unit = {
    dom.document.getElementById("scalajsShoutOut").textContent = SharedMessages.itWorks
  }
}
