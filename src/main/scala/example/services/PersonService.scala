package example.services

import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.stereotype.{Component, Service}

@EnableScheduling
@Component
class PersonService {

  import com.fasterxml.jackson.core.JsonProcessingException
  import org.springframework.scheduling.annotation.Scheduled

  @Scheduled(fixedDelay = 5000)
  //@throws[JsonProcessingException]
  def runMe(): Unit = {
    //val payload = null
    print("scheduling works.\n")
  }
}
