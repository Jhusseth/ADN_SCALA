package users.infrastructure.persistence.table

import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.sql.SqlProfile.ColumnOption.SqlType

trait Tables {
  self: HasDatabaseConfigProvider[JdbcProfile] =>
  import profile.api._

  val persons = TableQuery[PersonTable]

  class PersonTable(tag: Tag) extends Table[(Long, String, String, String,
    Option[java.sql.Date], Option[java.sql.Date])](tag, "PERSON") {

    def id = column[Long]("ID",O.PrimaryKey)
    def fullName = column[String]("FULL_NAME")
    def lastName = column[String]("LAST_NAME")
    def email = column[String]("EMAIL")
    def birthDate = column[Option[java.sql.Date]]("BIRTH_DATE")
    def createdAt = column[Option[java.sql.Date]]("CREATED_AT")

    def * = (id, fullName, lastName, email, birthDate, createdAt)

  }
}
