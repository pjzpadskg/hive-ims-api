package dtos

import play.api.data.Form
import play.api.data.Forms._
import models.domain.User

case class UserCreate (
  name: String,
  username: String,
  password: String
) {
  def toUser: User = User(username, name, password)
}

object UserCreate:
  def unapply(u: UserCreate): Option[(String, String, String)] =
    Some(u.name, u.username, u.password)

  val form: Form[UserCreate] = Form(
    mapping(
      "name" -> nonEmptyText,
      "username" -> nonEmptyText,
      "password" -> nonEmptyText
    )(UserCreate.apply)(UserCreate.unapply)
  )