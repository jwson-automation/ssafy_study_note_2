package com.ssafy.compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ssafy.compose.ui.theme.Ws_android_jetpack_03_3_sonjungwooTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ws_android_jetpack_03_3_sonjungwooTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyApp() {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("MyColorViews") },
                backgroundColor = MaterialTheme.colors.primary
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight(0.5f)
                ) {
                    Box(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth()
                            .weight(1f)
                            .border(
                                width = 2.dp,
                                color = Color.DarkGray,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .background(color = Color.Black, shape = RoundedCornerShape(8.dp))
                    ) {
                        Text(
                            text = "Box One",
                            color = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(2f),
                        horizontalArrangement = Arrangement.SpaceAround,
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxSize()
                                .weight(1f)
                                .border(
                                    width = 2.dp,
                                    color = Color.DarkGray,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .background(color = Color.Gray, shape = RoundedCornerShape(8.dp))
                        ) {
                            Text(
                                text = "Box Two", color = Color.White,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                        Column(
                            Modifier.weight(1f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxSize()
                                    .height(40.dp)
                                    .weight(1f)
                                    .border(
                                        width = 2.dp,
                                        color = Color.DarkGray,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .background(
                                        color = Color.LightGray,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                            ) {
                                Text(
                                    text = "Box Three",
                                    color = Color.White,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxSize()
                                    .weight(1f)
                                    .border(
                                        width = 2.dp,
                                        color = Color.DarkGray,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .background(color = Color.Red, shape = RoundedCornerShape(8.dp))
                            ) {
                                Text(
                                    text = "box Four",
                                    color = Color.White,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxSize()
                                    .weight(1f)
                                    .border(
                                        width = 2.dp,
                                        color = Color.DarkGray,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .background(
                                        color = Color.Magenta,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                            ) {
                                Text(
                                    text = "box Five", color = Color.White,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }

                    }

                    Row() {
                        Text(
                            text = "How to Play : ",
                            fontSize = 24.sp,
                        )
                        Text(
                            text = "Tap the screen and buttons",
                            fontSize = 12.sp,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }

                }
                Row(
                    modifier = Modifier
                ) {
                    Button(
                        onClick = {}, modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(12.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                    ) {
                        Text(text = "RED")
                    }
                    Button(
                        onClick = {}, modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(12.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)
                    ) {
                        Text(text = "YELLOW")
                    }
                    Button(
                        onClick = {}, modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(12.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
                    ) {
                        Text(text = "GREEN")
                    }

                }
            }

        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Ws_android_jetpack_03_3_sonjungwooTheme {
        MyApp()
    }
}