package com.devexperto.testingexpert.composeui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.devexperto.testingexpert.R

enum class Theme(val title: Int) {
    LIGHT(R.string.light),
    DARK(R.string.dark),
    SAME_AS_SYSTEM(R.string.same_as_system)
}

@Composable
fun SettingsDialog(vm: SettingsViewModel = hiltViewModel(), onDismissRequest: () -> Unit) {
    val theme by vm.theme.collectAsState(initial = Theme.SAME_AS_SYSTEM)
    SettingsDialog(theme, vm::saveTheme, onDismissRequest)
}

@Composable
fun SettingsDialog(theme: Theme, onChangeTheme: (Theme) -> Unit, onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = onDismissRequest) {
        Text(text = stringResource(id = R.string.change_theme))
        Column {
            Theme.values().forEach {
                val text = stringResource(id = it.title)
                Row {
                    RadioButton(
                        selected = theme == it,
                        onClick = { onChangeTheme(it) },
                        modifier = Modifier.semantics {
                            contentDescription = text
                        }
                    )
                    Text(text = text)
                }
            }
            Button(onClick = onDismissRequest) {
                Text(text = stringResource(id = R.string.close))
            }
        }
    }
}