package users.application.common.manager

import users.domain.common.model.Result.Response

trait QueryCommandManager[R] {
  def execute(): Response[R]
}
