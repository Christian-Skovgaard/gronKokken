package com.example.gronkokken

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.gronkokken.ui.theme.LeafGreen
import kotlinx.coroutines.delay
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.core.models.Shape
import java.util.concurrent.TimeUnit


@Composable // Denne funktion viser noget på skærmen (UI)
fun TopBar() {
    Row( // Lægger tingene vandret (på en række)
        modifier = Modifier
            .fillMaxWidth() // Rækken fylder hele skærmens bredde
            .padding(top = 70.dp, start = 16.dp, end = 16.dp), // Giver luft omkring
        verticalAlignment = Alignment.CenterVertically // Indholdet i rækken bliver centreret lodret
    ) {
        Icon( // Tilbage-pil
            imageVector = Icons.AutoMirrored.Filled.ArrowBack, // Selve pil-ikonet
            contentDescription = "Tilbage", // Beskrivelse til fx skærmlæsere
            tint = LeafGreen, // Farve
            modifier = Modifier
                .size(24.dp) // Størrelse på ikonet
                .clickable { } // Gør det klikbart (funktion kan tilføjes)
        )

        Spacer(modifier = Modifier.width(16.dp)) // Giver lidt afstand

        Icon( // Profil-ikon
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Profil",
            tint = LeafGreen,
            modifier = Modifier.size(64.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column { // Tekst lagt ovenpå hinanden (lodret)
            Text(
                text = "Mine upload", // Overskrift
                fontSize = 20.sp, // Skriftstørrelse
                fontWeight = FontWeight.Bold // Gør det fed
            )
            Text(
                text = "0 uploads", // Undertekst
                fontSize = 14.sp
            )
        }
    }
}



@Composable
fun UploadScreen() {
    var showConfetti by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(5000)
        showConfetti = false
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBar()

            // Midtersektion med placeholder
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 32.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        tint = LeafGreen,
                        modifier = Modifier.size(120.dp)
                    )
                    Text(
                        text = "Du har endnu ikke uploadet noget",
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
        }

        // Grøn knap nederst
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
                .clickable {
                    // Funktion til senere
                }
                .background(color = LeafGreen, shape = RoundedCornerShape(50))
                .height(50.dp)
                .width(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Tilføj",
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }

        // 🎉 Massevis af konfetti med fade-out
        AnimatedVisibility(
            visible = showConfetti,
            exit = fadeOut()
        ) {
            KonfettiView(
                modifier = Modifier.fillMaxSize(),
                parties = listOf(
                    // Venstre-top
                    Party(
                        emitter = Emitter(duration = 5000, TimeUnit.MILLISECONDS).perSecond(5000),
                        spread = 360,
                        speed = 10f,
                        position = Position.Relative(0.0, 0.0),
                        colors = konfettiColors(),
                        shapes = listOf(Shape.Circle)
                    ),
                    // Midt-top
                    Party(
                        emitter = Emitter(duration = 5000, TimeUnit.MILLISECONDS).perSecond(5000),
                        spread = 360,
                        speed = 10f,
                        position = Position.Relative(0.5, 0.0),
                        colors = konfettiColors(),
                        shapes = listOf(Shape.Circle)
                    ),
                    // Højre-top
                    Party(
                        emitter = Emitter(duration = 5000, TimeUnit.MILLISECONDS).perSecond(5000),
                        spread = 360,
                        speed = 10f,
                        position = Position.Relative(1.0, 0.0),
                        colors = konfettiColors(),
                        shapes = listOf(Shape.Circle)
                    )
                )
            )
        }
    }
}
fun konfettiColors() = listOf(
    0xfff44336.toInt(), // Rød
    0xff4caf50.toInt(), // Grøn
    0xff2196f3.toInt(), // Blå
    0xffffeb3b.toInt(), // Gul
    0xff9c27b0.toInt()  // Lilla
)



