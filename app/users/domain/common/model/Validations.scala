package users.domain.common.model

import cats.data.EitherT
import cats.implicits.catsSyntaxEitherId
import users.domain.common.error.DomainError
import users.domain.common.model.Result.Response
import users.domain.model.Person.IdPerson
import users.domain.persistence.repository.PersonRepository

object Validations {

  def duplicity(id: Long)(repo: PersonRepository): Response[Boolean] = EitherT {
    repo.exist(id).map {
      case true => DomainError.duplicate().asLeft
      case false => false.asRight
    }
  }

  def existence(id: Long)(repo: PersonRepository): Response[Boolean] = EitherT {
    repo.exist(id).map {
      case true => true.asRight
      case false => DomainError.notExist().asLeft
    }
  }
}
