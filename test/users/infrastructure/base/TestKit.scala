package users.infrastructure.base

import com.typesafe.config.ConfigFactory
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api._
import play.api.http.Port
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.inject.{DefaultApplicationLifecycle, bind}
import play.api.mvc._
import play.core.DefaultWebCommands
import play.core.server.{Server, ServerConfig, ServerProvider}
import slick.basic.DatabaseConfig
import slick.jdbc.H2Profile
import users.domain.persistence.dao.DaoPerson
import users.domain.persistence.repository.PersonRepository
import users.infrastructure.persistence.dao.DaoPersonMysql
import users.infrastructure.persistence.repository.PersonRepositoryMysql

import java.util.concurrent.Executors
import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, ExecutionContext, ExecutionContextExecutorService}
import scala.language.postfixOps

abstract class TestKit extends PlaySpec {
  implicit val appExecutionContext: ExecutionContextExecutorService =
    ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(15))
}

abstract class DatabaseTestKit extends AppTestKit {

  import H2Profile.api._
  val dbConfig: DatabaseConfig[H2Profile]

  val schema: slick.jdbc.H2Profile.DDL

  val logger: Logger = Logger(this.getClass)

  def testDbConfig(dbName: String): DatabaseConfig[H2Profile] =
    DatabaseConfig.forConfig[H2Profile](s"slick.dbs.$dbName", conf.underlying)

  def shutdownDb(): Unit = {
    logger.debug(s"DATABASE SHUTDOWN")
    val execute = for {
      _ <- dbConfig.db.run(schema.drop)
      _ <- dbConfig.db.shutdown
    } yield ()

    Await.result(execute, 5 seconds)
  }

  def initializeDb(): Unit = {
    logger.debug(s"INITIALIZING DATABASE")
    Await.result[Unit](dbConfig.db.run(schema.create), 5 seconds)
  }

}

import play.api.routing.Router

abstract class AppTestKit extends TestKit with GuiceOneAppPerSuite {

  final protected val appBuilder = new GuiceApplicationBuilder()

  final val conf: Configuration = Configuration(ConfigFactory.load("application-test.conf"))

  override def fakeApplication(): Application = appBuilder.configure(conf)
    .overrides(
      bind(classOf[PersonRepository]).to(classOf[PersonRepositoryMysql]),
      bind(classOf[DaoPerson]).to(classOf[DaoPersonMysql])).build()

}

object TestKit {

  def withRouter[T](config: ServerConfig = ServerConfig(port = Some(0), mode = Mode.Test))
                   (routes: PartialFunction[RequestHeader, Handler])
                   (block: Port => T)(implicit provider: ServerProvider): T = {

    //    val conf = Configuration(ConfigFactory.load("application-test.conf"))
    //
    //    val context = ApplicationLoader.Context(
    //      environment = Environment.simple(path = config.rootDir, mode = config.mode),
    //      initialConfiguration = conf,
    //      lifecycle = new DefaultApplicationLifecycle,
    //      devContext = None)
    //    val application = new BuiltInComponentsFromContext(context) with NoHttpFiltersComponents {
    //      def router: Router = Router.from(routes)
    //    }.application
    //
    //    Server.withApplication(application, config)(block)
    //  }
    val conf = Configuration(ConfigFactory.load("application-test.conf"))

    val context = ApplicationLoader.Context(
          Environment.simple(path = config.rootDir, mode = config.mode),
          conf,
          new DefaultApplicationLifecycle,
          None
    )

    val application = new BuiltInComponentsFromContext(context) with NoHttpFiltersComponents {
            def router: Router = Router.from(routes)
          }.application

    Server.withApplication(application, config)(block)
  }
}
