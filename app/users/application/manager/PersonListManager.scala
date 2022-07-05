package users.application.manager

import cats.data.EitherT
import cats.implicits._
import users.application.common.manager.QueryCommandManager
import users.domain.common.error.DomainError
import users.domain.common.model.Result.Response
import users.domain.dto.DtoPerson
import users.domain.persistence.dao.DaoPerson

import javax.inject.Inject

class PersonListManager @Inject() (daoPerson: DaoPerson) extends QueryCommandManager[List[DtoPerson]] {
  override def execute(): Response[List[DtoPerson]] = {
      EitherT {
        daoPerson.list.map(s => s.asRight[DomainError])
      }
  }
}
