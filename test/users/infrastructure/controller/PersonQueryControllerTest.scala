package users.infrastructure.controller

import org.scalatest.BeforeAndAfterAll
import org.scalatest.matchers.must.Matchers
import play.api.libs.json.{JsError, JsSuccess}
import play.api.test.FakeRequest
import play.api.test.Helpers._
import users.application.command.PersonCommand
import users.domain.dto.DtoPerson
import users.infrastructure.base.AppTestKit
import users.infrastructure.controller.format.FormatApplication

import java.time.LocalDate

class PersonQueryControllerTest extends AppTestKit with Matchers with FormatApplication with BeforeAndAfterAll {

  val API_PREFIX: String = "/api/v1/people"

  "PersonQueryController" can {
    "consulta las personas" when {
      "se invoca el servicio" in {

        val request = FakeRequest(GET, API_PREFIX)
        val response = route(app, request).get

        status(response) mustBe (OK)
        contentAsJson(response).validate[List[DtoPerson]] match {
          case JsError(errors) => fail(s"respuesta inesperada  ${errors.map(s => s" path ${s._1} ${s._2}")}")
          case JsSuccess(Nil, _) => fail("lista vacia")
          case JsSuccess(value: List[DtoPerson], _) => value.head.fullName.getOrElse("") mustBe "Test2"
        }

      }
    }

    "consulta la persona por id" when {
      "se invoca el servicio pero usuario no existe" in {

        val request = FakeRequest(GET, API_PREFIX + "/5")
        val response = route(app, request).get

        status(response) mustBe (NOT_FOUND)

      }
      "se invoca el servicio y el usuario existe" in {

        val request = FakeRequest(GET, API_PREFIX + "/2")
        val response = route(app, request).get

        status(response) mustBe (OK)
        contentAsJson(response).validate[DtoPerson] match {
          case JsError(errors) => fail(s"respuesta inesperada  ${errors.map(s => s" path ${s._1} ${s._2}")}")
          case JsSuccess(value, _) => value.fullName.getOrElse("") mustBe "Test2"
        }

      }
    }

    "consulta la persona por nombre" when {
      "se invoca el servicio" in {

        val request = FakeRequest(GET, API_PREFIX + "/search/Exa")
        val response = route(app, request).get

        status(response) mustBe (OK)
        contentAsJson(response).validate[List[DtoPerson]] match {
          case JsError(errors) => fail(s"respuesta inesperada  ${errors.map(s => s" path ${s._1} ${s._2}")}")
          case JsSuccess(value: List[DtoPerson], _) => value.head.fullName.getOrElse("") mustBe "Example"
        }

      }
    }
  }

  override protected def beforeAll(): Unit = {
    val dtoPerson = PersonCommand(2L, Some("Test2"),Some("Service"), Some("test2@test.com"),
      Some(LocalDate.of(1995,11,12)))
    val dtoPerson2 = PersonCommand(3L, Some("Test3"),Some("Service"), Some("test3@test.com"),
      Some(LocalDate.of(1995,11,12)))
    val dtoPerson3 = PersonCommand(4L, Some("Example"),Some("Service"), Some("example@test.com"),
      Some(LocalDate.of(1995,11,12)))

    val requestCreate = FakeRequest(POST, API_PREFIX).withJsonBody(commandPersonFormat.writes(dtoPerson))
    val responseRequest = route(app, requestCreate).get
    val jsonContent = contentAsJson(responseRequest)
    (jsonContent \ "respuesta").get.toString mustBe "2"

    val requestCreate2 = FakeRequest(POST, API_PREFIX).withJsonBody(commandPersonFormat.writes(dtoPerson2))
    val responseRequest2 = route(app, requestCreate2).get
    val jsonContent2 = contentAsJson(responseRequest2)
    (jsonContent2 \ "respuesta").get.toString mustBe "3"

    val requestCreate3 = FakeRequest(POST, API_PREFIX).withJsonBody(commandPersonFormat.writes(dtoPerson3))
    val responseRequest3 = route(app, requestCreate3).get
    val jsonContent3 = contentAsJson(responseRequest3)
    (jsonContent3 \ "respuesta").get.toString mustBe "4"
  }

}
