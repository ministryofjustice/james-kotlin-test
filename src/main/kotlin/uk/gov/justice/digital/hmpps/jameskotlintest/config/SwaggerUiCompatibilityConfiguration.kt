package uk.gov.justice.digital.hmpps.jameskotlintest.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.OncePerRequestFilter

@Configuration
class SwaggerUiCompatibilityConfiguration {
  @Bean
  fun swaggerUiCompatibilityFilter(): OncePerRequestFilter = object : OncePerRequestFilter() {
    override fun shouldNotFilter(request: HttpServletRequest): Boolean = !request.requestURI.startsWith("/swagger-ui/")

    override fun doFilterInternal(
      request: HttpServletRequest,
      response: HttpServletResponse,
      filterChain: FilterChain,
    ) {
      val pathSuffix = request.requestURI.removePrefix("/swagger-ui/")
      request.getRequestDispatcher("/webjars/swagger-ui/$pathSuffix").forward(request, response)
    }
  }
}
