package project.moojabi.server.error

abstract class BusinessException(
    open val status: Int,
    override val message: String
) : RuntimeException() {

    override fun fillInStackTrace() = this
}
