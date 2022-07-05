package users.domain.model

import cats.data.Validated._
import cats.implicits._
import users.domain.common.error.DomainError
import users.domain.model.Person.IdPerson

import java.time.{LocalDate, LocalDateTime}

final case class Person(id: IdPerson, fullName: String, lastName: String, email: String, birthDate: LocalDate,
                        createdAt: LocalDateTime)

object Person {
  type IdPerson = Long

  def createPersonValidate(id: IdPerson, fullName: Option[String], lastName: Option[String], email: Option[String],
                     birthDate: Option[LocalDate]): Either[DomainError, Person] =
    (
      mandatoryValue(fullName, "nombre obligatorio"),
      mandatoryValue(lastName, "apellido obligatorio"),
      mandatoryValue(email, "email obligatorio"),
      maxAge(birthDate,"fecha de nacimiento obligatoria", "debe ser mayor de edad"),
    ).mapN(Person(id, _, _, _, _,LocalDateTime.now()))

}