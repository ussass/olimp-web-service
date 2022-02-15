package bet.olimp.interview.entity

data class ErrorDescription(
    val className: String,
    val message: String?,
    val stackTrace: Array<StackTraceElement>
)
