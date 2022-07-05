package users.domain.model

case class ExceptionInvalidValue
  (
    value: String = "Valor invalido",
    throwable: Throwable
  ) extends Exception(value, throwable)
