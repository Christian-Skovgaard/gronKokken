package com.example.gronkokken.com.example.gronkokken.ui.pages.Frontpage

import com.example.gronkokken.R
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gronkokken.ui.theme.GronKokkenTheme

class FrontPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GronKokkenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FrontPageTeacherScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Composable
fun FrontPageTeacherScreen(modifier: Modifier = Modifier) {
    val robotoBold = FontFamily(Font(R.font.roboto))
//logo
    Column{
        Image(
            painter = painterResource(id = R.drawable.logo), // brug dit billednavn her
            contentDescription = "App logo",
            modifier = Modifier
                .padding(top = 16.dp)
                .width(450.dp).height(200.dp), // justér størrelsen her
            contentScale = ContentScale.Fit
        )

        Column(
            modifier = modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Spacer(modifier = Modifier.width(16.dp))
            // Ugens Opskrift
            Button(
                onClick = { Log.d("BTN", "Ugens opskrift klik") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF69BFFF)),
                modifier = Modifier.fillMaxWidth()
                    .height(80.dp),        ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    // Billede til venstre
                    Image(
                        painter = painterResource(id = R.drawable.chefhat),
                        contentDescription = "Billeder ikon",
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.CenterStart) // helt til venstre
                            .padding(start = 16.dp)
                    )

                    // Tekst i midten
                    Text(
                        text = "UGENS OPSKRIFT",
                        color = Color.Black,
                        fontFamily = robotoBold,
                        fontSize = 25.sp
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            // Råvarer i sæson
            Button(
                onClick = { Log.d("BTN", "Råvarer i sæson klik") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFBA27)),
                modifier = Modifier.fillMaxWidth()
                    .height(80.dp),    ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    // Billedet placeres helt til venstre
                    Image(
                        painter = painterResource(id = R.drawable.corn),
                        contentDescription = "Billeder ikon",
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.CenterStart) // venstrestillet
                            .padding(start = 16.dp)
                    )

                    // Teksten centreres
                    Text(
                        text = "RÅVARER I SÆSON",
                        fontSize = 25.sp,
                        fontFamily = robotoBold,
                        color = Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            // Opskrifter
            Button(
                onClick = { Log.d("BTN", "Opskrifter klik") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCEBAFF)),
                modifier = Modifier.fillMaxWidth()
                    .height(80.dp),        ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    // Billedet placeres helt til venstre
                    Image(
                        painter = painterResource(id = R.drawable.menu),
                        contentDescription = "Billeder ikon",
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.CenterStart) // venstrestillet
                            .padding(start = 16.dp)
                    )

                    // Teksten centreres
                    Text(
                        text = "OPSKRIFTER",
                        fontSize = 25.sp,
                        fontFamily = robotoBold,
                        color = Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            // Vores klimaplan
            Button(
                onClick = { Log.d("BTN", "Klimaplan klik") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4B84AD)),
                modifier = Modifier.fillMaxWidth()
                    .height(80.dp),        ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    // Billedet placeres helt til venstre
                    Image(
                        painter = painterResource(id = R.drawable.worldwide),
                        contentDescription = "Billeder ikon",
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.CenterStart) // venstrestillet
                            .padding(start = 16.dp)
                    )

                    // Teksten centreres
                    Text(
                        text = "VORES KLIMAPLAN",
                        fontSize = 25.sp,
                        fontFamily = robotoBold,
                        color = Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            // Billeder og Overview i række
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxWidth(),

                ) {
                Button(
                    onClick = { Log.d("BTN", "Billeder klik") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF8380)),
                    modifier = Modifier.weight(1f)
                        .height(80.dp),            ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        // Billedet placeres helt til venstre
                        Image(
                            painter = painterResource(id = R.drawable.pics),
                            contentDescription = "Billeder ikon",
                            modifier = Modifier
                                .size(38.dp)
                                .align(Alignment.CenterStart) // venstrestillet
                        )

                        // Teksten centreres
                        Text(
                            text = "BILLEDER",
                            fontSize = 19.sp,
                            fontFamily = robotoBold,
                            color = Color.Black,
                            modifier = Modifier.align(Alignment.CenterEnd)
                        )
                    }
                }

                Button(
                    onClick = { Log.d("BTN", "Overview klik") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFF048)),
                    modifier = Modifier.weight(1f)
                        .height(80.dp)           ) {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Billedet placeres helt til venstre
                        Image(
                            painter = painterResource(id = R.drawable.statistics),
                            contentDescription = "Billeder ikon",
                            modifier = Modifier
                                .size(38.dp)
                                .align(Alignment.CenterStart)
                        )

                        // Teksten centreres
                        Text(
                            text = "OVERVIEW",
                            fontSize = 19.sp,
                            fontFamily = robotoBold,
                            color = Color.Black,
                            modifier = Modifier.align(Alignment.CenterEnd)
                        )
                    }

                }
            }
        }}}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FrontPageTeacherScreen(

    )
}