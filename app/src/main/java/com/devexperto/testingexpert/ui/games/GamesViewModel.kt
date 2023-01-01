package com.devexperto.testingexpert.ui.games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devexperto.testingexpert.data.GamesRepository
import com.devexperto.testingexpert.domain.VideoGame
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(private val gamesRepository: GamesRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = UiState(isLoading = true)
            gamesRepository.games.collect {
                _state.value = UiState(games = it, isLoading = false)
            }
        }
    }

    data class UiState(
        val games: List<VideoGame> = emptyList(),
        val isLoading: Boolean = false,
    )
}