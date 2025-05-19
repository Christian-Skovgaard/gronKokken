package com.example.gronkokken


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Register : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContent {
                RegisterPage(onClick = {})
            }
    }
}

@Composable
fun RegisterPage(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        ) {
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
            Spacer(modifier = Modifier.height(60.dp))
            OutlinedText(
                text = "LOGIN",
                fontSize = 64.sp,
                outlineColor = Color.Black,
                textColor = Color(0xFF4B7A2B)
            )
        }
        Spacer(modifier = Modifier.height(70.dp))
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

@Composable
fun OutlinedText(
    text: String,
    fontSize: TextUnit = 40.sp,
    outlineColor: Color = Color.Black,
    textColor: Color = Color.White,
    strokeWidth: Float = 5f,
    fontWeight: FontWeight = FontWeight.Bold
) {
    Box {
        // Tegn omridset ved at tegne teksten flere gange med forskydning
        val offsets = listOf(
            Offset(-strokeWidth, -strokeWidth),
            Offset( strokeWidth, -strokeWidth),
            Offset(-strokeWidth,  strokeWidth),
            Offset( strokeWidth,  strokeWidth)
        )
        for (offset in offsets) {
            Text(
                text = text,
                fontSize = fontSize,
                color = outlineColor,
                fontWeight = fontWeight,
                modifier = Modifier.graphicsLayer {
                    translationX = offset.x
                    translationY = offset.y
                }
            )
        }

        // Tegn den faktiske tekst øverst
        Text(
            text = text,
            fontSize = fontSize,
            color = textColor,
            fontWeight = fontWeight,
        )
    }
}

@Composable
fun RegisterTextField() {

    var text by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
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

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    RegisterPage(onClick = {})
}

