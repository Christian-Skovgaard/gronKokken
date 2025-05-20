package com.example.gronkokken

import android.util.EventLogTags.Description
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Landingpage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFA0ED6E)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Image(
            painter = painterResource(R.drawable.landingpagelogo),
            contentDescription = "",
            modifier = Modifier
                .size(350.dp)
        )


    }
}

@Preview
@Composable
fun LandingpagePreview() {
    Landingpage()
}