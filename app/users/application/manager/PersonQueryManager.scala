package users.application.manager

import users.application.common.manager.QueryParameterManager
import users.domain.common.model.Result.Response
import users.domain.dto.DtoPerson
import users.domain.model.Person.IdPerson
import users.domain.persistence.dao.DaoPerson

import javax.inject.Inject

class PersonQueryManager @Inject() (daoPerson: DaoPerson) extends QueryParameterManager[IdPerson, DtoPerson] {

  override def execute(id: IdPerson): Response[DtoPerson] = {
    daoPerson.findById(id)
  }

}
