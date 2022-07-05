package users.domain.service

import users.domain.common.error.DomainError
import users.domain.common.model.Validations
import users.domain.common.service.{RepoReaderT, reader}
import users.domain.persistence.repository.PersonRepository

class DeletePersonService{

  def delete (id: Long): RepoReaderT[Long, DomainError] = reader {
    case repo: PersonRepository =>
      val r = for {
        _<- Validations.existence(id)(repo)
        delete <- repo.delete(id)
      } yield delete
      r
  }

}
