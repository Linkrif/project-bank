package br.com.projectbank.service.jwt

import br.com.projectbank.domain.dto.UserAuthDto
import br.com.projectbank.domain.entity.User

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.Instant

@Service
class JwtServiceImpl : JwtService {
    @Value("\${jwt.token-secret}")
    val tokenSecret: String = ""
    override fun generateUserAuth(user: User): UserAuthDto {
        val expiresAt = Instant.now().plusMillis(Duration.ofHours(TOKEN_EXPIRATION_HOURS).toMillis())
        val token: String = JWT.create()
            .withSubject(user.getId().toString())
             .withExpiresAt(expiresAt)
            .withClaim("roles", user.roles.map { it.toString() })
            .sign(Algorithm.HMAC512(tokenSecret.toByteArray()))
        val dto = UserAuthDto()
        dto.roles = (user.roles)
        dto.token = (token)
        return dto
    }

    override fun decodeToken(token: String?): DecodedJWT {
        return try {
            val verifier: JWTVerifier = JWT.require(Algorithm.HMAC512(tokenSecret.toByteArray())).build()
            verifier.verify(token)
        } catch (ex: SignatureVerificationException) {
            log.error("Invalid JWT signature")
            throw RuntimeException("Invalid JWT signature")
        } catch (ex: TokenExpiredException) {
            log.error("Expired JWT token")
            throw RuntimeException("Expired JWT token")
        } catch (ex: JWTVerificationException) {
            log.error(ex.localizedMessage)
            throw RuntimeException(ex.localizedMessage)
        }
    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(JwtServiceImpl::class.java)
        private const val TOKEN_EXPIRATION_HOURS = 8L
    }
}