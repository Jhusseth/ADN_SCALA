package users.domain.persistence.repository

import users.domain.common.persistence.BaseRepository
import users.domain.model.Person
import users.domain.model.Person.IdPerson

trait PersonRepository extends BaseRepository[IdPerson, Person]
