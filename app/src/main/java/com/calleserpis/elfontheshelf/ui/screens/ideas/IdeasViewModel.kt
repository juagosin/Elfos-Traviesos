package com.calleserpis.elfontheshelf.ui.screens.ideas

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calleserpis.elfontheshelf.R
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class IdeasViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(IdeasState())
    val state = _state.asStateFlow()

    private val json = Json {
        ignoreUnknownKeys = true
    }

    fun onInputChange(newText: String) {
        _state.update { it.copy(inputText = newText, errorMessageResId = null) }
    }

    fun onMagicClick(isOnline: Boolean) {
        if (!isOnline) {
            _state.update {
                it.copy(
                    isLoading = false,
                    errorMessageResId = R.string.ideas_error_no_connection
                )
            }
            return
        }

        val materials = _state.value.inputText.trim()
        if (materials.isEmpty()) {
            _state.update {
                it.copy(
                    isLoading = false,
                    errorMessageResId = R.string.ideas_error_empty_materials
                )
            }
            return
        }

        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                    errorMessageResId = null
                )
            }

            try {
                val prompt = buildPrompt(materials)

                val model = Firebase.ai(backend = GenerativeBackend.googleAI())
                    .generativeModel("gemini-2.5-flash-lite")

                val response = model.generateContent(prompt)
                val text = response.text.orEmpty().trim()

                if (text.isEmpty()) {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessageResId = R.string.ideas_error_no_ideas
                        )
                    }
                    return@launch
                }

                val ideas = parseIdeas(text)

                _state.update {
                    it.copy(
                        isLoading = false,
                        ideas = ideas,
                        errorMessageResId = null
                    )
                }
            } catch (e: Exception) {
                Log.e("IdeasViewModel", "Error al pedir ideas a los elfos", e)

                val friendlyMessageResId = when {
                    e.message?.contains("Default FirebaseApp is not initialized", ignoreCase = true) == true ||
                            e.message?.contains("NO_APP", ignoreCase = true) == true ->
                        R.string.ideas_error_firebase_not_configured

                    e.message?.contains("model", ignoreCase = true) == true &&
                            e.message?.contains("not found", ignoreCase = true) == true ->
                        R.string.ideas_error_model_not_found

                    else ->
                        R.string.ideas_error_generic
                }

                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessageResId = friendlyMessageResId
                    )
                }
            }
        }
    }

    private fun buildPrompt(materials: String): String {
        return """
            Eres un asistente creativo para la aplicación "Elf on the Shelf". 
            El usuario te dará una lista de materiales que tiene en casa y tú debes 
            sugerirle escenas creativas y divertidas que puede montar con el elfo 
            usando SOLO esos materiales. 
            Responde en el idioma que te han hablado.

            El usuario te proporcionará una lista de materiales y tú debes devolver
            EXCLUSIVAMENTE un JSON VÁLIDO con este formato exacto, sin ningún texto adicional,
            sin explicaciones, sin comentarios y sin markdown:

            {
              "ideas": [
                { "title": "Título 1", "description": "Descripción breve 1" },
                { "title": "Título 2", "description": "Descripción breve 2" },
                { "title": "Título 3", "description": "Descripción breve 3" }
              ]
            }

            Requisitos importantes:
            - Devuelve SIEMPRE exactamente 3 objetos dentro del array "ideas".
            - Cada "title" debe ser corto y descriptivo.
            - Cada "description" debe ser una explicación breve de 1 a 3 frases.
            - NO añadas ningún texto fuera del JSON.
            - NO añadas comillas triples ni bloques ```json.

            Materiales del usuario:
            $materials
        """.trimIndent()
    }

    private fun parseIdeas(rawText: String): List<IdeaSuggestion> {
        // 1) Intentar primero parsear como JSON del formato pactado
        runCatching {
            val cleaned = rawText
                .removePrefix("```json")
                .removePrefix("```JSON")
                .removePrefix("```")
                .removeSuffix("```")
                .trim()

            val response: IdeasApiResponse = json.decodeFromString(cleaned)
            if (response.ideas.isNotEmpty()) {
                return response.ideas.take(3)
            }
        }.onFailure { error ->
            Log.w("IdeasViewModel", "No se ha podido parsear la respuesta como JSON, se usa parser de texto libre", error)
        }

        // 2) Fallback: parser de texto libre antiguo
        val blocks = rawText
            .split(Regex("\\n\\s*\\n"))
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .take(3)

        if (blocks.isEmpty()) {
            return listOf(
                IdeaSuggestion(
                    title = "Idea de los elfos",
                    description = rawText
                )
            )
        }

        return blocks.mapIndexed { index, block ->
            val lines = block.lines().map { it.trim() }.filter { it.isNotEmpty() }
            val rawTitle = lines.firstOrNull().orEmpty()
            val title = rawTitle
                .removePrefix("${index + 1}.")
                .removePrefix("-")
                .removePrefix("*")
                .trim()
                .ifEmpty { "Idea ${index + 1}" }

            val description = lines.drop(1).joinToString("\n").ifEmpty { block }

            IdeaSuggestion(
                title = title,
                description = description
            )
        }
    }
}
