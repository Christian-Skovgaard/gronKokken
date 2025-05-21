package com.example.gronkokken.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
//Lukas
//textfield with a dropshadow
fun RegisterTextField() {

    var text by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            //the drawbehind is what makes the shadow, by making a rectangle behind the textfield
            .drawBehind {
                val shadowColor = Color(0xFF121212).copy(alpha = 0.2f)
                val shadowHeight = 20.dp.toPx()
                drawRoundRect(
                    color = shadowColor,
                    topLeft = Offset(0f,  60f),
                    size = Size(size.width, shadowHeight),
                    cornerRadius = CornerRadius(25.dp.toPx(), 25.dp.toPx())
                )
            }
    ) {
        //textfield
        BasicTextField(
            value = text,
            onValueChange = {text = it},
            textStyle = TextStyle.Default.copy(fontSize = 16.sp),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(15.dp))
                .height(37.dp)
                .padding(vertical = 6.dp, horizontal = 16.dp)
        )
    }


}