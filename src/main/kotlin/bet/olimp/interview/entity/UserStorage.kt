package bet.olimp.interview.entity

class UserStorage private constructor() {

    private val data = mutableListOf<User>()

    companion object {
        private var storage: UserStorage? = null

        fun getInstance(): UserStorage {
            storage?.let {
                return it
            }
            val instance = UserStorage()
            storage = instance
            return instance
        }
    }

    fun insertData(user: User) {
        data.add(user)
    }

    fun update(user: User) {
        data[user.id] = user
    }

    fun delete(userId: Int) {
        val user = User(userId, "")
        data[userId] = user
    }

    fun findAll() = data

    fun findById(userId: Int) = data[userId]

}