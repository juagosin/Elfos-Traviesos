package com.calleserpis.elfonavidenyo.data.local

import androidx.room.TypeConverter
import com.calleserpis.elfonavidenyo.domain.model.ElfTag
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.collections.map

class ElfAdventureTypeConverters {

    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromTagList(tags: List<ElfTag>): String =
        json.encodeToString(tags.map { ElfTagJson(it.label) })

    @TypeConverter
    fun toTagList(value: String): List<ElfTag> =
        json.decodeFromString<List<ElfTagJson>>(value)
            .map { ElfTag(label = it.label, colortype = 0L) }
}
