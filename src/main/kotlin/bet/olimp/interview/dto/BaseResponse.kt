package bet.olimp.interview.dto

import java.time.Instant

open class BaseResponse() {
    val timestamp: Long = Instant.now().epochSecond
}