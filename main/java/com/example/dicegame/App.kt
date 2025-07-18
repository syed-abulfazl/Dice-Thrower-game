package com.example.dicegame

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Preview(showBackground = true)
@Composable
fun App(modifier: Modifier = Modifier, innerPadding: PaddingValues = PaddingValues()) {

    val scorePlayer1 = remember { mutableStateOf(0) }
    val scorePlayer2 = remember { mutableStateOf(0) }

    var isPlayer1Turn = remember { mutableStateOf(true) }
    val currentImage = remember { mutableStateOf(0) }

    val images = listOf(
        R.drawable.dice_1,
        R.drawable.dice_2,
        R.drawable.dice_3,
        R.drawable.dice_4,
        R.drawable.dice_5,
        R.drawable.dice_6
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = if (isPlayer1Turn.value) "Player 1 Turn" else "Player 2 Turn",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.Black
        )

        Image(
            painter = if (currentImage.value == 0) {
                painterResource(R.drawable.tap)
            } else {
                painterResource(images[currentImage.value - 1])
            },
            contentDescription = null,
            modifier = Modifier.size(250.dp)
        )

        // Display the scores
        Row(
            modifier = Modifier.padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Player 1 Score",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(end = 20.dp)
                )
                Text(
                    text = "${scorePlayer1.value}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(end = 20.dp).padding(top = 8.dp)

                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Player 2 Score",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 20.dp)

                )
                Text(
                    text = "${scorePlayer2.value}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 20.dp).padding(top = 8.dp)

                )
            }
        }

        Row {
            Button(
                onClick = {
                    val random = Random.nextInt(6) + 1
                    scorePlayer1.value += random
                    isPlayer1Turn.value = false
                    currentImage.value = random
                },
                modifier = Modifier
                    .fillMaxWidth(.5f)
                    .padding(horizontal = 20.dp),
                enabled = isPlayer1Turn.value
            ) {
                Text(text = "Player 1")
            }

            Button(
                onClick = {
                    val random = Random.nextInt(6) + 1
                    scorePlayer2.value += random
                    isPlayer1Turn.value = true
                    currentImage.value = random
                },
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(horizontal = 20.dp),
                enabled = !isPlayer1Turn.value
            ) {
                Text(text = "Player 2")
            }
        }

        if (scorePlayer1.value >= 20 || scorePlayer2.value >= 20) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = if (scorePlayer1.value > scorePlayer2.value) "Player 1 Wins!" else "Player 2 Wins!",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.Black
                    )

                    Button(
                        onClick = {
                            scorePlayer1.value = 0
                            scorePlayer2.value = 0
                            isPlayer1Turn.value = true
                            currentImage.value = 0
                        },
                        modifier = Modifier
                            .fillMaxWidth(.5f)
                            .padding(vertical = 40.dp)
                    ) {
                        Text(text = "Reset")
                    }
                }
            }
        }
    }
}
