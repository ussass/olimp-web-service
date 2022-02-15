package bet.olimp.interview.service

import bet.olimp.interview.entity.User
import bet.olimp.interview.repositories.UserRepositoryImpl

class UserServiceImpl : UserService {

    private val userRepository = UserRepositoryImpl()

    override fun findAll(): List<User> {
        return userRepository.findAll()
    }

    override fun findById(id: Int): User {
        return userRepository.findById(id)
    }

    override fun save(user: User): User {
        return if (user.id == -1) {
            userRepository.save(user)
        } else {
            userRepository.update(user)
        }
    }

    override fun delete(id: Int) {
        userRepository.delete(id)
    }
}
