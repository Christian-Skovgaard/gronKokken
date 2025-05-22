package com.example.gronkokken.ui.pages.Register


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gronkokken.R
import com.example.gronkokken.ui.components.OutlinedText
import com.example.gronkokken.ui.components.RegisterTextField

class Register : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContent {
                RegisterPage(onClick = {}, onBackArrowClick = {})
            }
    }
}


//Lukas
@Composable
fun RegisterPage(onClick: () -> Unit, onBackArrowClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        ) {
        //backarrow that removes the current screen from the stack
        Icon(
            painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
            contentDescription = "back arrow",
            modifier = Modifier
                .clickable { onBackArrowClick() }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //outlined text at the top
            Spacer(modifier = Modifier.height(60.dp))
            OutlinedText(
                text = "OPRET",
                fontSize = 64.sp,
                outlineColor = Color.Black,
                textColor = Color(0xFF4B7A2B)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))

        //column with mail and password
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(243.dp)
            .background(Color(0xFFFFBA27), shape = RoundedCornerShape(15.dp))
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
                modifier = Modifier.padding(top = 10.dp)
            )
            RegisterTextField()
            Text(
                text = "Bekræft password",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 10.dp)
            )
            RegisterTextField()

        }

        Spacer(modifier = Modifier.height(30.dp))

        //column with school name and class name
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(243.dp)
            .background(Color(0xFFFFBA27), shape = RoundedCornerShape(15.dp))
            .padding(20.dp),
        ) {
            Text(
                text = "Skole",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )
            RegisterTextField()
            Text(
                text = "Klassenavn",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 10.dp)
            )
            RegisterTextField()
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Register button
            Button(
                onClick = {onClick()},
                modifier = Modifier
                    .offset(0.dp, 35.dp)
                    .width(166.dp),
                border = BorderStroke(2.dp, Color(0xFF121212)),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFBA27),
                    contentColor = Color(0xff121212)
                )
                ) {
                Text(
                    text = "Opret",
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
    RegisterPage(onClick = {}, onBackArrowClick = {})
}

