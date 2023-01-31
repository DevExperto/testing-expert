package com.devexperto.testingexpert.composeui.navigation

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.devexperto.testingexpert.R
import com.devexperto.testingexpert.composeui.settings.SettingsDialog

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        actions = {
            MoreOptionsTopBarMenu()
        }
    )
}

@Composable
fun MoreOptionsTopBarMenu() {
    var expanded by remember { mutableStateOf(false) }
    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = stringResource(id = R.string.more_options)
        )
        var dialogOpen by remember { mutableStateOf(false) }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(onClick = { expanded = false; dialogOpen = true }) {
                Text(text = stringResource(id = R.string.settings))
            }
        }
        if (dialogOpen) {
            SettingsDialog(onDismissRequest = { dialogOpen = false })
        }
    }
}

