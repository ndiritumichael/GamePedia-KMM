package com.devmike.gamepedia.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.devmike.gamepedia.Greeting
import com.devmike.gamepedia.GreetingKtor
import com.devmike.gamepedia.android.ui.GamePediaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val greeter = remember {
                GreetingKtor()
            }

            var message by remember {
                mutableStateOf("")
            }

            LaunchedEffect(key1 = true) {
                val data = greeter.greeting()
                message = data
            }
            GamePediaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(Greeting().greeting() + message)
                }
            }
        }
    }
}

@Composable
fun Greeting(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    GamePediaTheme {
        Greeting("Hello, Android!")
    }
}
