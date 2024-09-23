package br.com.projectbank.domain.entity


import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.io.Serial
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null) : Serializable {

    @CreationTimestamp
    private lateinit var createdAt: LocalDateTime

    @UpdateTimestamp
    private lateinit var updatedAt: LocalDateTime

    companion object {
        @Serial
        private val serialVersionUID = 1L
    }
    open fun getId() : Long{
        return this.id!!
    }
}