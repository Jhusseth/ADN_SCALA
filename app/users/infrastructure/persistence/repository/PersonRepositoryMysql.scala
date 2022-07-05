package users.infrastructure.persistence.repository

import cats.data.EitherT
import cats.implicits._
import monix.eval.Task
import play.api.db.slick.DatabaseConfigProvider
import users.domain.common.error.DomainError
import users.domain.common.model.Result.Response
import users.domain.model.Person
import users.domain.model.Person.IdPerson
import users.domain.persistence.repository.PersonRepository
import users.infrastructure.common.slick.SlickRepository
import users.infrastructure.persistence.table.Tables

import java.time.format.DateTimeFormatter
import javax.inject.Inject

class PersonRepositoryMysql @Inject()(val dbConfigProvider: DatabaseConfigProvider) extends PersonRepository
  with SlickRepository
  with Tables {

  import profile.api._
  import users.infrastructure.common.FutureTransform._

  override def saveEntity(entity: Person): Response[IdPerson] = EitherT {
    val data = getPersonData(entity)
    val query = persons += (data)
    db.run(query.transactionally).deferFuture().map(_ => entity.id.asRight[DomainError])
  }

  override def updateEntity(entity: Person): Response[IdPerson] = EitherT {
    val data = getPersonData(entity)
    val query = persons.filter(b=> b.id === entity.id).update(data)
    db.run(query.transactionally).deferFuture().map(_ => entity.id.asRight[DomainError])
  }

  override def delete(id: Long): Response[Long] = EitherT {
    db.run(persons.filter(_.id === id).delete).deferFuture().map(_ => id.asRight[DomainError])
  }

  override def exist(id: Long): Task[Boolean] = {
    val s = persons.filter(b => b.id === id).exists
    db.run(s.result).deferFuture()
  }

  private def getPersonData(entity: Person) = {
    (
      entity.id, entity.fullName, entity.lastName, entity.email,
      Some(java.sql.Date.valueOf(entity.birthDate)),
      Some(java.sql.Date.valueOf(entity.createdAt.toLocalDate))
    )
  }

}
