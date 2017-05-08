package models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Games.schema ++ Users.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Games
   *  @param gameid Database column gameID SqlType(INT), AutoInc, PrimaryKey
   *  @param username Database column username SqlType(VARCHAR), Length(255,true), Default(None) */
  case class GamesRow(gameid: Int, username: Option[String] = None)
  /** GetResult implicit for fetching GamesRow objects using plain SQL queries */
  implicit def GetResultGamesRow(implicit e0: GR[Int], e1: GR[Option[String]]): GR[GamesRow] = GR{
    prs => import prs._
    GamesRow.tupled((<<[Int], <<?[String]))
  }
  /** Table description of table games. Objects of this class serve as prototypes for rows in queries. */
  class Games(_tableTag: Tag) extends Table[GamesRow](_tableTag, "games") {
    def * = (gameid, username) <> (GamesRow.tupled, GamesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(gameid), username).shaped.<>({r=>import r._; _1.map(_=> GamesRow.tupled((_1.get, _2)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column gameID SqlType(INT), AutoInc, PrimaryKey */
    val gameid: Rep[Int] = column[Int]("gameID", O.AutoInc, O.PrimaryKey)
    /** Database column username SqlType(VARCHAR), Length(255,true), Default(None) */
    val username: Rep[Option[String]] = column[Option[String]]("username", O.Length(255,varying=true), O.Default(None))
  }
  /** Collection-like TableQuery object for table Games */
  lazy val Games = new TableQuery(tag => new Games(tag))

  /** Entity class storing rows of table Users
   *  @param username Database column username SqlType(VARCHAR), PrimaryKey, Length(50,true)
   *  @param password Database column password SqlType(VARCHAR), Length(50,true)
   *  @param highscore1 Database column highScore1 SqlType(INT), Default(None)
   *  @param highscore2 Database column highScore2 SqlType(INT), Default(None)
   *  @param highscore3 Database column highScore3 SqlType(INT), Default(None)
   *  @param wins Database column wins SqlType(INT), Default(Some(0))
   *  @param losses Database column losses SqlType(INT), Default(Some(0)) */
  case class UsersRow(username: String, password: String, highscore1: Option[Int] = None, highscore2: Option[Int] = None, highscore3: Option[Int] = None, wins: Option[Int] = Some(0), losses: Option[Int] = Some(0))
  /** GetResult implicit for fetching UsersRow objects using plain SQL queries */
  implicit def GetResultUsersRow(implicit e0: GR[String], e1: GR[Option[Int]]): GR[UsersRow] = GR{
    prs => import prs._
    UsersRow.tupled((<<[String], <<[String], <<?[Int], <<?[Int], <<?[Int], <<?[Int], <<?[Int]))
  }
  /** Table description of table users. Objects of this class serve as prototypes for rows in queries. */
  class Users(_tableTag: Tag) extends Table[UsersRow](_tableTag, "users") {
    def * = (username, password, highscore1, highscore2, highscore3, wins, losses) <> (UsersRow.tupled, UsersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(username), Rep.Some(password), highscore1, highscore2, highscore3, wins, losses).shaped.<>({r=>import r._; _1.map(_=> UsersRow.tupled((_1.get, _2.get, _3, _4, _5, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column username SqlType(VARCHAR), PrimaryKey, Length(50,true) */
    val username: Rep[String] = column[String]("username", O.PrimaryKey, O.Length(50,varying=true))
    /** Database column password SqlType(VARCHAR), Length(50,true) */
    val password: Rep[String] = column[String]("password", O.Length(50,varying=true))
    /** Database column highScore1 SqlType(INT), Default(None) */
    val highscore1: Rep[Option[Int]] = column[Option[Int]]("highScore1", O.Default(None))
    /** Database column highScore2 SqlType(INT), Default(None) */
    val highscore2: Rep[Option[Int]] = column[Option[Int]]("highScore2", O.Default(None))
    /** Database column highScore3 SqlType(INT), Default(None) */
    val highscore3: Rep[Option[Int]] = column[Option[Int]]("highScore3", O.Default(None))
    /** Database column wins SqlType(INT), Default(Some(0)) */
    val wins: Rep[Option[Int]] = column[Option[Int]]("wins", O.Default(Some(0)))
    /** Database column losses SqlType(INT), Default(Some(0)) */
    val losses: Rep[Option[Int]] = column[Option[Int]]("losses", O.Default(Some(0)))
  }
  /** Collection-like TableQuery object for table Users */
  lazy val Users = new TableQuery(tag => new Users(tag))
}
