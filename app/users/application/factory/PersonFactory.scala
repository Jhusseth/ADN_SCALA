package users.application.factory

import monix.eval.Task
import users.application.command.PersonCommand
import users.domain.common.error.DomainError
import users.domain.model.Person

object PersonFactory {

  def create (personCommand: PersonCommand): Task[Either[DomainError, Person]] = {
    Task.now(Person.createPersonValidate(personCommand.id, personCommand.fullName, personCommand.lastName,
      personCommand.email, personCommand.birthDate))
  }
}
