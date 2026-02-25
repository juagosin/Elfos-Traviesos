package com.calleserpis.elfontheshelf.data.mapper

import com.calleserpis.elfontheshelf.data.local.ElfAdventureEntity
import com.calleserpis.elfontheshelf.domain.model.ElfAdventure
import com.calleserpis.elfontheshelf.domain.model.ElfTag
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private val json = Json {
    ignoreUnknownKeys = true
}

@Serializable
private data class ElfTagDto(
    val label: String
)

private fun ElfTag.toDto() = ElfTagDto(
    label = label
)

private fun ElfTagDto.toElfTag() = ElfTag(
    label = label,
    colortype = 0L
)

// ── Mappers públicos ──────────────────────────────────────────────

fun ElfAdventureEntity.toDomain(): ElfAdventure {
    return ElfAdventure(
        id = id,
        title = title,
        imageRes = imageRes,
        tags = json.decodeFromString<List<ElfTagDto>>(tags).map { it.toElfTag() },
        description = description,
        isFavorite = isFavorite
    )
}

fun ElfAdventure.toEntity(): ElfAdventureEntity {
    return ElfAdventureEntity(
        id = id,
        title = title,
        imageRes = imageRes,
        tags = json.encodeToString(tags.map { it.toDto() }),
        description = description,
        isFavorite = isFavorite
    )
}

fun List<ElfAdventureEntity>.toDomain(): List<ElfAdventure> {
    return map { it.toDomain() }
}