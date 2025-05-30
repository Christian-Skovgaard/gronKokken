package com.example.gronkokken.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gronkokken.repository.UserViewModel

//Lukas
//button with parameters to customize it.
@Composable
fun LandingButton(
    text: String,
    width: Int,
    height: Int,
    buttonColor: Long,
    fontSize: Int,
    buttonOnClick: () -> Unit
) {
    Button(
        onClick = {buttonOnClick()},
        modifier = Modifier
            .offset(0.dp, 35.dp)
            .width(width.dp)
            .height(height.dp),
        border = BorderStroke(2.dp, Color(buttonColor)),
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF8F7FF),
            contentColor = Color(0xff121212)
        )
    ) {
        Text(
            text = text,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold,
            color = Color(buttonColor)
        )
    }
}