package com.devexperto.testingexpert.composeui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.devexperto.testingexpert.R
import com.devexperto.testingexpert.composeui.navigation.NavItem
import com.devexperto.testingexpert.composeui.navigation.Navigation

@Composable
fun Home() {

    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route ?: ""

    Scaffold(
        topBar = { TopBar() },
        bottomBar = {
            BottomNavBar(
                currentRoute = currentRoute,
                onNavItemClick = navController::navigate
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            Navigation(navController)
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) }
    )
}

@Composable
fun BottomNavBar(currentRoute: String, onNavItemClick: (NavItem) -> Unit) {
    BottomNavigation {
        NavItem.values().forEach {
            val title = stringResource(id = it.title)
            BottomNavigationItem(
                selected = currentRoute.contains(it.route),
                onClick = { onNavItemClick(it) },
                icon = { Icon(it.icon, contentDescription = title) },
                label = { Text(text = title) }
            )
        }
    }
}

private fun NavHostController.navigate(navItem: NavItem) {
    navigate(navItem.route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
}