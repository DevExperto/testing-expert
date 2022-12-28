package com.devexperto.testingexpert.ui.scoreboard

import androidx.lifecycle.ViewModel
import com.devexperto.testingexpert.data.ScoreboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScoreboardViewModel @Inject constructor(scoreboardRepository: ScoreboardRepository) :
    ViewModel() {

    val scores = scoreboardRepository.scores

}