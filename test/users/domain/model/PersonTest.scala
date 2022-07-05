package users.domain.model

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AsyncWordSpec
import users.domain.common.error.DomainErrors

import java.time.LocalDate

class PersonTest extends AsyncWordSpec with Matchers {

  "Person" can {
    "generate error" when {
      "no se envia nombre" in {
        val response = Person.createPersonValidate(1L, None,Some(""), Some(""),
          Some(LocalDate.of(1995,11,12)))

        response match {
          case Right(s) => fail()
          case Left(errorDominio) => errorDominio.asInstanceOf[DomainErrors].errors.head.message mustBe "nombre obligatorio"
        }

      }
      "no se envia el apellido" in {
        val respuesta = Person.createPersonValidate(1L, Some(""),None, Some(""),
          Some(LocalDate.of(1995,11,12)))

        respuesta match {
          case Right(s) => fail()
          case Left(errorDominio) => errorDominio.asInstanceOf[DomainErrors].errors.head.message mustBe "apellido obligatorio"
        }
      }
      "no se envia el email" in {
        val respuesta = Person.createPersonValidate(1L, Some(""),Some(""), None,
          Some(LocalDate.of(1995,11,12)))

        respuesta match {
          case Right(s) => fail()
          case Left(errorDominio) => errorDominio.asInstanceOf[DomainErrors].errors.head.message mustBe "email obligatorio"
        }
      }
      "no se envia la fecha de nacimiento" in {
        val respuesta = Person.createPersonValidate(1L, Some(""),Some(""), Some(""), None)

        respuesta match {
          case Right(s) => fail()
          case Left(errorDominio) => errorDominio.asInstanceOf[DomainErrors].errors.head.message mustBe "fecha de nacimiento obligatoria"
        }
      }
      "es menor de edad" in {
        val respuesta = Person.createPersonValidate(1L, Some(""),Some(""), Some(""), Some(LocalDate.now()))

        respuesta match {
          case Right(s) => fail()
          case Left(errorDominio) => errorDominio.asInstanceOf[DomainErrors].errors.head.message mustBe "debe ser mayor de edad"
        }
      }
    }
    "crearse" when {
      "crearse cando se envian parametros correctos" in {
        val respuesta = Person.createPersonValidate(1L, Some(""),Some(""), Some(""),
          Some(LocalDate.of(1995,11,12)))

        respuesta match {
          case Right(s) => s.id mustBe 1L
          case Left(_) => fail("respuesta inesperada")
        }
      }
    }

  }

}
