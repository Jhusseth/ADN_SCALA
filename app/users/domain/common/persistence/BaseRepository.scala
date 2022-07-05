package users.domain.common.persistence

import monix.eval.Task
import users.domain.common.model.Result.Response

trait BaseRepository[I, T] {

  def saveEntity(entity: T): Response[I]

  def updateEntity(entity: T): Response[Long]

  def delete(id: I): Response[Long]

  def exist(id: I): Task[Boolean]
}
