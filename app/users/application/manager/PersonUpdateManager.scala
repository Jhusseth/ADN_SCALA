package users.application.manager

import cats.data.EitherT
import users.application.command.PersonCommand
import users.application.common.ResponseCommand
import users.application.common.manager.{CommandResponse, ResponseCommandManager}
import users.application.factory.PersonFactory
import users.domain.model.Person.IdPerson
import users.domain.persistence.repository.PersonRepository
import users.domain.service.UpdatePersonService

import javax.inject.Inject

class PersonUpdateManager @Inject() (
  repository: PersonRepository,
  service: UpdatePersonService ) extends ResponseCommandManager[PersonCommand, IdPerson] {

  override def execute(command: PersonCommand): CommandResponse[IdPerson] = {
    for {
      person <- EitherT(PersonFactory.create(command))
      response <- service.update(person).run(repository)
    } yield ResponseCommand(response)
  }

}
