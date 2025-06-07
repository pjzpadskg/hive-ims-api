package controllers

import javax.inject._
import play.api._
import play.api.mvc._

@Singleton
class UserController @Inject()(
  val controllerComponents: ControllerComponents
) extends BaseController {
  def create = TODO
  def get = TODO
}
