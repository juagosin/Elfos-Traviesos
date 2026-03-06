package com.calleserpis.elfonavidenyo.ui.screens.ideas

import androidx.annotation.StringRes
import kotlinx.serialization.Serializable

@Serializable
data class IdeaSuggestion(
    val title: String,
    val description: String,
)

@Serializable
data class IdeasApiResponse(
    val ideas: List<IdeaSuggestion>,
)

data class IdeasState(
    val inputText: String = "",
    val isLoading: Boolean = false,
    @StringRes val errorMessageResId: Int? = null,
    val ideas: List<IdeaSuggestion> = emptyList(),
    val remainingUsesToday: Int = MAX_USES_PER_DAY,
)

const val MAX_USES_PER_DAY = 3


