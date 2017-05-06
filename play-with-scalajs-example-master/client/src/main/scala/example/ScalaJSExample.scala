package example

import org.scalajs.dom
import shared.SharedMessages

import scala.scalajs.js

import scala.scalajs.js.annotation.JSExportTopLevel


object ScalaJSExample extends js.JSApp {
  def pollList(): Unit = {
    println("In polling")
   
  }

  def main(): Unit = {
//    dom.document.getElementById("scalajsShoutOut").textContent = SharedMessages.itWorks
    println("Set up polling")
    scalajs.js.timers.setInterval(500)(pollList())
  }
}
