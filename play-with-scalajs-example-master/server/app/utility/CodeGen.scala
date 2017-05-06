package utility

object CodeGen extends App {
  slick.codegen.SourceCodeGenerator.main(
    Array("slick.driver.MySQLDriver", "com.mysql.jdbc.Driver", 
        "jdbc:mysql://localhost/gamestuff?user=gamestuff&password=GetBackToWork", 
        "/users/jyang/webApps/play-scala/app/", "models", "gamestuff", "GetBackToWork")
  )
}