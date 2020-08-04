package example.controllers

import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController}

@RestController
//@RequestMapping(path = Array("/api"))
class PersonController {

  @GetMapping(path = Array("/api/"))
  def getUser(): String = {
    "testUser"
  }
}
