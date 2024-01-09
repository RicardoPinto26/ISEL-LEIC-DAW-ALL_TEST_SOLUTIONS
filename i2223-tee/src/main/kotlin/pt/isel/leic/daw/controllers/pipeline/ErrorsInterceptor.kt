package pt.isel.leic.daw.controllers.pipeline

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.lang.Nullable
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import pt.isel.leic.daw.repositories.ErrorsRepository

@Component
class ErrorsInterceptor(val errorsRepository: ErrorsRepository) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        request.setAttribute("startTime", System.currentTimeMillis())
        return true
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        @Nullable modelAndView: ModelAndView?
    ) {
        if (response.status == 500) {
            errorsRepository.addErrorRequest(
                request.getAttribute("startTime") as Long,
                request.method,
                request.requestURI,
                if (handler is HandlerMethod) handler.beanType.simpleName else null
            )
        }
    }
}