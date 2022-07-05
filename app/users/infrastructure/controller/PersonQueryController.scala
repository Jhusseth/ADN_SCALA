package users.infrastructure.controller

import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}
import users.application.manager.{PersonListManager, PersonQueryByNameManager, PersonQueryManager}
import users.infrastructure.controller.format.FormatApplication

import javax.inject.Inject

class PersonQueryController @Inject() (
  cc: ControllerComponents,
  personListManager: PersonListManager,
  personQueryByNameManager: PersonQueryByNameManager,
  personQueryManager: PersonQueryManager)
  extends AbstractController(cc)
  with FormatApplication {

  def list() = Action.async { implicit request: Request[AnyContent] =>
    personListManager.execute()
  }

  def findById (id: Long)  = Action.async { implicit request: Request[AnyContent] =>
    personQueryManager.execute(id)
  }

  def findByName (name: String)  = Action.async { implicit request: Request[AnyContent] =>
    personQueryByNameManager.execute(name)
  }

}
