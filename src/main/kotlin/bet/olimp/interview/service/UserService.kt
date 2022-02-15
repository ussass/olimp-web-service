package bet.olimp.interview.service

import bet.olimp.interview.entity.User

interface UserService {

    fun findAll(): List<User>

    fun findById(id: Int): User

    fun save(user: User): User

    fun delete(id: Int)
}
