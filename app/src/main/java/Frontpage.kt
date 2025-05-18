package com.example.gronkokken

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gronkokken.ui.theme.GronKokkenTheme
import com.example.gronkokken.ui.theme.Typography

class FrontPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GronKokkenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FrontPageScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
@Composable
fun FrontPageScreen(modifier: Modifier = Modifier) {
    val week by remember {mutableStateOf("UGENS OPSKRIFT")}
    val season by remember {mutableStateOf("RÅVARER I SÆSON")}
    val recipes by remember { mutableStateOf("OPSKRIFTER")}
    val  clima by remember { mutableStateOf("VORES KLIMAPLAN")}
    val pics by remember { mutableStateOf("BILLEDER")}
    val overview by remember { mutableStateOf("OVERVIEW")}
    val BalooFontFamily = FontFamily(
        Font(R.font.baloo2))
//logo
    Column{
        Image(
            painter = painterResource(id = R.drawable.logo2), // brug dit billednavn her
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
            Text(text = "👨‍🍳 $week",
                    color = Color.Black)

        }
        Spacer(modifier = Modifier.width(16.dp))
        // Råvarer i sæson
        Button(
            onClick = { Log.d("BTN", "Råvarer i sæson klik") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFBA27)),
            modifier = Modifier.fillMaxWidth()
                .height(80.dp),    ) {
            Text(text = "🌽 $season", color = Color.Black)
        }
        Spacer(modifier = Modifier.width(16.dp))
        // Opskrifter
        Button(
            onClick = { Log.d("BTN", "Opskrifter klik") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFCEBAFF)),
            modifier = Modifier.fillMaxWidth()
                .height(80.dp),        ) {
            Text(text = "📖 $recipes", color = Color.Black)
        }
        Spacer(modifier = Modifier.width(16.dp))
        // Vores klimaplan
        Button(
            onClick = { Log.d("BTN", "Klimaplan klik") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4B84AD)),
            modifier = Modifier.fillMaxWidth()
                .height(80.dp),        ) {
            Text(text = "🍽 $clima", color = Color.Black)
        }
        Spacer(modifier = Modifier.width(16.dp))
        // Billeder og Overview i række
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { Log.d("BTN", "Billeder klik") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF8380)),
                modifier = Modifier.weight(1f)
                    .height(80.dp),            ) {
                Text(text = "🖼 $pics", color = Color.Black)
            }

            Button(
                onClick = { Log.d("BTN", "Overview klik") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFF048)),
                modifier = Modifier.weight(1f)
                    .height(80.dp)           ) {
 Text(text = "📋 $overview", color = Color.Black)

        }
    }
    }}}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GronKokkenTheme {
        FrontPageScreen(

        )
    }
}