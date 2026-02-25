package com.calleserpis.elfontheshelf.common

import com.calleserpis.elfontheshelf.domain.model.ElfTag
import okhttp3.internal.cache.DiskLruCache.Companion.MAGIC

object ElfTags {
    // Catálogo fijo de tags y sus colores
    val FOOD = ElfTag(label = "FOOD", colortype = 0xFF7C4DFF)
    val ADVENTURE = ElfTag(label = "ADVENTURE", colortype = 0xFF00BCD4)
    val FOREST = ElfTag(label = "FOREST", colortype = 0xFF4CAF50)
    val SNOW = ElfTag(label = "SNOW", colortype = 0xFF90CAF9)
    val DANGER = ElfTag(label = "DANGER", colortype = 0xFFF44336)
    val MYSTERY = ElfTag(label = "MYSTERY", colortype = 0xFF9C27B0)
    val TREASURE = ElfTag(label = "TREASURE", colortype = 0xFFFFD700)
    val FESTIVAL = ElfTag(label = "FESTIVAL", colortype = 0xFFFF8F00)

    val INDOOR = ElfTag(label = "INDOOR", colortype = 0xFF4DB6AC)
    val EASY = ElfTag(label = "EASY", colortype = 0xFF81C784)
    val MEDIUM = ElfTag(label = "MEDIUM", colortype = 0xFFF39F18)
    val HARD = ElfTag(label = "HARD", colortype = 0xFFFF4D54)

    val all = listOf(
        FOOD,
        ADVENTURE,
        FOREST,
        SNOW,
        DANGER,
        MYSTERY,
        TREASURE,
        FESTIVAL,
        INDOOR,
        EASY,
        MEDIUM,
        HARD
    )

    fun findByLabel(label: String): ElfTag? =
        all.firstOrNull { it.label.equals(label, ignoreCase = true) }
}