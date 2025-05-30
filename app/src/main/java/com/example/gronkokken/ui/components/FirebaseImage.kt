package com.example.gronkokken.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.gronkokken.repository.Firestore
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

//Lukas
@Composable
fun FirebaseImage(imagePath: String, modifier: Modifier) {
    var imageUrl by remember { mutableStateOf<String?>(null) }

    val firestore = Firestore()

    LaunchedEffect(imagePath) {
        firestore.getImageUrl(imagePath) {
            imageUrl = it
        }
    }

    if (imageUrl != null) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = modifier,
            contentScale = ContentScale.Crop
        )
    } else {
        Text("Indlæser billede...")
    }
}