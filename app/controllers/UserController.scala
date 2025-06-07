package controllers

import javax.inject.{ Singleton, Inject }
import scala.concurrent.{ Future, ExecutionContext }
import play.api.i18n.I18nSupport
import play.api.libs.json.Json
import play.api.mvc._

import dtos.UserCreate
import models.service.UserService

@Singleton
class UserController @Inject()(
  val controllerComponents: ControllerComponents,
  UserService: UserService
)(using ExecutionContext) extends BaseController with I18nSupport {
  def create = Action.async { implicit request: Request[AnyContent] =>
    UserCreate.form.bindFromRequest().fold(
      error => Future.successful(BadRequest(error.errorsAsJson)),
      data => UserService.create(data).fold(
        err => Status(err.code)(Json.obj("error" -> err.reason)),
        _ => Created
      )
    )
  }
}
