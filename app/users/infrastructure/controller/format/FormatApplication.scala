package users.infrastructure.controller.format

import play.api.libs.json.{Format, Json}
import users.application.command.PersonCommand
import users.domain.dto.DtoPerson

trait FormatApplication {

  implicit val dtoPersonFormat: Format[DtoPerson] = Json.format[DtoPerson]
  implicit val commandPersonFormat: Format[PersonCommand] = Json.format[PersonCommand]

}
