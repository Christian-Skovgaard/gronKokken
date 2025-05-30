package com.example.gronkokken.ui.pages

import com.example.gronkokken.R
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.gronkokken.repository.Firestore
import com.google.firebase.auth.FirebaseAuth


//class ClimaPlan : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            ClimaPlanScreen()
//        }
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClimaPlanScreen(
    userViewModel: com.example.gronkokken.repository.UserViewModel = viewModel(),
    navHostController: NavHostController) {
    val robotoBold = FontFamily(Font(R.font.roboto))
    var startpunkt by remember { mutableStateOf("") }
    var slutpunkt by remember { mutableStateOf("") }

    val user = FirebaseAuth.getInstance().currentUser
    val userId = user?.uid

    val fireStore = Firestore()
    LaunchedEffect(Unit) {
        val (hentetStart, hentetSlut) = fireStore.hentLaunchedEffectData("min-bruger")
        startpunkt = hentetStart
        slutpunkt = hentetSlut
    }



    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                            contentDescription = "Tilbage"
                        )

                    }

                }
            )
        }
    )
    { innerPadding ->


        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            // Titel
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center // centrerer indholdet
            ) {
                Text(
                    text = "Vores Klimaplan",
                    fontSize = 36.sp,
                    fontFamily = robotoBold,
                    color = Color(0xFF4CAF50)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Info sektioner
            InfoSection(
                title = "🌍 Hvad handler det om?",
                description = "Klimaforandringer påvirker hele verden – ikke kun Danmark. Derfor ser vi også på, hvordan vores valg og forbrug påvirker klimaet globalt. Det handler ikke kun om CO2 herhjemme, men også om den påvirkning vores mad, tøj og elektronik har i andre lande, hvor de bliver produceret."
            )

            InfoSection(
                title = "📟 CO2-beregneren",
                description = "Vi har udviklet en simpel beregner, der kan vise dig, hvor meget du forurener i hverdagen. Den fokuserer især på mad, fordi det er et område, hvor man kan gøre en stor forskel. Du får konkrete råd til, hvordan du kan hjælpe klimaet gennem små ændringer."
            )

            InfoSection(
                title = "📊 Hvor meget CO2 udleder vi?",
                description = "En gennemsnitlig dansker udleder omkring 11 tons CO2 om året. Det er alt for meget, hvis vi skal nå klimamålene. Derfor skal vi hjælpe hinanden med at reducere det tal, og det starter med viden og motivation – særligt blandt unge."
            )


            // Test-opfordring
            Text(
                text = "Tag klimaplans-testen!",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4CAF50)
            )
            Text(
                text = "Find ud af, hvad dit startpunkt er , og tag testen igen sidst på skoleåret og se, om du er blevet mere klimavenlig.",
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

            // "Startpunkt" input felt
            Text(
                text = "Startpunkt",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4CAF50)
            )
            TextField(
                value = startpunkt,
                onValueChange = { startpunkt = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.LightGray
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Slutpunkt input felt
            Text(
                text = "Slutpunkt",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4CAF50)
            )
            TextField(
                value = slutpunkt,
                onValueChange = { slutpunkt = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.LightGray
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            //Gem knap til input felt
// henter data fra firestore.kt
            Button(
                onClick = {
                    if (startpunkt.isNotBlank() && slutpunkt.isNotBlank()) {
                        fireStore.gemKlimaplanData(documentId = "min-bruger", startpunkt, slutpunkt)
                    } else {
                        Log.d("Firestore", "Udfyld venligst begge felter før du gemmer")
                    }
                    }
                ,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                Text(
                    text = "GEM",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            // Billede i bunden
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Image(
                    painter = painterResource(R.drawable.planet),
                    contentDescription = "Jord ikon",
                    modifier = Modifier.size(60.dp)
                )
            }
        }
    }
}




@Composable
fun InfoSection(title: String, description: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = title,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4CAF50)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = description,
            fontSize = 16.sp,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}





