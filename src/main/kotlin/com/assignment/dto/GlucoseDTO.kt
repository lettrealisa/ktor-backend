package com.assignment.dto
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.*

@Serializable
data class GlucoseDTO(val value: Double, val date: LocalDateTime)
