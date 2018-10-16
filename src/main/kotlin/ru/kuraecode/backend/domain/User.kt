package ru.kuraecode.backend.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*


@Entity
@Table(name = "usr")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        @Column(name = "login", unique = true, updatable = false)
        private var login: String = "",
        private var password: String = "",
        private var isEnable: Boolean = false,
        private var email: String = ""
) : UserDetails {
        override fun getUsername() = login
        override fun getAuthorities(): List<out GrantedAuthority> {
                return listOf(Role.ROLE_ADMIN, Role.ROLE_CLIENT)
        }
        override fun isEnabled() = true
        override fun isCredentialsNonExpired() = true
        override fun getPassword() = password
        override fun isAccountNonExpired() = true
        override fun isAccountNonLocked() = true
        fun getRoles() = listOf(Role.ROLE_ADMIN, Role.ROLE_CLIENT)
}