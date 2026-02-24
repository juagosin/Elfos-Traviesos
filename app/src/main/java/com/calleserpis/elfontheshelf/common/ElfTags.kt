package com.calleserpis.elfontheshelf.common

import android.R.attr.label
import androidx.compose.ui.graphics.Color
import com.calleserpis.elfontheshelf.domain.model.ElfTag

object ElfTags {
    val MAGIC = ElfTag(label = "Magic", colortype = 0xFF7C4DFF)
    val ADVENTURE = ElfTag(label = "Adventure", colortype = 0xFF00BCD4)
    val FOREST = ElfTag(label = "Forest", colortype = 0xFF4CAF50)
    val SNOW = ElfTag(label = "Snow", colortype = 0xFF90CAF9)
    val DANGER = ElfTag(label = "Danger", colortype = 0xFFF44336)
    val MYSTERY = ElfTag(label = "Mystery", colortype = 0xFF9C27B0)
    val TREASURE = ElfTag(label = "Treasure", colortype = 0xFFFFD700)
    val FESTIVAL = ElfTag(label = "Festival", colortype = 0xFFFF8F00)

    val all = listOf(MAGIC, ADVENTURE, FOREST, SNOW, DANGER, MYSTERY, TREASURE, FESTIVAL)
}