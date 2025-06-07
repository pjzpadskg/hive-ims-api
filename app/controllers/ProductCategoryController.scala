package controllers

import javax.inject._
import play.api._
import play.api.mvc._

@Singleton
class ProductCategoryController @Inject()(
  val controllerComponents: ControllerComponents
) extends BaseController {
  def index() = Action { implicit request: Request[AnyContent] => Ok }
}
