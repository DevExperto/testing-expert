package com.devexperto.testingexpert.composeui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.devexperto.testingexpert.composeui.board.Board
import com.devexperto.testingexpert.composeui.games.Games
import com.devexperto.testingexpert.composeui.scoreboard.Scoreboard

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = NavItem.BOARD.route) {

        composable(NavItem.BOARD.route) {
            Board()
        }

        composable(NavItem.SCOREBOARD.route) {
            Scoreboard()
        }

        composable(NavItem.GAMES.route) {
            Games()
        }

    }

}