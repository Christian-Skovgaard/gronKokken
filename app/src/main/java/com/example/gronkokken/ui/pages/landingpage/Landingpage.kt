package com.example.gronkokken.ui.pages.landingpage

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gronkokken.R
import com.example.gronkokken.repository.UserViewModel
import com.example.gronkokken.ui.components.LandingButton
import kotlin.math.log

@Composable
fun Landingpage(userViewModel: UserViewModel) {

    val user = userViewModel

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFA0ED6E))
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(150.dp))
        Image(
            painter = painterResource(R.drawable.landingpagelogo),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        ) {
            Text(
                text = "Klassekode",
                fontSize = 20.sp
            )
        }

        var text by remember { mutableStateOf("") }

        BasicTextField(
            value = text,
            onValueChange = {text = it},
            textStyle = TextStyle.Default.copy(fontSize = 16.sp),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFFFFFFF))
                .height(66.dp)
                .padding(vertical = 6.dp, horizontal = 16.dp)
        )

        LandingButton("Færdig", 166, 48, 0xFF121212, 25, {
            user.role = UserViewModel.Role.Student
        })

        Spacer(modifier = Modifier.height(80.dp))

        LandingButton("Lærer", 134, 40, 0xFF9A9A9A, 20, {
            user.role = UserViewModel.Role.Student
        })

        Spacer(modifier = Modifier.height(30.dp))

        LandingButton("Gæst?", 134, 40, 0xFF9A9A9A, 20, {
            user.role = UserViewModel.Role.Guest
        })


    }
}