package pt.isel.leic.daw.repositories

import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock
import org.springframework.stereotype.Component

class PendingRequest(
    val uri: String,
    val method: String
)

data class GetPendingDTO(
    val pendingGET: List<PendingRequest>,
    val pendingPOST: List<PendingRequest>,
    val pendingPUT: List<PendingRequest>,
    val pendingDELETE: List<PendingRequest>
)

@Component
class PendingServiceAndRepo {
    private val mLock = ReentrantLock()

    private val requests = mutableListOf<PendingRequest>()

    fun addPending(uri: String, method: String): PendingRequest {
        val pendingRequest = PendingRequest(uri, method)
        addPending(pendingRequest)
        return pendingRequest
    }

    private fun addPending(pendingRequest: PendingRequest) = mLock.withLock {
        requests.add(pendingRequest)
    }

    fun removePending(pendingRequest: PendingRequest) = mLock.withLock {
        requests.remove(pendingRequest)
    }

    fun getPending(): GetPendingDTO = mLock.withLock {
        GetPendingDTO(
            requests.filter { it.method == "GET" },
            requests.filter { it.method == "POST" },
            requests.filter { it.method == "PUT" },
            requests.filter { it.method == "DELETE" }
        )
    }
}