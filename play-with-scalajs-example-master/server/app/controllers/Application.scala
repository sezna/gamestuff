package controllers

import play.api.mvc._
import shared.SharedMessages
import models._

class Application extends Controller {

  def index = Action {
    Ok(views.html.index(SharedMessages.itWorks))
  }
  
  def studentRegistration() = Action {
    Ok(views.html.studentReg())
  }

  def addStudent(name: String) = Action {
    Student.addStudent(name)
    Ok("See something")
  }
}