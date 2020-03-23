package example

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Application
object Application {
  def main(args: Array[String]): Unit = {
    val app : Array[Class[_]] = Array(classOf[Application])
    SpringApplication.run(app, args)
  }
}