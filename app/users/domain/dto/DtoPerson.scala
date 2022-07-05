package users.domain.dto

import users.domain.model.Person.IdPerson

import java.time.{LocalDate, LocalDateTime}

case class DtoPerson(id: Option[IdPerson], fullName: Option[String], lastName: Option[String], email: Option[String],
                     birthDate: Option[LocalDate], createdAt: Option[LocalDateTime])
