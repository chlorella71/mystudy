package bitcamp.myapp.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

  private static final Log log = LogFactory.getLog(HomeController.class);

  public HomeController() {
    log.debug("HomeController() 호출됨");

  }

  @GetMapping("/home")
    public void home() {
  }
}