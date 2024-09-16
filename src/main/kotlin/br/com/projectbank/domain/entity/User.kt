package br.com.projectbank.domain.entity

import br.com.projectbank.domain.enums.RoleEnum

import javax.persistence.*
import javax.validation.constraints.NotBlank


@Entity(name = "tb_user")
class User(
    @NotBlank
    @Column(unique = true)
    var username: String? = null,
    var password: String? = null,
    @NotBlank
    var name: String? = null,
    @ElementCollection(targetClass = RoleEnum::class, fetch = FetchType.EAGER)
    @CollectionTable(name = "users_profiles")
    @Enumerated(EnumType.STRING)
    var roles: Set<RoleEnum> = HashSet()
) : BaseEntity()
{
    override fun getId() : Long{
        return this.id!!
    }
}