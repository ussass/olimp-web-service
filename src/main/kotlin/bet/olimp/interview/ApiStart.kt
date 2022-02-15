package bet.olimp.interview

import bet.olimp.interview.entity.ErrorDescription
import bet.olimp.interview.handler.*
import org.http4k.core.*
import org.http4k.format.Jackson.auto
import org.http4k.routing.routes
import org.http4k.server.Undertow
import org.http4k.server.asServer

object ApiStart {

    @JvmStatic
    fun main(args: Array<String>) {

        val catchAll = Filter { nextHandler ->
            val wrapperHandler: HttpHandler = { request ->
                try {
                    nextHandler(request)
                } catch (e: Exception) {
                    val errorDescription = ErrorDescription(e.javaClass.name, e.message, e.stackTrace)
                    Body.auto<ErrorDescription>().toLens().inject(errorDescription, Response(Status.INTERNAL_SERVER_ERROR))
                }
            }
            wrapperHandler
        }

        val httpHandler: HttpHandler = routes(
            pingGetHandler,
            pingPostHandler,
            getAll,
            getById,
            saveUser,
            updateUser,
            deleteUser
        ).withFilter(catchAll)
        httpHandler.asServer(Undertow(8080)).start()
    }
}
