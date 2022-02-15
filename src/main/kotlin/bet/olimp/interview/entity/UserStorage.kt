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

    fun findAll(): List<User> {
        val resultList = mutableListOf<User>()
        for (user in data) {
            if (user.name != "") {
                resultList.add(user)
            }
        }
        return resultList
    }

    fun findById(userId: Int) = data[userId]

    fun dataSize() = data.size
}