package models
package repo

import java.util.UUID
import javax.inject.{ Singleton, Inject }
import scala.concurrent.{ Future, ExecutionContext }
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.PostgresProfile
import domain.User
import dtos.UserCreate

@Singleton
class UserRepo @Inject()
(dbcp: DatabaseConfigProvider)
(using ExecutionContext) {

  private val dbConfig = dbcp.get[PostgresProfile]
  import dbConfig._
  import profile.api._

  private final class Users(t: Tag) extends Table[User](t, "USERS") {
    def id = column[UUID]("ID", O.PrimaryKey)
    def username = column[String]("EMAIL", O.Length(50), O.PrimaryKey)
    def name = column[String]("NAME", O.Length(255))
    def password = column[String]("PASSWORD", O.Length(50))
    def * = (id, username, name, password).mapTo[User]
  }

  private object Table extends TableQuery[Users](new Users(_))

  def create(user: User): Future[Int] = db.run(Table += user)

  def get(user: UserCreate): Future[Option[User]] = db.run(
    Table
      .filter(u => u.username === user.username && u.password === user.password)
      .result
      .headOption
  )
}