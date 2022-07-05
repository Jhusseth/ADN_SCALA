package users.application.common.manager

trait ResponseCommandManager[P, R] {
  def execute(command: P): CommandResponse[R]
}
