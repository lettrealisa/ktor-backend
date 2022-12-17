package com.assignment.models

import com.assignment.dto.FoodDTO
import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object Foods : IntIdTable() {
    val desc: Column<String> = varchar("desc", 255)
    val date: Column<LocalDateTime> = datetime("date")
}

class Food(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<Food>(Foods)
    var desc by Foods.desc
    var date by Foods.date

    fun toFoodsDTO() = FoodDTO(desc, date)
}