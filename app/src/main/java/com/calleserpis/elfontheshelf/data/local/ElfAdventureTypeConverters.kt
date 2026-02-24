package com.calleserpis.elfontheshelf.data.local

import androidx.compose.ui.graphics.Color
import androidx.room.TypeConverter
import com.calleserpis.elfontheshelf.domain.model.ElfTag
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.collections.map

class ElfAdventureTypeConverters {

    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromTagList(tags: List<ElfTag>): String =
        json.encodeToString(tags.map { ElfTagJson(it.label, it.colortype.value.toLong()) })

    @TypeConverter
    fun toTagList(value: String): List<ElfTag> =
        json.decodeFromString<List<ElfTagJson>>(value)
            .map { ElfTag(it.label, Color(it.colorLong.toULong())) }
}
