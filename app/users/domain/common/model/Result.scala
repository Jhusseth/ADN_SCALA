package users.domain.common.model

import cats.data.EitherT
import monix.eval.Task
import users.domain.common.error.DomainError

object Result {
  type Response[T] = EitherT[Task, DomainError, T]
}
