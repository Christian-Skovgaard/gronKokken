package com.example.gronkokken.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.R
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class ClimaPlan : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GronKokkenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ClimaPlanScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClimaPlanScreen(modifier: Modifier) {
    val robotoBold = FontFamily(Font(R.font.roboto_bold))

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { /* Gå tilbage */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Tilbage")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Titel
            Text(
                text = "Vores Klimaplan",
                fontSize = 24.sp,
                fontFamily = robotoBold,
                color = Color(0xFF4CAF50) // Grøn
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Info sektioner
            InfoSection("🌍 Hvad handler det om?", "Klimaforandringer påvirker hele verden...")

            InfoSection("📟 CO2-beregneren", "Vi har lavet en simpel beregner...")

            InfoSection("📊 Hvor meget?", "I gennemsnit forurener en dansker ca. 11 tons CO2...")

            // Test-opfordring
            Text(
                text = "Tag klimaplans-testen!",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4CAF50)
            )
            Text(
                text = "Find ud af, hvad dit startpunkt er, og tag testen igen sidst på skoleåret...",
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Link
            Text(
                text = "https://voresklimaplan.dk/co2calculator",
                color = Color.Blue,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // "Startpunkt" felt
            Text(text = "Startpunkt", fontWeight = FontWeight.Bold, color = Color(0xFF4CAF50))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(12.dp))

            // "Slutpunkt" felt
            Text(text = "Slutpunkt", fontWeight = FontWeight.Bold, color = Color(0xFF4CAF50))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Billede i bunden
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Image(
                    painterResource(id = R.drawable.earth), "Jord ikon", Modifier.size(60.dp)
                )
            }
        }
    }
}

@Composable
fun InfoSection(x0: String, x1: String) {
    TODO("Not yet implemented")
}





