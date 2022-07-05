package users.infrastructure.common.error

trait ErrorType extends Product with Serializable

trait ErrorTypeApplication extends ErrorType

object ErrorTypeApplication {

  def apply(value: String): ErrorTypeApplication = value match {
    case "Tecnico" => Technical
    case "Negocio" => Business
    case "Aplicacion" => Application
    case _ => TypeErrorUnknown
  }

  def unapply(value: ErrorTypeApplication): String = value match {
    case Technical => "Tecnico"
    case Business => "Negocio"
    case Application => "Aplicacion"
    case _ => "Error no reconocido"
  }

}

case object Technical extends ErrorTypeApplication

case object Business extends ErrorTypeApplication

case object Application extends ErrorTypeApplication

case object TypeErrorUnknown extends ErrorTypeApplication
