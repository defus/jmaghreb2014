
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class Money extends Simulation {

	val httpProtocol = http
		.baseURL("http://localhost:8080")
		.inferHtmlResources(BlackList(""".*192.168.33.13.*""", """.*localhost:9100.*"""), WhiteList())
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
		"""Accept""" -> """image/png,image/*;q=0.8,*/*;q=0.5""",
		"""Cache-Control""" -> """no-cache""",
		"""Pragma""" -> """no-cache""",
		"""X-Forwarded-For""" -> """217.70.184.38""")

	val headers_3 = Map(
		"""Cache-Control""" -> """no-cache""",
		"""Pragma""" -> """no-cache""",
		"""X-Forwarded-For""" -> """217.70.184.38""")

	val headers_8 = Map("""X-Forwarded-For""" -> """217.70.184.38""")

    val uri1 = """http://localhost:8080"""

	val scn = scenario("Money")
		.exec(http("request_0")
			.get("""/money-0.0.1-SNAPSHOT/""")
			.headers(headers_0)
			.resources(http("request_1")
			.get(uri1 + """/money-0.0.1-SNAPSHOT/images/development_ribbon.png""")
			.headers(headers_1),
            http("request_2")
			.get(uri1 + """/money-0.0.1-SNAPSHOT/bower_components/bootstrap-sass/vendor/assets/fonts/bootstrap/glyphicons-halflings-regular.woff""")
			.headers(headers_0),
            http("request_3")
			.get(uri1 + """/money-0.0.1-SNAPSHOT/i18n/en.json""")
			.headers(headers_3),
            http("request_4")
			.get(uri1 + """/i18n/en.json""")
			.headers(headers_3)
			.check(status.is(404)),
            http("request_5")
			.get(uri1 + """/money-0.0.1-SNAPSHOT/app/rest/operations?page=0&size=100&sortOrder=ASC""")
			.headers(headers_3)
			.check(status.is(401)),
            http("request_6")
			.get(uri1 + """/money-0.0.1-SNAPSHOT/protected/authentication_check.gif""")
			.headers(headers_3)
			.check(status.is(401)),
            http("request_7")
			.get(uri1 + """/money-0.0.1-SNAPSHOT/views/main.html""")
			.headers(headers_3),
            http("request_8")
			.get(uri1 + """/money-0.0.1-SNAPSHOT/app/rest/operations?page=0&size=100&sortOrder=ASC""")
			.headers(headers_8)
			.check(status.is(401)),
            http("request_9")
			.get(uri1 + """/money-0.0.1-SNAPSHOT/images/operation.png""")
			.headers(headers_1)))
		.pause(6)
		.exec(http("request_10")
			.get("""/money-0.0.1-SNAPSHOT/protected/authentication_check.gif""")
			.headers(headers_8)
			.check(status.is(401)))
		.pause(5)
		.exec(http("request_11")
			.post("""/money-0.0.1-SNAPSHOT/app/authentication""")
			.headers(headers_3)
			.formParam("""j_username""", """user""")
			.formParam("""j_password""", """user""")
			.formParam("""_spring_security_remember_me""", """false""")
			.formParam("""submit""", """Login""")
			.resources(http("request_12")
			.get(uri1 + """/money-0.0.1-SNAPSHOT/app/rest/account""")
			.headers(headers_8),
            http("request_13")
			.get(uri1 + """/money-0.0.1-SNAPSHOT/protected/authentication_check.gif""")
			.headers(headers_8),
            http("request_14")
			.get(uri1 + """/money-0.0.1-SNAPSHOT/app/rest/operations?page=0&size=100&sortOrder=ASC""")
			.headers(headers_8),
            http("request_15")
			.get(uri1 + """/money-0.0.1-SNAPSHOT/app/rest/operations?page=0&size=100&sortOrder=ASC""")
			.headers(headers_8),
            http("request_16")
			.get(uri1 + """/money-0.0.1-SNAPSHOT/app/rest/operations?page=0&size=100&sortOrder=ASC""")
			.headers(headers_8)))
		.pause(2)
		.exec(http("request_17")
			.get("""/money-0.0.1-SNAPSHOT/app/rest/operationcategories""")
			.headers(headers_8)
			.resources(http("request_18")
			.get(uri1 + """/money-0.0.1-SNAPSHOT/app/rest/operations""")
			.headers(headers_8)))
		.pause(1)
		.exec(http("request_19")
			.get("""/money-0.0.1-SNAPSHOT/app/rest/operations?page=1&size=20""")
			.headers(headers_8)
			.resources(http("request_20")
			.get(uri1 + """/money-0.0.1-SNAPSHOT/app/rest/operations?page=2&size=20""")
			.headers(headers_8),
            http("request_21")
			.get(uri1 + """/money-0.0.1-SNAPSHOT/app/rest/operations?page=3&size=20""")
			.headers(headers_8)))
		.pause(1)
		.exec(http("request_22")
			.get("""/money-0.0.1-SNAPSHOT/app/rest/budgets""")
			.headers(headers_8)
			.resources(http("request_23")
			.get(uri1 + """/money-0.0.1-SNAPSHOT/app/rest/operationcategories""")
			.headers(headers_8)))
		.pause(3)
		.exec(http("request_24")
			.get("""/money-0.0.1-SNAPSHOT/app/rest/operationcategories""")
			.headers(headers_8))
		.pause(3)
		.exec(http("request_25")
			.get("""/money-0.0.1-SNAPSHOT/app/logout""")
			.headers(headers_8)
			.resources(http("request_26")
			.get(uri1 + """/money-0.0.1-SNAPSHOT/app/rest/operations?page=0&size=100&sortOrder=ASC""")
			.headers(headers_8)
			.check(status.is(401)),
            http("request_27")
			.get(uri1 + """/money-0.0.1-SNAPSHOT/app/rest/account""")
			.headers(headers_8)
			.check(status.is(401)),
            http("request_28")
			.get(uri1 + """/money-0.0.1-SNAPSHOT/app/rest/account""")
			.headers(headers_8)
			.check(status.is(401))))

	//setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
      
  setUp(
    scn.inject(
      //nothingFor(4 seconds), // 1
      //atOnceUsers(10), // 2
      rampUsers(30) over(30 seconds) // 3
      //,nothingFor(20 seconds), // 1
      //constantUsersPerSec(1) during(30 seconds) // 4
      //,constantUsersPerSec(10) during(5 seconds) randomized // 5
      //,rampUsersPerSec(10) to(100) during(2 minutes) // 6
      //,splitUsers(1000) into(rampUsers(100) over(10 seconds)) separatedBy(10 seconds) // 8
      ).protocols(httpProtocol)
   )   
}