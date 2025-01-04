package io.wookoo.mediumsound.second.screen

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
fun SecondScreen(
    viewModel: SecondScreenViewModel = koinViewModel()
) {
    viewModel.uiState.collectAsStateWithLifecycle()

    SecondScreenContent(
        onThirdSoundClick = {
            viewModel.onIntent(SecondScreenContract.OnIntent.OnThirdSoundClick)
        },
        onFourthSoundClick = {
            viewModel.onIntent(SecondScreenContract.OnIntent.OnFourthSoundClick)
        }
    )
}

@Composable
internal fun SecondScreenContent(
    onThirdSoundClick: () -> Unit,
    onFourthSoundClick: () -> Unit
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
            onClick = onThirdSoundClick,
            enabled = true,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,
            )
        ) {
            Text(
                text = "Sound three",
                color = Color.Black,
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
            onClick = onFourthSoundClick,
            enabled = true,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Magenta,
            )
        ) {
            Text(
                text = "Sound four",
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
private fun SecondScreenContentPreview() {
    MaterialTheme {
        SecondScreenContent(
            onThirdSoundClick = {},
            onFourthSoundClick = {}
        )
    }
}