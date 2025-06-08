package dtos

import play.api.data.Form
import play.api.data.Forms._

case class UserCredentials (username: String, password: String)

object UserCredentials:
  def unapply(u: UserCredentials): Option[(String, String)] =
    Some(u.username, u.password)

  lazy val form: Form[UserCredentials] = Form(
    mapping(
      "username" -> nonEmptyText,
      "password" -> nonEmptyText
    )(UserCredentials.apply)(UserCredentials.unapply)
  )
  