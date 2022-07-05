package users.application.manager

import cats.data.EitherT
import cats.implicits._
import users.application.common.manager.QueryParameterManager
import users.domain.common.error.DomainError
import users.domain.common.model.Result.Response
import users.domain.dto.DtoPerson
import users.domain.persistence.dao.DaoPerson

import javax.inject.Inject

class PersonQueryByNameManager @Inject() (daoPerson: DaoPerson)
  extends QueryParameterManager[String, List[DtoPerson]] {

  override def execute(command: String): Response[List[DtoPerson]] = {
    EitherT {
      daoPerson.findByName(command).map(s => s.asRight[DomainError])
    }
  }
}
