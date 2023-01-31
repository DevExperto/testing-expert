package com.devexperto.testingexpert.data

import com.devexperto.testingexpert.composeui.settings.Theme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepository @Inject constructor() {

    private val inMemoryFlow = MutableStateFlow(Theme.SAME_AS_SYSTEM)
    val theme: Flow<Theme> = inMemoryFlow

    fun saveTheme(theme: Theme) {
        inMemoryFlow.value = theme
    }

}