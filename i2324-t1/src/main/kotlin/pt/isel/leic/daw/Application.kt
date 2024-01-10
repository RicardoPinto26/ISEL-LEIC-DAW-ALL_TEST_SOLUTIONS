package pt.isel.leic.daw

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
/*
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import pt.isel.leic.daw.controllers.pipeline.FailureInterceptor
*/
@SpringBootApplication
class Application
/*
@Configuration
class PipelineConfigurer(val failureInterceptor: FailureInterceptor) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(failureInterceptor)
    }
}
*/
fun main(args: Array<String>) {
    runApplication<Application>(*args)
}