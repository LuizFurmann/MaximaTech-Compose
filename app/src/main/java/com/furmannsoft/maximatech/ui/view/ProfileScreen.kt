package com.furmannsoft.maximatech.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProlifeScreen(modifier: Modifier = Modifier) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp)
            .wrapContentHeight(align = Alignment.CenterVertically),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = "Perfil",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}