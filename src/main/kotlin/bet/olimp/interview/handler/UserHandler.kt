package bet.olimp.interview.handler

import bet.olimp.interview.entity.User
import bet.olimp.interview.service.UserServiceImpl
import org.http4k.core.Body
import org.http4k.core.Method
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.format.Jackson.auto
import org.http4k.routing.bind
import org.http4k.routing.path

val userService = UserServiceImpl()
const val PREFIX = "api/v1/users"
private val userResponseLens = Body.auto<User>().toLens()
private val usersResponseLens = Body.auto<List<User>>().toLens()
private val userRequestLens = Body.auto<User>().toLens()

val getAll = PREFIX bind Method.GET to {
    usersResponseLens.inject(userService.findAll(), Response(Status.OK))
}
val getById = "$PREFIX/{id}" bind Method.GET to {
    val userId = try {
        it.path("id")?.toInt() ?: -1
    } catch (e: Exception) {
        -1
    }
    if (userId != -1) {
        val user = userService.findById(userId)
        userResponseLens.inject(user, Response(Status.OK))
    } else {
        Response.invoke(Status.BAD_REQUEST).body("User ID is not correct")
    }
}

val saveUser = PREFIX bind Method.POST to {
    val user = parsingUserFromString(it.bodyString())
    userRequestLens.inject(userService.save(user), Response(Status.CREATED))
}

val updateUser = PREFIX bind Method.PUT to {
    val user = parsingUserFromString(it.bodyString())
    userRequestLens.inject(userService.save(user), Response(Status.OK))
}

val deleteUser = PREFIX bind Method.DELETE to {
    val user = parsingUserFromString(it.bodyString())
    if (user.id != -1) {
        userService.delete(user.id)
        Response.invoke(Status.NO_CONTENT)
    } else {
        Response.invoke(Status.BAD_REQUEST).body("User ID is not correct")
    }
}

private fun parsingUserFromString(str: String): User {
    val array = str.split("Content-Disposition: form-data; name=\"")
    var userId = -1
    var userName = ""
    for (i in array) {
        if (!i.contains("\"")) {
            continue
        }
        val res = Regex("[^A-Za-z0-9\" ]")
            .replace(i.substring(0, i.indexOf("----------------------------")), "")

        if (res.split("\"")[0] == "id") {
            userId = try {
                res.split("\"")[1].toInt()
            } catch (e: Exception) {
                -1
            }
        }
        if (res.split("\"")[0] == "name") {
            userName = res.split("\"")[1]
        }
    }
    return User(userId, userName)
}
