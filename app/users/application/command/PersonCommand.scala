package users.application.command

import users.domain.model.Person.IdPerson

import java.time.LocalDate

case class PersonCommand(id: IdPerson, fullName: Option[String], lastName: Option[String],
           email: Option[String], birthDate: Option[LocalDate])
