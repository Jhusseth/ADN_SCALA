package users.domain.service

import users.domain.common.error.DomainError
import users.domain.common.model.Validations
import users.domain.common.service.{RepoReaderT, reader}
import users.domain.model.Person
import users.domain.persistence.repository.PersonRepository

class UpdatePersonService{

  def update (person: Person): RepoReaderT[Long, DomainError] = reader {
    case repo: PersonRepository =>
      for {
        _<- Validations.existence(person.id)(repo)
        updated <- repo.updateEntity(person)
      } yield updated
  }
}
