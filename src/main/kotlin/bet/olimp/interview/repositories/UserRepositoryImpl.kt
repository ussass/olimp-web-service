package bet.olimp.interview.repositories

import bet.olimp.interview.entity.User
import bet.olimp.interview.entity.UserStorage

class UserRepositoryImpl : UserRepository {

    private var userStorage = UserStorage.getInstance()


    override fun findAll(): List<User> {
        return userStorage.findAll()
    }

    override fun findById(id: Int): User {
        return userStorage.findById(id)
    }

    override fun save(user: User): User {
        val newUser = User(userStorage.findAll().size, user.name)
        userStorage.insertData(newUser)
        return newUser
    }

    override fun update(user: User): User {
        userStorage.update(user)
        return user
    }

    override fun delete(id: Int) {
        userStorage.delete(id)
    }
}