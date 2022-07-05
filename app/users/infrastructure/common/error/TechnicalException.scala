package users.infrastructure.common.error

case class TechnicalException(message: String, error: Throwable) extends Exception(message, error)
