package users.domain.common.error

import cats.data.NonEmptyList

sealed trait DomainError

final case class DomainErrors( errors: NonEmptyList[DetailsDomainError]) extends DomainError

sealed trait DetailsDomainError extends DomainError {
  val message: String
}

object DomainError {

  final case class ElementDuplicate(message: String) extends DetailsDomainError
  final case class ElementNotExist(message: String) extends DetailsDomainError
  final case class InvalidValue(message: String) extends DetailsDomainError
  final case class MandatoryValue(message: String) extends DetailsDomainError
  final case class MaxAge(message: String) extends DetailsDomainError

  def duplicate (message: String = "La persona ya existe"): DetailsDomainError = ElementDuplicate (message)
  def notExist (message: String = "La persona no existe"): DetailsDomainError = ElementNotExist (message)
  def invalidValue (message: String = "Valor invalido"): DetailsDomainError = InvalidValue (message)
  def mandatoryValue (message: String = "El valor es obligatorio"): DetailsDomainError = MandatoryValue (message)
  def maxAge (message: String = "Debe ser mayor de 18 años"): DetailsDomainError = MaxAge (message)

}
