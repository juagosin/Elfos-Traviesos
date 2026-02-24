package com.calleserpis.elfontheshelf.data.mapper

import com.calleserpis.elfontheshelf.data.local.ElfAdventureEntity
import com.calleserpis.elfontheshelf.domain.model.ElfAdventure
import com.calleserpis.elfontheshelf.domain.model.ElfTag
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
private data class ElfTagDto(
    val label: String,
    val colorValue: Long
)

private fun ElfTag.toDto() = ElfTagDto(
    label = label,
    colorValue = colortype
)

private fun ElfTagDto.toElfTag() = ElfTag(
    label = label,
    colortype = colorValue
)

// ── Mappers públicos ──────────────────────────────────────────────

fun ElfAdventureEntity.toDomain(): ElfAdventure {
    return ElfAdventure(
        id = id,
        title = title,
        imageRes = imageRes,
        tags = Json.decodeFromString<List<ElfTagDto>>(tags).map { it.toElfTag() },
        description = description,
        isFavorite = isFavorite
    )
}

fun ElfAdventure.toEntity(): ElfAdventureEntity {
    return ElfAdventureEntity(
        id = id,
        title = title,
        imageRes = imageRes,
        tags = Json.encodeToString(tags.map { it.toDto() }),
        description = description,
        isFavorite = isFavorite
    )
}

fun List<ElfAdventureEntity>.toDomain(): List<ElfAdventure> {
    return map { it.toDomain() }
}