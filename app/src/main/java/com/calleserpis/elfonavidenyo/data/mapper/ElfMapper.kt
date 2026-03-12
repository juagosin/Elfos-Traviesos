package com.calleserpis.elfonavidenyo.data.mapper

import com.calleserpis.elfonavidenyo.data.local.ElfAdventureEntity
import com.calleserpis.elfonavidenyo.domain.model.ElfAdventure
import com.calleserpis.elfonavidenyo.domain.model.ElfTag
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.Locale

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
    val isSpanish = Locale.getDefault().language == "es"

    val resolvedTitle = when {
        isSpanish -> title
        !title_en.isNullOrBlank() -> title_en
        else -> title
    }

    val resolvedDescription = when {
        isSpanish -> description
        !description_en.isNullOrBlank() -> description_en
        else -> description
    }

    return ElfAdventure(
        id = id,
        title = resolvedTitle,
        imageRes = imageRes,
        tags = json.decodeFromString<List<ElfTagDto>>(tags).map { it.toElfTag() },
        description = resolvedDescription,
        isFavorite = isFavorite
    )
}

fun ElfAdventure.toEntity(): ElfAdventureEntity {
    return ElfAdventureEntity(
        id = id,
        title = title,
        title_en = null,
        imageRes = imageRes,
        tags = json.encodeToString(tags.map { it.toDto() }),
        description = description,
        description_en = null,
        isFavorite = isFavorite
    )
}

fun List<ElfAdventureEntity>.toDomain(): List<ElfAdventure> {
    return map { it.toDomain() }
}