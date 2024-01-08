package pt.isel.leic.daw.controllers.pipeline

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.lang.System.nanoTime
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView

@Component
class LoggerInteceptor : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val currentTime = nanoTime()
        request.setAttribute("startTime", currentTime)
        return true
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        logger.info("HTTP method::${request.method}")
        logger.info("Request URI::${request.requestURI}")
        logger.info("Response status::${response.status}")
        if (handler is HandlerMethod) logger.info("Handler::${handler.shortLogMessage}")
        logger.info("Processing time::${(nanoTime() - request.getAttribute("startTime") as Long) * 0.000001}ms")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(LoggerInteceptor::class.java)
    }
}