package users.application.common

import cats.data.EitherT
import monix.eval.Task
import users.domain.common.error.DomainError
import users.domain.common.model.Result.Response

package object manager {
  type CommandResponse[T] = EitherT[Task, DomainError, ResponseCommand[T]]

  implicit def transform[T](domainResponse: Response[T]): CommandResponse[T] = {
    domainResponse.map(response => ResponseCommand[T](response))
  }
}
