package com.example.gronkokken.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
//Lukas
//to make outlinedtext i made the text 4 times, 1 going up, 1 going down, 1 to the right
// and 1 to the left.
fun OutlinedText(
    text: String,
    fontSize: TextUnit = 40.sp,
    outlineColor: Color = Color.Black,
    textColor: Color = Color.White,
    strokeWidth: Float = 5f,
    fontWeight: FontWeight = FontWeight.Bold
) {
    Box {
        // make the outline by offsetting the text 4 times
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

        // the actual text i want on top
        Text(
            text = text,
            fontSize = fontSize,
            color = textColor,
            fontWeight = fontWeight,
        )
    }
}