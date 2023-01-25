package com.devexperto.testingexpert.composeui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Scoreboard
import androidx.compose.material.icons.filled.VideogameAsset
import androidx.compose.ui.graphics.vector.ImageVector
import com.devexperto.testingexpert.R

enum class NavItem(val route: String, val title: Int, val icon: ImageVector) {
    BOARD("board", R.string.game, Icons.Default.Games),
    SCOREBOARD("scoreboard", R.string.scoreboard, Icons.Default.Scoreboard),
    GAMES("games", R.string.other_games, Icons.Default.VideogameAsset)
}