// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:5
package users.infrastructure.controller.javascript {

  // @LINE:5
  class ReversePersonQueryController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:5
    def list: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "users.infrastructure.controller.PersonQueryController.list",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/people"})
        }
      """
    )
  
    // @LINE:6
    def findById: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "users.infrastructure.controller.PersonQueryController.findById",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/people/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:7
    def findByName: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "users.infrastructure.controller.PersonQueryController.findByName",
      """
        function(name0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/people/search/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("name", name0))})
        }
      """
    )
  
  }

  // @LINE:8
  class ReversePersonCommandController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:8
    def create: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "users.infrastructure.controller.PersonCommandController.create",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/people"})
        }
      """
    )
  
    // @LINE:9
    def update: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "users.infrastructure.controller.PersonCommandController.update",
      """
        function() {
          return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/people"})
        }
      """
    )
  
    // @LINE:10
    def delete: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "users.infrastructure.controller.PersonCommandController.delete",
      """
        function(id0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "v1/people/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
  }


}
