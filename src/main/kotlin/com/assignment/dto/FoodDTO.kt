package com.assignment.dto
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.*

@Serializable
data class FoodDTO(val desc: String, val date: LocalDateTime)
