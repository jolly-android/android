package com.phoenix.pillreminder.feature_alarms.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

private const val KEY_PILLBOX_REMINDER = "pillbox_reminder"

@Singleton
class UserPreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    private object PreferenceKeys {
        val pillboxReminder = booleanPreferencesKey(KEY_PILLBOX_REMINDER)
    }

    val userPreferences: Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            UserPreferences(
                isPillboxReminderEnabled = preferences[PreferenceKeys.pillboxReminder] ?: false
            )
        }

    suspend fun setPillboxReminder(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.pillboxReminder] = enabled
        }
    }
}

