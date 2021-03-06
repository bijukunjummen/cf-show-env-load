package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class SimpleSimulation extends Simulation {

  val baseUrl = s"http://cf-show-env.local.pcfdev.io";

  val httpConf = http.baseURL(baseUrl)

  val headers = Map("Accept" -> """application/json""")

  val healthPage = repeat(60) {
    exec(http("health")
      .get("/health"))
      .pause(2, 8)
  }

  val scn = scenario("Get the Health Page")
    .exec(healthPage)



  setUp(scn.inject(atOnceUsers(5)).protocols(httpConf))
}
