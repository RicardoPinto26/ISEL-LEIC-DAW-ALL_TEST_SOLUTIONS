package pt.isel.leic.daw

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import pt.isel.leic.daw.controllers.pipeline.HandlersInterceptor

@SpringBootApplication
class Application

@Configuration
class PipelineConfigurer(val handlersInterceptor: HandlersInterceptor) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(handlersInterceptor)
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}