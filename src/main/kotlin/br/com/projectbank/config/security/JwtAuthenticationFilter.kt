package br.com.projectbank.config.security

import br.com.projectbank.service.jwt.JwtService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class JwtAuthenticationFilter : OncePerRequestFilter() {
    @Autowired
    lateinit var jwtService: JwtService

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val bearerToken = request.getHeader("Authorization")
            if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
                val token = bearerToken.substring("Bearer ".length)
                val decodedJWT = jwtService!!.decodeToken(token)
                val subject = decodedJWT!!.subject
                val roles = decodedJWT.getClaim("roles").asArray(String::class.java)
                val authorities: MutableCollection<SimpleGrantedAuthority> = ArrayList()
                Arrays.stream(roles).forEach { role: String ->
                    authorities.add(
                        SimpleGrantedAuthority("ROLE_$role")
                    )
                }
                val authentication = UsernamePasswordAuthenticationToken(
                    subject,
                    null, authorities
                )
                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (ex: Exception) {
            log.error("Could not set user authentication in security context", ex)
        }
        filterChain.doFilter(request, response)
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(JwtAuthenticationFilter::class.java)
    }
}