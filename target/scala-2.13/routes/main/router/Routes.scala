// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:5
  PersonQueryController_0: users.infrastructure.controller.PersonQueryController,
  // @LINE:8
  PersonCommandController_1: users.infrastructure.controller.PersonCommandController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:5
    PersonQueryController_0: users.infrastructure.controller.PersonQueryController,
    // @LINE:8
    PersonCommandController_1: users.infrastructure.controller.PersonCommandController
  ) = this(errorHandler, PersonQueryController_0, PersonCommandController_1, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, PersonQueryController_0, PersonCommandController_1, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """v1/people""", """users.infrastructure.controller.PersonQueryController.list()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """v1/people/""" + "$" + """id<[^/]+>""", """users.infrastructure.controller.PersonQueryController.findById(id:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """v1/people/search/""" + "$" + """name<[^/]+>""", """users.infrastructure.controller.PersonQueryController.findByName(name:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """v1/people""", """users.infrastructure.controller.PersonCommandController.create()"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """v1/people""", """users.infrastructure.controller.PersonCommandController.update()"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """v1/people/""" + "$" + """id<[^/]+>""", """users.infrastructure.controller.PersonCommandController.delete(id:Long)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:5
  private[this] lazy val users_infrastructure_controller_PersonQueryController_list0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("v1/people")))
  )
  private[this] lazy val users_infrastructure_controller_PersonQueryController_list0_invoker = createInvoker(
    PersonQueryController_0.list(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "users.infrastructure.controller.PersonQueryController",
      "list",
      Nil,
      "GET",
      this.prefix + """v1/people""",
      """""",
      Seq()
    )
  )

  // @LINE:6
  private[this] lazy val users_infrastructure_controller_PersonQueryController_findById1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("v1/people/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val users_infrastructure_controller_PersonQueryController_findById1_invoker = createInvoker(
    PersonQueryController_0.findById(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "users.infrastructure.controller.PersonQueryController",
      "findById",
      Seq(classOf[Long]),
      "GET",
      this.prefix + """v1/people/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:7
  private[this] lazy val users_infrastructure_controller_PersonQueryController_findByName2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("v1/people/search/"), DynamicPart("name", """[^/]+""",true)))
  )
  private[this] lazy val users_infrastructure_controller_PersonQueryController_findByName2_invoker = createInvoker(
    PersonQueryController_0.findByName(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "users.infrastructure.controller.PersonQueryController",
      "findByName",
      Seq(classOf[String]),
      "GET",
      this.prefix + """v1/people/search/""" + "$" + """name<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val users_infrastructure_controller_PersonCommandController_create3_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("v1/people")))
  )
  private[this] lazy val users_infrastructure_controller_PersonCommandController_create3_invoker = createInvoker(
    PersonCommandController_1.create(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "users.infrastructure.controller.PersonCommandController",
      "create",
      Nil,
      "POST",
      this.prefix + """v1/people""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val users_infrastructure_controller_PersonCommandController_update4_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("v1/people")))
  )
  private[this] lazy val users_infrastructure_controller_PersonCommandController_update4_invoker = createInvoker(
    PersonCommandController_1.update(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "users.infrastructure.controller.PersonCommandController",
      "update",
      Nil,
      "PUT",
      this.prefix + """v1/people""",
      """""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val users_infrastructure_controller_PersonCommandController_delete5_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("v1/people/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val users_infrastructure_controller_PersonCommandController_delete5_invoker = createInvoker(
    PersonCommandController_1.delete(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "users.infrastructure.controller.PersonCommandController",
      "delete",
      Seq(classOf[Long]),
      "DELETE",
      this.prefix + """v1/people/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:5
    case users_infrastructure_controller_PersonQueryController_list0_route(params@_) =>
      call { 
        users_infrastructure_controller_PersonQueryController_list0_invoker.call(PersonQueryController_0.list())
      }
  
    // @LINE:6
    case users_infrastructure_controller_PersonQueryController_findById1_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        users_infrastructure_controller_PersonQueryController_findById1_invoker.call(PersonQueryController_0.findById(id))
      }
  
    // @LINE:7
    case users_infrastructure_controller_PersonQueryController_findByName2_route(params@_) =>
      call(params.fromPath[String]("name", None)) { (name) =>
        users_infrastructure_controller_PersonQueryController_findByName2_invoker.call(PersonQueryController_0.findByName(name))
      }
  
    // @LINE:8
    case users_infrastructure_controller_PersonCommandController_create3_route(params@_) =>
      call { 
        users_infrastructure_controller_PersonCommandController_create3_invoker.call(PersonCommandController_1.create())
      }
  
    // @LINE:9
    case users_infrastructure_controller_PersonCommandController_update4_route(params@_) =>
      call { 
        users_infrastructure_controller_PersonCommandController_update4_invoker.call(PersonCommandController_1.update())
      }
  
    // @LINE:10
    case users_infrastructure_controller_PersonCommandController_delete5_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        users_infrastructure_controller_PersonCommandController_delete5_invoker.call(PersonCommandController_1.delete(id))
      }
  }
}
