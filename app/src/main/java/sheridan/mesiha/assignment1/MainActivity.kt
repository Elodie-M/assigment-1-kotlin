package sheridan.mesiha.assignment1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
//import sheridan.mesiha.assignment1.ui.theme.Assigment1ElodieMesihaTheme
import sheridan.mesiha.assignment1.ui.theme.GuessViewModel
import androidx.compose.ui.unit.dp
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import sheridan.mesiha.assignment1.ui.theme.GuessNumberTheme

class MainActivity : ComponentActivity() {
    private val viewModel: GuessViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GuessNumberTheme {
                GuessScreen(viewModel) }

        }
    }
}

@Composable
fun GuessScreen(viewModel: GuessViewModel){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var input by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Guess a number",
            style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = uiState.message)
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = input,
            onValueChange = {if (it.all {c -> c.isDigit()}) input = it},
            label = { Text("Your guess (1–10)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        if (!uiState.gameOver){
            Button(onClick = {
                input.toIntOrNull()?.let {viewModel.makeGuess(it)}
                input = ""
            }) {Text("OK") }
        } else {
            Button(onClick = {viewModel.resetGame()}) { Text ("Play Again")}
        }
    }
}
