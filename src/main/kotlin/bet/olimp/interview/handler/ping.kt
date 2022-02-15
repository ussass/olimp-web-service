package bet.olimp.interview.handler

import bet.olimp.interview.dto.PingRequest
import bet.olimp.interview.dto.PongResponse
import org.http4k.core.Body
import org.http4k.core.Method
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.format.Jackson.auto
import org.http4k.routing.bind
import java.util.*
import kotlin.math.absoluteValue

private val random = Random()
private val pingRequestLens = Body.auto<PingRequest>().toLens()
private val pongResponseLens = Body.auto<PongResponse>().toLens()

val pingGetHandler = "ping" bind Method.GET to {
    val pong = PongResponse(random.nextInt().absoluteValue)
    pongResponseLens.inject(pong, Response(OK))
}
val pingPostHandler = "ping" bind Method.POST to { req ->
    val request = pingRequestLens(req)
    val pong = PongResponse(request.userId)
    pongResponseLens.inject(pong, Response(OK))
}
