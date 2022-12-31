package com.assignment.dto

import kotlinx.serialization.*
import io.ktor.server.auth.*

@Serializable
data class JwtDTO(val token: String) : Principal
