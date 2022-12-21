package com.assignment.dto

import kotlinx.serialization.*
import kotlinx.datetime.LocalDateTime

@Serializable
data class UserDTO(val name: String, val age: Int, val job: String, val pet: String, val date: LocalDateTime, val role: RoleDTO)
