package controllers

import javax.inject.{ Singleton, Inject }
import scala.concurrent.{ Future, ExecutionContext }
import play.api.i18n.I18nSupport
import play.api.libs.json.Json
import play.api.mvc._

import dtos.UserCredentials
import models.service.UserService

@Singleton
class SessionController @Inject()(
  val controllerComponents: ControllerComponents,
  UserService: UserService
)(using ExecutionContext) extends BaseController with I18nSupport {

  def create = Action.async { implicit request: Request[AnyContent] => 
    UserCredentials.form.bindFromRequest().fold(
      error => Future.successful(BadRequest(error.errorsAsJson)),
      credentials => UserService.get(credentials).fold(
        _ => Unauthorized,
        user => Ok.withSession("sessionKey" -> user.id.toString)
      )
    )
  }

  def delete = Action { implicit request: Request[AnyContent] => Ok.withNewSession }
}
