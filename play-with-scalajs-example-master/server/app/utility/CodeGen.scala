package utility

object CodeGen extends App {
  slick.codegen.SourceCodeGenerator.main(
    Array("slick.driver.MySQLDriver", "com.mysql.jdbc.Driver", 
        "jdbc:mysql://localhost/gamestuff?user=gamestuff&password=GetBackToWork", 
        "/users/jyang/Local/HTML-Documents/WebApps/gamestuff/play-with-scalajs-example-master/server/app/", "models", "gamestuff", "GetBackToWork")
  )
      
}