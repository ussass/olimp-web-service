package bet.olimp.interview.repositories

import bet.olimp.interview.entity.User

interface UserRepository {

    fun findAll(): List<User>

    fun findById(id: Int): User

    fun save(user: User): User

    fun update(user: User): User

    fun delete(id: Int)
}
