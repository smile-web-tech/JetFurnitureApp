package com.example.jetpack3

import android.graphics.drawable.shapes.OvalShape
import android.graphics.fonts.FontFamily
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack3.ui.theme.BackgroundColor
import com.example.jetpack3.ui.theme.Purple40
import com.example.jetpack3.ui.theme.SolakColor

@Composable
fun WelcomeScreen(onStartClicked: () -> Unit){
    Column(
        modifier = Modifier.fillMaxSize().background(color = BackgroundColor).padding(24.dp)
    ) {
        Text(text = "Elegant \nSimple Furnitures.",
            fontSize = 45.sp,
            fontFamily = Myfonts,
            letterSpacing = 2.sp

        )
        Spacer(modifier = Modifier.size(24.dp))

        Image(
            painter = painterResource(id = R.drawable.black_chair),
            contentDescription = "Black modern chair",
            modifier = Modifier
                .size(420.dp)
            ,        )
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.padding(top = 24.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Affordable home furniture \ndesigns & ideas",
                fontSize = 14.sp,
                color = SolakColor
                )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {onStartClicked()},
                modifier = Modifier
                    .size(100.dp)
                    .aspectRatio(1f)
                ,
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)) {
                Text("START", fontFamily = everett, fontSize = 14.sp)
            }
        }
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(onStartClicked = {}) // <--- Empty {} just for preview
}
