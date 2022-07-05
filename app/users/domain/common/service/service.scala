package users.domain.common

import cats.data.{EitherT, NonEmptyList, Reader}
import monix.eval.Task
import users.domain.common.error.DomainError
import users.domain.common.persistence.BaseRepository

import scala.concurrent.Future

package object service {
  type RepoReaderFuture[D] = Reader[BaseRepository[_, _], Future[D]]

  type RepoReaderT[D, E <: DomainError] = Reader[BaseRepository[_, _], EitherT[Task, E, D]]

  def readerFuture[D](f: BaseRepository[_, _] => Future[D]): RepoReaderFuture[D] =
    Reader[BaseRepository[_, _], Future[D]](f)

  def reader[D, E <: DomainError](f: BaseRepository[_, _] => EitherT[Task, E, D]): RepoReaderT[D, E] =
    Reader[BaseRepository[_, _], EitherT[Task, E, D]](f)

  def toEmptyList(error: DomainError): NonEmptyList[DomainError] = NonEmptyList.one(error)
}
