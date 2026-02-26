package com.calleserpis.elfontheshelf.ui.screens.ideas

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
    val errorMessage: String? = null,
    val ideas: List<IdeaSuggestion> = emptyList(),
)

