package users.domain

import cats.data._
import cats.implicits._
import users.domain.common.error.{DetailsDomainError, DomainError, DomainErrors}

import java.time.LocalDate

package object model {
  type ValidationResult[A] = ValidatedNel[DetailsDomainError, A]

  implicit def toEitherDomain[T](e: ValidationResult[T]): Either[DomainError, T] = {
    e.toEither match {
      case Left(value) => Left(DomainErrors(value))
      case Right(person) => Right(person)
    }
  }

  def mandatoryValue[T](value: Option[T], message: String): ValidationResult[T] = {
    Either.cond(
      value.isDefined,
      value.get,
      DomainError.mandatoryValue(message)).toValidatedNel
  }

  def toValidatedNel[T](value: T): ValidationResult[T] = {
    value.validNel[DetailsDomainError]
  }

  def maxAge(value : Option[LocalDate], message1: String, message2: String): ValidationResult[LocalDate] = {

    if (value.isDefined) {
      Either.cond(
        maxAge(value),
        value.get,
        DomainError.maxAge(message2)).toValidatedNel
    } else {
      Either.cond(
        value.isDefined,
        value.get,
        DomainError.mandatoryValue(message1)).toValidatedNel
    }
  }

  private def maxAge(value : Option[LocalDate]): Boolean ={
    import java.time.Period
    val birthDate = value.get
    val now = LocalDate.now

    val period = Period.between(birthDate, now)

    period.getYears>18
  }

//  implicit def optToValue[T](valueOpT: Option[T]): T = {
//    try
//      valueOpT.get
//    catch {
//      case exception: Exception =>
//        throw ExceptionInvalidValue("Valor invalido", exception)
//    }
//  }
}
