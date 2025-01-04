package io.wookoo.mediumsound.first.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun FirstScreen(
    viewModel: FirstScreenViewModel = koinViewModel()
) {
    val onIntent = viewModel::onIntent
    viewModel.uiState.collectAsStateWithLifecycle()
    FirstScreenContent(
        onClickFirstSound = { onIntent(FirstScreenContract.OnIntent.OnFirstSoundClick) },
        onClickSecondSound = { onIntent(FirstScreenContract.OnIntent.OnSecondSoundClick) }
    )
}

@Composable
internal fun FirstScreenContent(
    onClickFirstSound: () -> Unit,
    onClickSecondSound: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp,
                pressedElevation = 0.dp
            ),
            onClick = onClickFirstSound,
            enabled = true,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
            )
        ) {
            Text(
                text = "Sound one",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp)
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp,
                pressedElevation = 0.dp
            ),
            onClick = onClickSecondSound,
            enabled = true,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Yellow,
            )
        ) {
            Text(
                text = "Sound two",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun FirstScreenContentPreview() {
    MaterialTheme {
        FirstScreenContent(
            onClickFirstSound = {},
            onClickSecondSound = {}
        )
    }
}