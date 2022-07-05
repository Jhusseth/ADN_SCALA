package users.application.common.manager

import users.domain.common.model.Result.Response

trait QueryParameterManager[P, R] {
  def execute(command: P): Response[R]
}
