package com.example.gronkokken.com.example.gronkokken.ui.pages.Frontpage

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.gronkokken.R
import com.example.gronkokken.ui.pages.frontpage.FrontPageViewModel
import com.example.gronkokken.ui.theme.GronKokkenTheme

@Composable
fun FrontPageScreen(navHostController: NavHostController) { //sahra

    val viewModel: FrontPageViewModel = viewModel()

    val week by remember { mutableStateOf("UGENS OPSKRIFT") }
    val season by remember { mutableStateOf("RÅVARER I SÆSON") }
    val recipes by remember { mutableStateOf("OPSKRIFTER") }
    val clima by remember { mutableStateOf("VORES KLIMAPLAN") }
    val pics by remember { mutableStateOf("BILLEDER") }
    val robotoBold = FontFamily(Font(R.font.roboto))

    Column {
        Image(
            painter = painterResource(id = R.drawable.logo2),
            contentDescription = "App logo",
            modifier = Modifier
                .padding(top = 16.dp)
                .width(450.dp)
                .height(200.dp),
            contentScale = ContentScale.Fit
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.width(20.dp))
            Button(
                onClick = { navHostController.navigate("recipe/${viewModel.currentRecipeId}") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF69BFFF)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    // Billedet placeres helt til venstre
                    Image(
                        painter = painterResource(id = R.drawable.chefhat),
                        contentDescription = "ikon",
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.CenterStart) // venstrestillet
                            .padding(start = 16.dp)
                    )

                    // Teksten centreres
                    Text(
                        text = week,
                        fontSize = 25.sp,
                        fontFamily = robotoBold,
                        color = Color.Black
                    )
                }
            }

            Button(
                onClick = TODO(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFBA27)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    // Billedet placeres helt til venstre
                    Image(
                        painter = painterResource(id = R.drawable.corn),
                        contentDescription = "Billeder ikon",
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.CenterStart) // venstrestillet
                            .padding(start = 16.dp)
                    )

                    // Teksten centreres
                    Text(
                        text = season,
                        fontSize = 25.sp,
                        fontFamily = robotoBold,
                        color = Color.Black
                    )
                }
            }

            Button(
                onClick = { navHostController.navigate("recipeListScreen") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCEBAFF)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    // Billedet placeres helt til venstre
                    Image(
                        painter = painterResource(id = R.drawable.menu),
                        contentDescription = "ikon",
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.CenterStart) // venstrestillet
                            .padding(start = 16.dp)
                    )

                    // Teksten centreres
                    Text(
                        text = recipes,
                        fontSize = 25.sp,
                        fontFamily = robotoBold,
                        color = Color.Black
                    )
                }
            }


            Button(
                onClick = {TODO()},
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4B84AD)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    // Billedet placeres helt til venstre
                    Image(
                        painter = painterResource(id = R.drawable.clima),
                        contentDescription = "Billeder ikon",
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.CenterStart) // venstrestillet
                            .padding(start = 8.dp)
                    )

                    // Teksten centreres
                    Text(
                        text = clima,
                        fontSize = 25.sp,
                        fontFamily = robotoBold,
                        color = Color.Black
                    )
                }
            }

            Button(
                onClick = { Log.d("BTN", "Billeder klik") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF8380)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    // Billedet placeres helt til venstre
                    Image(
                        painter = painterResource(id = R.drawable.pics),
                        contentDescription = "Billeder ikon",
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.CenterStart) // venstrestillet
                            .padding(start = 16.dp)
                    )

                    // Teksten centreres
                    Text(
                        text = pics,
                        fontSize = 25.sp,
                        fontFamily = robotoBold,
                        color = Color.Black
                    )
                }
            }
        }
    }
}
