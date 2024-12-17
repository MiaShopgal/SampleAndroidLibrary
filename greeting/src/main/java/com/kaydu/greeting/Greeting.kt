package com.kaydu.greeting

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Miao Hello $name!",
        modifier = modifier
    )
}

@Composable
fun GreetingTwo(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Miao Hello $name!",
        modifier = modifier
    )
}