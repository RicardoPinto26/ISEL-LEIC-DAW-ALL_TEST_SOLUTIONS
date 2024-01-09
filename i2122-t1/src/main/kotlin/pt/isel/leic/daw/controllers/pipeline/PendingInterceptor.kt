package pt.isel.leic.daw.controllers.pipeline

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import pt.isel.leic.daw.repositories.PendingRequest
import pt.isel.leic.daw.repositories.PendingServiceAndRepo

@Component
class PendingInterceptor(val pendingServiceAndRepo: PendingServiceAndRepo) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        request.setAttribute("pending", pendingServiceAndRepo.addPending(request.requestURI, request.method))
        return true
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        val pendingRequest = request.getAttribute("pending") as PendingRequest
        pendingServiceAndRepo.removePending(pendingRequest)
    }
}