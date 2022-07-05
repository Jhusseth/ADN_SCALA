package users.infrastructure

import monix.execution.ExecutionModel.AlwaysAsyncExecution
import monix.execution.Scheduler
import play.api.libs.json.{Json, Writes}
import play.api.mvc.Result
import users.application.common.ResponseCommand
import users.application.common.manager.CommandResponse
import users.domain.common.error.DomainError.{ElementDuplicate, ElementNotExist}
import users.domain.common.error.{DomainError, DomainErrors}
import users.domain.common.model.Result.Response
import users.infrastructure.common.error.Error.generateErrorMessage
import users.infrastructure.common.error.{Application, Business}

import scala.concurrent.Future

package object controller {

  import play.api.mvc.Results._

  implicit val scheduler = Scheduler.fixedPool(name = "main-pool", poolSize = 10, executionModel = AlwaysAsyncExecution)

  implicit def searchResultsWrites[T](implicit fmt: Writes[T]): Writes[ResponseCommand[T]] =
    (ts: ResponseCommand[T]) => Json.obj("respuesta" -> ts.response)

  implicit def transformCommand[T](s: CommandResponse[T])(implicit tjs: Writes[T]): Future[Result] = {
    s.fold(
      { err => classifyError(err) },
      { response => Ok(Json.toJson(response)) }).runToFuture
  }

  implicit def transform[T](s: Response[T])(implicit tjs: Writes[T]): Future[Result] = {
    s.fold(
      { err => classifyError(err) },
      { response => Ok(Json.toJson(response)) }).runToFuture
  }

  private def classifyError[T](err: DomainError) = {
    err match {
      case error: ElementDuplicate => BadRequest(generateErrorMessage(error.message, Business))
      case error: ElementNotExist => NotFound(generateErrorMessage(error.message, Business))
      case error: DomainErrors => BadRequest(generateErrorMessage(error.errors.toList.map(_.message).mkString(" - "), Business))
      case _ => InternalServerError(generateErrorMessage("Error, contacte al administrador", Application))
    }
  }
}
