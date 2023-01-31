package com.devexperto.testingexpert.composeui.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.devexperto.testingexpert.R

enum class Theme(val title: Int) {
    SAME_AS_SYSTEM(R.string.same_as_system),
    LIGHT(R.string.light),
    DARK(R.string.dark)
}

@Composable
fun SettingsDialog(vm: SettingsViewModel = hiltViewModel(), onDismissRequest: () -> Unit) {
    val theme by vm.theme.collectAsState(initial = Theme.SAME_AS_SYSTEM)
    SettingsDialog(theme, vm::saveTheme, onDismissRequest)
}

@Composable
fun SettingsDialog(theme: Theme, onChangeTheme: (Theme) -> Unit, onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = MaterialTheme.shapes.medium,
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.change_theme),
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(16.dp))
                Column(modifier = Modifier.selectableGroup()) {
                    Theme.values().forEach {
                        val text = stringResource(id = it.title)
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .selectable(
                                    selected = theme == it,
                                    onClick = { onChangeTheme(it) },
                                    role = Role.RadioButton
                                )
                        ) {
                            RadioButton(
                                selected = theme == it,
                                onClick = null,  // null recommended for accessibility with screenreaders
                                modifier = Modifier.semantics {
                                    contentDescription = text
                                }.size(48.dp)
                            )
                            Text(text = text)
                            Spacer(modifier = Modifier.width(16.dp))
                        }
                    }
                    TextButton(
                        onClick = onDismissRequest,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text = stringResource(id = R.string.close))
                    }
                }
            }
        }
    }
}