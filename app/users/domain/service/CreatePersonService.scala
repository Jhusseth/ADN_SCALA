package users.domain.service

import users.domain.common.error.DomainError
import users.domain.common.model.Validations
import users.domain.common.service.{RepoReaderT, reader}
import users.domain.model.Person
import users.domain.model.Person.IdPerson
import users.domain.persistence.repository.PersonRepository

class CreatePersonService {

  def create (person: Person): RepoReaderT[IdPerson,DomainError] = reader {
    case repo: PersonRepository =>
      for {
        _<- Validations.duplicity(person.id)(repo)
        created <- repo.saveEntity(person)
      } yield created
  }
}
