package com.calleserpis.elfonavidenyo.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

private val Context.ideasUsageDataStore by preferencesDataStore(name = "ideas_usage")

data class IdeasUsage(
    val date: LocalDate,
    val usedCount: Int,
)

@Singleton
class IdeasUsageDataStore @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    companion object {
        private val KEY_DATE = stringPreferencesKey("date")
        private val KEY_COUNT = intPreferencesKey("count")

        const val MAX_USES_PER_DAY = 3
    }

    private val dataStore = context.ideasUsageDataStore

    suspend fun getCurrentUsage(): IdeasUsage {
        var result: IdeasUsage? = null
        val today = LocalDate.now()

        dataStore.edit { prefs ->
            val storedDate = prefs[KEY_DATE]?.let { LocalDate.parse(it) }
            val storedCount = prefs[KEY_COUNT] ?: 0

            val (effectiveDate, effectiveCount) = if (storedDate == today) {
                today to storedCount
            } else {
                today to 0
            }

            prefs[KEY_DATE] = effectiveDate.toString()
            prefs[KEY_COUNT] = effectiveCount

            result = IdeasUsage(
                date = effectiveDate,
                usedCount = effectiveCount,
            )
        }

        return requireNotNull(result)
    }

    suspend fun incrementUsage(): IdeasUsage {
        var result: IdeasUsage? = null
        val today = LocalDate.now()

        dataStore.edit { prefs ->
            val storedDate = prefs[KEY_DATE]?.let { LocalDate.parse(it) }
            val storedCount = prefs[KEY_COUNT] ?: 0

            val (effectiveDate, effectiveCount) = if (storedDate == today) {
                if (storedCount >= MAX_USES_PER_DAY) {
                    today to storedCount
                } else {
                    today to (storedCount + 1)
                }
            } else {
                today to 1
            }

            prefs[KEY_DATE] = effectiveDate.toString()
            prefs[KEY_COUNT] = effectiveCount

            result = IdeasUsage(
                date = effectiveDate,
                usedCount = effectiveCount,
            )
        }

        return requireNotNull(result)
    }
}

