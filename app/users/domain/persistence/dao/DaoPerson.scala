package users.domain.persistence.dao

import users.domain.dto.DtoPerson
import monix.eval.Task
import users.domain.common.model.Result.Response
import users.domain.model.Person.IdPerson

trait DaoPerson {

  def list: Task[List[DtoPerson]]

  def findById(id: IdPerson): Response[DtoPerson]

  def findByName(name: String): Task[List[DtoPerson]]

}
