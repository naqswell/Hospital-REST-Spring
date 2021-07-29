package com.naqswell.hospital.models.users


import com.naqswell.hospital.models.BaseEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
@Table(name = "users_t")
class HospitalUsersEntity(
        @Column(name = "login")
        var login: String,

        @Column(name = "hash")
        var hash: String,

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "users_roles",
                joinColumns = [JoinColumn(name = "user_id")],
                inverseJoinColumns = [JoinColumn(name = "role_id")])
        var roles: MutableList<RoleEntity> = mutableListOf()

) : BaseEntity<Int>(), UserDetails {
//) : BaseEntity<Int>() {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return roles
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return login
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return hash
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    fun copy(): HospitalUsersEntity =
            HospitalUsersEntity(
                    login, hash, roles
            )
}