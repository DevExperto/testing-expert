package com.devexperto.testingexpert.composeui.settings

import androidx.lifecycle.ViewModel
import com.devexperto.testingexpert.data.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
): ViewModel() {
    val theme = settingsRepository.theme

    fun saveTheme(theme: Theme) {
        settingsRepository.saveTheme(theme)
    }
}