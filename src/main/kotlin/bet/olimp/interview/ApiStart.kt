package bet.olimp.interview

import bet.olimp.interview.handler.*
import org.http4k.routing.routes
import org.http4k.server.Undertow
import org.http4k.server.asServer

object ApiStart {

    @JvmStatic
    fun main(args: Array<String>) {
        routes(
            pingGetHandler,
            pingPostHandler,
            getAll,
            getById,
            saveUser,
            updateUser,
            deleteUser
        ).asServer(Undertow(8080))
            .start()
    }
}