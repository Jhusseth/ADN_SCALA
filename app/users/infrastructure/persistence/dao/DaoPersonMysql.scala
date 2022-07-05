package users.infrastructure.persistence.dao

import cats.data.EitherT
import cats.implicits.catsSyntaxEitherId
import monix.eval.Task
import play.api.db.slick.DatabaseConfigProvider
import users.domain.common.error.DomainError
import users.domain.common.model.Result.Response
import users.domain.dto.DtoPerson
import users.domain.persistence.dao.DaoPerson
import users.infrastructure.common.slick.SlickDao
import users.infrastructure.persistence.table.Tables

import javax.inject.Inject

class DaoPersonMysql @Inject()(val dbConfigProvider: DatabaseConfigProvider) extends DaoPerson
  with SlickDao with Tables {

  import DtoTransform._
  import profile.api._
  import users.infrastructure.common.FutureTransform._

  override def list: Task[List[DtoPerson]] = {
    val query = persons.result
    db.run(query).deferFuture().map(toGenerateMap)
  }

  override def findById(id: Long): Response[DtoPerson] = EitherT {
    val query = persons.filter(person => person.id === id).result
    db.run(query).deferFuture().map(x => x.headOption match {
      case None => DomainError.notExist().asLeft
      case Some(data) => fromRegisterToDtoPerson(data).asRight
    })

  }

  override def findByName(name: String): Task[List[DtoPerson]] = {
    val query = persons.filter(person => person.fullName.toLowerCase.startsWith(name.toLowerCase)).result
    db.run(query).deferFuture().map(toGenerateMap)
  }
}
