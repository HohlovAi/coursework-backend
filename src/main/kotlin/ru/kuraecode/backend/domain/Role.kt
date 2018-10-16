package ru.kuraecode.backend.domain

import org.springframework.security.core.GrantedAuthority

enum class Role : GrantedAuthority {

    ROLE_ADMIN {
        override fun getAuthority() = name
    },

    ROLE_CLIENT {
        override fun getAuthority() = name
    };

}