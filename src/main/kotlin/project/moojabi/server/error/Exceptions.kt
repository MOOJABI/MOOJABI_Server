package project.moojabi.server.error

sealed class Exceptions(
    override val status: Int,
    override val message: String
) : BusinessException(status, message) {

    class NotFound(message: String) : Exceptions(ErrorStatusCode.NOT_FOUND, message)

    class AlreadyExists(message: String) : Exceptions(ErrorStatusCode.ALREADY_EXISTS, message)
}

object ErrorStatusCode {
    const val NOT_FOUND = 404
    const val ALREADY_EXISTS = 409
}
