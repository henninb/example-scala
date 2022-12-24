package example.services

import org.springframework.stereotype.{Component, Service}
import com.fasterxml.jackson.core.JsonProcessingException
import org.springframework.scheduling.annotation.Scheduled

@Component
class ScheduledJobService {
  @Scheduled(fixedDelay = 5000)
  def runMe(): Unit = {
    print("scheduling works.\n")
  }
}
