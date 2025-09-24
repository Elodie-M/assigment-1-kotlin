package sheridan.mesiha.assignment1.ui.theme

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlin.random.Random
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class UiState(
    val message: String = "Guess a number between 1 and 10",
    val gameOver: Boolean = false
)
class GuessViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private var secretNumber = Random.nextInt(1,11)

    fun makeGuess(guess: Int){
        when{
            guess < secretNumber -> _uiState.value = _uiState.value.copy(message = "The number is larger")
            guess > secretNumber -> _uiState.value = _uiState.value.copy(message = "The number is smaller")
            else -> _uiState.value = _uiState.value.copy(message = "Correct. The number was $secretNumber", gameOver = true)

        }
    }

    fun resetGame(){
        secretNumber = Random.nextInt(1,11)
        _uiState.value = UiState()
    }
}