package errors

case class Error(code: Int, reason: String)

object Error {
  val BAD_REQUEST = 400
  val UNAUTHORIZED = 401
  val FORBIDDEN = 403
  val NOT_FOUND = 404
  val CONFLICT = 409
  val INTERNAL = 500

  val USER_CONFLICT = "USER_ALREADY_EXISTS"
  val USER_NOT_FOUND = "USER_NOT_FOUND"
}