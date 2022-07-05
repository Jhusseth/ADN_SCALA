package users.domain.service

import cats.data.EitherT
import cats.implicits._
import monix.eval.Task
import monix.execution.Scheduler
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AsyncWordSpec
import org.specs2.mock.Mockito
import users.domain.common.error.{DetailsDomainError, DomainError}
import users.domain.model.Person
import users.domain.persistence.repository.PersonRepository

import java.time.{LocalDate, LocalDateTime}

class PersonCreateServiceTest extends AsyncWordSpec with Matchers with Mockito {
  val mockRepoPerson: PersonRepository = mock[PersonRepository]
  implicit val s: Scheduler = Scheduler.global

  val service = new CreatePersonService

  "PersonCreateService" can {
    "generate error" when {
      "se va a crear una persona que existe previamente" in {
        val person = Person(1L, "Test","Service", "test@test.com",
          LocalDate.of(1995,11,12), LocalDateTime.now())
        mockRepoPerson.saveEntity(any[Person]()) returns EitherT(Task(1L.asRight[DomainError]))
        mockRepoPerson.exist(anyLong) returns Task(true)

        val response = service.create(person).run(mockRepoPerson).value

        response.runToFuture.map(result => {
          result.isLeft mustBe true
          result match {
            case Right(_) => fail("resultado inesperado")
            case Left(error) => error.asInstanceOf[DetailsDomainError].message mustBe "La persona ya existe"
          }
        })
      }

    }

    "crear una persona" when {
      "la persona no existe previamente" in {
        val person = Person(1L, "Test","Service", "test@test.com",
          LocalDate.of(1995,11,12), LocalDateTime.now())
        mockRepoPerson.saveEntity(any[Person]()) returns EitherT(Task(1L.asRight[DomainError]))
        mockRepoPerson.exist(anyLong) returns Task(false)

        val response = service.create(person).run(mockRepoPerson).value

        response.runToFuture.map(result => {
          result match {
            case Right(value) => value mustBe 1L
            case Left(_) => fail("resultado inesperado")
          }
        });
      }
    }
  }

}
