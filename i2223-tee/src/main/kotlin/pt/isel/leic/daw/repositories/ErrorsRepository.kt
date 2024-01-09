package pt.isel.leic.daw.repositories

import java.lang.System.currentTimeMillis
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock
import org.springframework.stereotype.Component

data class ErrorRequest(
    val timestamp: Long,
    val method: String,
    val uri: String,
    val controller: String?
)

@Component
class ErrorsRepository {
    private val mLock = ReentrantLock()
    private val requests = mutableListOf<ErrorRequest>()

    fun addErrorRequest(
        timestamp: Long,
        method: String,
        uri: String,
        controller: String?
    ) = addErrorRequest(ErrorRequest(timestamp, method, uri, controller))

    fun addErrorRequest(request: ErrorRequest) =
        mLock.withLock { requests.add(request) }

    fun getErrors() = mLock.withLock { requests.filter { it.timestamp >= currentTimeMillis() - 10 * 60 * 1000 } }
}