package pt.isel.leic.daw.repositories

import org.springframework.stereotype.Component

@Component
class MyRepository {
    fun delay(seconds: Int): String {
        Thread.sleep(seconds * 1000L)
        return "pong"
    }
}