package io.wookoo.mediumsound.settings.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel


@Composable
fun SettingsScreen(
    settingsScreenViewModel: SettingsScreenViewModel = koinViewModel()
) {
    val onIntent = settingsScreenViewModel::onIntent
    val uiState by settingsScreenViewModel.uiState.collectAsStateWithLifecycle()
    val isSoundEnabled = uiState.settings.isSoundEnabled
    val soundVolume = uiState.settings.soundVolume
    settingsScreenViewModel.uiState.collectAsStateWithLifecycle()

    SettingsScreenContent(
        soundVolume = soundVolume,
        isSoundEnabled = isSoundEnabled,
        onSwitchChange = { isEnabled ->
            onIntent(SettingsScreenContract.OnIntent.OnSoundSwitchClick(isEnabled))
        },
        onSoundVolumeChange = { volume ->
            onIntent(SettingsScreenContract.OnIntent.OnSoundVolumeChange(volume))
        }
    )
}

@Composable
internal fun SettingsScreenContent(
    soundVolume: Float,
    isSoundEnabled: Boolean,
    onSwitchChange: (Boolean) -> Unit,
    onSoundVolumeChange: (Float) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "Sound",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp)
            )
            Switch(
                checked = isSoundEnabled,
                onCheckedChange = { isSoundEnabled ->
                    onSwitchChange(isSoundEnabled)
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Yellow,
                    checkedTrackColor = Color.Black.copy(alpha = 0.5f),
                    uncheckedThumbColor = Color.Gray,
                    uncheckedTrackColor = Color.Gray.copy(alpha = 0.5f),
                ),
                modifier = Modifier.padding(10.dp)
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))

        Slider(
            modifier = Modifier.fillMaxWidth(0.8f),
            value = soundVolume,
            onValueChange = onSoundVolumeChange,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            steps = 9,
            valueRange = 0f..100f
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun SettingsScreenContentPreview() {
    MaterialTheme {
        SettingsScreenContent(
            isSoundEnabled = true,
            onSwitchChange = {},
            onSoundVolumeChange = {},
            soundVolume = 40f
        )
    }
}