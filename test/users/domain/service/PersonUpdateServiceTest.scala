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
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class PersonUpdateServiceTest extends AsyncWordSpec with Matchers with Mockito {
  val mockRepoPerson: PersonRepository = mock[PersonRepository]
  implicit val s: Scheduler = Scheduler.global

  val service = new UpdatePersonService

  "UpdatePersonService" can {
    "generate error" when {
      "se va a actualizar una peresona y no existe previamente" in {
        val person = Person(1L, "Test","Service", "test@test.com",
          LocalDate.of(1995,11,12), LocalDateTime.now())
        mockRepoPerson.updateEntity(any[Person]()) returns EitherT(Task(1L.asRight[DomainError]))
        mockRepoPerson.exist(anyLong) returns Task(false)

        val response = service.update(person).run(mockRepoPerson).value
        val result = Await.result(response.runToFuture, 2 seconds)

        result.isLeft mustBe true
        result match {
          case Right(_) => fail("resultado inesperado")
          case Left(error) => error.asInstanceOf[DetailsDomainError].message mustBe "La persona no existe"
        }
      }

    }

    "actualizar persona" when {
      "la persona existe previamente" in {
        val person = Person(1L, "Test","Service", "test@test.com",
          LocalDate.of(1995,11,12), LocalDateTime.now())
        mockRepoPerson.updateEntity(any[Person]()) returns EitherT(Task(1L.asRight[DomainError]))
        mockRepoPerson.exist(anyLong) returns Task(true)

        val response = service.update(person).run(mockRepoPerson).value
        val result = Await.result(response.runToFuture, 2 seconds)

        result match {
          case Right(valor) => valor mustBe 1L
          case Left(_) => fail("resultado inesperado")
        }
      }
    }
  }

}
