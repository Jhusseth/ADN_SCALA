// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package users.infrastructure.controller;

import router.RoutesPrefix;

public class routes {
  
  public static final users.infrastructure.controller.ReversePersonQueryController PersonQueryController = new users.infrastructure.controller.ReversePersonQueryController(RoutesPrefix.byNamePrefix());
  public static final users.infrastructure.controller.ReversePersonCommandController PersonCommandController = new users.infrastructure.controller.ReversePersonCommandController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final users.infrastructure.controller.javascript.ReversePersonQueryController PersonQueryController = new users.infrastructure.controller.javascript.ReversePersonQueryController(RoutesPrefix.byNamePrefix());
    public static final users.infrastructure.controller.javascript.ReversePersonCommandController PersonCommandController = new users.infrastructure.controller.javascript.ReversePersonCommandController(RoutesPrefix.byNamePrefix());
  }

}
