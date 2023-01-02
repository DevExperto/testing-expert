package com.devexperto.testingexpert.ui.scoreboard

import androidx.lifecycle.ViewModel
import com.devexperto.testingexpert.usecases.GetAllScoresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScoreboardViewModel @Inject constructor(getAllScoresUseCase: GetAllScoresUseCase) :
    ViewModel() {

    val scores = getAllScoresUseCase()

}