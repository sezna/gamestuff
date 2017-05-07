package controllers
import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.i18n.I18nSupport
import play.api.i18n.MessagesApi
import scala.concurrent.Future
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import slick.dbio.DBIOAction
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import models._
import models.Tables._
import slick.driver.MySQLDriver.api._

case class UserData(name: String, password: String)

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class UserController @Inject()(val messagesApi: MessagesApi, dbConfigProvider: DatabaseConfigProvider) extends Controller with I18nSupport {
  
  val dbConfig = dbConfigProvider.get[JdbcProfile]
  
  val userForm = Form(
    mapping(
      "name" -> text,
      "password" -> nonEmptyText
    )(UserData.apply)(UserData.unapply)
  )
  
  var username = ""
  
  def goGameTest = Action{
    Ok(views.html.game2)
  } 


  def index = Action {
    Ok(views.html.index(userForm))
  }
  
  def goGame() = Action.async{ implicit request =>
    val dataf = dbConfig.db.run(Users.filter(d => d.username === username).result)
    dataf.map(data => Ok(views.html.game(username, data)))
    
  }
  
  
  def setUser() = Action.async { implicit request =>
    userForm.bindFromRequest.fold(
        formWithErrors => {
          Future(BadRequest(views.html.index(formWithErrors)))
        },
        userData => {
          val dataf = dbConfig.db.run(Users.filter(d => d.username === userData.name && d.password === userData.password).result)
          dataf.map(data => {
            if(data.length == 1) {
              username = userData.name
              Redirect("/game")
            } else{Ok("Invalid Username or Password")}
          })
        }
    )
  }
  
  def addUser() = Action.async{implicit request =>
    userForm.bindFromRequest.fold(
      formWithErrors => {Future(Ok("Not signed up!"))},
      userData => {
        val insertActions = DBIO.seq(
          Users += UsersRow(userData.name, userData.password)
        )
        username = userData.name
        val dataf = dbConfig.db.run(insertActions)
        dataf.map(data => Redirect("/game"))
      }
    )
    
  }
  
}