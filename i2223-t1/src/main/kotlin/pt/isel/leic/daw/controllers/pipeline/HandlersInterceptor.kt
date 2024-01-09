package pt.isel.leic.daw.controllers.pipeline

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import pt.isel.leic.daw.repositories.HandlersRepository

@Component
class HandlersInterceptor(val handlersRepository: HandlersRepository) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        handler as HandlerMethod
        handlersRepository.incrementHandler(handler.shortLogMessage)
        return true
    }
}