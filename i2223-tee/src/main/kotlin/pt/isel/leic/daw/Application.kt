package pt.isel.leic.daw

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import pt.isel.leic.daw.controllers.pipeline.ErrorsInterceptor

@SpringBootApplication
class Application

@Configuration
class InterceptorConfigurer(val errorsInterceptor: ErrorsInterceptor) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(errorsInterceptor)
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}