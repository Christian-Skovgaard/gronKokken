package com.example.gronkokken.ui.pages.landingpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gronkokken.R
import com.example.gronkokken.repository.UserViewModel
import com.example.gronkokken.ui.components.LandingButton

//Lukas
@Composable
fun LandingpageTeacher(
    userViewModel: UserViewModel,
    loginButtonClick: () -> Unit,
    registerButtonClick: () -> Unit,
    onBackArrowClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFA0ED6E))
            .padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            //backarrow that removes the current screen from the stack
            Icon(
                painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "back arrow",
                modifier = Modifier
                    .clickable { onBackArrowClick() }
            )
        }
        Spacer(modifier = Modifier.height(150.dp))
        //logo
        Image(
            painter = painterResource(R.drawable.landingpagelogo),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        //takes you to login screen
        LandingButton(
            "Login",
            166,
            48,
            0xFF121212,
            25
        ) { loginButtonClick() }

        Spacer(modifier = Modifier.height(50.dp))

        //takes you to the register screen
        LandingButton(
            "Opret",
            166,
            48,
            0xFF121212,
            25
        ) { registerButtonClick() }


    }
}