package com.coelhocaique.finance.gateway.client.user

import java.time.LocalDateTime
import java.util.*

data class UserAuthenticationRequest(
        val user: String? = null,
        val password: String? = null
)

data class UserCreationRequest(
        val username: String? = null,
        val firstName: String? = null,
        val lastName: String? = null,
        val email: String? = null,
        val password: String? = null
)

data class UserResponse (
        val userId: UUID,
        val username: String,
        val firstName: String,
        val lastName: String,
        val email: String,
        val password: String? = null,
        val creationDate: LocalDateTime? = null,
        val accounts: List<AccountResponse>? = null,
        val links: List<Map<String, String>>? = null
)

data class AccountResponse (
        val accountId: UUID,
        val userId: UUID,
        val type: String,
        val creationDate: LocalDateTime? = null,
        val links: List<Map<String, String>>? = null
)