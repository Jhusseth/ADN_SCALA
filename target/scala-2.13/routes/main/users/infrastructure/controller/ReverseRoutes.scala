// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:5
package users.infrastructure.controller {

  // @LINE:5
  class ReversePersonQueryController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:5
    def list(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "v1/people")
    }
  
    // @LINE:6
    def findById(id:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "v1/people/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:7
    def findByName(name:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "v1/people/search/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("name", name)))
    }
  
  }

  // @LINE:8
  class ReversePersonCommandController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:8
    def create(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "v1/people")
    }
  
    // @LINE:9
    def update(): Call = {
      
      Call("PUT", _prefix + { _defaultPrefix } + "v1/people")
    }
  
    // @LINE:10
    def delete(id:Long): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "v1/people/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
  }


}
