
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class Money extends Simulation {

  val httpProtocol = http
    .baseURL("http://192.168.33.12:8080/money/")
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.(t|o)tf""", """.*\.png"""), WhiteList())
    .acceptHeader("""application/json, text/plain, */*""")
    .acceptEncodingHeader("""gzip, deflate""")
    .acceptLanguageHeader("""fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3""")
    .connection("""keep-alive""")
    .contentTypeHeader("""application/x-www-form-urlencoded; charset=UTF-8""")
    .userAgentHeader("""Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:32.0) Gecko/20100101 Firefox/32.0""")

  val headers_0 = Map(
    """Accept""" -> """text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8""",
    """Cache-Control""" -> """no-cache""",
    """Pragma""" -> """no-cache""",
    """X-Forwarded-For""" -> """217.70.184.38""")

  val headers_1 = Map(
    """Cache-Control""" -> """no-cache""",
    """Pragma""" -> """no-cache""",
    """X-Forwarded-For""" -> """217.70.184.38""")

  val headers_4 = Map("""X-Forwarded-For""" -> """217.70.184.38""")

    val uri1 = """http://localhost:8080"""

  val scn = scenario("RecordedSimulation")
    .exec(http("request_0")
      .get("""/""")
      .headers(headers_0)
      .resources(http("request_1")
      .get(uri1 + """/i18n/en.json""")
      .headers(headers_1)
      .check(status.is(404)),
            http("request_2")
      .get(uri1 + """/i18n/en.json""")
      .headers(headers_1),
            http("request_3")
      .get(uri1 + """/views/main.html""")
      .headers(headers_1)))
    .pause(8)
    .exec(http("request_4")
      .get("""/views/login.html""")
      .headers(headers_4))
    .pause(6)
    .exec(http("request_5")
      .post("""/app/authentication""")
      .headers(headers_1)
      .formParam("""j_username""", """admin""")
      .formParam("""j_password""", """admin""")
      .formParam("""_spring_security_remember_me""", """false""")
      .formParam("""submit""", """Login""")
      .check(status.is(401)))

  //setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
      
  setUp(
    scn.inject(
      nothingFor(4 seconds), // 1
      atOnceUsers(50), // 2
      rampUsers(100) over(5 seconds), // 3
      constantUsersPerSec(100) during(5 seconds), // 4
      constantUsersPerSec(100) during(5 seconds) randomized, // 5
      rampUsersPerSec(10) to(100) during(5 minutes) // 6
      //,splitUsers(1000) into(rampUsers(100) over(10 seconds)) separatedBy(10 seconds) // 8
      ).protocols(httpProtocol)
    )
}