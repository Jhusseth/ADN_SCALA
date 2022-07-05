package users.infrastructure.persistence.dao

import users.domain.dto.DtoPerson

object DtoTransform {
  implicit def fromRegisterToDtoPerson(data: (Long, String, String, String, Option[java.sql.Date],
    Option[java.sql.Date])): DtoPerson =
      DtoPerson(Some(data._1),Some(data._2), Some(data._3),Some(data._4), data._5.map(d => d.toLocalDate),
        data._6.map(d => d.toLocalDate.atStartOfDay()))

  implicit def toGenerateMap(register: Seq[(Long, String, String, String, Option[java.sql.Date],
    Option[java.sql.Date])]): List[DtoPerson] =
      register.map(fromRegisterToDtoPerson).toList
}
