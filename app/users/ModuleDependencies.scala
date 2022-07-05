package users

import com.google.inject.AbstractModule
import users.domain.persistence.dao.DaoPerson
import users.domain.persistence.repository.PersonRepository
import users.infrastructure.persistence.dao.DaoPersonMysql
import users.infrastructure.persistence.repository.PersonRepositoryMysql

class ModuleDependencies extends AbstractModule{

  override def configure() = {
    bind(classOf[PersonRepository]).to(classOf[PersonRepositoryMysql]).asEagerSingleton()
    bind(classOf[DaoPerson]).to(classOf[DaoPersonMysql]).asEagerSingleton()
  }

}
