package com.example.gronkokken.ui.pages.login


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gronkokken.R
import com.example.gronkokken.ui.components.OutlinedText
import com.example.gronkokken.ui.components.RegisterTextField

//Lukas
@Composable
fun LoginPage(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        ) {
        //back arrow
        Icon(
            painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
            contentDescription = "back arrow"
        )
        Spacer(modifier = Modifier.height(80.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Login text
            Spacer(modifier = Modifier.height(60.dp))
            OutlinedText(
                text = "LOGIN",
                fontSize = 64.sp,
                outlineColor = Color.Black,
                textColor = Color(0xFF4B7A2B)
            )
        }
        Spacer(modifier = Modifier.height(70.dp))

        //blå box til mail og password
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .background(Color(0xFF69BFFF), shape = RoundedCornerShape(15.dp))
            .padding(20.dp),
        ) {
            Text(
                text = "Mail",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            RegisterTextField()
            Text(
                text = "Password",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 25.dp)
            )
            RegisterTextField()
            Text(
                text = "Glemt password?",
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.padding(top = 6.dp)
            )

        }

        Spacer(modifier = Modifier.height(14.dp))


        Column(
            modifier = Modifier
                .fillMaxWidth()
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //login knap
            Button(
                onClick = {onClick()},
                modifier = Modifier
                    .offset(0.dp, 35.dp)
                    .width(166.dp)
                    .height(48.dp),
                border = BorderStroke(2.dp, Color(0xFF121212)),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF69BFFF),
                    contentColor = Color(0xff121212)
                )
                ) {
                Text(
                    text = "Login",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    )
            }

        }
    }


}


@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    LoginPage(onClick = {})
}

