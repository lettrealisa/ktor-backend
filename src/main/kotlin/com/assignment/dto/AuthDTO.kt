package com.assignment.dto
import kotlinx.serialization.*

@Serializable
data class AuthDTO(val token: String, val user: UserDTO)

