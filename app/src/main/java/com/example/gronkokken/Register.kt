package com.example.gronkokken


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gronkokken.ui.theme.GronKokkenTheme

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

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            OutlinedText(
                text = "OPRET",
                fontSize = 64.sp,
                outlineColor = Color.Black,
                textColor = Color(0xFF4B7A2B)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
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
                modifier = Modifier.padding(top = 10.dp)
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
                    text = "yo",
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

    var text by remember { mutableStateOf("yo") }

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

        TextField(
            value = text,
            onValueChange = {text = it},
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(15.dp))
                .height(37.dp)

            ,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,

                )

        )
    }


}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    RegisterPage(onClick = {})
}

