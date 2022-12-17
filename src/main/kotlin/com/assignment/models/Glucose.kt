package com.assignment.models

import com.assignment.dto.FoodDTO
import com.assignment.dto.GlucoseDTO
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object GlucoseData : IntIdTable() {
    val value: Column<Double> = double("value")
    val date: Column<LocalDateTime> = datetime("date")
}

class Glucose(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<Glucose>(GlucoseData)
    var value by GlucoseData.value
    var date by GlucoseData.date

    fun toGlucoseDataDTO() = GlucoseDTO(value, date)
}