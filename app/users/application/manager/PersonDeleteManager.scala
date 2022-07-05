package users.application.manager

import users.application.common.ResponseCommand
import users.application.common.manager.{CommandResponse, ResponseCommandManager}
import users.domain.model.Person.IdPerson
import users.domain.persistence.repository.PersonRepository
import users.domain.service.DeletePersonService

import javax.inject.Inject

class PersonDeleteManager @Inject() (
  repository: PersonRepository,
  service: DeletePersonService ) extends ResponseCommandManager[IdPerson, IdPerson] {

  override def execute(id: IdPerson): CommandResponse[IdPerson] = {
    service.delete(id).run(repository).map(response => ResponseCommand(response))
  }

}
