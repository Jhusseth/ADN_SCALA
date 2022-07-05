package users.domain.service

import cats.data.EitherT
import cats.implicits._
import monix.eval.Task
import monix.execution.Scheduler
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AsyncWordSpec
import org.specs2.mock.Mockito
import users.domain.common.error.{DetailsDomainError, DomainError}
import users.domain.persistence.repository.PersonRepository

import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class PersonDeleteServiceTest extends AsyncWordSpec with Matchers with Mockito{
  val mockRepoPerson: PersonRepository = mock[PersonRepository]
  implicit val s: Scheduler = Scheduler.global

  val service = new DeletePersonService

  "DeletePersonService" can {
    "generate error" when {
      "La persona no existe previamente" in {
        val id = 1L;
        mockRepoPerson.exist(anyLong) returns Task(false)

        val response = service.delete(id).run(mockRepoPerson).value
        val result = Await.result(response.runToFuture, 2 seconds)

        result.isLeft mustBe true
        result match {
          case Right(_) => fail("resultado inesperado")
          case Left(error) => error.asInstanceOf[DetailsDomainError].message mustBe "La persona no existe"
        }
      }
    }

    "eliminar persona" when {
      "La persona existe previamente" in {
        val id = 1L;
        mockRepoPerson.exist(anyLong) returns Task(true)
        mockRepoPerson.delete(anyLong) returns EitherT(Task(1L.asRight[DomainError]))

        val response = service.delete(id).run(mockRepoPerson).value
        val result = Await.result(response.runToFuture, 2 seconds)

        result.isRight mustBe true
        result.toOption.isDefined mustBe true
        result.toOption.get mustBe id
      }
    }
  }

}
