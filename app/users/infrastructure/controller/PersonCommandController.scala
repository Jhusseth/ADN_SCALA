package users.infrastructure.controller

import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}
import users.application.command.PersonCommand
import users.application.manager.{PersonCreateManager, PersonDeleteManager, PersonUpdateManager}
import users.infrastructure.controller.format.FormatApplication

import javax.inject.Inject

class PersonCommandController @Inject() (
  cc: ControllerComponents,
  personCreateManager: PersonCreateManager,
  personUpdateManager: PersonUpdateManager,
  personDeleteManager: PersonDeleteManager)
  extends AbstractController(cc)
  with FormatApplication {

  def create() = Action.async(parse.json[PersonCommand]) {
    implicit request: Request[PersonCommand] =>
      personCreateManager.execute(request.body)
  }

  def update() = Action.async(parse.json[PersonCommand]) {
    implicit request: Request[PersonCommand] =>
      personUpdateManager.execute(request.body)
  }

  def delete(id: Long) = Action.async { implicit request: Request[AnyContent] =>
    personDeleteManager.execute(id)
  }

}
