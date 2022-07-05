package users.application.manager

import cats.data.EitherT
import users.application.command.PersonCommand
import users.application.common.ResponseCommand
import users.application.common.manager.{CommandResponse, ResponseCommandManager}
import users.application.factory.PersonFactory
import users.domain.model.Person.IdPerson
import users.domain.persistence.repository.PersonRepository
import users.domain.service.CreatePersonService

import javax.inject.Inject

class PersonCreateManager @Inject() (
  repository: PersonRepository,
  service: CreatePersonService ) extends ResponseCommandManager[PersonCommand, IdPerson] {

  override def execute(personCommand: PersonCommand): CommandResponse[IdPerson] = {
    for {
      person <- EitherT(PersonFactory.create(personCommand))
      response <- service.create(person).run(repository)
    } yield ResponseCommand(response)
  }

}
