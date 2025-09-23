package sheridan.mesiha.assignment1.ui.theme

import androidx.compose.runtime.mutableStateOf
import kotlin.random.Random
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class GuessViewModel {
    private var secretNumber by mutableStateOf(Random.nextInt(1,11))

    var guess by mutableStateOf("")
    var message by mutableStateOf("Guess a number between 1 and 10")
    var gameOver by mutableStateOf(false)

    fun submitGuess(){

    }

    fun resetGame(){

    }
}