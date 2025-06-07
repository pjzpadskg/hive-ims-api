package models
package domain

import java.util.UUID

case class User (
  id: UUID,
  username: String,
  name: String,
  password: String
)

object User:
  def apply(username: String, name: String, password: String): User =
    User(UUID.randomUUID, username, name, password)