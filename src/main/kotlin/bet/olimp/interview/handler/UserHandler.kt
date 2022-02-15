package bet.olimp.interview.handler

import bet.olimp.interview.entity.User
import org.http4k.core.Method
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind
import org.http4k.routing.path

const val prefix = "api/v1/users"

val getAll = prefix bind Method.GET to {
    println("get all")
    Response.invoke(Status.OK).body("get all")
}
val getById = "$prefix/{id}" bind Method.GET to {
    println("get by id: " + it.path("id"))
    Response.invoke(Status.OK).body("get by id: " + it.path("id"))
}

val saveUser = prefix bind Method.POST to {
    val user = parsingUserFromString(it.bodyString())
    println(user.toString())
    Response.invoke(Status.CREATED).body(user.toString())
}

val updateUser = prefix bind Method.PUT to {
    val user = parsingUserFromString(it.bodyString())
    println(user.toString())
    Response.invoke(Status.CREATED).body(user.toString())
}

val deleteUser = prefix bind Method.DELETE to {
    val user = parsingUserFromString(it.bodyString())
    println(user.toString())
    Response.invoke(Status.CREATED).body(user.toString())
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

        if (res.split("\"")[0] == "id"){
            userId = res.split("\"")[1].toInt()
        }
        if (res.split("\"")[0] == "name"){
            userName = res.split("\"")[1]
        }
    }
    return User(userId, userName)
}