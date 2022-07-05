package users.infrastructure.common.error

import play.api.libs.json.Json

import java.text.SimpleDateFormat
import java.util.Calendar

object Error {
  def generateErrorMessage(message: String, errorType: ErrorType) = {
    Json.obj("codigo" -> getCode, "tipo" -> errorType.toString, "mensaje" -> message)
  }

  private def getCode: String = {
    s"ERR-$getDate"
  }

  private def getDate: String = {
    val now = Calendar.getInstance().getTime()
    val format = new SimpleDateFormat("yyMMddHHmmssSSS")
    format.format(now)
  }
}
