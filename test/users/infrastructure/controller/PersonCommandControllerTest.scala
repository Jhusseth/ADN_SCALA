package users.infrastructure.controller

import org.scalatest.matchers.must.Matchers
import play.api.test.FakeRequest
import play.api.test.Helpers._
import users.application.command.PersonCommand
import users.infrastructure.base.AppTestKit
import users.infrastructure.controller.format.FormatApplication

import java.time.LocalDate

class PersonCommandControllerTest extends AppTestKit with Matchers with FormatApplication {

  val API_PREFIX: String = "/api/v1/people"

  "PersonCommandController" can {
    "crea una persona" when {
      "se invoca el servicio" in {

        val dtoPerson = PersonCommand(1L, Some("Test"),Some("Service"), Some("test@test.com"),
          Some(LocalDate.of(1995,11,12)))
        val request = FakeRequest(POST, API_PREFIX).withJsonBody(commandPersonFormat.writes(dtoPerson))

        val response = route(app, request).get

        status(response) mustBe (OK)
        val jsonContent = contentAsJson(response)
        (jsonContent \ "respuesta").get.toString mustBe "1"

      }

    }

    "actualiza una persona" when {
      "se invoca el servicio" in {

        val dtoPerson = PersonCommand(1L, Some("Test"),Some("Service"), Some("test@test.com"),
          Some(LocalDate.of(1995,11,12)))
        val request = FakeRequest(PUT, API_PREFIX).withJsonBody(commandPersonFormat.writes(dtoPerson))

        val response = route(app, request).get

        status(response) mustBe (OK)
        val jsonContent = contentAsJson(response)
        (jsonContent \ "respuesta").get.toString mustBe "1"

      }

    }

    "elimina una persona" when {
      "se invoca el servicio" in {

        val request = FakeRequest(DELETE, API_PREFIX + "/1")

        val response = route(app, request).get

        status(response) mustBe (OK)
        val jsonContent = contentAsJson(response)
        (jsonContent \ "respuesta").get.toString mustBe "1"

      }

    }
  }

}
