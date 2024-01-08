package pt.isel.leic.daw.repositories

import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock
import org.springframework.stereotype.Component

@Component
class HandlersRepository {

    val handlers = HashMap<String, Int>()
    val mLock = ReentrantLock()
    fun getHandlers() = mLock.withLock { handlers.keys.toList().map { "/handlers/$it" } }
    fun getHandlerByID(id: String): Int = mLock.withLock { handlers[id] ?: 0 }

    fun incrementHandler(id: String): Int = mLock.withLock {
        val newExecutions = (handlers[id] ?: 0) + 1
        handlers[id] = newExecutions
        newExecutions
    }
}