package br.com.projectbank.domain.entity

import br.com.projectbank.domain.enums.RoleEnum

import javax.persistence.*
import javax.validation.constraints.NotBlank


@Entity(name = "tb_users")
class User(
    @NotBlank
    @Column(unique = true)
    var username: String = "",
    var password: String= "",
    var name : String = "",
    @ElementCollection(targetClass = RoleEnum::class, fetch = FetchType.EAGER)
    @CollectionTable(name = "tb_users_profiles")
    @Enumerated(EnumType.STRING)
    var roles: Set<RoleEnum> = HashSet(),
    var blocked : Boolean,
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    var client : Client?
) : BaseEntity()
{
    override fun getId() : Long{
        return this.id!!
    }
}